<%@include file="pages/commonHeader.jsp" %>
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
<fieldset>
<legend>Choose an Action...</legend>
<table width="250px">
	<tr>
		<td></td>	
		<td valign="middle"><img src ="img/edit.GIF"></td>
		<td><a href="processorPacket.do?action=edit&processorPacket.id=${param.parentId}">Edit DPPM</a></td>
		<td></td>
	</tr>	
	<tr class="hideDiv">
		<td></td>	
	    <td valign="middle"><img src ="img/delete.GIF"></td>
	    <td><a href="javascript:deleteConfirm('processorPacket.do?action=delete&processorPacket.id=${param.parentId}');">Delete DPPM</a></td>		
<!--		<td style="border-bottom: solid 1px silver;"><a href="processorPacket.do?action=delete&processorPacket.id=${param.parentId}">Delete DPPM</a></td>-->
		<td></td>
	</tr>
	<tr class="hideDiv">
		<td></td>
		<td></td>
		<td valign="middle"><a href="choosePolicy.do?action=list&processorPacket.id=${param.parentId}">Apply Traffic Policy</a></td>
     	<td></td>
	</tr>
	<tr class="hideDiv">
		<td></td>
		<td></td>
		<td valign="middle"><a href="chooseAcl.do?action=initial&acl.contextId=${param.parentId}&acl.name=${param.name}">Apply ACL</a></td>
     	<td></td>
	</tr>
	<tr class="hideDiv">
		<td></td>
		<td></td>
		<td valign="middle"><a href="processorPacket.do?action=listAcl&processorPacket.id=${param.parentId}">Show Applied ACL</a></td>
     	<td></td>
	</tr>
</table>
	
</fieldset>
</body>
</html>
