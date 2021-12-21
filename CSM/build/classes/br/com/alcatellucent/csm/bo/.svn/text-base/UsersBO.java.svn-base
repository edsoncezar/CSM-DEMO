package br.com.alcatellucent.csm.bo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import br.com.alcatellucent.csm.beans.Users;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbUsersDAO;
import br.com.alcatellucent.csm.utils.VarsSys;

public class UsersBO {

    private static final Logger logger = Logger.getLogger(UsersBO.class);

    private HbUsersDAO<Users> usersDao;

    public UsersBO() {
	logger.debug("Carregando UsersBO..");
	String url = VarsSys.configDir + File.separator
		+ "applicationContext-hibernate2.xml";
	XmlBeanFactory factory = new XmlBeanFactory(new FileSystemResource(url));
	usersDao = (HbUsersDAO) factory.getBean("usersDao");
    }   
   
   @SuppressWarnings("unchecked")
   public void save(Users users) throws ExceptionSys {       
       try {
    	  if( users.getId() != 0  ){
    		  usersDao.update(users);
              logger.info("Modify information of user "+users.getName());    		  
    	  }else{    		  
    		  usersDao.save(users);
    		  logger.info("Save information of user "+users.getName());    		  
    	  }	
       }catch(Exception e){
       	   logger.info("ERROR "+e);
           throw new ExceptionSys(e);
       }
   }   
     
   
   @SuppressWarnings("unchecked")
   public void delete(Long id) throws ExceptionSys {       
       try {    	   
    	  Users users = usersDao.findById(id);
    	  usersDao.delete(users);		
    	  logger.info("Delete information of user "+id);	
       }catch(Exception e){
       	  logger.info("ERROR "+e);
          throw new ExceptionSys(e);
       }
   }
   
   @SuppressWarnings("unchecked")
   public Collection<Users> list() throws ExceptionSys {
	   Collection<Users> listUsers = new ArrayList<Users>();		
	   listUsers  =  usersDao.findAll();
	   return listUsers;
   }
   
   public Users edit(Long id) throws ExceptionSys {
	   Users users  =  usersDao.findById( id );	   	   
	   return users;
   }
   
   

}
