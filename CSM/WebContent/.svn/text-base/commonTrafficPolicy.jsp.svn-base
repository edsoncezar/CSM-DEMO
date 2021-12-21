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
		<td valign="middle"><img src ="img/edit.GIF"></td><td align="left"> <a href="trafficPolicy.do?action=edit&trafficPolicy.id=${param.parentId}">Edit Traffic Policy</a></td>
		<td></td>

	</tr>

	<tr class="hideDiv">
		<td></td>
	    <td valign="middle"><img src ="img/delete.GIF"></td>
	     <td><a href="javascript:deleteConfirm('trafficPolicy.do?action=view&trafficPolicy.id=${param.parentId}');">Delete Traffic Policy</a></td>		
<!--	    <td style="border-bottom: solid 1px silver;"align="left"><a href="trafficPolicy.do?action=view&trafficPolicy.id=${param.parentId}">Delete Traffic Policy</a></td>-->
		<td></td>
	</tr>

	<tr class="hideDiv">
		<td></td>
	    <td valign="middle"></td><td align="left"><a href="scheduler.do?action=initial&scheduling.trafficPolicyId=${param.parentId}&scheduling.contextId=${param.contextId}">Add new Schedule</a></td>
		<td></td>
	</tr>	

	<tr>
		<td></td>
	    <td valign="middle"></td><td align="left"><a href="schedulerPanel.do?action=list&scheduling.trafficPolicyId=${param.parentId}">Scheduler Panel</a></td>
		<td></td>
	</tr>

</table>
</fieldset>
</body>
</html>