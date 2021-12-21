package br.com.alcatellucent.csm.utils.comm;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.utils.AgyaDaemonManager;

/**
 * This Class represents the ACL Counter Servlet, responsible to load the
 * configuration values for the ACL Counter Service-Thread
 * 
 * @author Alisson Leite de Morais Veras
 *         <Alisson_Leite_De_Morais.Veras@alcatel-lucent.com.br>
 * @version 1.0 <BR>
 *          Copyright &copy; 2006-2007 Alcatel-Lucent. All rights reserved.
 */
public class DeviceACLCountServlet extends DeviceBasicServlet {

	private static final long serialVersionUID = 9031586477106103389L;

	DeviceACLCountServletListener _daemonACLCountListener = null;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public DeviceACLCountServlet() {
		_logger = Logger.getLogger(DeviceACLCountServlet.class);
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
			throw new ServletException("Polling Agent initialization failed", e);
		}
		AgyaDaemonManager agyaManager = AgyaDaemonManager.getInstance();
		_daemonACLCountListener = (DeviceACLCountServletListener) getServletContext().getAttribute(
				DeviceACLCountServletListener.MY_NAME);
		agyaManager.setDeviceAclPoller(_daemonACLCountListener);
		_logger.debug("LoopTime set to " + getPollingPeriod());
		_daemonACLCountListener.setLoopTime(getPollingPeriod());
		_logger.debug("ConnectionTimeOut set to " + getConnectionTimeOut());
		_daemonACLCountListener.setConnectionTimeOut(getConnectionTimeOut());
	}

}