package br.com.alcatellucent.csm.util.persistence;

import java.io.Serializable;
import java.util.Collection;

import br.com.alcatellucent.csm.exception.ExceptionSys;




/**
 * Interface genérica para implementação do DAO
 * 
 * @author 
 *
 * @param <T>
 */
public interface DAOInterface<T> {
    
    public Serializable save(T entity) throws ExceptionSys;    
    public void update(T entity) throws ExceptionSys;    
    public void delete(T entity) throws ExceptionSys;    
    public void deleteByPrimaryKey(Class klass, Serializable pk) throws ExceptionSys;    
    public T findByPrimaryKey(Class<T> klass, Serializable pk) throws ExceptionSys;   
    public Collection findByQuery(String hql) throws ExceptionSys;    
    public Collection<T> findCriteriaByEntity(T entity , String[] fieldsOrdinance) throws ExceptionSys;        
    public Collection<T> findAll() throws ExceptionSys;    

}
