package br.com.alcatellucent.csm.bo;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.alde.management.ALdeConfig;
import br.com.alcatellucent.csm.alde.management.CSMAuthConfig;
import br.com.alcatellucent.csm.alde.management.client.ALdeClientProxy;
import br.com.alcatellucent.csm.alde.management.client.ALdeClientProxyException;
import br.com.alcatellucent.csm.alde.management.client.ALdeDynamicLearningMode;
import br.com.alcatellucent.csm.alde.management.client.ALdeDynamicModeConfigRequest;
import br.com.alcatellucent.csm.alde.management.client.ALdeDynamicStatusRequest;
import br.com.alcatellucent.csm.alde.management.client.ALdeException;
import br.com.alcatellucent.csm.alde.management.client.ALdeLearningMode;
import br.com.alcatellucent.csm.alde.management.client.ALdeService;
import br.com.alcatellucent.csm.alde.management.client.ALdeStaticLearningMode;
import br.com.alcatellucent.csm.alde.management.client.ALdeStaticModeConfigRequest;
import br.com.alcatellucent.csm.alde.management.client.ALdeStaticStatusRequest;
import br.com.alcatellucent.csm.alde.management.client.ALdeStatusRequest;
import br.com.alcatellucent.csm.alde.management.client.DetectionEngineConfiguration;
import br.com.alcatellucent.csm.beans.Alde;
import br.com.alcatellucent.csm.beans.AldeConfiguration;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;
import br.com.alcatellucent.csm.util.StringArrayUtil;

/**
 * 
 * @author Edson Moreira Cezar
 * @date 08/10/2007
 * @version 1.0 - Business Object of ALDE
 * 
 */
public class AldeBO {

	private static final Logger logger = Logger.getLogger(AldeBO.class);

	private HbCommonDAO<Alde> aldeDao = null;

	public AldeBO() {
		logger.debug("Carregando AldeBO..");
		aldeDao = new HbCommonDAO<Alde>(Alde.class);
	}

	@SuppressWarnings("unchecked")
	public void save(Alde alde) throws ExceptionSys {
		try {
			alde.setId(alde.getId() == null ? "" : alde.getId());
			if (alde.getId().equalsIgnoreCase("")) {
				aldeDao.save(alde);
				logger.info("Save ALDE information: " + alde.getId());
			} else {
				aldeDao.update(alde);
				logger.info("Modify ALDE information: " + alde.getId());
			}
		} catch (Exception e) {
			logger.error("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	public Alde edit(String id) throws ExceptionSys {
		Alde alde = (Alde) aldeDao.findById(id);
		return alde;
	}

	@SuppressWarnings("unchecked")
	public void delete(Alde alde) throws ExceptionSys {
		AldeBO aldeBO = new AldeBO();
		try {
			alde = aldeBO.edit(alde.getId());
			AldeConfiguration aldeConfig = (AldeConfiguration) aldeBO.findConfigurationByAldeId(alde.getId());
			Set<AldeConfiguration> aldeList = new HashSet<AldeConfiguration>();
			aldeConfig.setAlde(alde);
			aldeList.add(aldeConfig);
			alde.setAldeConfig(aldeList);
			aldeDao.delete(alde);
			logger.info("Delete information of alde " + alde.getId());
		} catch (ExceptionSys e) {
			logger.error("ERROR " + e);
			throw e;
		}
	}

	/**
	 * Return all ALDE's from a specified context by its ID
	 * 
	 * @param contextId
	 *           Context id to search for ALDE's
	 * @param master
	 *           {@code true} to return Master ALDE's or {@code false} for Probe
	 *           ALDE's
	 * @return
	 * @throws ExceptionSys
	 */
	@SuppressWarnings("unchecked")
	public Collection<Alde> list(String contextId, Boolean master) throws ExceptionSys {
		Collection<Alde> listAlde = null;
		Properties criteria = new Properties();
		try {
			if (contextId != null) {
				criteria.put("contextId", contextId);
			} else {
				criteria.put("contextId", new ContextBO().findRoot());
			}
			criteria.put("master", master);
			// criteria.put("isMaster", master);
			listAlde = aldeDao.findByCriteria(Alde.class, criteria);
		} catch (ExceptionSys e) {
			logger.error("Error finding Alde by ContextId ..." + contextId + " error: " + e);
			throw e;
		}
		return listAlde;
	}

	public Collection<Alde> getAldesByContextId(String contextId) throws ExceptionSys {
		return this.list(contextId, false);
	}

	@SuppressWarnings("unchecked")
	public AldeConfiguration findConfigurationByAldeId(String aldeId) throws ExceptionSys {
		try {
			Properties criterios = new Properties();
			criterios.put("alde.id", aldeId);

			Collection cDeviceManager = (Collection<AldeConfiguration>) aldeDao.findByCriteria(AldeConfiguration.class,
					criterios);

			AldeConfiguration aldeConfigTemp = new AldeConfiguration();
			Iterator iterator = cDeviceManager.iterator();
			while (iterator.hasNext()) {
				AldeConfiguration aldeConfig = (AldeConfiguration) iterator.next();
				aldeConfigTemp = aldeConfig;
			}
			return aldeConfigTemp;
		} catch (ExceptionSys e) {
			logger.error("Error finding AldeConfiguration by ALDE ID ..." + e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public AldeConfiguration findAldeIdByAlert(String alde) throws ExceptionSys {
		try {
			Properties criterios = new Properties();
			criterios.put("identification", alde);

			Collection cDeviceManager = (Collection<AldeConfiguration>) aldeDao.findByCriteria(AldeConfiguration.class,
					criterios);

			AldeConfiguration aldeConfigTemp = new AldeConfiguration();
			Iterator iterator = cDeviceManager.iterator();
			while (iterator.hasNext()) {
				AldeConfiguration aldeConfig = (AldeConfiguration) iterator.next();
				aldeConfigTemp = aldeConfig;
			}
			return aldeConfigTemp;
		} catch (ExceptionSys e) {
			logger.error("Error finding AldeConfig by ALDE ..." + e);
			throw e;
		}
	}

	/*
	 * Incluido por Igor em 2007-set-05: findByAldeContext
	 */
	@SuppressWarnings("unchecked")
	public Collection<Alde> findByAldeContext(String contextId) throws ExceptionSys {

		try {
			Properties criterios = new Properties();
			criterios.put("contextId", contextId);
			Collection aldeList = (Collection<Alde>) aldeDao.findByCriteria(Alde.class, criterios);
			return aldeList;

		} catch (ExceptionSys e) {
			logger.error("Error finding Alde by Context ..." + e);
			throw e;
		}
	}

	/*
	 * Incluido por Fernando em 2007-out-30: findByAldeContext
	 */

	@SuppressWarnings("unchecked")
	/**
	 * This method returns a list of all AlDES registred in a context;
	 * 
	 * @param contextId
	 *           the id of the context.
	 * @param isMaster
	 *           the criteria of the search, if true it returns only the alde
	 *           configured as master config.
	 */
	public Collection<Alde> findByAldeContext(String contextId, Boolean isMaster) throws ExceptionSys {

		try {
			Properties criterios = new Properties();
			criterios.put("contextId", contextId);
			criterios.put("master", isMaster);
			Collection aldeList = (Collection<Alde>) aldeDao.findByCriteria(Alde.class, criterios);
			return aldeList;

		} catch (ExceptionSys e) {
			logger.error("Error finding Alde by Context ..." + e);
			throw e;
		}
	}

	/**
	 * Responsible Method for the test of connection with LDE
	 * 
	 * @param aldeCurrentBean
	 * @return
	 * @throws ExceptionSys
	 * @throws ALdeException
	 */
	public String applySettings(Alde aldeCurrentBean) throws ExceptionSys, ALdeException {

		String resposta = null;
		try {
			ALdeConfig alde = new ALdeConfig(aldeCurrentBean.getUserName(), aldeCurrentBean.getUserPass(), aldeCurrentBean
					.getHost());
			ALdeClientProxy aLdeClientProxy = new ALdeClientProxy(alde);
			DetectionEngineConfiguration aldeConfig = null;
			AldeConfiguration aldeConfigBean = null;
			Iterator<AldeConfiguration> iterator = aldeCurrentBean.getAldeConfig().iterator();
			// Today, we have only one configuration
			if (iterator.hasNext()) {
				aldeConfigBean = (AldeConfiguration) iterator.next();
			} else {
				throw new ExceptionSys("ALDE does not have configuration to apply - aldeConfigBean = null");
			}
			// TODO Implement device authorization
			CSMAuthConfig csAuthConfig = new CSMAuthConfig(Alde.DEFAULT_USERNAME, Alde.DEFAULT_PASSWORD, aldeConfigBean
					.getCsmip(), aldeConfigBean.getCsmport());

			if (Integer.valueOf(aldeConfigBean.getMode()) == AldeConfiguration.CONFIG_STATIC) {
				aldeConfig = new ALdeStaticLearningMode(aldeConfigBean.getAttacktolerance(), aldeConfigBean.getOldnet(),
						aldeConfigBean.getSafepollmode(), aldeConfigBean.getSample(), aldeConfigBean.getWaitalert(),
						csAuthConfig, aldeConfigBean.getIdentification(), aldeConfigBean.getBaselinetime(), aldeConfigBean
								.getCurrentbaseline(), aldeConfigBean.getOldbaseline(), aldeConfigBean.getFilter());
			} else if (Integer.valueOf(aldeConfigBean.getMode()) == AldeConfiguration.CONFIG_DYNAMIC) {
				aldeConfig = new ALdeDynamicLearningMode(aldeConfigBean.getAttacktolerance(), aldeConfigBean.getOldnet(),
						aldeConfigBean.getSafepollmode(), aldeConfigBean.getSample(), aldeConfigBean.getWaitalert(),
						csAuthConfig, aldeConfigBean.getIdentification(), aldeConfigBean.getBaselinetime(), aldeConfigBean
								.getCurrentbaseline(), aldeConfigBean.getOldbaseline(), aldeConfigBean.getAdjustmentmin(),
						aldeConfigBean.getAdjustmentmax(), aldeConfigBean.getFilter());
			} else {
				logger.error("Invalid configuration type: " + aldeConfigBean.getMode());
				throw new ExceptionSys("Invalid configuration type: " + aldeConfigBean.getMode());
			}
			resposta = aLdeClientProxy.setConfiguration(aldeConfig);
		} catch (ALdeException e) {
			throw e;
		} catch (ALdeClientProxyException e) {
			throw new ExceptionSys(e.getMessage(), e);
		} catch (Exception e) {
			throw new ExceptionSys(e.getMessage(), e);
		}
		return resposta;
	}

	public void savePlusConfig(Alde alde, AldeConfiguration aldeConfig) throws ExceptionSys {
		aldePlusConfigPackage(alde, aldeConfig);
		try {
			this.save(alde);
		} catch (ExceptionSys e) {
			logger.error("ERROR " + e);
			throw e;
		}
	}

	private void aldePlusConfigPackage(Alde alde, AldeConfiguration aldeConfig) {
		Set<AldeConfiguration> aldeList = null;
		if (alde.getMaster() == false) {
			if (aldeConfig.getId() != null && aldeConfig.getId().length() == 0) {
				aldeConfig.setId(null);
			}
			aldeList = new HashSet<AldeConfiguration>();
			aldeConfig.setAlde(alde);
			aldeList.add(aldeConfig);
			alde.setAldeConfig(aldeList);
		}
	}

	public static int getStaticStatus(Alde alde) throws ExceptionSys, ALdeClientProxyException, ALdeException {
		ALdeStaticStatusRequest statusRequest = new ALdeStaticStatusRequest();
		try {
			return getRunningStatus(alde, statusRequest);
		} catch (MalformedURLException e) {
			throw new ExceptionSys(e.getMessage(), e);
		}
	}

	public static int getDynamicStatus(Alde alde) throws ExceptionSys, ALdeClientProxyException, ALdeException {
		ALdeDynamicStatusRequest statusRequest = new ALdeDynamicStatusRequest();
		try {
			return getRunningStatus(alde, statusRequest);
		} catch (MalformedURLException e) {
			throw new ExceptionSys(e.getMessage(), e);
		}
	}

	public static int getRunningStatus(Alde alde) throws ExceptionSys, ALdeClientProxyException, ALdeException {
		try {
			return getRunningStatus(alde, null);
		} catch (MalformedURLException e) {
			throw new ExceptionSys(e.getMessage(), e);
		}
	}

	private static int getRunningStatus(Alde alde, ALdeStatusRequest request) throws ALdeClientProxyException,
			ALdeException, ExceptionSys, MalformedURLException {
		if (request == null) {
			request = new ALdeStatusRequest();
		}
		String aldeStatus = null;
		int statusId = 0;
		ALdeConfig aldeConfig = new ALdeConfig(alde.getUserName(), alde.getUserPass(), alde.getHost());
		ALdeClientProxy proxy = new ALdeClientProxy(aldeConfig);
		aldeStatus = proxy.getALdeStatus(request);
		statusId = StringArrayUtil.getIdByDescription(ALdeStatusRequest.NORMAL_RESPONSE, aldeStatus);
		if (statusId == -1) {
			// This should never happen, the proxy throws an ALdeException if
			// receives an Invalid Response
			throw new ExceptionSys("Invalid ALDE Response: " + aldeStatus);
		}
		return statusId;
	}

	public void resetLearning(Alde alde, AldeConfiguration aldeConfiguration) throws ExceptionSys {
		ALdeClientProxy proxy = null;
		aldePlusConfigPackage(alde, aldeConfiguration);
		ALdeService service = null;
		String proxyOut = null;
		try {
			switch (Integer.valueOf(aldeConfiguration.getMode())) {
			case AldeConfiguration.CONFIG_STATIC:
				service = new ALdeService(ALdeService.STATIC_RESTART_ID);
				break;
			case AldeConfiguration.CONFIG_DYNAMIC:
				service = new ALdeService(ALdeService.DYNAMIC_RESTART_ID);
				break;
			default:
				throw new ExceptionSys("Invalid ALDE Configuration Mode");
			}
			proxy = getProxy(alde);
			proxyOut = proxy.setStartStop(service);
			System.out.println(proxyOut);
		} catch (ALdeClientProxyException e) {
			throw new ExceptionSys("Proxy error", e);
		} catch (ALdeException e) {
			throw new ExceptionSys("ALDE Returned error: " + (proxyOut != null ? proxyOut : ""), e);
		}

	}

	public void startStop(Alde alde, AldeConfiguration aldeConfig, String mode, boolean b) throws ExceptionSys {
		String proxyOut = null;
		ALdeService service = null;
		if (mode.equalsIgnoreCase("static")) {
			if (b == true) {
				service = new ALdeService(ALdeService.STATIC_START_ID);
			} else {
				service = new ALdeService(ALdeService.STATIC_STOP_ID);
			}
		} else {
			if (b == true) {
				service = new ALdeService(ALdeService.DYNAMIC_START_ID);
			} else {
				service = new ALdeService(ALdeService.DYNAMIC_STOP_ID);
			}
		}
		try {
			ALdeClientProxy proxy = getProxy(alde);
			proxyOut = proxy.setStartStop(service);
		} catch (ALdeClientProxyException e) {
			throw new ExceptionSys("Proxy error", e);
		} catch (ALdeException e) {
			throw new ExceptionSys("ALDE Returned error: " + (proxyOut != null ? proxyOut : ""), e);
		}
	}

	private ALdeClientProxy getProxy(Alde alde) throws ALdeClientProxyException {
		ALdeClientProxy myProxy = null;
		ALdeConfig aldeConfig = null;

		aldeConfig = new ALdeConfig(alde.getUserName(), alde.getUserPass(), alde.getHost());
		myProxy = new ALdeClientProxy(aldeConfig);
		return myProxy;
	}

	public void loadConfig(Alde alde, AldeConfiguration aldeConfig) throws ExceptionSys {
		AldeConfiguration aldeConfigToShow = null;
		if (Integer.valueOf(aldeConfig.getStatus()) == AldeConfiguration.SETTINGS_REMOTE) {
			CSMAuthConfig csmAuthConfig = new CSMAuthConfig(Alde.DEFAULT_USERNAME, Alde.DEFAULT_PASSWORD, aldeConfig
					.getCsmip(), aldeConfig.getCsmport());

			DetectionEngineConfiguration remoteConfig = getRemoteConfig(alde, aldeConfig, csmAuthConfig, Integer
					.valueOf(aldeConfig.getMode()));
			aldeConfigToShow = new AldeConfiguration();
			populateConfigFromProxy(aldeConfigToShow, (ALdeLearningMode) remoteConfig);
			aldeConfigToShow.setStatus(String.valueOf(AldeConfiguration.SETTINGS_REMOTE));
			aldeConfigToShow.setMode(aldeConfig.getMode());
		} else {
			aldeConfigToShow = findConfigurationByAldeId(alde.getId());
		}
		aldePlusConfigPackage(alde, aldeConfigToShow);
	}

	private void populateConfigFromProxy(AldeConfiguration aldeConfigToShow, ALdeLearningMode remoteConfig) {
		aldeConfigToShow.setIdentification(remoteConfig.getIdentification());
		aldeConfigToShow.setAttacktolerance(remoteConfig.getAttackTolerance());
		aldeConfigToShow.setWaitalert(remoteConfig.getWaitAlert());
		aldeConfigToShow.setSample(remoteConfig.getSample());
		aldeConfigToShow.setOldnet(remoteConfig.getOldNet());
		aldeConfigToShow.setSafepollmode(remoteConfig.getSafeModePool());
		aldeConfigToShow.setOldbaseline(remoteConfig.getOldBaseLine());
		aldeConfigToShow.setCurrentbaseline(remoteConfig.getCurrentBaseLine());
		aldeConfigToShow.setBaselinetime(remoteConfig.getBaseLineTime());
		aldeConfigToShow.setCsmip(remoteConfig.getCsmAuthConfig().getIpAddress());
		aldeConfigToShow.setCsmport(remoteConfig.getCsmAuthConfig().getPort());
		if (remoteConfig instanceof ALdeDynamicLearningMode) {
			aldeConfigToShow.setAdjustmentmin(remoteConfig.getMinAdjustment());
			aldeConfigToShow.setAdjustmentmax(remoteConfig.getMaxAdjustment());
		}
		aldeConfigToShow.setFilter(remoteConfig.getFilter());
	}

	private DetectionEngineConfiguration getRemoteConfig(Alde alde, AldeConfiguration aldeConfig,
			CSMAuthConfig csmAuthConfig, int staticDynamic) throws ExceptionSys {
		ALdeClientProxy myProxy = null;
		String proxyOut = null;
		DetectionEngineConfiguration config;
		if (staticDynamic == AldeConfiguration.CONFIG_STATIC) {
			config = new ALdeStaticModeConfigRequest(csmAuthConfig, aldeConfig.getIdentification(), aldeConfig.getFilter());
		} else {
			config = new ALdeDynamicModeConfigRequest(csmAuthConfig, aldeConfig.getIdentification(), aldeConfig
					.getFilter());
		}
		try {
			myProxy = getProxy(alde);
			config = myProxy.getConfiguration(config);
		} catch (ALdeClientProxyException e) {
			throw new ExceptionSys("Proxy error", e);
		} catch (ALdeException e) {
			throw new ExceptionSys("ALDE Returned error: " + (proxyOut != null ? proxyOut : ""), e);
		}
		return config;
	}

}
