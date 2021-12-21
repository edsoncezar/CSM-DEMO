package br.com.alcatellucent.csm.logging.persistence;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;

public class CsmLogBO {
	
   private static final Logger logger = Logger.getLogger(CsmLogBO.class);

    private HbCommonDAO<CsmLog> csmLogDao;

    public CsmLogBO() {
	logger.debug("Carregando CsmLogDAO..");
	csmLogDao = new HbCommonDAO<CsmLog>(CsmLog.class);
    }
   
   @SuppressWarnings("unchecked")
   public Collection<CsmLog> list() throws ExceptionSys {
	   Collection<CsmLog> listCsmLog = new ArrayList<CsmLog>();		
	   listCsmLog  =  csmLogDao.findAll();
	   return listCsmLog;
   }
   
   @SuppressWarnings("unchecked")
   public Collection<CsmLog> findByQuery(String hql) throws ExceptionSys {
	   Collection<CsmLog> listCsmLog = new ArrayList<CsmLog>();		
	   listCsmLog  =  csmLogDao.findByQuery(hql);
	   return listCsmLog;
   }
   
   
   @SuppressWarnings("unchecked")
   public void save(CsmLog csmLog) throws ExceptionSys {   

       try {
    	  if(  !(csmLog.getId()== null)){
    		  csmLogDao.update(csmLog);
              logger.info("Modify information of CSMLog .. "+csmLog.getEvent());    		  
    	  }else{
    		  csmLogDao.save(csmLog);
    		  logger.info("Save information of CSMLog .. "+csmLog.getEvent());    		  
    	  }	
       }catch(Exception e){
    	   e.printStackTrace();
       	   logger.info("ERROR "+e);
           throw new ExceptionSys(e);
       }
   }
   
	public CsmLog edit(String id) throws ExceptionSys {
		CsmLog csmLog = (CsmLog) csmLogDao.findById(id);
		return csmLog;
	}

}
