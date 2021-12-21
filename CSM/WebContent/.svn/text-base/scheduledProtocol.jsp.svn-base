<%@include file="pages/commonHeader.jsp"%>
<script>
	function actionForm(action){
		if($('scheduledProtocol.name').value.length < 1){
			message('Insert a valid <b>Name</b>', 'ERR');
			return false;
		}
		
		if($('scheduledProtocol.description').value.length < 1){
			message('Insert a valid <b>Description</b>', 'ERR');
			return false;
		}
		
		if($('scheduledProtocol.internalNumber').value.length < 1){
			message('Insert a valid <b>Internal Number</b>', 'ERR');
			return false;
		}
		if(	$('scheduledProtocol.scheduledTrafficValues.flowsValues').value.length < 1 || 
			$('scheduledProtocol.scheduledTrafficValues.flowsValues').value < 0 ||
			$('scheduledProtocol.scheduledTrafficValues.flowsValues').value > 255 ){
			message('Insert a valid <b>Flows</b> value <b>(between 0 - 255)</b>', 'ERR');
			return false;
		}
		if(	$('scheduledProtocol.scheduledTrafficValues.downStreamValue').value.length < 1 ||
			$('scheduledProtocol.scheduledTrafficValues.downStreamValue').value < 0 ||
			$('scheduledProtocol.scheduledTrafficValues.downStreamValue').value > 4294967295){
			message('Insert a valid <b>Downstream</b> value <b>(between 0 - 4294967295)</b>', 'ERR');
			return false;
		}
		if(	$('scheduledProtocol.scheduledTrafficValues.upStreamValue').value.length < 1 ||
			$('scheduledProtocol.scheduledTrafficValues.upStreamValue').value < 0 ||
			$('scheduledProtocol.scheduledTrafficValues.upStreamValue').value > 4294967295){
			message('Insert a valid <b>Upstream</b> value <b>(between 0 - 4294967295)</b>', 'ERR');
			return false;
		}

		document.forms[0].action.value = action; 
		$('frmSchProtcol').submit();	
	} 
</script>
<%@include file="pages/errorContainer.jsp" %>
<br>
<fieldset style="width:630;">
<legend>Protocols</legend>

<html:form method="post" action="scheduledProtocol.do" styleId="frmSchProtcol">
<html:hidden property="action" value="save"/>
<html:hidden property="scheduledProtocol.id" />
<html:hidden property="scheduledGroup.id" />
<html:hidden property="scheduledProtocol.scheduledTrafficValues.id" />

<!--  Estes campos estão reservados para uso futuro -->
<html:hidden property="scheduledProtocol.scheduledTrafficValues.outputVlanValue" value="0"/>
<html:hidden property="scheduledProtocol.scheduledTrafficValues.outputVlanUnit" value="0"/>
<html:hidden property="scheduledProtocol.scheduledTrafficValues.outputmPlsValue" value="0"/>
<html:hidden property="scheduledProtocol.scheduledTrafficValues.outputmPlsUnit" value="0"/>

<table border="0" width="500 px" height="50 px" cellpadding="0" cellspacing="1">
	
	<tr>	
		<td >Name</td>
		<td><html:text property="scheduledProtocol.name" styleId="scheduledProtocol.name" size="60" readonly="true"/></td>
		<td></td>
		<td></td>
	</tr>
	
	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>

	<tr>
		<td >Description</td>
		<td colspan="2"><html:textarea property="scheduledProtocol.description" styleId="scheduledProtocol.description" cols="80"  rows="5" readonly="true"/></td>
		<td></td>
		<td></td>
	</tr>
	
	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>
	
	<tr>
		<td nowrap="nowrap">Internal number  </td>
		<td>
			<html:text 	property="scheduledProtocol.internalNumber"
						styleId="scheduledProtocol.internalNumber" 
						style="width:100px;" 
						readonly="true"/>
		</td>
		<td></td>
		<td></td>
	</tr>
	
	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>
	
	<tr>
		<td nowrap="nowrap"><h4>Traffic Values</h4></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	
	
	<tr>
		<td>Flows</td>
		<td>
			<html:text 	property="scheduledProtocol.scheduledTrafficValues.flowsValues" 
						styleId="scheduledProtocol.scheduledTrafficValues.flowsValues"
					 	style="width:100px;"/>
		</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>Downstream</td>
		<td>
			<html:text 	property="scheduledProtocol.scheduledTrafficValues.downStreamValue" 
						styleId="scheduledProtocol.scheduledTrafficValues.downStreamValue"
						style="width:100px;"/>
			<html:select property="scheduledProtocol.scheduledTrafficValues.downStreamUnit" style="width=50">
				<html:option value="MB">MB</html:option>[Kbits/Mbits]
				<html:option value="kB">KB</html:option>[Kbits/Mbits]
			</html:select>
		</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>Upstream</td>
		<td>
			<html:text 	property="scheduledProtocol.scheduledTrafficValues.upStreamValue" 
						styleId="scheduledProtocol.scheduledTrafficValues.upStreamValue"
						style="width:100px;"/>
						
			<html:select property="scheduledProtocol.scheduledTrafficValues.upStreamUnit" style="width=50">
				<html:option value="MB">MB</html:option>[Kbits/Mbits]
				<html:option value="kB">KB</html:option>[Kbits/Mbits]
			</html:select>
		</td>
		<td></td>
		<td></td>
	</tr>
	
	
	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>
	
		<tr>
			<td></td>
			<td></td>	
			<td></td>	
			<td align="right"><input type="button" class="submit" name='btBack'    value='Back'  onclick="javascript:actionForm('list');" ></td>
			<td>&nbsp;</td>
			<td align="right"><input type="button" class="submit" name='btSubmit'  value='Send'  onclick="javascript:actionForm('save');"></td>
		</tr>

</table>
</html:form>
<!-- Default Values -->
</fieldset>


