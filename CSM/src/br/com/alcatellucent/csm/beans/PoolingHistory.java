/**
 * 
 */
package br.com.alcatellucent.csm.beans;

import java.util.Date;

import br.com.alcatellucent.csm.beans.common.CustomObject;
import br.com.alcatellucent.csm.utils.DateUtil;

/**
 * @author Fernando Caruso
 *
 */
public class PoolingHistory extends CustomObject {
	
	private Long poolTime;
	private Boolean	poolStatus;
	private Device 	device;
	private String	poolTimeToDate;  


	/**
	 * @return the poolTime
	 */
	public Long getPoolTime() {
		return poolTime;
	}
	/**
	 * @param poolTime the poolTime to set
	 */
	public void setPoolTime(Long poolTime) {
		this.poolTime = poolTime;
	}
	/**
	 * @return the poolStatus
	 */
	public Boolean getPoolStatus() {
		if(null == poolStatus){
			poolStatus = false;
		}
		return poolStatus;
	}
	/**
	 * @param poolStatus the poolStatus to set
	 */
	public void setPoolStatus(Boolean poolStatus) {
		this.poolStatus = poolStatus;
	}
	/**
	 * @return the device
	 */
	public Device getDevice() {
		if(null == device){
			device = new Device();
		}
		return device;
	}
	/**
	 * @param device the device to set
	 */
	public void setDevice(Device device) {
		this.device = device;
	}
	/**
	 * @return the poolTime converted to java.util.Date
	 */
	public String getPoolTimeToDate() {
		return DateUtil.longToString(DateUtil.getLongToDate(getPoolTime()));
	}

	  
}
