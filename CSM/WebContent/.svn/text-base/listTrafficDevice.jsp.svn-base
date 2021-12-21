<%@include file="pages/commonHeader.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ALU - Traffic History By Device</title>
<script>
function loadAll(){
<c:forEach var="trafficHistory" items="${requestScope.TRAFFIC_HISTORY_LIST}">
	<c:if test="${not empty(trafficHistory.id)}">
		
		//$('policy_${trafficHistory.dppmId}').id = "${trafficHistory.id}";
		$('date_${trafficHistory.dppmId}').innerHTML = "<fmt:formatDate type="both" pattern="dd/MM/yy HH:mm:ss" value="${trafficHistory.date.time}"/>";
		//$('time_${trafficHistory.dppmId}').innerHTML = "${trafficHistory.date}";
		
		$('status_${trafficHistory.dppmId}').innerHTML = <c:choose>
															<c:when test="${trafficHistory.statusApplied eq true}">
															 	"<img src='img/accept.gif'>"
															</c:when>
															<c:otherwise>
																"<img src='img/error.gif'>"
															</c:otherwise>
														</c:choose>
														
		$('mode_${trafficHistory.dppmId}').innerHTML = <c:choose>
															<c:when test="${trafficHistory.mode eq 1}">
																"<img src='img/icons/general/clock_go.png'>"
															</c:when>
															<c:otherwise>
																"<img src='img/icons/general/wrench.png'>"
															</c:otherwise>
														</c:choose>
	</c:if>
</c:forEach>
<c:forEach var="tPolicy" items="${requestScope.TRAFFIC_POLICY_LIST}">
		$('policy_${tPolicy.id}').innerHTML = "${tPolicy.name}";
</c:forEach>
}
</script>
<style type="text/css">
	.borderCell{
		border: 1px solid silver;
	}
	.borderCellRt{
		border: 1px solid silver;
		border-left: none;
	}
	.borderCellLt{
		border: 1px solid silver;
		border-right: none;
	}
	
	.borderLeftBottom{
		border-left: 1px solid silver;
		border-bottom: 1px solid silver;
	}
	
	.borderLeftOnly{
		border-left: 1px solid silver;
	}
	
	.borderRightBottom{
		border-right: 1px solid silver;
		border-bottom: 1px solid silver;
	}
	.boderRightOnly{
		border-right: 1px solid silver;
	}
	
	
	.borderCellM{
		border: 1px solid silver;
	}
	.borderTop{
		border-top: 1px solid silver;
	}
	.borderBottom{
		border-bottom: 1px solid silver;
	}
</style>
<link rel="stylesheet" href="Chart/Contents/Style.css" type="text/css" />
</head>
<body onload="loadAll();">
<table style="width: 650px;"  cellpadding="2" cellspacing="1" style="border:none;">
  	<thead>
    <tr >
      <td colspan="3" rowspan="1" >&nbsp;</td>
      <td style="text-align: center; color: #004D99; font-weight: bold;" colspan="3"  bgcolor="#CDC7FF">
      	Last traffic policy applied
      </td>
      <td style="width:13px;" bgcolor="white">&nbsp;&nbsp;</td>
    </tr>
    <tr style="	background-color: #CCCCFF;	border-bottom: solid 1px silver; color: #004D99; font-weight: bold; padding-left: 5px; text-align: center">
		<td style="width:20px;" >&nbsp;</td>
		<td style="width:100px;">Device</td>
		<td style="width:100px;">Last Poll</td>
		<td style="width:100px;">Policy</td>
		<td style="width:100px;">Date</td>
		<td style="width:100px;">Status</td>
		<td style="width:100px;">mode</td>
    </tr>
    </thead>
</table>
<div style="border: solid 1px black; width: 650;">
<table style="width: 650px;"  cellpadding="2" cellspacing="1" style="border:none;">
    
	<c:forEach var="device" items="${requestScope.DEVICE_LIST}" >
   		
		<tr  style="background-color: #EDE5FF"  style=" text-align: center;">
			  <td style="width:20px;" bgcolor="#DDFFC7">&nbsp;</td>
			  <td style="width:100px;" >${device.name}</td>
			  <td colspan="5" >&nbsp;</td>

		</tr>
    	<c:forEach var="pPacket" items="${device.processorPacketList}" >
		   <tr id="tr_${device.id}" style="background-color: #EDE5FF; text-align: center;">
		      <td bgcolor="white"></td>
		      <td bgcolor="white"></td>
		      <td style="background-color: #FFCCBB;">${pPacket.name}</td>
		      <td style="width:100px;" id="policy_${pPacket.trafficPolicyId}">&nbsp;</td>
		      <td style="width:100px;" id="date_${pPacket.id}">&nbsp;</td>
		      <td style="width:100px;" id="status_${pPacket.id}">&nbsp;</td>
		      <td style="width:100px;" id="mode_${pPacket.id}"></td>
		    </tr>
    	</c:forEach>
    	
	</c:forEach>
   
</table>
</div>

<br><br>
</body>
</html>