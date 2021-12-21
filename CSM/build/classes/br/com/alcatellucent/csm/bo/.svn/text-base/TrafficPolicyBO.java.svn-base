package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.alcatellucent.csm.beans.Context;
import br.com.alcatellucent.csm.beans.Device;
import br.com.alcatellucent.csm.beans.TrafficPolicy;
import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;

public class TrafficPolicyBO {

	private static final Logger logger = Logger.getLogger(TrafficPolicyBO.class);

	private HbCommonDAO<TrafficPolicy> trafficPolicyDao;

	public TrafficPolicyBO() {
		logger.debug("Carregando Traffic Policy..");
		trafficPolicyDao = new HbCommonDAO<TrafficPolicy>(TrafficPolicy.class);
	}

	@SuppressWarnings("unchecked")
	public Object save(TrafficPolicy trafficPolicy) throws ExceptionSys {

		try {
			if (trafficPolicy.getId() == "") {
				trafficPolicyDao.save(trafficPolicy);
				logger.info("Modify Traffic Policy .." + trafficPolicy.getName());
			} else {
				trafficPolicyDao.update(trafficPolicy);
				logger.info("Save Traffic Policy .." + trafficPolicy.getName());
			}
		} catch (ExceptionSys e) {
			e.printStackTrace();
			logger.info("Error Traffic Policy .." + e);
		}

		return trafficPolicyDao.getLastObject();
	}

	@SuppressWarnings("unchecked")
	public void delete(String trafficPolicyId) throws ExceptionSys {
		try {
			TrafficPolicy trafficPolicy = (TrafficPolicy) trafficPolicyDao.findById(trafficPolicyId);
			trafficPolicyDao.delete(trafficPolicy);
		} catch (ExceptionSys e) {
			e.printStackTrace();
			logger.info("Error delete Traffic Policy ");
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<TrafficPolicy> list() throws ExceptionSys {
		Collection<TrafficPolicy> listTrafficPolice = new ArrayList<TrafficPolicy>();
		listTrafficPolice = trafficPolicyDao.findAll();
		return listTrafficPolice;
	}

	public TrafficPolicy findById(String id) throws ExceptionSys {
		TrafficPolicy trafficPolice = (TrafficPolicy) trafficPolicyDao.findById(id);
		return trafficPolice;
	}

	@SuppressWarnings("unchecked")
	public Collection<UserProfile> list(String operatorId) throws ExceptionSys {

		Properties criterios = new Properties();
		criterios.put("id", operatorId);
		Collection coll = (Collection<UserProfile>) trafficPolicyDao.findByCriteria(TrafficPolicy.class, criterios);

		return coll;
	}

	@SuppressWarnings("unchecked")
	public Collection<TrafficPolicy> list(Context ctx) throws ExceptionSys {
		Properties criterios = new Properties();
		criterios.put("contextId", ctx.getId());
		Collection coll = (Collection<TrafficPolicy>) trafficPolicyDao.findByCriteria(TrafficPolicy.class, criterios);
		return coll;
	}

	@SuppressWarnings("unchecked")
	public Collection<TrafficPolicy> listTraffic(String ctx) throws ExceptionSys {
		Properties criterios = new Properties();
		criterios.put("contextId", ctx);
		Collection coll = (Collection<TrafficPolicy>) trafficPolicyDao.findByCriteria(TrafficPolicy.class, criterios);
		return coll;
	}

	public String findContextId(String deviceId) {

		String hql;
		String contextId = "";

		Session sessionHibernate = null;
		try {
			Collection<Device> col2 = null;
			hql = "select contextId from Device where id= '" + deviceId + "'";
			sessionHibernate = HibernateUtil.currentSession();
			Query query = sessionHibernate.createQuery(hql);
			col2 = query.list();
			Iterator iterator = col2.iterator();

			while (iterator.hasNext()) {
				contextId = (String) iterator.next();
			}

		} finally {
			HibernateUtil.closeSession();
		}

		return contextId;
	}

}
