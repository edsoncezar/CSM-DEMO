<%@include file="pages/commonHeader.jsp"%>

<html>
<head>
<title>ALU-Agya</title>
<script type="text/JavaScript">

function hideLinks(){

	if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE},${OPERATOR_ROLE}', true)){
		HideDivs();
		document.location.href ='user.do?action=initial';
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
		<td><a href="user.do?action=initial&contextId=<%=request.getParameter("parentId")%>">Add New User</a></td>
		<td></td>

	</tr>

	<tr>
		<td></td>
		<td style="border-bottom: solid 1px silver;"><a href="user.do?action=list&contextId=<%=request.getParameter("parentId")%>">List Users</a></td>
		<td></td>
	</tr>
	

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
