package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.Port;
import br.com.alcatellucent.csm.beans.PortProtocolGroup;
import br.com.alcatellucent.csm.beans.Protocol;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;

public class PortProtocolGroupBO {

    private static final Logger logger = Logger.getLogger(PortProtocolGroupBO.class);

    private HbCommonDAO<PortProtocolGroup> portProtocolGroupDAO;

    public PortProtocolGroupBO() {
	logger.debug("Loading PortProtocolGroupDAO..");
	portProtocolGroupDAO = new HbCommonDAO<PortProtocolGroup>(PortProtocolGroup.class);
    }
   
   
   @SuppressWarnings("unchecked")
   public void save(PortProtocolGroup portProtocolGroup) throws ExceptionSys {   


	   // Verifica se as tabelas possuem elementos nulos (ver na criação do Bean correspondente)
	   // caso exista um objeto valido nulo, a Coleção é limpada com um novo HashSet
	   // Igor - 21-ago-2007
	   //
	   for (Protocol pt : portProtocolGroup.getProtocols()) {
		   if (pt == null) {
			   portProtocolGroup.setProtocols(new HashSet<Protocol>());
		   }
	   }
	   
	   for (Port pt : portProtocolGroup.getPorts()) {
		   if (pt == null) {
			   portProtocolGroup.setPorts(new HashSet<Port>());
		   }
	   }
	   ////////////////////////////////////////////////////////////////////////////////////////////
	   
       try {
    	   
    	   Collection<Port> ports = new HashSet<Port>();
    	   Set<Port> nPorts = new HashSet<Port>();
    	   
    	   ports = portProtocolGroup.getPorts();
    	   
    	   for (Port p : ports) {
    		   p.setValue(0);
    		   nPorts.add(p);
    	   }
    	   
    	   portProtocolGroup.setPorts(nPorts);
    	   //
    	   
    	  if(  !portProtocolGroup.getId().equals("") ){
    		  portProtocolGroupDAO.update(portProtocolGroup);
              logger.info("Modify information of portProtocolGroup "+portProtocolGroup.getName()+" id: "+portProtocolGroup.getId());    		  
    	  }else{
    		  portProtocolGroupDAO.save(portProtocolGroup);
    		  logger.info("Save information of portProtocolGroup "+portProtocolGroup.getName()+" id: "+portProtocolGroup.getId());    		  
    	  }	
       }catch(Exception e){
       	   logger.info("ERROR "+e);
           throw new ExceptionSys(e);
       }
   }   
     
   
   @SuppressWarnings("unchecked")
   public void delete(String id) throws ExceptionSys {       
       try {    	   
    	  PortProtocolGroup port = (PortProtocolGroup)portProtocolGroupDAO.findById(id); 
    	  portProtocolGroupDAO.delete(port);		
    	  logger.info("Delete information of portProtocolGroup "+id);	
       }catch(Exception e){
       	  logger.info("ERROR "+e);
          throw new ExceptionSys(e);
       }
   }
   
   @SuppressWarnings("unchecked")
   public Collection<PortProtocolGroup> list() throws ExceptionSys {
	   Collection<PortProtocolGroup> listPortProtocolGroup = new ArrayList<PortProtocolGroup>();		
	   listPortProtocolGroup  =  portProtocolGroupDAO.findAll();
	   return listPortProtocolGroup;
   }
   
   public PortProtocolGroup edit(String id) throws ExceptionSys {
	   PortProtocolGroup portProtocolGroup  =  (PortProtocolGroup)portProtocolGroupDAO.findById( id );	   	   
	   return portProtocolGroup;
   }


}
