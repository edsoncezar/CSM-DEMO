package br.com.alcatellucent.csm.util.persistence.interfaces;

import java.io.Serializable;
import java.util.Collection;

import br.com.alcatellucent.csm.exception.ExceptionSys;

public interface CommonDAO<T> {
    public Serializable save(T object) throws ExceptionSys;

    public void update(T obj) throws ExceptionSys;

    public Collection<T> findAll() throws ExceptionSys;

    public Object findById(String id) throws ExceptionSys;

    public void delete(T object) throws ExceptionSys;
}
