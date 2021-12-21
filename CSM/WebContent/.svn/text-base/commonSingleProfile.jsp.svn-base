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
	
	<tr>
		<td></td>
		<c:choose>
	 		<c:when test="${(param.name ne 'admin' and param.name ne 'ADMIN') and  (param.id eq sessionScope.userProfile.id)}"></c:when>
	 		<c:otherwise>
					<td valign="middle"><img src ="img/edit.GIF"></td><td align="left"> <a href="profile.do?action=edit&id=${param.id}">Edit User Profile</a></td>	
	     </c:otherwise>
	 	</c:choose>
		<td></td>
	</tr>

	<tr class="hideDiv">
		<td></td>
		<c:choose>
	 		<c:when test="${param.name eq 'admin' or param.name eq 'ADMIN'}"></c:when>
	 		<c:when test="${param.id eq sessionScope.userProfile.id}"></c:when>
	 		<c:otherwise>
	   		 	<td valign="middle"><img src ="img/delete.GIF"></td><td align="left">
	    		<a href="javascript:deleteConfirm('profile.do?action=delete&id=${param.id}');">Delete User Profile</a>
	     	</td>	
	     </c:otherwise>
	 	</c:choose>
		<td></td>
	</tr>

</table>
</fieldset>
</body>
</html>