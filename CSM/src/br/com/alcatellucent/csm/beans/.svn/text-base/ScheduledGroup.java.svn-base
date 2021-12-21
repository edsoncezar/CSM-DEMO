package br.com.alcatellucent.csm.beans;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import br.com.alcatellucent.csm.beans.common.CustomObject;

public class ScheduledGroup extends CustomObject {

	private Boolean flActive;
	private Collection<ScheduledProtocol> scheduledProtocols;
	private Scheduling scheduling; 
	private String originalId;
	private Integer flowsValue;
	private String downStreamValue;
	private String downStreamUnit;
	private String upStreamValue;
	private String upStreamUnit;
	private String control;
	private Integer priority;
	
	/**
	 * @return the flowsValue
	 */
	public Integer getFlowsValue() {
		return flowsValue;
	}

	/**
	 * @param flowsValue the flowsValue to set
	 */
	public void setFlowsValue(Integer flowsValue) {
		this.flowsValue = flowsValue;
	}

	/**
	 * @return the downStreamValue
	 */
	public String getDownStreamValue() {
		return downStreamValue;
	}

	/**
	 * @param downStreamValue the downStreamValue to set
	 */
	public void setDownStreamValue(String downStreamValue) {
		this.downStreamValue = downStreamValue;
	}

	/**
	 * @return the downStreamUnit
	 */
	public String getDownStreamUnit() {
		return downStreamUnit;
	}

	/**
	 * @param downStreamUnit the downStreamUnit to set
	 */
	public void setDownStreamUnit(String downStreamUnit) {
		this.downStreamUnit = downStreamUnit;
	}

	/**
	 * @return the upStreamValue
	 */
	public String getUpStreamValue() {
		return upStreamValue;
	}

	/**
	 * @param upStreamValue the upStreamValue to set
	 */
	public void setUpStreamValue(String upStreamValue) {
		this.upStreamValue = upStreamValue;
	}

	/**
	 * @return the upStreamUnit
	 */
	public String getUpStreamUnit() {
		return upStreamUnit;
	}

	/**
	 * @param upStreamUnit the upStreamUnit to set
	 */
	public void setUpStreamUnit(String upStreamUnit) {
		this.upStreamUnit = upStreamUnit;
	}

	public Boolean getFlActive() {
		return flActive;
	}

	public void setFlActive(Boolean flActive) {
		this.flActive = flActive;
	}

	// public Set<ScheduledProtocol> getScheduledProtocols() {
	public Collection<ScheduledProtocol> getScheduledProtocols() {
		if (null == scheduledProtocols)
			scheduledProtocols = new HashSet<ScheduledProtocol>();
		return scheduledProtocols;
	}

	public void setScheduledProtocols(Set<ScheduledProtocol> scheduledProtocols) {
		this.scheduledProtocols = scheduledProtocols;
	}

	/**
	 * @return the originalId
	 */
	public String getOriginalId() {
		return originalId;
	}

	/**
	 * @param originalId the originalId to set
	 */
	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

	/**
	 * @return the scheduling
	 */
	public Scheduling getScheduling() {
		if(null == scheduling)
			scheduling = new Scheduling();
		return scheduling;
	}

	/**
	 * @param scheduling the scheduling to set
	 */
	public void setScheduling(Scheduling scheduling) {
		this.scheduling = scheduling;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	
	

}
