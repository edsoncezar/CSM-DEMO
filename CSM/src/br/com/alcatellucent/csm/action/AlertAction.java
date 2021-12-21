package br.com.alcatellucent.csm.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Acl;
import br.com.alcatellucent.csm.beans.Alert;
import br.com.alcatellucent.csm.beans.ProcessorPacket;
import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.bo.AclBO;
import br.com.alcatellucent.csm.bo.AclHistory;
import br.com.alcatellucent.csm.bo.AclHistoryBO;
import br.com.alcatellucent.csm.bo.AlertBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.AlertForm;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;

public class AlertAction extends CsmBaseAction{
	
	private int status = 0;
	private Collection<Alert> listAlerts = new ArrayList<Alert>();
	private String actionPerformed = null;
	
	public ActionForward initial(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		
		if (null == actionForm) {
			actionForm = new AlertForm();
		}
		
		AlertForm alertForm = (AlertForm) actionForm;
		AlertBO alertBO = new AlertBO();
		AclBO aclBO = new AclBO();
		String redir = "";
		Alert alert;
		Acl acl;
		String key = (String) request.getParameter("alertId");
		
		try {

			alert = alertBO.edit(key);
			alertForm.setAlert(alert);
			
			acl = aclBO.findAclByAlertId(alert.getId());
			alertForm.setAcl(acl);
			
			redir = FORWARD_INITIAL;
			
		} catch (ExceptionSys es) {
			redir = "falid";
		} catch (Exception e) {
			redir = "falid";
		}
		
		return actionMapping.findForward(redir);
		
	}
	
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request,
							  HttpServletResponse response) throws ExceptionSys, ServletException, IOException {
		
		String redir = "successAlert";
		
		AlertForm alertForm = (AlertForm) form;
		
		AlertBO alertBO = new AlertBO();
		Alert alert = alertForm.getAlert();
		
		AlertBO alertBo = new AlertBO();
		AclBO aclBO = new AclBO();
		
		// getting the user form session
		User user = new User();
		user = (User)request.getSession().getAttribute("userSession");
		
		try {
			listAlerts = alertBo.getAlertCollection(alert);
		} catch (ExceptionSys e) {
			throw e;
		}
		
		for (Alert a : listAlerts) {
			a.setStatus(this.status);
			try {
				this.saveLog(request, "alert.manual.close",
							"Action performed: " + actionPerformed + alert.getName(),
							CsmLogSeverity.LOW_SEVERITY.getValue());
				alertBO.save(a);
			} catch (ExceptionSys es) {
				redir = "falid";
			} catch (Exception e) {
				redir = "falid";
			}
		} 

		if (null != alertForm.getAcl().getId()) {
			try {
				Acl acl = alertForm.getAcl();
				acl.setStatus(status);
				aclBO.save(acl, user);
				this.saveAclLog(alertForm.getAcl(), request, 
					actionPerformed + alertForm.getAcl().getName(), 
					false);
			} catch (Exception e) {
				redir = "falid";
			}
		}
	
		return mapping.findForward(redir);
	}
						
	public void close(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			  HttpServletResponse response) throws ExceptionSys, ServletException, IOException {
		
		this.status = 1;
		this.actionPerformed = "Close Alert: ";
		
		this.save(mapping, form, request, response);
		
	}
	
	private void saveAclLog(Acl acl, HttpServletRequest request, String details, Boolean status) 
			throws ExceptionSys {

		User user = (User) request.getSession().getAttribute("userSession");

		AclHistory aclHistory = new AclHistory();
		aclHistory.setAclId(acl.getId());
		aclHistory.setAlertId(acl.getAlertId());
		aclHistory.setCsmUserId(user.getId());
		aclHistory.setDateTime(Calendar.getInstance());
		aclHistory.setDetails(details);
		aclHistory.setDppmId("***");
		aclHistory.setDppmName("");
		aclHistory.setStatusApplied(status);

		AclHistoryBO aclHistoryBO = new AclHistoryBO();

		try {
			aclHistoryBO.save(aclHistory);
		} catch (ExceptionSys es) {
			throw es;
		}

	}
}
