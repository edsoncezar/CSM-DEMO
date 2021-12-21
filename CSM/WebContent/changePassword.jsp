<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="pages/commonHeader.jsp" %>
<html>
<head>
<title>ALU-Agya</title>
<script language="javascript">

	function actionConfirm(action){
	if(action=='savePassword'){
		if($('newPassword').value == "" ){
		 alert('The new password can not be null');
			return;
		}	
		
		if($('newPassword').value.length < 3 ){
		 alert('The new password must be at least three characters long');
			return;
		}	
		
		if($('newPassword').value != $('confirmNewPassword').value ){
		 alert('The new password does not match');
			return;
		}
		}	
		
		actionForm(action)
	}
	
	function actionForm(action){
		
		document.forms[0].action.value = action;
		document.forms[0].submit();	
		
	}

</script>
</head>
<body>
<div style="position: relative; top: 150px;" align="center">
<html:form styleId="formLogin" method="post" action="login.do">
<table cellpadding="0" cellspacing="0">
	
		<html:hidden property="action" styleId="action" />
		<html:hidden property="user.userName" styleId="username"/>
		<tr>
			<td>
			<fieldset style="background-image: url('img/loginBackground.gif');" class="fieldset">
			<legend style="background-image: url('img/loginBackground.gif');" class="legend" >Change Password</legend>

			<table width="320" cellpadding="2" cellspacing="2">
				<tr>
					<td colspan="5">
					<p><img title="Alcatel-Lucent" height="66" alt="Alcatel-Lucent"
						src="img/logo.jpg" border="0"></p>
					<p align="right"><img src="img/logoSmall.gif"
						border="0"></p>
					</td>
				</tr>

				<tr>
					<td colspan="5" align="right"><font color="red" face="Verdana"
						size="1px"><b><html:messages id="message"
						message="true">${message}</html:messages></b></font></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>Current Password</td>
					<td></td>
					<td align="right"><html:password property="userAccessControl.userPassword"
						maxlength="60" style="width:150px;" /></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>New Password</td>
					<td></td>
					<td align="right"><html:password property="newPassword"
						styleId="newPassword" maxlength="60" style="width:150px;" /></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="2">Confirm New Password </td>
					<td align="right"><html:password
						property="confirmNewPassword" styleId="confirmNewPassword" maxlength="60" style="width:150px;" />
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="4" align="right">
						<input type=button class="submit" name='btBack'
						value='Back' onclick="javascript:history.go(-1);">
						<input type="button"
						class="submit" name='Save' value='Save' id='btnSave' onclick="javascript:actionConfirm('savePassword')"></td>
				</tr>
				
			</table>
	</fieldset>
	</td>
	</tr>
	</table>
	</html:form>

</div>
</body>
</html>
