package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.alcatellucent.csm.beans.Domain;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;

public class DomainBO {
	private static final Logger logger = Logger.getLogger(DomainBO.class);

	private HbCommonDAO<Domain> domainDao;

	public DomainBO() {
		logger.debug("Carregando DomainBO..");
		domainDao = new HbCommonDAO<Domain>(Domain.class);
	}

	@SuppressWarnings("unchecked")
	public void save(Domain domain) throws ExceptionSys {

		try {

			if (null != domain.getId() && !domain.getId().equals("")) {
				domainDao.update(domain);
				logger.info("Modify information of domain " + domain.getName() + " id: " + domain.getId());
			} else {
				domainDao.save(domain);
				logger.info("Save information of domain " + domain.getName() + " id: " + domain.getId());
			}
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void delete(String id) throws ExceptionSys {
		try {
			Domain domain = (Domain) domainDao.findById(id);
			domainDao.delete(domain);
			logger.info("Delete information of domain " + id);
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<Domain> list() throws ExceptionSys {
		Collection<Domain> listDomain = new ArrayList<Domain>();
		listDomain = domainDao.findAll();
		return listDomain;
	}

	public Domain edit(String id) throws ExceptionSys {
		Domain domain = (Domain) domainDao.findById(id);
		return domain;
	}

	@SuppressWarnings("unchecked")
	public List<Domain> getDomainsByContextId(String contextId) {
		Session session = null;
		List<Domain> listDomains = new ArrayList<Domain>();

		String hql = "from Domain where contextId ='" + contextId + "'";
		try {
			session = HibernateUtil.currentSession();
			Query query = session.createQuery(hql);

			listDomains = query.list();
		} finally {
			HibernateUtil.closeSession();
		}

		return listDomains;

	}

}
