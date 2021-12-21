package br.com.alcatellucent.csm.beans;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import br.com.alcatellucent.csm.beans.common.CustomObject;
 
public class PortProtocolGroup extends CustomObject {

	private Boolean flActive;
	private Collection<Protocol> protocols;
	private Collection<Port> ports;
	private Integer flowsValue;
	private String downStreamValue;
	private String downStreamUnit;
	private String upStreamValue;
	private String upStreamUnit;


	public Boolean getFlActive() {
		
		if (null == this.flActive) {
			this.setFlActive(true);
		}
		
		return flActive;
	}

	public void setFlActive(Boolean flActive) {
		this.flActive = flActive;
	}

	// public Set<Protocol> getProtocols() {
	public Collection<Protocol> getProtocols() {
		if (null == protocols)
			protocols = new HashSet<Protocol>();
		return protocols;
	}

	public void setProtocols(Set<Protocol> protocols) {
		this.protocols = protocols;
	}

	public Collection<Port> getPorts() {
		if (null == ports)
			ports = new HashSet<Port>();
		return ports;
	}

	public void setPorts(Set<Port> ports) {
		this.ports = ports;
	}

	public void clear() {
		super.clear();
		//setFlActive(Boolean.TRUE);		
	}

	/**
	 * @return the flowsValue
	 */
	public Integer getFlowsValue() {
		return flowsValue;
	}

	/**
	 * @param flowsValue the flowsValue to set
	 */
	public void setFlowsValue(Integer flowsValue) {
		this.flowsValue = flowsValue;
	}

	/**
	 * @return the downStreamValue
	 */
	public String getDownStreamValue() {
		return downStreamValue;
	}

	/**
	 * @param downStreamValue the downStreamValue to set
	 */
	public void setDownStreamValue(String downStreamValue) {
		this.downStreamValue = downStreamValue;
	}

	/**
	 * @return the downStreamUnit
	 */
	public String getDownStreamUnit() {
		return downStreamUnit;
	}

	/**
	 * @param downStreamUnit the downStreamUnit to set
	 */
	public void setDownStreamUnit(String downStreamUnit) {
		this.downStreamUnit = downStreamUnit;
	}

	/**
	 * @return the upStreamValue
	 */
	public String getUpStreamValue() {
		return upStreamValue;
	}

	/**
	 * @param upStreamValue the upStreamValue to set
	 */
	public void setUpStreamValue(String upStreamValue) {
		this.upStreamValue = upStreamValue;
	}

	/**
	 * @return the upStreamUnit
	 */
	public String getUpStreamUnit() {
		return upStreamUnit;
	}

	/**
	 * @param upStreamUnit the upStreamUnit to set
	 */
	public void setUpStreamUnit(String upStreamUnit) {
		this.upStreamUnit = upStreamUnit;
	}
}
