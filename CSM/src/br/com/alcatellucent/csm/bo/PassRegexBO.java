package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.PassRegex;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;

/**
 * @author Igor Ivanoff Takats
 */
public class PassRegexBO {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(AclBO.class);

	private HbCommonDAO<PassRegex> passRegexDao = null;

	public PassRegexBO() {
		logger.debug("Carregando PassRegexBO..");
		passRegexDao = new HbCommonDAO<PassRegex>(PassRegex.class);
	}

	@SuppressWarnings("unchecked")
	public void save(PassRegex passRegex) throws ExceptionSys {
		try {
			if (null == passRegex.getId() || passRegex.getId().equals("")) {
				passRegexDao.save(passRegex);
				logger.info("Save information of PassRegex " + passRegex.getId());
			} else {
				passRegexDao.update(passRegex);
				logger.info("Modify information of PassRegex " + passRegex.getId());
			}
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void delete(String id) throws ExceptionSys {
		try {
			PassRegex passRegex = (PassRegex) passRegexDao.findById(id);
			passRegexDao.delete(passRegex);
			logger.info("Delete information of PassRegex " + id);
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	public PassRegex edit(String id) throws ExceptionSys {
		PassRegex passRegex = (PassRegex) passRegexDao.findById(id);
		return passRegex;
	}
	

    @SuppressWarnings("unchecked")
    public Collection<PassRegex> list() throws ExceptionSys {
    	Collection<PassRegex> listPassRegex = new ArrayList<PassRegex>();
    	listPassRegex = passRegexDao.findAll();
		return listPassRegex;
    }
}
