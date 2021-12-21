package br.com.alcatellucent.csm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Context;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.TrafficPolicy;
import br.com.alcatellucent.csm.bo.ContextBO;
import br.com.alcatellucent.csm.bo.SchedulingBO;
import br.com.alcatellucent.csm.bo.TrafficPolicyBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.TrafficPolicyForm;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;

public class TrafficPolicyAction extends CsmBaseAction {	
// Action nunca pode ter propriedade (manter estado)
//	TrafficPolicyBO trafficBO   = null;
//	SchedulingBO  schedulingBO  = null;
		
	@SuppressWarnings("unchecked")
	public TrafficPolicyAction() {
		super();
//		trafficBO = new TrafficPolicyBO();
//		schedulingBO = new SchedulingBO(); 
	}

	public ActionForward initial(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws ExceptionSys {
		String redir = FORWARD_INITIAL;
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			TrafficPolicyForm form = (TrafficPolicyForm) actionForm;
			TrafficPolicy trafficPolicy = form.getTrafficPolicy();
			form.setTrafficPolicy(trafficPolicy);
			form.clearForm();
		} else {
			redir = FORWARD_FORBIDDEN;
		}

		return actionMapping.findForward(redir);
	}

	@SuppressWarnings("unchecked")
	public ActionForward save(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws ExceptionSys, Exception {
		String redir = FORWARD_SUCCESS;
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			HttpSession session = request.getSession();
			TrafficPolicyForm form = (TrafficPolicyForm) actionForm;
			TrafficPolicy trafficPolicy = form.getTrafficPolicy();

			String contextId = request.getParameter("trafficPolicy.contextId");
			try {
				TrafficPolicyBO trafficBO = new TrafficPolicyBO();
				// Boolean isActive = ( trafficPolicy.getIsActiveForm()== null
				// || ! trafficPolicy.getIsActiveForm().equals("on") ? false :
				// true );
				// trafficPolicy.setIsActive(isActive);
				trafficBO.save(trafficPolicy);
				showMessages(request, "default.save.ok", MESSAGE_WARNING);
				// Karine - refresh tree
				refresh(request, TYPE_TRAFFICPOLICY, trafficPolicy.getId());

				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "traffic_policy.title", "key = "
						+ trafficPolicy.getId(), CsmLogSeverity.LOW_SEVERITY
						.getValue());

				ContextBO ctxBO = new ContextBO();
				Context ctx = ctxBO.edit(contextId);
				session.setAttribute("listTraffic", trafficBO.list(ctx));
				request.setAttribute("reloaderForm", "true");
				request.setAttribute("contextId", contextId);

			} catch (Exception e) {

				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "traffic_policy.title", "key = "
						+ trafficPolicy.getId(), CsmLogSeverity.FATAL_SEVERITY
						.getValue());

				showMessages(request, "default.save.error", MESSAGE_ERROR);
				// e.printStackTrace();
				logger.error("Error " + e);
			}
		} else {
			redir=FORWARD_FORBIDDEN;
		}

		return actionMapping.findForward(redir);
	}

	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws ExceptionSys {
		
		HttpSession session = request.getSession();
		ContextBO ctxBO = new ContextBO();
		TrafficPolicyBO trafficBO = new TrafficPolicyBO();
		String contextId =  request.getParameter("trafficPolicy.contextId");
		
		Context ctx = ctxBO.edit(contextId);
		request.setAttribute("contextId", contextId);		
		session.setAttribute("listTraffic", trafficBO.list(ctx));
		
		return actionMapping.findForward("list");
	}

	@SuppressWarnings("unchecked")
	public ActionForward edit(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws ExceptionSys {
		
		HttpSession session = request.getSession();

		TrafficPolicy trafficPolicy = new TrafficPolicy();
		TrafficPolicyForm form = (TrafficPolicyForm) actionForm;
		//FIXME This logic is very strange. Instantiate an objects and throw it away!
		//What happens if trafficPolicy is null ? 
		TrafficPolicyBO trafficBO = new TrafficPolicyBO();
		trafficPolicy = trafficBO.findById(form.getTrafficPolicy().getId());
		
		form.setTrafficPolicy(trafficPolicy);
		session.setAttribute("listTraffic", trafficBO.list(form.getTrafficPolicy().getId()));
		return actionMapping.findForward(FORWARD_INITIAL);
	}

	@SuppressWarnings("unchecked")
	public ActionForward delete(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws ExceptionSys, Exception {
		String redir = FORWARD_LIST;
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			HttpSession session = request.getSession();
			// TrafficPolicyBO trafficPolicyBO = new TrafficPolicyBO();
			TrafficPolicyForm form = (TrafficPolicyForm) actionForm;
			TrafficPolicy traffic = new TrafficPolicy();
			String contextId;
			TrafficPolicyBO trafficBO = new TrafficPolicyBO();
			try {
				traffic = trafficBO.findById(form.getTrafficPolicy().getId());
				contextId = traffic.getContextId();
				trafficBO.delete(form.getTrafficPolicy().getId());

				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "traffic_policy.title.delete", "KEY = "
						+ form.getTrafficPolicy().getId(),
						CsmLogSeverity.LOW_SEVERITY.getValue());

				showMessages(request, "default.delete.ok", MESSAGE_WARNING);

				// Karine - refresh tree
				refresh(request, TYPE_TRAFFIC, contextId.substring(0, 3));
			} catch (ExceptionSys e) {
				// redir = "falid";

				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "traffic_policy.title.delete.error",
						"KEY = " + form.getTrafficPolicy().getId()
								+ " error = " + e.getMessage(),
						CsmLogSeverity.FATAL_SEVERITY.getValue());

				showMessages(request, "default.delete.error", MESSAGE_ERROR);
				logger.error("Error " + e);
			}

			ContextBO ctxBO = new ContextBO();
			Context ctx = ctxBO.edit(form.getTrafficPolicy().getContextId());
			session.setAttribute("listTraffic", trafficBO.list(ctx));
		} else {
			redir = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}
	
	
	@SuppressWarnings("unchecked")
	public ActionForward view(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws ExceptionSys {
			 
		HttpSession session = request.getSession();
		String redir = FORWARD_EDIT;
		TrafficPolicyBO trafficBO = new TrafficPolicyBO();
		SchedulingBO schedulingBO = new SchedulingBO(); 
		TrafficPolicy trafficPolicy = new TrafficPolicy();
		TrafficPolicyForm form = (TrafficPolicyForm) actionForm;
		trafficPolicy = trafficBO.findById(form.getTrafficPolicy().getId());
		request.setAttribute("trafficPolicy", trafficPolicy);
		
		session.setAttribute("listScheduling",schedulingBO.listSchedulingPanel(form.getTrafficPolicy().getId()));
		
				
		return actionMapping.findForward(redir);
	}
}
