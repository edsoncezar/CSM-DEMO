package br.com.alcatellucent.csm.beans;

import br.com.alcatellucent.csm.beans.common.CustomObject;

/**
 * 
 * @author Igor I. Takats - G&P Date: 14/08/2007
 * 
 * Bean for Port Entity
 * 
 */
public class SchedulerPort extends CustomObject {

	private Integer value[];
	private String unit[];
	private Integer portNumber[];
	public Integer[] getValue() {
		return value;
	}
	public void setValue(Integer[] value) {
		this.value = value;
	}
	public String[] getUnit() {
		return unit;
	}
	public void setUnit(String[] unit) {
		this.unit = unit;
	}
	public Integer[] getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(Integer[] portNumber) {
		this.portNumber = portNumber;
	}

}
