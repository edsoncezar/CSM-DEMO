<%@include file="pages/commonHeader.jsp" %>

<html>
<head>
<c:set var="selected" value="selected"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ALU-Agya</title>
</head>
<script>

function actionForm(action){
	
	document.forms[0].action.value = action;
	document.forms[0].submit();	
}

function enableRefused() {

	var refuseObj = document.getElementById('passRegex.isRefused');
	var regexObj = document.getElementById('passRegex.isRegExp');

	if (regexObj.value=="true") {           // is regex
		refuseObj.value="false";
		refuseObj.disabled = false;
	} else {
		refuseObj.value="true";
		refuseObj.disabled = true;	
	}
	
	return;
}

function setDefaults() {

	var refuseObj = document.getElementById('passRegex.isRefused');
	var regexObj = document.getElementById('passRegex.isRegExp');
	var literalObj = document.getElementById('passRegex.literal');
	
	if (literalObj.value.length < 1) {
		regexObj.value=true;
		regexObj.disabled = false;
	} else {
		enableRefused();
	}
	
	return;
}


</script>

<br>
<body onLoad="setDefaults()">
<%@include file="pages/errorContainer.jsp"%>
<br>

<fieldset style="width:600px">
<legend >Password dictionary</legend>
<html:form  method="post" action="passRegex.do">
<html:hidden property="passRegex.id" />
<html:hidden property="action" styleId="action"/>
<table border="0" width="600px" cellpadding="3" cellspacing="1" align="center">
	<tr>
		<td width="5%"></td>
		<td width="20%"></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td>Type</td>
		<td>
			<html:select property="passRegex.isRegExp" styleId="passRegex.isRegExp" onchange="enableRefused()">
				<html:option value="false">Word</html:option>
				<html:option value="true">Regex</html:option>
			</html:select>
		</td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td >Literal</td>
		<td><html:text size="80" property="passRegex.literal" styleId="passRegex.literal"/></td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td>Action</td>
		<td>${PasswordRegexForm.passRegex.isRefused}
			<html:select property="passRegex.isRefused" styleId="passRegex.isRefused" >
				<html:option value="1">Refuse</html:option>
				<html:option value="0">Accept</html:option>
			</html:select>
		</td>
		<td></td>
	</tr>
	<tr>
		<td colspan="4" align="right">
			<input type=button class="submit" name='btBack'    value='Back'  onclick="javascript:actionForm('list');">
			<input type=button class="submit" name='btSubmit'  value='Send' onclick="javascript:actionForm('save')"; >
		</td>
	</tr>
</table>

</html:form>
</fieldset>
</body>
</html>
