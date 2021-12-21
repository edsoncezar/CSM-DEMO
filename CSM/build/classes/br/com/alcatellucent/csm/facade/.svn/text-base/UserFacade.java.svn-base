package br.com.alcatellucent.csm.facade;

import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.bo.UserBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;

public class UserFacade {
    
 	private static final Logger logger = Logger.getLogger(UserFacade.class);
 	
 	private UserBO userBO;

	public UserFacade() {
	    	logger.debug("Carregando UserFacade...");        
		userBO = new  UserBO();
	}

	
	public void delete(String id) throws ExceptionSys {
		userBO.delete(id);
	}
	
	public void save(User user) throws ExceptionSys {
		userBO.save(user);
	}
	
	public Collection<User> list() throws ExceptionSys {
		return userBO.list();
	}
	
	public User edit(String id) throws ExceptionSys {
		return userBO.edit(id);
	}
	
}
