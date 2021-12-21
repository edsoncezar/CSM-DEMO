package br.com.alcatellucent.csm.utils;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alcatellucent.csm.exception.ExceptionSys;

/**
 * Servlet implementation class for Servlet: DeviceWalkerServlet
 */
public class DeviceWalkerServlet extends javax.servlet.http.HttpServlet
	implements javax.servlet.Servlet {

    private static final long serialVersionUID = -951294042865100175L;
    DeviceWalkerServletListener _daemon = null;
    private static int _poolingPeriod = 0;
    private static int _connectionTimeOut = 0;

    /*
     * (non-Java-doc)
     * 
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public DeviceWalkerServlet() {
	super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
	super.init(config);
	getInitilizationParameters(config);
	AgyaDaemonManager agyaManager = AgyaDaemonManager.getInstance();
	_daemon = (DeviceWalkerServletListener) getServletContext()
		.getAttribute(DeviceWalkerServletListener.MY_NAME);
	agyaManager.setDeviceWalkerDaemon(_daemon);
	_daemon.setLoopTime(_poolingPeriod);
	_daemon.setConnectionTimeOut(_connectionTimeOut);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	if (!_daemon.isInitialized()) {
	    throw new ServletException(DeviceWalkerServletListener.MY_NAME
		    + " not initialized");
	}
	String deviceManagerID = req.getParameter("deviceManagerID");
	try {
	    _daemon.notifyChangedDeviceManager(deviceManagerID);
	} catch (ExceptionSys e) {
	    throw new ServletException("Problem when notifying daemon: "
		    + deviceManagerID, e);
	}
    }

    private void getInitilizationParameters(ServletConfig config)
	    throws ServletException {
	int value;
	try {
	    value = Integer.parseInt(config.getInitParameter("poolingPeriod"));
	    _poolingPeriod = value;
	    setPoolingPeriod(value);
	    value = Integer.parseInt(config
		    .getInitParameter("connectionTimeOut"));
	    setConnectionTimeOut(value);
	} catch (Exception e) {
	    throw new ServletException(
		    "Polling Agent initialization failed. Check initialization parameters"
			    + "\"pollingPeriod\" and \"pollingTimeOut\"", e);
	}
    }

    private void setConnectionTimeOut(int period) {
	if (period < 0) {
	    _connectionTimeOut = period * (-1);
	} else {
	    _connectionTimeOut = period;
	}
    }

    private static void setPoolingPeriod(int period) {
	if (period < 0) {
	    _poolingPeriod = period * (-1);
	} else {
	    _poolingPeriod = period;
	}
    }

}