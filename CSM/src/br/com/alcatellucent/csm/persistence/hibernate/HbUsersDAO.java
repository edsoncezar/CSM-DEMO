package br.com.alcatellucent.csm.persistence.hibernate;


import java.util.Collection;
import java.util.Enumeration;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import br.com.alcatellucent.csm.beans.Users;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;
import br.com.alcatellucent.csm.util.persistence.HbGenericDAO;
import br.com.alcatellucent.csm.util.persistence.interfaces.UsersDAO;


@SuppressWarnings("unchecked")
public class HbUsersDAO<T> extends HbGenericDAO implements UsersDAO{
	public Collection findAll() throws ExceptionSys {
		return HibernateUtil.currentSession().createCriteria(Users.class).list();
	}
	public Users findById(Long id ) throws ExceptionSys {
		return (Users) super.findByPrimaryKey(Users.class, id);
	}
	
	
	// Recebe uma hashtable (Properties) com os nomes dos campos e os valores de busca
	public Collection findByCriteria(Properties criterios) throws ExceptionSys{
		
		Criteria criteria = HibernateUtil.currentSession().createCriteria(Users.class);
		Enumeration campos = criterios.keys();
		int i = 0;
		Criterion[] criterions = new Criterion[criterios.size()];
		Order [] order = new Order[criterios.size()];
		while (campos.hasMoreElements()){
			String chave = (String) campos.nextElement();
			criterions[i] = Expression.eq(chave, criterios.getProperty(chave));
			order[i] = Order.asc(chave);
			i++;
			//criteria.add(Expression.like(chave, criterios.getProperty(chave)) );
		}
		return findCriteria(Users.class, criterions, order);
	}	
	
	@SuppressWarnings("unchecked")
    public Collection<T> findByLoginPassword(String login, String password) throws ExceptionSys {
        Users users = new Users();
       
        if ((login != null))  {
        	users.setMail(login);
                        
        }
        if ((password != null))  {
        	users.setPassword(password);            
        }
        
        
        return super.findCriteriaByEntity((T) users, null);
    }
	   
	
	
	
	
}


	