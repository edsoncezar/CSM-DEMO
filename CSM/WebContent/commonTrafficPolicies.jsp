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

<fieldset><legend style="width: 150px;">Choose an
Action...</legend>
<table width="250px">
	<tr class="hideDiv">
		<td></td>
		<td><a href="trafficPolicy.do?action=initial&trafficPolicy.contextId=${param.parentId}">Add new Traffic Policy</a></td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td><a
			href="trafficPolicy.do?action=list&amp;trafficPolicy.contextId=${param.parentId}">List
		Traffic Policies</a></td>
		<td></td>
	</tr>
	<tr class="hideDiv">
		<td></td>
<!--		<td><a href="accessControlList.jsp">Add new ACL+</a></td>-->
			<td  style="border-top: solid 1px silver;"><a href="acl.do?action=initial&acl.contextId=${param.parentId}">Add new ACL+</a></td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td><a
			href="acl.do?action=list&amp;acl.contextId=${param.parentId}">List
		ACL+</a></td>
		<td></td>
	</tr>
</table>
</fieldset>
</body>
</html>