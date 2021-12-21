package br.com.alcatellucent.csm.utils.comm;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.bo.AclBO;
import br.com.alcatellucent.csm.bo.DeviceManagerBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.struts.CsmPlugin;

public class DeviceConfigManagerServletListener extends DeviceBasicServletListener {
	
	public static final String MY_NAME = "ConfigManagerRobot";

	AclBO aclBO = null;
	DeviceManagerBO asmBO = null;

	public DeviceConfigManagerServletListener() {
		_logger = Logger.getLogger(DeviceConfigManagerServletListener.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent contextEvent) {
		contextInitialized(contextEvent, MY_NAME);
	}

	protected void init() throws ServletException {

		while (!CsmPlugin.isSystemRunning()) {
			try {
				_logger.debug("Waiting for the CsmPlugin to start...");
				sleep(getInitTimeOut());
			} catch (InterruptedException e) {
				return;
			}
		}

		System.out.println("****************************************");
		System.out.println("* Init " + this.getClass().getName() + "*");
		System.out.println("****************************************");

		_logger.debug(MY_NAME + " : Starting...");
		// Instantiating Business Objects
		aclBO = new AclBO();
		asmBO = new DeviceManagerBO();
		setIsInitialized(true);
		_logger.debug(MY_NAME + " : Initialization completed");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (!_mustBeKilled) {
			try {
				doWork();
				sleep(getLoopTime());
			} catch (InterruptedException e) {
				_logger.info("Execution interrupted");
			} catch (ExceptionSys e) {
				_logger.error("ExceptionSys received from testCommunication", e);
			}
		}
		return;
	}

	@Override
	protected void doWork() throws ExceptionSys {
	}

}
