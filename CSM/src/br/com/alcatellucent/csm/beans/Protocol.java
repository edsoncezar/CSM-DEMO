package br.com.alcatellucent.csm.beans;

import java.util.Collection;

import br.com.alcatellucent.csm.beans.common.CustomObject;

/**
 * 
 * @author Igor I. Takats - G&P Date: 14/08/2007
 * 
 * Bean for Protocol Entity
 * 
 */
public class Protocol extends CustomObject {

    public static final String UNIT_MB = "Mb";
    public static final String UNIT_KB = "Kb";

    private Integer internalNumber;
    private Boolean aclUsed;
    private Boolean isRealTime;

    private Collection<PortProtocolGroup> portProtocolGroups;

    
    private Integer flowsValues = 0;
    private String downStreamValue = "0";
    private String downStreamUnit = UNIT_MB; // caso nada seja setado o valor vai ser default
    private String upStreamValue = "0";
    private String upStreamUnit = UNIT_MB;// caso nada seja setado o valor vai ser default
    
    private String control = "0";
    private String controlUnit = UNIT_MB; 
    private Integer priority = 0;
    
    
    public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * @return the flowsValues
	 */
	public Integer getFlowsValues() {
		return flowsValues;
	}

	/**
	 * @param flowsValues the flowsValues to set
	 */
	public void setFlowsValues(Integer flowsValues) {
		this.flowsValues = flowsValues;
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

	public Collection<PortProtocolGroup> getPortProtocolGroups() {
	return portProtocolGroups;
    }

    public void setPortProtocolGroups(
	    Collection<PortProtocolGroup> portProtocolGroups) {
	this.portProtocolGroups = portProtocolGroups;
    }


    /**
     * @return the internalNumber
     */
    public Integer getInternalNumber() {
	return internalNumber;
    }

    /**
     * @return the aclUsed
     */
    public Boolean getAclUsed() {
    	if(null == aclUsed){
    		aclUsed = new Boolean("false");
    	}
	return aclUsed;
    }

    /**
     * @param aclUsed
     *                the aclUsed to set
     */
    public void setAclUsed(Boolean aclUsed) {
	this.aclUsed = aclUsed;
    }

    /**
     * @return the isRealTime
     */
    public Boolean getIsRealTime() {
	return isRealTime;
    }

    /**
     * @param isRealTime
     *                the isRealTime to set
     */
    public void setIsRealTime(Boolean isRealTime) {
	this.isRealTime = isRealTime;
    }

    /**
     * @param internalNumber
     *                the internalNumber to set
     */
    public void setInternalNumber(Integer internalNumber) {
	this.internalNumber = internalNumber;
    }

	public String getControlUnit() {
		return controlUnit;
	}

	public void setControlUnit(String controlUnit) {
		this.controlUnit = controlUnit;
	}

}
