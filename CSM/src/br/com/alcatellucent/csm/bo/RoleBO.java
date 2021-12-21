package br.com.alcatellucent.csm.bo;

import java.util.Collection;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.alcatellucent.csm.beans.Acl;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;

public class RoleBO {

	private static final Logger logger = Logger.getLogger(RoleBO.class);

	private transient final HbCommonDAO<Role> roleDao;

	public RoleBO() {
		logger.debug("Carregando Roles..");
		roleDao = new HbCommonDAO<Role>(Role.class);
	}

	@SuppressWarnings("unchecked")
	public Collection<Role> list() throws ExceptionSys {
		Session sessionHibernate = null;
		Collection<Role> coll = null;
		try {
			String hql = "from Role where name not in('" + Role.ROOT_ADMIN_ROLE + "','" + Role.MASTER_ROLE + "')";
			sessionHibernate = HibernateUtil.currentSession();
			Query query = sessionHibernate.createQuery(hql);
			coll = query.list();
		} finally {
			HibernateUtil.closeSession();
		}

		return coll;
	}

	@SuppressWarnings("unchecked")
	public Collection<Role> list(Boolean all) throws ExceptionSys {
		Collection<Role> retorno = null;
		if (all) {
			retorno = roleDao.findAll();
		} else {
			retorno = list();
		}
		return retorno;
	}

	public Role findById(final String roleId) throws ExceptionSys {
		return (Role) roleDao.findById(roleId);
	}

}
