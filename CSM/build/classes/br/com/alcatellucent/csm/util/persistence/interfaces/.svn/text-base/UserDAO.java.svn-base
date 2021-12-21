package br.com.alcatellucent.csm.util.persistence.interfaces;

import java.io.Serializable;
import java.util.Collection;
import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.exception.ExceptionSys;


public interface UserDAO<T> {
	public Serializable save(T user) throws ExceptionSys;
	public void update(T obj) throws ExceptionSys;
	public Collection<T> findAll() throws ExceptionSys; 
	public User findById(String id) throws ExceptionSys;
	public void delete(T user) throws ExceptionSys;
}
