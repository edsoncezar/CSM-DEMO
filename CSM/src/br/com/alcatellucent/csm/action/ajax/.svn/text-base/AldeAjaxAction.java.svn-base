package br.com.alcatellucent.csm.action.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.action.CsmBaseAction;
import br.com.alcatellucent.csm.alde.AlertMessage;
import br.com.alcatellucent.csm.alde.management.client.ALdeException;
import br.com.alcatellucent.csm.alde.management.client.ALdeStatusRequest;
import br.com.alcatellucent.csm.beans.Alde;
import br.com.alcatellucent.csm.beans.Alert;
import br.com.alcatellucent.csm.bo.AldeBO;
import br.com.alcatellucent.csm.bo.AlertBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;

public class AldeAjaxAction extends CsmBaseAction {

	private static final String BMP_STATUS_ALDE_RUNNING = "activeserver16.bmp";
	private static final String BMP_STATUS_ALDE_STOPPED = "inativeserver16.bmp";
	private static final String BMP_STATUS_ALDE_UNKNOWN = "disconnectedserver16.bmp";
	private static final String STATUS_MESSAGE = "<img src='img/%s' title='%s' alt='%s' />%s %s";
	private static final String MSG_ERROR_CONN_USERPASS = "Connection Error... Please, check username and password used!";
	private static final String MSG_ERROR_CONN = "Connection Error";
	private static final String MSG_RNG_DYNAMIC = "ALDE Running in Dynamic Mode";
	private static final String MSG_RNG_STATIC = "ALDE Running in Static Mode";
	private static final String MSG_RNG_STOPED = "ALDE Stoped";

	private static void initResponse(HttpServletResponse response) {
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
	}

	public void getALDEStatus(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ALdeException {
		initResponse(response);
		PrintWriter out = response.getWriter();
		String aldeId = request.getParameter("id");
		AldeBO aldeBO = new AldeBO();
		Alde alde;
		try {
			alde = aldeBO.edit(aldeId);
			switch (AldeBO.getRunningStatus(alde)) {
			case ALdeStatusRequest.STATUS_DYNAMIC_RUNNING:
				out.write(String.format(STATUS_MESSAGE, BMP_STATUS_ALDE_RUNNING, MSG_RNG_DYNAMIC, MSG_RNG_DYNAMIC,
						"Dynamic Mode", "(<a href=\"#\" onclick=\"javascript:stopALDE('dynamic')\">stop</a>)"));
				break;
			case ALdeStatusRequest.STATUS_STATIC_RUNNING:
				out.write(String.format(STATUS_MESSAGE, BMP_STATUS_ALDE_RUNNING, MSG_RNG_STATIC, MSG_RNG_STATIC,
						"Static Mode", "(<a href=\"#\" onclick=\"javascript:stopALDE('static')\">stop</a>)"));
				break;
			case ALdeStatusRequest.STATUS_INATIVE:
				out
						.write(String
								.format(
										STATUS_MESSAGE,
										BMP_STATUS_ALDE_STOPPED,
										MSG_RNG_STOPED,
										MSG_RNG_STOPED,
										"Inactive",
										"(<a href=\"#\" onclick=\"javascript:startALDE('static')\">Start Static</a>) or (<a href=\"#\" onclick=\"javascript:startALDE('dynamic')\">Start Dynamic</a>)"));
				break;
			default:
				break;
			}
		} catch (ALdeException e) {
			out.write(String.format(STATUS_MESSAGE, BMP_STATUS_ALDE_UNKNOWN, MSG_ERROR_CONN_USERPASS,
					MSG_ERROR_CONN_USERPASS, "<font color=\"red\">ALDE error</font>",
					"(<a href=\"#\" onclick=\"javascript:loadAldeStatus()\">Test again</a>)"));
			logger.error(e);
		} catch (Throwable t) {
			out.write(String.format(STATUS_MESSAGE, BMP_STATUS_ALDE_UNKNOWN, MSG_ERROR_CONN, MSG_ERROR_CONN,
					"<font color=\"red\">Error connecting to ALDE</font>",
					"(<a href=\"#\" onclick=\"javascript:loadAldeStatus()\">Test again</a>)"));
			logger.error(t);
		} finally {
			out.flush();
		}
	}

	public void PoolingALDEStatus(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		initResponse(response);
		PrintWriter out = response.getWriter();

		AlertBO alertBO = new AlertBO();
		String alertGrade = "green";
		try {
			List<Alert> alertList = alertBO.getActiveAlerts();
			alertGrade = alertBO.getAlertGrade(alertList);
		} catch (ExceptionSys e) {
			e.printStackTrace();
		}

		out.print(getAlertOut(alertGrade));

	}

	public void alertList(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		initResponse(response);
		PrintWriter out = response.getWriter();
		AlertBO alertBO = new AlertBO();
		String alertOut = "Nothing to display...";
		try {
			List<Alert> alertList = alertBO.getActiveAlerts();
			alertOut = outAlertList(alertList);
		} catch (ExceptionSys e) {
			e.printStackTrace();
		}
		out.print(alertOut);

	}

	protected String getAlertOut(String alertGrade) {

		StringBuffer strOut = new StringBuffer();

		if (alertGrade.equalsIgnoreCase("green")) {
			strOut
					.append("<a href=\"#\"><img src='img/home/alert/greenAlert.gif'	border='0' onclick='loadDDOSStatus()'></a>");
			strOut.append("<a href=\"#\"><img src='img/off_alert.gif' border='0' onclick='showDDOSAlerts()'></a>");
			strOut
					.append("<a href=\"#\"><img src='img/off_alert.gif' border='0' onclick='showDDOSAlerts()'>&nbsp;&nbsp;</a>");
		}
		if (alertGrade.equalsIgnoreCase("yellow")) {
			strOut.append("<a href=\"#\"><img src='img/off_alert.gif' border='0' onclick='loadDDOSStatus()'></a>");
			strOut
					.append("<a href=\"#\"><img src='img/home/alert/yellowAlert.gif' border='0' onclick='showDDOSAlerts()'></a>");
			strOut
					.append("<a href=\"#\"><img src='img/off_alert.gif' border='0' onclick='showDDOSAlerts()'>&nbsp;&nbsp;</a>");
		}
		if (alertGrade.equalsIgnoreCase("red")) {
			strOut.append("<a href=\"#\"><img src='img/off_alert.gif' border='0' onclick='loadDDOSStatus()'></a>");
			strOut.append("<a href=\"#\"><img src='img/off_alert.gif' border='0' onclick='showDDOSAlerts()'></a>");
			strOut
					.append("<a href=\"#\"><img src='img/home/alert/redAlert.gif' border='0' onclick='showDDOSAlerts()'>&nbsp;&nbsp;</a>");
		}
		return strOut.toString();
	}

	protected String outAlertList(List<Alert> alertList) {

		StringBuffer strOut = new StringBuffer();
		String alertIcon = "";
		String nextColorCSS = "silver";

		// Date date = new Date();

		// strOut.append("<iframe width=\"400px\" height=\"200px\">");
		strOut.append("<br /><a href=\"javascript:showHide('DDOSListDiv')\">close</a><br>");
		strOut.append("<table style=\"width=90%\" id=\"DDOSTable\">");
		// strOut.append("<tr>");
		strOut.append("<th>&nbsp;</th>");
		strOut.append("<th>Alde</th>");
		strOut.append("<th>Date</th>");
		strOut.append("<th>Time</th>");
		strOut.append("<th>Attack Type</th>");
		strOut.append("<th>Status</th>");
		strOut.append("<th align=\"center\">Actions</th>");

		for (Alert alert : alertList) {

			// if (alert.getRule().equalsIgnoreCase("drop")) {
			// strOut.append("<ul id=\"errorEvent\" ><li >"+alert.getAlde()+" :
			// "+ alert.getMessageType()+" : "+ alert.getRule() +"</li></ul>");
			// } else {
			// strOut.append("<ul id=\"warnEvent\" ><li>"+alert.getAlde()+" : "+
			// alert.getMessageType()+" : "+ alert.getRule() +"</li></ul>");
			// }

			if (alert.getRule().equalsIgnoreCase("drop")) {
				alertIcon = "./img/icons/action_stop.gif";
			} else {
				alertIcon = "./img/icons/page_alert.gif";
			}

			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(alert.getTimeStampMessage());

			SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sTime = new SimpleDateFormat("hh:mm:ss");
			String fDate = null;
			String fTime = null;

			try {
				fDate = sDate.format(cal.getTime());
				fTime = sTime.format(cal.getTime());

			} catch (Exception e) {

			}

			String attackType = null;
			String status = null;
			String editAcl = "<a href=aclAlert.do?action=alert&alertId=" + alert.getId()
					+ " target=\"bodyContent\">Edit ACL </a>";
			String closeAcl = "<a href=alert.do?action=initial&alertId=" + alert.getId()
			+ " target=\"bodyContent\">Close ACL </a>";
			attackType = AlertMessage.ATTACK_TYPE_DESC[alert.getAttackType()];

			switch (alert.getStatus()) {
			case 0:
				status = "Waiting";
				break;
			case 1:
				status = "Closed";
				break;
			case 2:
				status = "Running";
				break;
			case 3:
				status = "Refused";
				break;
			default:
				status = "***";
			}

			strOut.append("<tr " + nextColorCSS + ">");
			strOut.append("<td style=\"width: 3%; text-align: center;\"> <img src=\"" + alertIcon + "\"/></td>");
			strOut.append("<td style=\"width: 15%; text-align: left;\">" + alert.getAlde() + "</td>");
			strOut.append("<td style=\"width: 15%; text-align: left;\">" + fDate.toString() + "</td>");
			strOut.append("<td style=\"width: 15%; text-align: left;\">" + fTime.toString() + "</td>");
			strOut.append("<td style=\"width: 15%; text-align: left;\">" + attackType + "</td>");
			strOut.append("<td style=\"width: 15%; text-align: left;\">" + status + "</td>");
			strOut.append("<td><table>");
			strOut.append("<tr>");
			strOut.append("<td style=\"width: 15%; text-align: left;\">" + editAcl + "</td>");
			strOut.append("<td style=\"width: 15%; text-align: left;\">" + closeAcl + "</td>");
			strOut.append("</tr>");
			strOut.append("</td></table>");
			strOut.append("</tr>");

			if (nextColorCSS.equals("silver")) {
				nextColorCSS = "style=\"background-color: White;\"";
			} else {
				nextColorCSS = "style=\"background-color: #E9E9E9;\"";
			}
		}

		strOut.append("</table>");
		strOut.append("<br /><a href=\"javascript:showHide('DDOSListDiv')\">close</a><br><br>");

		// strOut.append("</iframe>");
		return strOut.toString();
	}
}
