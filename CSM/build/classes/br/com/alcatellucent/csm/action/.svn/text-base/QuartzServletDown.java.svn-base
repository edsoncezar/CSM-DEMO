package br.com.alcatellucent.csm.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.bo.QuartzBO;

/**
 * Servlet implementation class for Servlet: BuildTreeServlet
 * 
 */
public class QuartzServletDown extends javax.servlet.http.HttpServlet implements
	javax.servlet.Servlet {

    /**
     * 
     */
    private static final long serialVersionUID = 6470998124770224946L;

    private final Logger logger = Logger.getLogger(this.getClass());

    private final String MSG_CLOSE = "<br /><br /><input type=\"button\" class=\"submit\""
	    + " onclick=\"javascript:self.close();\" value=\"Close\" />";

    /*
     * (non-Java-doc)
     * 
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public QuartzServletDown() {
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

	try {
	    String schedulingId = request.getParameter("id");
	    quartzBO.delJobDetails(schedulingId);
	    out.println("<b>Scheduler Stopped</b>");
	    out.println(MSG_CLOSE);
	} catch (Exception e) {
	    e.printStackTrace();
	    out
		    .println("<font color=darkred face=verdana size=1>Error Stopping Scheduler</b></font>");
	    logger.info("Error " + e);
	}
	out.println("</td></tr>");
	out.println("</table>");
	out.println("</fieldset>");
	out
		.println("<script language='JavaScript'>window.opener.location.reload();</script>");
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