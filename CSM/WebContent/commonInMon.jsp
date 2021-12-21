<%@include file="pages/commonHeader.jsp"%>
<html>
<head>
<title>ALU-Agya</title>
<script type="text/JavaScript">

function hideLinks(){

	if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE},${OPERATOR_ROLE}', true)){
		HideDivs();
	}
}

</script>
</head>
<body onload="hideLinks();">
<fieldset>
<legend style="width: 150px;">Choose an Action...</legend>
<table width="250px">

<c:choose>
	<c:when test="${viewMode == 2}">
		<c:set var="target" value="_blank"/>
	</c:when>
	<c:otherwise>
		<c:set var="target" value=""/>
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${not empty(id)}">
	<tr>
		<td></td>
		<td><a href="${url}${credential}" target="${target}" >Connect to InMon</a></td>
		<td></td>
	</tr>
	</c:when>
</c:choose>
	<tr class="hideDiv">
		<td></td>
		<td><a href="inmon.do?action=edit&inMon.id=${id}">InMon Settings</a></td>
		<td></td>
	</tr>
	
</table>
</fieldset>
</body>
</html>