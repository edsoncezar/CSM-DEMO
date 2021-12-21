package br.com.alcatellucent.csm.util.persistence.interfaces;

import java.io.Serializable;
import java.util.Collection;

import br.com.alcatellucent.csm.beans.Context;
import br.com.alcatellucent.csm.exception.ExceptionSys;


public interface ContextDao<T> {
	public Serializable save(T obj) throws ExceptionSys;
	public void update(T obj) throws ExceptionSys;
	public Collection<Context> findAll() throws ExceptionSys; 
	public Context findById(String id) throws ExceptionSys;
	public void delete(T obj) throws ExceptionSys;
}
