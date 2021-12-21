package br.com.alcatellucent.csm.beans;

import br.com.alcatellucent.csm.beans.common.CustomObject;

/**
 * @author Edson
 * 
 */
public class Acl extends CustomObject {

	private String contextId;
	private String sourceIp;
	private String sourceIpMask;
	private Integer sourcePort;
	private String sourcePortMask;
	private String destIp;
	private String destIpMask;
	private Integer _destPort;
	private String _destPortMask;
	private String protocol;
	private int protocolInternalNumber;
	private String protocolMask;
	private Integer action;
	private String actionMask = "0xff";
	private Integer threshold;
	private String thresholdMask = "0xffff";
	private Integer priority;
	private Integer status;
	private Integer timeToLive;
	private Boolean isAldeAcl;
	private String isAldeAclForm;
	private String alertId;

	private String[] aclId;

	/**
	 * @return the timeToLive
	 */
	public Integer getTimeToLive() {
		return timeToLive;
	}

	/**
	 * @param timeToLive
	 *           the timeToLive to set
	 */
	public void setTimeToLive(Integer timeToLive) {
		this.timeToLive = timeToLive;
	}

	/**
	 * @return the contextId
	 */
	public String getContextId() {
		return contextId;
	}

	/**
	 * @param contextId
	 *           the contextId to set
	 */
	public void setContextId(String contextId) {
		this.contextId = contextId;
	}

	/**
	 * @return the sourceIp
	 */
	public String getSourceIp() {
		return sourceIp;
	}

	/**
	 * @param sourceIp
	 *           the sourceIp to set
	 */
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}

	/**
	 * @return the sourceIpMask
	 */
	public String getSourceIpMask() {
		return sourceIpMask;
	}

	/**
	 * @param sourceIpMask
	 *           the sourceIpMask to set
	 */
	public void setSourceIpMask(String sourceIpMask) {
		this.sourceIpMask = sourceIpMask;
	}

	/**
	 * @return the sourcePort
	 */
	public Integer getSourcePort() {
		return sourcePort;
	}

	/**
	 * @param sourcePort
	 *           the sourcePort to set
	 */
	public void setSourcePort(Integer sourcePort) {
		this.sourcePort = sourcePort;
		setSourcePortMask();
	}

	/**
	 * @return the sourcePortMask
	 */
	public String getSourcePortMask() {
		return sourcePortMask;
	}

	/**
	 * @param sourcePortMask
	 *           the sourcePortMask to set
	 */
	public void setSourcePortMask() {
		if (null == this.sourcePort || this.sourcePort == 0) {
			this.sourcePortMask = "0x0000";
		} else {
			this.sourcePortMask = "0xffff";
		}
	}

	/**
	 * @return the destIp
	 */
	public String getDestIp() {
		return destIp;
	}

	/**
	 * @param destIp
	 *           the destIp to set
	 */
	public void setDestIp(String destIp) {
		this.destIp = destIp;
	}

	/**
	 * @return the destIpMask
	 */
	public String getDestIpMask() {
		return destIpMask;
	}

	/**
	 * @param destIpMask
	 *           the destIpMask to set
	 */
	public void setDestIpMask(String destIpMask) {
		this.destIpMask = destIpMask;
	}

	/**
	 * @return the destPort
	 */
	public Integer getDestPort() {
		return _destPort;
	}

	/**
	 * @param destPort
	 *           the destPort to set
	 */
	public void setDestPort(Integer destPort) {
		this._destPort = destPort;
		setDestPortMask();
	}

	/**
	 * @return the destPortMask
	 */
	public String getDestPortMask() {
		return _destPortMask;
	}

	/**
	 * @param destPortMask
	 *           the destPortMask to set
	 */
	public void setDestPortMask() {
		if (null == this._destPort || this._destPort == 0) {
			this._destPortMask = "0x0000";
		} else {
			this._destPortMask = "0xffff";
		}
	}

	/**
	 * @return the protocol
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol
	 *           the protocol to set
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
		setProtocolMask();
	}

	/**
	 * @return the protocolMask
	 */
	public String getProtocolMask() {
		return protocolMask;
	}

	/**
	 * @param protocolMask
	 *           the protocolMask to set
	 */
	public void setProtocolMask() {
		if (null != protocol && protocol.equals("ANY")) {
			this.protocolMask = "0x00";
		} else {
			this.protocolMask = "0xff";
		}
	}

	/**
	 * @return the action
	 */
	public Integer getAction() {
		return action;
	}

	/**
	 * @param action
	 *           the action to set
	 */
	public void setAction(Integer action) {
		this.action = action;
	}

	/**
	 * @return the actionMask
	 */
	public String getActionMask() {
		return actionMask;
	}

	/**
	 * @return the threshold
	 */
	public Integer getThreshold() {
		return threshold;
	}

	/**
	 * @param threshold
	 *           the threshold to set
	 */
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	/**
	 * @return the thresholdMask
	 */
	public String getThresholdMask() {
		return thresholdMask;
	}

	/**
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *           the priority to set
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *           the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the aclId
	 */
	public String[] getAclId() {
		return aclId;
	}

	/**
	 * @param aclId
	 *           the aclId to set
	 */
	public void setAclId(String[] aclId) {
		this.aclId = aclId;
	}

	/**
	 * @return the protocolInternalNumber
	 */
	public int getProtocolInternalNumber() {
		return protocolInternalNumber;
	}

	/**
	 * @param protocolInternalNumber
	 *           the protocolInternalNumber to set
	 */
	public void setProtocolInternalNumber(int protocolInternalNumber) {
		this.protocolInternalNumber = protocolInternalNumber;
	}

	public String getAlertId() {
		return alertId;
	}

	public void setAlertId(String alertId) {
		this.alertId = alertId;
	}

	public Boolean getIsAldeAcl() {
		return isAldeAcl;
	}

	public void setIsAldeAcl(Boolean isAldeAcl) {
		this.isAldeAcl = isAldeAcl;
	}

	public String getIsAldeAclForm() {
		return isAldeAclForm;
	}

	public void setIsAldeAclForm(String isAldeAclForm) {
		this.isAldeAclForm = isAldeAclForm;
	}
}
