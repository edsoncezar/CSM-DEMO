<%@include file="pages/commonHeader.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script language="JavaScript">
	
	function actionForm(action){	
		if( $('newPassword').value != "" ) 
		
		{
			if($('newPassword').value.length <3){
		 		alert('Minimum password size is 3 characteres');
					return;
			}	
		
			if($('newPassword').value != $('confirmNewPassword').value ){
		 		alert('The new password does not match.');
					return;
			}	
		}

			document.forms[0].action.value = action;
			document.forms[0].submit();				
	}	
		
	</script>	
</head>

<body onload="javascript:changeMenuColor('tabMyAccount', 'true');">
<%@include file="pages/errorContainer.jsp"%>
<br>

		<fieldset style="width:650px; height: 220px;">
		<legend>My Account</legend>
		
		<table border="0" width="100%" cellpadding="3" cellspacing="1" align="center">
			<tr>
			<table width="100%">
				<tr>
					<td><jsp:include page="tabMenu.jsp">
						<jsp:param name="tabNames" value="usrLogInfo,usrContactInfo,usrPassInfo" />
						<jsp:param name="tabDescription" value="User Login,Contact,Change Password" />
						<jsp:param name="tabID" value="1" />
						<jsp:param name="currentTab" value="usrLogInfo" />
					</jsp:include></td>
				</tr>
			</table>
			</tr>
			<html:form styleId="MyAccount" method="post" action="myAccount.do">
 			<html:hidden property="action"/>
 			<html:hidden property="user.id"/> 
 			<html:hidden property="user.contextId" />
 			<html:hidden property="user.userProfileId" />
 			<html:hidden property="user.isActive" />
 			<br>
			<tr>
				<td colspan="2">
<!--				<fieldset style="width: 620px;">		-->
<!--					<legend>User Login</legend>-->
					<table width="100%">
						<tbody id="usrLogInfo">
						<tr>
							<td></td>
							<td style="width: 15%;">User name</td>
							<td><html:text readonly="true" property="user.userName" 
							     				styleClass="user.name" 
							     				style="width:300px;"/></td>
							<td></td>
						</tr>
						<tr>
							<td></td>
							<td style="width: 15%">Name</td>
							<td><html:text readonly="true" property="user.name" styleId="user.name" style="width:300px;"/></td>
							<td></td>
						</tr>
		
						<tr>
							<td></td>
							<td>Description</td>
							<td><html:textarea  property="user.description" 
							     				styleClass="textarea" 
							     				style="width:300px; height:50px;"/></td>
							<td></td>
						</tr>	
					
						</tbody>
				  </table>
<!--		    	</fieldset>-->
			</td>
		</tr>
		<tr>				
			<td  align="left" valign="top">
<!--				<fieldset  style="width: 620px;">-->
<!--				<legend>Contact</legend>-->

				<table width="100%">
					<tbody id="usrContactInfo" style="display: none;">
					<tr style="text-align:left">
						<td ></td>
						<td style="width: 15%">Area</td>
						<td><html:text property="contact.area" style="width:260px;"/></td>
						<td></td>
					</tr>
				
					<tr>
						<td></td>
						<td>Phone</td>
						<td><html:text property="contact.phone" style="width:260px;"/></td>
						<td></td>
				
					</tr>
					<tr>
						<td></td>
						<td>Mobile</td>
						<td><html:text property="contact.mobile" style="width:260px;"/></td>
						<td></td>
					</tr>
					
					<tr>
						<td></td>
						<td>E-Mail</td>
						<td><html:text property="contact.email" styleId="contact.email" style="width:260px;"/></td>
						<td></td>
					</tr>
					</tbody>
				</table>
<!--				</fieldset>-->
			</td>
			</tr>
			<tr>
				<td  align="left" valign="top">
<!--					<fieldset  style="width: 620px;">-->
<!--					<legend>Change Password</legend>-->
					
					<table width="100%">
						<tbody id="usrPassInfo" style="display: none;">
						<tr>
							<td></td>
							<td style="width:15%" nowrap="nowrap">Current Password</td>
							<td><html:password property="userAccessControl.userPassword" style="width:170px;"/></td>
							<td></td>
						</tr>
					
						<tr>
							<td></td>
							<td nowrap="nowrap">New Password</td>
							<td><html:password property="newPassword" styleId="newPassword" style="width:170px;"/>&nbsp;</td>
							<td></td>
					
						</tr>
						<tr>
							<td></td>
							<td nowrap="nowrap">Confirm</td>
							<td><html:password property="confirmNewPassword" styleId="confirmNewPassword" style="width:170px;"/></td>
							<td></td>
						</tr>
						
						<tr>
							<td></td>
							<td>&nbsp;</td>
							<!-- <td colspan='2' align="right"><font color="red" face="Verdana"
								size="2px"><b><html:messages id="message"
							    message="true">${message}</html:messages></b></font></td>  -->
							<td>&nbsp;</td>
						</tr>

						</tbody>
					</table>
<!--					</fieldset>-->
				</td>
			</tr>

			<tr >	
				<td colspan="4">
					<table border="0" style="float: right; bottom: 20px;">
						<tr>
							<td><input type=button class="submit" name='btBack'    value='Back'  onclick="javascript:actionForm('list')" >
							    &nbsp;
								<input type=button class="submit" name='btSubmit'  value='Send'  onclick="javascript:actionForm('authentication')">			    
							</td>
						</tr>
					</table>
				</td>
			</tr>
</html:form>
</table>
</body>
</html>

