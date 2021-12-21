package br.com.alcatellucent.csm.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.beans.PassSettings;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;


/**
 * @author Karine Camhy
 * 
 */
public class PasswordSettingForm extends CommonForm {

	private static final long serialVersionUID = 1902532234973784337L;
	
	private PassSettings passSetting = new PassSettings();

	public PassSettings getPassSetting() {
		return passSetting;
	}

	public void setPassSetting(PassSettings passSetting) {
		this.passSetting = passSetting;
	}
	
	 public ActionErrors validate(ActionMapping mapping,
			    HttpServletRequest request) {

			ActionErrors errors = new ActionErrors();
			boolean emptyForm = true;

			// Verifica validade das informações digitadas conforme tipo do dado.
			//	
			if (((String) request.getParameter("action")).equals("save")) {
				
				  if (this.getPassSetting().getFailedAttempts() != null) {
						emptyForm = false;
						if (!BasicValueCheck.isNumericNoSignal(this.getPassSetting().getFailedAttempts().toString())) {
						    errors.add("passSettingForm.failedAttempt", new ActionMessage(
							    "error.passSettingForm.failedAttempt"));
						} else if (this.getPassSetting().getFailedAttempts() < 0 || this.getPassSetting().getFailedAttempts()>5 ) {
						    errors.add("passSettingForm.failedAttempt", new ActionMessage(
							    "error.passSettingForm.failedAttempt"));
						}
				  }
				  
				  if (this.getPassSetting().getMinimunLength() != null) {
						emptyForm = false;
						if (!BasicValueCheck.isNumericNoSignal(this.getPassSetting().getMinimunLength().toString())) {
						    errors.add("passSettingForm.minimunLength", new ActionMessage(
							    "error.passSettingForm.minimunLength"));
						} else if (this.getPassSetting().getMinimunLength() < 4 || this.getPassSetting().getMinimunLength() > 8)  {
						    errors.add("passSettingForm.minimunLength", new ActionMessage(
							    "error.passSettingForm.minimunLength"));
						}
				  }
				  
				  if (this.getPassSetting().getEnforceHistoryQty() != null) {
						emptyForm = false;
						if (!BasicValueCheck.isNumericNoSignal(this.getPassSetting().getEnforceHistoryQty().toString())) {
						    errors.add("passSettingForm.enforceHistoryQty", new ActionMessage(
							    "error.passSettingForm.enforceHistoryQty"));
						} 
				  }
				  
				  if (this.getPassSetting().getEnforceChangingDays() != null) {
						emptyForm = false;
						if (!BasicValueCheck.isNumericNoSignal(this.getPassSetting().getEnforceChangingDays().toString())) {
						    errors.add("passSettingForm.enforceChangingDays", new ActionMessage(
							    "error.passSettingForm.enforceChangingDays"));
						}
				  }
				  
				  if (this.getPassSetting().getLockDurationMin() != null) {
						emptyForm = false;
						if (!BasicValueCheck.isNumericNoSignal(this.getPassSetting().getLockDurationMin().toString())) {
						    errors.add("passSettingForm.lockDurationMin", new ActionMessage(
							    "error.passSettingForm.lockDurationMin"));
						}
				  }
			}
			    

			if (emptyForm) {
			    return null;
			} else {
			    return errors;
			}
		    }
	 
	@Override
	public void reset(ActionMapping map, HttpServletRequest req) {
		passSetting = new PassSettings();
	}

}