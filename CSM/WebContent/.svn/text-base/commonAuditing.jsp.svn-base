<%@include file="pages/commonHeader.jsp"%>
<html>
<head>
<title>ALU-Agya</title>
<script type="text/JavaScript">

function hideLinks(){

	if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE},${OPERATOR_ROLE}', true)){
		HideDivs();
		document.location.href ='AclHistory.do?action=initial';
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
		<td></td>
		<td><a href="CsmlogSearch.jsp">Log Search</a></td>
		<td></td>
	</tr>
	
	<tr>
		<td></td>
		<td></td>
		<td><a href="trafficPolicyHistory.jsp">Traffic Policy History Search</a></td>
		<td></td>
	</tr>
	
	<tr>
		<td></td>
		<td></td>		
		<td ><a href="AclHistory.do?action=initial">ACL History Search</a></td>
		<td></td>
	</tr>
	
	
</table>
</fieldset>
</body>
</html>