package br.com.alcatellucent.csm.beans;

import br.com.alcatellucent.csm.beans.common.CustomObject;

public class ProcessorPacket extends CustomObject {

	private int _number = 0;
	private Device device;
	private String trafficPolicyId;
	private String aldeId; // Incluido por Igor Takats 2007-set-05
	private Integer sampleThreshold = 2000;

	public String getAldeId() {
		return aldeId;
	}

	public void setAldeId(String aldeId) {
		this.aldeId = aldeId;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return _number;
	}

	/**
	 * @param number
	 *           the number to set
	 */
	public void setNumber(int number) {
		this._number = number;
	}

	/**
	 * @return the trafficPolicyId
	 */
	public String getTrafficPolicyId() {
		return trafficPolicyId;
	}

	/**
	 * @param trafficPolicyId
	 *           the trafficPolicyId to set
	 */
	public void setTrafficPolicyId(String trafficPolicyId) {
		this.trafficPolicyId = trafficPolicyId;
	}

	/**
	 * @return the device
	 */
	public Device getDevice() {
		if (null == device)
			device = new Device();
		return device;
	}

	/**
	 * @param device
	 *           the device to set
	 */
	public void setDevice(Device device) {
		this.device = device;
	}

	public Integer getSampleThreshold() {
		return sampleThreshold;
	}

	/**
	 * @param sampleThreshold
	 *           the sampleThreshold to set
	 */
	public void setSampleThreshold(Integer sampleThreshold) {
		this.sampleThreshold = sampleThreshold;
	}

}
