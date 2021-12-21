package br.com.alcatellucent.csm.utils.comm;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

/**
 * This Class represents the Basic Servlet containing the methods for the basic
 * configuration for all Service-Threads
 * 
 * @author Alisson Leite de Morais Veras
 *         <Alisson_Leite_De_Morais.Veras@alcatel-lucent.com.br>
 * @version 1.0 <BR>
 *          Copyright &copy; 2006-2007 Alcatel-Lucent. All rights reserved.
 */
public abstract class DeviceBasicServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	/**
	 * Logger common to all Service-Threads Servlet Configuration
	 */
	protected Logger _logger = null;

	/**
	 * Wait time for the thread, before call the {@code doWork()} method
	 */
	private static int _pollingPeriod = 0;
	/**
	 * Time-out for communication to the Remote System
	 */
	private static int _connectionTimeOut = 0;

	/**
	 * Return the time-out value
	 * 
	 * @return the _connectionTimeOut
	 */
	protected static int getConnectionTimeOut() {
		return _connectionTimeOut;
	}

	/**
	 * Set the Time-out for communicating to the Remote System
	 * 
	 * @param timeOut
	 */
	private void setConnectionTimeOut(int timeOut) {
		if (timeOut < 0) {
			_connectionTimeOut = timeOut * (-1);
		} else {
			_connectionTimeOut = timeOut;
		}
	}

	/**
	 * Return the Polling wait-time
	 * 
	 * @return the _pollingPeriod
	 */
	protected static int getPollingPeriod() {
		return _pollingPeriod;
	}

	/**
	 * Set the Polling wait-time
	 * 
	 * @param period
	 */
	private static void setPollingPeriod(int period) {
		if (period < 0) {
			_pollingPeriod = period * (-1);
		} else {
			_pollingPeriod = period;
		}
	}

	/**
	 * Set the initialization parameters that are common to all Service-Threads
	 * 
	 * @param config
	 *           {@code ServletConfig} containing the configuration parameters
	 * @throws ServletException
	 */
	protected void setInitilizationParameters(ServletConfig config) throws ServletException {
		int value;
		try {
			value = Integer.parseInt(config.getInitParameter("poolingPeriod"));
			setPollingPeriod(value);
			value = Integer.parseInt(config.getInitParameter("connectionTimeOut"));
			setConnectionTimeOut(value);
		} catch (Exception e) {
			throw new ServletException("Check initialization parameters \"pollingPeriod\" and \"pollingTimeOut\"", e);
		}
	}

}
