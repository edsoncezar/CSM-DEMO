<%@include file="pages/commonHeader.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ALU-Agya</title>
</head>
<body>


<script>
	function actionForm(action){
		
		document.forms[0].action.value = action;
		document.forms[0].submit();	
	}
</script>
 <br><br>
<fieldset align="center">
<legend>Formulário</legend>

<html:form styleId="formUser" method="post" action="users.do">
 <html:hidden property="action"/>
 <html:hidden property="id"/> 
 <html:hidden property="contextId" value="0"/>
  
	<table border="0" width="550 px" cellpadding="3" cellspacing="1" align="center">
		<tr>
			<td>Name</td>
			<td>
			     <html:text property="name" maxlength="60" size="45 px"/>
			 </td>
		</tr>
		<tr>
			<td>E-mail</td>
			<td>
				<html:text property="mail" maxlength="60" size="45 px"/>				
			</td>
		</tr>
		
		<tr>
			<td>Password</td>
			<td>
				<html:password property="password" maxlength="60" size="60px"/>				
			</td>
		</tr>
		
		<tr>
			<td align="center" colspan="2">
				<table>
					<tr>
			   		    <td align="center"><input type=button class="submit" name='btBack'    value='Back'  onclick="javascript:actionForm('list')" ></td>
						<td align="center"><input type=button class="submit" name='btSubmit'  value='Send'  onclick="javascript:actionForm('save')"></td>
					</tr>
				</table>
			</td>			
		</tr>
	</table>


</html:form>
</fieldset>

</body>
</html>

