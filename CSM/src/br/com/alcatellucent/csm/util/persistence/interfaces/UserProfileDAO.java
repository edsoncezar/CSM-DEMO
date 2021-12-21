package br.com.alcatellucent.csm.util.persistence.interfaces;


import java.io.Serializable;
import java.util.Collection;

import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.exception.ExceptionSys;


public interface UserProfileDAO<T> {
	public Serializable save(T userProfile) throws ExceptionSys;
	public void update(T obj) throws ExceptionSys;
	public Collection<T> findAll() throws ExceptionSys; 
	public UserProfile findById(String id) throws ExceptionSys;
	public void delete(T userProfile) throws ExceptionSys;
}
