package br.com.alcatellucent.csm.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.beans.TrafficPolicy;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

public class TrafficPolicyForm  extends CommonForm {

	private static final long serialVersionUID = 1L;
	private TrafficPolicy  trafficPolicy;	
	private Boolean isActiveForm;
	

	/**
	 * @return the isActiveForm
	 */
	public Boolean getIsActiveForm() {
		return isActiveForm;
	}
	/**
	 * @param isActiveForm the isActiveForm to set
	 */
	public void setIsActiveForm(Boolean isActiveForm) {
		this.isActiveForm = isActiveForm;
	}
	public TrafficPolicy getTrafficPolicy() {
		if(null == trafficPolicy)
			this.trafficPolicy = new TrafficPolicy();	
		return trafficPolicy;
	}
	public void setTrafficPolicy(TrafficPolicy trafficPolicy) {
		this.trafficPolicy = trafficPolicy;
	}
	
	public void clearForm(){
		trafficPolicy.clear();
	}	
	
public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		boolean emptyForm = true;
	
//Verifica validade das informações digitadas conforme tipo do dado.
		if (((String) request.getParameter("action")).equals("save")) {
		if (this.getTrafficPolicy().getName()!=null&&this.getTrafficPolicy().getName()!=""){
			emptyForm = false;
			if(BasicValueCheck.isEmptyString(this.getTrafficPolicy().getName())) {
				errors.add("trafficForm.name",new ActionMessage("erro.profileForm.Name"));
			}
			else if(this.getTrafficPolicy().getName().length()<3){
				errors.add("trafficForm.name",new ActionMessage("erro.profileForm.Name"));
				}
		}
		}
		

		if (emptyForm) { 
			return null;
		} else {
			return errors;
		}
	}
}
