package br.com.alcatellucent.csm.beans;

import br.com.alcatellucent.csm.beans.common.CustomObject;

public class StaticIP extends CustomObject {

	private String IP;
	private String mask;
	private String trafficPolicyId;
	private String contextId;
	
	/**
	 * @return the iP
	 */
	public String getIP() {
		return IP;
	}
	/**
	 * @param ip the iP to set
	 */
	public void setIP(String ip) {
		IP = ip;
	}
	/**
	 * @return the mask
	 */
	public String getMask() {
		return mask;
	}
	/**
	 * @param mask the mask to set
	 */
	public void setMask(String mask) {
		this.mask = mask;
	}
   
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
	 * @return the contextId
	 */
	public String getContextId() {
		return contextId;
	}
	/**
	 * @param contextId the contextId to set
	 */
	public void setContextId(String contextId) {
		this.contextId = contextId;
	}
	
	
	
	
}
