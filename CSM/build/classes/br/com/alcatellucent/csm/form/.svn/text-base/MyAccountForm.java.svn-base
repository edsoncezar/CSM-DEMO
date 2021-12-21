package br.com.alcatellucent.csm.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.beans.Contact;
import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.beans.UserAccessControl;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

/**
 * 
 * @author Edson Moreira Cezar
 * @date 09/04/2007
 * @version 1.0
 * 
 * @description : This class represents Business Object of MyAccount.
 */
public class MyAccountForm extends CommonForm {

    private static final long serialVersionUID = -1628720566397681518L;

    private User user = null;

    private UserAccessControl userAccessControl = null;

    private Contact contact = null;

    private String confirmNewPassword = null;

    private String newPassword = null;

    /**
     * @return the user
     */
    public User getUser() {
	if (null == user) {
	    user = new User();
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
	if (null == userAccessControl) {
	    userAccessControl = new UserAccessControl();
	}

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
     * @return the contact
     */
    public Contact getContact() {
	if (null == contact) {
	    contact = new Contact();
	}
	return contact;
    }

    /**
     * @param contact
     *                the contact to set
     */
    public void setContact(Contact contact) {
	this.contact = contact;
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

    public void clearPassword() {
	this.userAccessControl.clear();
    }

    public ActionErrors validate(ActionMapping mapping,
    	    HttpServletRequest request) {

	ActionErrors errors = new ActionErrors();
	boolean emptyForm = true;

	if (getUser().getName() != null
		&& BasicValueCheck.isEmptyString(getUser().getName())) {
	    emptyForm = false;
	    errors.add("error.user", new ActionMessage("error.user"));
	}
	
	if (this.getUserAccessControl().getUserPassword() != null
			&& this.getUserAccessControl().getUserPassword()!="") {
		    emptyForm = false;
		    if (!BasicValueCheck.isEmptyString(this.getUserAccessControl().getUserPassword()) && (BasicValueCheck.isEmptyString(this.getConfirmNewPassword())||(BasicValueCheck.isEmptyString(this.getNewPassword())))) {
			    errors.add("error.password", new ActionMessage(
				    "error.password"));
			} 
		}

    if (this.getContact().getMobile() != null
		    && !this.getContact().getMobile().equals("")) {
		emptyForm = false;

		if (BasicValueCheck.isMobile(this.getContact().getMobile()) == false) {
		    errors.add("operatorForm.Phone", new ActionMessage(
			    "erro.operatorForm.PhoneLength"));
		}
		
		if (BasicValueCheck.isMobile(this.getContact().getPhone()) == false) {
		    errors.add("erro.userForm.Phone", new ActionMessage(
			    "erro.operatorForm.MobileLength"));
		}
		
	    }

	    
	    if (this.getContact().getEmail() != null
		    && !this.getContact().getEmail().equals("")) {
		emptyForm = false;

		if (BasicValueCheck.isEmail(this.getContact().getEmail()) == false) {
		    errors.add("operatorForm.email", new ActionMessage(
			    "erro.operatorForm.InvalidEmail"));
		}
	    
	}
	    
	if (emptyForm) {
	    return null;
	} else {
	    return errors;
	}
    }

    public void reset(ActionMapping map, HttpServletRequest req) {
	this.user = new User();
    }

}
