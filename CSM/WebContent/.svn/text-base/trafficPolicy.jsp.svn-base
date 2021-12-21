<%@include file="pages/commonHeader.jsp" %>

<html>
<head>
  <title>ALU-Agya</title>
  
  <script>
  
	function actionForm(action){
		
		document.forms[0].action.value = action;
		document.forms[0].submit();	
	}
	
	function protectForm(){
		if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE}', true)){
			protectAllFields(true);
		}
	}
	
	function popCheck() {
		setCheck('trafficPolicy.isActive', 'isActiveForm');
		
	}
</script>
</head>
<body onload="protectForm();popCheck();">
<%@include file="pages/errorContainer.jsp"%>
<br>

<html:form styleId="TrafficPolicyForm" method="post" action="trafficPolicy.do">
<fieldset style="width:630; align:center" >
<legend>Traffic Policy </legend>
 <html:hidden property="action"/>
 <html:hidden property="trafficPolicy.contextId" />
  
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td style="align:  vertical-align: top;">Name&nbsp;</td>
			<td style=" vertical-align: top;">
			     <html:text property="trafficPolicy.name" size="70px"/>
			     <b style="color: red;">*</b>
			 </td>
			 <td>&nbsp;</td>
			 <td>&nbsp;</td>

		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td style=" vertical-align: top;">Description&nbsp;</td>
			<td colspan="2" style="vertical-align: top;">
				<html:textarea  property="trafficPolicy.description" cols="100"  rows="10"/>	
				 <b style="color: red; text-align: top;">*</b>			
			</td>
			 <td>&nbsp;</td>			
		</tr>
		
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		
		<tr>
			<td>&nbsp;</td>	
			<td class="checkbox">
				<html:hidden   property="trafficPolicy.isActive"  styleId="trafficPolicy.isActive" />
				<input type="checkbox" name="isActiveForm" id="isActiveForm" onclick="verifyChecked('isActiveForm','trafficPolicy.isActive')" />
			    Active
			</td>
			<td>&nbsp;</td>	
			<td>&nbsp;</td>				
		</tr>
		<tr>
			<td>&nbsp;</td>	
			<td>&nbsp;</td>	
			<td align="right">
			<span class="enable">	
				<input type=button class="submit" name='btBack'    value='Back'  onclick="javascript:actionForm('list');" >
			</span>	
				&nbsp;&nbsp;
				<input type=button class="submit" name='btSubmit'  value='Send'  onclick="javascript:actionForm('save');">						
			</td>
			<td>&nbsp;</td>				
		</tr>
		
	</table>
</fieldset>
</html:form>

</body>
</html>