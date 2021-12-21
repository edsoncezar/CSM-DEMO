<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="version" class="br.com.alcatellucent.csm.SystemInformation" scope="session" ></jsp:useBean>
<%-- <%@include file="pages/commonHeader.jsp" %> --%>
<%@ taglib 	uri="http://struts.apache.org/tags-html" 		prefix="html" %>
<%@ taglib 	uri="http://struts.apache.org/tags-logic" 		prefix="logic" %>
<%@ taglib 	uri="http://java.sun.com/jstl/core_rt" 			prefix="c"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" 			prefix="fmt"%>  
<%@ taglib  uri="http://displaytag.sf.net/el"				prefix="display"%>
<%@ taglib 	uri="http://java.sun.com/jsp/jstl/functions"	prefix="fn"%>
<%@ taglib  uri="/WEB-INF/AgyaTagLib.tld"					prefix="se"%>


<link href="css/portal.css" rel="styleSheet" type="text/css"></link>
<link href="dhtmlXtree/css/dhtmlXTree.css" 	rel="STYLESHEET" type="text/css">

<script language="javascript" src="dhtmlXtree/js/dhtmlXCommon.js"></script>
<script language="javascript" src="dhtmlXtree/js/dhtmlXTree.js"></script>
<script language="javascript" src="scripts/prototype.js"></script>
<script language="javascript" src="scripts/portal.js"></script>
<script language="javascript" src="scripts/calendar.js"></script>
<script language="javascript" src="scripts/optionTransfer.js"></script>

<script language="javascript">

	function actionConfirm(action){
	
		if($('newPassword').value == "" ){
		 alert('The new password can not be null');
			return;
		}	
		
		if($('newPassword').value.length < 3 ){
		 alert('The new password length does not minor than three');
			return;
		}	
		
		if($('newPassword').value != $('confirmNewPassword').value ){
		 alert('The new password does not confer');
			return;
		}	
		
		actionForm(action)
	}
	
	function actionForm(action){
		
		document.forms[0].action.value = action;
		document.forms[0].submit();	
		
	}
	

</script>
<html>
<head>
<title>ALU-Agya</title>
</head>
<body>
<div style="position: relative; top: 150px;" align="center">
<html:form styleId="formLogin" method="post" action="login.do" >
<table cellpadding="0" cellspacing="0">
	
		<html:hidden property="action" styleId="action" />
		<tr>
			<td>
			<fieldset style="background-image: url('img/loginBackground.gif');" class="fieldset">
			<legend style="background-image: url('img/loginBackground.gif');" class="legend" >Login</legend>

			<table width="250px;" cellpadding="2" cellspacing="2">
				<tr>
					<td colspan="4">
					<p><img title="Alcatel-Lucent" height="66" alt="Alcatel-Lucent"
						src="img/logo.jpg" border="0"></p>
					<p align="right"><img src="img/logoSmall.gif"
						border="0"></p>
					</td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td>Username</td>
					<td><html:text property="user.userName" maxlength="60"
						style="width:150px;" /></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>Password</td>
					<td><html:password property="userAccessControl.userPassword"
						maxlength="60" style="width:150px;" /></td>
					<td>&nbsp;</td>
				</tr>

				<c:if
					test="${LoginForm.userAccessControl.flChangeNextLogin eq 'true'}">
					<tr>
						<td>&nbsp;</td>
						<td>New Password</td>
						<td align="right"><html:password property="newPassword"
							styleId="newPassword" maxlength="60" style="width:150px;" /></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>Confirm New Password</td>
						<td align="right"><html:password
							property="confirmNewPassword" styleId="confirmNewPassword" maxlength="60" style="width:150px;" />
						</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
					<td>&nbsp;</td>
						<td colspan='2' align="right"><input type="button"
						 class="submit" name='Save' value='Save' id='btnSave' onclick="javascript:actionConfirm('savePassword')"></td>
						<td>&nbsp;</td>
					</tr>
				</c:if>
				<tr>
					<td>&nbsp;</td>
					<td colspan='2' align="right"><font color="red" face="Verdana"
						size="2px"><b><html:messages id="message"
						message="true">${message}</html:messages></b></font></td>
					<td>&nbsp;</td>
				</tr>
				
				<c:if
					test="${LoginForm.userAccessControl.flChangeNextLogin != 'true'}">
				<tr>
					<td>&nbsp;</td>
						<td colspan='2' align="right"><input type="submit" class="submit"
						name='Login' value='Login' id='btnLogin' onclick="javascript:actionForm('auth')"></td>
					<td>&nbsp;</td> 
				</tr>
				</c:if>

			</table>
	</fieldset>
	</td>
	</tr>
	<tr>
	<td style="text-align: center;" > Version:
	<jsp:getProperty name="version" property="version" />
	</td>
	</tr>
	</table>
	</html:form>

</div>
</body>
</html>
