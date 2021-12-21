<%@include file="pages/commonHeader.jsp"%>
<html>
<head>
<title>User profiles</title>
<link href="css/displaytag.css" rel="STYLESHEET" type="text/css">

</head>
<body
	<c:if test="${requestScope.refresh eq 'true'}">
			onload="reloadTree('${requestScope.nodeType}','${requestScope.nodeId}');"
			<c:set var="requestScope.refresh" value="${'false'}"/>
		</c:if>>
<%@include file="pages/errorContainer.jsp"%>
<br>
<h3>Profile List</h3>
<display:table name="sessionScope.listUserProfile" id="row" export="true" defaultsort="1" pagesize="25">
	<display:column width="80%" sortable="true" property="name" title="Name" maxLength="80" />
	
	<display:column style="text-align: center" width="10%" title="" media="html">
		<c:choose>
			<c:when test="${row.name eq 'admin' or row.name eq 'ADMIN'}"></c:when>
			<c:when test="${row.id eq sessionScope.userProfile.id}"></c:when>
			<c:otherwise>
				<a href="profile.do?action=edit&id=${row.id}">Edit</a>
			</c:otherwise>
		</c:choose>
	</display:column>

	<display:column style="text-align: center" width="10%" paramId="id"
		paramProperty="id" media="html">
		<c:choose>
			<c:when test="${row.name eq 'admin' or row.name eq 'ADMIN'}"></c:when>
			<c:when test="${row.id eq sessionScope.userProfile.id}"></c:when>
			<c:otherwise>
				<a
					href="javascript:deleteConfirm('profile.do?action=delete&id=${row.id}');"><%="Delete"%></a>
			</c:otherwise>
		</c:choose>
	</display:column>
</display:table>
<br />
</html>


