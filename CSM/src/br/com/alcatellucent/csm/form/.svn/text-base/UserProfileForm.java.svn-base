package br.com.alcatellucent.csm.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

/**
 * @author Karine Camhy
 * 
 */
public class UserProfileForm  extends CommonForm {
	
	private static final long serialVersionUID = -8805509389124508611L;
	private UserProfile userProfile;
	private String[] deviceDenied = {new String()};


	/**
	 * @return the userProfile
	 */
	public UserProfile getUserProfile() {
		return userProfile;
	}

	/**
	 * @param userProfile the userProfile to set
	 */
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
	
	/**
	 * @return the deviceDenied
	 */
	public String[] getDeviceDenied() {
		return deviceDenied;
	}

	/**
	 * @param deviceDenied the deviceDenied to set
	 */
	public void setDeviceDenied(String[] deviceDenied) {
		this.deviceDenied = deviceDenied;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		boolean emptyForm = true;
	
//Verifica validade das informações digitadas conforme tipo do dado.
	
		if (this.getUserProfile().getName()!=null&&this.getUserProfile().getName()!=""){
			emptyForm = false;
			if(BasicValueCheck.isEmptyString(this.getUserProfile().getName())) {
				errors.add("profileForm.name",new ActionMessage("erro.profileForm.Name"));
			}
			else if(this.getUserProfile().getName().length()<3){
				errors.add("profileForm.name",new ActionMessage("erro.profileForm.NameLength"));
				}
		}
		
		if (this.getUserProfile().getRole()!=null&&this.getUserProfile().getRole()!=""){
			emptyForm = false;
			if(BasicValueCheck.isEmptyString(this.getUserProfile().getRole())) {
				errors.add("role",new ActionMessage("erro.profileForm.Role"));
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
		
			this.userProfile = new UserProfile();
			deviceDenied = null;
			String deviceDenied[] = {new String()};
		
	}

}
