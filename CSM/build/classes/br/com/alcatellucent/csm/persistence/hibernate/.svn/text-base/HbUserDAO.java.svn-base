package br.com.alcatellucent.csm.persistence.hibernate;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;
import br.com.alcatellucent.csm.util.persistence.HbGenericDAO;
import br.com.alcatellucent.csm.util.persistence.interfaces.UserDAO;

@SuppressWarnings("unchecked")
public class HbUserDAO<T> extends HbGenericDAO implements UserDAO {
	public Collection findAll() throws ExceptionSys {
		Collection result = null;
		Session session = HibernateUtil.currentSession();
		result = session.createCriteria(User.class).list();
		return result;
		// return
		// HibernateUtil.currentSession().createCriteria(User.class).list();
	}

	public User findById(String id) throws ExceptionSys {
		return (User) super.findByPrimaryKey(User.class, id);
	}

	// Recebe uma hashtable (Properties) com os nomes dos campos e os valores de
	// busca
	public Collection findByCriteria(Properties criterios) throws ExceptionSys {

		// Criteria criteria =
		// HibernateUtil.currentSession().createCriteria(User.class);
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
		return findCriteria(User.class, criterions, order);
	}

	@SuppressWarnings("unchecked")
	public Collection<T> findByLoginPassword(String userName, String password) throws ExceptionSys {
		User user = new User();

		user.setUserName(userName);
		return super.findCriteriaByEntity((T) user, null);
	}

}
