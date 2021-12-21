package br.com.alcatellucent.csm.facade;

import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.Users;
import br.com.alcatellucent.csm.bo.UsersBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;

public class UsersFacade {
    
    private static final Logger logger = Logger.getLogger(UsersFacade.class);
    
    private UsersBO usersBO;

    public UsersFacade() {
	logger.debug("Carregando UsersFacade...");
	usersBO = new UsersBO();
    }

    public void delete(Long id) throws ExceptionSys {
	usersBO.delete(id);
    }

    public void save(Users users) throws ExceptionSys {
	usersBO.save(users);
    }

    public Collection<Users> list() throws ExceptionSys {
	return usersBO.list();
    }

    public Users edit(Long id) throws ExceptionSys {
	return usersBO.edit(id);
    }

}
