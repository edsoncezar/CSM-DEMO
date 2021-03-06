/**
 * 
 */
package br.com.alcatellucent.csm.beans;

import java.util.Date;

/**
 * @author Edson Moreira C?zar
 * @Date   04/12/2007
 * @Describe This class represents users loggeds in the System
 *
 */
public class UserLogged {
	
	private String name;
	
	private Date   lastLogin;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lastLogin
	 */
	public Date getLastLogin() {
		return lastLogin;
	}

	/**
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
}
