package br.com.alcatellucent.csm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.alde.management.client.ALdeException;
import br.com.alcatellucent.csm.beans.Alde;
import br.com.alcatellucent.csm.beans.AldeConfiguration;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.bo.AldeBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.AldeForm;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;

/**
 * 
 * @author Edson Moreira Cezar
 * @date 08/09/2007
 * @version 1.0
 * 
 * @description : This class represents actions for ALDE.
 */
public class AldeAction extends CsmBaseAction {

	public ActionForward initial(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		String redir = FORWARD_INITIAL;
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			AldeForm form = (AldeForm) actionForm;
			Alde alde = form.getAlde();
			alde.setContextId(request.getParameter("alde.contextId"));
		} else {
			redir = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	@SuppressWarnings("unchecked")
	public ActionForward save(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception {

		AldeBO aldeBO = new AldeBO();

		HttpSession session = request.getSession();
		String redir = FORWARD_LIST;
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			AldeForm form = (AldeForm) actionForm;
			Alde alde = form.getAlde();
			AldeConfiguration aldeConfig = form.getAldeConfig();
			try {
				aldeBO.savePlusConfig(alde, aldeConfig);
				showMessages(request, "default.save.ok", MESSAGE_WARNING);

				// Karine - refresh tree
				refresh(request, TYPE_ALDEPROBE, alde.getId());
			} catch (Exception e) {
				showMessages(request, "default.save.error", MESSAGE_ERROR);
				// redir = "falid";
			}

			// Igor - 2007-10-01 - Inclusão de Log da aplicação.
			this.saveLog(request, "alde.title", "key = " + alde.getId(), CsmLogSeverity.LOW_SEVERITY.getValue());

			session.setAttribute("listAlde", aldeBO.getAldesByContextId(form.getAlde().getContextId()));
		} else {
			redir = FORWARD_FORBIDDEN;
		}

		return actionMapping.findForward(redir);
	}

	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		// AldeForm form = (AldeForm) actionForm;
		AldeBO aldeBO = new AldeBO();
		HttpSession session = request.getSession();
		session.setAttribute("listAlde", aldeBO.getAldesByContextId(request.getParameter("alde.contextId")));
		return actionMapping.findForward(FORWARD_LIST);
	}

	@SuppressWarnings("unchecked")
	public ActionForward edit(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {

		String redir = "view";
		AldeBO aldeBO = new AldeBO();
		Alde alde = new Alde();
		AldeForm form = (AldeForm) actionForm;

		String id = form.getAlde().getId();
		alde = aldeBO.edit(id);
		aldeBO.loadConfig(alde, form.getAldeConfig());
		form.setAldeConfig((AldeConfiguration) alde.getAldeConfig().toArray()[0]);
		form.setAlde(alde);

		return actionMapping.findForward(redir);
	}

	@SuppressWarnings("unchecked")
	public ActionForward delete(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception {
		String redir = FORWARD_LIST;
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			AldeBO aldeBO = new AldeBO();
			AldeForm form = (AldeForm) actionForm;
			Alde alde = aldeBO.edit(form.getAlde().getId());
			HttpSession session = request.getSession();
			try {
				// form.setAlde(aldeBO.edit(request.getParameter("alde.id")));
				aldeBO.delete(form.getAlde());
				showMessages(request, "default.delete.ok", MESSAGE_WARNING);

				// Karine - refresh tree
				refresh(request, TYPE_DEVICE, alde.getContextId().substring(0, 12));
				// form.setRefresh("true");
			} catch (Exception e) {
				showMessages(request, "default.delete.error", MESSAGE_ERROR);
				// redir = FORWARD_FAILED;
			}
			// Igor - 2007-10-01 - Inclusão de Log da aplicação.
			this.saveLog(request, "alde.title.delete", "key = " + form.getAlde().getId(), CsmLogSeverity.LOW_SEVERITY
					.getValue());

			session.setAttribute("listAlde", aldeBO.getAldesByContextId(alde.getContextId()));
		} else {
			redir = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	public ActionForward applySettings(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception {
		String redir = "view";
		AldeBO aldeBO = null;
		Alde alde = null;
		AldeConfiguration aldeConfig = null;
		boolean configurationSaved = false;
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			AldeForm form = (AldeForm) actionForm;
			alde = form.getAlde();
			aldeConfig = form.getAldeConfig();
			aldeBO = new AldeBO();
			try {
				aldeBO.savePlusConfig(alde, aldeConfig);
				configurationSaved = true;
				showMessages(request, "default.save.ok", MESSAGE_WARNING);
			} catch (Exception e) {
				showMessages(request, "default.save.error", MESSAGE_ERROR);
			}

			if (configurationSaved) {
				try {
					aldeBO.applySettings(alde);
					showMessages(request, "erro.AldeForm.connectionAccept");
					form.setRefresh("true");
				} catch (ExceptionSys e) {
					logger.error("Error connecting to ALDE", e);
					showMessages(request, "erro.AldeForm.connectionRefused");
				} catch (ALdeException e) {
					logger.error("ALDE returned error", e);
					showMessages(request, "erro.AldeForm.connectionRefused");
				}

				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "alde.title.apply", "key = " + form.getAlde().getId(), CsmLogSeverity.LOW_SEVERITY
						.getValue());

			}
			// HttpSession session = request.getSession();
			// if (Integer.valueOf(form.getAldeConfig().getStatus()) ==
			// AldeStaticMode.LDE_SHOW_LOCAL_SETTINGS) {
			// String id = form.getAlde().getId();
			// alde = aldeBO.edit(id);
			// aldeConfig = aldeBO.findConfigurationByAldeId(alde.getId());
			// }
			// session.setAttribute("listAlde", aldeBO.findAldeId(form.getAlde()
			// .getContextId()));
		} else {
			redir = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	public ActionForward reset(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception {

		String forwardName = "view";

		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			try {
				AldeForm form = (AldeForm) actionForm;
				Alde alde = form.getAlde();
				AldeConfiguration aldeConfig = form.getAldeConfig();
				AldeBO aldeBO = new AldeBO();
				aldeBO.resetLearning(alde, aldeConfig);
				request.setAttribute("action", "edit");
				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "alde.title.reset", "key = " + form.getAlde().getId(), CsmLogSeverity.LOW_SEVERITY
						.getValue());
			} catch (ExceptionSys e) {
				showMessages(request, "erro.AldeForm.connectionRefused", MESSAGE_ERROR);
			}
		} else {
			forwardName = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(forwardName);
	}

	public ActionForward start(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {

		String forwardName = "view";

		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			String mode = request.getParameter("modeToStartStop");
			AldeForm form = (AldeForm) actionForm;
			Alde alde = form.getAlde();
			AldeConfiguration aldeConfig = form.getAldeConfig();
			AldeBO aldeBO = new AldeBO();
			aldeBO.startStop(alde, aldeConfig, mode, true);
			request.setAttribute("action", "edit");
		} else {
			forwardName = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(forwardName);
	}

	public ActionForward stop(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception {

		String forwardName = "view";

		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			String mode = request.getParameter("modeToStartStop");
			AldeForm form = (AldeForm) actionForm;
			Alde alde = form.getAlde();
			AldeConfiguration aldeConfig = form.getAldeConfig();
			AldeBO aldeBO = new AldeBO();
			aldeBO.startStop(alde, aldeConfig, mode, false);
			request.setAttribute("action", "edit");
			// Igor - 2007-10-01 - Inclusão de Log da aplicação.
			this.saveLog(request, "alde.title.stop", "key = " + form.getAlde().getId(), CsmLogSeverity.LOW_SEVERITY
					.getValue());
		} else {
			forwardName = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(forwardName);
	}

}
