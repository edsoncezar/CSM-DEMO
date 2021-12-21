package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.Port;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;

public class PortBO {

    private static final Logger logger = Logger.getLogger(PortBO.class);

    private HbCommonDAO<Port> portDAO;

    public PortBO() {
	logger.debug("Loading PortDAO..");
	portDAO = new HbCommonDAO<Port>(Port.class);
    }
   
   
   @SuppressWarnings("unchecked")
   public void save(Port port) throws ExceptionSys {   

       try {
    	  if(  !port.getId().equals("") ){
    		  portDAO.update(port);
              logger.info("Modify information of port .."+port.getName()+" id: "+port.getId());    		  
    	  }else{
    		  portDAO.save(port);
    		  logger.info("Save information of port .. "+port.getName()+" id: "+port.getId());    		  
    	  }	
       }catch(Exception e){
       	   logger.info("ERROR "+e);
           throw new ExceptionSys(e);
       }
   }   
     
   
   @SuppressWarnings("unchecked")
   public void delete(String id) throws ExceptionSys {       
       try {    	   
    	  Port port = (Port)portDAO.findById(id); 
    	  portDAO.delete(port);		
    	  logger.info("Delete information of port .."+id);	
       }catch(Exception e){
       	  logger.info("ERROR "+e);
          throw new ExceptionSys(e);
       }
   }
   
   @SuppressWarnings("unchecked")
   public Collection<Port> list() throws ExceptionSys {
	   Collection<Port> listPort = new ArrayList<Port>();		
	   listPort  =  portDAO.findAll();
	   return listPort;
   }
   
   public Port edit(String id) throws ExceptionSys {
	   Port port  =  (Port)portDAO.findById( id );	   	   
	   return port;
   }


}
