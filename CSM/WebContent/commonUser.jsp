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
	<tr>
		<td></td>
		<td valign="middle"><img src ="img/edit.GIF"></td><td align="left"> <a href="user.do?action=edit&user.id=${param.parentId}">Edit User</a></td>
		<td></td>

	</tr>

	<tr class="hideDiv">
		<td></td>
		 <c:choose>
	 		<c:when test="${param.userName eq 'admin' or param.userName eq 'ADMIN' or param.userName eq 'aldeadmin' or param.userName eq 'ALDEADMIN'}"></c:when>
	 		<c:when test="${param.userName eq userSession.userName}"></c:when>
	 		<c:otherwise>
	   			 <td valign="middle"><img src ="img/delete.GIF"></td><td align="left">
	    		 <a href="javascript:deleteConfirm('user.do?action=delete&id=${param.parentId}');">Delete User</a>
	     		</td>	
	     	</c:otherwise>
	 	</c:choose>
<!--	    <a href="user.do?action=delete&user.id=${param.parentId}">Delete User</a></td>-->
		<td></td>
	</tr>

</table>
</fieldset>
</body>
</html>