<%@include file="pages/commonHeader.jsp"%>
<html>
<head>
<title>ALU-Agya</title>
<script type="text/JavaScript">

function hideLinks(){

	if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${MASTER_ROLE},${ROOT_ADMIN_ROLE}', false)){
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
		<td>
			<a href="portProtocolGroup.do?action=initial">Add new Group</a>
		</td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td><a href="portProtocolGroup.do?action=list">List Groups</a></td>
		<td></td>
	</tr>
</table>
</fieldset>
</body>
</html>