package br.com.alcatellucent.csm.scheduler;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import br.com.alcatellucent.csm.beans.Context;
import br.com.alcatellucent.csm.beans.Device;
import br.com.alcatellucent.csm.beans.DeviceManager;
import br.com.alcatellucent.csm.beans.ProcessorPacket;
import br.com.alcatellucent.csm.beans.Scheduling;
import br.com.alcatellucent.csm.beans.TrafficPolicy;
import br.com.alcatellucent.csm.beans.TrafficPolicyHistory;
import br.com.alcatellucent.csm.bo.ContextBO;
import br.com.alcatellucent.csm.bo.DeviceBO;
import br.com.alcatellucent.csm.bo.DeviceManagerBO;
import br.com.alcatellucent.csm.bo.ProcessorPacketBO;
import br.com.alcatellucent.csm.bo.SchedulingBO;
import br.com.alcatellucent.csm.bo.TrafficPolicyBO;
import br.com.alcatellucent.csm.bo.TrafficPolicyHistoryBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

public class SchedulerTrafficPropagation {

	private static final Logger logger = Logger.getLogger(SchedulerTrafficPropagation.class);

	public void executeScheduler(String schedulerId) throws ExceptionSys {

		Scheduling sch = null;
		Context ctx = null;
		ContextBO ctxBO = null;
		TrafficPolicyBO tpBO = null;
		TrafficPolicy tp = null;
		String tpContextId = null;

		SchedulingBO schBO = new SchedulingBO();
		sch = schBO.findById(schedulerId);
		if (sch == null) {
			logger.fatal("Scheduler not found: " + schedulerId);
			try {
				SchedulerFactory factory = new StdSchedulerFactory();
				Scheduler sched = null ;
				sched = factory.getScheduler();
				sched.deleteJob(schedulerId, schedulerId);
			} catch (SchedulerException e) {
				logger.fatal("Error deleting scheduler: " + schedulerId, e);
			}
			return;
		}
		tpBO = new TrafficPolicyBO();
		tp = tpBO.findById(sch.getTrafficPolicyId());
		if (tp == null) {
			logger.fatal("TrafficPolicy not found: " + sch.getTrafficPolicyId());
			return;
		}
		tpContextId = tp.getContextId();
		ctxBO = new ContextBO();
		ctx = ctxBO.edit(tpContextId);
		if (ctx == null) {
			logger.fatal("Context not found: " + tpContextId);
			return;
		}
		propagateRules(ctx, sch, tp, false);
	}

	private static List<Context> findChildren(Context father) throws ExceptionSys {
		ContextBO ctxBO = new ContextBO();
		return ctxBO.getListContextChildren(father.getId(), false);
	}

	private void propagateRules(Context father, Scheduling sch, TrafficPolicy tp, boolean propagate) throws ExceptionSys {
		String errorMsg = new String();
		List<Context> children = null;
		List<Device> deviceList = null;
		DeviceBO devBO = new DeviceBO();
		// Get Device List, then, the DeviceManager (ASM) List
		deviceList = devBO.getDevicesByContextId(father.getId());
		// If the parent has the same traffic policy, it must be inherited by
		// the children, otherwise, don't touch them!
		if (propagate
				&& BasicValueCheck.isEmptyString(father.getTrafficPolicyId())
				|| (!BasicValueCheck.isEmptyString(father.getTrafficPolicyId()) && 
						(father.getTrafficPolicyId().equals(sch.getTrafficPolicyId())))) {
			propagate = true;
		} else {
			propagate = false;
		}

		for (Device device : deviceList) {
			try {
				propagateToDevice(device, sch, tp, propagate);
			} catch (ExceptionSys e) {
				errorMsg += e.getMessage() + "\r\n";
			}
		}
		try {
			children = findChildren(father);
			children.remove(0);
		} catch (ExceptionSys e) {
			errorMsg += e.getMessage() + "\r\n";
		}
		// I have at least on child
		for (Context child : children) {
			try {
				propagateRules(child, sch, tp, propagate);
			} catch (ExceptionSys e) {
				errorMsg += e.getMessage() + "\r\n";
			}
		}
		if (errorMsg.length() != 0) {
			throw new ExceptionSys(errorMsg);
		}
	}

	private void propagateToDevice(Device device, Scheduling sch, TrafficPolicy tp, boolean propagate)
			throws ExceptionSys {
		TrafficPolicyHistoryBO tfpBO = new TrafficPolicyHistoryBO();
		String errorMsg = new String();
		String commandExecuted = null;
		boolean success = true;
		// If the parent has the same traffic policy, it must be inherited by
		// the
		// children, otherwise, don't touch them!
		if (propagate
				&& BasicValueCheck.isEmptyString(device.getTrafficPolicyId())
				|| (!BasicValueCheck.isEmptyString(device.getTrafficPolicyId()) && (device.getTrafficPolicyId().equals(sch
						.getTrafficPolicyId())))) {
			propagate = true;
		} else {
			propagate = false;
		}
		ProcessorPacketBO dppmBO = null;
		Collection<ProcessorPacket> dppmList = null;
		SchedulingBO schBO = new SchedulingBO();
		DeviceManagerBO asmBO = new DeviceManagerBO();
		DeviceManager asm = asmBO.findByDeviceId(device.getId()); // go away!
		if ((asm == null) || ((asm != null) && (asm.getId() == null))) {
			String message = "Device Manager (ASM) not found";
			success = false;
			tfpBO.saveHistory("0", sch.getId(), null, TrafficPolicyHistory.MODE_SCHEDULED, success, message);
			throw new ExceptionSys(message);
		}
		// device
		dppmBO = new ProcessorPacketBO();
		dppmList = dppmBO.findByDeviceId(asm.getId()); // go away!
		for (ProcessorPacket dppm : dppmList) {
			if (((BasicValueCheck.isEmptyString(dppm.getTrafficPolicyId()) && propagate))
					|| ((!BasicValueCheck.isEmptyString(dppm.getTrafficPolicyId())) && dppm.getTrafficPolicyId().equals(
							sch.getTrafficPolicyId()))) {
				String message = "Policy " + tp.getId() + " ";
				try {
					// execute errors go to a
					commandExecuted = schBO.execute(sch, asm, dppm);
					message = "successfully applied to: " + asm.getId() + ":" + dppm.getNumber();
					// "stack"
				} catch (ExceptionSys e) {
					success = false;
					errorMsg += e.getMessage() + "\r\n";
					message = e.getMessage();
				} finally {
					tfpBO
							.saveHistory(dppm.getId(), sch.getId(), null, TrafficPolicyHistory.MODE_SCHEDULED, success,
									message);
				}
			}
		}
		if (errorMsg.length() != 0) {
			logger.error(errorMsg + commandExecuted);
			throw new ExceptionSys(errorMsg);
		}
	}

}
