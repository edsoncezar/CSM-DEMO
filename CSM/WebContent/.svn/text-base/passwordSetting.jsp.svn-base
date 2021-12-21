<%@include file="pages/commonHeader.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ALU-Agya</title>
</head>
<script>

function actionForm(action){
	
	document.forms[0].action.value = action;
	document.forms[0].submit();	
}

function verifyWordOrRegex() {


}
</script>

<br>
<body>
<%@include file="pages/errorContainer.jsp"%>
<br>

<fieldset style="width:600px">
<legend >Password Settings</legend>
<html:form  styleId="PasswordSettingForm" method="post" action="passwordSetting.do">
<html:hidden property="action" styleId="action"/>
<html:hidden property="passSetting.id" />
<table border="0" width="600px" cellpadding="3" cellspacing="1" align="center">
	<tr>
		<td colspan="4">
	</tr>	
	<tr>
		<td></td>
		<td style="width: 30%">Failed attempts</td>
		<td style="width: 30%">
			<html:text property="passSetting.failedAttempts" styleId="passSetting.failedAttempts" maxlength="10" 
			onkeydown ="checkNumber(this, '${PasswordSettingForm.passSetting.failedAttempts}');" 
			onblur="checkNumber(this, '${PasswordSettingForm.passSetting.failedAttempts}');"/>
		</td>
		<td class="checkbox" align="left"><html:checkbox property="passSetting.flagFailedAttempts" />Disable</td>
		
	</tr>

	<tr>
		<td></td>
		<td >Minimum password length</td>
		<td><html:text property="passSetting.minimunLength" styleId="passSetting.minimunLength" maxlength="10" 
			onkeydown ="checkNumber(this, '${PasswordSettingForm.passSetting.minimunLength}');" 
			onblur="checkNumber(this, '${PasswordSettingForm.passSetting.minimunLength}');"/></td>
		<td></td>

	</tr>
	<tr>
		<td></td>
		<td >Password level security</td>
		<td width="10">
			<html:select property="passSetting.levelSecurity" styleId="passSetting.levelSecurity">
				<html:option value="0">Low</html:option>
				<html:option value="1">Medium</html:option>
				<html:option value="2">High</html:option>
			</html:select>
		</td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td >Enforce password history</td>
		<td><html:text property="passSetting.enforceHistoryQty" styleId="passSetting.enforceHistoryQty" maxlength="10"
			onkeydown ="checkNumber(this, '${PasswordSettingForm.passSetting.enforceHistoryQty}');" 
			onblur="checkNumber(this, '${PasswordSettingForm.passSetting.enforceHistoryQty}');" /></td>
		<td class="checkbox" align="left"><html:checkbox property="passSetting.enforceHistoryEnable" />Disable</td>

	</tr>
	<tr>
		<td></td>
		<td >Enforce password changing</td>
		<td><html:text property="passSetting.enforceChangingDays" styleId="passSetting.enforceChangingDays" maxlength="10" 
			onkeydown ="checkNumber(this, '${PasswordSettingForm.passSetting.enforceChangingDays}');" 
			onblur="checkNumber(this, '${PasswordSettingForm.passSetting.enforceChangingDays}');"/> Days</td>
		<td class="checkbox" align="left"><html:checkbox property="passSetting.enforceChangingEnable" />Disable</td>

	</tr>
	<tr>
		<td></td>
		<td >Account lockout duration</td>
		<td><html:text property="passSetting.lockDurationMin" styleId="passSetting.lockDurationMin" maxlength="10"
			onkeydown ="checkNumber(this, '${PasswordSettingForm.passSetting.lockDurationMin}');" 
			onblur="checkNumber(this, '${PasswordSettingForm.passSetting.lockDurationMin}');" /> Min</td>
		<td class="checkbox" align="left"><html:checkbox property="passSetting.lockDurationEnable" />Disable</td>

	</tr>
	<tr>
		<td colspan="4">
	</tr>	
	<tr>
		<td colspan="4" align="right">
			<input type=button class="submit" name='btBack'    value='Back'  onclick="javascript:actionForm('list');">
			<input type=button class="submit" name='btSubmit'  value='Send' onclick="javascript:actionForm('save');">
		</td>
	</tr>
</table>		
</html:form>
</fieldset>

<table>
	<tr>
		<td>* Low level security : Any character</td>
	</tr>
	<tr>
		<td>* Medium level security : Letters and number</td>
	</tr>
	<tr>
		<td>* High level security : Letters, numbers and special characteres</td>
	</tr>	
</table>
</body>
</html>
