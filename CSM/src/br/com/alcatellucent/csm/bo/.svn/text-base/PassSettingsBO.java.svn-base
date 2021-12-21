package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.PassSettings;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;

/**
 * @author Igor Ivanoff Takats
 */
public class PassSettingsBO {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(PassSettingsBO.class);

	private HbCommonDAO<PassSettings> passSettingsDao = null;

	public PassSettingsBO() {
		logger.debug("Carregando PassSettingsBO..");
		passSettingsDao = new HbCommonDAO<PassSettings>(PassSettings.class);
	}

	@SuppressWarnings("unchecked")
	public void save(PassSettings passSettings) throws ExceptionSys {
		try {
			if (null == passSettings.getId() || passSettings.getId().equals("")) {
				passSettingsDao.save(passSettings);
				logger.info("Save information of PassSettings " + passSettings.getId());
			} else {
				passSettingsDao.update(passSettings);
				logger.info("Modify information of PassSettings " + passSettings.getId());
			}
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void delete(String id) throws ExceptionSys {
		try {
			PassSettings passSettings = (PassSettings) passSettingsDao.findById(id);
			passSettingsDao.delete(passSettings);
			logger.info("Delete information of PassSettings " + id);
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public PassSettings findFirst() throws ExceptionSys{
		
		Collection<PassSettings> listPassSettings = new ArrayList<PassSettings>();
		PassSettings passSettings = null;
		
		try {

			listPassSettings =  passSettingsDao.findAll();

			if (listPassSettings.size() > 1) {
				logger.fatal("Tabelas pass_settings possui mais de um registro");
				throw new ExceptionSys("Tabelas pass_settings possui mais de um registro");
			}
			
			for(PassSettings p : listPassSettings) {
				passSettings = p;
				break;
			}
			if (passSettings == null) {
				return null;
			}
		} catch (ExceptionSys es) {
			throw es;
		}

		return passSettings;
		
	}
	
	public PassSettings edit(String id) throws ExceptionSys {
		PassSettings passSettings = (PassSettings) passSettingsDao.findById(id);
		return passSettings;
	}
	
	public final void createNewSettings() throws ExceptionSys{
		
		PassSettings passSettings = new PassSettings();
		PassSettingsBO passSettingsBO = new PassSettingsBO();
		
		passSettings.setId(null);
		passSettings.setDescription("");
		passSettings.setEnforceChangingDays(0);
		passSettings.setEnforceChangingEnable(new Boolean(true));
		passSettings.setEnforceHistoryEnable(new Boolean(true));
		passSettings.setEnforceHistoryQty(5);
		passSettings.setFailedAttempts(3);
		passSettings.setFlagFailedAttempts(new Boolean(true));
		passSettings.setLevelSecurity(0);
		passSettings.setLockDurationEnable(new Boolean(true));
		passSettings.setLockDurationMin(1);
		passSettings.setMinimunLength(4);
		passSettings.setName("");
		
		try {
			passSettingsBO.save(passSettings);
		} catch (ExceptionSys e) {
			throw e;
		}
		
	}
	
}
