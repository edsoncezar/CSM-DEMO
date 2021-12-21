package br.com.alcatellucent.csm.utils.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.Context;
import br.com.alcatellucent.csm.beans.Device;
import br.com.alcatellucent.csm.beans.DeviceManager;
import br.com.alcatellucent.csm.beans.PoolingHistory;
import br.com.alcatellucent.csm.beans.ProcessorPacket;
import br.com.alcatellucent.csm.bo.ContextBO;
import br.com.alcatellucent.csm.bo.DeviceBO;
import br.com.alcatellucent.csm.bo.DeviceManagerBO;
import br.com.alcatellucent.csm.bo.PoolingHistoryBO;
import br.com.alcatellucent.csm.cs.client.CsClientProxy;
import br.com.alcatellucent.csm.cs.client.CsClientProxyConnectionException;
import br.com.alcatellucent.csm.cs.client.message.CSParameterVar;
import br.com.alcatellucent.csm.cs.client.message.bo.CSVarConstants;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.struts.CsmPlugin;

/**
 * This Class represents the DeviceWalker Servlet Listener, responsible to query
 * the devices for the up/down status. This is a Service-Thread
 * 
 * @author Alisson Leite de Morais Veras
 *         <Alisson_Leite_De_Morais.Veras@alcatel-lucent.com.br>
 * @version 1.1 <BR>
 *          Copyright &copy; 2006-2007 Alcatel-Lucent. All rights reserved.<BR>
 *          Version History:<BR>
 *          <li>1.1: Changed the pooling method. Now, the sample rate is sent
 *          to the devices, instead of getting the Dropper Packets per Protocol
 */
public class DeviceWalkerServletListener extends DeviceBasicServletListener {

	/**
	 * RAVE variable to be collected.
	 */
	private static String ASM_TEST_VARIABLE = "G32_sample_threshold";

	/**
	 * Number of Milliseconds in one minute
	 */
	private static int MY_MINUTS = 1000 * 60;

	public static final String MY_NAME = "DeviceWalkerRobot";

	/**
	 * Data-structure to store all the device managers to be polled
	 */
	private HashMap<String, DeviceManager> _deviceMap = null;

	/**
	 * Indicate that this was updated
	 */
	private boolean _isUpdated = false;

	public DeviceWalkerServletListener() {
		_logger = Logger.getLogger(DeviceConfigManagerServletListener.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent contextEvent) {
		contextInitialized(contextEvent, MY_NAME);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.alcatellucent.csm.utils.comm.DeviceBasicServletListener#init()
	 */
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
		System.out.println("**                     Loading DeviceWalkerRobot                     **");
		System.out.println("***********************************************************************");

		_logger.debug(MY_NAME + " : Starting...");
		_deviceMap = new HashMap<String, DeviceManager>();
		ContextBO contextBO = new ContextBO();
		DeviceBO deviceBO = new DeviceBO();
		Context rootContext = null;
		List<Device> deviceList = null;
		DeviceManager asm = null;

		try {
			rootContext = contextBO.findRoot();
			deviceList = deviceBO.getListDeviceByHierarchy(rootContext.getId());
			DeviceManagerBO asmBO = new DeviceManagerBO();
			// Fills deviceMap with device in hierarchy
			for (Device cloudshield : deviceList) {
				asm = asmBO.findByDeviceId(cloudshield.getId());
				if (asm.getId() != null) {
					_deviceMap.put(asm.getId(), asm);
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
				sleep(getInitTimeOut());
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
				_logger.debug("Execution interrupted");
			} catch (ExceptionSys e) {
				_logger.error("ExceptionSys received from doWork", e);
			}
		}
		return;
	}

	/**
	 * Test the communication to the ASMs in the map. The process is resumed if a
	 * device is inserted, updated or deleted.
	 * 
	 * @throws ExceptionSys
	 *            When a problem to save the DeviceManager to the DataBase
	 */
	protected void doWork() throws ExceptionSys {
		boolean finished = true;
		if (_isUpdated) {
			_isUpdated = false;
		}
		do {
			_logger.debug("Begining tests...");
			Iterator<DeviceManager> iter = _deviceMap.values().iterator();
			DeviceManager asm = null;
			finished = true;
			while (iter.hasNext()) {
				asm = iter.next();
				testAndUpdate(asm);
				if (_isUpdated) {
					finished = false;
					break;
				}
			}
			if (_isUpdated) {
				_isUpdated = false;
				_logger.debug("Reconfiguration, restarting tests...");
				continue;
			}
			_logger.debug("Ending tests...");
		} while (!finished);
	}

	/**
	 * Test the communication to the ASM and update the ASM information in the
	 * DataBase. The process is aborted if a device is inserted, updated or
	 * deleted.
	 * 
	 * @param asm
	 *           ASM to test
	 * @throws ExceptionSys
	 *            When {@code DeviceManagerBO} reports a problem to save the
	 *            DeviceManager to the database
	 */
	private void testAndUpdate(DeviceManager asm) throws ExceptionSys {
		long now = System.currentTimeMillis();
		DeviceManagerBO deviceManagerBO = null;
		boolean isAlive = true;
		// If needed to be tested
		if ((asm.getPoolPeriod() != 0)
				&& ((asm.getLastPoolingTime() == 0) || ((now - asm.getLastPoolingTime()) > (asm.getPoolPeriod() * MY_MINUTS)))) {
			_logger.debug("Testing: " + asm.getId() + " - " + asm.getHost() + ":" + asm.getPort());
			isAlive = testCommunication(asm);
			synchronized (_deviceMap) {
				if (!_isUpdated) {
					_logger.debug("Updating: " + asm.getId() + " - Is alive=" + isAlive);
					if (isAlive) {
						asm.setPollingErrors(0);
					} else {
						asm.setPollingErrors(asm.getPollingErrors() + 1);
					}
					asm.setLastPoolingTime(now);
					_deviceMap.put(asm.getId(), asm);
					deviceManagerBO = new DeviceManagerBO();
					deviceManagerBO.save(asm);
					// saving pooling history
					// The asm.id is the same device.id
					savePoolingHistory(asm.getId(), now, isAlive);
				}
			}
		}
	}

	/**
	 * Test the communication to the ASM
	 * 
	 * @param asm
	 *           ASM to be tested
	 * @return
	 *           <li>{@code true}: Communication is successfully</li>
	 *           <li>{@code false}: Communication has failed</li>
	 */
	private boolean testCommunication(DeviceManager asm) {
		CSParameterVar csSampleThreshold = new CSParameterVar(ASM_TEST_VARIABLE, 0, 0, 1, 1);
		ArrayList<CSParameterVar> paramList = new ArrayList<CSParameterVar>();
		paramList.add(csSampleThreshold);
		CsClientProxy myProxy = new CsClientProxy(asm.getHost(), asm.getPort());
		myProxy.setTimeout(getConnectionTimeOut());
		String[][] value = new String[1][1];
		boolean result = false;
		for (ProcessorPacket dppm : asm.getDevice().getProcessorPacketList()) {
			value[0][0] = String.valueOf((Long.valueOf(CSVarConstants.UINT_MAX_VALUE) / dppm.getSampleThreshold()));
			csSampleThreshold.setValue(value);
			try {
				myProxy.putParameter(dppm.getNumber(), paramList);
				result = true; // At least one dppm is alive
			} catch (CsClientProxyConnectionException e) {
			} catch (Exception e) {
				_logger.error("Test communication error: ", e);
			}
		}
		_logger.debug("Communication Successfully to: " + asm.getHost());
		return result;
	}

	/**
	 * Notify the Listener to update the device map with the information of the
	 * device that has been (inserted/updated/deleted).<BR>
	 * This method forces the testing procedure to restart due the device list
	 * update
	 * 
	 * @param asmId
	 *           Device Manager (ASM) Unique Identification
	 * @throws ExceptionSys
	 *            When {@code DeviceManagerBO} reports a problem to load the
	 *            DeviceManager from the database
	 */
	public void notifyChangedDeviceManager(Device device) throws ExceptionSys {
		synchronized (_deviceMap) {
			_isUpdated = true;
			DeviceManagerBO deviceManagerBO = new DeviceManagerBO();
			DeviceManager asm = deviceManagerBO.edit(device.getId());
			int mapSize = 0;
			if (asm == null) {
				_logger.debug("Removing device: " + asm.getId());
				_deviceMap.remove(asm.getId());
			} else {
				mapSize = _deviceMap.size();
				_deviceMap.put(asm.getId(), asm);
				if (mapSize == _deviceMap.size()) {
					_logger.debug("Updating device: " + asm.getId());
				} else {
					_logger.debug("Adding device: " + asm.getId());
				}
			}
		}
	}

	private void savePoolingHistory(PoolingHistory poolingHistory) throws ExceptionSys {
		PoolingHistoryBO poolingHistoryBO = new PoolingHistoryBO();
		poolingHistoryBO.save(poolingHistory);

	}

	private void savePoolingHistory(String deviceId, Long poolTime, Boolean poolStatus) throws ExceptionSys {
		PoolingHistory poolingHistory = new PoolingHistory();
		DeviceBO deviceBO = new DeviceBO();
		Device device = deviceBO.edit(deviceId);
		poolingHistory.setDevice(device);
		poolingHistory.setPoolTime(poolTime);
		poolingHistory.setPoolStatus(poolStatus);
		savePoolingHistory(poolingHistory);
	}

	/**
	 * Return the DeviceMap in use for the polling process.
	 * 
	 * @return Device Map
	 */
	public HashMap<String, DeviceManager> getDeviceMap(String requester) {
		if (isInitialized()) {
			HashMap<String, DeviceManager> deviceMap = new HashMap<String, DeviceManager>();
			_logger.debug("Loading device map for " + requester);
			synchronized (_deviceMap) {
				Iterator<DeviceManager> iter = _deviceMap.values().iterator();
				DeviceManager asm = null;
				while (iter.hasNext()) {
					asm = iter.next();
					deviceMap.put(asm.getId(), asm);
				}
			}
			_logger.debug("Finished loading device map for " + requester);
		}
		return _deviceMap;
	}

}
