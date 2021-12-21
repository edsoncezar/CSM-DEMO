<%@include file="pages/commonHeader.jsp"%>
<html>
<head>
<title>ALU-Agya</title>
<script language="javascript" src="scripts/logging/html-form-input-mask.js"></script>
<script> 

	var protRootAdmin = false;

	function actionForm(action){
	
		if($('protocol.name').value.length < 1){
			message('Insert a valid <b>Name</b>', 'ERR');
			return false;
		}
		if($('protocol.description').value.length < 1){
			message('Insert a valid <b>Description</b>', 'ERR');
			return false;
		}
		if(	$('protocol.internalNumber').value < 0 			||
			$('protocol.internalNumber').value > 49){
			message('Insert a valid <b>Internal Number</b> value <b>(between 0 - 49)</b>', 'ERR');
			return false;
		}
		if(	$('protocol.flowsValues').value < 0 ||
			$('protocol.flowsValues').value > 255 ){
			message('Insert a valid <b>Flows</b> value <b>(between 0 - 255)</b>', 'ERR');
			return false;
		}
		if(	$('protocol.downStreamValue').value < 0 ||
			$('protocol.downStreamValue').value > 4294967295){
			message('Insert a valid <b>Downstream</b> value <b>(between 0 - 4294967295)</b>', 'ERR');
			return false;
		}
		if(	$('protocol.upStreamValue').value < 0 ||
			$('protocol.upStreamValue').value > 4294967295){
			message('Insert a valid <b>Upstream</b> value <b>(between 0 - 4294967295)</b>', 'ERR');
			return false;
		}

		document.forms[0].action.value = action; document.forms[0].submit();	
	} 
	
	var arrUsedNumbers = new Array();
	<c:forEach items="${requestScope.USED_NUMBERS}" var="usedNumbers" varStatus="i">
		arrUsedNumbers[${i.index}] = ${usedNumbers};		 
	</c:forEach>
	
	function checkInternalNumber(number){
		if(number.value.length > 0){
			if(!isNumber(number.value)){
				message("<br>Please insert a <b>number</b>.", "ERR");
				number.value = "${ProtocolForm.protocol.internalNumber}";
			}
		}
		arrUsedNumbers.each(
			function(iNumber){
				if(number.value!="" && number.value !="${ProtocolForm.protocol.internalNumber}" && number.value == iNumber){
					message("This number is <b>already in use</b>. <br>Please, choose another one.", "ERR");
					number.value = "${ProtocolForm.protocol.internalNumber}";
				}
			}
		)
	}
	
	function protectForm(){
	
		var protRootAdmin = (checkCrendentials('${sessionScope.userProfile.userRole.name}', '${ROOT_ADMIN_ROLE}', false));
		
		if((checkCrendentials('${sessionScope.userProfile.userRole.name}', '${MASTER_ROLE}', false))){
			protectAllFields(true);
		}
		
		if((checkCrendentials('${sessionScope.userProfile.userRole.name}', '${ROOT_ADMIN_ROLE}', false))){
		} else {
			unprotectSpecific();	
		}	
	}
	
	function unprotectSpecific() {
	
		$('protocol.upStreamValue').style.background = "";
		$('protocol.upStreamValue').readOnly = false;
		$('protocol.upStreamValue').onkeydown ="checkNumber(this, '${ProtocolForm.protocol.upStreamValue}');" 
		$('protocol.upStreamValue').onblur="checkNumber(this, '${ProtocolForm.protocol.upStreamValue}');"
		$('protocol.upStreamUnit').disabled = false;

		$('protocol.flowsValues').style.background = "";
		$('protocol.flowsValues').readOnly = false;
		$('protocol.flowsValues').onkeydown ="checkNumber(this, '${ProtocolForm.protocol.flowsValues}');" 
		$('protocol.flowsValues').onblur="checkNumber(this, '${ProtocolForm.protocol.flowsValues}');"

		$('protocol.downStreamValue').style.background = "";
		$('protocol.downStreamValue').readOnly = false;
		$('protocol.downStreamValue').onkeydown ="checkNumber(this, '${ProtocolForm.protocol.downStreamValue}');" 
		$('protocol.downStreamValue').onblur="checkNumber(this, '${ProtocolForm.protocol.downStreamValue}');"
		$('protocol.downStreamUnit').disabled = false;				

		$('protocol.control').style.background = "";
		$('protocol.control').readOnly = false;
		$('protocol.control').onkeydown ="checkNumber(this, '${ProtocolForm.protocol.control}');" 
		$('protocol.control').onblur="checkNumber(this, '${ProtocolForm.protocol.control}');"
		$('protocol.controlUnit').disabled = false;

		$('protocol.priority').style.background = "";
		$('protocol.priority').readOnly = false;
		$('protocol.priority').onkeydown ="checkNumber(this, '${ProtocolForm.protocol.priority}');" 
		$('protocol.priority').onblur="checkNumber(this, '${ProtocolForm.protocol.priority}');"

		$('btnSubmit').disabled = false;
		$('btnSubmit').style.backgroundImage = "";
		// $('btnSubmit').addEventListener("onclick", "actionForm('save')", false);
		$('btnSubmit').onclick=actionForm('save');
	
	
	}
	
</script>
</head>
<body onload="protectForm()">
<%@include file="pages/errorContainer.jsp"%>
<br>
<fieldset style="width:590;align: center;">

<legend>Protocols</legend>
<html:form method="post" action="/protocol.do">
<html:hidden property="action" />
<html:hidden property="protocol.id" />

<%--  Estes campos estão reservados para uso futuro -->
<html:hidden property="protocol.outputVlanValue" value="0"/>
<html:hidden property="protocol.outputVlanUnit" value="0"/>
<html:hidden property="protocol.outputmPlsValue" value="0"/>
<html:hidden property="protocol.outputmPlsUnit" value="0"/>--%>

<table border="0" width="100%" cellpadding="0" cellspacing="0" align="left">
	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>	
		<td >Name</td>
		<td>
			<html:text property="protocol.name" size="60" styleId="protocol.name" onblur="toUppercase(this)"/>
			<b style="color: red; ">*</b>
		</td>
		<td>&nbsp;</td>	
	</tr>
	
	<tr>
		<td colspan="4">&nbsp;</td>	
	</tr>

	<tr>
		<td>&nbsp;</td>	
		<td >Description</td>	
		<td><html:textarea property="protocol.description" cols="90"  rows="5" styleId="protocol.description" onblur="toUppercase(this)"/></td>
		<td>&nbsp;</td>	
	</tr>

	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>

	<tr>
		<td>&nbsp;</td>
     	<td colspan="2" class="checkbox">
     		<html:checkbox property="protocol.isRealTime" styleId="protocol.isRealTime">Real Time&nbsp;&nbsp;</html:checkbox>
			<html:checkbox property="protocol.aclUsed" styleId="protocol.aclUsed">ACL</html:checkbox>
 		</td>
 		<td>&nbsp;</td>	
	</tr>
	
	<tr>
		<td colspan="4">&nbsp;</td>		
	</tr>
	
	
	<tr>
		<td>&nbsp;</td>	
		<td nowrap="nowrap">Internal number &nbsp;&nbsp;</td>
		<td>
			<html:text	property="protocol.internalNumber" 
						styleId="protocol.internalNumber"
						styleClass="text input_mask mask_time"
						style="width:100px;"
						onkeydown ="checkNumber(this, '${ProtocolForm.protocol.internalNumber}');"
						onblur="checkInternalNumber(this);"
						maxlength="2">
			</html:text>
		</td>
		<td>&nbsp;</td>	
	</tr>
	
	<tr>
		<td colspan="4">&nbsp;</td>		
	</tr>
	
	<tr>
		<td>&nbsp;</td>	
		<td nowrap="nowrap" colspan="2"><h4>Default Values</h4></td>
		<td>&nbsp;</td>
	</tr>
	
	
	<tr>
		<td>&nbsp;</td>	
		<td>Flows</td>
		<td>
			<html:text property="protocol.flowsValues" styleId="protocol.flowsValues" style="width:100px;" 
			onkeydown ="checkNumber(this, '${ProtocolForm.protocol.flowsValues}');" 
			onblur="checkNumber(this, '${ProtocolForm.protocol.flowsValues}');"></html:text>
			</td>
		<td>&nbsp;</td>	
	</tr>

	<tr>
		<td>&nbsp;</td>	
		<td>Downstream</td>
		<td>
			<html:text property="protocol.downStreamValue" styleId="protocol.downStreamValue" style="width:100px;"
			onkeydown ="checkNumber(this, '${ProtocolForm.protocol.downStreamValue}');" 
			onblur="checkNumber(this, '${ProtocolForm.protocol.downStreamValue}');"></html:text>
			<html:select property="protocol.downStreamUnit" styleId="protocol.downStreamUnit" style="width=50">
				<html:option value="Mb">Mbit/s</html:option>[Kbits/Mbits]
				<html:option value="Kb">Kbit/s</html:option>[Kbits/Mbits]
			</html:select>
		</td>
		<td>&nbsp;</td>	
	</tr>

	<tr>
		<td>&nbsp;</td>	
		<td>Upstream</td>
		<td>
			<html:text property="protocol.upStreamValue" styleId="protocol.upStreamValue" style="width:100px;"
			onkeydown ="checkNumber(this, '${ProtocolForm.protocol.upStreamValue}');" 
			onblur="checkNumber(this, '${ProtocolForm.protocol.upStreamValue}');"></html:text>
			<html:select property="protocol.upStreamUnit" styleId="protocol.upStreamUnit" style="width=50">
				<html:option value="Mb">Mbit/s</html:option>[Kbits/Mbits]
				<html:option value="Kb">Kbit/s</html:option>[Kbits/Mbits]
			</html:select>
		</td>
		<td>&nbsp;</td>
	</tr>
	
	<!-- Igor 2007-10-22 FS#105 -->
	<tr>
		<td>&nbsp;</td>	
		<td>Control</td>
		<td>
			<html:text property="protocol.control" styleId="protocol.control" style="width:100px;"
			onkeydown ="checkNumber(this, '${ProtocolForm.protocol.control}');" 
			onblur="checkNumber(this, '${ProtocolForm.protocol.control}');"></html:text>
			<html:select property="protocol.controlUnit" styleId="protocol.controlUnit" style="width=50">
				<html:option value="Mb">Mbit/s</html:option>[Kbits/Mbits]
				<html:option value="Kb">Kbit/s</html:option>[Kbits/Mbits]
			</html:select>
		</td>
		<td>&nbsp;</td>
	</tr>
	
		<!-- Igor 2007-10-22 FS#105 -->
	<tr>
		<td>&nbsp;</td>	
		<td>Priority</td>
		<td>
			<html:text property="protocol.priority" styleId="protocol.priority" style="width:100px;"
			onkeydown ="checkNumber(this, '${ProtocolForm.protocol.priority}');" 
			onblur="checkNumber(this, '${ProtocolForm.protocol.priority}');"></html:text>
		</td>
		<td>&nbsp;</td>
	</tr>
	
	<tr>
		<td colspan="4">&nbsp;</td>		
	</tr>
	<tr>
			<td colspan="4">
			<table width=100% style="text-align:right"><tr style="text-align:right">
				<td>
			  	  <span class="enable">
					<input type=button class="submit" name='btBack'    value='Back'  onclick="javascript:actionForm('list')" >&nbsp;
				</span>
		    		<input id="btnSubmit" type=button class="submit" name='btSubmit'  value='Send'  onclick="javascript:actionForm('save')">
				</td>
			    </tr>
			 </table>			
			</td>
	</tr>
	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>

</table>
<!-- Default Values -->

</html:form>
</fieldset>
</body>
</html>
