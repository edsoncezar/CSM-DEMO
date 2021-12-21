package br.com.alcatellucent.csm.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.ChoosePolicyObj;
import br.com.alcatellucent.csm.beans.Context;
import br.com.alcatellucent.csm.beans.Device;
import br.com.alcatellucent.csm.beans.ProcessorPacket;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.ScheduledGroup;
import br.com.alcatellucent.csm.beans.ScheduledProtocol;
import br.com.alcatellucent.csm.beans.Scheduling;
import br.com.alcatellucent.csm.beans.TrafficPolicy;
import br.com.alcatellucent.csm.beans.TrafficPolicyHistory;
import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.bo.DeviceBO;
import br.com.alcatellucent.csm.bo.ProcessorPacketBO;
import br.com.alcatellucent.csm.bo.ScheduledGroupBO;
import br.com.alcatellucent.csm.bo.ScheduledProtocolBO;
import br.com.alcatellucent.csm.bo.SchedulingBO;
import br.com.alcatellucent.csm.bo.TrafficPolicyBO;
import br.com.alcatellucent.csm.bo.TrafficPolicyHistoryBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.ChoosePolicyForm;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;

/**
 * @author Fernando
 */
public class ChoosePolicyAction extends CsmBaseAction {

	public ActionForward save(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		
		@SuppressWarnings("unused")
		String redir="details";
		if(checkCredentials(request, new String[]{Role.GUEST_ROLE}, false)){
		ChoosePolicyForm form = (ChoosePolicyForm) actionForm;
		form.setGroupValues(request.getParameter("groupsValues"));

		try {

			this.populateMultipleGroups(actionForm);
			this.populateMultipleProtocols(actionForm);

		} catch (Exception e) {
			throw new ExceptionSys(e);
		}

		showMessages(request, "message.protocols.policy.apply.ok");
		this.details(actionMapping, actionForm, request, response);
		}else{
			redir=FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	@SuppressWarnings("unused")
	private void populateMultipleGroups(ActionForm actionForm) throws ExceptionSys {

		ChoosePolicyForm form = (ChoosePolicyForm) actionForm;
		String groupValues = form.getGroupsValues();

		String arrayObjects[] = groupValues.split(";");
		ScheduledGroupBO scheduledGroupBO = new ScheduledGroupBO();
		String[] objectValues;
		ScheduledGroup group = null;
		for (String properties : arrayObjects) {
			objectValues = properties.split(",");
			group = scheduledGroupBO.edit(objectValues[0]);
			group.setUpStreamValue(objectValues[2]);
			group.setUpStreamUnit(objectValues[3]);
			group.setDownStreamValue(objectValues[4]);
			group.setDownStreamUnit(objectValues[5]);
			scheduledGroupBO.save(group);
		}
	}

	@SuppressWarnings("unused")
	private void populateMultipleProtocols(ActionForm actionForm) throws ExceptionSys {

		ChoosePolicyForm form = (ChoosePolicyForm) actionForm;
		String protocolValues = form.getProtocolValues();

		String arrayObjects[] = protocolValues.split(";");
		ScheduledProtocolBO scheduledProtocolBO = new ScheduledProtocolBO();
		String[] objectValues;
		ScheduledProtocol protocol = null;
		for (String properties : arrayObjects) {
			objectValues = properties.split(",");
			protocol = scheduledProtocolBO.edit(objectValues[0]);
			protocol.getScheduledTrafficValues().setFlowsValues(Integer.parseInt(objectValues[1]));
			protocol.getScheduledTrafficValues().setUpStreamValue(objectValues[2]);
			protocol.getScheduledTrafficValues().setUpStreamUnit(objectValues[3]);
			protocol.getScheduledTrafficValues().setDownStreamValue(objectValues[4]);
			protocol.getScheduledTrafficValues().setDownStreamUnit(objectValues[5]);
			scheduledProtocolBO.save(protocol);
		}
	}

	public ActionForward list(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		String redir=FORWARD_LIST;
		HttpSession session = request.getSession();
		ChoosePolicyForm form = (ChoosePolicyForm) actionForm;
		ProcessorPacketBO packetBO = new ProcessorPacketBO();
		if(checkCredentials(request, new String[]{Role.GUEST_ROLE}, false)){
		ProcessorPacket packet = form.getProcessorPacket();
		form.setAction("applyNow");

		packet = packetBO.edit(packet.getId());
		form.setProcessorPacket(packet);
		DeviceBO deviceBO = new DeviceBO();
		Device device = deviceBO.edit(packet.getDevice().getId());

		populateSchedulesList(session, device.getContextId());
		}else{
			redir=FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	public ActionForward applyNow(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		ChoosePolicyForm form = (ChoosePolicyForm) actionForm;
		String redir=FORWARD_INITIAL;
		if(checkCredentials(request, new String[]{Role.GUEST_ROLE}, false)){
		ProcessorPacket pPacket = form.getProcessorPacket();

		TrafficPolicyHistoryBO historyBO = new TrafficPolicyHistoryBO();
		String message = null;
		String execOut = null;
		Boolean status = true;
		try {
			SchedulingBO schBO = new SchedulingBO();
			execOut = schBO.execute(form.getScheduleId(), pPacket.getId());
			message = "Policy successfully applied to: " + pPacket.getDevice().getId() + ":" + pPacket.getId() + " "
					+ execOut;
			showMessages(request, "message.policy.apply.ok", MESSAGE_WARNING);
			// Igor - 2007-10-01 - Inclusão de Log da aplicação.
			this.saveLog(request, "choose_policy.title.apply_now", message, CsmLogSeverity.LOW_SEVERITY.getValue());
		} catch (Exception ex) {
			message = "Error applying policy to: " + pPacket.getDevice().getId() + ":" + pPacket.getId() + " "
					+ (execOut == null ? "" : execOut) + "\r\n" + ex.getMessage();
			status = false;
			showMessages(request, "error.policy.apply.fail", MESSAGE_ERROR);
		}
		historyBO.saveHistory(pPacket.getId(), form.getScheduleId(), ((User) request.getSession().getAttribute(
				"userSession")).getId(), TrafficPolicyHistory.MODE_MANUAL, status, message);
		}else{
			redir=FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	public ActionForward listForDevice(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		String redir=FORWARD_LIST;
		HttpSession session = request.getSession();
		ChoosePolicyForm form = (ChoosePolicyForm) actionForm;
		if(checkCredentials(request, new String[]{Role.GUEST_ROLE}, false)){
		form.setAction("applyNowForDevice");
		DeviceBO deviceBO = new DeviceBO();
		Device device = deviceBO.edit(form.getDevice().getId());

		populateSchedulesList(session, device.getContextId());
		}else{
			redir=FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	public ActionForward applyNowForDevice(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws ExceptionSys {
		ChoosePolicyForm form = (ChoosePolicyForm) actionForm;
		String redir=FORWARD_INITIAL;
		if(checkCredentials(request, new String[]{Role.GUEST_ROLE}, false)){
			Device device = form.getDevice();

			DeviceBO deviceBO = new DeviceBO();
			device = deviceBO.edit(device.getId());
			ProcessorPacketBO pPacketBO = new ProcessorPacketBO();
			device.setProcessorPacketList(pPacketBO.findByDeviceId(device.getId()));

			SchedulingBO schBO = new SchedulingBO();
			TrafficPolicyHistoryBO historyBO = new TrafficPolicyHistoryBO();
			// apply the selected schedule for each Processor Packet
			String message = null;
			String execOut = null;
			Boolean status = true;
			Boolean fullApplyed = true;
			for (ProcessorPacket pPacket : device.getProcessorPacketList()) {
				try {
					schBO.execute(form.getScheduleId(), pPacket.getId());
					message = "Policy successfully applied to: " + pPacket.getDevice().getId() + ":" + pPacket.getId() + " "
						+ execOut;
					// Igor - 2007-10-01 - Inclusão de Log da aplicação.
					this.saveLog(request, "choose_policy.title.apply_now", message, CsmLogSeverity.LOW_SEVERITY.getValue());
				} catch (Exception ex) {
					message = "Error applying policy to: " + pPacket.getDevice().getId() + ":" + pPacket.getId() + " "
							+ (execOut == null ? "" : execOut) + "\r\n" + ex.getMessage();
					status = false;
					fullApplyed = false;
				}
				historyBO.saveHistory(pPacket.getId(), form.getScheduleId(), ((User) request.getSession().getAttribute(
						"userSession")).getId(), TrafficPolicyHistory.MODE_MANUAL, status, message);
			}
			if (fullApplyed == true) {
				showMessages(request, "message.policy.apply.ok", MESSAGE_WARNING);
			} else {
				showMessages(request, "error.policy.apply.device", MESSAGE_ERROR);
			}
		}else{
			redir=FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	public ActionForward listForContext(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		String redir=FORWARD_LIST;
		if(checkCredentials(request, new String[]{Role.GUEST_ROLE}, false)){
			HttpSession session = request.getSession();
			ChoosePolicyForm form = (ChoosePolicyForm) actionForm;
			form.setAction("applyNowForContext");

			populateSchedulesList(session, form.getContext().getId());
		}else{
			redir=FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	public ActionForward applyNowForContext(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) throws ExceptionSys {
		ChoosePolicyForm form = (ChoosePolicyForm) actionForm;
		String redir=FORWARD_INITIAL;
		if(checkCredentials(request, new String[]{Role.GUEST_ROLE}, false)){
			Context context = form.getContext();
			DeviceBO deviceBO = new DeviceBO();
			SchedulingBO schBO = new SchedulingBO();
			// Get all devices
			List<Device> devicesToApply = deviceBO
					.getListDeviceByHierarchy(context.getId());

			ProcessorPacketBO pPacketBO = new ProcessorPacketBO();

			TrafficPolicyHistoryBO historyBO = new TrafficPolicyHistoryBO();
			Boolean fullApplyed = true;
			for (Device actualDevice : devicesToApply) {
				// apply the selected schedule for each Processor Packet
				actualDevice.setProcessorPacketList(pPacketBO
						.findByDeviceId(actualDevice.getId()));
				String message = null;
				String execOut = null;
				Boolean status = true;
				for (ProcessorPacket pPacket : actualDevice
						.getProcessorPacketList()) {
					try {
						schBO.execute(form.getScheduleId(), pPacket.getId());
						message = "Policy successfully applied to: "
								+ pPacket.getDevice().getId() + ":"
								+ pPacket.getId() + " " + execOut;
						// Igor - 2007-10-01 - Inclusão de Log da aplicação.
						this
								.saveLog(request,
										"choose_policy.title.apply_now",
										message, CsmLogSeverity.LOW_SEVERITY
												.getValue());
					} catch (Exception ex) {
						message = "Error applying policy to: "
								+ pPacket.getDevice().getId() + ":"
								+ pPacket.getId() + " "
								+ (execOut == null ? "" : execOut) + "\r\n"
								+ ex.getMessage();
						status = false;
						fullApplyed = false;
					}
					historyBO.saveHistory(pPacket.getId(),
							form.getScheduleId(), ((User) request.getSession()
									.getAttribute("userSession")).getId(),
							TrafficPolicyHistory.MODE_MANUAL, status, message);
				}
			}

			if (fullApplyed == true) {
				showMessages(request, "message.policy.apply.ok",
						MESSAGE_WARNING);
			} else {
				showMessages(request, "error.policy.apply.context",
						MESSAGE_ERROR);
			}
		}
		else{
			redir=FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	protected void populateSchedulesList(HttpSession session, String contextId) throws ExceptionSys {
		SchedulingBO schedulingBO = new SchedulingBO();
		Collection<Scheduling> collSchedules = schedulingBO.list(contextId);
		TrafficPolicyBO trfPolicyBO = new TrafficPolicyBO();
		TrafficPolicy tPolicy;
		for (Scheduling schAtual : collSchedules) {
			tPolicy = trfPolicyBO.findById(schAtual.getTrafficPolicyId());
			if (tPolicy != null) {
				schAtual.setTrafficPolicyName(tPolicy.getName());
			}
		}
		session.setAttribute("listSchedules", collSchedules);
	}

	@SuppressWarnings("unchecked")
	public ActionForward details(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		String redir="details";
		if(checkCredentials(request, new String[]{Role.GUEST_ROLE}, false)){
		HttpSession session = request.getSession();
		String id = request.getParameter("id");

		if (null == id) {
			id = (String) session.getAttribute("idDetails");
		}
		session.setAttribute("idDetails", id);

		SchedulingBO schedullingBO = new SchedulingBO();
		Scheduling schedulling = new Scheduling();
		schedulling = (Scheduling) schedullingBO.findById(id);

		Collection<ChoosePolicyObj> listScheduledGroups = new ArrayList<ChoosePolicyObj>();
		Collection<ScheduledGroup> scheduledGroups = (Set<ScheduledGroup>) schedulling.getScheduledGroups();

		for (ScheduledGroup sg : scheduledGroups) {
			for (ScheduledProtocol p : sg.getScheduledProtocols()) {
				ChoosePolicyObj cp = new ChoosePolicyObj();
				cp.setScheduledProtocol(p);
				cp.setScheduledGroup(sg);
				listScheduledGroups.add(cp);
			}
		}
		session.setAttribute("LIST_PROTOCOL_DETAILS", listScheduledGroups);
		}else{
			redir=FORWARD_FORBIDDEN;
		}
		return mapping.findForward(redir);
	}
}