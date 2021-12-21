package br.com.alcatellucent.csm.beans;

import java.util.Collection;
import java.util.Set;

import br.com.alcatellucent.csm.beans.common.CustomObject;

/**
 * 
 * @author Igor I. Takats - G&P Date: 14/08/2007
 * 
 * Bean for Port Entity
 * 
 */
public class Port extends CustomObject {

	private Integer value;
	private String unit;
	private Integer portNumber;

	// Valores Default
	//private TraficValues traficValues;
	private Set<TraficValues> traficValues;

	private Collection<PortProtocolGroup> portProtocolGroups;

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(Integer portNumber) {
		this.portNumber = portNumber;
	}

	public Collection<PortProtocolGroup> getPortProtocolGroups() {
		return portProtocolGroups;
	}

	public void setPortProtocolGroups(
			Collection<PortProtocolGroup> portProtocolGroups) {
		this.portProtocolGroups = portProtocolGroups;
	}

	public Set<TraficValues> getTraficValues() {
		return traficValues;
	}

	public void setTraficValues(Set<TraficValues> traficValues) {
		this.traficValues = traficValues;
	}


}
