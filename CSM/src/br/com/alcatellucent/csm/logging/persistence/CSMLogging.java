package br.com.alcatellucent.csm.logging.persistence;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.exception.ExceptionSys;

/**
 * This class records log for all events occured in the CSM application.
 * 
 * @author Igor I. Takats
 * 
 * Date: 2007-ago-28
 * 
 */
public class CSMLogging {

    private static final Logger logger = Logger.getLogger(CSMLogging.class);

    private HttpServletRequest request;
    private CsmLog csmLog;

    /**
     * The class contructor
     */
    public CSMLogging() {
	super();
    }

    /**
     * The class constructor
     * 
     * @param request -
     *                the user request object
     * @param csmLog -
     *                the log object to be persisted.
     */
    public CSMLogging(HttpServletRequest request, CsmLog csmLog) {

	this.setCsmLog(csmLog);
	this.setRequest(request);

	this.getCsmLog().setIpClient(request.getRemoteAddr());
	this.getCsmLog().setIpServer(request.getLocalAddr());

	Date today = new java.util.Date();
	this.getCsmLog().setEventTime(new java.sql.Timestamp(today.getTime()));

	csmLog.setDateEvent(new java.sql.Date(System.currentTimeMillis()));

	HttpSession session = request.getSession();
	User user = (User) session.getAttribute("userSession");
	
	this.getCsmLog().setUser(user.getName());
	this.getCsmLog().setUserName(user.getUserName());
	this.getCsmLog().setUserId(user.getId());
    }

    /**
     * 
     */
    public void putLog() throws ExceptionSys {

	CsmLogBO csmLogBO = new CsmLogBO();

	try {
	    csmLogBO.save(csmLog);
	} catch (ExceptionSys eS) {
	    eS.printStackTrace();
	    logger.error("Erro: " + this.getClass().getName() + "\n" + eS);
	}

    }

    /**
     * Public method for the request object
     * 
     * 
     * @param request:
     *                the current request session object
     * @see javax.servlet.http.HttpServletRequest
     */
    public void setRequest(HttpServletRequest request) {
	this.request = request;
    }

    /**
     * Return the CSM Log object
     * 
     * @param request:
     *                the current request session object
     * @see javax.servlet.http.HttpServletRequest
     */
    private CsmLog getCsmLog() {
	return csmLog;
    }

    public void setCsmLog(CsmLog csmLog) {
	this.csmLog = csmLog;
    }

}
