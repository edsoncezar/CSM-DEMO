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
		<td><a href="operator.do?action=initial&operator.id=${param.parentId}">Add New Operator</a></td>
		<td></td>
	</tr>

	<tr>
		<td></td>
		<td><a href="operator.do?action=list&operator.id=${param.parentId}">List Operator</a></td>
		<td></td>
	</tr>
	
</table>
</fieldset>
</body>
</html>