package br.com.alcatellucent.csm.beans;

import br.com.alcatellucent.csm.beans.common.CustomObject;


public class Resolver extends CustomObject {
	public static final String ANY_MASK = "255.255.255.255";
	private Domain domain;
	private String ipAddress;
	private Boolean isSourceAddress;
	/**
	 * @return the domain
	 */
	public Domain getDomain() {
		if(null == domain)
			domain =  new Domain();
		return domain;
	}
	/**
	 * @param domain the domain to set
	 */
	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}
	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	/**
	 * @return the isSourceAddress
	 */
	public Boolean getIsSourceAddress() {
		if(null == isSourceAddress)
			isSourceAddress = new Boolean(false);
		return isSourceAddress;
	}
	/**
	 * @param isSourceAddress the isSourceAddress to set
	 */
	public void setIsSourceAddress(Boolean isSourceAddress) {
		this.isSourceAddress = isSourceAddress;
	}
	
	
}
