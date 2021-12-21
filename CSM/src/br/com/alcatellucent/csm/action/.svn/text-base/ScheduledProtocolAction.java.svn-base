package br.com.alcatellucent.csm.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.ScheduledGroup;
import br.com.alcatellucent.csm.beans.ScheduledProtocol;
import br.com.alcatellucent.csm.beans.ScheduledTrafficValues;
import br.com.alcatellucent.csm.bo.ScheduledGroupBO;
import br.com.alcatellucent.csm.bo.ScheduledProtocolBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.ScheduledProtocolForm;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;

public final class ScheduledProtocolAction extends CsmBaseAction {

// Action nunca pode ter propriedade (manter estado)
//	private String retorno;

	public ActionForward initial(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		
		ScheduledProtocolForm scheduledProtocolForm = (ScheduledProtocolForm) actionForm;
		scheduledProtocolForm.setScheduledProtocol(new ScheduledProtocol());
		
		return actionMapping.findForward("initial");

	}

	public ActionForward save(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception{

		String retorno = "list";

		ScheduledProtocolForm scheduledProtocolForm = (ScheduledProtocolForm) actionForm;
		ScheduledGroup sGroup = scheduledProtocolForm.getScheduledGroup();
		
		ScheduledGroupBO sGroupBO 						= new ScheduledGroupBO();
		ScheduledProtocolBO protocolBO 					= new ScheduledProtocolBO();
		ScheduledProtocol scheduledProtocol 			= scheduledProtocolForm.getScheduledProtocol();
		ScheduledTrafficValues scheduledTrafficValues 	= scheduledProtocolForm.getScheduledProtocol().getScheduledTrafficValues();
		try {
			
			scheduledProtocol = protocolBO.edit(scheduledProtocol.getId());
			scheduledTrafficValues.setScheduledProtocol(scheduledProtocol);
			scheduledProtocol.setScheduledTrafficValues(scheduledTrafficValues);
			
			
			protocolBO.save(scheduledProtocol);

			// Igor - 2007-10-01 - Inclusão de Log da aplicação.
			this.saveLog(request, "scheduled_protocol.title", "key = " + scheduledProtocol.getId(), CsmLogSeverity.LOW_SEVERITY.getValue());
			
			retorno = "list";
			HttpSession session = request.getSession();
			sGroup = sGroupBO.edit(sGroup.getId());
			
			scheduledProtocolForm.setScheduledGroup(sGroup);
			
			
			showMessages(request, "default.save.ok", MESSAGE_WARNING);
			session.setAttribute("LIST_PROTOCOLS", sGroup.getScheduledProtocols());

		} catch (ExceptionSys ex) {
			
			// Igor - 2007-10-01 - Inclusão de Log da aplicação.
			this.saveLog(request, "scheduled_protocol.title.error", "key = " + scheduledProtocol.getId() + " error = " + ex.getMessage(), CsmLogSeverity.FATAL_SEVERITY.getValue());
			
			showMessages(request, "default.save.error", MESSAGE_ERROR);
//			retorno = "falid";
		}

		return actionMapping.findForward(retorno);
	}

	public ActionForward list(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String retorno = "list";
		HttpSession session = request.getSession();
		ScheduledProtocolBO protocolBO = new ScheduledProtocolBO();

		try {
			Collection<ScheduledProtocol> protocolList = protocolBO.list();
			session.setAttribute("LIST_PROTOCOL", protocolList);
		} catch (ExceptionSys ex) {
			ex.printStackTrace();
			retorno = "falid";
		}

		return actionMapping.findForward(retorno);
	}

	public ActionForward edit(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		String retorno = "initial";

		ScheduledProtocolForm scheduledProtocolForm = (ScheduledProtocolForm) actionForm;

		ScheduledProtocolBO protocolBO = new ScheduledProtocolBO();

		try {
			ScheduledProtocol protocol = protocolBO.edit(scheduledProtocolForm.getScheduledProtocol().getId());
			scheduledProtocolForm.setScheduledProtocol(protocol);
			

		} catch (ExceptionSys ex) {
			ex.printStackTrace();
			retorno = "falid";
		}

		return actionMapping.findForward(retorno);
	}

	public ActionForward delete(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception{

		HttpSession session = request.getSession();
		
		String retorno = "list";

		ScheduledProtocolForm scheduledProtocolForm = (ScheduledProtocolForm) actionForm;

		ScheduledProtocolBO scheduledProtocolBO = new ScheduledProtocolBO();
		
		try {
			scheduledProtocolBO.delete(scheduledProtocolForm.getScheduledProtocol().getId());

			// Igor - 2007-10-01 - Inclusão de Log da aplicação.
			this.saveLog(request, "scheduled_protocol.title.delete", "key = " + scheduledProtocolForm.getScheduledProtocol().getId(), CsmLogSeverity.LOW_SEVERITY.getValue());
			
			Collection<ScheduledProtocol> protocolList = scheduledProtocolBO.list();
			showMessages(request, "default.delete.ok", MESSAGE_WARNING);
			session.setAttribute("LIST_PROTOCOL", protocolList);
			
		} catch (ExceptionSys ex) {
			// Igor - 2007-10-01 - Inclusão de Log da aplicação.
			this.saveLog(request, "scheduled_protocol.title.delete.error", "key = " + scheduledProtocolForm.getScheduledProtocol().getId() + " error = " + ex.getMessage(), CsmLogSeverity.FATAL_SEVERITY.getValue());
			showMessages(request, "default.delete.error", MESSAGE_ERROR);
//			ex.printStackTrace();
//			retorno = "falid";
		}

		return actionMapping.findForward(retorno);
	}

}
