<%@include file="pages/commonHeader.jsp"%>
<html>
<head>
<title>ALU-Agya</title>
<script>
	function actionForm(action){
	/*
		if(action=='save'){
			if($('name').value == "" ){
		 	alert('The name can not be null');
				return;
			}	
		}*/
		document.forms[0].action.value = action;
		document.forms[0].submit();	
	}

	var arrUsedNumbers = new Array();
	<c:forEach items="${requestScope.USED_NUMBERS}" var="usedNumbers" varStatus="i">
		arrUsedNumbers[${i.index}] = ${usedNumbers};		 
	</c:forEach>
	
	function checkInternalNumber(number){
		if(number.value.length > 0){
			if(!isNumber(number.value)){
				message("<br>Please insert a <b>number</b>.", "ERR");
				number.value = "${ProcessorPacketForm.processorPacket.number}";
			}
			else if(number.value!= 1 && number.value!= 2 ){
				message("<br><b>Number must be 1 or 2<b> .", "ERR");
				number.value = "${ProcessorPacketForm.processorPacket.number}";
			}
		}
		arrUsedNumbers.each(
			function(iNumber){
				if(number.value!="" && number.value !="${ProcessorPacketForm.processorPacket.number}" && number.value == iNumber){
					message("This number is <b>already in use</b>. <br>Please, choose another one.", "ERR");
					number.value = "${ProcessorPacketForm.processorPacket.number}";
				}
			}
		)
	}
	
	function protectForm(){
		if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE}', true)){
			protectAllFields(true);
		}
	}
	
	function askAndSave() {
		confirmDialog("The Sample Threshold <B>MUST</B> be equal to the sample in the sFlow probe!<BR><B>Do you want to continue</B>?", "actionForm('save')", "");
	}

</script>
</head>
<body onload="protectForm()">
<%@include file="pages/errorContainer.jsp"%>
<br>
<fieldset style="width: 640;"><legend>Packet
Processor</legend> <html:form action="/processorPacket.do" method="post">
	<html:hidden property="action" />
	<html:hidden property="processorPacket.id" />
	<html:hidden property="processorPacket.device.id" />


	<table cellpadding="1" cellspacing="4">
		<tr>
			<td colspan="6">&nbsp;</td>
		</tr>

		<tr>
			<td>&nbsp;</td>
			<td colspan="5">Device - ${requestScope.DEVICE.name}</td>
		</tr>

		<tr>
			<td colspan="6">&nbsp;</td>
		</tr>
		
		<tr>
			<td>&nbsp;</td>
			<td>Name</td>
			<td><html:text property="processorPacket.name" styleId="name"
				style="width:310px;" /><b style="color: red; ">*</b></td>
			<td align="right">Number</td>
			<td align="right"><html:text property="processorPacket.number"
				style="width:40px;"
				onkeydown="checkNumber(this, '${ProcessorPacketForm.processorPacket.number}');"
				onblur="checkInternalNumber(this);" /><b style="color: red; ">*</b></td>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td>&nbsp;</td>
			<td>Description</td>
			<td colspan="3" align="left"><html:textarea
				property="processorPacket.description" styleClass="textarea"
				cols="60" rows="5" style="width:480px;" /></td>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td>&nbsp;</td>
			<td>Sample Threshold</td>
			<td colspan="3" align="left"><html:text
				property="processorPacket.sampleThreshold" styleClass="textarea"
				style="width:40px;"
				onkeydown="checkNumber(this, '${ProcessorPacketForm.processorPacket.sampleThreshold}');"
				/><b style="color: red; ">*</b></td>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td>&nbsp;</td>
			<td>Traffic Policy</td>
			<td colspan="2"><html:select
				property="processorPacket.trafficPolicyId">
				<html:option value="">---</html:option>
				<c:forEach var="traffic" items="${sessionScope.listTraffic}">
					<html:option value="${traffic.id}">${traffic.name}</html:option>
				</c:forEach>
			</html:select></td>
		</tr>

		<tr>
			<td>&nbsp;</td>
			<td>ALDE</td>
			<td colspan="2"><html:select property="processorPacket.aldeId">
				<html:option value="">---</html:option>
				<c:forEach var="alde" items="${sessionScope.listAlde}">
					<html:option value="${alde.id}">${alde.name}</html:option>
				</c:forEach>
			</html:select></td>
		</tr>
		<tr>
			<td colspan="6">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="5" align="right"><span class="enable">
			<input type=button class="submit" name='btBack' value='Back'
				onclick="javascript:actionForm('list')"> </span>
			&nbsp; <input type=button class="submit" name='btSubmit' value='Send'
				onclick="javascript:askAndSave()"></td>
			<td>&nbsp;</td>

		</tr>
	</table>
</html:form></fieldset>
</body>
</html>