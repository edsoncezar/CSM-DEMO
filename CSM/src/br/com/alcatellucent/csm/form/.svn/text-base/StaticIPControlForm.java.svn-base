package br.com.alcatellucent.csm.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.beans.StaticIP;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

public class StaticIPControlForm extends CommonForm{
	private static final long serialVersionUID = 1L;
	private StaticIP staticIP;
	
	

	/**
	 * @return the staticIP
	 */
	public StaticIP getStaticIP() {
		if(null == staticIP)
			staticIP = new StaticIP();
		return staticIP;
	}


	/**
	 * @param staticIP the staticIP to set
	 */
	public void setStaticIP(StaticIP staticIP) {
		this.staticIP = staticIP;
	}



	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = null;
		errors = new ActionErrors();
		// faz as verificações antes de salvar
		if(getAction().equalsIgnoreCase("save")){
			if(BasicValueCheck.isEmptyString(getStaticIP().getIP()) || !BasicValueCheck.isIP(getStaticIP().getIP())  ){
				errors.add("error.staticIPControlForm.noIP",new ActionMessage("error.staticIPControlForm.noIP"));
			}
			
			if(BasicValueCheck.isEmptyString(getStaticIP().getMask())|| !BasicValueCheck.isIP(getStaticIP().getMask()) ){
				errors.add("error.staticIPControlForm.noMask",new ActionMessage("error.staticIPControlForm.noMask"));
			}
			
			if(BasicValueCheck.isEmptyString(getStaticIP().getTrafficPolicyId())){
				errors.add("error.staticIPControlForm.noTrafficPolicy",new ActionMessage("error.staticIPControlForm.noTrafficPolicy"));
			}
			
		}
		return errors;
	}
	
	
}
