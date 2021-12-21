package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.ProcessorPacket;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;

/**
 * 
 * @author Fernando Caruso Olívio
 *
 */

public class ProcessorPacketBO {

    private static final Logger logger = Logger.getLogger(ProcessorPacketBO.class);

    private HbCommonDAO<ProcessorPacket> processorPacketDAO;

    public ProcessorPacketBO() {
	logger.debug("Loading ProcessorPacketBO..");
	processorPacketDAO = new HbCommonDAO<ProcessorPacket>(ProcessorPacket.class);
    }
   
   
   @SuppressWarnings("unchecked")
   public void save(ProcessorPacket processorPacket) throws ExceptionSys {      
	   
	   
       try {
    	  if( !processorPacket.getId().equals("") ){
    		  processorPacketDAO.update(processorPacket);
              logger.info("Modify information of processorPacket .." + processorPacket.getName());    		  
    	  }else{    	
    		  
    		  processorPacketDAO.save(processorPacket);
    		  logger.info("Save information of processorPacket .. " + processorPacket.getName());    		  
    	  }	
       }catch(Exception e){
       	   logger.info("ERROR "+e);
           throw new ExceptionSys(e);
       }
   }   
     
   
   @SuppressWarnings("unchecked")
   public void delete(String id) throws ExceptionSys {       
       try {    	   
    	  ProcessorPacket processorPacket = (ProcessorPacket)processorPacketDAO.findById(id);    	  
    	  processorPacketDAO.delete(processorPacket);		
    	  logger.info("Delete information of processorPacket .." + id );	
       }catch(Exception e){
       	  logger.info("ERROR " + e);
          throw new ExceptionSys(e);
       }
   }
   
   @SuppressWarnings("unchecked")
   public Collection<ProcessorPacket> list() throws ExceptionSys {
	   Collection<ProcessorPacket> listProcessorPacket = new ArrayList<ProcessorPacket>();		
	   listProcessorPacket  =  processorPacketDAO.findAll();
	   return listProcessorPacket;
   }
   
   public ProcessorPacket edit(String id) throws ExceptionSys {
	   ProcessorPacket processorPacket  =  (ProcessorPacket)processorPacketDAO.findById( id );	   	   
	   return processorPacket;
   }
   
   
	@SuppressWarnings("unchecked")
    public Collection<ProcessorPacket> findByDeviceId(String deviceId) throws ExceptionSys {
		
		try {			
			Properties criterios = new Properties();
			criterios.put("device.id",  deviceId  );
			Collection cProcessorPacket = (Collection<ProcessorPacket>) processorPacketDAO.findByCriteria(ProcessorPacket.class, criterios);
	        return cProcessorPacket;
	        
		}catch(ExceptionSys e){
			logger.error("Error finding ProcessorPacket by Device ID ..." + e);
			throw new ExceptionSys(e);
		}
	} 
	
	   
	@SuppressWarnings("unchecked")
    public Collection<ProcessorPacket> findByTrafficPolicyId(String trafficPolicyId) throws ExceptionSys {
		
		try {			
			Properties criterios = new Properties();
			criterios.put("trafficPolicyId",  trafficPolicyId  );
			Collection cProcessorPacket = (Collection<ProcessorPacket>) processorPacketDAO.findByCriteria(ProcessorPacket.class, criterios);
	        return cProcessorPacket;
	        
		}catch(ExceptionSys e){
			logger.error("Error finding ProcessorPacket by TrafficPolicy ID ..." + e);
			throw new ExceptionSys(e);
		}
	} 
	
	@SuppressWarnings("unchecked")
    public Collection<ProcessorPacket> findByAldeId(String aldeId) throws ExceptionSys {
		
		try {			
			Properties criterios = new Properties();
			criterios.put("aldeId",  aldeId  );
			Collection cProcessorPacket = (Collection<ProcessorPacket>) processorPacketDAO.findByCriteria(ProcessorPacket.class, criterios);
	        return cProcessorPacket;
	      
		}catch(ExceptionSys e){
			logger.error("Error finding ProcessorPacket by TrafficPolicy ID ..." + e);
			throw new ExceptionSys(e);
		}
	}
	
	@SuppressWarnings("unchecked")
    public List<String> getUsedNumbers(String deviceId) throws ExceptionSys {
		List<String> usedDppm = new ArrayList<String>();
		try{
			Collection<ProcessorPacket> cProcessorPacket = this.findByDeviceId(deviceId);
			for(ProcessorPacket pPacket: cProcessorPacket){
				usedDppm.add(String.valueOf(pPacket.getNumber()));
			}
		}catch(ExceptionSys e){
			logger.error("Error Getting used identification numbers for ProcessorPacket by Device ID ..." + e);
			throw new ExceptionSys(e);
		}
		return usedDppm;
    }
   
	public void deleteByDeviceId(String deviceId) throws ExceptionSys {
		Collection<ProcessorPacket> cProcessorPacket = findByDeviceId(deviceId);
		for (ProcessorPacket processorPacket : cProcessorPacket) {
			delete(processorPacket.getId());
		}
	}
}
