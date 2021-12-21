package br.com.alcatellucent.csm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.UnableToInterruptJobException;
import org.quartz.impl.StdSchedulerFactory;

import br.com.alcatellucent.csm.beans.Scheduling;
import br.com.alcatellucent.csm.bo.QuartzBO;
import br.com.alcatellucent.csm.bo.SchedulingBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;
import br.com.alcatellucent.csm.logging.persistence.CSMLogging;
import br.com.alcatellucent.csm.logging.persistence.CsmLog;

/**
 * Servlet implementation class for Servlet: BuildTreeServlet
 * 
 */
public class QuartzServletDelete extends javax.servlet.http.HttpServlet
	implements javax.servlet.Servlet {
    static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(this.getClass());

    private final String MSG_CLOSE = "<br /><br /><input type=\"button\" class=\"submit\""
	    + " onclick=\"javascript:self.close();\" value=\"Close\" />";

    /*
     * (non-Java-doc)
     * 
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public QuartzServletDelete() {
	super();
    }

    /*
     * (non-Java-doc)
     * 
     * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	QuartzBO quartzBO = new QuartzBO();

	out.println("<html><head>");
	out.println("<link href=\"css/portal.css\" rel=\"styleSheet\"");
	out.println(" type=\"text/css\"></link>");
	out.println("</head><body>");
	out.println("<br />");
	out.println("<fieldset align='center'>");
	out.println("<legend>Scheduler</legend>");
	out.println("<table width='100%'>");
	out.println("<tr><td>");
	
	 Scheduling scheduling = null;;

	try {
	    String schedulingId = request.getParameter("id");
	    quartzBO.delJobDetails(schedulingId);
	    
	    
	    SchedulingBO schedulingBO = new SchedulingBO();
	    scheduling = (Scheduling) schedulingBO.findById(schedulingId);
//	    String trafficPolicyId = scheduling.getTrafficPolicyId();
	    schedulingBO.delete(scheduling);
	    
	    this.deleteJob(scheduling.getTrafficPolicyId(), Scheduler.DEFAULT_GROUP);
	    
	 // Igor - 2007-10-01 - Inclusão de Log da aplicação.
	    this.saveLog(request, "quartz.title.delete", "name = " + scheduling.getName(), CsmLogSeverity.LOW_SEVERITY.getValue());

	    out.println("<b>Scheduler Deleted</b>");
	    out.println(MSG_CLOSE);
	} catch (ExceptionSys e) {
		
		 // Igor - 2007-10-01 - Inclusão de Log da aplicação.
		
	    e.printStackTrace();
	    out
		    .println("<font color=darkred face=verdana size=1>Error Deleting Scheduler</b></font>");
	    logger.info("Error " + e);
	} catch (SchedulerException se) {
	    se.printStackTrace();
	    out
		    .println("<font color=darkred face=verdana size=1>Error Deleting Scheduler</b></font>");
	    logger.info("Error " + se);
	}   catch (Exception se) {
	    se.printStackTrace();
	    out
		    .println("<font color=darkred face=verdana size=1>Error Deleting Scheduler</b></font>");
	    logger.info("Error " + se);
	}
	out.println("</td></tr>");
	out.println("</table>");
	out.println("</fieldset>");
	out
		.println("<script language='JavaScript'>window.opener.location='schedulerPanel.do?action=list';</script>");
	out.println("</body></html>");

    }

    /*
     * (non-Java-doc)
     * 
     * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	super.doPost(request, response);
    }
    
    
    @SuppressWarnings("unused")
	private void deleteJob(String jobName, String groupName) throws SchedulerException, Exception {
    	
    	try {
    		SchedulerFactory sf = new StdSchedulerFactory();
    		Scheduler sch = sf.getScheduler();
    		sch.deleteJob(jobName, groupName);
    	}	catch (SchedulerException se) {
    			throw new SchedulerException("An error has ocurred when deleting a JOB " + se.getMessage());
    	}   catch (Exception e) {
    			throw e;
    	}
    	
    }
    
    
    protected void saveLog(HttpServletRequest request, String eventKey, String detailsKey, Integer severity) 
    			   throws ExceptionSys{
    	
    	CsmLog csmLog = new CsmLog();
    	
    	ResourceBundle resourceBundle = ResourceBundle.getBundle("CsmLog");
    	csmLog.setSeverity(severity);
    	
    	try {
        	csmLog.setEvent(resourceBundle.getString(eventKey));
    	} catch(MissingResourceException ee) {
    		csmLog.setEvent(eventKey);
    	}
    	
    	try {
    		csmLog.setDetails(resourceBundle.getString(detailsKey));
    	} catch(MissingResourceException ee) {
    		csmLog.setDetails(detailsKey);
    	}
    	
    	try {
    		new CSMLogging(request, csmLog).putLog();
    	} catch(ExceptionSys e) {
    		throw e;
    	}
    	
    	csmLog = null;
    	resourceBundle = null;
    	
    }
}