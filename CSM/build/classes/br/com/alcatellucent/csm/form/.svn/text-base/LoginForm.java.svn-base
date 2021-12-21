package br.com.alcatellucent.csm.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.beans.UserAccessControl;
import br.com.alcatellucent.csm.form.common.CommonForm;

public class LoginForm extends CommonForm {

    private static final long serialVersionUID = 4905144921027832096L;

    private User user = null;

    private UserAccessControl userAccessControl = null;

    private String confirmNewPassword = null;

    private String newPassword = null;

    /**
     * @return the user
     */
    public User getUser() {
	if (null == user) {
	    this.user = new User();
	}
	return user;
    }

    /**
     * @param user
     *                the user to set
     */
    public void setUser(User user) {
	this.user = user;
    }

    /**
     * @return the userAccessControl
     */
    public UserAccessControl getUserAccessControl() {
	if (null == userAccessControl)
	    this.userAccessControl = new UserAccessControl();

	return userAccessControl;
    }

    /**
     * @param userAccessControl
     *                the userAccessControl to set
     */
    public void setUserAccessControl(UserAccessControl userAccessControl) {
	this.userAccessControl = userAccessControl;
    }

    /**
     * @return the confirmNewPassword
     */
    public String getConfirmNewPassword() {
	return confirmNewPassword;
    }

    /**
     * @param confirmNewPassword
     *                the confirmNewPassword to set
     */
    public void setConfirmNewPassword(String confirmNewPassword) {
	this.confirmNewPassword = confirmNewPassword;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
	return newPassword;
    }

    /**
     * @param newPassword
     *                the newPassword to set
     */
    public void setNewPassword(String newPassword) {
	this.newPassword = newPassword;
    }
    
    public void reset(ActionMapping map, HttpServletRequest req) {
    	    this.userAccessControl = new UserAccessControl();
    	    this.user = new User();
    	    this.confirmNewPassword=null;
    	    this.newPassword=null;
    	}

}
