package br.com.alcatellucent.csm.utils.comm;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.utils.AgyaDaemonManager;

/**
 * This Class represents the Device Walker Servlet, responsible to load the
 * configuration values for the DeviceWalker Service-Thread
 * 
 * @author Alisson Leite de Morais Veras
 *         <Alisson_Leite_De_Morais.Veras@alcatel-lucent.com.br>
 * @version 1.0 <BR>
 *          Copyright &copy; 2006-2007 Alcatel-Lucent. All rights reserved.
 */
public class DeviceWalkerServlet extends DeviceBasicServlet {

	private static final long serialVersionUID = -1015025861471669438L;

	DeviceWalkerServletListener _daemonWalkerListener = null;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public DeviceWalkerServlet() {
		_logger = Logger.getLogger(DeviceWalkerServlet.class);
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
		_daemonWalkerListener = (DeviceWalkerServletListener) getServletContext().getAttribute(
				DeviceWalkerServletListener.MY_NAME);
		agyaManager.setDeviceWalkerDaemon(_daemonWalkerListener);
		_logger.debug("LoopTime set to " + getPollingPeriod());
		_daemonWalkerListener.setLoopTime(getPollingPeriod());
		_logger.debug("ConnectionTimeOut set to " + getConnectionTimeOut());
		_daemonWalkerListener.setConnectionTimeOut(getConnectionTimeOut());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	// @Override
	// protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	// throws ServletException, IOException {
	// if (!_daemonWalkerListener.isInitialized()) {
	// throw new ServletException(_daemonWalkerListener.MY_NAME + " not
	// initialized");
	// }
	// String deviceManagerID = req.getParameter("deviceManagerID");
	// try {
	// _daemonWalkerListener.notifyChangedDeviceManager(deviceManagerID);
	// } catch (ExceptionSys e) {
	// throw new ServletException("Problem when notifying daemon: " +
	// deviceManagerID, e);
	// }
	// }
}