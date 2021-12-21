<%@include file="pages/commonHeader.jsp"%>
<html>
<head>
<title>ALU-Agya</title>
<script type="text/JavaScript">
	function actionForm(action) {
	/*
		if(action=='save'){
		
			if($('device.name').value == "" ){
				message('Insert a valid <b>Device Name</b>', 'MSG');
				return false;
			}
			var objtraffic = $('trafficpolicyid').options[$('trafficpolicyid').selectedindex].value; 

			if($('devicemanager.name').value == "" ){
				message('Insert a valid <b>Device ManagetName</b>', 'MSG');
				return false;
			}
			if($('devicemanager.host').value == "" ){
			 	message('please enter an <b>IP Address</b>', 'MSG');
				return false;
			}
			if($('devicemanager.poolperiod').value == "" ){
				message('please enter a <b> Pool Period</b>', 'MSG');
				return false;
			}
		}
		*/
		document.forms[0].action.value = action;
		document.forms[0].submit();
	}
	
	function protectForm(){
		if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE}', true)){
			protectAllFields(true);
		}
	}
	
</script>
</head>
<body onload="protectForm()">
<%@include file="pages/errorContainer.jsp"%>
<br>
<html:form styleId="DeviceForm" method="post" action="device.do">
	<fieldset style="width: 640px;">
		<legend>Device</legend> 
		<html:hidden property="action" /> 
		<html:hidden property="device.id" /> 
		<html:hidden property="device.contextId" />
<table style="width: 620px;">
<tr style="height: 270px;" valign="top">
	<td valign="top">
	<table width="100%">
		<tr>
			<td><jsp:include page="tabMenu.jsp">
				<jsp:param name="tabNames" value="device,deviceManager" />
				<jsp:param name="tabDescription" value="Device Data,Device Manager Data" />
				<jsp:param name="tabID" value="1" />
				<jsp:param name="currentTab" value="device" />
			</jsp:include></td>
		</tr>
	</table>
	<br>
	<div id="device" style="vertical-align: top;">
		<table border="0" cellpadding="4" cellspacing="0">
			<tr>
			<td>&nbsp;</td>
			<td >Name</td>
			<td><html:text property="device.name" styleId="device.name" maxlength="60"
				style="width:350px;" /><b style="color: red; "> *</b></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td style="vertical-align: top;">Description</td>
			<td style="vertical-align: top;">
				<html:textarea property="device.description" cols="40"
				rows="10" style="width:450px;" /><b style="color: red; "> *</b></td>
			<td>&nbsp;&nbsp;&nbsp;</td>
		</tr>
		
		<tr>
			<td>&nbsp;</td>
			<td >Traffic Policy</td>
			<td colspan="2">
			
			<c:set var="trafficPolicies" value="${sessionScope.listTraffic}" />
			
			<html:select property="device.trafficPolicyId" styleId="trafficPolicyId">
				<html:option value="">---</html:option>
				<html:optionsCollection  name="trafficPolicies"  value="id" label="name" />
				
		    </html:select>	
			</td>
		</tr>
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
</table>
</div>

	<!-- //////////////////////////////// ASM ///////////////////////////////////////////////////////////////////// -->
	
<div id="deviceManager" style="display: none; vertical-align: top;">
	 <html:hidden property="deviceManager.device.id" /> 
	 <html:hidden property="deviceManager.id" />
	 <table cellpadding="4" cellspacing="0" align="left" width="100%">
		<tr>
			<td></td>
			<td nowrap="nowrap">Name</td>
			<td>
				<html:text property="deviceManager.name" style="width:200px;"  styleId="deviceManager.name"/><b style="color: red; "> *</b>
			</td>
			<td width="30%"></td>
		</tr>
		<tr>
			<td></td>
			<td nowrap="nowrap">IP Address</td>
			<td><html:text property="deviceManager.host"
				style="width:200px;" styleId="deviceManager.host" /><b style="color: red; "> *</b></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td nowrap="nowrap">Poll Period (min.)</td>
			<td><html:text property="deviceManager.poolPeriod" styleId="deviceManager.poolPeriod"
				style="width:200px;" 
				onkeydown ="checkNumber(this, '${DeviceForm.deviceManager.poolPeriod}');" 
				onblur="checkNumber(this, '${DeviceForm.deviceManager.poolPeriod}');"/><b style="color: red; "> *</b></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td nowrap="nowrap" style="vertical-align: top;">Description</td>
			<td style="vertical-align: top; width: 98%;"><html:textarea property="deviceManager.description"
				styleClass="textarea" cols="40" rows="5" /><b style="color: red; "> *</b></td>
			<td></td>
		</tr>
	</table>
</div>
	
	<!--//////////////////////////////// ASM /////////////////////////////////////////////////////////////////////-->
</td>
</tr>
<tr>
<td>
	
		<table style="float: right; bottom: 20px;">
			<tr>
				<td align="right">
					<span class="enable"> 
						<input 	type="button" 
								class="submit" 
								name='btBack' 
								value='Back' 
								onclick="javascript:actionForm('list')"> 
					</span> &nbsp; 
				</td>	
				<td align="right">	
					<input 	type="button" 
							class="submit" 
							name='btSubmit' 
							value='Send' 
							onclick="javascript:actionForm('save')">
			 	</td>
			</tr>
		</table>
</td>
</tr>
</table>
	</fieldset>
</html:form>

</body>
</html>

