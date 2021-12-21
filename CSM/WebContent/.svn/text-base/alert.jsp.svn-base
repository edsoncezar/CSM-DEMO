<%@include file="pages/commonHeader.jsp" %>
<html>
<head>
<link href="css/displaytag.css" 	rel="STYLESHEET" type="text/css">
<script>
	function actionForm(action){
		
		document.forms[0].action.value = action;
		document.forms[0].submit();	
	}

</script>
<!-- Script incluido por Igor Takats em 2007-set-04 -->

<script language="JavaScript">
function confirmCancel() {

	var close = confirm("You really want to close this Alert ?");

	if (close == true) {
		actionForm('close');
	} else {
		alert('Action canceled');
	}
	
}
</script>
</head>
<body>
<%@include file="pages/errorContainer.jsp"%>
<br>
<fieldset style="width:580; align:center" >
<legend>Alert</legend>
<html:form styleId="AclForm" method="post" action="alert.do">
 <html:hidden property="action"/>
 <html:hidden property="alert.id"/>
  
	<table border="0" cellpadding="0" cellspacing="0" width="300" style="border : none;">		
		<tr>
			<td width="100">Alde</td>
			<td>
				<html:text property="alert.alde" size="20" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td width="100">Date</td>
			<td>
				<html:text property="alert.formattedDate" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td width="100">Time</td>
			<td>
				<html:text property="alert.formattedTime" readonly="true"/>
			</td>
		</tr>			
		<tr>
			<td width="100">Attack Type</td>
			<td>
				<html:text property="alert.attackTypeDescription" size="20" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td width="100">Status</td>
			<td>
				<html:text property="alert.statusDescription" size="20" readonly="true"/>
			</td>
		</tr>	
		<tr>
			<td width="100">Associated ACL</td>
			<td>
				<html:text property="alert.alde" size="20" readonly="true" onclick="showHide('acl')"/>
			</td>
		<tr>
			<td width="100">
				<div id="acl" style="display: none;">
					<br>Teste
				</div>
			</td>
		</tr>
	
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
	
		<tr>
			<td></td>
			<td align="right"><input type=button class="submit" value="Close Alert" onclick="confirmCancel();"></td>
			<td></td>
		</tr>	
	</table>
</html:form>
		<c:if test="${alertForm.acl.id!=null}">
			<b><a href="#" onclick="showHide('acldetails')">This alert has a associated ACL, click here to view details.</a></b>
			<div id="acldetails" style="display: none;">
				<jsp:include page="./pages/aclAlertView.jsp"></jsp:include>
			</div>
		</c:if>
</fieldset>
</body>
</html>