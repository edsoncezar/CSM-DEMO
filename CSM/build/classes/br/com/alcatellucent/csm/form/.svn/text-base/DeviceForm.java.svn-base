package br.com.alcatellucent.csm.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.beans.Device;
import br.com.alcatellucent.csm.beans.DeviceManager;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

/**
 * @author Fernando Caruso Olívio & José Celeste Soares
 * 
 */
public class DeviceForm extends CommonForm {

    private static final long serialVersionUID = 8477505264151170993L;

    public Device device = null;

    public DeviceManager deviceManager = null;

    /**
     * @return the device
     */
    public Device getDevice() {
	if (null == device) {
	    this.device = new Device();
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
     * @return the deviceManager
     */
    public DeviceManager getDeviceManager() {
	if (null == deviceManager)
	    deviceManager = new DeviceManager();

	return deviceManager;
    }

    /**
     * @param deviceManager
     *                the deviceManager to set
     */
    public void setDeviceManager(DeviceManager deviceManager) {
	this.deviceManager = deviceManager;
    }

    @Override
    public void reset(ActionMapping map, HttpServletRequest req) {
	if (((String) req.getParameter("action")).equals("initial")) {
	    device = new Device();
	    deviceManager = new DeviceManager();
	}

    }

    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {

	ActionErrors errors = new ActionErrors();
	boolean emptyForm = true;

	// Verifica validade das informações digitadas conforme tipo do dado.
	if (((String) request.getParameter("action")).equals("save")) {
	    if (this.getDevice().getName() != null
		    && this.getDevice().getName() != "") {
		emptyForm = false;
		if (BasicValueCheck.isEmptyString(this.getDevice().getName())) {
		    errors.add("deviceForm.name", new ActionMessage(
			    "erro.deviceForm.Name"));
		}
	    }

	    if (this.getDeviceManager().getName() != null
		    && this.getDeviceManager().getName() != "") {
		emptyForm = false;
		if (BasicValueCheck.isEmptyString(this.getDeviceManager()
			.getName())) {
		    errors.add("deviceForm.managerName", new ActionMessage(
			    "erro.deviceForm.ManagerName"));
		}
	    }

	    if (this.getDeviceManager().getHost() != null
		    && this.getDeviceManager().getHost() != "") {
		emptyForm = false;
		if (BasicValueCheck.isEmptyString(this.getDeviceManager()
			.getHost())) {
		    errors.add("deviceForm.host", new ActionMessage(
			    "erro.deviceForm.Host"));
		} else if (BasicValueCheck.isIP(this.getDeviceManager()
			.getHost()) == false) {
		    errors.add("deviceForm.host", new ActionMessage(
			    "erro.deviceForm.HostCheck"));
		}
	    }

	    if (this.getDeviceManager().getPoolPeriod() != null) {
		emptyForm = false;
		if (!BasicValueCheck.isNumericNoSignal(this.getDeviceManager()
			.getPoolPeriod().toString())) {
		    errors.add("deviceForm.poolPeriod", new ActionMessage(
			    "erro.deviceForm.poolPeriod"));
		} else if (this.getDeviceManager().getPoolPeriod() <= 0) {
		    errors.add("deviceForm.poolPeriod", new ActionMessage(
			    "erro.deviceForm.PoolPeriod"));
		}
	    }
	}

	if (emptyForm) {
	    return null;
	} else {
	    return errors;
	}
    }

}
