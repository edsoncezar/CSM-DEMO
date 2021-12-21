package br.com.alcatellucent.csm.beans;

/**
 * @author Igor
 * 
 * Classe que representa a Entidade TraficValues a qual possue os valores
 * default a serem enviados aos dispositivos
 * 
 */
public class TraficValues {

    private String id;
    private Integer flowsValues = 0;
    private String downStreamValue = "0";
    private String downStreamUnit;
    private String upStreamValue = "0";
    private String upStreamUnit;
    private Integer outputVlanValue = 0;
    private String outputVlanUnit;
    private Integer outputmPlsValue = 0;
    private String outputmPlsUnit;

    private Port port;
    private Protocol protocol;

    public Integer getFlowsValues() {
	return flowsValues;
    }

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
     * @param downStreamValue
     *                the downStreamValue to set
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
     * @param downStreamUnit
     *                the downStreamUnit to set
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
     * @param upStreamValue
     *                the upStreamValue to set
     */
    public void setUpStreamValue(String upStreamValue) {
	this.upStreamValue = upStreamValue;
    }

    public String getUpStreamUnit() {
	return upStreamUnit;
    }

    public void setUpStreamUnit(String upStreamUnit) {
	this.upStreamUnit = upStreamUnit;
    }

    public Integer getOutputVlanValue() {
	return outputVlanValue;
    }

    public void setOutputVlanValue(Integer outputVlanValue) {
	this.outputVlanValue = outputVlanValue;
    }

    public String getOutputVlanUnit() {
	return outputVlanUnit;
    }

    public void setOutputVlanUnit(String outputVlanUnit) {
	this.outputVlanUnit = outputVlanUnit;
    }

    public Integer getOutputmPlsValue() {
	return outputmPlsValue;
    }

    public void setOutputmPlsValue(Integer outputmPlsValue) {
	this.outputmPlsValue = outputmPlsValue;
    }

    public String getOutputmPlsUnit() {
	return outputmPlsUnit;
    }

    public void setOutputmPlsUnit(String outputmPlsUnit) {
	this.outputmPlsUnit = outputmPlsUnit;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public Port getPort() {
	return port;
    }

    public void setPort(Port port) {
	this.port = port;
    }

    public Protocol getProtocol() {
	return protocol;
    }

    public void setProtocol(Protocol protocol) {
	this.protocol = protocol;
    }

}
