package br.com.alcatellucent.csm.action;

import java.util.Collection;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Protocol;
import br.com.alcatellucent.csm.beans.ScheduledGroup;
import br.com.alcatellucent.csm.beans.ScheduledProtocol;
import br.com.alcatellucent.csm.bo.ProtocolBO;
import br.com.alcatellucent.csm.bo.ScheduledGroupBO;
import br.com.alcatellucent.csm.bo.ScheduledProtocolBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.ScheduledGroupForm;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;

public final class ScheduledGroupAction extends CsmBaseAction {

// Action nunca pode ter propriedade (manter estado)
//	private String retorno;

	public ActionForward edit(ActionMapping actionMapping,ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws ExceptionSys {
		String retorno = "initial";
		ScheduledGroupBO sGroupBO =  new ScheduledGroupBO();
		ScheduledGroupForm form = (ScheduledGroupForm)actionForm;
		
		ScheduledGroup sGroup = form.getScheduledGroup();
		sGroup =  sGroupBO.edit(sGroup.getId());
		form.setScheduledGroup(sGroup);
		form.setNewProtocols(new String[]{});
		request.getSession().setAttribute("LIST_PROTOCOLS", sGroup.getScheduledProtocols());
		populateAvailableProtocols(request,response, sGroup);
		return actionMapping.findForward(retorno);
	}
	
	
	public ActionForward save(ActionMapping actionMapping,ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws ExceptionSys, Exception {
		String retorno = "initial";
		ScheduledGroupBO sGroupBO =  new ScheduledGroupBO();
		ScheduledGroupForm form = (ScheduledGroupForm)actionForm;
		
		
		ScheduledGroup sGroup = new ScheduledGroup();
		sGroup = sGroupBO.edit(form.getScheduledGroup().getId());
		try {
			
			sGroup.setFlowsValue(form.getScheduledGroup().getFlowsValue());
			
			sGroup.setDownStreamValue(form.getScheduledGroup().getDownStreamValue());
			sGroup.setDownStreamUnit(form.getScheduledGroup().getDownStreamUnit());
			sGroup.setUpStreamValue(form.getScheduledGroup().getUpStreamValue());
			sGroup.setUpStreamUnit(form.getScheduledGroup().getUpStreamUnit());
			sGroup.setControl(form.getScheduledGroup().getControl());
			sGroup.setPriority(form.getScheduledGroup().getPriority());
			sGroupBO.save(sGroup);
			
			//saving the multiple protocols values ---------------------------
			populateMultiple(actionForm, request);
			
			// reload to reflect changes made in protocols -------------------
			sGroup = sGroupBO.edit(sGroup.getId());
			
			form.setScheduledGroup(sGroup);
			
			// Igor - 2007-10-02 - Inclusão de Log da aplicação.
			this.saveLog(request, "scheduled_group.title", "key = " + sGroup.getId(), CsmLogSeverity.LOW_SEVERITY.getValue());
			
			showMessages(request, "default.save.ok", MESSAGE_WARNING);
			
		} catch (RuntimeException e) {
			
			// Igor - 2007-10-02 - Inclusão de Log da aplicação.
			this.saveLog(request, "scheduled_group.title.error", "key = " + sGroup.getId() + " error = " + e.getMessage(), CsmLogSeverity.LOW_SEVERITY.getValue());
			
			showMessages(request, "default.save.error", MESSAGE_ERROR);
		}
		populateAvailableProtocols(request, response, sGroup);
		request.getSession().setAttribute("LIST_PROTOCOLS", sGroup.getScheduledProtocols());
		return actionMapping.findForward(retorno);
	}
	
	/**
	 * This methods saves values from the multiples ScheduledsProtocols edited in form 
	 * @param actionForm
	 * @throws ExceptionSys
	 */
	private void populateMultiple(ActionForm actionForm, HttpServletRequest request) throws ExceptionSys{
		ScheduledGroupForm form =  (ScheduledGroupForm)actionForm;
		String protocolValues = form.getProtocolValues();
		
		String arrayObjects[] = protocolValues.split(";");
		ScheduledProtocolBO protocolBO = new ScheduledProtocolBO();		
		String[] objectValues;
		ScheduledProtocol protocol = null;
		for(String properties: arrayObjects){
			objectValues = properties.split(",");
			
			protocol = protocolBO.edit(objectValues[0]);
			protocol.getScheduledTrafficValues().setFlowsValues(Integer.parseInt(objectValues[1]));
			protocol.getScheduledTrafficValues().setUpStreamValue(objectValues[2]);
			protocol.getScheduledTrafficValues().setUpStreamUnit(objectValues[3]);
			protocol.getScheduledTrafficValues().setDownStreamValue(objectValues[4]);
			protocol.getScheduledTrafficValues().setDownStreamUnit(objectValues[5]);
			protocol.getScheduledTrafficValues().setControl(objectValues[6]);
			protocol.getScheduledTrafficValues().setControlUnit(objectValues[7]);
			
			if (objectValues.length<9) {
				protocol.getScheduledTrafficValues().setPriority(0);
			} else {
				protocol.getScheduledTrafficValues().setPriority(Integer.parseInt(objectValues[8]));
			}
			
			protocolBO.save(protocol);
			// Igor - 2007-10-02 - Inclusão de Log da aplicação.
			// this.saveLog(request, "scheduled_group.protocol.title", "key = " + protocol.getId(), CsmLogSeverity.LOW_SEVERITY.getValue());
		}
		
	}
	
	public void populateAvailableProtocols(HttpServletRequest request, HttpServletResponse response, ScheduledGroup schedeledGroup){
		ProtocolBO protocolBO = new ProtocolBO();
		
		Collection<Protocol> listProtocols = new HashSet<Protocol>();
		try {
			listProtocols = protocolBO.listNotScheduled(false, schedeledGroup);
		} catch (ExceptionSys e) {
			showMessages(request, "error.scheduledGroup.loadAvailableProtocols", MESSAGE_ERROR);
		}
		request.setAttribute("LIST_AVAILABLE_PROTOCOLS", listProtocols);
	}
	
	public ActionForward addProtocol(ActionMapping actionMapping,ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws ExceptionSys, Exception{
		ScheduledGroupForm form = (ScheduledGroupForm)actionForm;
		ScheduledGroupBO sGroupBO = new ScheduledGroupBO();
		
		ScheduledGroup sGroup = sGroupBO.edit(form.getScheduledGroup().getId());
		ScheduledProtocolBO  sProtocolBO = new ScheduledProtocolBO();
		ProtocolBO  protocolBO = new ProtocolBO();
		Protocol actualProtocol = null;
		Collection<Protocol> collProtocol = new HashSet<Protocol>();
		try{
			for(String id: form.getNewProtocols()){
				actualProtocol = protocolBO.edit(id);
				collProtocol.add(actualProtocol);
			}
			sProtocolBO.copyAllProtocols(collProtocol, sGroup.getScheduledProtocols(), sGroup);
			sGroupBO.save(sGroup);
			//cleaning form
			form.setNewProtocols(new String[]{});
			
			// Igor - 2007-10-02 - Inclusão de Log da aplicação.
			this.saveLog(request, "scheduled_group.title.protocol", "key = " + sGroup.getId(), CsmLogSeverity.LOW_SEVERITY.getValue());
			
			showMessages(request, "message.scheduledGroup.addAvailableProtocols.ok", MESSAGE_WARNING);
		}catch (Exception e) {
			
			// Igor - 2007-10-02 - Inclusão de Log da aplicação.
			this.saveLog(request, "scheduled_group.title.protocol.error", "key = " + sGroup.getId() + " error = " + e.getMessage(), CsmLogSeverity.FATAL_SEVERITY.getValue());
			
			showMessages(request, "error.scheduledGroup.addAvailableProtocols", MESSAGE_ERROR);
		}
		return edit(actionMapping, actionForm, request, response);
	}
	
	public ActionForward removeProtocol(ActionMapping actionMapping,ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws ExceptionSys, Exception{
		ScheduledGroupForm form = (ScheduledGroupForm)actionForm;
		ScheduledProtocolBO  sProtocolBO = new ScheduledProtocolBO();
		try{
			for(String id: form.getSelectedProtocols()){
				sProtocolBO.delete(id);
				
				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "scheduled_group.title.remove_protocol", " key = " + id, CsmLogSeverity.LOW_SEVERITY.getValue());
				
			}
			showMessages(request, "message.scheduledGroup.removeAvailableProtocols.ok", MESSAGE_WARNING);

			
		}catch (Exception e) {
			
			// Igor - 2007-10-01 - Inclusão de Log da aplicação.
			this.saveLog(request, "scheduled_group.title.remove_protocol.error", "  " , CsmLogSeverity.FATAL_SEVERITY.getValue());
			
			showMessages(request, "error.scheduledGroup.removeAvailableProtocols", MESSAGE_ERROR);
		}
		return edit(actionMapping, actionForm, request, response);
	}
}
