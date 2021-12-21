package br.com.alcatellucent.csm.persistence.hibernate;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;
import br.com.alcatellucent.csm.util.persistence.HbGenericDAO;
import br.com.alcatellucent.csm.util.persistence.interfaces.UserProfileDAO;

@SuppressWarnings("unchecked")
public class HbUserProfileDAO<T> extends HbGenericDAO implements UserProfileDAO {
	public Collection findAll() throws ExceptionSys {
		Collection result = null;
		Session session = HibernateUtil.currentSession();
		result = session.createCriteria(UserProfile.class).list();
		return result;
	}

	public UserProfile findById(String id) throws ExceptionSys {
		return (UserProfile) super.findByPrimaryKey(UserProfile.class, id);
	}

	// Recebe uma hashtable (Properties) com os nomes dos campos e os valores de
	// busca
	public Collection findByCriteria(Properties criterios) throws ExceptionSys {

		// Criteria criteria =
		// HibernateUtil.currentSession().createCriteria(UserProfile.class);
		Enumeration campos = criterios.keys();
		int i = 0;
		Criterion[] criterions = new Criterion[criterios.size()];
		Order[] order = new Order[criterios.size()];
		while (campos.hasMoreElements()) {
			String chave = (String) campos.nextElement();
			criterions[i] = Expression.eq(chave, criterios.getProperty(chave));
			order[i] = Order.asc(chave);
			i++;
			// criteria.add(Expression.like(chave, criterios.getProperty(chave)) );
		}
		return findCriteria(UserProfile.class, criterions, order);
	}
}
