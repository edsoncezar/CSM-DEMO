<%@ page import="br.com.alcatellucent.csm.logging.persistence.CsmLogBO"%>
<%@ page import="br.com.alcatellucent.csm.logging.persistence.CsmLog"%>
<%@ page import="br.com.alcatellucent.csm.exception.ExceptionSys"%>

<%@ page import="br.com.alcatellucent.csm.beans.User"%>
<%@ page import="br.com.alcatellucent.csm.bo.UserBO"%>
<%@ page import="br.com.alcatellucent.csm.beans.Alert"%>
<%@ page import="br.com.alcatellucent.csm.bo.AlertBO"%>
<%@ page import="br.com.alcatellucent.csm.exception.ExceptionSys"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
	String id = request.getParameter("csmLog.id");

	UserBO userBO = new UserBO();
	AlertBO alertBO = new AlertBO();
	CsmLogBO csmLogBO = new CsmLogBO();
	
	Alert alert;
	User user;
	CsmLog csmLog;

	try {
		csmLog = csmLogBO.edit(id);
		user = userBO.edit(csmLog.getUserId());
		
		if (user == null) { user = new User();}
		
	} catch (ExceptionSys e) {
		e.printStackTrace();
		return;
	}

	String severity = null;
	
	switch ((Integer)(csmLog.getSeverity())) {
		case 1: severity = "Medium";
		case 2: severity = "High";
		case 3: severity = "Critical";
		default: severity = "Low";
	}
	
	// long timeZone = ((java.util.GregorianCalendar) aclHistory.getDateTime()).getTimeInMillis();
	//	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy dd:MM:ss");
	//	java.util.Date formatedDate = new java.util.Date();
	//	formatedDate.setTime(timeZone); */
	
%>

<!-- Área de Informações  -->
<html>
<title></title>
<head>
<link href="css/csmlogStyle.css" rel="styleSheet" type="text/css"></link>
<script language="javascript" src="scripts/portal.js"
	type="text/javascript"></script>
<link href="css/portal.css" rel="styleSheet" type="text/css"></link>
</head>
<body>
<%@include file="pages/warning.jsp"%>
<br>
<fieldset style="width: 630px;">
<legend>Log Details</legend>
<table border="0" width="80%" height="50 px" cellpadding="0"
	cellspacing="1">
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td style="width: 30%; align: right;"><b>User:</b>
		</td>
		<td style="width: 70%;"><%=(user.getName() == null?"***":(user.getUserName() +" - " + user.getName())) %></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td style="width: 30%; align: right;"><b>Severity:</b></td>
		<td style="width: 70%;"><%=severity %></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td style="width: 30%; align: right;"><b>IP Client:</b></td>
		<td style="width: 70%;"><%= csmLog.getIpClient() %></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td style="width: 30%; align: right;"><b>IP Server: </b></td>
		<td style="width: 70%;"><%=csmLog.getIpServer() %></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td style="width: 30%; align: right;"><b>Event:</b></td>
		<td style="width: 70%;"><%=csmLog.getEvent() %></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td style="width: 30%; align: right;"><b>Date/Time Event:</b></td>
		<td style="width: 70%;"><%=csmLog.getDateEvent() %></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
</table>
<table border="0" width="98%" " height="50 px" cellpadding="0"
	cellspacing="1">
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr style="width: 100%">
		<td>
		<fieldset style="width: 100%;">
			<legend>Details</legend>
				 <textarea 
					rows="20" cols="300" readonly="readonly" style="text-align:left;border:1;width: 98%">
				    <%=csmLog.getDetails()%>
				</textarea>
		</fieldset>
		</td>
	</tr>
</table>
</fieldset>
<table>
<tr>
	<td align="right" colspan="2">
	<table>
		<tr>
			<td style="text-align: right;" ><input type="button" class="submit"
				name='btBack' value='Back' onclick="javascript:history.back()"></td>
		</tr>
	</table>
	</td>
</tr>
</table>
</html>
