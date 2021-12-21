package br.com.alcatellucent.csm.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.Context;
import br.com.alcatellucent.csm.beans.Device;
import br.com.alcatellucent.csm.beans.DeviceManager;
import br.com.alcatellucent.csm.beans.PoolingHistory;
import br.com.alcatellucent.csm.bo.ContextBO;
import br.com.alcatellucent.csm.bo.DeviceBO;
import br.com.alcatellucent.csm.bo.DeviceManagerBO;
import br.com.alcatellucent.csm.bo.PoolingHistoryBO;
import br.com.alcatellucent.csm.cs.client.CsClientProxy;
import br.com.alcatellucent.csm.cs.client.CsClientProxyConnectionException;
import br.com.alcatellucent.csm.exception.ExceptionSys;

public class DeviceWalkerServletListener extends Thread implements ServletContextListener {

	private static final Logger _logger = Logger.getLogger(DeviceWalkerServletListener.class);

	public static String MY_NAME = "DeviceWalkerRobot";
	private static String ASM_TEST_VARIABLE = "A32_droppedPktsPerProtocol";
	private static int MY_MINUTS = 1000 * 60;

	private HashMap<String, DeviceManager> _deviceMap = null;
	private int _initTimeOut = 5000;
	private int _connectionTimeOut = 5000;
	private int _loopTime = 60000;
	private boolean _isInitialized = false;
	private boolean _mustBeKilled = false;
	private boolean _isUpdated = false;

	/**
	 * @param loopTime
	 *           the loopTime to set
	 */
	public void setLoopTime(int loopTime) {
		_logger.info("LoopTime set to " + loopTime);
		this._loopTime = loopTime;
	}

	/**
	 * @return the connectionTimeOut
	 */
	public void setConnectionTimeOut(int timeOut) {
		_logger.info("ConnectionTimeOut set to " + timeOut);
		this._connectionTimeOut = timeOut;
	}

	/**
	 * @return the isInitialized
	 */
	boolean isInitialized() {
		return _isInitialized;
	}

	public void killMe() {
		this._mustBeKilled = true;
	}

	public void contextDestroyed(ServletContextEvent contextEvent) {
		_logger.info(MY_NAME + " is about to be destroyed");
		_mustBeKilled = true;
		this.interrupt(); // Interrupt sleep for the shutdown process
	}

	public void contextInitialized(ServletContextEvent contextEvent) {
		contextEvent.getServletContext().setAttribute(MY_NAME, this);
		_logger.info(MY_NAME + " is alive!");
		this.setName(MY_NAME);
		this.start();
	}

	private void init() throws ServletException {

		System.out.println("****************************************");
		System.out.println("* Init " + this.getClass().getName());
		System.out.println("****************************************");

		_logger.info(MY_NAME + " is starting...");
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
			_isInitialized = false;
			throw new ServletException("Initialization failed", e);
		}
		_isInitialized = true;
		_logger.info("Initialization completed");
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
				sleep(_initTimeOut);
			} catch (ServletException e) {
				_logger.error("Initialization process has failed", e);
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
				testCommunication();
				sleep(_loopTime);
			} catch (InterruptedException e) {
				_logger.info("Execution interrupted");
			} catch (ExceptionSys e) {
				_logger.error("ExceptionSys received from testCommunication", e);
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
	private void testCommunication() throws ExceptionSys {
		boolean finished = true;
		if (_isUpdated) {
			_isUpdated = false;
		}
		do {
			_logger.info("Begining tests...");
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
			_logger.info("Ending tests...");
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
			isAlive = testCommunication(asm);
			_logger.debug("Testing: " + asm.getId() + " - " + asm.getHost() + ":" + asm.getPort());
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
		CsClientProxy myProxy = new CsClientProxy(asm.getHost(), asm.getPort());
		try {
			myProxy.setTimeout(_connectionTimeOut);
			myProxy.getInt(1, ASM_TEST_VARIABLE);
			_logger.debug("Communication Successfully to: " + asm.getHost());
		} catch (CsClientProxyConnectionException e) {
			return false;
		} catch (Exception e) {
			_logger.error("Test communication error: ", e);
			return false;
		}
		return true;
	}

	/**
	 * Notify the Listener to update the device map with the information of the
	 * device that has been (inserted/updated/deleted).<BR>
	 * This method forces the testing procedure to restart due the device list
	 * update
	 * 
	 * @param deviceId
	 *           Device Manager (ASM) Unique Identification
	 * @throws ExceptionSys
	 *            When {@code DeviceManagerBO} reports a problem to load the
	 *            DeviceManager from the database
	 */
	public void notifyChangedDeviceManager(String deviceId) throws ExceptionSys {
		synchronized (_deviceMap) {
			_isUpdated = true;
			DeviceManagerBO deviceManagerBO = new DeviceManagerBO();
			DeviceManager device = deviceManagerBO.edit(deviceId);
			int mapSize = 0;
			if (device == null) {
				_logger.debug("Removing device: " + deviceId);
				_deviceMap.remove(deviceId);
			} else {
				mapSize = _deviceMap.size();
				_deviceMap.put(deviceId, device);
				if (mapSize == _deviceMap.size()) {
					_logger.debug("Updating device: " + deviceId);
				} else {
					_logger.debug("Adding device: " + deviceId);
				}
			}
		}
	}

	public void savePoolingHistory(PoolingHistory poolingHistory) throws ExceptionSys {
		PoolingHistoryBO poolingHistoryBO = new PoolingHistoryBO();
		poolingHistoryBO.save(poolingHistory);

	}

	public void savePoolingHistory(String deviceId, Long poolTime, Boolean poolStatus) throws ExceptionSys {
		PoolingHistory poolingHistory = new PoolingHistory();
		DeviceBO deviceBO = new DeviceBO();
		Device device = deviceBO.edit(deviceId);
		poolingHistory.setDevice(device);
		poolingHistory.setPoolTime(poolTime);
		poolingHistory.setPoolStatus(poolStatus);
		savePoolingHistory(poolingHistory);
	}

}
