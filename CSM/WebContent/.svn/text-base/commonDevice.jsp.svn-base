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

	<tr>
		<td></td>
		<td valign="middle"><img src ="img/edit.GIF"></td>	
		<td>
			<a href="device.do?action=edit&device.id=${param.deviceId}&device.contextId=${param.parentId}">Edit Device</a>
		</td>
		<td></td>

	</tr>
	<tr class="hideDiv">
		<td></td>
	   
	    <td valign="middle"><img src ="img/delete.GIF"></td>
<!--		<td style="border-bottom: solid 1px silver;">-->
			<td>
			<a href="javascript:deleteConfirm('device.do?action=delete&device.id=${param.deviceId}&device.contextId=${param.parentId}');">
			Delete Device</a>
			</td>
		<td></td>
	</tr>
	<tr class="hideDiv">
		<td></td>
		<td></td>
		<td style="border-top: solid 1px silver;">
		<a href="processorPacket.do?action=initial&processorPacket.device.id=${param.deviceId}">Add new DPPM</a>
		</td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td></td>
		<td><a href="processorPacket.do?action=list&processorPacket.device.id=${param.deviceId}">List DPPMs</a></td>
		<td></td>
	</tr>
	<tr class="hideDiv">
		<td></td>
		<td></td>		
		<td style="border-top: solid 1px silver;"><a href="choosePolicy.do?action=listForDevice&device.id=${param.deviceId}">Apply Traffic Policy</a></td>
		<td></td>
	</tr>

	
</table>
</fieldset>
</body>
</html>