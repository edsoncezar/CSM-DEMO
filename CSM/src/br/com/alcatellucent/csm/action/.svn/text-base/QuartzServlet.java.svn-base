
package br.com.alcatellucent.csm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import br.com.alcatellucent.csm.bo.SchedulerBO;

/**
 * Servlet implementation class for Servlet: BuildTreeServlet
 * 
 */
public class QuartzServlet extends javax.servlet.http.HttpServlet implements
	javax.servlet.Servlet {

    /**
     * 
     */
    private static final long serialVersionUID = -2566667798075798432L;

    private final Logger logger = Logger.getLogger(this.getClass());

    private final String MSG_CLOSE = "<br /><br /><input type=\"button\" class=\"submit\""
	    + " onclick=\"javascript:self.close();\" value=\"Close\" />";

    /*
     * (non-Java-doc)
     * 
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public QuartzServlet() {
	super();
    }

    //Igor 2007-11-23
    public void init() throws ServletException{

    	System.out.println("***********************************************************************");
    	System.out.println("**                   Loading QuartzServlet                           **");
    	System.out.println("***********************************************************************");
    	
    	Scheduler sched = null ;
    	SchedulerFactory factory = new StdSchedulerFactory();
    	
    	try {
    		sched = factory.getScheduler();
    		sched.start();
    		System.out.println("***********************************************************************");
        	System.out.println("**          QuartzServlet  successfully loaded                       **"); 
        	System.out.println("***********************************************************************");
    	} catch (Exception e) {
    		System.out.println("***********************************************************************");
        	System.out.println("** An error occurred while Loading QuartzServlet, please verify Logs.**"); 
        	System.out.println("***********************************************************************");
    		throw new ServletException(e);
    	}
    	
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
	String schedulerName = request.getParameter("id");
	String cronExpression = request.getParameter("cronExpression");

	out.println("<html><head>");
	out.println("<link href=\"css/portal.css\" rel=\"styleSheet\"");
	out.println(" type=\"text/css\"></link>");
	out.println("</head><body>");
	out.println("<br />");
	out.println("<fieldset align='center'>");
	out.println("<legend>Scheduler</legend>");
	out.println("<table width='100%'>");
	out.println("<tr><td>");

	// try {
	// Criando Job
	SchedulerFactory factory = new StdSchedulerFactory();
	JobDetail jobDetail = null;
	Scheduler sched = null ;
	CronTrigger trigger = null;
	
	// Igor: BUILD Variables
    jobDetail = new JobDetail(schedulerName, Scheduler.DEFAULT_GROUP, SchedulerBO.class);
	jobDetail.setGroup(schedulerName);
	jobDetail.getJobDataMap().put("id", schedulerName);
    
	// Try to Delete scheduler, if an erro occurs, it means that sch does not exist.
	try {
		sched = factory.getScheduler();
		sched.deleteJob(jobDetail.getName(), jobDetail.getGroup());
		//sched.shutdown();
	} catch (Exception e) {
		System.out.println("Erro: " + e.getMessage());
	}
	
	try {
	    
	    trigger = new CronTrigger(schedulerName, Scheduler.DEFAULT_GROUP,  cronExpression);

	    //sched.shutdown();
	    sched.start();
	    //sched.addJob(jobDetail, true);
	    sched.scheduleJob(jobDetail, trigger);

	    out.println("<b>Scheduler Started</b>");
	    out.println(MSG_CLOSE);
	    
	 // Igor 2007-10-08: FX#89 - Erro quando submetido job
	} catch (SchedulerException se) {

		out.println("<font color=darkred face=verdana size=1><b>Your Scheduler has errors.</b></font>");
		out.println("<font color=darkred face=verdana size=1><b>Please, correct before submit again. Message:</b></font><br/>");
 		out.println("<br><font color=darkred face=verdana size=1>" + se.getMessage()+"</font>");
	    logger.fatal(se);
		
	} catch (ParseException e) {
	    out
		    .println("<font color=darkred face=verdana size=1><b>Error starting Scheduler</b></font><br/>");
	    out
		    .println("<font color=darkred face=verdana size=1><b>Check your Cron Expression and try again.</b></font>");
	    logger.info("Error starting Scheduler", e);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Erro em shcedule Job: " + e.getMessage());
	}
	out.println("</td></tr>");
	out.println("</table>");
	out.println("</fieldset>");

	out
		.println("<script language='JavaScript'>window.opener.location.href='schedulerPanel.do?action=list'</script>");
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
}