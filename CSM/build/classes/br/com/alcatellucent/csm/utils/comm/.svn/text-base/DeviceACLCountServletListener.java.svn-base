package br.com.alcatellucent.csm.utils.comm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.AclRecipient;
import br.com.alcatellucent.csm.beans.Context;
import br.com.alcatellucent.csm.beans.Device;
import br.com.alcatellucent.csm.beans.ProcessorPacket;
import br.com.alcatellucent.csm.bo.AclBO;
import br.com.alcatellucent.csm.bo.AclRecipientBO;
import br.com.alcatellucent.csm.bo.ContextBO;
import br.com.alcatellucent.csm.bo.DeviceBO;
import br.com.alcatellucent.csm.bo.ProcessorPacketBO;
import br.com.alcatellucent.csm.cs.client.CsClientProxy;
import br.com.alcatellucent.csm.cs.client.CsClientProxyConnectionException;
import br.com.alcatellucent.csm.cs.client.message.CSAnswer_GET;
import br.com.alcatellucent.csm.cs.client.message.CSParameterVar;
import br.com.alcatellucent.csm.cs.client.message.bo.CSAcl;
import br.com.alcatellucent.csm.cs.client.message.bo.CSVarConstants;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.struts.CsmPlugin;
import br.com.alcatellucent.csm.utils.BasicValueCheck;
import br.com.alcatellucent.csm.utils.Utils;

public class DeviceACLCountServletListener extends DeviceBasicServletListener {

	public static final String MY_NAME = "ACLCounterRobot";

	public static final String ACL_COUNTER_VAR = "M32_acl_counter";

	/**
	 * Data-structure to store all the device managers to be polled
	 */
	private HashMap<String, Device> _deviceMap = null;

	HashMap<String, ACLCountProcessorPacket> _dppmMap = new HashMap<String, ACLCountProcessorPacket>();

	private List<String> _changedDeviceBuffer = new ArrayList<String>();

	/**
	 * Indicate that this was updated
	 */
	// private boolean _isUpdated = false;
	public DeviceACLCountServletListener() {
		_logger = Logger.getLogger(DeviceACLCountServletListener.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent contextEvent) {
		contextInitialized(contextEvent, MY_NAME);
	}

	protected void init() throws ServletException {

		while (!CsmPlugin.isSystemRunning()) {
			try {
				_logger.debug("Waiting for the CsmPlugin to start...");
				sleep(getInitTimeOut());
			} catch (InterruptedException e) {
				return;
			}
		}

		System.out.println("***********************************************************************");
		System.out.println("**                       Loading ACLCountRobot                       **");
		System.out.println("***********************************************************************");

		_logger.debug(MY_NAME + " : Starting...");
		_deviceMap = new HashMap<String, Device>();
		ContextBO contextBO = new ContextBO();
		DeviceBO deviceBO = new DeviceBO();
		Context rootContext = null;
		List<Device> deviceList = null;

		try {
			rootContext = contextBO.findRoot();
			deviceList = deviceBO.getListDeviceByHierarchy(rootContext.getId());
			// Fills the deviceMap with device in hierarchy
			for (Device device : deviceList) {
				if (!BasicValueCheck.isEmptyString(device.getId())) {
					initPopulateDppmMapFromDevice(device);
				}
			}
		} catch (ExceptionSys e) {
			setIsInitialized(false);
			throw new ServletException("Initialization failed", e);
		}
		setIsInitialized(true);
		_logger.debug(MY_NAME + " : Initialization completed");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (!isInitialized()) {
			try {
				init();
				if (!isInitialized()) {
					sleep(getInitTimeOut());
				}
			} catch (ServletException e) {
				_logger.error("Initialization process has failed", e);
				_mustBeKilled = true;
			} catch (InterruptedException e) {
				_logger.info("Execution interrupted - init loop");
				e.printStackTrace();
			} finally {
				if (_mustBeKilled) {
					return;
				}
			}
		}
		while (!_mustBeKilled) {
			try {
				doWork();
				sleep(getLoopTime());
			} catch (InterruptedException e) {
				_logger.info("Execution interrupted");
			} catch (ExceptionSys e) {
				_logger.error("ExceptionSys received from doWork", e);
			}
		}
		return;
	}

	@Override
	protected void doWork() throws ExceptionSys {
		Iterator<Device> iterDevice = null;
		updateDeviceMap();
		iterDevice = _deviceMap.values().iterator();
		// I don't need to synchronize the access to the device map, because the
		// dppms to be polled are in the other structure. If it's not still there,
		// I will found a null and do nothing!
		while (iterDevice.hasNext()) {
			countACL(iterDevice.next());
		}
		verifyForEndOfAttack();
		applyNewAcl();
	}

	/**
	 * Nothing for now
	 */
	private void applyNewAcl() {
		Iterator<ACLCountProcessorPacket> iter = null;
		ACLCountProcessorPacket aclDppm = null;
		AclBO aclBO = new AclBO();
		synchronized (_dppmMap) {
			iter = _dppmMap.values().iterator();
			while (iter.hasNext()) {
				aclDppm = iter.next();
				synchronized (aclDppm) {
					try {
						if (aclDppm.getAclListToBeApplied().size() > 0) {
							String[][] newCounter = aclDppm.getNewCounterArray();
							CSParameterVar newCounterVar = new CSParameterVar(ACL_COUNTER_VAR, 0, 0, newCounter.length, 2);
							newCounterVar.setValue(newCounter);
							aclBO.appyAcl(aclDppm.getDppmId(), aclDppm.getAclListToBeApplied(), newCounterVar);
							aclDppm.setApplied();
						}
					} catch (ExceptionSys e) {
						_logger.error("Error applying ACL on Dppm: " + aclDppm.getDppmId(), e);
					}
				}
			}
		}
	}

	/**
	 * Nothing for now
	 */
	private void verifyForEndOfAttack() {
		// Test if the Device is null
	}

	private void updateDeviceMap() throws ExceptionSys {
		Device deviceChanged = null;
		Device deviceInMemory = null;
		DeviceBO deviceBO = new DeviceBO();
		int i = 0;
		// The access to the _deviceMap does not need to be synchronized because
		// items are note inserted and deleted in different places, after the
		// counter is initialized
		synchronized (_changedDeviceBuffer) {
			for (i = 0; i < _changedDeviceBuffer.size(); i++) {
				deviceChanged = deviceBO.edit(_changedDeviceBuffer.get(i));
				deviceInMemory = _deviceMap.get(deviceChanged.getId());
				if (deviceInMemory != null) { // Already stored
					removeDppms(deviceChanged, deviceInMemory);
				} else {
					_deviceMap.put(deviceChanged.getId(), deviceChanged);
					deviceInMemory = _deviceMap.get(deviceChanged.getId());
				}
				insertDppms(deviceChanged, deviceInMemory);
				_changedDeviceBuffer.remove(i);
			}
		}
	}

	private void countACL(Device device) {
		ACLCountProcessorPacket aclDppm = null;
		for (ProcessorPacket dppm : device.getProcessorPacketList()) {
			aclDppm = _dppmMap.get(dppm.getId());
			// This condition is because I don't to synchronize the access to the
			// DPPM structure during the whole process
			countACL(device, aclDppm);
		}
	}

	private void countACL(Device device, ACLCountProcessorPacket aclDppm) {
		int aclsToCount = 0;
		if (device != null && aclDppm.getAclList() != null && aclDppm.getAclList().size() > 0) {
			aclsToCount = aclDppm.getAclList().size();
			ArrayList<CSParameterVar> paramList = new ArrayList<CSParameterVar>();
			CSParameterVar csAclCount = new CSParameterVar(ACL_COUNTER_VAR, 0, 0, 0, 2);
			paramList.add(csAclCount);
			CSAnswer_GET answer = null;
			CsClientProxy myProxy = new CsClientProxy(device.getDeviceManager().getHost(), device.getDeviceManager()
					.getPort());
			myProxy.setTimeout(getConnectionTimeOut());
			String[][] aclCount = null;
			boolean status = true;
			int j = 0;
			int qtdeToRequest = (aclsToCount > CSVarConstants.PROTOCOL_MAX_ROWS) ? CSVarConstants.PROTOCOL_MAX_ROWS
					: aclsToCount;
			aclCount = new String[aclsToCount][2];
			long[] atualAclCount = new long[aclsToCount];
			csAclCount.setValue(aclCount);
			csAclCount.setRowNumber(qtdeToRequest);
			do {
				if (j > aclsToCount) {
					break;
				}
				if (j + qtdeToRequest > aclsToCount) {
					qtdeToRequest = aclsToCount - j;
					csAclCount.setRowNumber(qtdeToRequest);
				}
				try {
					csAclCount.setInitRow(j);
					answer = myProxy.getParameter(aclDppm.getNumber(), paramList);
					aclCount = answer.getVarList().get(0).getValue();
					for (int i = j; ((i - j) < qtdeToRequest); i++) {
						atualAclCount[i] = ((Long.parseLong(aclCount[i - j][1])) << 32) + Long.parseLong(aclCount[i - j][0]);
					}
					j += CSVarConstants.PROTOCOL_MAX_ROWS;
					if (j > CSAcl.FILTER_TABLE_ROWS - 1) {
						break;
					}
				} catch (CsClientProxyConnectionException e) {
					status = false;
					System.out.println("Connection error...");
				} catch (Exception e) {
					status = false;
					e.printStackTrace();
					break;
				}
			} while (true);
			if (status == true) {
				synchronized (aclDppm) {
					// If the device or dppm was not updated, store new values,
					// otherwise count in the next poll
					aclDppm.addAtualCounter();
					if (!aclDppm.isUpdated()) {
						for (int i = 0; i < aclsToCount; i++) {
							aclDppm.aclCountValues[aclDppm.getAtualCounter()][i] = atualAclCount[i];
						}
					}
					aclDppm.aclCountTimestamp[aclDppm.getAtualCounter()] = System.currentTimeMillis();
				}
			}
		}
	}

	/**
	 * Notify the Listener to update the device map with the information of the
	 * device that has been (inserted/updated/deleted).<BR>
	 * This method forces the testing procedure to restart due the device list
	 * update
	 * 
	 * @param dppmId
	 *           Device Manager (ASM) Unique Identification
	 * @throws ExceptionSys
	 *            When {@code DeviceManagerBO} reports a problem to load the
	 *            DeviceManager from the database
	 */
	public void notifyChangedAcl(String dppmId, List<AclRecipient> aclList) throws ExceptionSys {
		ACLCountProcessorPacket aclDppm = _dppmMap.get(dppmId);
		ProcessorPacket dppm = null;
		if (aclDppm == null) {
			dppm = new ProcessorPacketBO().edit(dppmId);
			aclDppm = new ACLCountProcessorPacket(0, aclList.size());
			aclDppm.setDppmId(dppmId);
			aclDppm.setNumber(dppm.getNumber());
			_dppmMap.put(dppmId, aclDppm);
		}
		synchronized (aclDppm) {
			aclDppm.setAclListToBeApplied(aclList);
		}
	}

	/**
	 * Notify the manager that a Device has been updated.<BR />
	 * This method searches the changed device buffer
	 * {@code _changedDeviceBuffer} for the currently changed device in such a
	 * way that the changed device is only once in that buffer.
	 * 
	 * @param deviceId
	 *           Device that was changed
	 */
	public void notifyChangedDevice(String deviceId) {
		synchronized (_changedDeviceBuffer) {
			for (String chDevice : _changedDeviceBuffer) {
				if (chDevice.equals(deviceId)) {
					return;
				}
			}
			_changedDeviceBuffer.add(deviceId);
		}
	}

	private void initPopulateDppmMapFromDevice(Device device) throws ExceptionSys {
		for (ProcessorPacket dppm : device.getProcessorPacketList()) {
			populateAclCountFromDppm(dppm);
		}
		_deviceMap.put(device.getId(), device);
	}

	private void insertDppms(Device deviceChanged, Device deviceInMemory) throws ExceptionSys {
		boolean isNewDppm = true;
		for (ProcessorPacket dppmChanged : deviceChanged.getProcessorPacketList()) {
			for (ProcessorPacket dppmInMemory : deviceInMemory.getProcessorPacketList()) {
				isNewDppm = true;
				if (dppmChanged.getId().equals(dppmInMemory.getId())
						&& (dppmChanged.getNumber() == dppmInMemory.getNumber())) {
					isNewDppm = false;
					break;
				}
			}
			if (isNewDppm) {
				populateAclCountFromDppm(dppmChanged);
			}
		}
	}

	private void removeDppms(Device deviceChanged, Device deviceInMemory) {
		boolean dppmStillExists = false;
		for (ProcessorPacket dppmInMemory : deviceInMemory.getProcessorPacketList()) {
			dppmStillExists = false;
			for (ProcessorPacket dppmChanged : deviceChanged.getProcessorPacketList()) {
				if (dppmChanged.getId().equals(dppmInMemory.getId())
						&& (dppmChanged.getNumber() == dppmInMemory.getNumber())) {
					dppmStillExists = true;
					break;
				}
			}
			if (!dppmStillExists) {
				_dppmMap.remove(dppmInMemory.getId());
			}
		}
	}

	private void populateAclCountFromDppm(ProcessorPacket dppm) throws ExceptionSys {

		Collection<AclRecipient> aclRecipientListApplied = null;
		Collection<AclRecipient> aclRecipientListToApply = null;
		ACLCountProcessorPacket aclDppm = null;
		AclRecipientBO aclRecipientBO = new AclRecipientBO();

		aclRecipientListApplied = aclRecipientBO.listAppliedByDppm(dppm.getId());
		aclRecipientListToApply = aclRecipientBO.listToApplyByDppm(dppm.getId());
		if (aclRecipientListApplied == null) {
			aclRecipientListApplied = new ArrayList<AclRecipient>();
		}
		if (aclRecipientListToApply == null) {
			aclRecipientListToApply = new ArrayList<AclRecipient>();
		}

		aclDppm = new ACLCountProcessorPacket(aclRecipientListApplied.size(), aclRecipientListToApply.size());
		aclDppm.setDppmId(dppm.getId());
		aclDppm.setNumber(dppm.getNumber());
		aclDppm.setAclListToBeApplied(Utils.collectionToList(aclRecipientListToApply, AclRecipient.class));
		aclDppm.setAclList(Utils.collectionToList(aclRecipientListApplied, AclRecipient.class));

		synchronized (_dppmMap) {
			_dppmMap.put(aclDppm.getDppmId(), aclDppm);
		}
	}

}
/*
 * public void notifyChangedDeviceManager(Device device) throws ExceptionSys {
 * synchronized (_deviceMap) { _isUpdated = true; DeviceManagerBO
 * deviceManagerBO = new DeviceManagerBO(); DeviceManager asm =
 * deviceManagerBO.edit(device.getId()); int mapSize = 0; if (asm == null) {
 * _logger.debug("Removing device: " + device.getId());
 * removeFromDppmMap(device); _deviceMap.remove(device.getId()); } else {
 * mapSize = _deviceMap.size(); _deviceMap.put(asm.getId(), asm); if (mapSize ==
 * _deviceMap.size()) { removeFromDppmMap(device);
 * populateDppmMapFromAsm(device.getDeviceManager()); _logger.debug("Updating
 * device: " + device.getId()); } else {
 * populateDppmMapFromAsm(device.getDeviceManager()); _logger.debug("Adding
 * device: " + device.getId()); } } } }
 * 
 * private final void removeFromDppmMap(Device device) { synchronized (_dppmMap) {
 * for (ProcessorPacket dppm : device.getProcessorPacketList()) {
 * _dppmMap.remove(dppm.getId()); } } }
 */

/*
 * private HashMap<String, DeviceManager> getDeviceMap() { HashMap<String,
 * DeviceManager> deviceMap = null; AgyaDaemonManager agyaManager =
 * AgyaDaemonManager.getInstance(); DeviceWalkerServletListener daemon =
 * agyaManager.getDeviceWalkerDaemon(); if (daemon == null) {
 * _logger.fatal(DeviceWalkerServletListener.MY_NAME + " not loaded"); } else {
 * if (daemon.isInitialized()) { deviceMap = daemon.getDeviceMap(MY_NAME); }
 * else { _logger.debug(DeviceWalkerServletListener.MY_NAME + " not
 * initialized"); } } return deviceMap; }
 */