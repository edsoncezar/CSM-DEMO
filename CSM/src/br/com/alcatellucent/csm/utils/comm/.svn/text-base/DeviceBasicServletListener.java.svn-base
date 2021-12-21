package br.com.alcatellucent.csm.utils.comm;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.exception.ExceptionSys;

/**
 * This Class represents the Basic Servlet Listener. This class must be
 * inherited by Service-Thread classes
 * 
 * @author Alisson Leite de Morais Veras
 *         <Alisson_Leite_De_Morais.Veras@alcatel-lucent.com.br>
 * @version 1.0 <BR>
 *          Copyright &copy; 2006-2007 Alcatel-Lucent. All rights reserved.
 */
public abstract class DeviceBasicServletListener extends Thread implements ServletContextListener {

	/**
	 * Logger common to all Service-Threads
	 */
	protected Logger _logger = null;

	/**
	 * Service Name
	 */
	public String MY_NAME = "";

	/**
	 * Default value for the connection to the Remote System
	 */
	private int _connectionTimeOut = 5000;

	/**
	 * Default value for the wait-time in the doWork
	 */
	private int _loopTime = 60000;

	/**
	 * Default value for the initialization process
	 */
	private int _initTimeOut = 5000;

	/**
	 * Indicates the Service-Thread loop must be terminated
	 */
	protected boolean _mustBeKilled = false;

	/**
	 * Indicates the Service-Thread Resources were successfully allocated
	 */
	private boolean _isInitialized = false;

	/**
	 * @return the _loopTime
	 */
	public int getLoopTime() {
		return _loopTime;
	}

	/**
	 * @param loopTime
	 *           the loopTime to set
	 */
	public void setLoopTime(int loopTime) {
		this._loopTime = loopTime;
	}

	/**
	 * @return the _connectionTimeOut
	 */
	public int getConnectionTimeOut() {
		return _connectionTimeOut;
	}

	/**
	 * @return the connectionTimeOut
	 */
	public void setConnectionTimeOut(int timeOut) {
		this._connectionTimeOut = timeOut;
	}

	/**
	 * @return the initTimeOut
	 */
	public int getInitTimeOut() {
		return _initTimeOut;
	}

	/**
	 * @param initTimeOut
	 *           the initTimeOut to set
	 */
	public void setInitTimeOut(int initTimeOut) {
		this._initTimeOut = initTimeOut;
	}

	/**
	 * @return the isInitialized
	 */
	protected boolean isInitialized() {
		return _isInitialized;
	}

	/**
	 * Indicates that the Service-Thread was successfully initialized
	 * 
	 * @param initialized
	 */
	protected void setIsInitialized(boolean initialized) {
		_isInitialized = initialized;
	}

	/**
	 * Signalizes that the Service-Thread should be killed - execution ended
	 */
	public void killMe() {
		this._mustBeKilled = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent contextEvent, String myName) {
		contextEvent.getServletContext().setAttribute(myName, this);
		_logger.info(myName + " is starting...");
		MY_NAME = myName;
		this.start();
		_logger.info(myName + " is running...");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent contextEvent) {
		_logger.info(MY_NAME + " is about to be destroyed...");
		_mustBeKilled = true;
		this.interrupt(); // Interrupt sleep for the shutdown process
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	public abstract void run();

	/**
	 * Service-Thread initialization process
	 * 
	 * @throws ServletException
	 */
	protected abstract void init() throws ServletException;

	/**
	 * Service-Thread work
	 * 
	 * @throws ExceptionSys
	 */
	protected abstract void doWork() throws ExceptionSys;

}
