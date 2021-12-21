package br.com.alcatellucent.csm.action;

import java.util.Collection;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.PortProtocolGroup;
import br.com.alcatellucent.csm.beans.Protocol;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.bo.PortBO;
import br.com.alcatellucent.csm.bo.PortProtocolGroupBO;
import br.com.alcatellucent.csm.bo.ProtocolBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.PortProtocolGroupForm;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;

public final class PortProtocolGroupAction extends CsmBaseAction {

	public ActionForward initial(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		String retorno = FORWARD_INITIAL;
		
		if(checkCredentials(request, new String[]{Role.ROOT_ADMIN_ROLE,Role.MASTER_ROLE}, true)){
			PortProtocolGroup portProtocolGroup=new PortProtocolGroup();
			portProtocolGroup.setFlActive(true);
			PortProtocolGroupForm portProtocolGroupForm = (PortProtocolGroupForm) actionForm;
			portProtocolGroupForm.setPortProtocolGroup(portProtocolGroup);
			loadLists(actionForm);
		}else{
			retorno = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(retorno);

	}

	public ActionForward save(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception {

		String retorno= FORWARD_LIST;
		if(checkCredentials(request, new String[]{Role.MASTER_ROLE, Role.ROOT_ADMIN_ROLE}, true)){
			HttpSession session = request.getSession();
			PortProtocolGroupForm portProtocolGroupForm = (PortProtocolGroupForm) actionForm;
	
			portProtocolGroupForm.getPortProtocolGroup().setFlActive(portProtocolGroupForm.getPortProtocolGroup().getFlActive());
			// Elimina os AvPorts e AvProtocol - Igor
			portProtocolGroupForm.setAvPorts(null);
			portProtocolGroupForm.setAvProtocol(null);
	
			PortProtocolGroupBO portProtocolGroupBO = new PortProtocolGroupBO();
	
			try {
				// retrievLists(actionForm);
				//retrievLists(portProtocolGroupForm);
	
				// Zera Value
				// /////////////////////////////////////////////////////////////
				portProtocolGroupBO.save(portProtocolGroupForm
						.getPortProtocolGroup());
				showMessages(request, "default.save.ok", MESSAGE_WARNING);
				
				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "port_protocol_group.title", "key = " + portProtocolGroupForm.getPortProtocolGroup().getId(), CsmLogSeverity.LOW_SEVERITY.getValue());
				
				Collection<PortProtocolGroup> portProtocolGroupList = portProtocolGroupBO
						.list();
				session.setAttribute("LIST_PORT_PROTOCOL_GROUP",
						portProtocolGroupList);
	
			} catch (ExceptionSys ex) {
				this.saveLog(request, "port_protocol_group.title.error", "key = " + portProtocolGroupForm.getPortProtocolGroup().getId(), CsmLogSeverity.FATAL_SEVERITY.getValue());
				if (ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
					showMessages(request, "erro.portProtocolForm.DuplicateName", CsmBaseAction.MESSAGE_ERROR);
					
					// Igor: 2007-10-05: retornar a página de entrada em caso de nome duplicado
					retorno = FORWARD_ERROR;
				}else{
				showMessages(request, "default.save.error", MESSAGE_ERROR);
				}
			//	retorno = "falid";
			}
			loadLists(actionForm);
			Collection<PortProtocolGroup> portProtocolGroupList = portProtocolGroupBO.list();
			session.setAttribute("LIST_PORT_PROTOCOL_GROUP",portProtocolGroupList);
		}else{
			retorno = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(retorno);
	}

	public ActionForward list(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {
		String retorno = FORWARD_LIST;
		HttpSession session = request.getSession();
		PortProtocolGroupBO portProtocolGroupBO = new PortProtocolGroupBO();

		try {
			Collection<PortProtocolGroup> portProtocolGroupList = portProtocolGroupBO.list();
			session.setAttribute("LIST_PORT_PROTOCOL_GROUP",portProtocolGroupList);
		} catch (ExceptionSys ex) {
			ex.printStackTrace();
			retorno = FORWARD_FAILED;
		}
		loadLists(actionForm);
		return actionMapping.findForward(retorno);
	}

	public ActionForward edit(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		HttpSession session = request.getSession();
		String retorno = FORWARD_INITIAL;
//		if(checkCredentials(request, new String[]{Role.GUEST_ROLE}, false)){
				
			PortProtocolGroupForm portProtocolGroupForm = (PortProtocolGroupForm) actionForm;

			PortProtocolGroupBO portProtocolGroupBO = new PortProtocolGroupBO();

			try {
				PortProtocolGroup portProtocolGroup = portProtocolGroupBO
					.edit(portProtocolGroupForm.getPortProtocolGroup().getId());
				portProtocolGroupForm.setPortProtocolGroup(portProtocolGroup);
				Collection<PortProtocolGroup> portProtocolGroupList = portProtocolGroupBO
					.list();
				session.setAttribute("LIST_PORT_PROTOCOL_GROUP",portProtocolGroupList);
			} catch (ExceptionSys ex) {
				ex.printStackTrace();
				retorno = "falid";
			}
			loadLists(actionForm);
//		}else{
//			retorno = FORWARD_FORBIDDEN;
//		}
		return actionMapping.findForward(retorno);
	}

	public ActionForward delete(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception {

		HttpSession session = request.getSession();
		String retorno = FORWARD_LIST;

		if(checkCredentials(request, new String[]{Role.ROOT_ADMIN_ROLE,Role.MASTER_ROLE}, true)){
			PortProtocolGroupBO portProtocolGroupBO = new PortProtocolGroupBO();
			try {
				portProtocolGroupBO.delete(request.getParameter("id"));
				showMessages(request, "default.delete.ok", MESSAGE_WARNING);
				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "port_protocol_group.title.delete", "key = " + request.getParameter("id"), CsmLogSeverity.LOW_SEVERITY.getValue());
				Collection<PortProtocolGroup> portProtocolGroupList = portProtocolGroupBO.list();
				session.setAttribute("LIST_PORT_PROTOCOL_GROUP",portProtocolGroupList);
			} catch (ExceptionSys ex) {
				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "port_protocol_group.title.delete.error", "key = " + request.getParameter("id"), CsmLogSeverity.HIGH_SEVERITY.getValue());
				showMessages(request, "default.delete.error", MESSAGE_ERROR);
			}
			loadLists(actionForm);
		}else{
			retorno = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(retorno);
	}

	private void loadLists(ActionForm actionForm) {

		PortBO portBO = new PortBO();
		ProtocolBO protocolBO = new ProtocolBO();

		PortProtocolGroupForm form = (PortProtocolGroupForm) actionForm;

		try {
			form.setPortList(portBO.list()); 			// Lista de portas disponiveis
			form.setProtocolList(protocolBO.list(false)); 	// lista de protocolos disponiveis

			// Igor - Portas disponiveis

			form.setAvPorts(form.getPortList());
			form.setAvProtocol(form.getProtocolList());

		} catch (ExceptionSys e) {
			e.printStackTrace();
		}

		// Verifica se as portas disponiveis ja foram selecionadas. Caso tenham
		// sido selecionadas, retirar da lista de disponiveis.

		Collection<Protocol> dispProtocols = new HashSet<Protocol>();
		dispProtocols = form.getAvProtocol()!= null ? form.getAvProtocol():dispProtocols;

		Collection<Protocol> selProtocols = new HashSet<Protocol>();
		selProtocols = form.getPortProtocolGroup().getProtocols() != null ? form.getPortProtocolGroup().getProtocols() : selProtocols;
		
		Collection<Protocol> newDispProtocol = new HashSet<Protocol>();
		
		Protocol p = null;
		boolean hasElement = false;
		
		if (selProtocols.size() != 0) {
			for (Protocol dispProtocol : dispProtocols) {
				hasElement = false;
				p = dispProtocol;
				for (Protocol selProtocol : selProtocols) {
					if (dispProtocol != null) {
						if (selProtocol.getName().equals(dispProtocol.getName())) {
							hasElement = true;
						}
					}
				}
				if (!hasElement) {
					newDispProtocol.add(p);
				};
			}
				form.setAvProtocol(newDispProtocol);
		} 
			
	}

//	/**
//	 * Carrega as Listas com os novos protocolos e portas selecionadas no form
//	 * 
//	 * @param actionForm
//	 */
//	private void retrievLists(ActionForm actionForm) {
//
//		PortBO portBO = new PortBO();
//		ProtocolBO protocolBO = new ProtocolBO();
//
//		PortProtocolGroupForm form = (PortProtocolGroupForm) actionForm;
//		// PortProtocolGroup group = form.getPortProtocolGroup();
///*
//		Set<Port> newSetPort = new HashSet<Port>();
//		String newPortList = form.getNewPortList();
//		String[] newArrayPort = newPortList.split(",");
//*/
//		Set<Protocol> newSetProtocol = new HashSet<Protocol>();
//		String newProtocolList = form.getNewProtocolList();
//		String[] newArrayProtocol = newProtocolList.split(",");
//
//		try {
///*
//			for (String id : newArrayPort) {
//				newSetPort.add(portBO.edit(id));
//			}*/
//
//			for (String id : newArrayProtocol) {
//				newSetProtocol.add(protocolBO.edit(id));
//			}
//
//		/*	form.getPortProtocolGroup().setPorts(newSetPort);*/
//			form.getPortProtocolGroup().setProtocols(newSetProtocol);
//
//		} catch (ExceptionSys e) {
//			e.printStackTrace();
//		}
//
//	}

}
