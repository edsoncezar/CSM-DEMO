package br.com.alcatellucent.csm.beans;

import br.com.alcatellucent.csm.beans.common.CustomObject;
import br.com.alcatellucent.csm.utils.DateUtil;

public class DeviceManager extends CustomObject {

	private String host;
	private int port = 3674;
	private Integer poolPeriod;
	private Device device;
	private long lastPoolingTime = System.currentTimeMillis();
	private int pollingErrors = 0;

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the poolPeriod
	 */
	public Integer getPoolPeriod() {
		return poolPeriod;
	}

	/**
	 * @param poolPeriod
	 *            the poolPeriod to set
	 */
	public void setPoolPeriod(Integer poolPeriod) {
		this.poolPeriod = poolPeriod;
	}

	/**
	 * @return the device
	 */
	public Device getDevice() {
		if (null == device) {
			device = new Device();
		}
		return device;
	}

	/**
	 * @param device
	 *            the device to set
	 */
	public void setDevice(Device device) {
		this.device = device;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the lastPoolingTime
	 */
	public long getLastPoolingTime() {
		return lastPoolingTime;
	}

	/**
	 * @param lastPoolingTime
	 *            the lastPoolingTime to set
	 */
	public void setLastPoolingTime(long lastPoolingTime) {
		this.lastPoolingTime = lastPoolingTime;
	}

	/**
	 * @return the pollingErrors
	 */
	public int getPollingErrors() {
		return pollingErrors;
	}

	/**
	 * @param pollingErrors
	 *            the pollingErrors to set
	 */
	public void setPollingErrors(int pollingErrors) {
		this.pollingErrors = pollingErrors;
	}
	public String getLastPoolingDateFormatted() {
		
		return DateUtil.longToString(DateUtil.getLongToDate(this.getLastPoolingTime()));
		
	}
	
	
}
