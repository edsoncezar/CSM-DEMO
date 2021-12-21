<%@include file="pages/commonHeader.jsp" %>
<html>
<head>
<link href="css/displaytag.css" 	rel="STYLESHEET" type="text/css">
<script>
	function apply(){
		confirmDialog("This is an asynchronous process.<br>" + 
					  "Changes can take some seconds to take effect." , "actionForm('applyAcl')", "");
	}
	
	function actionForm(action){
		document.forms[0].action.value = action;
		document.forms[0].submit();	
	}
</script>
</head>
<body>
<fieldset style="width:650;" >
<legend>Choose the ACLs to be applied</legend>
<html:form styleId="AclForm" method="post" action="chooseAcl.do">
 <html:hidden property="action"/>
 <html:hidden property="acl.contextId"/>
 <html:hidden property="acl.name"/>
	<table style="border:none;width:100%" cellpadding="0" cellspacing="0">				
		<tr> 
      		<td>
   				<display:table style="width:100%" name="sessionScope.listAcl" id="acl" export="false">
    				<display:column width="10px" class="checkbox">
    					<html:checkbox property="acl.aclId" value="${acl.id}" />
     				</display:column>
     			<display:column title="ACL">
    					ACL - ${acl.name}
     			</display:column>
     			<display:column title="DPPM">
    					${AclForm.acl.name}
     			</display:column>
   				</display:table>
      		</td>
   		</tr>
   		
  		<c:if test="${empty sessionScope.listAcl}">
  		<tr>
			<td style="text-align: right;">
				<input type=button disabled="disabled" class="submit" name='btSubmit'  value='Apply' onclick="javascript:apply();">
			</td>
		</tr>
  		</c:if>
  		
  		<c:if test="${not empty sessionScope.listAcl}">
  		<tr>
			<td style="text-align: right;">
				<input type=button class="submit" name='btSubmit'  value='Apply' onclick="javascript:apply();">
			</td>
		</tr>
		</c:if>	
	</table>
</html:form>
</fieldset>
</body>
</html>