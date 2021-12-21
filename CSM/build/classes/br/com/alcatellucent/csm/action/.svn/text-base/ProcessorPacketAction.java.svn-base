package br.com.alcatellucent.csm.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Device;
import br.com.alcatellucent.csm.beans.AclRecipient;
import br.com.alcatellucent.csm.beans.ProcessorPacket;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.bo.AclRecipientBO;
import br.com.alcatellucent.csm.bo.AldeBO;
import br.com.alcatellucent.csm.bo.DeviceBO;
import br.com.alcatellucent.csm.bo.ProcessorPacketBO;
import br.com.alcatellucent.csm.bo.TrafficPolicyBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.ProcessorPacketForm;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;
import br.com.alcatellucent.csm.utils.AgyaDaemonManager;
import br.com.alcatellucent.csm.utils.comm.DeviceACLCountServletListener;

public class ProcessorPacketAction extends CsmBaseAction {

	public ActionForward initial(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception {

		HttpSession session = request.getSession();
		String redir = FORWARD_INITIAL;
		String contextId = "";

		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			ProcessorPacketForm form = (ProcessorPacketForm) actionForm;

			// Reseting values for a new insertion /////////////////////////////
			ProcessorPacket processorPacket = form.getProcessorPacket();
			// String deviceId = processorPacket.getDeviceId();
			String deviceId = processorPacket.getDevice().getId();
			processorPacket = new ProcessorPacket();
			processorPacket.getDevice().setId(deviceId);
			form.setProcessorPacket(processorPacket);
			ProcessorPacketBO processorPacketBO = new ProcessorPacketBO();
			List<String> usedNumbers = processorPacketBO.getUsedNumbers(deviceId);
			request.setAttribute("USED_NUMBERS", usedNumbers);
			TrafficPolicyBO traffPolicyBO = new TrafficPolicyBO();
			contextId = traffPolicyBO.findContextId(deviceId);
			session.setAttribute("listTraffic", traffPolicyBO.listTraffic(contextId));

			AldeBO aldeBo = new AldeBO();
			session.setAttribute("listAlde", aldeBo.findByAldeContext(contextId, false));

			DeviceBO deviceBO = new DeviceBO();
			request.setAttribute("DEVICE", deviceBO.edit(deviceId));
		} else {
			redir = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {

		// Declarations
		// //////////////////////////////////////////////////////////////////////////
		HttpSession session = request.getSession();
		// ProcessorPacketForm form = (ProcessorPacketForm) actionForm;
		ProcessorPacketBO processorPacketBO = new ProcessorPacketBO();
		// ProcessorPacket processorPacket = form.getProcessorPacket();
		Collection<ProcessorPacket> cPacket;

		// Getting Processor Packets from Database
		// //////////////////////////////////////////////
		cPacket = processorPacketBO.findByDeviceId(request.getParameter("processorPacket.device.id"));

		session.setAttribute("listProcessorPacket", cPacket);
		return actionMapping.findForward("list");
	}

	@SuppressWarnings("unchecked")
	public ActionForward edit(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		HttpSession session = request.getSession();
		String redir = "view";
		String contextId = "";

		// if (((UserProfile) request.getSession().getAttribute("userProfile"))
		// .getUserRole().getName().equals(Role.GUEST_ROLE)) {
		// redir = FORWARD_FORBIDDEN;
		// } else {
		ProcessorPacketForm form = (ProcessorPacketForm) actionForm;
		ProcessorPacketBO processorPacketBO = new ProcessorPacketBO();
		ProcessorPacket processorPacket = form.getProcessorPacket();
		processorPacket = processorPacketBO.edit(processorPacket.getId());
		form.setProcessorPacket(processorPacket);

		String deviceId = form.getProcessorPacket().getDevice().getId();

		TrafficPolicyBO traffPolicyBO = new TrafficPolicyBO();
		contextId = traffPolicyBO.findContextId(deviceId);
		session.setAttribute("listTraffic", traffPolicyBO.listTraffic(contextId));

		List<String> usedNumbers = processorPacketBO.getUsedNumbers(deviceId);
		request.setAttribute("USED_NUMBERS", usedNumbers);

		AldeBO aldeBo = new AldeBO();
		session.setAttribute("listAlde", aldeBo.findByAldeContext(contextId, false));

		DeviceBO deviceBO = new DeviceBO();
		request.setAttribute("DEVICE", deviceBO.edit(deviceId));
		// }
		return actionMapping.findForward(redir);
	}

	@SuppressWarnings("unchecked")
	public ActionForward save(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception {
		String redir = FORWARD_SUCCESS;
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			HttpSession session = request.getSession();
			ProcessorPacketForm form = (ProcessorPacketForm) actionForm;
			ProcessorPacket processorPacket = form.getProcessorPacket();
			ProcessorPacketBO processorPacketBO = new ProcessorPacketBO();
			if (null == processorPacket.getId() || processorPacket.getId().equals("")) {
				DeviceBO deviceBO = new DeviceBO();
				processorPacket.setDevice(deviceBO.edit(processorPacket.getDevice().getId()));
			}
			try {
				processorPacketBO.save(processorPacket);
				showMessages(request, "default.save.ok", MESSAGE_WARNING);

				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "processor_packet.title", "Key = " + processorPacket.getId(),
						CsmLogSeverity.LOW_SEVERITY.getValue());

				session.setAttribute("processorPacket", processorPacket);
				session.setAttribute("hasProcessorPacket", null == processorPacket.getId() ? false : true);
				// Refreshing Tree
				try {
					notifyChangedDevice(processorPacket.getDevice());
				} catch (Exception e) {
					logger.fatal("Error notifying changed device: " + processorPacket.getDevice().getId(), e);
				}
				refresh(request, TYPE_DPPM, processorPacket.getId());
			} catch (Exception e) {
				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "processor_packet.title.error", "Key = " + processorPacket.getId() + " error = "
						+ e.getMessage(), CsmLogSeverity.LOW_SEVERITY.getValue());
				showMessages(request, "default.save.error", MESSAGE_ERROR);
				// redir = "falid";
			}

			String contextId = "";

			TrafficPolicyBO traffPolicyBO = new TrafficPolicyBO();
			contextId = traffPolicyBO.findContextId(form.getProcessorPacket().getDevice().getId());
			session.setAttribute("listTraffic", traffPolicyBO.listTraffic(contextId));
			session.setAttribute("listProcessorPacket", processorPacketBO.findByDeviceId(processorPacket.getDevice()
					.getId()));
		} else {
			redir = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	@SuppressWarnings("unchecked")
	public ActionForward delete(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		HttpSession session = request.getSession();
		String redir = FORWARD_SUCCESS;
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			ProcessorPacketBO processorPacketBO = new ProcessorPacketBO();
			ProcessorPacketForm form = (ProcessorPacketForm) actionForm;
			String deviceId = "";
			try {
				ProcessorPacket processorPacket = processorPacketBO.edit(form.getProcessorPacket().getId());
				deviceId = processorPacket.getDevice().getId();
				processorPacketBO.delete(form.getProcessorPacket().getId());

				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "processor_packet.title.delete", "Key = " + processorPacket.getId(),
						CsmLogSeverity.LOW_SEVERITY.getValue());

				showMessages(request, "default.delete.ok", MESSAGE_WARNING);
				// Refreshing Tree
				refresh(request, TYPE_DEVICE, deviceId);
				try {
					notifyChangedDevice(processorPacket.getDevice());
				} catch (Exception e) {
					logger.fatal("Error notifying changed device: " + processorPacket.getDevice().getId(), e);
				}
			} catch (Exception e) {
				showMessages(request, "default.delete.error", MESSAGE_ERROR);
				// redir = "falid";
			}
			session.setAttribute("listProcessorPacket", processorPacketBO.findByDeviceId(deviceId));
		} else {
			redir = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	/**
	 * This method returns the DPPM´s List of applied ACLs
	 */
	@SuppressWarnings("unchecked")
	public ActionForward listAcl(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		String redir = "listAcl";
		ProcessorPacketForm form = (ProcessorPacketForm) actionForm;
		ProcessorPacket pPacket = form.getProcessorPacket();
		HttpSession session = request.getSession();
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			try {
				ProcessorPacketBO pPacketBO = new ProcessorPacketBO();
				pPacket = pPacketBO.edit(pPacket.getId());
				AclRecipientBO aclRecipientBO = new AclRecipientBO();
				form.setProcessorPacket(pPacket);
				session.setAttribute("ACL_LIST", aclRecipientBO.listAppliedByDppm(pPacket.getId()));
			} catch (ExceptionSys ex) {
				showMessages(request, "error.pktAction.LoadingACLList", MESSAGE_ERROR);
				session.setAttribute("ACL_LIST", new ArrayList<AclRecipient>());
			}
		} else {
			redir = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	private void notifyChangedDevice(Device device) throws ExceptionSys {
		AgyaDaemonManager agyaManager = AgyaDaemonManager.getInstance();
		DeviceACLCountServletListener daemonAclCounter = agyaManager.getDeviceAclPoller();
		if (daemonAclCounter == null) {
			logger.fatal(DeviceACLCountServletListener.MY_NAME + " not initialized");
		} else {
			daemonAclCounter.notifyChangedDevice(device.getId());
		}
	}

}
