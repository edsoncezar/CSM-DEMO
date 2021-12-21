package br.com.alcatellucent.csm.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.beans.ScheduledGroup;
import br.com.alcatellucent.csm.beans.ScheduledProtocol;
import br.com.alcatellucent.csm.beans.ScheduledTrafficValues;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

public class ScheduledProtocolForm extends CommonForm {
	

	private static final long serialVersionUID = 1L;
	private ScheduledGroup	scheduledGroup;
	private ScheduledProtocol scheduledScheduledProtocol = new ScheduledProtocol();
	
	private ScheduledTrafficValues scheduledTrafficValues = new ScheduledTrafficValues();

	public ScheduledProtocol getScheduledProtocol() {
		
		if(null == scheduledScheduledProtocol)
			scheduledScheduledProtocol = new ScheduledProtocol();
		return scheduledScheduledProtocol;
		
	}

	public void setScheduledProtocol(ScheduledProtocol protocol) {
		this.scheduledScheduledProtocol = protocol;
	}
	
	public ScheduledTrafficValues getScheduledTrafficValues() {
		
		if (null == scheduledTrafficValues) {
			this.scheduledTrafficValues = new ScheduledTrafficValues();
		}
				
		return this.scheduledTrafficValues;
	}

	public void setScheduledTrafficValues(ScheduledTrafficValues trafficValues) {
		this.scheduledTrafficValues = trafficValues;
	}
	
	
	@Override
	public void reset(ActionMapping map, HttpServletRequest req) {
		
		this.scheduledScheduledProtocol = new ScheduledProtocol();
		
		if (((String)req.getParameter("action")).equals("initial")) {
			this.scheduledTrafficValues = new ScheduledTrafficValues();
		}
		
	}
	
	/**
	 * @return the scheduledGroup
	 */
	public ScheduledGroup getScheduledGroup() {
		if(null == scheduledGroup)
			scheduledGroup = new ScheduledGroup();
		return scheduledGroup;
	}

	/**
	 * @param scheduledGroup the scheduledGroup to set
	 */
	public void setScheduledGroup(ScheduledGroup scheduledGroup) {
		this.scheduledGroup = scheduledGroup;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
			ActionErrors errors = new ActionErrors();
			boolean emptyForm = true;
		
// Verifica validade das informações digitadas conforme tipo do dado.
//		
			if (this.getScheduledProtocol().getName()!=null &&this.getScheduledProtocol().getName()!=""){
				emptyForm = false;
				if(BasicValueCheck.isEmptyString(this.getScheduledProtocol().getName())) {
					errors.add("portForm.nome",new ActionMessage("erro.protocolForm.Name"));
				}
			}
			
			if (this.getScheduledProtocol().getInternalNumber()!=null){
				emptyForm = false;
				if(!BasicValueCheck.isNumericNoSignal(this.getScheduledProtocol().getInternalNumber().toString())) {
					errors.add("portForm.InternalNumber",new ActionMessage("erro.protocolForm.InternalNumber"));
				}else if(this.getScheduledProtocol().getInternalNumber()<=0){
					errors.add("portForm.InternalNumber",new ActionMessage("erro.protocolForm.InternalNumber"));
				}
			}

			if (this.getScheduledProtocol().getScheduledTrafficValues().getFlowsValues()!=null){
				emptyForm = false;
				if(!BasicValueCheck.isNumericNoSignal(this.getScheduledProtocol().getScheduledTrafficValues().getFlowsValues().toString())) {
					errors.add("portForm.flowsValues",new ActionMessage("erro.protocolForm.Flows"));
				}else if(this.getScheduledProtocol().getScheduledTrafficValues().getFlowsValues()<0  || this.getScheduledProtocol().getScheduledTrafficValues().getFlowsValues()>255 ) {
					errors.add("portForm.flowsValues",new ActionMessage("erro.protocolForm.Flows"));
				}
			}
			
			if (this.getScheduledProtocol().getScheduledTrafficValues().getDownStreamValue()!=null){
				emptyForm = false;
				if(!BasicValueCheck.isNumericNoSignal(this.getScheduledProtocol().getScheduledTrafficValues().getDownStreamValue().toString())) {
					errors.add("portForm.DownStream",new ActionMessage("erro.protocolForm.DownStream"));
				}else if(Long.valueOf(this.getScheduledProtocol().getScheduledTrafficValues().getDownStreamValue())<0  || (Long.valueOf(this.getScheduledProtocol().getScheduledTrafficValues().getDownStreamValue())>(Long.valueOf("4294967295")))) {
					errors.add("portForm.DownStream",new ActionMessage("erro.protocolForm.DownStream"));
				}
			}
			
			if (this.getScheduledProtocol().getScheduledTrafficValues().getUpStreamValue()!=null){
				emptyForm = false;
				if(!BasicValueCheck.isNumericNoSignal(this.getScheduledProtocol().getScheduledTrafficValues().getUpStreamValue().toString())) {
					errors.add("portForm.UpStream",new ActionMessage("erro.protocolForm.UpStream"));
				}else if(Long.valueOf(this.getScheduledProtocol().getScheduledTrafficValues().getUpStreamValue())<0  || (Long.valueOf(this.getScheduledProtocol().getScheduledTrafficValues().getUpStreamValue())>(Long.valueOf("4294967295")))) {
					errors.add("portForm.UpStream",new ActionMessage("erro.protocolForm.UpStream"));
				}
			}
			
	
//
//		Fim Verifica validade;
//		
		if (emptyForm) { 
			return null;
		} else {
			return errors;
		}
	}

}