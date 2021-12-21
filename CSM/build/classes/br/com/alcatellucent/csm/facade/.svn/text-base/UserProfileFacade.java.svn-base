package br.com.alcatellucent.csm.facade;

import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.bo.UserProfileBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;

public class UserProfileFacade {

    private static final Logger logger = Logger.getLogger(UserProfileFacade.class);
    
    private UserProfileBO userProfileBO;

    public UserProfileFacade() {
	logger.debug("Carregando Context...");
	userProfileBO = new UserProfileBO();
    }

    public void delete(String id) throws ExceptionSys {
	userProfileBO.delete(id);
    }

    public void save(UserProfile userProfile) throws ExceptionSys {
	userProfileBO.save(userProfile);
    }

    public Collection<UserProfile> list(String contextId) throws ExceptionSys {
	return userProfileBO.list(contextId);
    }

    public UserProfile edit(String id) throws ExceptionSys {
	return userProfileBO.edit(id);
    }
    
    public Boolean nameExist(UserProfile userProfile) throws ExceptionSys {
		return userProfileBO.nameExist(userProfile);
	}
    
}