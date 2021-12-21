<%@include file="pages/commonHeader.jsp"%>
<html>
<head>
<title>ALU-Agya</title>
<script type="text/JavaScript">

function hideLinks(){
	if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${MASTER_ROLE},${ROOT_ADMIN_ROLE}', false)){
		var group= document.getElementById("groupDiv");
		$(group).style.display="none";
	}
	
	if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${MASTER_ROLE}', false)){
		var protocol= document.getElementById("protocolDiv");
		$(protocol).style.display="none";
	}
}


</script>
</head>
<body onload="hideLinks();">
<fieldset>
<legend style="width: 150px;">Choose an Action...</legend>
<table width="250px">
	<tr id="groupDiv">
		<td></td>
		<td>
			<a href="portProtocolGroup.do?action=initial">Add new Traffic Group</a>
		</td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td style="border-bottom: solid 1px silver;"><a href="portProtocolGroup.do?action=list">List Traffic Groups</a></td>
		<td></td>
	</tr>
	<tr id="protocolDiv">
		<td></td>
		<td>
			<!-- <a href="processorPacket.do?action=initial&processorPacket.deviceId=${param.deviceId}">Add new Processor Packet  -->
			<a href="protocol.do?action=initial">Add new Protocol</a>
		</td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td><a href="protocol.do?action=list">List Protocols</a></td>
		<td></td>
	</tr>
</table>
</fieldset>
</body>
</html>