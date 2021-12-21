package br.com.alcatellucent.csm.beans;

import java.io.Serializable;
import java.util.Date;

import br.com.alcatellucent.csm.beans.common.CustomObject;

/**
 * 
 * @author Fernando Caruso Olívio & Edson Moreira Cezar
 * @date 08/01/2007
 * @version 1.0
 * 
 * @description : This class represents users on csm.
 */
public class User extends CustomObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3157587976925154408L;
	
	private String contextId;
	private String userName;
	private String userProfileId;
	private Date lastLogin;
	private Date createdAt;
	private String createdBy;
	private Boolean isActive = false;

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
	 * @return the userProfileId
	 */
	public String getUserProfileId() {
		return userProfileId;
	}

	/**
	 * @param userProfileId
	 *           the userProfileId to set
	 */
	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}

	/**
	 * @return the lastLogin
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin
	 *           the lastLogin to set
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt
	 *           the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *           the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the isActive
	 */
	public Boolean getIsActive() {
		if (null == isActive) {
			isActive = new Boolean(false);
		}
		return isActive;
	}

	/**
	 * @param isActive
	 *           the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}