/**
 * 
 */
package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.InMon;
import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;

/**
 * 
 *@author Edson Moreira Cezar
 *@date   09/03/2007
 *@version 1.0
 *
 *@description : This class represents Business Object of InMon.
 */
public class InMonBO {
		
    private static final Logger logger = Logger.getLogger(InMon.class);

    private HbCommonDAO<InMon> inMonDao;

    public InMonBO() {
	logger.debug("Carregando InMonBO..");
	inMonDao = new HbCommonDAO<InMon>(InMon.class);
    }
	   
	   
	   @SuppressWarnings("unchecked")
	   public void save(InMon inMon) throws ExceptionSys {      
		   
	       try {
	    	  if(null == inMon.getId() || inMon.getId().equalsIgnoreCase("")){
	    		  inMonDao.save(inMon);
	              logger.info("Save information inMon: "+inMon.getName());    		  
	    	  }else{    	
	    		  
	    		  inMonDao.update(inMon);
	    		  logger.info("Modify information inMon: "+inMon.getName());    		  
	    	  }	
	       }catch(Exception e){
	       	   logger.info("ERROR "+e);
	           throw new ExceptionSys(e);
	       }
	   }   
	     
	   
	   @SuppressWarnings("unchecked")
	   public void delete(String id) throws ExceptionSys {       
	       try { 
	    	  InMon inMon = (InMon)inMonDao.findById(id);  
	    	  inMonDao.delete(inMon);		
	    	  logger.info("Delete information of inMon "+id);	
	       }catch(Exception e){
	       	  logger.info("ERROR "+e);
	          throw new ExceptionSys(e);
	       }
	   }
	   
	   @SuppressWarnings("unchecked")
	   public Collection<InMon> list() throws ExceptionSys {
		   Collection<InMon> inMon = new ArrayList<InMon>();		
		   inMon  =  inMonDao.findAll();
		   return inMon;
	   }
	   
	   @SuppressWarnings("unchecked")
	   public InMon getInMon() throws ExceptionSys {
		   InMon inMon = null;
		   Collection<InMon> inMonColl = new ArrayList<InMon>();		
		   inMonColl  =  inMonDao.findAll();		   
		   Iterator iter = inMonColl.iterator();
		   if (iter.hasNext()) {
			inMon = (InMon) iter.next();	
		   }
		   return inMon;
	   }
	   
	   @SuppressWarnings("unchecked")
	   public Collection<UserProfile> list(String inMonId) throws ExceptionSys {
		   	   
		   Properties criterios = new Properties();
		   criterios.put("id",  inMonId  );		
		   Collection coll = (Collection<UserProfile>) inMonDao.findByCriteria(InMon.class, criterios);
		      
		   return coll;
	   }
	   
	   public InMon edit(String id) throws ExceptionSys {
		   InMon inMon  =  (InMon)inMonDao.findById( id );   
		   return inMon;
	   }
}
