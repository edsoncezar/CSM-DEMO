<%@include file="pages/commonHeader.jsp"%>
<html>
<head>
	<title>ALU-Agya</title>
	<script language="javascript">		
		function hideLinks(){
			if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${MASTER_ROLE},${ROOT_ADMIN_ROLE}', false)){
				HideDivs();
				document.location.href ='passRegex.do?action=initial';
				
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
		<td style="border-bottom: solid 1px silver;"><a href="passwordSetting.do?action=edit">Add/Edit Password Setting</a></td>
		<td></td>

	</tr>

	<tr class="hideDiv">
		<td></td>
		<td><a href="passRegex.do?action=initial">Add New Password dictionary</a></td>
		<td></td>

	</tr>
	
	<tr>
		<td></td>
		<td><a href="passRegex.do?action=list">List Password dictionary</a></td>
		<td></td>
	</tr>

</table>
</fieldset>

</body>
</html>