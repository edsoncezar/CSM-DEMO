package br.com.alcatellucent.csm.beans;

import java.util.Calendar;

import br.com.alcatellucent.csm.beans.common.CustomObject;


public class TrafficPolicyHistory extends CustomObject {
	
	public static final int MODE_MANUAL = 0;
	
	public static final int MODE_SCHEDULED = 1;
	
	private String 			trafficPolicyId;
	private String 			dppmId;
	private String 			csmUserId;
	private Calendar 		date;
	private Boolean 		statusApplied;
	private Integer 		mode;
	private String			details;
	
	/**
	 * @return the trafficPolicyId
	 */
	public String getTrafficPolicyId() {
		return trafficPolicyId;
	}
	/**
	 * @param trafficPolicyId the trafficPolicyId to set
	 */
	public void setTrafficPolicyId(String trafficPolicyId) {
		this.trafficPolicyId = trafficPolicyId;
	}
	/**
	 * @return the dppmId
	 */
	public String getDppmId() {
		return dppmId;
	}
	/**
	 * @param dppmId the dppmId to set
	 */
	public void setDppmId(String dppmId) {
		this.dppmId = dppmId;
	}
	/**
	 * @return the csmUserId
	 */
	public String getCsmUserId() {
		return csmUserId;
	}
	/**
	 * @param csmUserId the csmUserId to set
	 */
	public void setCsmUserId(String csmUserId) {
		this.csmUserId = csmUserId;
	}
	/**
	 * @return the date
	 */
	public Calendar getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Calendar date) {
		this.date = date;
	}
	/**
	 * @return the statusApplied
	 */
	public Boolean getStatusApplied() {
		return statusApplied;
	}
	/**
	 * @param statusApplied the statusApplied to set
	 */
	public void setStatusApplied(Boolean statusApplied) {
		this.statusApplied = statusApplied;
	}
	/**
	 * @return the mode
	 */
	public Integer getMode() {
		return mode;
	}
	/**
	 * @param mode the mode to set
	 */
	public void setMode(Integer mode) {
		this.mode = mode;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}
	
	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}
	
}
