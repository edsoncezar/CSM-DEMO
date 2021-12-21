<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="pages/commonHeader.jsp" %>

<html>
<head>
<title>ALU-Agya</title>
</head>
<body>
<div style="position: relative; top: 150px;" align="center">
<table cellpadding="0" cellspacing="0">
		<tr>
			<td>
			<fieldset style="background-image: url('img/loginBackground.gif');" class="fieldset">
			<table width="250px;" cellpadding="2" cellspacing="2">
				<tr>
					<td colspan="4">
					<p><img title="Alcatel-Lucent" height="66" alt="Alcatel-Lucent"
						src="img/logo.jpg" border="0"></p>
					<p align="right"><img src="img/logoSmall.gif"
						border="0"></p>
						<p><font color="#554295">&nbsp; Alcatel-Lucent - All rights reserved.</font></p>
					</td>
				</tr>
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
</div>
</body>
</html>
