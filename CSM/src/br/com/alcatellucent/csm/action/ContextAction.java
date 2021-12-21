package br.com.alcatellucent.csm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Context;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.bo.ContextBO;
import br.com.alcatellucent.csm.bo.TrafficPolicyBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.ContextForm;

public class ContextAction extends CsmBaseAction {

	public ActionForward initial(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {

		String redir = FORWARD_INITIAL;
		HttpSession session = request.getSession();
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE, Role.OPERATOR_ROLE }, false)) {
			ContextForm form = (ContextForm) actionForm;
			TrafficPolicyBO traffPolicyBO = new TrafficPolicyBO();

			session.setAttribute("listTraffic", traffPolicyBO.listTraffic(form.getContext().getParentId()));
			form.reset();
		} else {
			redir = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	public ActionForward list(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		String redir = FORWARD_LIST;
		ContextBO contextBO = new ContextBO();
		// if (checkCredentials(request, new String[] {
		// Role.GUEST_ROLE,Role.OPERATOR_ROLE}, false)) {
		HttpSession session = request.getSession();
		ContextForm form = (ContextForm) actionForm;
		session.setAttribute("listContext", contextBO.getListContextChildren(form.getContext().getId(), true));
		// }else{
		// redir=FORWARD_FORBIDDEN;
		// }
		return actionMapping.findForward(redir);
	}

	public ActionForward save(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {

		HttpSession session = request.getSession();
		String redir = FORWARD_LIST;
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE, Role.OPERATOR_ROLE }, false)) {

			ContextForm form = (ContextForm) actionForm;
			ContextBO contextBO = new ContextBO();
			Context context = form.getContext();

			try {
				contextBO.save(context);
				showMessages(request, "default.save.ok", MESSAGE_WARNING);
				refresh(request, TYPE_CONTEXT, context.getId());

				session.setAttribute("listContext", contextBO.getListContextChildren(context.getId(), true));
				TrafficPolicyBO traffPolicyBO = new TrafficPolicyBO();

				session.setAttribute("listTraffic", traffPolicyBO.listTraffic(context.getId()));

			} catch (Exception e) {

				if (e.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					// showMessages(request, "error.context.delete.Contraint",
					// super.ERROR_MESSAGE);
					redir = "view";
					showMessages(request, "error.context.duplicated", MESSAGE_ERROR);
				} else {
					showMessages(request, "default.delete.error", MESSAGE_ERROR);
				}

				// e.printStackTrace();
				// redir = "falid";
			}
		} else {
			redir = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	public ActionForward edit(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {

		ContextForm form = (ContextForm) actionForm;
		HttpSession session = request.getSession();
		String redir = "view";
		ContextBO contextBO = new ContextBO();
		form.setContext(contextBO.edit(form.getContext().getId()));
		TrafficPolicyBO traffPolicyBO = new TrafficPolicyBO();
		if (form.getContext().getParentId().equals("0")) {

			session.setAttribute("listTraffic", traffPolicyBO.listTraffic(form.getContext().getId()));
		} else {
			session.setAttribute("listTraffic", traffPolicyBO.listTraffic(form.getContext().getParentId()));
		}
		return actionMapping.findForward(redir);
	}

	public ActionForward delete(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {

		String redir = FORWARD_LIST;

		if (checkCredentials(request, new String[] { Role.GUEST_ROLE, Role.OPERATOR_ROLE }, false)) {
			ContextForm form = (ContextForm) actionForm;
			ContextBO contextBO = new ContextBO();
			// Igor 2007-10-05: FS#82: Erro na deleção de contextos
			Context deletedContext = contextBO.edit(form.getContext().getId());
			// Context deletedContext = contextBO.edit(request.getParameter("id"));
			try {
				contextBO.delete(form.getContext().getId());
				showMessages(request, "default.delete.ok", MESSAGE_WARNING);
				// function to refresh tree
				refresh(request, TYPE_CONTEXT, deletedContext.getParentId());
			} catch (Exception e) {
				if (e.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					// showMessages(request, "error.context.delete.Contraint",
					// super.ERROR_MESSAGE);
					showMessages(request, "error.context.delete.Contraint", CsmBaseAction.MESSAGE_WARNING);
				} else
					showMessages(request, "default.delete.error", MESSAGE_ERROR);
			}
			HttpSession session = request.getSession();
			session.setAttribute("listContext", contextBO.getListContextChildren(deletedContext.getParentId(), true));
		} else {
			redir = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}
}
