package br.com.alcatellucent.csm.utils;

import br.com.alcatellucent.csm.utils.comm.DeviceACLCountServletListener;
import br.com.alcatellucent.csm.utils.comm.DeviceConfigManagerServletListener;
import br.com.alcatellucent.csm.utils.comm.DeviceWalkerServletListener;

public class AgyaDaemonManager {

	private DeviceWalkerServletListener deviceWalkerDaemon = null;

	private DeviceConfigManagerServletListener deviceConfigManager = null;

	private DeviceACLCountServletListener deviceAclPoller = null;

	private static AgyaDaemonManager me = null;

	public static AgyaDaemonManager getInstance() {
		if (null == me) {
			me = new AgyaDaemonManager();
		}
		return me;
	}

	public DeviceWalkerServletListener getDeviceWalkerDaemon() {
		return deviceWalkerDaemon;
	}

	public void setDeviceWalkerDaemon(DeviceWalkerServletListener daemon) {
		deviceWalkerDaemon = daemon;
	}

	/**
	 * @return the deviceConfigManager
	 */
	public DeviceConfigManagerServletListener getDeviceConfigManager() {
		return deviceConfigManager;
	}

	/**
	 * @param deviceConfigManager
	 *           the deviceConfigManager to set
	 */
	public void setDeviceConfigManager(DeviceConfigManagerServletListener deviceConfigManager) {
		this.deviceConfigManager = deviceConfigManager;
	}

	/**
	 * @return the deviceAclPoller
	 */
	public DeviceACLCountServletListener getDeviceAclPoller() {
		return deviceAclPoller;
	}

	/**
	 * @param deviceAclPoller
	 *           the deviceAclPoller to set
	 */
	public void setDeviceAclPoller(DeviceACLCountServletListener deviceAclPoller) {
		this.deviceAclPoller = deviceAclPoller;
	}

}
