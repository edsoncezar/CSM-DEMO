package br.com.alcatellucent.csm.form;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.beans.PassRegex;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.passwordmanager.PasswordManager;
import br.com.alcatellucent.csm.utils.BasicValueCheck;


/**
 * @author Karine Camhy
 * 
 */
public class PasswordRegexForm extends CommonForm {

 
	private static final long serialVersionUID = 7384515516262702150L;
	
	private PassRegex passRegex=new PassRegex();

	public PassRegex getPassRegex() {
		return passRegex;
	}

	public void setPassRegex(PassRegex passRegex) {
		this.passRegex = passRegex;
	}
	
	@Override
	public void reset(ActionMapping map, HttpServletRequest req) {
		
		passRegex=new PassRegex();
	}
	
	 public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

			ActionErrors errors = new ActionErrors();
			boolean emptyForm = true;

			// Verifica validade das informações digitadas conforme tipo do dado.
			//	
			if (((String) request.getParameter("action")).equals("save")) {
				
			
				 if (this.getPassRegex().getLiteral() != null && this.getPassRegex().getLiteral() != "") {
						emptyForm = false;
						if(BasicValueCheck.isEmptyString(this.getPassRegex().getLiteral())) {
							errors.add("passSettingForm.literal",new ActionMessage("error.passSettingForm.literal"));
						}
						try{
						PasswordManager pm=new PasswordManager();
						if (!pm.isValidRegex(this.getPassRegex().getLiteral())) {
						    errors.add("passSettingForm.regex", new ActionMessage(
							    "error.passSettingForm.regex"));
					  	}
						}catch(ExceptionSys ex) {
							ex.printStackTrace();
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
