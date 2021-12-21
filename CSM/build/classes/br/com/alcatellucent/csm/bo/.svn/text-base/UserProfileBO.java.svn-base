package br.com.alcatellucent.csm.bo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;

public class UserProfileBO {
	
   private static final Logger logger = Logger.getLogger(UserProfileBO.class);
   
   private HbCommonDAO<UserProfile> userProfileDao;   
		
   public UserProfileBO() {
	logger.debug("Carregando UserProfileBO..");
	userProfileDao = new HbCommonDAO<UserProfile>(UserProfile.class);
		
   }
   
   @SuppressWarnings("unchecked")
   public void save(UserProfile userProfile) throws ExceptionSys {   
	
       try {
    	  if(  !userProfile.getId().equals("") ){
    		  userProfileDao.update(userProfile);
              logger.info("Modify information of userProfile "+userProfile.getId());    		  
    	  }else{   
    		  userProfileDao.save(userProfile);
    		  logger.info("Save information of userProfile "+userProfile.getId());    		  
    	  }	
       }catch(Exception e){
    	   e.printStackTrace();
       	   logger.info("ERROR "+e);
           throw new ExceptionSys(e);
       }
   }   
     
   
   @SuppressWarnings("unchecked")
   public void delete(String id) throws ExceptionSys {       
       try { 
    	  UserProfile userProfile = (UserProfile)userProfileDao.findById(id);    	  
    	  userProfileDao.delete(userProfile);			
    	  logger.info("Delete information of UserProfile "+id);	
       }catch(Exception e){
       	  logger.info("ERROR "+e);
          throw new ExceptionSys(e);
       }
   }
   
   @SuppressWarnings("unchecked")
   public Collection<UserProfile> list(String contextId) throws ExceptionSys {
	   	   
	   Properties criterios = new Properties();
	   criterios.put("contextId",  contextId  );		
	   Collection coll = (Collection<UserProfile>) userProfileDao.findByCriteria(UserProfile.class, criterios);
	      
	   return coll;
   }
   
   public Collection<UserProfile> list(String contextId, Boolean hideSuperUser) throws ExceptionSys {
   	   
	   Collection<UserProfile> coll  = list(contextId);
	   Collection<UserProfile> retorno =  new HashSet<UserProfile>();
	   RoleBO roleBO =  new RoleBO();
	   Role actualRole =  null;
	   // Caso esteja habilitado é carregada a lista sem os profiles anexadosm aos super users MASTER ADMIN 
	   if(hideSuperUser){
		   for(UserProfile profile: coll){
			   actualRole = roleBO.findById(profile.getRole());
			   if(!actualRole.getName().equals(Role.MASTER_ROLE) && !actualRole.getName().equals(Role.ROOT_ADMIN_ROLE)){
				   retorno.add(profile);
			   }
		   }
	   }else{
		   retorno = list(contextId);
	   }
	      
	   return retorno;
   }
   
   public UserProfile edit(String id) throws ExceptionSys {
	   UserProfile userProfile  = (UserProfile) userProfileDao.findById( id );	   	   
	   return userProfile;
   }
   
   
   public UserProfile findById(String userProfileId) throws ExceptionSys{
	   
	  UserProfile userProfile=(UserProfile)userProfileDao.findById(userProfileId);
	  RoleBO roleBO = new RoleBO();
	  userProfile.setUserRole(roleBO.findById(userProfile.getRole()));
	  return userProfile;	   
   }
   
   public Boolean nameExist(UserProfile userProfile) throws ExceptionSys{
	   Boolean flagName=true;
	   Collection<UserProfile> nameByContext;
	try {
		nameByContext = this.list(userProfile.getContextId());
    	   for(UserProfile c:nameByContext){
    		   if(c.getName().equalsIgnoreCase(userProfile.getName())){
    			   if(!c.getId().equals(userProfile.getId()))
    			   flagName=false;
    		   }
    	   }
	}
    	   catch (ExceptionSys e) {
    			 logger.info("ERROR "+e);
    	         throw new ExceptionSys(e);
    		}
    	   return flagName;
   }
   
   public Role findRole(String userProfileId) throws ExceptionSys{
	   Role role = null;
	   RoleBO roleBO = new RoleBO();
	   role = roleBO.findById(userProfileId);
	   return role;
   }
}
