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
<fieldset>
<legend style="width: 150px;">Choose an Action...</legend>

<table width="250px">
	<tr>
		<td></td>
		<td valign="middle"><img src ="img/edit.GIF"></td><td align="left"> <a href="alde.do?action=edit&alde.id=${param.id}">Edit ALDE</a></td>
		<td></td>

	</tr>

	<tr class="hideDiv">
		<td></td>
	    <td valign="middle"><img src ="img/delete.GIF"></td><td align="left">
	     <a href="javascript:deleteConfirm('alde.do?action=delete&alde.id=${param.id}');">Delete ALDE</a>
	     </td>	
		<td></td>
	</tr>

</table>
</fieldset>