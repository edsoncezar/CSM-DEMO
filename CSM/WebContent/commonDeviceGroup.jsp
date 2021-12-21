<%@include file="pages/commonHeader.jsp"%>
<html>
<head>
<title>ALU-Agya</title>
<script type="text/JavaScript">

function hideLinks(){

	if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE}', true)){
		HideDivs();
	}
}

</script>
</head>
<body onload="hideLinks();">
<fieldset>
<legend style="width: 150px;">Choose an Action...</legend>

<table width="250px">

	<tr class="hideDiv">
		<td></td>
		<td><a href="device.do?action=initial&device.contextId=${param.parentId}">Add New Device</a></td>
		<td></td>

	</tr>

	<tr>
		<td></td>
		<td style="border-bottom: solid 1px silver;"><a href="device.do?action=list&device.contextId=${param.parentId}">List Devices</a></td>
		<td></td>
	</tr>
	
	
	<tr class="hideDiv">
		<td></td>
		<td><a href="alde.do?action=initial&alde.contextId=${param.parentId}">Add New ALDE Probe</a></td>
		<td></td>

	</tr>

	<tr>
		<td></td>
		<td><a href="alde.do?action=list&alde.contextId=${param.parentId}">List ALDE Probe</a></td>
		<td></td>
	</tr>
	
	<tr class="hideDiv">
		<td></td>		
		<td style="border-top: solid 1px silver;"><a href="choosePolicy.do?action=listForContext&context.id=${param.parentId}">Apply Traffic Policy</a></td>
		<td></td>
	</tr>
	
</table>
</fieldset>
</body>
</html>