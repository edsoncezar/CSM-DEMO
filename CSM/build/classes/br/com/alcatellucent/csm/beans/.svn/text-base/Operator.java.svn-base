/**
 * 
 */
package br.com.alcatellucent.csm.beans;

import java.util.Date;

import br.com.alcatellucent.csm.beans.common.CustomObject;
import br.com.alcatellucent.csm.utils.DateUtil;

/**
 * 
 *@author Edson Moreira Cezar
 *@date   08/18/2007 
 *@version 1.0
 *
 */
public class Operator extends CustomObject {
	
	private String email;
	private String mobile;
	private java.util.Date lastAlert;
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the lastAlert
	 */
	public Date getLastAlert() {
		return lastAlert;
	}
	/**
	 * @param lastAlert the lastAlert to set
	 */
	public void setLastAlert(Date lastAlert) {
		
		this.lastAlert = lastAlert;
	}
	 
	public void clear(){
		super.clear();
		this.email="";
		this.mobile="";
		this.lastAlert= DateUtil.getCurrentSQLDate();
	}
	
}
