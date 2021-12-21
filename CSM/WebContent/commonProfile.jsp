<%@include file="pages/commonHeader.jsp"%>

<html>
<head>
<title>ALU-Agya</title>
<script type="text/JavaScript">

function hideLinks(){

	if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE},${OPERATOR_ROLE}', true)){
		HideDivs();
		document.location.href ='profile.do?action=initial';
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
		<td><a href="profile.do?action=initial&contextId=<%=request.getParameter("parentId")%>">Add New Profile</a></td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td><a href="profile.do?action=list&contextId=<%=request.getParameter("parentId")%>">List Profiles</a></td>
		<td></td>
	</tr>

</table>
</fieldset>
</body>
</html>