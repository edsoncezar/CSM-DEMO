package br.com.alcatellucent.csm.quartz.beans;

import java.util.Set;

public class CronTriggers {
	private String triggerName;
	private String triggerGroup;
	private String cronExpression;
	private String timeZoneId;
	private Set<Triggers> firedTriggersList;
	private JobDetails jobDetails;
		
	
	/**
	 * @return the firedTriggersList
	 */
	public Set<Triggers> getFiredTriggersList() {
		return firedTriggersList;
	}
	/**
	 * @param firedTriggersList the firedTriggersList to set
	 */
	public void setFiredTriggersList(Set<Triggers> firedTriggersList) {
		this.firedTriggersList = firedTriggersList;
	}
	/**
	 * @return the triggerName
	 */
	public String getTriggerName() {
		return triggerName;
	}
	/**
	 * @param triggerName the triggerName to set
	 */
	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	/**
	 * @return the tiggerGroup
	 */
	public String getTriggerGroup() {
		return triggerGroup;
	}
	/**
	 * @param tiggerGroup the tiggerGroup to set
	 */
	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}
	/**
	 * @return the cronExpression
	 */
	public String getCronExpression() {
		return cronExpression;
	}
	/**
	 * @param cronExpression the cronExpression to set
	 */
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	/**
	 * @return the timeZoneId
	 */
	public String getTimeZoneId() {
		return timeZoneId;
	}
	/**
	 * @param timeZoneId the timeZoneId to set
	 */
	public void setTimeZoneId(String timeZoneId) {
		this.timeZoneId = timeZoneId;
	}
	
	public JobDetails getJobDetails() {
		return jobDetails;
	}
	public void setJobDetails(JobDetails jobDetails) {
		this.jobDetails = jobDetails;
	}

}
