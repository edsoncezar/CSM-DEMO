package br.com.alcatellucent.csm.persistence.hibernate;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;
import br.com.alcatellucent.csm.util.persistence.HbGenericDAO;
import br.com.alcatellucent.csm.util.persistence.interfaces.CommonDAO;

@SuppressWarnings("unchecked")
public class HbCommonDAO<T> extends HbGenericDAO implements CommonDAO {
    private Class klass;

    public HbCommonDAO(Class objClass) {
	klass = objClass;
    }

    public Collection findAll() throws ExceptionSys {
 		Collection result = null;
		Session session = HibernateUtil.currentSession();
		result = session.createCriteria(klass).list();
		return result;
		// return HibernateUtil.currentSession().createCriteria(klass).list();
    }

    public Object findById(String id) throws ExceptionSys {
	return (Object) super.findByPrimaryKey(klass, id);
    }

    // Recebe uma hashtable (Properties) com os nomes dos campos e os valores de
    // busca
    public Collection findByCriteria(Properties criterios) throws ExceptionSys {

//	Criteria criteria = HibernateUtil.currentSession()
//		.createCriteria(klass);
	Enumeration campos = criterios.keys();
	int i = 0;
	Criterion[] criterions = new Criterion[criterios.size()];
	Order[] order = new Order[criterios.size()];
	while (campos.hasMoreElements()) {
	    String chave = (String) campos.nextElement();
	    criterions[i] = Expression.eq(chave, criterios.getProperty(chave));
	    order[i] = Order.asc(chave);
	    i++;
	    // criteria.add(Expression.like(chave, criterios.getProperty(chave))
	    // );
	}
	return findCriteria(klass, criterions, order);
    }

    public Collection findByCriteria(Properties criterios, String chave, String sort) throws ExceptionSys {
    	
	Criterion[] criterions = new Criterion[criterios.size()];
	Order[] order = new Order[criterios.size()];

	criterions[0] = Expression.eq(chave, criterios.getProperty(chave));
	    
	if (sort.equals("asc")) {
	  	order[0] = Order.asc(chave);
	} else {
			order[0] = Order.desc(chave);
	}
	return findCriteria(klass, criterions, order);
    }

}
