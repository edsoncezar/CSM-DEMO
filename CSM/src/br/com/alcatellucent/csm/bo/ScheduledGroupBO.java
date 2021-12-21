package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.PortProtocolGroup;
import br.com.alcatellucent.csm.beans.ScheduledGroup;
import br.com.alcatellucent.csm.beans.ScheduledProtocol;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;

public class ScheduledGroupBO {
    
    private static final Logger logger = Logger.getLogger(ScheduledGroupBO.class);

    private HbCommonDAO<ScheduledGroup> scheduledGroupDAO;

    public ScheduledGroupBO() {
	logger.debug("Loading ScheduledGroupDAO..");
	scheduledGroupDAO = new HbCommonDAO<ScheduledGroup>(ScheduledGroup.class);
    }   
   
   @SuppressWarnings("unchecked")
   public void save(ScheduledGroup scheduledGroup) throws ExceptionSys {   


	   // Verifica se as tabelas possuem elementos nulos (ver na criação do Bean correspondente)
	   // caso exista um objeto valido nulo, a Coleção é limpada com um novo HashSet
	   // Igor - 21-ago-2007
	   //
	   for (ScheduledProtocol pt : scheduledGroup.getScheduledProtocols()) {
		   if (pt == null) {
			   scheduledGroup.setScheduledProtocols(new HashSet<ScheduledProtocol>());
		   }
	   }
	   
	   ////////////////////////////////////////////////////////////////////////////////////////////
	   
       try {
    	  if(  null != scheduledGroup.getId() && !scheduledGroup.getId().equals("") ){
    		  scheduledGroupDAO.update(scheduledGroup);
              logger.info("Modify information of ScheduledGroup .."+scheduledGroup.getName());    		  
    	  }else{
    		  scheduledGroupDAO.save(scheduledGroup);
    		  logger.info("Save information of ScheduledGroup .."+scheduledGroup.getName());    		  
    	  }	
       }catch(Exception e){
       	   logger.info("ERROR "+e);
           throw new ExceptionSys(e);
       }
   }   
     
   
   @SuppressWarnings("unchecked")
   public void delete(String id) throws ExceptionSys {       
       try {    	   
    	  ScheduledGroup port = (ScheduledGroup)scheduledGroupDAO.findById(id); 
    	  scheduledGroupDAO.delete(port);		
    	  logger.info("Delete information of ScheduledGroup .."+id);	
       }catch(Exception e){
       	  logger.info("ERROR "+e);
          throw new ExceptionSys(e);
       }
   }
   
   @SuppressWarnings("unchecked")
   public Collection<ScheduledGroup> list() throws ExceptionSys {
	   Collection<ScheduledGroup> listScheduledGroup = new ArrayList<ScheduledGroup>();		
	   listScheduledGroup  =  scheduledGroupDAO.findAll();
	   return listScheduledGroup;
   }
   
   public ScheduledGroup edit(String id) throws ExceptionSys {
	   ScheduledGroup scheduledGroup  =  (ScheduledGroup)scheduledGroupDAO.findById( id );	   	   
	   return scheduledGroup;
   }
   
   public ScheduledGroup copyPortProtocolGroupById(String id) throws ExceptionSys{
	   ScheduledGroup scheduledGroup = new ScheduledGroup();
	   PortProtocolGroupBO ppGroupBO = new PortProtocolGroupBO();
	   PortProtocolGroup ppGroup = ppGroupBO.edit(id);
	   scheduledGroup = copyPortProtocolGroup(ppGroup);
	   return scheduledGroup;
   }
   
   /**
    * This method converts a PortProtocolGroup to ScheduledGroup by the set of key within a Array
    * @param groupsId
    * @return
    * @throws ExceptionSys
    */
   public Set<ScheduledGroup> copyAllPortProtocolGroupById(String[] groupsId) throws ExceptionSys{
	   Set<ScheduledGroup> scheduledGroups	= new HashSet<ScheduledGroup>();
	   ScheduledGroup scheduledGroup 		= new ScheduledGroup();
	   PortProtocolGroupBO ppGroupBO 		= new PortProtocolGroupBO();
	   
	   for(String id: groupsId){
		   if(null != id && !id.equals("") ){
			   scheduledGroup 				= new ScheduledGroup();
			   PortProtocolGroup ppGroup 	= ppGroupBO.edit(id);
			   scheduledGroup 				= copyPortProtocolGroup(ppGroup);
			   scheduledGroups.add(scheduledGroup);
		   }
	   }
	  
	   return scheduledGroups;
   }
   
   /*
    * This method converts a PortProtocolGroup to a ScheduledGroup
    */
   public ScheduledGroup copyPortProtocolGroup(PortProtocolGroup portProtocolGroup) throws ExceptionSys{
	   ScheduledGroup scheduledGroup = new ScheduledGroup();
	   
	   PortProtocolGroupBO ppGroupBO =  new PortProtocolGroupBO();
	   
	   // seting parameters ////////////////////////////////////////////
	   scheduledGroup.setDescription(portProtocolGroup.getDescription());
	   scheduledGroup.setFlActive(portProtocolGroup.getFlActive());
	   scheduledGroup.setName(portProtocolGroup.getName());
	   scheduledGroup.setOriginalId(portProtocolGroup.getId());
	   scheduledGroup.setFlowsValue(portProtocolGroup.getFlowsValue());
	   scheduledGroup.setDownStreamValue(portProtocolGroup.getDownStreamValue());
	   scheduledGroup.setDownStreamUnit(portProtocolGroup.getDownStreamUnit());
	   scheduledGroup.setUpStreamValue(portProtocolGroup.getUpStreamValue());
	   scheduledGroup.setUpStreamUnit(portProtocolGroup.getUpStreamUnit());
	   // copy protocols within ///////////////////////////////////////
	   Set<ScheduledProtocol> copyOfProtocols =  new HashSet<ScheduledProtocol>();
	   ScheduledProtocolBO scheduledProtocolBO = new ScheduledProtocolBO();
	   scheduledProtocolBO.copyAllProtocols(portProtocolGroup.getProtocols(), copyOfProtocols, scheduledGroup);
	   scheduledGroup.setScheduledProtocols(copyOfProtocols);
	   
	   return scheduledGroup;
   }
   
   public void populateCollections(Collection<ScheduledGroup> newCollection, Collection<ScheduledGroup> oldCollection) {
	   Boolean add 		= false;
	   Boolean remove 	= false;
	   Collection<ScheduledGroup> auxCollection;
	   for(ScheduledGroup newScheduledGroup: newCollection){
		   add 		= true;
		   
		   
		   for(ScheduledGroup oldScheduledGroup: oldCollection){
			   if(newScheduledGroup.getOriginalId().equals(oldScheduledGroup.getOriginalId())){
				  add = false;
				  break;
			   }
			   
		   }
		   
		   if(add){
			   oldCollection.add(newScheduledGroup);
		   }
	   }
	   
	   auxCollection = new HashSet<ScheduledGroup>(oldCollection);
	   for(ScheduledGroup oldScheduledGroup: auxCollection){
		   remove = true;
		   
		   
		   for(ScheduledGroup newScheduledGroup: newCollection){
			   if(oldScheduledGroup.getOriginalId().equals(newScheduledGroup.getOriginalId())){
				  remove = false;
				  break;
			   }
		   }
		   
		   if(remove){
			   oldCollection.remove(oldScheduledGroup);
		   }
	   }
	   
   }

}
