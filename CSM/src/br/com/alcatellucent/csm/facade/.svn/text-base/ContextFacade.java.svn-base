package br.com.alcatellucent.csm.facade;

import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.Context;
import br.com.alcatellucent.csm.bo.ContextBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;

public class ContextFacade {
	
	private static final Logger logger = Logger.getLogger(ContextFacade.class);
 	private ContextBO contextBO;

	public ContextFacade() {
	    	logger.debug("Carregando Context...");        
		contextBO = new  ContextBO();
	}
	
	public void delete(String id) throws ExceptionSys {
		contextBO.delete(id);
	}
	
	public void save(Context context) throws ExceptionSys {
		contextBO.save(context);
	}
	
	public Collection<Context> list() throws ExceptionSys {
		return contextBO.list();
	}
	
	public Context edit(String parentid) throws ExceptionSys {
		return contextBO.edit(parentid);
	}

}
