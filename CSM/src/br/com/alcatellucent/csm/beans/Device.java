package br.com.alcatellucent.csm.beans;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import br.com.alcatellucent.csm.beans.common.CustomObject;

public class Device extends CustomObject {
	private String	contextId;
	private Collection<ProcessorPacket>	processorPacketList;
	private DeviceManager deviceManager;
	private String trafficPolicyId;
	private Set<PoolingHistory> poolingHistory;
//	private Integer trafficPolicyRefresh = 1;
//	private Integer aclRefresh = 1;
	
	/**
	 * @return the contextId
	 */
	public String getContextId() {
		return contextId;
	}

	/**
	 * @return the trafficPolicyId
	 */
	public String getTrafficPolicyId() {
		return trafficPolicyId;
	}

	/**
	 * @param trafficPolicyId the trafficPolicyId to set
	 */
	public void setTrafficPolicyId(String trafficPolicyId) {
		this.trafficPolicyId = trafficPolicyId;
	}

	/**
	 * @param contextId
	 *           the contextId to set
	 */
	public void setContextId(String contextId) {
		this.contextId = contextId;
	}

	/**
	 * @return the processorPacket
	 */
	public Collection<ProcessorPacket> getProcessorPacketList() {

		return processorPacketList;
	}

	/**
	 * @param processorPacket
	 *           the processorPacket to set
	 */
	public void setProcessorPacketList(Collection<ProcessorPacket> processorPacket) {
		processorPacketList = processorPacket;
	}

	/**
	 * @return the deviceManager
	 */
	public DeviceManager getDeviceManager() {
		if (null == deviceManager)
			deviceManager = new DeviceManager();
		return deviceManager;
	}

	/**
	 * @param deviceManager
	 *           the deviceManager to set
	 */
	public void setDeviceManager(DeviceManager deviceManager) {
		this.deviceManager = deviceManager;
	}

	/**
	 * @return the poolingHistory
	 */
	public Set<PoolingHistory> getPoolingHistory() {
		if(null == poolingHistory){
			poolingHistory =  new HashSet<PoolingHistory>();
		}
		return poolingHistory;
	}

	/**
	 * @param poolingHistory the poolingHistory to set
	 */
	public void setPoolingHistory(Set<PoolingHistory> poolingHistory) {
		this.poolingHistory = poolingHistory;
	}

//	/**
//	 * @return the trafficPolicyRefresh
//	 */
//	public Integer getTrafficPolicyRefresh() {
//		return trafficPolicyRefresh;
//	}
//
//	/**
//	 * @param trafficPolicyRefresh the trafficPolicyRefresh to set
//	 */
//	public void setTrafficPolicyRefresh(Integer trafficPolicyRefresh) {
//		this.trafficPolicyRefresh = trafficPolicyRefresh;
//	}
//
//	/**
//	 * @return the aclRefresh
//	 */
//	public Integer getAclRefresh() {
//		return aclRefresh;
//	}
//
//	/**
//	 * @param aclRefresh the aclRefresh to set
//	 */
//	public void setAclRefresh(Integer aclRefresh) {
//		this.aclRefresh = aclRefresh;
//	}
	
	
}
