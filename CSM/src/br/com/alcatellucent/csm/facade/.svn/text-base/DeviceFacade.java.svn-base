package br.com.alcatellucent.csm.facade;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.Device;
import br.com.alcatellucent.csm.bo.DeviceBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;

public class DeviceFacade {
    
 	private static final Logger logger = Logger.getLogger(DeviceFacade.class);
 	
 	private DeviceBO deviceBO;

	public DeviceFacade() {
		logger.debug("Carregando UsersFacade...");        
		deviceBO = new  DeviceBO();
	}

	
	public void delete(String id) throws ExceptionSys {
		deviceBO.delete(id);
	}
	
	public void save(Device device) throws ExceptionSys {
		deviceBO.save(device);
	}
	
	public Collection<Device> list() throws ExceptionSys {
		return deviceBO.list();
	}
	
	public Device edit(String id) throws ExceptionSys {
		return deviceBO.edit(id);
	}
	
	public List<Device> getListDeviceByHierarchy(String initialId) throws ExceptionSys{
		return deviceBO.getListDeviceByHierarchy(initialId);
	}
	
	
	
	
}
