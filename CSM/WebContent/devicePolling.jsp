<%@include file="pages/commonHeader.jsp"%>
<%@ page import ="br.com.alcatellucent.csm.utils.DateUtil" %>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>

<title>ALU - AGYA</title>
<link rel="stylesheet" href="Chart/Contents/Style.css" type="text/css" />
<script language="JavaScript" src="Chart/JSClass/FusionCharts.js"></script>
<script>
	var actualChartType = "lastWeekvailability";
	function getNextAvailableChart(){
		if(actualChartType == "lastWeekvailability"){
			actualChartType = "lastMonthAvailability";
		}else{
			actualChartType = "lastWeekvailability"
		}
		var chartAvail = new FusionCharts("Chart/Charts/Pie3D.swf", "", "400px", "200px", "0", "1");
		chartAvail.setDataURL("dashBoard.do?action="+actualChartType);
		chartAvail.render("chartAvailDiv");
	}
	
	function popUp(URL) {
		day = new Date();
		id = day.getTime();
		eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=665,height=200');");
	}

</script>
</head>
<body topmargin="0" leftmargin="0">

<table width="640" border="0">
	<tr>
		<td rowspan="2">
		<table style="width: 250px;" cellpadding="2" cellspacing="1"
			style="border: solid 1px black;">
			<thead>
				<tr
					style="background-color: #CCCCFF; border-bottom: dotted 1px silver; color: #004D99; font-weight: bold; padding-left: 5px; text-align: center">
					<td colspan="3"  style="width: 142px;">Device</td>
					<td>Last Polling</td>

				</tr>
			</thead>
		</table>
		<div
			style="overflow: auto; height: 150px; width: 250px;">
		<table style="width: 100%;" cellpadding="2" cellspacing="1"
			style="border:none;">

			<c:forEach var="device" items="${requestScope.DEVICE_LIST}">
				<tr style="background-color: #FFFFFF; text-align: center;">
					<td style="width: 20px; padding: Opx;"><c:choose>
						<c:when test="${device.deviceManager.pollingErrors eq '0'}">
							<img src="img/accept.gif" title="Active">
						</c:when>
						<c:otherwise>
							<img src="img/error.gif"
								title="Inactive [${device.deviceManager.pollingErrors} attempt(s)]">
						</c:otherwise>
					</c:choose></td>
					<td style="width: 20px; padding: Opx;" >
						<a href="javascript:popUp('dashBoard.do?action=list&traffic=${device.id}')">
						<img src="img/server_lightning.png" title="DPPM History" border="0">
						</a>
					</td>
					<td style="width: 100px;">${device.name}</td>
					<td>
					${device.deviceManager.lastPoolingDateFormatted}
					</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		<td>
			
		</td>
	</tr>
</table>

<br>
<br>

</body>
</html>