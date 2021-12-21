package br.com.alcatellucent.csm.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.beans.Domain;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

public class DomainControlForm extends CommonForm {
	private static final long serialVersionUID = 5290768766056651226L;
	
	private Domain domain;

	/**
	 * @return the domain
	 */
	public Domain getDomain() {
		if(null == domain)
			domain =  new Domain();
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = null;
		errors = new ActionErrors();
		// faz as verificações antes de salvar
		if(getAction().equalsIgnoreCase("save")){
			if(!getDomain().getAnyDestination() && BasicValueCheck.isEmptyString(getDomain().getDestinationDomain()) ){
				errors.add("error.domainControlForm.noDestination",new ActionMessage("error.domainControlForm.noDestination"));
			}
			
			if(!getDomain().getAnySource() && BasicValueCheck.isEmptyString(getDomain().getSourceDomain()) ){
				errors.add("error.domainControlForm.noSource",new ActionMessage("error.domainControlForm.noSource"));
			}
			
			if(BasicValueCheck.isEmptyString(getDomain().getTrafficPolicyId())){
				errors.add("error.domainControlForm.noTrafficPolicy",new ActionMessage("error.domainControlForm.noTrafficPolicy"));
			}
			
		}
		return errors;
	}
	
	
}
