package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;

public class AclHistoryBO {

    private static final Logger logger = Logger.getLogger(AclHistoryBO.class);

    private HbCommonDAO<AclHistory> AclHistoryDAO;

    public AclHistoryBO() {
	logger.debug("Carregando AclHistoryDAO..");
	AclHistoryDAO = new HbCommonDAO<AclHistory>(AclHistory.class);
    }

    @SuppressWarnings("unchecked")
    public Collection<AclHistory> list() throws ExceptionSys {
	Collection<AclHistory> listAclHistory = new ArrayList<AclHistory>();
	listAclHistory = AclHistoryDAO.findAll();
	return listAclHistory;
    }
   
   
   public AclHistory edit(String id) throws ExceptionSys {
	   AclHistory AclHistory  =  (AclHistory)AclHistoryDAO.findById( id );	   	   
	   return AclHistory;
   }
   
   	@SuppressWarnings("unchecked")
   	public Collection<AclHistory> findByQuery(String hql)  throws ExceptionSys {
	   
   		Collection<AclHistory> listAclHistory = new ArrayList<AclHistory>();
   		
   		try {
   			listAclHistory  =  AclHistoryDAO.findByQuery(hql);
   		} catch (ExceptionSys e) {
   			throw new ExceptionSys("DAO Access Error " + e );
   		}

   		return listAclHistory;
   }
   	
    @SuppressWarnings("unchecked")
    public void save(AclHistory acl) throws ExceptionSys {

	try {
		
	    if (null == acl.getId() || acl.getId().equals("")) {
	    	AclHistoryDAO.save(acl);
	    } else {
	    	AclHistoryDAO.update(acl);
	    }
	    
	} catch (Exception e) {
	    logger.info("ERROR " + e);
	    throw new ExceptionSys(e);
	  }
    }
}
