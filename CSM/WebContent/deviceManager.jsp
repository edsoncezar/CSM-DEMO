<%@include file="pages/commonHeader.jsp" %>
<script>
	function actionForm(action){
		
		document.forms[0].action.value = action;
		document.forms[0].submit();	
	}

</script>
<fieldset>
<legend>Device Manager</legend>
<html:form action="deviceManager.do" method="post" >
<html:hidden property="action" />
<html:hidden property="deviceManager.device.id"/>
<html:hidden property="deviceManager.id"/> 


	<table cellpadding="0" cellspacing="2" width="400px">
		<tr>
			<td></td>
			<td>Name</td>
			<td><html:text property="deviceManager.name" style="width:200px;"/></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>Host</td>
			<td><html:text property="deviceManager.host" style="width:200px;" /></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>Poll Period</td>
			<td><html:text property="deviceManager.poolPeriod" style="width:200px;"/></td>
			<td></td>
		</tr>

		<tr>
			<td></td>
			<td>Description</td>
			<td><html:textarea property="deviceManager.description" styleClass="textarea" cols="40" rows="5"/></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
   		    <td align="center" colspan="2">
   		    	<input type=button class="submit" name='btBack'    value='Back'  onclick="javascript:actionForm('list')" >
				<input type=button class="submit" name='btSubmit'  value='Send'  onclick="javascript:actionForm('save')">
			</td>
			<td></td>
		</tr>



	</table>
</html:form> 
</fieldset>
