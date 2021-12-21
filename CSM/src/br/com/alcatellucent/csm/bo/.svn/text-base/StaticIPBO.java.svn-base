package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.alcatellucent.csm.beans.StaticIP;
import br.com.alcatellucent.csm.cs.client.CsClientProxy;
import br.com.alcatellucent.csm.cs.client.CsClientProxyConnectionException;
import br.com.alcatellucent.csm.cs.client.CsClientProxyRuntimeException;
import br.com.alcatellucent.csm.cs.client.message.CSClientRemoteErrorException;
import br.com.alcatellucent.csm.cs.client.message.CSInvalidAnswerException;
import br.com.alcatellucent.csm.cs.client.message.CSParameterCAM;
import br.com.alcatellucent.csm.cs.client.message.bo.CSProfile;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;
import br.com.alcatellucent.csm.util.InvalidParameterException;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;

public class StaticIPBO {

	private static final Logger logger = Logger.getLogger(StaticIPBO.class);

	private HbCommonDAO<StaticIP> staticIPDao;

	public StaticIPBO() {
		logger.debug("Carregando StaticIPBO..");
		staticIPDao = new HbCommonDAO<StaticIP>(StaticIP.class);
	}

	@SuppressWarnings("unchecked")
	public void save(StaticIP staticIP) throws ExceptionSys {

		try {
			if (null != staticIP.getId() && !staticIP.getId().equals("")) {
				staticIPDao.update(staticIP);
				logger.info("Modify information of staticIP " + staticIP.getName() + " id: " + staticIP.getId());
			} else {
				staticIPDao.save(staticIP);
				logger.info("Save information of staticIP " + staticIP.getName() + " id: " + staticIP.getId());
			}
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void delete(String id) throws ExceptionSys {
		try {
			StaticIP staticIP = (StaticIP) staticIPDao.findById(id);
			staticIPDao.delete(staticIP);
			logger.info("Delete information of staticIP " + id);
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<StaticIP> list() throws ExceptionSys {
		Collection<StaticIP> listStaticIP = new ArrayList<StaticIP>();
		listStaticIP = staticIPDao.findAll();
		return listStaticIP;
	}

	public StaticIP edit(String id) throws ExceptionSys {
		StaticIP staticIP = (StaticIP) staticIPDao.findById(id);
		return staticIP;
	}

	@SuppressWarnings("unchecked")
	public List<StaticIP> getStaticIPsByContextId(String contextId) {
		Session session = null;
		List<StaticIP> listStaticIPs = new ArrayList<StaticIP>();

		String hql = "from StaticIP where contextId ='" + contextId + "'";
		try {
			session = HibernateUtil.currentSession();
			Query query = session.createQuery(hql);

			listStaticIPs = query.list();
		} finally {
			HibernateUtil.closeSession();
		}
		return listStaticIPs;
	}

	// Apply static users filter
	public void Apply(List<StaticIP> ipList, String ipAddress, int dppmNumber, int port) {
		ArrayList<CSParameterCAM> profileList = new ArrayList<CSParameterCAM>();
		CsClientProxy proxy = null;
		CSProfile profile = null;
		// Set profile list from a Static IP list
		for (StaticIP ip : ipList) {
			profile = new CSProfile();
			profile.setSrcAddress(ip.getIP());
			profile.setSrcMask(ip.getMask());
			// Source Filter, ignore destination
			profile.setDestAddress("0.0.0.0");
			profile.setDestMask("0.0.0.0");
			// TODO Set correct policy ID when necessary
			profile.setPolId(0); // Hard-coded now, for compatibility reason
			// Store Static IP Information
			profileList.add(profile);
		}
		try {
			// Instantiating a new proxy object
			proxy = new CsClientProxy(ipAddress, port);
			proxy.setProfile(dppmNumber, profileList);
			profileList = null;
		} catch (CsClientProxyRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsClientProxyConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CSInvalidAnswerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CSClientRemoteErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
