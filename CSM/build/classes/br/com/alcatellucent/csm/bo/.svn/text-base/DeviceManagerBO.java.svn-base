package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.DeviceManager;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;

/**
 * 
 * @author Fernando Caruso Olívio
 * 
 */

public class DeviceManagerBO {

    private static final Logger logger = Logger.getLogger(DeviceManagerBO.class);

    private HbCommonDAO<DeviceManager> deviceManagerDAO;

    public DeviceManagerBO() {
	logger.debug("Carregando DeviceManagerBO..");
	deviceManagerDAO = new HbCommonDAO<DeviceManager>(DeviceManager.class);
    }

    @SuppressWarnings("unchecked")
    public void save(DeviceManager deviceManager) throws ExceptionSys {

	try {
	    if (!deviceManager.getId().equals("")) {
		deviceManagerDAO.update(deviceManager);
		logger.info("Modify information of Devicemanager .." + deviceManager.getName());
	    } else {
		deviceManager.setLastPoolingTime(0);
		deviceManager.setPollingErrors(0);
		deviceManagerDAO.save(deviceManager);
		logger.info("Save information of Devicemanager "
			+ deviceManager.getName());
	    }
	} catch (Exception e) {
	    logger.info("ERROR " + e);
	    throw new ExceptionSys(e);
	}
    }

    @SuppressWarnings("unchecked")
    public void delete(String id) throws ExceptionSys {
	try {
	    DeviceManager deviceManager = (DeviceManager) deviceManagerDAO
		    .findById(id);
	    deviceManagerDAO.delete(deviceManager);
	    logger.info("Delete information of deviceManager " + id);
	} catch (Exception e) {
	    logger.info("ERROR " + e);
	    throw new ExceptionSys(e);
	}
    }

    @SuppressWarnings("unchecked")
    public Collection<DeviceManager> list() throws ExceptionSys {
	Collection<DeviceManager> listDeviceManager = new ArrayList<DeviceManager>();
	listDeviceManager = deviceManagerDAO.findAll();
	return listDeviceManager;
    }

    public DeviceManager edit(String id) throws ExceptionSys {
	DeviceManager deviceManager = (DeviceManager) deviceManagerDAO
		.findById(id);
	return deviceManager;
    }

    @SuppressWarnings("unchecked")
    public DeviceManager findByDeviceId(String deviceId) throws ExceptionSys {

	try {
	    Properties criterios = new Properties();
	    criterios.put("device.id", deviceId);

	    Collection cDeviceManager = (Collection<DeviceManager>) deviceManagerDAO
		    .findByCriteria(DeviceManager.class, criterios);

	    DeviceManager deviceManagerTemp = new DeviceManager();
	    Iterator iterator = cDeviceManager.iterator();
	    while (iterator.hasNext()) {
		DeviceManager deviceManager = (DeviceManager) iterator.next();
		deviceManagerTemp = deviceManager;
	    }
	    return deviceManagerTemp;
	} catch (ExceptionSys e) {
	    logger.error("Error finding DeviceManager by Device ID ..." + e);
	    throw new ExceptionSys(e);
	}
    }

    public void deleteByDeviceId(String deviceId) throws ExceptionSys {
	DeviceManager deviceManager = findByDeviceId(deviceId);
	if (null != deviceManager.getId())
	    delete(deviceManager.getId());
    }

}
