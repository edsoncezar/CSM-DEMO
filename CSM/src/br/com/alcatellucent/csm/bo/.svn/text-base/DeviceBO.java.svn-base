package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.alcatellucent.csm.beans.Context;
import br.com.alcatellucent.csm.beans.Device;
import br.com.alcatellucent.csm.beans.DeviceDenied;
import br.com.alcatellucent.csm.beans.ProcessorPacket;
import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;

public class DeviceBO {

	private static final Logger logger = Logger.getLogger(DeviceBO.class);

	private HbCommonDAO<Device> deviceDao;

	public DeviceBO() {
		logger.debug("Carregando DeviceBO..");
		deviceDao = new HbCommonDAO<Device>(Device.class);
	}

	@SuppressWarnings("unchecked")
	public void save(Device device) throws ExceptionSys {

		try {

			if (null != device.getId() && !device.getId().equals("")) {
				deviceDao.update(device);
				logger.info("Modify information of device " + device.getName() + " id: " + device.getId());
			} else {
				// Creating a default configuration with two DPPMS ////////////////
				setDefaultCSConfig(device);
				// /////////////////////////////////////////////////////////////////
				deviceDao.save(device);

				logger.info("Save information of device " + device.getName() + " id: " + device.getId());
			}
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void delete(String id) throws ExceptionSys {
		try {
			Device device = (Device) deviceDao.findById(id);
			deleteRelations(device);
			deviceDao.delete(device);
			logger.info("Delete information of device " + id);
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<Device> list() throws ExceptionSys {
		Collection<Device> listDevice = new ArrayList<Device>();
		listDevice = deviceDao.findAll();
		return listDevice;
	}

	public Device edit(String id) throws ExceptionSys {
		Device device = (Device) deviceDao.findById(id);
		return device;
	}

	@SuppressWarnings("unchecked")
	public Collection<Device> listDeviceContextos() throws ExceptionSys {
		Collection<Device> listDevice = new ArrayList<Device>();
		listDevice = deviceDao.findAll();
		return listDevice;
	}

	public List<Device> getListDeviceByHierarchy(String initialId) throws ExceptionSys {

		List<Device> listDevices = new ArrayList<Device>();
		List<Context> listContext = new ArrayList<Context>();
		List<Device> auxListDevices = null;
		// Get All context list /////////////////////////////////
		ContextBO contextBO = new ContextBO();
		listContext = contextBO.getListContextChildren(initialId, true);
		for (Context context : listContext) {
			auxListDevices = new ArrayList<Device>();
			auxListDevices = getDevicesByContextId(context.getId());
			for (Device device : auxListDevices) {
				listDevices.add(device);
			}
		}
		// //////////////////////////////////////////////////////
		return listDevices;

	}

	@SuppressWarnings("unchecked")
	public List<Device> getDevicesByContextId(String contextId) {
		Session session = null;
		List<Device> listDevices = new ArrayList<Device>();

		String hql = "from Device where contextId = '" + contextId + "'";
		try {
			session = HibernateUtil.currentSession();
			Query query = session.createQuery(hql);

			listDevices = query.list();
		} finally {
			HibernateUtil.closeSession();
		}

		return listDevices;

	}

	@SuppressWarnings("unchecked")
	public List<Device> getDevicesByContextId(String contextId, UserProfile userProfile) {
		Session session = null;
		List<Device> listDevices = new ArrayList<Device>();

		DeviceDeniedBO deviceDeniedBO = new DeviceDeniedBO();
		Collection<DeviceDenied> listDeviceDenied = new ArrayList<DeviceDenied>();
		StringBuffer devDenied = new StringBuffer();
		String in = null;

		try {

			listDeviceDenied = deviceDeniedBO.findIdProfile(userProfile.getId());
			devDenied = new StringBuffer();
			devDenied.append("(");

			for (DeviceDenied d : listDeviceDenied) {
				devDenied.append("'" + d.getCsmdeviceId() + "',");
			}

			in = devDenied.toString();
			in = (in.substring(0, (in.length() - 1))) + ")";

		} catch (ExceptionSys e) {
			logger.error("Error in getDevices for context " + contextId, e);
		}

		String hql = "from Device where contextId = '" + contextId + "'";
		hql += in.length() > 2 ? " and id not in " + in : "";
		try {
			session = HibernateUtil.currentSession();
			Query query = session.createQuery(hql);

			listDevices = query.list();
		} finally {
			HibernateUtil.closeSession();
		}

		return listDevices;

	}

	// This method creates a default CS2000 configuration with two DPPM´s
	public void setDefaultCSConfig(Device device) throws ExceptionSys {
		// ProcessorPacketBO processorPacketBO = new ProcessorPacketBO();
		ProcessorPacket pPacket1 = new ProcessorPacket(); // DPPM object
		ProcessorPacket pPacket2 = new ProcessorPacket();
		HashSet<ProcessorPacket> ppacketList = new HashSet<ProcessorPacket>();
		// Saving the first one /////////////////////////////////
		// setting default DPPMS info //////////////////////////////////
		// pPacket1.setId("");
		pPacket1.setDescription("DPPM 1 from :" + device.getName());
		pPacket1.setNumber(1);
		pPacket1.setName("DPPM 1");
		// pPacket1.setDeviceId(device.getId());
		pPacket1.setDevice(device);
		// processorPacketBO.save(pPacket1);
		ppacketList.add(pPacket1);

		// Updating the number and saving the second //////////////////
		pPacket2 = new ProcessorPacket();
		// pPacket2.setId("");
		pPacket2.setDescription("DPPM 2 from :" + device.getName());
		pPacket2.setNumber(2);
		pPacket2.setName("DPPM 2");
		// pPacket2.setDeviceId(device.getId());
		pPacket2.setDevice(device);
		// processorPacketBO.save(pPacket2);
		ppacketList.add(pPacket2);

		device.setProcessorPacketList(ppacketList);
	}

	// / delete all relations all dppms and asm
	protected void deleteRelations(Device device) throws ExceptionSys {
		// Deleting device manager ///////////////////////////////
		// DeviceManagerBO deviceManagerBO = new DeviceManagerBO();
		// deviceManagerBO.deleteByDeviceId(device.getId());
		// //Deleting processorPacket ////////////////////////////
		ProcessorPacketBO processorPacketBO = new ProcessorPacketBO();
		processorPacketBO.deleteByDeviceId(device.getId());

	}

}
