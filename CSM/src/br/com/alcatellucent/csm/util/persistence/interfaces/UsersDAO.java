package br.com.alcatellucent.csm.util.persistence.interfaces;

import java.io.Serializable;
import java.util.Collection;

import br.com.alcatellucent.csm.beans.Users;
import br.com.alcatellucent.csm.exception.ExceptionSys;


public interface UsersDAO<T> {
	public Serializable save(T users) throws ExceptionSys;
	public void update(T obj) throws ExceptionSys;
	public Collection<T> findAll() throws ExceptionSys; 
	public Users findById(Long id) throws ExceptionSys;
	public void delete(T users) throws ExceptionSys;
}
