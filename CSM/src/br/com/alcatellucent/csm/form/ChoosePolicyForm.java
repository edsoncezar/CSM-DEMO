package br.com.alcatellucent.csm.form;

import br.com.alcatellucent.csm.beans.Context;
import br.com.alcatellucent.csm.beans.Device;
import br.com.alcatellucent.csm.beans.ProcessorPacket;
import br.com.alcatellucent.csm.form.common.CommonForm;

public class ChoosePolicyForm extends CommonForm {

    private static final long serialVersionUID = -7870627613343154404L;
    
    private ProcessorPacket processorPacket = null;
    private String scheduleId = null;
    private Device device = null;
    private Context context = null;
    
    // Igor 2007-09-26 - Receptores de valores de grupos/Protocolos alterados em 'choosePolicyProtocol.jsp'
    private String protocolValues = null;
    private String groupsValues = null;

    /**
     * @return the device
     */
    public Device getDevice() {
	if (null == device) {
	    device = new Device();
	}
	return device;
    }

    /**
     * @param device
     *                the device to set
     */
    public void setDevice(Device device) {
	this.device = device;
    }

    /**
     * @return the context
     */
    public Context getContext() {
	if (null == context) {
	    context = new Context();
	}
	return context;
    }

    /**
     * @param context
     *                the context to set
     */
    public void setContext(Context context) {
	this.context = context;
    }

    /**
     * @return the processorPacket
     */
    public ProcessorPacket getProcessorPacket() {
	if (null == processorPacket) {
	    processorPacket = new ProcessorPacket();
	}
	return processorPacket;
    }

    /**
     * @param processorPacket
     *                the processorPacket to set
     */
    public void setProcessorPacket(ProcessorPacket processorPacket) {
	this.processorPacket = processorPacket;
    }

    /**
     * @return the scheduleId
     */
    public String getScheduleId() {
	return scheduleId;
    }

    /**
     * @param scheduleId
     *                the scheduleId to set
     */
    public void setScheduleId(String scheduleId) {
	this.scheduleId = scheduleId;
    }

	public String getProtocolValues() {
		return protocolValues;
	}

	public void setProtocolValues(String protocolValues) {
		this.protocolValues = protocolValues;
	}

	public String getGroupsValues() {
		return groupsValues;
	}

	public void setGroupValues(String groupsValues) {
		this.groupsValues = groupsValues;
	}


}
