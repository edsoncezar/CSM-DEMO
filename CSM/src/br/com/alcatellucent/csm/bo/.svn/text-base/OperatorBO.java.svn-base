package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.Operator;
import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;

/**
 * 
 *@author Edson Moreira Cezar
 *@date   08/18/2007 
 *@version 1.0 - Business Object of Operator
 *
 */
public class OperatorBO {
    
    private static final Logger logger = Logger.getLogger(OperatorBO.class);
    
    private HbCommonDAO<Operator> operatorDao;

    public OperatorBO() {
	logger.debug("Carregando OperatorBO..");
	operatorDao = new HbCommonDAO<Operator>(Operator.class);
    }

    @SuppressWarnings("unchecked")
    public void save(Operator operator) throws ExceptionSys {      
		   
	       try {
	    	  if(null == operator.getId() || operator.getId().equalsIgnoreCase("")){
	    		  operatorDao.save(operator);
	              logger.info("Save information Operator: "+operator.getName()+" id: "+operator.getId());    		  
	    	  }else{    	
	    		  
	    		  operatorDao.update(operator);
	    		  logger.info("Modify information Operator: "+operator.getName()+" id: "+operator.getId());    		  
	    	  }	
	       }catch(Exception e){
	       	   logger.info("ERROR "+e);
	           throw new ExceptionSys(e);
	       }
	   }   
	     
	   
	   @SuppressWarnings("unchecked")
	   public void delete(String id) throws ExceptionSys {       
	       try { 
	    	  Operator operator = (Operator)operatorDao.findById(id);  
	    	  operatorDao.delete(operator);		
	    	  logger.info("Delete information of operator "+id);	
	       }catch(Exception e){
	       	  logger.info("ERROR "+e);
	          throw new ExceptionSys(e);
	       }
	   }
	   
	   @SuppressWarnings("unchecked")
	   public Collection<Operator> list() throws ExceptionSys {
		   Collection<Operator> operator = new ArrayList<Operator>();		
		   operator  =  operatorDao.findAll();
		   return operator;
	   }
	   
	   @SuppressWarnings("unchecked")
	   public Collection<UserProfile> list(String operatorId) throws ExceptionSys {
		   	   
		   Properties criterios = new Properties();
		   criterios.put("id",  operatorId  );		
		   Collection coll = (Collection<UserProfile>) operatorDao.findByCriteria(Operator.class, criterios);
		      
		   return coll;
	   }
	   
	   public Operator edit(String id) throws ExceptionSys {
		   Operator operator  =  (Operator)operatorDao.findById( id );   
		   return operator;
	   }
	 
}
