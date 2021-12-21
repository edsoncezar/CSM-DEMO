package br.com.alcatellucent.csm.quartz.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class for Servlet: BuildTreeServlet
 * 
 */
public class ServletChart extends javax.servlet.http.HttpServlet implements
	javax.servlet.Servlet {

    private static final long serialVersionUID = -4560895203160978996L;

    private final Logger logger = Logger.getLogger(this.getClass());

    /*
     * (non-Java-doc)
     * 
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public ServletChart() {
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

	PrintWriter out = response.getWriter();
	// String date = request.getParameter("date");

	// String[] colors = { "#000000", "#FCC6B6", "#FBE28E", "#E0DEFE",
	// "#DAF8B1", "#EADE8A", "#33FF00", "#330033", "#24C2FF",
	// "#FAD1F9", "#EED2CA", "#AC9999", "#968405", "#F1DCE6",
	// "#91D988", "#330033", "#D7F8C9", "#FFFF00", "#990033",
	// "#CC9933", "#FF3300", "#996633", "#336600", "#336699",
	// "#33CC00", "#99FF99", "#00CC99", "#00CC99", "#336600",
	// "#00FFCC", "#3333CC" };

	StringBuilder xml = new StringBuilder();
	try {

	    xml.append("<chart caption='' subcaption='Month' decimals='1' baseFontSize='11' isSliced='0' useSameSlantAngle='1' isHollow='0' labelDistance='5'>");

	    final Integer[] days = SchedulerMonth
		    .quantifyMonthActive("01/09/2007");

	    for (int i = 0; i < days.length; i++) {
		if (days[i] > 0)
		    xml.append("<set label='" + i + "' value='" + days[i]
			    + "'/>");
	    }

	    xml.append("   <styles>");
	    xml.append("     <definition>");
	    xml.append("       <style type='font' name='captionFont' size='15'/>");
	    xml.append("     </definition>");
	    xml.append("    <application>");
	    xml.append("      <apply toObject='CAPTION' styles='captionFont'/>");
	    xml.append("    </application>");
	    xml.append("   </styles>");
	    xml.append("</chart>");

	    // FIM
	} catch (Exception e) {
	    e.printStackTrace();
	    out.println("<font color=darkred face=verdana size=1><b>Error Scheduling</b></font><br/>");
	    out.println("<font color=darkred face=verdana  size=1><b>Problems Probably with CronExpression</b></font>");
	    logger.info(e);
	}
	response.setContentType("text/xml");
	// out.println(xml.toString());

	out.flush();
    }

    /*
     * (non-Java-doc)
     * 
     * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
     *      HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
    }

}