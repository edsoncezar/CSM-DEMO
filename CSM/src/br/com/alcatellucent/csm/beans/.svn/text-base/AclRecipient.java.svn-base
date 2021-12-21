package br.com.alcatellucent.csm.beans;

import java.util.Date;

import br.com.alcatellucent.csm.beans.common.CustomObject;

public class AclRecipient extends CustomObject {

	private String dppmId;
	private String userId;
	private Integer aclNumber;
	private Date dateApplied;
	private boolean applied = false;
	private Acl acl;
	/**
	 * @return the dppmId
	 */
	public String getDppmId() {
		return dppmId;
	}

	/**
	 * @param dppmId
	 *           the dppmId to set
	 */
	public void setDppmId(String dppmId) {
		this.dppmId = dppmId;
	}


	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *           the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the aclNumber
	 */
	public Integer getAclNumber() {
		return aclNumber;
	}

	/**
	 * @param aclNumber
	 *           the aclNumber to set
	 */
	public void setAclNumber(Integer aclNumber) {
		this.aclNumber = aclNumber;
	}

	/**
	 * @return the dateApplied
	 */
	public Date getDateApplied() {
		return dateApplied;
	}

	/**
	 * @param dateApplied
	 *           the dateApplied to set
	 */
	public void setDateApplied(Date dateApplied) {
		this.dateApplied = dateApplied;
	}

	/**
	 * @return the applied
	 */
	public boolean isApplied() {
		return applied;
	}

	/**
	 * @param applied
	 *           the applied to set
	 */
	public void setApplied(boolean applied) {
		this.applied = applied;
	}

	/**
	 * @return the acl
	 */
	public Acl getAcl() {
		if(null == acl)
			acl = new Acl();
		return acl;
	}

	/**
	 * @param acl the acl to set
	 */
	public void setAcl(Acl acl) {
		this.acl = acl;
	}

}
