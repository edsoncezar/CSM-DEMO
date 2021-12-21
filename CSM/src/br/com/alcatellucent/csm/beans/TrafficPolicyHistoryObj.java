package br.com.alcatellucent.csm.beans;

/**
 * @author Igor Ivanoff Takats
 * 
 * This Java Bean is a custom object for Traffic Policy history Search
 */
public class TrafficPolicyHistoryObj {

    private TrafficPolicyHistory trafficPolicyHistory;

    private Context context;

    private ProcessorPacket processorPacket;

    private TrafficPolicy trafficPolicy;

    private Device device;

    public TrafficPolicy getTrafficPolicy() {
	return trafficPolicy;
    }

    public void setTrafficPolicy(TrafficPolicy trafficPolicy) {
	this.trafficPolicy = trafficPolicy;
    }

    public TrafficPolicyHistory getTrafficPolicyHistory() {
	return trafficPolicyHistory;
    }

    public void setTrafficPolicyHistory(
	    TrafficPolicyHistory trafficPolicyHistory) {
	this.trafficPolicyHistory = trafficPolicyHistory;
    }

    public Context getContext() {
	return context;
    }

    public void setContext(Context context) {
	this.context = context;
    }

    public ProcessorPacket getProcessorPacket() {
	return processorPacket;
    }

    public void setProcessorPacket(ProcessorPacket processorPacket) {
	this.processorPacket = processorPacket;
    }

    public String getStatus() {
	if (this.trafficPolicyHistory == null) {
	    this.trafficPolicyHistory = new TrafficPolicyHistory();
	}
	return "";
	// return
	// (this.trafficPolicyHistory.getStatusApplied()?"Error":"Applied");
    }

    public String getMode() {
	return "";
	// return (this.trafficPolicyHistory.getMode()==1?"Manual":"Scheduled");
    }

    public Device getDevice() {
	return device;
    }

    public void setDevice(Device device) {
	this.device = device;
    }

}
