package br.com.alcatellucent.csm.beans;

import br.com.alcatellucent.csm.beans.common.CustomObject;

/**
 * 
 * @author Igor I. Takats - G&P
 * Date:   14/08/2007
 * 
 * Bean for Protocol Entity
 *
 */
public class ScheduledProtocol extends CustomObject{
	
	private Integer internalNumber;
	private Boolean aclUsed;
	private Boolean isRealTime;
	private Integer flowValue;
	
	private ScheduledGroup scheduledGroup;
	
	// Valores Default
	private ScheduledTrafficValues scheduledTrafficValues;
//
//	public Collection<ScheduledGroup> getScheduledGroups() {
//		return scheduledGroups;
//	}
//	public void setScheduledGroups(
//			Collection<ScheduledGroup> scheduledGroups) {
//		this.scheduledGroups = scheduledGroups;
//	}

	
		
	/**
	 * @return the scheduledTrafficValues
	 */
	public ScheduledTrafficValues getScheduledTrafficValues() {
		if(null == scheduledTrafficValues)
			scheduledTrafficValues = new ScheduledTrafficValues();
		
		return scheduledTrafficValues;
	}
	/**
	 * @param scheduledTrafficValues the scheduledTrafficValues to set
	 */
	public void setScheduledTrafficValues(ScheduledTrafficValues scheduledTrafficValues) {
		this.scheduledTrafficValues = scheduledTrafficValues;
	}
	/**
	 * @return the internalNumber
	 */
	public Integer getInternalNumber() {
		return internalNumber;
	}
	/**
	 * @param internalNumber the internalNumber to set
	 */
	public void setInternalNumber(Integer internalNumber) {
		this.internalNumber = internalNumber;
	}
	/**
	 * @return the aclUsed
	 */
	public Boolean getAclUsed() {
		return aclUsed;
	}
	/**
	 * @param aclUsed the aclUsed to set
	 */
	public void setAclUsed(Boolean aclUsed) {
		this.aclUsed = aclUsed;
	}
	/**
	 * @return the isRealTime
	 */
	public Boolean getIsRealTime() {
		return isRealTime;
	}
	/**
	 * @param isRealTime the isRealTime to set
	 */
	public void setIsRealTime(Boolean isRealTime) {
		this.isRealTime = isRealTime;
	}
	/**
	 * @return the flowValue
	 */
	public int getFlowValue() {
		return ((ScheduledTrafficValues)scheduledTrafficValues).getFlowsValues();
	}
	/**
	 * @param flowValue the flowValue to set
	 */
	public void setFlowValue(int flowValue) {
		this.flowValue = flowValue;
	}
	/**
	 * @return the scheduledGroup
	 */
	public ScheduledGroup getScheduledGroup() {
		if(null == scheduledGroup)
			scheduledGroup = new ScheduledGroup();
		return scheduledGroup;
	}
	/**
	 * @param scheduledGroup the scheduledGroup to set
	 */
	public void setScheduledGroup(ScheduledGroup scheduledGroup) {
		this.scheduledGroup = scheduledGroup;
	}

	
	

}
