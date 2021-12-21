package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sun.org.apache.regexp.internal.RESyntaxException;

import br.com.alcatellucent.csm.Md5;
import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.beans.UserAccessControl;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.LoginForm;
import br.com.alcatellucent.csm.passwordmanager.PasswordManager;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;

public class UserAccessControlBO {

    private static final String DEFAULT_PASSWORD = "alu123";

    private static final Logger logger = Logger
	    .getLogger(UserAccessControlBO.class);

    private HbCommonDAO<UserAccessControl> userAcessControl;

    public UserAccessControlBO() {
	logger.debug("Carregando UserAccessControlBO..");
	userAcessControl = new HbCommonDAO<UserAccessControl>(
		UserAccessControl.class);
    }

    @SuppressWarnings("unchecked")
    public void save(UserAccessControl userAccessControl) throws ExceptionSys {

	try {
		PasswordManager passwordManager=new PasswordManager();
		if(!userAccessControl.getUser().getUserName().equalsIgnoreCase("admin")){
			userAccessControl.setLastChangeDate(passwordManager.getLastChangePassword());
		}else{
			userAccessControl.setLastChangeDate(null);
		}
	    if (userAccessControl.getId() == null
		    || userAccessControl.getId().equals("")) {
		userAcessControl.save(userAccessControl);
		logger.info("Modify information of UserAccessControl .. "
			+ userAccessControl.getUser().getName());
	    } else {
		userAcessControl.update(userAccessControl);
		logger.info("Save information of UserAccessControl .. "
			+ userAccessControl.getUser().getName());
	    }
	} catch (Exception e) {
	    logger.info("ERROR " + e);
	    throw new ExceptionSys(e);
	}
    }

    @SuppressWarnings("unchecked")
    public void delete(String id) throws ExceptionSys {
	try {
	    UserAccessControl userAccessControl = (UserAccessControl) userAcessControl
		    .findById(id);
	    userAcessControl.delete(userAccessControl);
	    logger.info("Delete information of UserAccessControl .. " + id);
	} catch (Exception e) {
	    logger.info("ERROR " + e);
	    throw new ExceptionSys(e);
	}
    }

    @SuppressWarnings("unchecked")
    public Collection<UserAccessControl> list() throws ExceptionSys {
	Collection<UserAccessControl> listUserAccessControl = new ArrayList<UserAccessControl>();
	listUserAccessControl = userAcessControl.findAll();
	return listUserAccessControl;
    }

    public UserAccessControl edit(String id) throws ExceptionSys {
	UserAccessControl userAccessControl = (UserAccessControl) userAcessControl
		.findById(id);
	return userAccessControl;
    }

    @SuppressWarnings("unchecked")
    public UserAccessControl findByUserId(String userId) throws ExceptionSys {

	try {
	    Properties criterios = new Properties();
	    criterios.put("user.id", userId);

	    Collection<UserAccessControl> cAccessControl = (Collection<UserAccessControl>) userAcessControl
		    .findByCriteria(UserAccessControl.class, criterios);

	    UserAccessControl accessControlTemp = new UserAccessControl();
	    Iterator iterator = cAccessControl.iterator();
	    List<UserAccessControl> list = new ArrayList<UserAccessControl>();
	    while (iterator.hasNext()) {
		UserAccessControl accessControl = (UserAccessControl) iterator
			.next();
		accessControlTemp = accessControl;
	    }

	    return accessControlTemp;
	} catch (ExceptionSys e) {
	    logger.error("Error finding by User ID ..." + e);
	    throw new ExceptionSys(e);
	}
    }

    public boolean authenticate(UserAccessControl userControl)
	    throws ExceptionSys {
	Boolean result = false;
	UserAccessControl originalUserControl = findByUserId(userControl
		.getUser().getId());
	
	
		if (originalUserControl.getUserPassword().equals(
			getPasswd(userControl.getUser().getUserName(), userControl
					.getUserPassword()))) {
			userControl = originalUserControl;
			result = true;
	}
	
	return result;
    }

    public String  userAuthenticationPassword(UserAccessControl userControl,
	    String newPassword,String confirm) throws ExceptionSys, Exception {
	String result = null;
	UserAccessControl compareControl = findByUserId(userControl.getUser()
		.getId());
	if (compareControl.getUserPassword().equals(
		getPasswd(userControl.getUser().getUserName(), newPassword))) {
	    userControl = compareControl;
	    //result = true;
	}
	
	
	// Igor 2007-10-16> FS#26
	
	try {

		PasswordManager passwordManager = new PasswordManager();
		result=passwordManager.checkPasswordConfirmed(newPassword, confirm);
		if(result==null){
			result = passwordManager.checkPasswordCorrect(compareControl, newPassword);
		}
	} catch (ExceptionSys es) {
		throw es;
	} catch (Exception e) {
		throw e;
	}
	
	
	return result;
    }

    public static String getDefaultPasswd(String username) throws ExceptionSys {
	return getPasswd(username, DEFAULT_PASSWORD);
    }

    public static String getPasswd(String username, String passwd)
	    throws ExceptionSys {
	return Md5.md5(username + passwd);
    }

    public boolean changePassword(User user, LoginForm form) throws Exception {
	boolean mustChange = false;
	UserAccessControlBO userAccessControlBO = new UserAccessControlBO();
	UserAccessControl userAccessControl = userAccessControlBO
		.findByUserId(user.getId());
	userAccessControl.setUser(user);
	if (userAccessControl.getFlChangeNextLogin() == true) {
	    form.setUser(user);
	    form.setUserAccessControl(userAccessControl);
	    userAccessControl.setUserPassword("");
	    mustChange = true;
	} else {
	    mustChange = false;
	}
	return mustChange;
    }
}
