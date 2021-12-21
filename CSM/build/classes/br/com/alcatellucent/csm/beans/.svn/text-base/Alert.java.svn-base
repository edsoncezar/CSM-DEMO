package br.com.alcatellucent.csm.beans;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

import br.com.alcatellucent.csm.alde.AlertMessage;
import br.com.alcatellucent.csm.beans.common.CustomObject;

/**
 * @author Fernando
 *
 */
public class Alert extends CustomObject {
	
	private Calendar cal = Calendar.getInstance();
	private String fDate = null;
	private String fTime = null;
	private SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sTime = new SimpleDateFormat("hh:mm:ss");
	
	private Integer 	messageType;
	private Long 		timeStampMessage;
	private String 		rule;
	private String 		sourceIp;
	private String 		destinationIp;
	private Integer 	destinationPort;
	private Integer 	protocol;
	private Integer 	status;
	private Integer 	attackType;
	private String 		csmUser;
	private String 		alde;
	private String      dateMessage;
	private String      timeMessage;
	private Set<Alert> 	alerts;
	
	
	/**
	 * @return the messageType
	 */
	public Integer getMessageType() {
		return messageType;
	}
	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	/**
	 * @return the timeStampMessage
	 */
	public Long getTimeStampMessage() {
		return timeStampMessage;
	}
	/**
	 * @param timeStampMessage the timeStampMessage to set
	 */
	public void setTimeStampMessage(Long timeStampMessage) {
		this.timeStampMessage = timeStampMessage;
	}
	/**
	 * @return the rule
	 */
	public String getRule() {
		return rule;
	}
	/**
	 * @param rule the rule to set
	 */
	public void setRule(String rule) {
		this.rule = rule;
	}
	/**
	 * @return the sourceIp
	 */
	public String getSourceIp() {
		return sourceIp;
	}
	/**
	 * @param sourceIp the sourceIp to set
	 */
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	/**
	 * @return the destinationIp
	 */
	public String getDestinationIp() {
		return destinationIp;
	}
	/**
	 * @param destinationIp the destinationIp to set
	 */
	public void setDestinationIp(String destinationIp) {
		this.destinationIp = destinationIp;
	}
	/**
	 * @return the destinationPort
	 */
	public Integer getDestinationPort() {
		return destinationPort;
	}
	/**
	 * @param destinationPort the destinationPort to set
	 */
	public void setDestinationPort(Integer destinationPort) {
		this.destinationPort = destinationPort;
	}
	/**
	 * @return the protocol
	 */
	public Integer getProtocol() {
		return protocol;
	}
	/**
	 * @param protocol the protocol to set
	 */
	public void setProtocol(Integer protocol) {
		this.protocol = protocol;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return the attackType
	 */
	public Integer getAttackType() {
		return attackType;
	}
	/**
	 * @param attackType the attackType to set
	 */
	public void setAttackType(Integer attackType) {
		this.attackType = attackType;
	}
	/**
	 * @return the csmUser
	 */
	public String getCsmUser() {
		return csmUser;
	}
	/**
	 * @param csmUser the csmUser to set
	 */
	public void setCsmUser(String csmUser) {
		this.csmUser = csmUser;
	}
	/**
	 * @return the alde
	 */
	public String getAlde() {
		return alde;
	}
	/**
	 * @param alde the alde to set
	 */
	public void setAlde(String alde) {
		this.alde = alde;
	}
	public String getDateMessage() {
		return dateMessage;
	}
	public void setDateMessage(String dateMessage) {
		this.dateMessage = dateMessage;
	}
	public String getTimeMessage() {
		return timeMessage;
	}
	public void setTimeMessage(String timeMessage) {
		this.timeMessage = timeMessage;
	}
	
	public String getAttackTypeDescription() {
		return AlertMessage.ATTACK_TYPE_DESC[this.getAttackType()];
	}
	
	public String getFormattedDate() {
		
		cal.setTimeInMillis(this.getTimeStampMessage());
		
		try {
			fDate = sDate.format(cal.getTime());
		} catch (Exception e) {
			fDate = "";
		}
		
		return fDate;
	}
	
	public String getFormattedTime() {

		cal.setTimeInMillis(this.getTimeStampMessage());
		
		try {
			fTime = sTime.format(cal.getTime());
		} catch (Exception e) {
			fTime = "";
		}
		
		return fTime;		
	}
	public Set<Alert> getAlerts() {
		return alerts;
	}
	public void setAlerts(Set<Alert> alerts) {
		this.alerts = alerts;
	}
	
	
	public String getStatusDescription() {
		
		String status = null;
	
		switch (this.getStatus()) {
		case 0:
			status = "Waiting";
			break;
		case 1:
			status = "Closed";
			break;
		case 2:
			status = "Running";
			break;
		case 3:
			status = "Refused";
			break;
		default:
			status = "***";
		}
		
		return status;
	}
}
