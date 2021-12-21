package br.com.alcatellucent.csm.action;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Port;
import br.com.alcatellucent.csm.beans.TraficValues;
import br.com.alcatellucent.csm.bo.PortBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.PortForm;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;

public final class PortAction extends CsmBaseAction {
// Action nunca pode ter propriedade (manter estado)
//	private String retorno;
	
	public ActionForward initial(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
	{
	   PortForm portForm = (PortForm) actionForm;
	   portForm.setPort(new Port());
	   portForm.setTraficValues(new TraficValues());
	   return actionMapping.findForward("initial");	
	   
	}
	
	public ActionForward save(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
			throws ExceptionSys, Exception{
		
		String retorno = "list";
		HttpSession session =  request.getSession();
		PortForm portForm = (PortForm) actionForm;
		
		PortBO portBO = new PortBO();		
		
		try {
//			validate(actionMapping, actionForm);
			
			TraficValues traficValues = portForm.getTraficValues();
			Set<TraficValues> hashTraficValues = new HashSet<TraficValues>();
			
			hashTraficValues.add(traficValues);
			portForm.getPort().setTraficValues(hashTraficValues);
			portForm.getTraficValues().setPort(portForm.getPort());
			//portForm.getTraficValues().setProtocol(new Protocol());
			
			portBO.save(portForm.getPort());
			showMessages(request, "default.save.ok", MESSAGE_WARNING);
		
			Collection<Port> portList = portBO.list();
						
			session.setAttribute("LIST_PORT", portList);
			
			// Igor - 2007-10-01 - Inclusão de Log da aplicação.
			this.saveLog(request, "port.title", "key = " + portForm.getPort().getId(), CsmLogSeverity.LOW_SEVERITY.getValue());
			

		} catch (ExceptionSys ex) {
			// Igor - 2007-10-01 - Inclusão de Log da aplicação.
			this.saveLog(request, "port.title.error", "key = " + portForm.getPort().getId() + " error = " + ex.getMessage(), CsmLogSeverity.LOW_SEVERITY.getValue());
			showMessages(request, "default.save.error", MESSAGE_ERROR);
//			ex.printStackTrace();
//			retorno = "falid";	
		}
		
		return actionMapping.findForward(retorno);
	}
  
	public ActionForward list(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response){
		String retorno = "list";
		HttpSession session =  request.getSession();
		PortBO portBO = new PortBO();
		
		try {
			Collection<Port> portList = portBO.list();
			session.setAttribute("LIST_PORT", portList);
		} catch (ExceptionSys ex) {
			ex.printStackTrace();
			retorno = "falid";	
		}
		
		return actionMapping.findForward(retorno);
	}
	
	public ActionForward edit(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response){

		String retorno = "initial";
		HttpSession session =  request.getSession();
		PortForm portForm = (PortForm) actionForm;
		
		PortBO portBO = new PortBO();
		
		try {
			Port port = portBO.edit(portForm.getPort().getId());
			portForm.setPort(port);
			Collection<Port> portList = portBO.list();
			session.setAttribute("LIST_PORT", portList);
		} catch (ExceptionSys ex) {
			ex.printStackTrace();
			retorno = "falid";	
		}
		
		return actionMapping.findForward(retorno);
	}
	
	
	public ActionForward delete(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
			throws ExceptionSys, Exception{

		String retorno = "list";
		HttpSession session =  request.getSession();
		PortForm portForm = (PortForm) actionForm;
		
		PortBO portBO = new PortBO();
		
		try {
			Collection<Port> portList = portBO.list();
			session.setAttribute("LIST_PORT", portList);
			portBO.delete(portForm.getPort().getId());
			showMessages(request, "default.delete.ok", MESSAGE_WARNING);
			// Igor - 2007-10-01 - Inclusão de Log da aplicação.
			this.saveLog(request, "port.title.delete", "key = " + portForm.getPort().getId(), CsmLogSeverity.LOW_SEVERITY.getValue());
		} catch (ExceptionSys ex) {
			showMessages(request, "default.delete.error", MESSAGE_ERROR);
			// Igor - 2007-10-01 - Inclusão de Log da aplicação.
			this.saveLog(request, "port.title.delete.error", "key = " + portForm.getPort().getId(), CsmLogSeverity.LOW_SEVERITY.getValue());
		}
		
		return actionMapping.findForward(retorno);
	}
	
}
