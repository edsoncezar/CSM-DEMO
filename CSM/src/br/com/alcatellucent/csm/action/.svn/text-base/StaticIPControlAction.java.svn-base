package br.com.alcatellucent.csm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.StaticIP;
import br.com.alcatellucent.csm.bo.StaticIPBO;
import br.com.alcatellucent.csm.bo.TrafficPolicyBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.StaticIPControlForm;

public class StaticIPControlAction extends CsmBaseAction {

	public ActionForward initial(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		String redir=FORWARD_INITIAL;
//		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			StaticIPControlForm form = (StaticIPControlForm) actionForm;
			StaticIP staticIP = form.getStaticIP();
			form.setStaticIP(new StaticIP());
			form.getStaticIP().setContextId(staticIP.getContextId());
//		}else{
//			redir=FORWARD_FORBIDDEN;
//		}
		loadLists(request, actionForm);
		return actionMapping.findForward(redir);
	}

	@SuppressWarnings("finally")
	public ActionForward save(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		String redir=FORWARD_INITIAL;
		StaticIPBO staticIPBO 		=  new StaticIPBO();
		StaticIPControlForm form 	= (StaticIPControlForm)actionForm;
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			StaticIP staticIP = form.getStaticIP();

			try {
				staticIPBO.save(staticIP);
				showMessages(request, "default.save.ok", MESSAGE_WARNING);
			} catch (ExceptionSys e) {
				showMessages(request, "default.save.error", MESSAGE_ERROR);
			}

			loadLists(request, actionForm);
		}else{
			redir=FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	
	}

	
	public ActionForward edit(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		
		StaticIPBO staticIPBO =  new StaticIPBO();
		StaticIPControlForm form = (StaticIPControlForm)actionForm;
		StaticIP staticIP =  form.getStaticIP();
		form.setStaticIP(staticIPBO.edit(staticIP.getId()));
		loadLists(request, actionForm);
		return actionMapping.findForward(FORWARD_INITIAL);
	}
	
	
	public ActionForward delete(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		
		StaticIPBO staticIPBO =  new StaticIPBO();
		String redir=FORWARD_INITIAL;
		StaticIPControlForm form = (StaticIPControlForm)actionForm;
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			StaticIP staticIP = form.getStaticIP();
			try {
				staticIPBO.delete(staticIP.getId());
				String ctxId = staticIP.getContextId();
				staticIP.setId("");
				form.setStaticIP(new StaticIP());
				form.getStaticIP().setContextId(ctxId);
				showMessages(request, "default.delete.ok", MESSAGE_WARNING);
			} catch (ExceptionSys e) {
				showMessages(request, "default.delete.error", MESSAGE_ERROR);

			}
			loadLists(request, actionForm);
		} else {
			redir=FORWARD_FORBIDDEN;
		}
		
		
		return actionMapping.findForward(redir);
	}
	
	
	protected void loadLists(HttpServletRequest request, ActionForm actionForm){
		TrafficPolicyBO trafficPolicyBO =  new TrafficPolicyBO();
		StaticIPBO staticIPBO =  new StaticIPBO();
		StaticIPControlForm form = (StaticIPControlForm)actionForm;
		try {
			request.getSession().setAttribute("LIST_STATICIPS", staticIPBO.getStaticIPsByContextId(form.getStaticIP().getContextId()));
			request.getSession().setAttribute("LIST_TRAFFICPOLICY", trafficPolicyBO.listTraffic(form.getStaticIP().getContextId()));
		} catch (ExceptionSys e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
