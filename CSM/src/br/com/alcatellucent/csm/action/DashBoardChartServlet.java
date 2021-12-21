package br.com.alcatellucent.csm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.AldeAlertsHours;
import br.com.alcatellucent.csm.bo.DashBoardChartBO;

public class DashBoardChartServlet extends javax.servlet.http.HttpServlet
	implements javax.servlet.Servlet {

    /**
     * 
     */
    private static final long serialVersionUID = -7964366507172905604L;

    private final Logger logger = Logger.getLogger(this.getClass());

    /*
     * (non-Java-doc)
     * 
     * @see javax.servlet.http.HttpServlet#HttpServlet()
     */
    public DashBoardChartServlet() {
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
//	HashMap mSeriesName = new HashMap();

	String[] seriesName = { "Shaping", "Drop" };
	String[] seriesKeyName = { "1", "DROP" };

	DashBoardChartBO dashBoardChartBO = new DashBoardChartBO();

	StringBuilder xml = new StringBuilder();

	try {
	    HashMap<String, AldeAlertsHours> mLast24Hours = dashBoardChartBO
		    .chartLast24Hours(new Date());

	    xml
		    .append("<chart caption='Last 24 hours ALDE alerts' xAxisName='' palette='2'"
			    + " animation='1' formatNumberScale='0' numberPrefix='' showValues='0' numDivLines='4' "
			    + "legendPosition='BOTTOM'>");

	    xml.append("<categories>");
	    for (AldeAlertsHours hours : dashBoardChartBO.getHours()) {
		xml.append("<category label='" + hours.getName() + "'/>");
	    }
	    xml.append("</categories>");

	    for (int x = 0; x < seriesName.length; x++) {
		xml.append("<dataset seriesName='" + seriesName[x] + "'>");
		for (AldeAlertsHours hoursBean : dashBoardChartBO.getHours()) {
		    String key = hoursBean.getId() + seriesKeyName[x];
		    AldeAlertsHours mapTemp = (AldeAlertsHours) mLast24Hours
			    .get(key);
		    int amountHour = (null == mapTemp ? 0 : mapTemp.getCount());

		    xml.append("<set value='" + amountHour + "'/>");
		}
		xml.append("</dataset>");
	    }

	    xml.append("<styles>");
	    xml.append("<definition>");
	    xml
		    .append("	<style type='font' name='CaptionFont' color='666666' size='15'/>");
	    xml.append("	<style type='font' name='SubCaptionFont' bold='0'/>");
	    xml.append("</definition>");

	    xml.append("<application>");
	    xml.append("	<apply toObject='caption'    styles='CaptionFont'/>");
	    xml
		    .append("	<apply toObject='SubCaption' styles='SubCaptionFont'/>");
	    xml.append("</application>");
	    xml.append("</styles>");
	    xml.append("</chart>");

	    // FIM
	} catch (Exception e) {
	    e.printStackTrace();
	    out
		    .println("<font color=darkred face=verdana size=1><b>Error Scheduling</b></font><br/>");
	    out
		    .println("<font color=darkred face=verdana  size=1><b>Problems Probably with CronExpression</b></font>");
	    logger.info(e);
	}
	response.setContentType("text/xml");
	out.println(xml.toString());

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