package br.com.alcatellucent.csm.form;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.beans.ScheduledGroup;
import br.com.alcatellucent.csm.beans.ScheduledProtocol;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

public class ScheduledGroupForm extends CommonForm {
	private static final long serialVersionUID = 1L;
	
	private ScheduledGroup scheduledGroup;
	private Collection<ScheduledProtocol> protocolList;
	private String newProtocolList;
	private Collection<ScheduledProtocol> avProtocol;
	private String protocolValues;
	private String[] newProtocols;
	private String[] selectedProtocols;
	
	/**
	 * @return the selectedProtocols
	 */
	public String[] getSelectedProtocols() {
		return selectedProtocols;
	}

	/**
	 * @param selectedProtocols the selectedProtocols to set
	 */
	public void setSelectedProtocols(String[] selectedProtocols) {
		this.selectedProtocols = selectedProtocols;
	}

	/**
	 * @return the newProtocols
	 */
	public String[] getNewProtocols() {
		return newProtocols;
	}

	/**
	 * @param newProtocols the newProtocols to set
	 */
	public void setNewProtocols(String[] newProtocols) {
		this.newProtocols = newProtocols;
	}

	/**
	 * @return the protocolValues
	 */
	public String getProtocolValues() {
		return protocolValues;
	}

	/**
	 * @param protocolValues the protocolValues to set
	 */
	public void setProtocolValues(String protocolValues) {
		this.protocolValues = protocolValues;
	}

	public ScheduledGroup getScheduledGroup() {
		if(null == scheduledGroup)
			this.scheduledGroup =  new ScheduledGroup();
		
		return scheduledGroup;
	}

	public void setScheduledGroup(ScheduledGroup scheduledGroup) {
		this.scheduledGroup = scheduledGroup;
	}


	/**
	 * @return the protocolList
	 */
	public Collection<ScheduledProtocol> getProtocolList() {
		return protocolList;
	}

	/**
	 * @param protocolList the protocolList to set
	 */
	public void setProtocolList(Collection<ScheduledProtocol> protocolList) {
		this.protocolList = protocolList;
	}


	public String getNewProtocolList() {
		return newProtocolList;
	}



	public void setNewProtocolList(String newProtocolList) {
		this.newProtocolList = newProtocolList;
	}



	public Collection<ScheduledProtocol> getAvProtocol() {
		return avProtocol;
	}



	public void setAvProtocol(Collection<ScheduledProtocol> avProtocol) {
		this.avProtocol = avProtocol;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		boolean emptyForm = true;
		
		if (!(request.getParameter("action").equalsIgnoreCase("save"))) {
			return null;
		}
	
//Verifica validade das informações digitadas conforme tipo do dado.
//	
		if (this.getScheduledGroup().getName()!=null &&this.getScheduledGroup().getName()!=""){
			emptyForm = false;
			if(BasicValueCheck.isEmptyString(this.getScheduledGroup().getName())) {
				errors.add("portForm.nome",new ActionMessage("erro.protocolForm.Name"));
			}
		}

		if (this.getScheduledGroup().getFlowsValue()!=null){
			emptyForm = false;
			if(!BasicValueCheck.isNumericNoSignal(this.getScheduledGroup().getFlowsValue().toString())) {
				errors.add("portForm.flowsValues",new ActionMessage("erro.protocolForm.Flows"));
			}else if(this.getScheduledGroup().getFlowsValue()<0  || this.getScheduledGroup().getFlowsValue()>255 ) {
				errors.add("portForm.flowsValues",new ActionMessage("erro.protocolForm.Flows"));
			}
		}
		
		if (this.getScheduledGroup().getDownStreamValue()!=null){
			emptyForm = false;
			if(!BasicValueCheck.isNumericNoSignal(this.getScheduledGroup().getDownStreamValue().toString())) {
				errors.add("portForm.DownStream",new ActionMessage("erro.protocolForm.DownStream"));
			}else if(Long.valueOf(this.getScheduledGroup().getDownStreamValue())<0  || (Long.valueOf(this.getScheduledGroup().getDownStreamValue())>(Long.valueOf("4294967295")))) {
				errors.add("portForm.DownStream",new ActionMessage("erro.protocolForm.DownStream"));
			}
		}
		
		if (this.getScheduledGroup().getUpStreamValue()!=null){
			emptyForm = false;
			if(!BasicValueCheck.isNumericNoSignal(this.getScheduledGroup().getUpStreamValue().toString())) {
				errors.add("portForm.UpStream",new ActionMessage("erro.protocolForm.UpStream"));
			}else if(Long.valueOf(this.getScheduledGroup().getUpStreamValue())<0  || (Long.valueOf(getScheduledGroup().getUpStreamValue())>(Long.valueOf("4294967295")))) {
				errors.add("portForm.UpStream",new ActionMessage("erro.protocolForm.UpStream"));
			}
		}
		

//
//	Fim Verifica validade;
//	
	if (emptyForm) { 
		return null;
	} else {
		return errors;
	}
}



}