package br.com.alcatellucent.csm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.PassSettings;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.bo.PassSettingsBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.PasswordSettingForm;

public class PasswordSettingAction extends CsmBaseAction {

	public PasswordSettingAction() {
		super();
	}

	// FIXME This method is useless
	public ActionForward initial(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {

		return actionMapping.findForward("initial");
	}

	// FIXME This method is useless
	public ActionForward list(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {

		return actionMapping.findForward("list");
	}

	public ActionForward edit(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		PassSettings passSetting = new PassSettings();
		PassSettingsBO passSettingsBO = new PassSettingsBO();
		PasswordSettingForm form = (PasswordSettingForm) actionForm;
		String retorno = "view";
		if(checkCredentials(request, new String[]{Role.MASTER_ROLE,Role.ROOT_ADMIN_ROLE}, true)){
			passSetting = passSettingsBO.findFirst();
			if (passSetting != null) {
				form.setPassSetting(passSetting);
			}
		}else{
			retorno=FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(retorno);
	}

	public ActionForward save(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception {
		PassSettingsBO passSettingsBO = new PassSettingsBO();
		PasswordSettingForm form = (PasswordSettingForm) actionForm;
		PassSettings passSetting = form.getPassSetting();
		String retorno = "success";
		if(checkCredentials(request, new String[]{Role.MASTER_ROLE,Role.ROOT_ADMIN_ROLE}, true)){
			try {
				passSettingsBO.save(passSetting);
				showMessages(request, "default.save.ok", MESSAGE_WARNING);
			} catch (ExceptionSys e) {
				showMessages(request, "default.save.error", MESSAGE_ERROR);
				retorno = "view";
			}
		}else{
			retorno=FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(retorno);
	}

	// FIXME This method is useless
	public ActionForward delete(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception {

		return actionMapping.findForward("success");
	}
}
