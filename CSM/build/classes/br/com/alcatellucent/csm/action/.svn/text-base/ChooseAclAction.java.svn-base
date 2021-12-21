package br.com.alcatellucent.csm.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Acl;
import br.com.alcatellucent.csm.beans.ProcessorPacket;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.bo.AclBO;
import br.com.alcatellucent.csm.bo.ProcessorPacketBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.AclForm;

/**
 * 
 * @author Edson Moreira Cezar
 * @date 08/30/2007
 * @version 1.0
 * 
 * @description : This class represents Choose ACL.
 */
public class ChooseAclAction extends CsmBaseAction {

	public ActionForward initial(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {

		HttpSession session = request.getSession();
		request.getParameter("name");
		String redir = FORWARD_INITIAL;
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			AclBO aclBO = new AclBO();
			AclForm form = (AclForm) actionForm;
			Acl acl = form.getAcl();
			acl.setId("");
			acl.setDescription("");

			String contextId = aclBO.findContextId(form);
			if (contextId == null) {
				logger.fatal("Could not find context for device in context " + form.getAcl().getContextId());
				return actionMapping.findForward(FORWARD_ERROR);
			}

			session.setAttribute("listAcl", aclBO.findAclByCriteria(contextId));
		} else {
			redir = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	public ActionForward applyAcl(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception {
		request.getParameter("name");
		AclBO aclBO = new AclBO();
		AclForm form = (AclForm) actionForm;
		Acl acl = form.getAcl();
		acl.setId("");
		acl.setDescription("");

		ArrayList<Acl> aclList = new ArrayList<Acl>();
		ProcessorPacket processorPacket = null;

		ProcessorPacketBO processorPacketBO = new ProcessorPacketBO();
		processorPacket = processorPacketBO.edit(acl.getContextId());
		if (acl.getAclId() != null) {
			for (int i = 0; i < acl.getAclId().length; i++) {
				aclList.add(aclBO.listAcl(acl.getAclId()[i]));
			}
		}
		try {
			// Setting acl configurations ...

			// getting the user form session
			User user = new User();
			user = (User) request.getSession().getAttribute("userSession");

			aclBO.setAcl(aclList, processorPacket, user);
			// OK
			showMessages(request, "message.chooseAcl.sucessfully", MESSAGE_WARNING);
		} catch (Exception ex) {
			// if an error occurs..
			showMessages(request, "error.chosseAcl.fail", MESSAGE_ERROR);
		}

		return actionMapping.findForward(FORWARD_INITIAL);
	}
}
