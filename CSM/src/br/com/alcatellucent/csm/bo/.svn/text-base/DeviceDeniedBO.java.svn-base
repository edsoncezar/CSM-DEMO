package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.Device;
import br.com.alcatellucent.csm.beans.DeviceDenied;
import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;

public class DeviceDeniedBO {
    
   private static final Logger logger = Logger.getLogger(DeviceDeniedBO.class);

    private HbCommonDAO<DeviceDenied> deviceDeniedBO;

    public DeviceDeniedBO() {
	logger.debug("Carregando DeviceDeniedBO..");
	deviceDeniedBO = new HbCommonDAO<DeviceDenied>(DeviceDenied.class);
    }   
   
   @SuppressWarnings("unchecked")
   public void save(DeviceDenied deviceDenied) throws ExceptionSys {   
	   
       try {
    	  if( !deviceDenied.getPrfid().equals("") ){
    		  deviceDeniedBO.update(deviceDenied);
              logger.info("Modify information of DeviceDenied .."+deviceDenied.getPrfid());    		  
    	  }else{
    		  deviceDeniedBO.save(deviceDenied);
    		  logger.info("Save information of DeviceDenied .."+deviceDenied.getPrfid());    		  
    	  }	
       }catch(Exception e){
       	   logger.info("ERROR "+e);
           throw new ExceptionSys(e);
       }
   }   
     
   
   @SuppressWarnings("unchecked")
   public void delete(String prfid) throws ExceptionSys {       
       try {    	   
    	  DeviceDenied deviceDenied = (DeviceDenied)deviceDeniedBO.findById(prfid);    	  
    	  deviceDeniedBO.delete(deviceDenied);		
    	  logger.info("Delete information of deviceDenied .."+prfid);	
       }catch(Exception e){
       	  logger.info("ERROR "+e);
          throw new ExceptionSys(e);
       }
   }  
   
   public void saveDeviceDenied(UserProfile userProfile,String[] device) throws ExceptionSys {   
       try {
     	  for (int i = 0; i < device.length; i++) {
     		  DeviceDenied  deviceDenied = new DeviceDenied();
     		  deviceDenied.setCsmdeviceId(device[i]);
     		  deviceDenied.setUserProfileId(userProfile.getId());
     		  deviceDenied.setPrfid("");
     		  
     		  this.save(deviceDenied);
		  }
        }catch(Exception e){
        	   logger.info("ERROR "+e);
            throw new ExceptionSys(e);
        }
    }
   
   public void deleteDeviceDenied(String userProfileId) throws ExceptionSys {   
       try {
    	   Collection<DeviceDenied> coll = (Collection<DeviceDenied>) this.findIdProfile(userProfileId);
    	   Iterator<DeviceDenied> it=coll.iterator();
    	   String i=new String();
    	  while(it.hasNext()){
    		  i=it.next().getPrfid();
    		  this.delete(i);
    	  }
       }catch(Exception e){
        	   logger.info("ERROR "+e);
            throw new ExceptionSys(e);
        }
    }   
   	
	   
   public Collection<DeviceDenied> findIdProfile( String userProfileId) throws ExceptionSys {
	   
	   Properties criterios = new Properties();
	   criterios.put("userProfileId",  userProfileId );					
	   Collection coll = (Collection<DeviceDenied>) deviceDeniedBO.findByCriteria(DeviceDenied.class, criterios);

	   return coll;   
   }
   /**
    * Get a list of managed devices by user profile.
    * @param profile
    * @return List, containing only profile´s managed devices. 
    * @throws ExceptionSys
    */
   public List<Device> getManagedDevices(UserProfile profile) throws ExceptionSys{
	   List<Device> managedDevices = new ArrayList<Device>();
	   try {
			DeviceBO deviceBO = new DeviceBO();
			List<Device> listDevice = deviceBO.getListDeviceByHierarchy(profile.getContextId());
			Collection <DeviceDenied>  listDenied =  this.findIdProfile(profile.getId());
			managedDevices = listDevice;
			boolean remove = false;
			for(Device device: listDevice){
				remove = false;
				for(DeviceDenied deviceDenied: listDenied){
					if(device.getId().equals(deviceDenied.getCsmdeviceId())) remove = true;
				}
				if(remove) managedDevices.remove(device);
			}
						
		} catch (ExceptionSys e) {
			 logger.info("ERROR "+e);
	            throw new ExceptionSys(e);
		}
		return managedDevices;
   }
}
