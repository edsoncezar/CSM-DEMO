package br.com.alcatellucent.csm.utils.comm;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.utils.AgyaDaemonManager;

/**
 * This Class represents the Configuration Manager Servlet, responsible to load
 * the configuration values for the Configuration Manager Service-Thread
 * 
 * @author Alisson Leite de Morais Veras
 *         <Alisson_Leite_De_Morais.Veras@alcatel-lucent.com.br>
 * @version 1.0 <BR>
 *          Copyright &copy; 2006-2007 Alcatel-Lucent. All rights reserved.
 */
public class DeviceConfigManagerServlet extends DeviceBasicServlet {

	private static final long serialVersionUID = 882521961597302725L;

	DeviceConfigManagerServletListener _daemonConfManagerListener = null;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public DeviceConfigManagerServlet() {
		_logger = Logger.getLogger(DeviceConfigManagerServlet.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			super.init(config);
			super.setInitilizationParameters(config);
		} catch (ServletException e) {
			throw new ServletException("Configuration Manager initialization failed", e);
		}
		AgyaDaemonManager agyaManager = AgyaDaemonManager.getInstance();
		_daemonConfManagerListener = (DeviceConfigManagerServletListener) getServletContext().getAttribute(
				DeviceConfigManagerServletListener.MY_NAME);
		agyaManager.setDeviceConfigManager(_daemonConfManagerListener);
		_logger.debug("LoopTime set to " + getPollingPeriod());
		_daemonConfManagerListener.setLoopTime(getPollingPeriod());
		_logger.debug("ConnectionTimeOut set to " + getConnectionTimeOut());
		_daemonConfManagerListener.setConnectionTimeOut(getConnectionTimeOut());
	}

}