package br.com.alcatellucent.csm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Device;
import br.com.alcatellucent.csm.beans.DeviceManager;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.bo.DeviceBO;
import br.com.alcatellucent.csm.bo.DeviceManagerBO;
import br.com.alcatellucent.csm.bo.TrafficPolicyBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.facade.DeviceFacade;
import br.com.alcatellucent.csm.form.DeviceForm;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;
import br.com.alcatellucent.csm.utils.AgyaDaemonManager;
import br.com.alcatellucent.csm.utils.comm.DeviceACLCountServletListener;
import br.com.alcatellucent.csm.utils.comm.DeviceWalkerServletListener;

public class DeviceAction extends CsmBaseAction {

	public ActionForward initial(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {

		HttpSession session = request.getSession();
		String redir = FORWARD_INITIAL;
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			DeviceForm form = (DeviceForm) actionForm;
			Device device = new Device();
			DeviceManager deviceManager = new DeviceManager();
			device.setContextId(form.getDevice().getContextId());
			deviceManager.setDevice(device);
			TrafficPolicyBO traffPolicyBO = new TrafficPolicyBO();

			session.setAttribute("listTraffic", traffPolicyBO.listTraffic(form.getDevice().getContextId()));
			form.setDevice(device);
			form.setDeviceManager(deviceManager);
		} else {
			redir = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		DeviceForm form = (DeviceForm) actionForm;
		DeviceBO deviceBO = new DeviceBO();
		TrafficPolicyBO traffPolicyBO = new TrafficPolicyBO();
		HttpSession session = request.getSession();

		UserProfile userProfile = (UserProfile) session.getAttribute("userProfile");

		session.setAttribute("listDevice", deviceBO.getDevicesByContextId(form.getDevice().getContextId(), userProfile));
		session.setAttribute("listTraffic", traffPolicyBO.listTraffic(form.getDevice().getContextId()));
		return actionMapping.findForward(FORWARD_LIST);
	}

	@SuppressWarnings("unchecked")
	public ActionForward edit(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {

		HttpSession session = request.getSession();
		String redir = FORWARD_EDIT;
		// if (((UserProfile)
		// request.getSession().getAttribute("userProfile")).getUserRole().getName().equals(
		// Role.GUEST_ROLE)) {
		// redir = FORWARD_FORBIDDEN;
		// } else {
		DeviceFacade deviceFacade = (DeviceFacade) request.getSession().getServletContext().getAttribute("deviceFacade");
		DeviceManagerBO deviceManagerBO = new DeviceManagerBO();
		DeviceForm form = (DeviceForm) actionForm;
		Device device = deviceFacade.edit(form.getDevice().getId());
		DeviceManager deviceManager = deviceManagerBO.findByDeviceId(device.getId());
		form.setDevice(device);
		form.setDeviceManager(deviceManager);
		// }
		//
		// DeviceForm form = (DeviceForm) actionForm;
		TrafficPolicyBO traffPolicyBO = new TrafficPolicyBO();

		session.setAttribute("listTraffic", traffPolicyBO.listTraffic(form.getDevice().getContextId()));
		return actionMapping.findForward(redir);
	}

	@SuppressWarnings("unchecked")
	public ActionForward save(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		String redir = FORWARD_LIST;
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			HttpSession session = request.getSession();

			UserProfile userProfile = (UserProfile) session.getAttribute("userProfile");

			DeviceBO deviceBO = new DeviceBO();
			DeviceManagerBO deviceManagerBO = new DeviceManagerBO();

			DeviceForm form = (DeviceForm) actionForm;
			Device device = form.getDevice();
			DeviceManager deviceManager = form.getDeviceManager();

			device.setDeviceManager(deviceManager);
			try {
				deviceBO.save(device);
				deviceManager.setDevice(device);
				deviceManagerBO.save(deviceManager);
				form.setRefresh("true");
				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "device.title", "key = " + device.getId(), CsmLogSeverity.LOW_SEVERITY.getValue());
				showMessages(request, "default.save.ok", MESSAGE_WARNING);
				refresh(request, TYPE_DEVICE, device.getId());
			} catch (Exception e) {
				showMessages(request, "default.save.error", MESSAGE_ERROR);
				// redir = "falid";
				logger.error(e);
			}
			try {
				notifyChangedDevice(device);
			} catch (Exception e) {
				logger.fatal("Error notifying changed device: " + form.getDevice().getId(), e);
			}

			session.setAttribute("listDevice", deviceBO
					.getDevicesByContextId(form.getDevice().getContextId(), userProfile));
		} else {
			redir = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	@SuppressWarnings("unchecked")
	public ActionForward delete(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		HttpSession session = request.getSession();

		UserProfile userProfile = (UserProfile) session.getAttribute("userProfile");

		DeviceBO deviceBO = new DeviceBO();
		DeviceForm form = (DeviceForm) actionForm;
		String redir = FORWARD_LIST;
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			Device deletedDevice = null;
			try {
				deletedDevice = deviceBO.edit(form.getDevice().getId());
				deviceBO.delete(form.getDevice().getId());
				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "device.title.delete", "key = " + form.getDevice().getId(),
						CsmLogSeverity.LOW_SEVERITY.getValue());
				// send message to view
				showMessages(request, "default.delete.ok", MESSAGE_WARNING);
				// refreshing tree -- don't change the substring method!!!
				refresh(request, TYPE_DEVICE, deletedDevice.getContextId().substring(0, 12));

			} catch (Exception e) {
				showMessages(request, "default.delete.error", MESSAGE_ERROR);
				// redir = "falid";
				logger.error(e);
			}

			try {
				if (deletedDevice != null) {
					notifyChangedDevice(deletedDevice);
				}
			} catch (Exception e) {
				logger.fatal("Error notifying changed device: " + form.getDevice().getId(), e);
			}

			session.setAttribute("listDevice", deviceBO
					.getDevicesByContextId(form.getDevice().getContextId(), userProfile));
		} else {
			redir = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	/**
	 * @param device
	 * @param action
	 *           0: DELETE, 1: UPDATE, INSERT
	 * @throws ExceptionSys
	 */
	private void notifyChangedDevice(Device device) throws ExceptionSys {
		AgyaDaemonManager agyaManager = AgyaDaemonManager.getInstance();
		DeviceWalkerServletListener daemonWalker = agyaManager.getDeviceWalkerDaemon();
		DeviceACLCountServletListener daemonAclCounter = agyaManager.getDeviceAclPoller();
		if (daemonWalker == null) {
			logger.fatal(DeviceWalkerServletListener.MY_NAME + " not initialized");
		} else {
			daemonWalker.notifyChangedDeviceManager(device);
		}
		if (daemonAclCounter == null) {
			logger.fatal(DeviceACLCountServletListener.MY_NAME + " not initialized");
		} else {
			daemonAclCounter.notifyChangedDevice(device.getId());
		}
	}

}
