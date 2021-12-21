package br.com.alcatellucent.csm.util.persistence;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;

public abstract class HbGenericDAO<T> implements DAOInterface<T> {
	private Object lastObject;
//	private String erroFinalizandoSessao = "Erro ao Finalizar Sessão\n";

	public Serializable save(T obj) throws ExceptionSys {
		Transaction transaction = null;
		Session session = null;
		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();
			// session.evict(obj);
			Serializable ret = session.save(obj);
			// session.saveOrUpdate(obj);
			// Serializable ret = null;
			transaction.commit();
			setLastObject(obj);
			// session.evict(obj);
			return ret;
		} catch (HibernateException e) {
			try {
				transaction.rollback();
			} catch (Exception he) {
				throw new ExceptionSys(e);
			}
			throw new ExceptionSys(e);
		} finally {
			try {
				HibernateUtil.closeSession();
			} catch (HibernateException he) {
//				throw new ExceptionSys(erroFinalizandoSessao + he);
			}
		}
	}

	public void delete(T obj) throws ExceptionSys {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();
			session.delete(obj);
			transaction.commit();
		} catch (HibernateException e) {
			try {
				transaction.rollback();
			} catch (Exception he) {
				throw new ExceptionSys(e);
			}
			throw new ExceptionSys(e);
		} finally {
			try {
				HibernateUtil.closeSession();
			} catch (HibernateException he) {
//				throw new ExceptionSys(erroFinalizandoSessao + he);
			}
		}
	}

	public void update(T obj) throws ExceptionSys {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.currentSession();
			session.clear(); // / Hibernate Non-unique error;
			transaction = session.beginTransaction();
			session.update(obj);
			transaction.commit();
			setLastObject(obj);

		} catch (HibernateException e) {
			try {
				transaction.rollback();
			} catch (Exception he) {
				throw new ExceptionSys(e);
			}
			throw new ExceptionSys(e);
		} finally {
			try {
				HibernateUtil.closeSession();
			} catch (HibernateException he) {
//				throw new ExceptionSys(he);
			}
		}
	}

	public void deleteByPrimaryKey(Class xclass, Serializable pk) throws ExceptionSys {
		Object rObj = null;
		int delRow = 0;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();
			rObj = session.get(xclass, pk);
			if (rObj != null) {
				session.delete(rObj);
				delRow++;
			}
			transaction.commit();
		} catch (HibernateException e) {
			try {
				transaction.rollback();
			} catch (Exception he) {
				throw new ExceptionSys(e);
			}
			throw new ExceptionSys(e);
		} finally {
			try {
				HibernateUtil.closeSession();
			} catch (HibernateException he) {
//				throw new ExceptionSys(erroFinalizandoSessao + he);
			}
		}
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public T findByPrimaryKey(Class<T> xclass, Serializable pk) throws ExceptionSys {
		Object rObj;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.currentSession();
			transaction = session.beginTransaction();
			rObj = session.get(xclass, pk);
			transaction.commit();
			session.evict(rObj);
		} catch (HibernateException e) {
			try {
				transaction.rollback();
			} catch (Exception he) {
				throw new ExceptionSys(e);
			}
			throw new ExceptionSys(e);
		} finally {
			try {
				HibernateUtil.closeSession();
			} catch (HibernateException he) {
//				throw new ExceptionSys(erroFinalizandoSessao + he);
			}
		}
		return (T) rObj;
	}

	/**
	 * @param hql
	 * @return Collection
	 * @throws DatabaseException
	 */
	public Collection findByQuery(String hql) throws ExceptionSys {
		Collection col = null;
		Session session = null;
		try {
			session = HibernateUtil.currentSession();
			Query query = session.createQuery(hql);
			col = query.list();
		} catch (HibernateException he) {
			throw new ExceptionSys("Erro ao executar getByQuery(" + hql + ") \n" + he.getMessage());
		} finally {
			try {
				HibernateUtil.closeSession();
			} catch (Exception he) {
//				throw new ExceptionSys(he);
			}
		}
		return col;
	}

	@SuppressWarnings("unchecked")
	public Collection<T> findCriteria(Class klass, Criterion[] criterion, Order[] order) throws ExceptionSys {
		Session session = null;
		Collection<T> list = null;
		try {
			session = HibernateUtil.currentSession();
			Criteria criteria = session.createCriteria(klass);
			for (Criterion crit : criterion) {
				criteria.add(crit);
			}
			for (Order ord : order) {
				criteria.addOrder(ord);
			}
			list = criteria.list();
		} catch (Exception e) {
			throw new ExceptionSys("findCriteria executing error", e);
		} finally {
			try {
				HibernateUtil.closeSession();
			} catch (Exception e1) {
//				throw new ExceptionSys("Hibernate session closing error", e1);
			}
		}
		return list;

	}

	/**
	 * @param object
	 * @param fieldsOrdinance
	 * @return Collection
	 * @throws DatabaseException
	 */
	@SuppressWarnings("unchecked")
	public Collection<T> findCriteriaByEntity(T object, String[] fieldsOrdinance) throws ExceptionSys {

		Session session = null;
		Collection<T> list = null;

		try {
			session = HibernateUtil.currentSession();
			Criteria criteria = mountDynaCriteria(session, object);
			if (fieldsOrdinance != null) {
				for (int i = 0; i < fieldsOrdinance.length; i++) {
					criteria.addOrder(Order.asc(fieldsOrdinance[i]));
				}
			}
			list = criteria.list();
		} catch (Exception e) {
			throw new ExceptionSys("findCriteria executing error", e);
		} finally {
			try {
				HibernateUtil.closeSession();
			} catch (HibernateException e1) {
//				throw new ExceptionSys("Hibernate session closing error", e1);
			}
		}
		return list;
	}

	/**
	 * Método auxiliar p/ montar um Criteria
	 * 
	 * @param session
	 * @param object
	 * @return Criteria
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private Criteria mountDynaCriteria(Session session, Object object) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Class klass = object.getClass();
		Method[] m = klass.getMethods();

		Criteria criteria = session.createCriteria(klass);

		for (int i = 0; i < m.length; i++) {
			String nameMethod = m[i].getName();
			if (nameMethod.startsWith("get", 0)) {
				String initName = nameMethod.substring(3, 4).toLowerCase();
				String nameField = initName + nameMethod.substring(4);
				if (m[i].invoke(object) != null && !nameField.equals("class")) {
					criteria.add(Expression.eq(nameField, m[i].invoke(object)));
				}
			}
		}
		return criteria;
	}

	/*
	 * Método que interfaceia a chamada a findCriteria. O cliente não só passa a
	 * classe e uma lista de atributos e valores de pesquisa.
	 */
	public Collection findByCriteria(Class klass, Properties criterios) throws ExceptionSys {
//		Criteria criteria = HibernateUtil.currentSession().createCriteria(klass);
		Enumeration campos = criterios.keys();
		int i = 0;
		Criterion[] criterions = new Criterion[criterios.size()];
		Order[] order = new Order[criterios.size()];
		while (campos.hasMoreElements()) {
			String chave = (String) campos.nextElement();
			criterions[i] = Expression.eq(chave, criterios.get(chave));
			order[i] = Order.asc(chave);
			i++;
			// criteria.add(Expression.like(chave, criterios.getProperty(chave)) );
		}

		return findCriteria(klass, criterions, order);
	}

	/**
	 * @return the lastObject
	 */
	public Object getLastObject() {
		return lastObject;
	}

	/**
	 * @param lastObject
	 *           the lastObject to set
	 */
	public void setLastObject(Object lastObject) {
		this.lastObject = lastObject;
	}

}
