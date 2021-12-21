package br.com.alcatellucent.csm.action;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Protocol;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.bo.ProtocolBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.ProtocolForm;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;

public final class ProtocolAction extends CsmBaseAction {


// Action nunca pode ter propriedade (manter estado)
//	private String retorno;

	public ActionForward initial(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		String redir = FORWARD_INITIAL;
		if(checkCredentials(request, new String[]{Role.MASTER_ROLE}, true)){
			ProtocolForm protocolForm = (ProtocolForm) actionForm;
			protocolForm.setProtocol(new Protocol());
			ProtocolBO protocolBO = new ProtocolBO();
			List<String> usedNumbers = protocolBO.getUsedInternalNumbers();
			request.setAttribute("USED_NUMBERS", usedNumbers);
		}else{
			redir=FORWARD_FORBIDDEN;
		}
	return actionMapping.findForward(redir);

	}

	public ActionForward save(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception {

		String retorno = FORWARD_LIST;
		if(checkCredentials(request, new String[]{Role.MASTER_ROLE}, true)){
			HttpSession session = request.getSession();
			ProtocolForm protocolForm = (ProtocolForm) actionForm;

			ProtocolBO protocolBO = new ProtocolBO();

			try {
				protocolBO.save(protocolForm.getProtocol());
				
				// Igor - 2007-10-02 - Inclusão de Log da aplicação.
				this.saveLog(request, "protocol.title", "key = " + protocolForm.getProtocol().getId(), CsmLogSeverity.LOW_SEVERITY.getValue());
				
				
				retorno = "list";
				
				
				showMessages(request, "message.save.ok");
			} catch (ExceptionSys ex) {
				// Igor - 2007-10-02 - Inclusão de Log da aplicação.
				this.saveLog(request, "protocol.title", "key = " + protocolForm.getProtocol().getId(), CsmLogSeverity.FATAL_SEVERITY.getValue());
				if (ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) 
					showMessages(request, "error.protocol.save.Contraint", MESSAGE_WARNING);
				else showMessages(request, "default.save.error", MESSAGE_ERROR);
			}
			Collection<Protocol> protocolList = protocolBO.list();
			session.setAttribute("LIST_PROTOCOL", protocolList);
		}else{
			retorno=FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(retorno);
	}

	public ActionForward list(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String retorno = FORWARD_LIST;
		HttpSession session = request.getSession();
		ProtocolBO protocolBO = new ProtocolBO();

		try {
			Collection<Protocol> protocolList = protocolBO.list();
			session.setAttribute("LIST_PROTOCOL", protocolList);
		} catch (ExceptionSys ex) {
			ex.printStackTrace();
			retorno = "falid";
		}

		return actionMapping.findForward(retorno);
	}

	public ActionForward edit(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {

			String retorno = FORWARD_INITIAL;
			ProtocolForm protocolForm = (ProtocolForm) actionForm;

			ProtocolBO protocolBO = new ProtocolBO();

			try {
				Protocol protocol = protocolBO.edit(protocolForm.getProtocol().getId());
				protocolForm.setProtocol(protocol);
				
				List<String> usedNumbers = protocolBO.getUsedInternalNumbers();
				request.setAttribute("USED_NUMBERS", usedNumbers);
			} catch (ExceptionSys ex) {
				ex.printStackTrace();
				retorno = "falid";
			}
		return actionMapping.findForward(retorno);
	}

	public ActionForward delete(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception {

		HttpSession session = request.getSession();	
		String retorno = FORWARD_LIST;
		if(checkCredentials(request, new String[]{Role.MASTER_ROLE}, true)){
			ProtocolForm protocolForm = (ProtocolForm) actionForm;
			ProtocolBO protocolBO = new ProtocolBO();
			try {
				protocolBO.delete(protocolForm.getProtocol().getId());
				showMessages(request, "message.delete.ok");
				// Igor - 2007-10-02 - Inclusão de Log da aplicação.
				this.saveLog(request, "protocol.title.delete", "key = " + protocolForm.getProtocol().getId(), CsmLogSeverity.LOW_SEVERITY.getValue());
				Collection<Protocol> protocolList = protocolBO.list();
				session.setAttribute("LIST_PROTOCOL", protocolList);
			} catch (ExceptionSys ex) {
				// Igor - 2007-10-02 - Inclusão de Log da aplicação.
				this.saveLog(request, "protocol.title.delete.error", "key = " + 
							 protocolForm.getProtocol().getId() + " error = " + ex.getMessage(), 
							 CsmLogSeverity.LOW_SEVERITY.getValue());
				if (ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) 
					showMessages(request, "error.protocol.save.Contraint", MESSAGE_WARNING);
				else showMessages(request, "default.delete.error", MESSAGE_ERROR);
				
			}
		}else{
			retorno=FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(retorno);
	}

}
