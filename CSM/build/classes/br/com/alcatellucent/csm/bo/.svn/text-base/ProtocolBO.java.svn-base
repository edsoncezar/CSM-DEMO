package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.alcatellucent.csm.beans.Protocol;
import br.com.alcatellucent.csm.beans.ScheduledGroup;
import br.com.alcatellucent.csm.beans.ScheduledProtocol;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;

public class ProtocolBO {

	private static final Logger logger = Logger.getLogger(ProtocolBO.class);

	private HbCommonDAO<Protocol> protocolDao;

	public ProtocolBO() {
		logger.debug("Carregando ProtocolDAO..");
		protocolDao = new HbCommonDAO<Protocol>(Protocol.class);
	}

	@SuppressWarnings("unchecked")
	public void save(Protocol protocol) throws ExceptionSys {

		try {
			if (!protocol.getId().equals("")) {
				protocolDao.update(protocol);
				logger.info("Modify information of protocol .." + protocol.getName());
			} else {
				protocolDao.save(protocol);
				logger.info("Save information of protocol .. " + protocol.getName());
			}
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void delete(String id) throws ExceptionSys {
		try {
			Protocol protocol = (Protocol) protocolDao.findById(id);
			protocolDao.delete(protocol);
			logger.info("Delete information of protocol .." + id);
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<Protocol> list() throws ExceptionSys {
		Collection<Protocol> listProtocol = new ArrayList<Protocol>();
		listProtocol = protocolDao.findAll();
		return listProtocol;
	}

	@SuppressWarnings("unchecked")
	public Collection<Protocol> list(boolean isAclUsedProtocol) throws ExceptionSys {
		Collection<Protocol> listPortProtocolGroup = null;
		Properties criterios = new Properties();
		criterios.put("aclUsed", isAclUsedProtocol);
		listPortProtocolGroup = protocolDao.findByCriteria(Protocol.class, criterios);
		return listPortProtocolGroup;
	}

	/**
	 * 
	 * This methods avoids duplicate insertions of ScheduledProtocols in a
	 * ScheduledGroup. Returns a list of all available protocols that is not in
	 * the ScheduledGroup, by comparing the internal protocol number of the
	 * scheduledProtocol and Protocol.
	 * 
	 * @param isAclUsedProtocol
	 * @return Collection<Protocol> - with the protocols that was not in the
	 *         ScheduledGroup.
	 * @throws ExceptionSys
	 */
	@SuppressWarnings("unchecked")
	public Collection<Protocol> listNotScheduled(boolean isAclUsedProtocol, ScheduledGroup scheduledGroup)
			throws ExceptionSys {
		Collection<Protocol> listProtocol = list(isAclUsedProtocol);
		Collection<Protocol> returnList = new HashSet<Protocol>();
		Boolean keep = true;
		for (Protocol protocol : listProtocol) {
			keep = true;
			for (ScheduledProtocol schProtocol : scheduledGroup.getScheduledProtocols()) {
				if (protocol.getInternalNumber().equals(schProtocol.getInternalNumber())) {
					keep = false;

				}
			}
			if (keep)
				returnList.add(protocol);
		}
		return returnList;
	}

	public Protocol edit(String id) throws ExceptionSys {
		Protocol protocol = (Protocol) protocolDao.findById(id);
		return protocol;
	}

	@SuppressWarnings("unchecked")
	public List<String> getUsedInternalNumbers() {
		List<String> usedNumbers = null;
		Session session = null;
		try {
			String sql = "select internalNumber internalNumber from protocol order by internalNumber";
			session = HibernateUtil.currentSession();
			Query query = session.createSQLQuery(sql);
			usedNumbers = (List<String>) query.list();
		} finally {
			HibernateUtil.closeSession();
		}
		return usedNumbers;
	}
}
