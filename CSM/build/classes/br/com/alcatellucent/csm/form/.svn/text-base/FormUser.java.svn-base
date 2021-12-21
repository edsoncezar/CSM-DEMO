package br.com.alcatellucent.csm.form;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.beans.Contact;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.beans.UserAccessControl;
import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

/**
 * @author Fernando Caruso
 * 
 */
public class FormUser extends CommonForm {

    private static final long serialVersionUID = 543106859101801439L;
    

    private User user = null;

    private Contact contact = null;

    private Collection<UserProfile> collProfile = null;
    
    private Collection<Role> lisRole=null;

    private UserAccessControl userAccessControl = null;

    private String expirationDateForm = null;
    
    private String confirm=null;
    
    private String newPassword=null;
    
    private Boolean flActive=false;
    
    private Boolean flChangeNextLogin = false;

    public Boolean getFlChangeNextLogin() {
		return flChangeNextLogin;
	}

	public void setFlChangeNextLogin(Boolean flChangeNextLogin) {
		this.flChangeNextLogin = flChangeNextLogin;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public User getUser() {
	if (null == user) {
	    this.user = new User();
	}
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Contact getContact() {
	if (null == contact) {
	    this.contact = new Contact();
	}
	return contact;
    }

    public void setContact(Contact contact) {
	this.contact = contact;
    }

    /**
     * @return the collProfile
     */
    public Collection<UserProfile> getCollProfile() {
	return collProfile;
    }

    /**
     * @param collProfile
     *                the collProfile to set
     */
    public void setCollProfile(Collection<UserProfile> collProfile) {
	this.collProfile = collProfile;
    }

    /**
     * @return the userAccessControl
     */
    public UserAccessControl getUserAccessControl() {
	if (null == userAccessControl) {
	    this.userAccessControl = new UserAccessControl();
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

    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {

	ActionErrors errors = new ActionErrors();
	boolean emptyForm = true;

	// Verifica validade das informações digitadas conforme tipo do dado.
	//	
	if (((String) request.getParameter("action")).equals("save")) {
	    if (this.getUser().getUserName() != null
		    && this.getUser().getUserName() != "") {
		emptyForm = false;
		if (BasicValueCheck.isEmptyString(this.getUser().getUserName())) {
		    errors.add("userForm.log", new ActionMessage(
			    "erro.userForm.Log"));
		} else if (this.getUser().getUserName().length() < 3) {
		    errors.add("userForm.loglength", new ActionMessage(
			    "erro.userForm.LogLength"));
		}
	    }

	    if (this.getUser().getName() != null
		    && this.getUser().getName() != "") {
		emptyForm = false;
		if (BasicValueCheck.isEmptyString(this.getUser().getName())) {
		    errors.add("userForm.nome", new ActionMessage(
			    "erro.userForm.Name"));
		} else if (this.getUser().getName().length() < 3) {
		    errors.add("userForm.nomelength", new ActionMessage(
			    "erro.userForm.NameLength"));
		}
	    }

	    // if (this.getExpirationDateForm()!=null &&
	    // this.getExpirationDateForm()!=""){
	    // emptyForm = false;
	    //			
	    // if(BasicValueCheck.isEmptyString(this.getExpirationDateForm())){
	    // errors.add("userForm.date",new
	    // ActionMessage("erro.userForm.Date"));
	    // }
	    // }
	    if (this.getUser().getUserProfileId() != null
		    && this.getUser().getUserProfileId() != "") {
		emptyForm = false;

		if (BasicValueCheck.isEmptyString(this.getUser()
			.getUserProfileId())) {
		    errors.add("userForm.profile", new ActionMessage(
			    "erro.userForm.Profile"));
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
	}

	if (emptyForm) {
	    return null;
	} else {
	    return errors;
	}
    }

    /**
     * @return the expirationDate
     */
    public String getExpirationDateForm() {
	return expirationDateForm;
    }

    /**
     * @param expirationDate
     *                the expirationDate to set
     */
    public void setExpirationDateForm(String expirationDateForm) {
	this.expirationDateForm = expirationDateForm;
    }

    public void reset(ActionMapping map, HttpServletRequest req) {
	if (((String) req.getParameter("action")).equals("initial")) {
	    this.userAccessControl = new UserAccessControl();
	    this.user = new User();
	    this.contact = new Contact();
	    this.expirationDateForm = null;
	    this.confirm=null;
	    this.newPassword=null;
	}

    }

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Collection<Role> getLisRole() {
		return lisRole;
	}

	public void setLisRole(Collection<Role> lisRole) {
		this.lisRole = lisRole;
	}

	public Boolean getFlActive() {
		if (null == this.flActive) {
			this.flActive=false;
		}
		
		return flActive;
	}

	public void setFlActive(Boolean flActive) {
		this.flActive = flActive;
	}


}
