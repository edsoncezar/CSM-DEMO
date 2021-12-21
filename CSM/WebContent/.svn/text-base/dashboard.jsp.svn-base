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
<body onload="changeMenuColor('tabDashBoard', 'true');">

<table width="650" border="0">
	<tr>
		<td rowspan="2" align="right">
		<iframe style="overflow: hidden;width: 260px; height: 200px;" scrolling="no" frameborder="0"  src="dashBoard.do?action=initial2"></iframe>
		<td>
			<div id="chartAvailDiv"></div>
		</td>
	</tr>
	<tr>
		<td align="right">
			<a href="javascript:getNextAvailableChart();">
				next...
			</a>
		</td>
	</tr>
</table>
<script type="text/javascript">
   var chartAvail = new FusionCharts("Chart/Charts/Pie3D.swf", "", "400px", "200px", "0", "1");
   chartAvail.setDataURL("dashBoard.do?action=lastWeekvailability");
   chartAvail.render("chartAvailDiv");
</script>
<table style="width: 650px;" cellpadding="2" cellspacing="1"
	style="border:none;">
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>
		<div id="chart1div">
		<p>&nbsp;</p>
		</div>

		<script type="text/javascript">
			   var chart1 = new FusionCharts("Chart/Charts/MSColumn3D.swf", "sampleChart", "340", "250", "0", "1");
			   chart1.setDataURL("dashBoardChart?");
			   chart1.render("chart1div");
			</script></td>

		<td>
		<div id="chart2div">
		<p>&nbsp;</p>
		</div>

		<script type="text/javascript">
			   var chart1 = new FusionCharts("Chart/Charts/MSColumn3D.swf", "sampleChart", "340", "250", "0", "1");
			   chart1.setDataURL("dashBoardMonthChart?");
			   chart1.render("chart2div");
			</script></td>
	</tr>
</table>


<table style="width: 650px;" cellpadding="2" cellspacing="1"
	style="border: solid 1px black;">

	<tr
		style="background-color: #CCCCFF; border-bottom: solid 1px silver; color: #004D99; font-weight: bold; padding-left: 5px; text-align: center">
		<td style="width: 100px;">ALDE</td>
		<td style="width: 100px;">Date</td>
		<td style="width: 100px;">Time</td>
		<td style="width: 100px;">Attack Type</td>
		<td style="width: 100px;">Source IP</td>
		<td style="width: 100px;">Dest IP</td>
		<td style="width: 100px;">Dest Port</td>
	</tr>

	<c:forEach var="alert" items="${requestScope.listTop10AldeAlerts}">
		<tr>
			<td style="width: 100px;" id="id_${alert.id}">${alert.alde}</td>
			<td style="width: 100px;" id="date_${alert.id}">${alert.dateMessage}</td>
			<td style="width: 100px;" id="time_${alert.id}">${alert.timeMessage}</td>
			<td style="width: 100px;" id="attackType_${alert.id}">${alert.attackType}</td>
			<td style="width: 100px;" id="sourceIp_${alert.id}">${alert.sourceIp}</td>
			<td style="width: 100px;" id="destIp_${alert.id}">${alert.destinationIp}</td>
			<td style="width: 100px;" id="destPort_${alert.id}">${alert.destinationPort}</td>
		</tr>
	</c:forEach>

</table>
<br>
<br>
</body>
</html>