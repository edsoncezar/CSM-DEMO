package br.com.alcatellucent.csm.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Acl;
import br.com.alcatellucent.csm.beans.AldeConfiguration;
import br.com.alcatellucent.csm.beans.Alert;
import br.com.alcatellucent.csm.beans.Context;
import br.com.alcatellucent.csm.beans.ProcessorPacket;
import br.com.alcatellucent.csm.beans.Protocol;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.bo.AclBO;
import br.com.alcatellucent.csm.bo.AldeBO;
import br.com.alcatellucent.csm.bo.AlertBO;
import br.com.alcatellucent.csm.bo.ContextBO;
import br.com.alcatellucent.csm.bo.ProcessorPacketBO;
import br.com.alcatellucent.csm.bo.ProtocolBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.AclForm;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;

/**
 * @author Edson
 * 
 */
public class AclAction extends CsmBaseAction {
	// Action nunca pode ter propriedade (manter estado)
	// AclBO aclBO = null;
	// ProtocolBO pBO = null;
	// AldeBO aldeBO = null;
	// ProcessorPacketBO packetBO = null;

	public AclAction() {
		super();
	}

	public ActionForward initial(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
	
		String redir = FORWARD_INITIAL;

		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			HttpSession session = request.getSession();
			AclForm form = (AclForm) actionForm;
			Acl acl = new Acl();
			ProtocolBO pBO = new ProtocolBO();
			acl.setContextId(form.getAcl().getContextId());

			boolean isAlert = (request.getParameter("action") == "alert" ? true : false);
			acl.setIsAldeAcl(isAlert);

			form.setAcl(acl);

			session.setAttribute("listProtocol", pBO.list(true));
		} else {
			redir = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	@SuppressWarnings("unchecked")
	public ActionForward save(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception {
		String redir = "";
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			HttpSession session = request.getSession();
			
			// getting the user form session
			User user = new User();
			user = (User)request.getSession().getAttribute("userSession");
			
			AclForm form = (AclForm) actionForm;
			AclBO aclBO = new AclBO();
			Acl acl = form.getAcl();


			try {
				// acl.setIsAldeAcl(isAldeAcl);
				aclBO.save(acl, user);
				if (!(acl.getIsAldeAcl())) {
					redir = FORWARD_SUCCESS;
					showMessages(request, "default.save.ok", MESSAGE_WARNING);

					// Karine - refresh tree
					refresh(request, TYPE_ACLPLUS, acl.getId());
				} else {
					redir = "successAlert";
				}
				// Igor - 2007-10-02 - Inclusão de Log da aplicação.
				this.saveLog(request, "acl.title", "key = " + acl.getId(), CsmLogSeverity.LOW_SEVERITY.getValue());

				// form.setRefresh("true");
				redir = FORWARD_LIST;
			} catch (Exception e) {

				// Igor - 2007-10-02 - Inclusão de Log da aplicação.
				this.saveLog(request, "acl.title", "key = " + acl.getId() + " error = " + e.getMessage(),
						CsmLogSeverity.FATAL_SEVERITY.getValue());

				showMessages(request, "default.save.error", MESSAGE_ERROR);
				// e.printStackTrace();
				// redir = "falid";
			}

			session.setAttribute("listAcl", aclBO.list(acl.getContextId(), acl.getIsAldeAcl()));
		} else {
			redir = FORWARD_FORBIDDEN;
		}
		// request.setAttribute("reloaderForm",true);
		return actionMapping.findForward(redir);
	}

	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {

		HttpSession session = request.getSession();
		AclForm form = (AclForm) actionForm;
		AclBO aclBO = new AclBO();

		if (request.getParameter("ini") != null) {
			form.getAcl().setStatus(1);

			if (request.getParameter("acl.isAldeAcl").equalsIgnoreCase("alert")) {
				session.setAttribute("alert", true);
			} else {
				session.setAttribute("alert", false);
			}
		}

		form.getAcl().setIsAldeAcl((Boolean) session.getAttribute("alert"));
		Boolean isAldeAcl = form.getAcl().getIsAldeAcl() == null ? false : form.getAcl().getIsAldeAcl();
		Integer statusAcl = form.getAcl().getStatus() == null ? 1 : form.getAcl().getStatus();

		form.setAcl(form.getAcl());
		session.setAttribute("listAcl", aclBO.list(form.getAcl().getContextId(), isAldeAcl, statusAcl));
		return actionMapping.findForward(FORWARD_LIST);
	}

	@SuppressWarnings("unchecked")
	public ActionForward edit(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {

		HttpSession session = request.getSession();

		AclBO aclBO = new AclBO();
		AclForm form = (AclForm) actionForm;
		Acl aclId = form.getAcl();
		Acl acl = aclBO.edit(aclId.getId());
		ProtocolBO pBO = new ProtocolBO();

		String isAldeAcl = "";
		if (acl.getIsAldeAcl() == null) {
			isAldeAcl = "false";
		} else {
			isAldeAcl = (acl.getIsAldeAcl() == false ? "false" : "true");
		}

		acl.setIsAldeAclForm(isAldeAcl);
		form.setAcl(acl);
		request.setAttribute("acl", acl);
		session.setAttribute("listProtocol", pBO.list(true));

		return actionMapping.findForward("view");
	}

	@SuppressWarnings("unchecked")
	public ActionForward delete(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception {
		String redir = "";
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			HttpSession session = request.getSession();
			
			User user = (User)(session.getAttribute("userSession")); 
			
			AclBO aclBO = new AclBO();
			AclForm form = (AclForm) actionForm;
			// Apesar do nome ser aclId parecer que contem o Id, o mesmo contem o objeto ACL. Igor.
			Acl aclId = form.getAcl();
			redir = FORWARD_SUCCESS;

			try {
				//aclBO.delete(request.getParameter("acl.id"));
				aclBO.delete(aclId, user);

				// Igor - 2007-10-02 - Inclusão de Log da aplicação.
				this.saveLog(request, "acl.title", "key = " + request.getParameter("acl.id"), CsmLogSeverity.LOW_SEVERITY
						.getValue());

				showMessages(request, "default.delete.ok", MESSAGE_WARNING);
				// aclBO.delete(aclId.getId());
				if (!aclId.getIsAldeAcl()) {
					// Karine - refresh tree
					refresh(request, TYPE_ACL, form.getAcl().getContextId().substring(0, 4));
				}
				redir = FORWARD_LIST;

			} catch (Exception e) {

				// Igor - 2007-10-02 - Inclusão de Log da aplicação.
				this.saveLog(request, "acl.title", "key = " + request.getParameter("acl.id"), CsmLogSeverity.LOW_SEVERITY
						.getValue());

				showMessages(request, "default.delete.error", MESSAGE_ERROR);
				// redir = "falid";
				logger.error(e);
			}
			session.setAttribute("listAcl", aclBO.list(aclId.getContextId(), aclId.getIsAldeAcl()));
			// request.setAttribute("reloaderForm",true);
		} else {
			redir = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	/**
	 * @author Igor Ivanoff Takats
	 * @since 2007-09-01
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param request
	 * @param response
	 * @return
	 * @throws ExceptionSys
	 */

	public ActionForward alert(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception {

		String redir = FORWARD_INITIAL;

		HttpSession session = request.getSession();

		AldeBO aldeBO = new AldeBO();
		ProcessorPacketBO packetBO = new ProcessorPacketBO();
		Alert alert = new Alert(); // Isto aqui está estranho
		AlertBO alertBO = new AlertBO();
		Acl acl = null;

		try {
			alert = alertBO.edit(request.getParameter("alertId"));
		} catch (ExceptionSys e) {
			throw e;
		}
		
		try {
			AclBO aclBo = new AclBO();
			acl = aclBo.findAclByAlertId(alert.getId());
		} catch (Exception e) {
			throw e;
		}

		ProtocolBO pBO = new ProtocolBO();
		Collection<Protocol> protocolList = pBO.list(true);

		if (null == acl || null == acl.getId()) {
			acl = buildNewAclAlert(alert, protocolList);
		}

		request.setAttribute("flagAlert", true);

		AclForm form = (AclForm) actionForm;
		form.setAcl(acl);

		session.setAttribute("listProtocol", protocolList);

		AldeConfiguration config = null;
		config = aldeBO.findAldeIdByAlert(alert.getAlde());

		Collection<ProcessorPacket> packet = null;
		packet = packetBO.findByAldeId(config.getAlde().getId());

		request.setAttribute("LIST_DPPM_ALERT", packet);

		return actionMapping.findForward(redir); // Is this right ?
	}

	private Acl buildNewAclAlert(Alert alert, Collection<Protocol> protocolList) throws ExceptionSys {
		
		Acl acl = new Acl();

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(alert.getTimeStampMessage());

		SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sTime = new SimpleDateFormat("hh:mm:ss");
		String fDate = null;
		String fTime = null;

		try {
			fDate = sDate.format(cal.getTime());
			fTime = sTime.format(cal.getTime());

		} catch (Exception e) {
		}

		Integer aclAction = null;

		// String context = null;

		ContextBO contextBO = new ContextBO();
		Context ctx = null;

		try {
			ctx = contextBO.findRoot();
		} catch (ExceptionSys e) {
			throw e;
		}

		if (alert.getRule().equalsIgnoreCase("drop")) {
			aclAction = 2;
		} else {
			aclAction = 1;
		}

		String[] aclIds = {};
		acl.setName("ALDE alert at: " + fDate + " " + fTime);
		acl.setDescription("ALDE generated by alert " + alert.getAlde() + " at alert date/time: " + fDate + " " + fTime);
		acl.setPriority(1);
		acl.setAclId(aclIds);
		acl.setAction(aclAction);
		acl.setContextId(ctx.getId());
		acl.setDestIp(alert.getDestinationIp());
		if (Integer.parseInt(acl.getDestIp().substring(acl.getDestIp().lastIndexOf('.') + 1)) != 0) {
			acl.setDestIpMask("255.255.255.255");
		} else {
			acl.setDestIpMask("255.255.255.0");
		}
		acl.setDestPort(alert.getDestinationPort());
		acl.setId("");
		acl.setIsAldeAcl(true);
		acl.setPriority(1);
		for (Protocol protocol : protocolList) {
			if (protocol.getName().equalsIgnoreCase(alert.getProtocol() == 0 ? "UDP" : "TCP")) {
				acl.setProtocol(protocol.getId());
			}
		}
		acl.setProtocolInternalNumber(0);
		acl.setSourceIp(alert.getSourceIp());
		if (alert.getSourceIp().equals("IPSPOOF")) {
			acl.setSourceIpMask("0.0.0.0");
		} else {
			acl.setSourceIpMask("255.255.255.255");
		}
		acl.setSourcePort(0);
		acl.setStatus(1);
		acl.setThreshold(alert.getRule().equalsIgnoreCase("drop") ? 0 : Integer.parseInt(alert.getRule()));
		acl.setTimeToLive(240);
		acl.setAlertId(alert.getId());
		
		return acl;
		
	}
	
	public void refuse(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception {

		AclForm form = (AclForm) actionForm;

		form.getAcl().setStatus(3);

		this.save(actionMapping, actionForm, request, response);
	}
	

}
