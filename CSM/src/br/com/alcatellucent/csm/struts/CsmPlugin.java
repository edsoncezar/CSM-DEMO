package br.com.alcatellucent.csm.struts;

import java.io.File;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.config.PlugInConfig;

import br.com.alcatellucent.csm.bo.RoleBO;
import br.com.alcatellucent.csm.facade.ContextFacade;
import br.com.alcatellucent.csm.facade.DeviceFacade;
import br.com.alcatellucent.csm.facade.UserFacade;
import br.com.alcatellucent.csm.facade.UserProfileFacade;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;

/**
 * Plugin do Struts responsável pela inicialização do sistema
 * 
 */
public class CsmPlugin implements PlugIn {

	private static final Logger logger = Logger.getLogger(CsmPlugin.class);

	private UserFacade userFacade = null;
	private ContextFacade contextFacade = null;
	private DeviceFacade deviceFacade = null;
	private UserProfileFacade userProfileFacade = null;
	private RoleBO roleBO = null;

	private static boolean systemRunning = false;

	public void init(ActionServlet actionServlet, ModuleConfig moduleConfig) throws ServletException {

		// // Recuperar o caminho do arquivo de configuração
		// PlugInConfig[] configs = moduleConfig.findPlugInConfigs();
		// String configPath = null;
		// for (int i = 0; i < configs.length; i++) {
		// Map properties = configs[i].getProperties();
		//
		// if (properties.containsKey("configPath")) {
		// configPath = properties.get("configPath").toString();
		// break;
		// }
		// }
		//
		// // Test if path is present.
		// if (configPath == null) {
		// throw new ServletException(
		// "Configuration file 'config-path' parameter is missing. Please, check
		// struts-config.xml");
		// }
		//
		// // Initialize logging system.
		// String logConfigFile = configPath + File.separator +
		// "log4j.properties";
		//
		// System.out.println("path " + logConfigFile);
		//
		// if ((new File(logConfigFile)).exists() == false) {
		// throw new ServletException("Log configuration file does not exist: " +
		// logConfigFile);
		// }

		logger.debug("System Initialization... ");

		// if ((new File(logConfigFile)).exists() == false) {
		// System.out.println("Log configuration file does not exist: " +
		// logConfigFile);
		// return;
		// }
		// PropertyConfigurator.configure(logConfigFile);

		logger.info("**************************************************");
		logger.info("*          Alcatel-Lucent Agya                   *");
		logger.info("*                                                *");
		logger.info("*  Start up time: " + new Date() + "   *");
		logger.info("**************************************************");

		ServletContext context = actionServlet.getServletContext();

		try {
			HibernateUtil.currentSession();

			logger.info("Create system entrance facade.");
			userFacade = new UserFacade();
			context.setAttribute("userFacade", userFacade);

			contextFacade = new ContextFacade();
			context.setAttribute("contextFacade", contextFacade);

			userProfileFacade = new UserProfileFacade();
			context.setAttribute("userProfileFacade", userProfileFacade);

			deviceFacade = new DeviceFacade();
			context.setAttribute("deviceFacade", deviceFacade);

			roleBO = new RoleBO();
			context.setAttribute("roleBO", roleBO);
			
			systemRunning  = true;

		} catch (Exception e) {
			logger.fatal(e.getMessage());
		} finally {
			HibernateUtil.closeSession();
		}
		
	}

	public void destroy() {
		HibernateUtil.closeSessionFactory();
	}

	/**
	 * @return the systemRunning
	 */
	public static boolean isSystemRunning() {
		return systemRunning;
	}
}
