package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.Contact;
import br.com.alcatellucent.csm.beans.PassHistory;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;

/**
 * @author Igor Ivanoff Takats
 */
public class PassHistoryBO {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(AclBO.class);

	private HbCommonDAO<PassHistory> passHistoryDao = null;

	public PassHistoryBO() {
		logger.debug("Carregando PassHistoryBO..");
		passHistoryDao = new HbCommonDAO<PassHistory>(PassHistory.class);
	}

	@SuppressWarnings("unchecked")
	public void save(PassHistory passHistory) throws ExceptionSys {
		try {
			if (null == passHistory.getId() || passHistory.getId().equals("")) {
				passHistoryDao.save(passHistory);
				logger.info("Save information of PassHistory " + passHistory.getId());
			} else {
				passHistoryDao.update(passHistory);
				logger.info("Modify information of PassHistory " + passHistory.getId());
			}
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void delete(String id) throws ExceptionSys {
		try {
			PassHistory passHistory = (PassHistory) passHistoryDao.findById(id);
			passHistoryDao.delete(passHistory);
			logger.info("Delete information of PassHistory " + id);
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	public PassHistory edit(String id) throws ExceptionSys {
		PassHistory passHistory = (PassHistory) passHistoryDao.findById(id);
		return passHistory;
	}
	

    @SuppressWarnings("unchecked")
    public Collection<PassHistory> list() throws ExceptionSys {
    	Collection<PassHistory> listPassHistory = new ArrayList<PassHistory>();
    	listPassHistory = passHistoryDao.findAll();
		return listPassHistory;
    }
    
    @SuppressWarnings("unchecked")
	public Collection<PassHistory> findByCriteria(Properties criterios) throws ExceptionSys{
    	
    	Collection<PassHistory> listPassHistory = new ArrayList<PassHistory>();
    	
    	try {
    		listPassHistory = passHistoryDao.findByCriteria(criterios);
    	} catch (ExceptionSys es) {
    		throw es;
    	}
    	
    	return listPassHistory;
    }
    
    public void deleteByUserName(String userName) throws ExceptionSys {
    	try {
    	    Properties criterios = new Properties();
    	    criterios.put("userId", userName);

    	    Collection histories = (Collection<PassHistory>) passHistoryDao.findByCriteria(PassHistory.class, criterios);
    	    Iterator iterator = histories.iterator();
    	    while (iterator.hasNext()) {
    	    	PassHistory passHistory = (PassHistory) iterator.next();
    	    	passHistoryDao.delete(passHistory);
    	    }
    	    logger.info("Delete information of PassHistory " + userName);
    	} catch (ExceptionSys e) {
    	    logger.error("Error finding PassHistory by User Name ..." + e);
    	    throw new ExceptionSys(e);
    	}
	}
}
