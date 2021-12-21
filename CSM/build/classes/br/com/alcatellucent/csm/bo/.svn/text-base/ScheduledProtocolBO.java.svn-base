package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.Protocol;
import br.com.alcatellucent.csm.beans.ScheduledGroup;
import br.com.alcatellucent.csm.beans.ScheduledProtocol;
import br.com.alcatellucent.csm.beans.ScheduledTrafficValues;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;

public class ScheduledProtocolBO {

	private static final Logger logger = Logger.getLogger(ScheduledProtocolBO.class);

	private HbCommonDAO<ScheduledProtocol> scheduledProtocolDAO;
	private HbCommonDAO<ScheduledTrafficValues> scheduledTrafficValuesDAO;

	@SuppressWarnings("unchecked")
	public ScheduledProtocolBO() {
		scheduledProtocolDAO = new HbCommonDAO(ScheduledProtocol.class);
		scheduledTrafficValuesDAO = new HbCommonDAO(
				ScheduledTrafficValues.class);
		logger.debug("Carregando ScheduledProtocolDAO..");
	}

	@SuppressWarnings("unchecked")
	public void save(ScheduledProtocol scheduledProtocol) throws ExceptionSys {

		try {
			if (null != scheduledProtocol.getId() && !scheduledProtocol.getId().equals("")) {
				scheduledProtocolDAO.update(scheduledProtocol);
				logger.info("Modify information of ScheduledProtocol .."
						+ scheduledProtocol.getName());
			} else {
				scheduledProtocolDAO.save(scheduledProtocol);
				logger.info("Save information of ScheduledProtocol .."
						+ scheduledProtocol.getName());
			}
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void delete(String id) throws ExceptionSys {
		try {
			ScheduledProtocol scheduledProtocol = (ScheduledProtocol) scheduledProtocolDAO
					.findById(id);
			scheduledProtocolDAO.delete(scheduledProtocol);
			logger.info("Delete information of ScheduledProtocol .." + id);
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<ScheduledProtocol> list() throws ExceptionSys {
		Collection<ScheduledProtocol> listProtocol = new ArrayList<ScheduledProtocol>();
		listProtocol = scheduledProtocolDAO.findAll();
		return listProtocol;
	}

	public ScheduledProtocol edit(String id) throws ExceptionSys {
		ScheduledProtocol scheduledProtocol = (ScheduledProtocol) scheduledProtocolDAO
				.findById(id);
		return scheduledProtocol;
	}

	public ScheduledTrafficValues editTraficValues(String id)
			throws ExceptionSys {
		ScheduledTrafficValues scheduledTrafficValues = (ScheduledTrafficValues) scheduledTrafficValuesDAO
				.findById(id);
		return scheduledTrafficValues;
	}

	public void copyAllProtocols(Collection<Protocol> original,	Collection<ScheduledProtocol> setCopy, ScheduledGroup scheduledGroup) {
		ScheduledProtocol sProtocol;
		for (Protocol protocolOriginal : original) {
			sProtocol = copyProtocol(protocolOriginal);
			sProtocol.setScheduledGroup(scheduledGroup);
			setCopy.add(sProtocol);
		}
	}

	/**
	 * make a copy from a Protocol to a ScheduledProtocol
	 */
	public ScheduledProtocol copyProtocol(Protocol protocol) {

		ScheduledProtocol scheduledProtocol = new ScheduledProtocol();
		scheduledProtocol.setDescription(protocol.getDescription());
		scheduledProtocol.setInternalNumber(protocol.getInternalNumber());
		scheduledProtocol.setName(protocol.getName());
		
		ScheduledTrafficValues sTrafficValues = new ScheduledTrafficValues();
		sTrafficValues = copyTrafficValues(protocol);
		scheduledProtocol.setScheduledTrafficValues(sTrafficValues);
		 
		// / setting the scheduledProtocol in the ScheduledtrafficValues
		sTrafficValues.setScheduledProtocol(scheduledProtocol);
		
		return scheduledProtocol;

	}

	/**
	 * make a copy of a single trafficValue to a ScheduledTrafficValue;
	 */
	public ScheduledTrafficValues copyTrafficValues(Protocol protocol) {
		ScheduledTrafficValues scheduledTrafficValues = new ScheduledTrafficValues();

		scheduledTrafficValues.setDownStreamUnit(protocol.getDownStreamUnit());
		scheduledTrafficValues.setDownStreamValue(protocol.getDownStreamValue());
		scheduledTrafficValues.setFlowsValues(protocol.getFlowsValues());
		scheduledTrafficValues.setUpStreamUnit(protocol.getUpStreamUnit());
		scheduledTrafficValues.setUpStreamValue(protocol.getUpStreamValue());
		
		// -- uso futuro -----------------------------------------------------
//		scheduledTrafficValues.setOutputmPlsUnit("");
//		scheduledTrafficValues.setOutputmPlsValue(0);
//		scheduledTrafficValues.setOutputVlanUnit("");
//		scheduledTrafficValues.setOutputVlanValue(0);


		return scheduledTrafficValues;

	}
	
}
