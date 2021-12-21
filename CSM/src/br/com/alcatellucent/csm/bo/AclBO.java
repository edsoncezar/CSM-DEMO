package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.alcatellucent.csm.action.CsmBaseAction;
import br.com.alcatellucent.csm.beans.Acl;
import br.com.alcatellucent.csm.beans.AclRecipient;
import br.com.alcatellucent.csm.beans.DeviceManager;
import br.com.alcatellucent.csm.beans.ProcessorPacket;
import br.com.alcatellucent.csm.beans.Protocol;
import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.cs.client.CsClientProxy;
import br.com.alcatellucent.csm.cs.client.CsClientProxyConnectionException;
import br.com.alcatellucent.csm.cs.client.CsClientProxyRuntimeException;
import br.com.alcatellucent.csm.cs.client.message.CSClientRemoteErrorException;
import br.com.alcatellucent.csm.cs.client.message.CSInvalidAnswerException;
import br.com.alcatellucent.csm.cs.client.message.CSParameterVar;
import br.com.alcatellucent.csm.cs.client.message.bo.CSAcl;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.AclForm;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;
import br.com.alcatellucent.csm.util.InvalidParameterException;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;
import br.com.alcatellucent.csm.utils.NumberManipulationUtil;

/**
 * @author Edson
 */
public class AclBO extends CsmBaseAction {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(AclBO.class);

	public static final int SHAPE_MIN_VALUE = 0;
	public static final int SHAPE_MAX_VALUE = 65535;

	private HbCommonDAO<Acl> aclDao = null;

	public AclBO() {
		logger.debug("Carregando AclBO..");
		aclDao = new HbCommonDAO<Acl>(Acl.class);
	}

	@SuppressWarnings("unchecked")
	public void save(Acl acl, User user) throws ExceptionSys, Exception {

		AclRecipientBO aclRecipientBO = new AclRecipientBO();

		try {
			if (null == acl.getId() || acl.getId().equals("")) {
				aclDao.save(acl);
				logger.info("Save information of ACL " + acl.getId());
			} else {
				aclDao.update(acl);
				logger.info("Modify information of ACL " + acl.getId());
				// aclRecipientBO.notifyChangedAcl(acl, user);
			}
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		} finally {
			try {
				aclRecipientBO.notifyChangedAcl(acl, user);
			} catch (ExceptionSys e) {
				throw e;
			} catch (Exception e) {
				throw new ExceptionSys(e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	// public void delete(String id, User user) throws ExceptionSys {
	public void delete(Acl acl, User user) throws ExceptionSys {

		AclRecipientBO aclRecipientBO = new AclRecipientBO();

		try {
			// acl = (Acl) aclDao.findById(id);
			aclDao.delete(acl);
			logger.info("Delete information of Acl " + acl.getId());
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		} finally {
			try {
				aclRecipientBO.notifyChangedAcl(acl, user);
			} catch (ExceptionSys e) {
				throw e;
			} catch (Exception e) {
				throw new ExceptionSys(e);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Deprecated
	public Collection<Acl> list(String contextId) throws ExceptionSys {

		Properties criterios = new Properties();
		criterios.put("contextId", contextId);
		criterios.put("alertId", "is null");
		Collection coll = (Collection<Acl>) aclDao.findByCriteria(Acl.class, criterios);

		return coll;
	}

	@SuppressWarnings("unchecked")
	public Collection<Acl> list(String contextId, Boolean isAldeAcl) throws ExceptionSys {
		Collection coll = null;
		try {
			Properties criterios = new Properties();
			criterios.put("contextId", contextId);
			criterios.put("isAldeAcl", isAldeAcl);
			coll = (Collection<Acl>) aclDao.findByCriteria(Acl.class, criterios);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coll;
	}

	@SuppressWarnings("unchecked")
	public Collection<Acl> list(String contextId, Boolean isAldeAcl, Integer status) throws ExceptionSys {
		Collection coll = null;
		try {
			Properties criterios = new Properties();
			criterios.put("isAldeAcl", isAldeAcl);
			criterios.put("status", status);
			if (!isAldeAcl) {
				criterios.put("contextId", contextId);
			}
			coll = (Collection<Acl>) aclDao.findByCriteria(Acl.class, criterios);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coll;
	}

	@SuppressWarnings("unchecked")
	public Collection<Acl> list() throws ExceptionSys {
		Collection listAclHistory = null;
		try {
			listAclHistory = aclDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listAclHistory;
	}

	@SuppressWarnings("unchecked")
	public Acl listAcl(String contextId) throws ExceptionSys {

		Properties criterios = new Properties();
		ProtocolBO pbo = new ProtocolBO();
		Protocol p = null;
		criterios.put("id", contextId);
		Collection coll = (Collection<Acl>) aclDao.findByCriteria(Acl.class, criterios);
		Acl acl = null;

		Iterator iter = coll.iterator();

		while (iter.hasNext()) {
			acl = (Acl) iter.next();
		}
		if (!acl.getProtocol().equals("ANY")) {
			p = pbo.edit(acl.getProtocol());
			acl.setProtocolInternalNumber(p.getInternalNumber());
		} else {
			acl.setProtocolInternalNumber(0);
		}
		return acl;
	}

	@SuppressWarnings("unchecked")
	public Collection<Acl> listAcl() throws ExceptionSys {
		Collection coll = (Collection<Acl>) aclDao.findAll();
		return coll;
	}

	public Acl edit(String id) throws ExceptionSys {
		Acl acl = (Acl) aclDao.findById(id);
		return acl;
	}

	@SuppressWarnings("unchecked")
	public Collection<Acl> findByCriteria(Properties criterios) throws ExceptionSys {
		return aclDao.findByCriteria(criterios);
	}

	/**
	 * Apply an ACL List to a {@code ProcessorPacket} dppm
	 * 
	 * @param aclList
	 *           ACL List to apply
	 * @param dppm
	 *           Processor Packet to be updated
	 * @throws ExceptionSys
	 *            If any problem
	 */
	@SuppressWarnings("unchecked")
	public void setAcl(List<Acl> aclList, ProcessorPacket dppm, User user) throws ExceptionSys, Exception {
		List<AclRecipient> aclRecipientList = new ArrayList<AclRecipient>();
		// getting the user form session
		// User user = new User();
		// user = (User)request.getSession().getAttribute("userSession");
		if (null == user) {
			logger.info("ERROR User not found in session... ");
			throw new ExceptionSys("User not found in session... ");
		}

		// Order the ACLlist by priority
		Collections.sort((List<Acl>) aclList, new Comparator() {
			public int compare(Object o1, Object o2) {
				Acl acl1 = (Acl) o1;
				Acl acl2 = (Acl) o2;
				return (acl1.getPriority().compareTo(acl2.getPriority()));

			}
		});

		AclRecipientBO aclRecipientBO = new AclRecipientBO();
		boolean status = false;
		try {
			// set the aclRecipient to persist
			AclRecipient aclRecipient;
			for (Acl acl : aclList) {
				aclRecipient = new AclRecipient();
				aclRecipient.setAcl(acl);
				aclRecipient.setAclNumber(acl.getPriority());
				aclRecipient.setApplied(status);
				aclRecipient.setDateApplied(new Date());
				aclRecipient.setDppmId(dppm.getId());
				aclRecipient.setUserId(user.getId());
				aclRecipientList.add(aclRecipient);
			}
			if (!aclRecipientList.isEmpty()) {
				aclRecipientBO.saveAll(aclRecipientList);
			} else {
				aclRecipientBO.deleteAllFromDppm(dppm.getId());
			}
			status = true;

		} catch (ExceptionSys ex) {
			logger.info("ERROR " + ex);
			throw ex;
		}
		// successfully saved then notify
		if (status) {
			try {
				aclRecipientBO.notifyChangedAcl(dppm.getId(), aclRecipientList);
			} catch (ExceptionSys ex) {
				logger.info("ERROR " + ex);
				throw ex;
			}
		}
	}

	public void appyAcl(String dppmId, List<AclRecipient> aclList, CSParameterVar newCounter) throws ExceptionSys {
		DeviceManagerBO dManagerBO = new DeviceManagerBO();
		ProcessorPacketBO dppmBO = new ProcessorPacketBO();
		ProcessorPacket dppm = dppmBO.edit(dppmId);
		DeviceManager asm = dManagerBO.findByDeviceId(dppm.getDevice().getId());
		CsClientProxy myProxy = new CsClientProxy(asm.getHost(), asm.getPort());
		ArrayList<CSParameterVar> paramList = new ArrayList<CSParameterVar>();
		CSAcl varAcl = null;
		Acl myAcl = null;
		int i = 0;
		for (i = 0; i < aclList.size(); i++) {
			varAcl = new CSAcl();
			myAcl = aclList.get(i).getAcl();
			// Adjust Threshold
			if (myAcl.getAction() == CSAcl.RULE_DROP) {
				myAcl.setThreshold(AclBO.SHAPE_MIN_VALUE);
			} else if (myAcl.getAction() == CSAcl.RULE_ACCEPT) {
				myAcl.setThreshold(AclBO.SHAPE_MAX_VALUE);
			} else {
				if (myAcl.getThreshold() > AclBO.SHAPE_MAX_VALUE) {
					myAcl.setThreshold(AclBO.SHAPE_MAX_VALUE);
				} else if (myAcl.getThreshold() < AclBO.SHAPE_MIN_VALUE) {
					myAcl.setThreshold(AclBO.SHAPE_MIN_VALUE);
				}
			}
			// TODO The Exception handling algorithm MUST be enhanced
			try {
				varAcl.addFilter(myAcl.getSourceIp(), myAcl.getSourceIpMask(), myAcl.getDestIp(), myAcl.getDestIpMask(),
						(int) myAcl.getSourcePort(),
						NumberManipulationUtil.getDecIntFromHexString(myAcl.getSourcePortMask()), (int) myAcl.getDestPort(),
						NumberManipulationUtil.getDecIntFromHexString(myAcl.getDestPortMask()), myAcl
								.getProtocolInternalNumber(), NumberManipulationUtil.getDecIntFromHexString(myAcl
								.getProtocolMask()), (int) myAcl.getAction(), NumberManipulationUtil
								.getDecIntFromHexString(myAcl.getActionMask()), myAcl.getThreshold(), NumberManipulationUtil
								.getDecIntFromHexString(myAcl.getThresholdMask()), myAcl.getPriority());
			} catch (NumberFormatException e) {
				throw new ExceptionSys(e);
			} catch (InvalidParameterException e) {
				throw new ExceptionSys(e);
			}
			paramList.add(varAcl);
		}
		ArrayList<CSParameterVar> counterList = new ArrayList<CSParameterVar>();
		;
		counterList.add(newCounter);
		// Add here code for individual ACL to be applied

		// try {
		// this.saveLog(request, "acl_action.title", "key = " + "",
		// CsmLogSeverity.LOW_SEVERITY.getValue());
		// } catch (ExceptionSys e) {
		// throw e;
		// }
		// }
		try {
			myProxy.setAclList((int) dppm.getNumber(), paramList);
			myProxy.putParameter((int) dppm.getNumber(), counterList);
			// List<AclRecipient> aclRecipientList = new ArrayList<AclRecipient>();
			AclRecipientBO aclRecipientBO = new AclRecipientBO();

			for (AclRecipient aclRecipient : aclList) {
				aclRecipient.setApplied(true);
				aclRecipient.setDateApplied(new Date());
				aclRecipient.setDppmId(dppm.getId());
			}
			aclRecipientBO.saveAll(aclList);

			// Add here code to store log information for success. (Not
			// necessary?)
		} catch (CSInvalidAnswerException e) {
			this.saveAclLog(aclList, e.getMessage(), false, dppm);
			throw new ExceptionSys(e);
		} catch (CSClientRemoteErrorException e) {
			this.saveAclLog(aclList, e.getMessage(), false, dppm);
			throw new ExceptionSys(e);
		} catch (CsClientProxyConnectionException e) {
			this.saveAclLog(aclList, e.getMessage(), false, dppm);
			throw new ExceptionSys(e);
		} catch (CsClientProxyRuntimeException e) {
			this.saveAclLog(aclList, e.getMessage(), false, dppm);
			throw new ExceptionSys(e);
		} catch (InvalidParameterException e) {
			this.saveAclLog(aclList, e.getMessage(), false, dppm);
			throw new ExceptionSys(e);
		} finally {
			myProxy.disconnect();
		}
		this.saveAclLog(aclList, "Applied", true, dppm);

	}

	@SuppressWarnings("unchecked")
	public String findContextId(AclForm form) {
		String hql;
		String contextId = null;
		Session sessionHibernate = null;

		Collection<String> col2 = null;
		hql = "select contextId from Device where id in" + " (select device.id from ProcessorPacket where id= '"
				+ form.getAcl().getContextId() + "')";
		try {
			sessionHibernate = HibernateUtil.currentSession();
			Query query = sessionHibernate.createQuery(hql);
			col2 = query.list();
		} finally {
			HibernateUtil.closeSession();
		}
		Iterator iterator = col2.iterator();
		if (iterator.hasNext()) {
			contextId = (String) iterator.next();
		}
		return contextId;
	}

	@SuppressWarnings("unchecked")
	public Collection<Acl> findAclByCriteria(String contextId) {
		Session sessionHibernate = null;
		Collection<Acl> col2 = null;
		String hql = "from Acl where contextid = '" + contextId + "' and alert_id is null";
		try {
			sessionHibernate = HibernateUtil.currentSession();
			Query query = sessionHibernate.createQuery(hql);
			col2 = query.list();
		} finally {
			HibernateUtil.closeSession();
		}

		return col2;
	}

	public Acl findAclByAlertId(String id) {

		Session session = null;
		Acl acl = null;
		String hql = "from Acl where alert_id = '" + id + "'";
		try {
			session = HibernateUtil.currentSession();
			Query query = session.createQuery(hql);
			acl = (Acl) query.uniqueResult();
		} finally {
			HibernateUtil.closeSession();
		}

		if (null == acl) {
			acl = new Acl();
		}

		return acl;

	}

	private void saveAclLog(Collection<AclRecipient> aclList, String details, Boolean status, ProcessorPacket dev)
			throws ExceptionSys {

		// User user = (User) request.getSession().getAttribute("userSession");

		for (AclRecipient acl : aclList) {

			AclHistory aclHistory = new AclHistory();
			aclHistory.setAclId(acl.getId());
			aclHistory.setAlertId(acl.getAcl().getAlertId());
			// aclHistory.setCsmUserId(user.getId());
			aclHistory.setDateTime(Calendar.getInstance());
			aclHistory.setDetails(details);
			aclHistory.setDppmId(dev.getId());
			aclHistory.setDppmName(dev.getName());
			aclHistory.setStatusApplied(status);

			AclHistoryBO aclHistoryBO = new AclHistoryBO();

			try {
				aclHistoryBO.save(aclHistory);
			} catch (ExceptionSys es) {
				throw es;
			}

		}
	}
}
