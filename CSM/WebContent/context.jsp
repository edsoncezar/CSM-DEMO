<%@include file="pages/commonHeader.jsp"%>
<html>
<head>
<script>
	function actionForm(action) {
	/*	
		if(action=='save'){
			if($('name').value == "" ){
		 	alert('Please enter a name');
				return;
			}
			var objRole = $('trafficPolicyId').options[$('trafficPolicyId').selectedIndex].value; 
		}*/	
		document.forms[0].action.value = action;
		document.forms[0].submit();	
	}
	
		function protectForm() {
			if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE},${OPERATOR_ROLE}', true)){
				protectAllFields(true);
			}
		 	if ($('name').value.toUpperCase() == 'ROOT') {
				document.getElementById('name').readOnly=true;
			}		
	}
</script>
</head>
<body onload="protectForm();"> 
<%@include file="pages/errorContainer.jsp"%>
<br>

<html:form styleId="formContext" method="post" action="context.do">
<fieldset style="width: 640px;; align: center"><legend>Context</legend>
	<html:hidden property="action" />
	<html:hidden property="context.parentId" />
	<html:hidden property="context.id" />

	<table border="0" cellpadding="0" cellspacing="0" align="left">
		<tr>
			<td colspan="4">&nbsp;</td>		
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>Name</td>
			<td><html:text property="context.name" styleId="name" size="80px" />
			<b style="color: red; ">*</b></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>		
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td style="vertical-align: top;">Description&nbsp;&nbsp;</td>
			<td style="vertical-align: top">
				<html:textarea property="context.description" cols="100" rows="10" />
				<b style="color: red; ">*</b>
			</td>
			<td>&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>		
		</tr>		
		<tr>
			<td>&nbsp;</td>
			<td>Traffic Policy&nbsp;</td>
			<td colspan="2">
			<html:select property="context.trafficPolicyId" styleId="trafficPolicyId">
			<html:option value="">---</html:option>
		        	<c:forEach var="traffic" items="${sessionScope.listTraffic}">
				          <html:option value="${traffic.id}">${traffic.name}</html:option>
					</c:forEach>
		    </html:select>	
			</td>
		</tr>

     	<tr>
			<td colspan="4">&nbsp;</td>
		</tr>

<%-- Implementar combo com traffic policies cadastras no mesmo contexto		
		<tr>
			<td align="left">
				Traffic Police
			</td>
			<td align="left" colspan="2">
				<html:select property="context.trafficProfileId">
		        	<c:forEach var="traffic" items="${collTrafficPolice}">
				          <html:option value="${trafficPolice.id}">${trafficPolice.name}</html:option>
					</c:forEach>
		        </html:select>	
			</td>			
		</tr>
--%>
		<tr>
			<td>&nbsp;</td>
			<td align="center">&nbsp;</td>
			<td align="right">
			<span class="enable">	
				<input type=button class="submit"
					name='btBack' value='Back' onclick="actionForm('list')">
			</span>
			<input type=button class="submit" name='btSubmit' value='Send'
				onclick="javascript:actionForm('save')"></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
		</tr>
	</table>
	</fieldset>
</html:form>
</body>
</html>

