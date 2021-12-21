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
		<td><a href="trafficPolicy.do?action=initial&trafficPolicy.contextId=${param.parentId}">Add new Traffic Policy</a></td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td><a href="trafficPolicy.do?action=list&trafficPolicy.contextId=${param.parentId}">List Traffic Policy</a></td>
		<td></td>
	</tr>	
</table>
</fieldset>
</body>
</html>