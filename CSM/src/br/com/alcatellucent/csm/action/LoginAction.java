package br.com.alcatellucent.csm.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.beans.UserLogged;

import br.com.alcatellucent.csm.beans.UserAccessControl;
import br.com.alcatellucent.csm.bo.UserAccessControlBO;
import br.com.alcatellucent.csm.bo.UserBO;
import br.com.alcatellucent.csm.bo.UserProfileBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;

import br.com.alcatellucent.csm.form.LoginForm;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;
import br.com.alcatellucent.csm.passwordmanager.PasswordManager;

public final class LoginAction extends CsmBaseAction implements Runnable{
	
	private String userLogin; 
	private Collection collection = new ArrayList();
	Thread thread = new Thread();

    public ActionForward start(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	String retorno = "initial";
	LoginForm form = (LoginForm) actionForm;
	form.setNewPassword("");
	form.setConfirmNewPassword("");
	form.setUser(new User());
	form.setUserAccessControl(new UserAccessControl());
	form.setAction("auth");

	thread.start();
	
	return actionMapping.findForward(retorno);
    }

    public ActionForward auth(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {

	String retorno = "errorLogin";

	LoginForm form = (LoginForm) actionForm;
	User user = form.getUser();
	UserAccessControl userControl = form.getUserAccessControl();
	Role role=new Role();
	
	try {
	// Loading user By UserName ///////////////////////////////////////////
	UserAccessControlBO userControlBO = new UserAccessControlBO();
	UserBO userBO = new UserBO();
	user = userBO.findByUserName(user.getUserName());
	
	//Igor 2007-10-10: Password manager
	PasswordManager pm = new PasswordManager();
	if (user.getId() != null || user.getContextId() != null) {
		role=userBO.findUserRole(user.getUserProfileId());
	}
	// User or context not found => Invalid user
	if (user.getId() == null || user.getContextId() == null) {
	    showMessages(request, "error.user");
	} else if (!user.getIsActive()){
			showMessages(request, "error.user.inactive");
	} else {
		
	    // setting user into userControl and try to logon
	    // ////////////////////
	    userControl.setUser(user);
	    UserAccessControl userAccess = userControlBO.findByUserId(user.getId());
	    // Check for login rules
	   
	    	
	    	//Igor 2007-10-10: Check the number of login attempts is not reached.
	    	
	    	if (!pm.isLoginAttemptReached(userAccess)) {
	    		//Check Password
	    		//if (userControlBO.authenticate(userControl)) {
	    			// Test if user has to change password
	    			//pm.setLoginOk(userAccess);
	    			retorno = userControlBO.changePassword(user, form) ? "changePassword":"success";
	    			// Karine:  If user is admin root or master the system doesn't need to chech expiration date
	    			if(!role.getName().equals(Role.MASTER_ROLE) && !role.getName().equals(Role.ROOT_ADMIN_ROLE) ){
		    			// Igor: 2007-10-24: isExpirationDate checked, don't verify when
		    			// the exp. date is expired neither when it's first time.
	    				if (pm.isAccountExpired(userAccess)) {
	    					showMessages(request, "error.expire.account");
	    					user.setIsActive(false);
	    					userAccess.setUser(user);
	    					userBO.save(user);
	    					retorno = "errorLogin";
	    				} else {
	    					java.sql.Date newExpirationDate = pm.getNewExpirationDate(userAccess.getExpirationDate());
	    					if (!pm.isExpirationDateChecked()) {
	    						if ((userAccess.getLastChangeDate() != null && !(userAccess.getLastChangeDate().equals("")))
	    								&& newExpirationDate.before(new Date())) {
	    							retorno="changePassword";
	    							showMessages(request, "error.expire");
	    						} 
	    					}
	    				}
	    		} else {
	    			
	    			//Checks if user is admin root or master,if user is admin root or master we don´t block him
	    			if(!role.getName().equals(Role.MASTER_ROLE) && !role.getName().equals(Role.ROOT_ADMIN_ROLE) ){
	    				pm.setFailedAttempts(userAccess);
	    				showMessages(request, "error.password");
	    			}else showMessages(request, "error.password");
	    		}
//	    	} else {
//	    		showMessages(request, "error.password.attempts");
//	    	}
	    }
	    
	    UserProfileBO uProfileBO = new UserProfileBO();
	    request.getSession().setAttribute("userSession", user);
	    request.getSession().setAttribute("userProfile",
		uProfileBO.findById(user.getUserProfileId()));
	    
	    UserBO.updateLastLogin(user.getId());

	    // Igor - 2007-10-01 - Inclusaão de Logg da aplicação.
	    this.saveLog(request, "login.title", "login.details",
		    CsmLogSeverity.LOW_SEVERITY.getValue());
	}

	} catch (ExceptionSys e) { 
		e.printStackTrace();
	    this.saveLog(request, "login.title", e.getMessage() + " : " + e.getCause() ,
			    CsmLogSeverity.LOW_SEVERITY.getValue());
		showMessages(request, "error.login");
	}
	
	userLogin = user.getUserName();
	
	Thread thread = new Thread(this);
	thread.start();
	
	return actionMapping.findForward(retorno);
    }

    public ActionForward savePassword(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	String retorno = "errorChange";
	LoginForm form = (LoginForm) actionForm;
	User user = form.getUser();

	// Loading user By UserName ///////////////////////////////////////////
	UserBO userBO = new UserBO();
	user = userBO.findByUserName(user.getUserName());
	UserAccessControlBO userControlBO = new UserAccessControlBO();
	UserAccessControl userControl = form.getUserAccessControl();
	// setting user into userControl and try to logon ////////////////////
	userControl.setUser(user);

	String msg = userControlBO.userAuthenticationPassword(userControl, form.getNewPassword(),form.getConfirmNewPassword());
	if (userControlBO.authenticate(userControl)) {
	    if (!(null == msg )) {
	    	showMessages(request, msg);
	    	// Igor - 2007-10-01 - Inclusão de Log da aplicação.
	    	this.saveLog(request, "Password change", msg, CsmLogSeverity.HIGH_SEVERITY.getValue());
	    } else {		
	    	userControl=userControlBO.findByUserId(user.getId());
	    	userControl.setUserPassword(userControlBO.getPasswd(userControl.getUser().getUserName(), form.getNewPassword()));
	    	userControl.setFlChangeNextLogin(false);
	    	userControl.setLastChangeDate(new Date());
	    	PasswordManager passwordManager = new PasswordManager();
	    	passwordManager.putHistory(userControl);
	    	userControlBO.save(userControl);
	    	// Igor - 2007-10-01 - Inclusão de Log da aplicação.
	    	this.saveLog(request, "Password change",
	    			"User changed password successfully",
			CsmLogSeverity.LOW_SEVERITY.getValue());
	    	retorno = "success";
	    }
	} else {
	    showMessages(request, "error.currentPassword");
	    // Igor - 2007-10-01 - Inclusaão de Logg da aplicação.
	    this.saveLog(request, "Password change", "Error changing password",
		    CsmLogSeverity.HIGH_SEVERITY.getValue());
	}
	request.getSession().setAttribute("userSession", user);
	request.getSession().setAttribute("userCollection", collection);
	// request.getSession().setAttribute("listContext", contextBO.list());
	return actionMapping.findForward(retorno);
    }

    /**
     * This method represents interaction of user in real time
     * add name and date of users in a collection for show in the system.
     */
	public void run(){
		
		UserLogged logged = new UserLogged();
		
		if(collection.isEmpty()){
			logged.setName(userLogin);
			logged.setLastLogin(new Date());
			
			collection.add(logged);
		}
		
		Iterator iterator = collection.iterator();
		
		while (iterator.hasNext()) {
			UserLogged atualUserLogin = (UserLogged) iterator.next();
			
			if(!atualUserLogin.getName().equalsIgnoreCase(userLogin)){
				logged.setName(userLogin);
				logged.setLastLogin(new Date());
				
				collection.add(logged);
			}		
		}
	}
}


