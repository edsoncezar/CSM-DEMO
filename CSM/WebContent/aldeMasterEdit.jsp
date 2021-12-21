<%@include file="pages/commonHeader.jsp"%>
<!--/////////////////////// ALDE INFO //////////////////////////////////////////////////  -->
<html>
<head>
<script language="javascript">
	function actionForm(action){
		document.forms[0].action.value = action;
		document.forms[0].submit();	
	}
	
	function protectForm(){
		if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE}', true)){
			protectAllFields(true);
		}
	}
</script>
</head>
<body onload="protectForm()">
<%@include file="pages/errorContainer.jsp"%>
<br>
<fieldset style="width: 650px;"><legend style="right: 150px">ALDE Master</legend>

<table width="600 px" cellpadding="4" cellspacing="0" border="0">
	<tbody id="aldeInfo">

		<html:form styleId="aldeForm" method="post" action="aldeMaster.do">
			<html:hidden property="action" />
			<html:hidden property="alde.id" />
			<html:hidden property="alde.master" />
			<html:hidden property="alde.contextId"/>
			<tr>
				<td valign="top">

				<table cellpadding="4" cellspacing="0" border="0">
					<tbody id="aldeDescription">
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td>Name</td>
							<td><html:text property="alde.name" styleId="alde.name"
								maxlength="60" size="30 px" /></td>
						</tr>
						<tr>
							<td>Description</td>
							<td><html:textarea property="alde.description"
								styleClass="textarea" style="width: 350px; height:50px;" /></td>
						</tr>					
						
						<tr>
							<td>Host</td>
							<td><html:text property="alde.host" styleId="alde.host"
								maxlength="60" size="30 px" /></td>
						</tr>

						<tr>
							<td>User</td>
							<td><html:text property="alde.userName"
								styleId="alde.userName" maxlength="60" size="30 px" /></td>
						</tr>

						<tr>
							<td>Password</td>
							<td><html:password property="alde.userPass"
								styleId="alde.userPass" maxlength="60" size="30 px" /></td>
						</tr>

					</tbody>
				</table>

				</td>
			</tr>
			<tr>
				<td align="right" colspan="4">
				<table cellpadding="0" cellspacing="4">
					<tr>
						<td width="500" align="left">&nbsp;</td>
						<td >&nbsp;</td>
						<td align="center"><input type=button class="submit"
							name='btBack' value='Back' onclick="javascript:actionForm('back')"></td>
						<td align="center"><input type=button class="submit"
							name='btSubmit' value='Send'
							onclick="javascript:actionForm('saveMaster')"></td>
					</tr>
				</table>
			</td>
			</tr>
		</html:form>
		</tbody>
	</table>
</fieldset>
		<!--/////////////////////// END - ALDE INFO //////////////////////////////////////////////////  -->
</body>
</html>

