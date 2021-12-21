package br.com.alcatellucent.csm.beans;

import java.io.Serializable;
import java.sql.Date;

import br.com.alcatellucent.csm.bo.UserAccessControlBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;

/**
 * @author Fernando Caruso Olívio & Edson Moreira Cezar
 * 
 */
public class UserAccessControl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -469595080849513259L;
	
	private String id;
	private String userPassword;
	private java.util.Date lastChangeDate;
	private Date expirationDate;
	private Boolean flChangeNextLogin;
	private User user;
	private java.util.Date dateTimeBlock;
	private Integer wrongPassCount;

	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword
	 *           the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the lastChangeDate
	 */
	public java.util.Date getLastChangeDate() {
		return lastChangeDate;
	}

	/**
	 * @param lastChangeDate
	 *           the lastChangeDate to set
	 */
	public void setLastChangeDate(java.util.Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	/**
	 * @return the expirationDate
	 */
	public java.sql.Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * @param expirationDate
	 *           the expirationDate to set
	 */
	public void setExpirationDate(java.sql.Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * @return the flChangeNextLogin
	 */
	public Boolean getFlChangeNextLogin() {
		return flChangeNextLogin;
	}

	/**
	 * @param flChangeNextLogin
	 *           the flChangeNextLogin to set
	 */
	public void setFlChangeNextLogin(Boolean flChangeNextLogin) {
		this.flChangeNextLogin = flChangeNextLogin;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *           the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *           the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public void clear() {
		this.userPassword = "";
	}

	public void setDefaultPassword() throws ExceptionSys {
		setUserPassword(UserAccessControlBO.getDefaultPasswd(getUser().getUserName()));
	}

	public Integer getWrongPassCount() {
		return wrongPassCount;
	}

	public void setWrongPassCount(Integer wrongPassCount) {
		this.wrongPassCount = wrongPassCount;
	}

	public java.util.Date getDateTimeBlock() {
		return dateTimeBlock;
	}

	@SuppressWarnings("deprecation")
	public void setDateTimeBlock(Object dateTimeBlock) {

		if ((null == dateTimeBlock) || (!(dateTimeBlock instanceof java.util.Date))) {
			this.dateTimeBlock = new java.util.Date();
		} else {
			this.dateTimeBlock = (java.util.Date) dateTimeBlock;
		}

	}

	// @SuppressWarnings("deprecation")
	// public void setDateTimeBlock(java.util.Date dateTimeBlock) {
	//		
	// if (null == dateTimeBlock) {
	// dateTimeBlock = new java.util.Date();
	// }
	//		
	// this.dateTimeBlock = dateTimeBlock;
	// }
}
