<%@include file="pages/commonHeader.jsp"%>
<html>
<c:set var="nodeId" value="${param.nodeId}" scope="session" />
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
	<tr>
		<td></td>
		<td><a href="context.do?action=edit&context.id=${param.parentId}">Edit Context</a></td>
		<td></td>
	</tr>
	<c:if test="${empty(param.isroot)}" >
	<tr class="hideDiv">
		<td></td>
			<td><a href="javascript:deleteConfirm('context.do?action=delete&context.id=${param.parentId}');">Delete Context</a></td>
<!--		<td><a href="context.do?action=delete&context.id=${param.parentId}">Delete Context</a></td>-->
		<td></td>
	</tr>
	</c:if>
	
	<tr class="hideDiv" >
		<td>&nbsp;</td>
		<td valign="bottom"><a href="context.do?action=initial&context.parentId=${param.parentId}">Add New Context</a></td>
		<td>&nbsp;</td>
	</tr>

	<tr>
		<td></td>
		<td><a href="context.do?action=list&context.id=${param.parentId}">List Contexts</a></td>
		<td ></td>

	</tr>
	
	<tr>
		<td></td>
		<td><a href="device.do?action=list&device.contextId=${param.parentId}">List Devices</a></td>
		<td></td>
	</tr>

	<tr class="hideDiv">
		<td></td>		
		<td style="border-top: solid 1px silver;" ><a href="choosePolicy.do?action=listForContext&context.id=${param.parentId}">Apply traffic policy</a></td>
		<td></td>
	</tr>
	
</table>
</fieldset>
</body>
</html>
