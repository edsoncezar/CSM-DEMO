package br.com.alcatellucent.csm.beans;

import java.util.Set;

import br.com.alcatellucent.csm.beans.common.CustomObject;

/**
 * 
 * @author Edson Moreira Cezar
 * @date 08/09/2007
 * @version 1.0
 * 
 * @description : This class represents AlDE.
 */
public class Alde extends CustomObject {

	public static final String DEFAULT_USERNAME = "aldeadmin";

	public static final String DEFAULT_PASSWORD = "aldeAdM!n1q";

	private String contextId;
	private String host;
	private String userName;
	private String userPass;
	private Set<AldeConfiguration> aldeConfig;
	private String adressIp;
	private Device device;
	private Boolean master = false;
	private Set<Alert> alerts;

	/**
	 * @return the device
	 */
	public Device getDevice() {
		return device;
	}

	/**
	 * @param device
	 *           the device to set
	 */
	public void setDevice(Device device) {
		this.device = device;
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
	 * @return the aldeConfig
	 */
	public Set<AldeConfiguration> getAldeConfig() {
		return aldeConfig;
	}

	/**
	 * @param aldeConfig
	 *           the aldeConfig to set
	 */
	public void setAldeConfig(Set<AldeConfiguration> aldeConfig) {
		this.aldeConfig = aldeConfig;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *           the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *           the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userPass
	 */
	public String getUserPass() {
		return userPass;
	}

	/**
	 * @param userPass
	 *           the userPass to set
	 */
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	/**
	 * @return the adressIp
	 */
	public String getAdressIp() {
		return adressIp;
	}

	/**
	 * @param adressIp
	 *           the adressIp to set
	 */
	public void setAdressIp(String adressIp) {
		this.adressIp = adressIp;
	}

	/**
	 * @return the alerts
	 */
	public Set<Alert> getAlerts() {
		return alerts;
	}

	/**
	 * @param alerts
	 *           the alerts to set
	 */
	public void setAlerts(Set<Alert> alerts) {
		this.alerts = alerts;
	}

	public void clear() {
		this.adressIp = "";
		this.host = "";
		this.userName = "";
		this.userPass = "";
		if (null != this.aldeConfig)
			this.aldeConfig.clear();
		super.clear();
	}

	/**
	 * @return the isMaster
	 */
	public Boolean getMaster() {
		return master;
	}

	/**
	 * @param isMaster
	 *           the isMaster to set
	 */
	public void setMaster(Boolean isMaster) {
		this.master = isMaster;
	}

}
