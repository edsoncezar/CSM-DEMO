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
		<td><a href="operator.do?action=initial&device.contextId=${param.parentId}">Add Operator</a></td>
		<td></td>

	</tr>

	<tr>
		<td></td>
		<td><a href="operator.do?action=list&device.contextId=${param.parentId}">List Operators</a></td>
		<td></td>
	</tr>
	
	
	<tr class="hideDiv">
		<td></td>
		<td style="border-top: solid 1px silver;"><a href="aldeMaster.do?action=edit&alde.contextId=${param.parentId}">Add/Edit ALDE Master</a></td>
		<td></td>

	</tr>
	
</table>
</fieldset>
</body>
</html>