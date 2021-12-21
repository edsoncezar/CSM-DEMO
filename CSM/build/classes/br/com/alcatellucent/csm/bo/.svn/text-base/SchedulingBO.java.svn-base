package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.alcatellucent.csm.beans.DeviceManager;
import br.com.alcatellucent.csm.beans.ProcessorPacket;
import br.com.alcatellucent.csm.beans.Protocol;
import br.com.alcatellucent.csm.beans.ScheduledGroup;
import br.com.alcatellucent.csm.beans.ScheduledProtocol;
import br.com.alcatellucent.csm.beans.Scheduling;
import br.com.alcatellucent.csm.cs.client.CsClientProxy;
import br.com.alcatellucent.csm.cs.client.CsClientProxyConnectionException;
import br.com.alcatellucent.csm.cs.client.CsClientProxyRuntimeException;
import br.com.alcatellucent.csm.cs.client.message.CSAnswer_Message;
import br.com.alcatellucent.csm.cs.client.message.CSClientRemoteErrorException;
import br.com.alcatellucent.csm.cs.client.message.CSInvalidAnswerException;
import br.com.alcatellucent.csm.cs.client.message.bo.CSPolicy;
import br.com.alcatellucent.csm.cs.client.message.bo.CSVarConstants;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;
import br.com.alcatellucent.csm.quartz.beans.JobDetails;
import br.com.alcatellucent.csm.util.InvalidParameterException;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;

public class SchedulingBO {

	private static final Logger logger = Logger.getLogger(SchedulingBO.class);

	private HbCommonDAO<Scheduling> schedulingDao;
	private QuartzBO quartzBO = null;

	public SchedulingBO() {
		logger.debug("Carregando SchedulingBO ..");
		schedulingDao = new HbCommonDAO<Scheduling>(Scheduling.class);
		quartzBO = new QuartzBO();
	}

	@SuppressWarnings("unchecked")
	public Object save(Scheduling scheduling) throws ExceptionSys {
		try {
			if (scheduling.getId().equals("")) {
				schedulingDao.save(scheduling);
				logger.info("Modify scheduling .." + scheduling.getName());
			} else {
				schedulingDao.update(scheduling);
				logger.info("Save scheduling .." + scheduling.getName());
			}
		} catch (ExceptionSys e) {
			e.printStackTrace();
			logger.info("Error scheduling .." + e);
		}
		return schedulingDao.getLastObject();
	}

	@SuppressWarnings("unchecked")
	public void delete(Scheduling scheduling) throws ExceptionSys {
		try {
			schedulingDao.delete(scheduling);
		} catch (ExceptionSys e) {
			e.printStackTrace();
			logger.info("Error delete scheduling .." + scheduling.getId());
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<Scheduling> list() throws ExceptionSys {
		Collection<Scheduling> listScheduling = new ArrayList<Scheduling>();
		listScheduling = schedulingDao.findAll();
		return listScheduling;
	}

	public Scheduling findById(String id) throws ExceptionSys {
		Scheduling scheduling = (Scheduling) schedulingDao.findById(id);
		return scheduling;
	}

	@SuppressWarnings("unchecked")
	public List<Scheduling> getSchedByTrafficPolicyId(String trafficPolicyId) {
		Session session = null;
		List<Scheduling> listSched = new ArrayList<Scheduling>();

		String hql = "from Scheduling where trafficpolicy_id ='" + trafficPolicyId + "'";
		try {
			session = HibernateUtil.currentSession();
			Query query = session.createQuery(hql);

			listSched = query.list();
		} finally {
			HibernateUtil.closeSession();
		}

		return listSched;
	}

	public String execute(String scheduleId, String processorPacketId) throws ExceptionSys {

		String out = null;

		ProcessorPacketBO pPacketBO = new ProcessorPacketBO();
		DeviceManagerBO dManagerBO = new DeviceManagerBO();
		ProcessorPacket pPacket = pPacketBO.edit(processorPacketId);
		DeviceManager asm = dManagerBO.findByDeviceId(pPacket.getDevice().getId());

		Scheduling scheduling = findById(scheduleId);

		out = execute(scheduling, asm, pPacket);

		pPacket.setTrafficPolicyId(scheduling.getTrafficPolicyId());
		pPacketBO.save(pPacket);

		return out;
	}

	public String execute(Scheduling scheduling, DeviceManager asm, ProcessorPacket dppm) throws ExceptionSys {

		String[][] flows = new String[CSVarConstants.NUMBER_OF_PROTOCOLS][1];
		String[] limitUp = new String[CSVarConstants.NUMBER_OF_PROTOCOLS];
		String[] limitDown = new String[CSVarConstants.NUMBER_OF_PROTOCOLS];
		// Up and Down, or Down and Up
		String[][] groupLimitDownUp = new String[CSVarConstants.NUMBER_OF_PROTOCOLS][2];
		String[] protocolGroupMapping = new String[CSVarConstants.NUMBER_OF_PROTOCOLS];
		CSAnswer_Message policyAnswer = null;
		String command = null;
		int i = 0;
		// New code added for the QOS feature
		// ArrayList<CSParameterVar> qosVarList = new ArrayList<CSParameterVar>();
		// CSParameterVar diffServ = new CSParameterVar();
		// CSParameterVar diffServEn = new CSParameterVar();
		// CSDiffServ diffServ = new CSDiffServ();
		// String[] diffServValues = new
		// String[CSVarConstants.NUMBER_OF_PROTOCOLS];
		// String[] diffServEnValues = new
		// String[CSVarConstants.NUMBER_OF_PROTOCOLS];
		// \ New code added for the QOS feature
		for (i = 0; i < CSVarConstants.NUMBER_OF_PROTOCOLS; i++) {
			flows[i][0] = CSVarConstants.FLOWS_DEFAULT_VALUE;
			limitUp[i] = CSVarConstants.PROTOCOL_DEFAULT_VALUE;
			limitDown[i] = CSVarConstants.PROTOCOL_DEFAULT_VALUE;
			groupLimitDownUp[i][0] = CSVarConstants.PROTOCOL_DEFAULT_VALUE;
			groupLimitDownUp[i][1] = CSVarConstants.PROTOCOL_DEFAULT_VALUE;
			protocolGroupMapping[i] = "0";
			// New code added for the QOS feature
			// diffServValues[i] = "2"; // default value, should never be used
			// diffServEnValues[i] = CSVarConstants.PROTOCOL_MAX_VALUE;
			// \ New code added for the QOS feature
		}
		i = 1;
		for (ScheduledGroup group : scheduling.getScheduledGroups()) {
			for (ScheduledProtocol protocol : group.getScheduledProtocols()) {
				flows[protocol.getInternalNumber().intValue()][0] = protocol.getScheduledTrafficValues().getFlowsValues()
						.toString();
				limitDown[protocol.getInternalNumber().intValue()] = getValueInBytes(protocol.getScheduledTrafficValues()
						.getDownStreamValue(), protocol.getScheduledTrafficValues().getDownStreamUnit());
				limitUp[protocol.getInternalNumber().intValue()] = getValueInBytes(protocol.getScheduledTrafficValues()
						.getUpStreamValue(), protocol.getScheduledTrafficValues().getUpStreamUnit());
				protocolGroupMapping[protocol.getInternalNumber().intValue()] = String.valueOf(i);
				// New code added for the QOS feature
				// diffServValues[protocol.getInternalNumber().intValue()] =
				// String.valueOf(protocol
				// .getScheduledTrafficValues().getPriority() << 2); // left shift 2
				// bits. This shift must be removed from the RAVE code
				// diffServEnValues[protocol.getInternalNumber().intValue()] =
				// getValueInBytes(protocol
				// .getScheduledTrafficValues().getControl(),
				// protocol.getScheduledTrafficValues().getControlUnit());
				// \ New code added for the QOS feature
			}
			groupLimitDownUp[i][0] = getValueInBytes(group.getDownStreamValue(), group.getDownStreamUnit());
			groupLimitDownUp[i][1] = getValueInBytes(group.getUpStreamValue(), group.getUpStreamUnit());
			i++;
		}
		CsClientProxy myProxy = new CsClientProxy(asm.getHost(), asm.getPort());
		CSPolicy policy = new CSPolicy();
		try {
			// New code added for the QOS feature
			// diffServ.setControl(diffServEnValues);
			// diffServ.setPriority(diffServValues);
			// myProxy.setDiffServ(dppm.getNumber(), diffServ);
			// myProxy.putParameter(dppm.getNumber(), qosVarList);
			// \ New code added for the QOS feature
			policy.setFlows(flows);
			policy.setRateLimitDown(limitDown);
			policy.setRateLimitUp(limitUp);
			policy.setProtocolGroupMapping(protocolGroupMapping);
			policy.setGroupRate(groupLimitDownUp);
			policyAnswer = myProxy.setPolicy(dppm.getNumber(), policy);
		} catch (CSInvalidAnswerException e) {
			throw new ExceptionSys(e);
		} catch (CSClientRemoteErrorException e) {
			throw new ExceptionSys(e);
		} catch (CsClientProxyConnectionException e) {
			throw new ExceptionSys(e);
		} catch (CsClientProxyRuntimeException e) {
			throw new ExceptionSys(e);
		} catch (InvalidParameterException e) {
			throw new ExceptionSys(e);
		} finally {
			myProxy.disconnect();
		}
		command = "(" + myProxy.getLastCommand() + "|"
				+ (policyAnswer != null ? policyAnswer.getMessage() : "No answer from device") + ")";
		return command;
	}

	public Collection<Scheduling> listSchedulingPanel(String trafficPolicyId) {
		Collection<Scheduling> cSchedulingTemp = new ArrayList<Scheduling>();
		try {
			Collection<JobDetails> cjobDetails = quartzBO.listJobDetails();
			Collection<Scheduling> cScheduling = this.listScheduler(trafficPolicyId);
			Iterator<Scheduling> iterator = null;
			for (iterator = cScheduling.iterator(); iterator.hasNext();) {
				Scheduling scheduling = (Scheduling) iterator.next();

				Scheduling schedulingTemp = new Scheduling();
				String statusQuartz = "Disabled";
				String cronExpression = scheduling.getCronExpression();
				// Fazendo for para verificar se o scheduling está na tabela do
				// Quartz
				Iterator<JobDetails> iteratorj = null;
				for (iteratorj = cjobDetails.iterator(); iteratorj.hasNext();) {
					JobDetails jobDetails = (JobDetails) iteratorj.next();
					if (jobDetails.getJobName().equals(scheduling.getId())) {
						statusQuartz = "Started";
						cronExpression = jobDetails.getCronTriggers().getCronExpression();
					}
				}
				schedulingTemp.setId(scheduling.getId());
				schedulingTemp.setName(scheduling.getName());
				schedulingTemp.setContextId(scheduling.getContextId());
				schedulingTemp.setDateStart(scheduling.getDateStart());
				schedulingTemp.setTimeStart(scheduling.getTimeStart());
				schedulingTemp.setStatusQuartz(statusQuartz);
				schedulingTemp.setCronExpression(cronExpression);
				schedulingTemp.setTrafficPolicyId(scheduling.getTrafficPolicyId());
				cSchedulingTemp.add(schedulingTemp);
			}
		} catch (ExceptionSys e) {
			logger.error("Error  ..." + e);
		}
		return cSchedulingTemp;
	}

	@SuppressWarnings("unchecked")
	public Collection<Scheduling> listScheduler(String trafficPolicyId) throws ExceptionSys {
		Properties criterios = new Properties();
		criterios.put("trafficPolicyId", trafficPolicyId);
		Collection<Scheduling> coll = (Collection<Scheduling>) schedulingDao.findByCriteria(Scheduling.class, criterios);
		return coll;
	}

	@SuppressWarnings("unchecked")
	public Collection<Scheduling> list(String contextId) throws ExceptionSys {
		Properties criterios = new Properties();
		criterios.put("contextId", contextId);
		Collection<Scheduling> coll = (Collection<Scheduling>) schedulingDao.findByCriteria(Scheduling.class, criterios);
		return coll;
	}

	private static String getValueInBytes(String value, String unit) {
		String retValue = null;
		if (unit.equalsIgnoreCase(Protocol.UNIT_MB)) {
			retValue = String.valueOf(Long.valueOf(value).longValue() * 1048576 / 8);
		} else {
			retValue = String.valueOf(Long.valueOf(value).longValue() * 1024 / 8);
		}
		return retValue;
	}
}
