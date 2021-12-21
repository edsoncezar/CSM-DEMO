package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.alcatellucent.csm.beans.Acl;
import br.com.alcatellucent.csm.beans.AclRecipient;
import br.com.alcatellucent.csm.beans.ProcessorPacket;
import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;
import br.com.alcatellucent.csm.utils.AgyaDaemonManager;
import br.com.alcatellucent.csm.utils.comm.DeviceACLCountServletListener;

public class AclRecipientBO {
	private static final Logger logger = Logger.getLogger(AclRecipientBO.class);
	private HbCommonDAO<AclRecipient> aclRecipientDao;

	public AclRecipientBO() {
		logger.debug("Carregando AclRecipientDAO..");
		aclRecipientDao = new HbCommonDAO<AclRecipient>(AclRecipient.class);
	}

	@SuppressWarnings("unchecked")
	public void save(AclRecipient aclRecipient) throws ExceptionSys {

		try {
			if (null != aclRecipient.getId() && !aclRecipient.getId().equals("")) {
				aclRecipientDao.update(aclRecipient);
				logger.info("Modify information of aclRecipient .." + aclRecipient.getName());
			} else {
				aclRecipientDao.save(aclRecipient);
				logger.info("Save information of aclRecipient .. " + aclRecipient.getName());
			}
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void delete(String id) throws ExceptionSys {
		try {
			AclRecipient aclRecipient = (AclRecipient) aclRecipientDao.findById(id);
			aclRecipientDao.delete(aclRecipient);
			logger.info("Delete information of aclRecipient .." + id);
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<AclRecipient> list() throws ExceptionSys {
		Collection<AclRecipient> listAclRecipient = new ArrayList<AclRecipient>();
		listAclRecipient = aclRecipientDao.findAll();
		return listAclRecipient;
	}

	@SuppressWarnings("unchecked")
	public Collection<AclRecipient> listAppliedByDppm(String dppmId) throws ExceptionSys {
		Collection<AclRecipient> listAclRecipient = null;
		Session sessionHibernate = null;
		String hql = "from AclRecipient WHERE dppm_id='" + dppmId + "' AND applied != 0 ORDER BY aclNumber";
		try {
			sessionHibernate = HibernateUtil.currentSession();
			Query query = sessionHibernate.createQuery(hql);
			listAclRecipient = query.list();
		} finally {
			HibernateUtil.closeSession();
		}
		return listAclRecipient;
	}

	@SuppressWarnings("unchecked")
	public Collection<AclRecipient> listToApplyByDppm(String dppmId) throws ExceptionSys {
		Collection<AclRecipient> listAclRecipient = null;
		Session sessionHibernate = null;
		String hql = "from AclRecipient WHERE dppm_id='" + dppmId + "' AND applied = 0 ORDER BY aclNumber";
		try {
			sessionHibernate = HibernateUtil.currentSession();
			Query query = sessionHibernate.createQuery(hql);
			listAclRecipient = query.list();
		} finally {
			HibernateUtil.closeSession();
		}
		return listAclRecipient;
	}

	@SuppressWarnings("unchecked")
	public List<ProcessorPacket> listProcessorPacketByAcl(String aclId) throws ExceptionSys {
		Collection<AclRecipient> listAclRecipient = null;
		Session sessionHibernate = null;
		String hql = "from AclRecipient WHERE acl_id='" + aclId + "' ORDER BY aclNumber";
		try {
			sessionHibernate = HibernateUtil.currentSession();
			Query query = sessionHibernate.createQuery(hql);
			listAclRecipient = query.list();
		} finally {
			HibernateUtil.closeSession();
		}

		ProcessorPacketBO processorPacketBO = new ProcessorPacketBO();
		List<ProcessorPacket> listProcessorPacket = new ArrayList<ProcessorPacket>();

		for (AclRecipient aclRecipient : listAclRecipient) {
			listProcessorPacket.add(processorPacketBO.edit(aclRecipient.getDppmId()));
		}

		return listProcessorPacket;
	}

	public AclRecipient edit(String id) throws ExceptionSys {
		AclRecipient aclRecipient = (AclRecipient) aclRecipientDao.findById(id);
		return aclRecipient;
	}

	public void notifyChangedAcl(String dppmId, List<AclRecipient> aclRecicipentList) throws ExceptionSys {
		AgyaDaemonManager agyaManager = AgyaDaemonManager.getInstance();
		DeviceACLCountServletListener daemon = agyaManager.getDeviceAclPoller();
		if (daemon == null) {
			logger.fatal(DeviceACLCountServletListener.MY_NAME + " not initialized");
		} else {
			daemon.notifyChangedAcl(dppmId, aclRecicipentList);
		}
	}

	public void notifyChangedAcl(Acl acl, User user) throws ExceptionSys, Exception {

		List<Acl> tmpAcl = new ArrayList<Acl>();
		Collection<AclRecipient> collRecipients;
		AclBO aclBO = new AclBO();
		try {
			// Get all processor Packets for this acl
			List<ProcessorPacket> listProcessorPacket = this.listProcessorPacketByAcl(acl.getId());
			for (ProcessorPacket p : listProcessorPacket) {
				tmpAcl = new ArrayList<Acl>();
				// Get all AclRecipients related with this DPPM
				collRecipients = this.listAppliedByDppm(p.getId());
				for (AclRecipient aclRecipient : collRecipients) {
					// Generate a list of ACL to notify
					tmpAcl.add(aclBO.edit(aclRecipient.getAcl().getId()));
				}
				// Finally runs the notify method
				// this.notifyChangedAcl(p.getId(), tmpAcl);
				aclBO.setAcl(tmpAcl, p, user);
				tmpAcl = null;
			}
		} catch (ExceptionSys e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This methods saves a list of AclRecipient. In order to do this, it must
	 * delete all old records related with the DPPM
	 * 
	 * @param aclRecipientList
	 * @throws ExceptionSys
	 */
	public void saveAll(List<AclRecipient> aclRecipientList) throws ExceptionSys {
		// Get the dppm id to delete old data from table
		if (null != aclRecipientList && !aclRecipientList.isEmpty()) {
			AclRecipient aclR = aclRecipientList.get(0);
			// get the collection with all ACLs from this DPPM;
			Collection<AclRecipient> mustBeDeletedList = listAppliedByDppm(aclR.getDppmId());
			// delete old data from database
			for (AclRecipient aclRecipientToDelete : mustBeDeletedList) {
				this.delete(aclRecipientToDelete.getId());
			}
		}
		// add new data
		for (AclRecipient aclRecipient : aclRecipientList) {
			this.save(aclRecipient);
		}
	}

	public void deleteAllFromDppm(String id) throws ExceptionSys {
		Session sessionHibernate = null;
		Transaction trans = null;
		int rowCount = 0;
		try {
			String hql = "delete from AclRecipient WHERE dppm_id = :dppmid";
			sessionHibernate = HibernateUtil.currentSession();
			trans = sessionHibernate.beginTransaction();
			Query query = sessionHibernate.createQuery(hql);
			query.setString("dppmid", id);
			rowCount = query.executeUpdate();
			trans.commit();
			if (rowCount == 0) {
				throw new ExceptionSys("No AclRecipient could be deleted for dppm: " + id);
			}
		} catch (HibernateException he) {
			trans.rollback();
		} finally {
			HibernateUtil.closeSession();
		}
	}

}
