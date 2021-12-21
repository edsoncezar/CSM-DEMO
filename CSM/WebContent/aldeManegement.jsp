<%@include file="pages/commonHeader.jsp"%>
<!--/////////////////////// ALDE INFO //////////////////////////////////////////////////  -->
<html>
<head>
<script language="javascript">
	var tabToGo='';
	var tab='';
<c:if test="${not empty requestScope.ValidateFrom}">
	tabToGo="${requestScope.ValidateFrom}";
</c:if>

	function actionForm(action){
		document.forms['aldeForm'].action.value = action;
		document.forms['aldeForm'].submit();	
	}
	
	
	function showConfiguration() {
		if ($('alde.mode').value == 1) {
			$('alde.adjustmentmin').disabled = true;
			$('alde.adjustmentmax').disabled = true;
		} else {
			$('alde.adjustmentmin').disabled = false;
			$('alde.adjustmentmax').disabled = false;
		}
	}

	var url = 'aldeAjax.do?action=getALDEStatus'; 
 	var statusTransport = 1;
	
	function loadAldeStatus() {
	
		$('ALDEStatus').innerHTML =  "Fetching Status Information...";
		var myAjax = new Ajax.Request( url, {	method: 'get', 
											    parameters:  {id: '${aldeForm.alde.id}'}, 
											    onComplete: showStatus,
											    onSuccess: showSuccess,
											    onFailure: showError} ); 
		showConfiguration();
	 }
	 
	function showSuccess(obreq){
		statusTransport = 1;
	}
	
	function showError(obreq){
		statusTransport = 0;
	}

	function showStatus(obreq){
		if (statusTransport == 1){
			$('ALDEStatus').innerHTML =  obreq.responseText;
		}else{
			$('ALDEStatus').innerHTML =  "Error fetching ALDE information...";
		}
	}
	
	function stopALDE(mode) {
		document.forms['aldeForm'].modeToStartStop.value = mode;
		actionForm('stop');
	}
	
	function startALDE(mode) {
		document.forms['aldeForm'].modeToStartStop.value = mode;
		actionForm('start');
	}
	
	function protectForm(){
		if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE}', true)){
			protectAllFields(true);
		}
	}

</script>

</head>
<body onload="loadAldeStatus();protectForm()">
<%@include file="pages/errorContainer.jsp"%>
<br>

<html:form styleId="aldeForm" method="post" action="alde.do">
	<input type="hidden" name="modeToStartStop" value="" />
	<html:hidden property="action" />
	<html:hidden property="alde.id" />
	<html:hidden property="alde.master" />
	<html:hidden property="alde.contextId" />

	<fieldset style="width: 630px; height: 550px;"><legend style="right: 150px">ALDE</legend>

	<table style="text-align: left; width: 100%;" border="0" cellpadding="4" cellspacing="0">
		<tr>
			<td style="text-align: right" colspan="4"></td>
			<td style="text-align: right" valign="middle">
			<div id="ALDEStatus" onclick='loadAldeStatus()'>ALDE Status</div>
			</td>			
		</tr>
		<tr>
			<table width="100%">
				<tr>
					<td><jsp:include page="tabMenu.jsp">
						<jsp:param name="tabNames" value="registrationSet,settingsSet" />
						<jsp:param name="tabDescription" value="Registration,Settings" />
						<jsp:param name="tabID" value="1" />
						<jsp:param name="currentTab" value="registrationSet" />
					</jsp:include></td>
				</tr>
			</table>
		</tr>
<!--		<tr>-->
<!--			<td align="center" valign="middle"-->
<!--				style="width: 150px; text-align: center; border: 1px solid silver; border-bottom: none;"-->
<!--				id="registrationTab" onclick="changeToSettings();"><b>Registration</b></td>-->
<!--			<td style="width: 2px;"></td>-->
<!--			<td align="center" valign="middle"-->
<!--				style="width: 150px; text-align: center; border: 1px solid silver; border-bottom: none;"-->
<!--				onclick="changeToSettings();"-->
<!--				id="settingsTab">Settings</td>-->
<!--			<td colspan="2">			-->
<!--			</td>-->
<!--		</tr>-->
		<tr>
			<td colspan="5" style="border: 1px solid silver; height: 400px;">					
			<table width="625px" cellpadding="4" cellspacing="0"  id="registrationSet" border="0">
					<tr>
						<td>Name</td>
						<td><html:text property="alde.name" styleId="alde.name"
							maxlength="60" size="30 px" /><b style="color: red; "> *</b>
					</td>
					</tr>
					<tr>
						<td>Host</td>
						<td><html:text property="alde.host" styleId="alde.host"
							maxlength="60" size="30 px" /><b style="color: red; "> *</b></td>
					</tr>
					<tr>
						<td>User</td>
						<td><html:text property="alde.userName"
							styleId="alde.userName" maxlength="60" size="30 px" /><b style="color: red; "> *</b></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><html:password property="alde.userPass"
							styleId="alde.userPass" maxlength="60" size="30 px" /><b style="color: red; "> *</b></td>
					</tr>
					<tr>
						<td style="vertical-align: top;">Description</td>
						<td><html:textarea property="alde.description"
							styleClass="textarea" style="width: 450px; height:150px;" /><b style="color: red; "> *</b></td>
					</tr>
					<tr>
						<td align="left" colspan="3"></td>
					</tr>
					<tr>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr style="height: 27px;">
						<td colspan="3">&nbsp;</td>
					</tr>
			</table>
			<table width="625px" cellpadding="3" cellspacing="0" id="settingsSet" style="display: none;" border="0">
					<tr >
						<td></td>
						<td	 align="left">View :</td>
						<td>
							<html:select property="aldeConfig.status" size="1" styleId="alde.status">
								<html:option value="1">Show Local Settings</html:option>
								<html:option value="2">Show Current Settings</html:option>
							</html:select>						
						</td>
						<td><a href="#" onClick="javascript:actionForm('edit');">(Update Configuration)</a></td>							
					</tr>
					<tr><td colspan="4">&nbsp;</tr>					
					<tr>
						<td></td>
						<td align="left">Mode</td>
						<td><html:select property="aldeConfig.mode" size="1"
							styleId="alde.mode" onchange="javascript: showConfiguration();">
							<html:option value="1"> Static </html:option>
							<html:option value="2"> Dynamic</html:option>
						</html:select></td>
						<td><html:hidden property="aldeConfig.id" /></td>
					</tr>
					<tr>
						<td></td>
						<td align="left">Identification</td>
						<td><html:text property="aldeConfig.identification"
							style="width:177px;" /><b style="color: red; "> *</b></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td align="left">Attack Tolerance</td>
						<td><html:text property="aldeConfig.attacktolerance"
							styleId="alde.attacktolerance" style="width:177px;" /><b style="color: red; "> *</b></td>
						<td>(2 to 10000)</td>
					</tr>
					<tr>
						<td></td>
						<td align="left">Wait Alert</td>
						<td><html:text property="aldeConfig.waitalert"
							styleId="alde.waitalert" style="width:177px;" /><b style="color: red; "> *</b></td>
						<td>(1 to 100)</td>
					</tr>
					<tr>
						<td></td>
						<td align="left">Sample</td>
						<td><html:text property="aldeConfig.sample"
							styleId="alde.sample" style="width:177px;" /><b style="color: red; "> *</b></td>
						<td>(1 to 10000000)</td>
					</tr>
					<tr>
						<td></td>
						<td align="left">Old Net</td>
						<td><html:text property="aldeConfig.oldnet"
							styleId="alde.oldnet" style="width:177px;" /><b style="color: red; "> *</b></td>
						<td>(BaseLine time to 2678400)</td>
					</tr>
					<tr>
						<td></td>
						<td align="left">Safe Poll Mode</td>
						<td><html:text property="aldeConfig.safepollmode"
							styleId="alde.safepollmode" style="width:177px;" /><b style="color: red; "> *</b></td>
						<td>(1 to 2678400/BaseLine Time)</td>
					</tr>
					<tr>
						<td></td>
						<td align="left">Old Base Line</td>
						<td><html:text property="aldeConfig.oldbaseline"
							styleId="alde.oldbaseline" style="width:177px;" /><b style="color: red; "> *</b></td>
						<td>(5 to 95)</td>
					</tr>
					<tr>
						<td></td>
						<td align="left">Current Base Line</td>
						<td><html:text property="aldeConfig.currentbaseline"
							styleId="alde.currentbaseline" style="width:177px;" /><b style="color: red; "> *</b></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td align="left">Base Line Time</td>
						<td><html:text property="aldeConfig.baselinetime"
							styleId="alde.baselinetime" style="width:177px;" /><b style="color: red; "> *</b></td>
						<td>(10 to 2678400)</td>
					</tr>
					<tr>
						<td></td>
						<td align="left">Adjust Min</td>
						<td><html:text property="aldeConfig.adjustmentmin"
							styleId="alde.adjustmentmin" style="width:177px;" /></td>
						<td>(1 to 90)</td>
					</tr>
					<tr>
						<td></td>
						<td align="left">Adjust Max</td>
						<td><html:text property="aldeConfig.adjustmentmax"
							styleId="alde.adjustmentmax" style="width:177px;" /></td>
						<td>(1 to 10000000)</td>
					</tr>
					<tr>
						<td></td>
						<td align="left">Network Filter</td>
						<td><html:text property="aldeConfig.filter"
							styleId="alde.filter" style="width:177px;" /></td>
						<td>(tcpdump filter)</td>
					</tr>
					<tr>
						<td></td>
						<td align="left">Agya - IP</td>
						<td><html:text property="aldeConfig.csmip"
							styleId="alde.csmip" style="width:177px;" /><b style="color: red; "> *</b></td>
						<td>(0.0.0.0 to 255.255.255.255) </td>
					</tr>
					<tr>
						<td></td>
						<td align="left">Agya - PORT</td>
						<td><html:text property="aldeConfig.csmport"
							styleId="alde.csmport" style="width:177px;" /><b style="color: red; "> *</b></td>
						<td>(1 to 65535)</td>							
					</tr>
					<tr><td colspan="4"></td></tr>
					<tr>
					 	<td></td>
					 	<td></td>
						<td align="left"><input type=button class="submit"
							name='btApply' value='Apply Settings'
							onclick="javascript:actionForm('applySettings')">
							<input type=button class="submit"
							name='btReset' value='Reset ALDE'
							onclick="javascript:actionForm('reset')">
						</td>
						<td>
						</td>	
					</tr>					
				</table>			
			<td>
		<tr>
	</table>
	<table cellpadding="0" cellspacing="4">
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td></td>
			<td width="500"></td>
			<td align="center">
			<span class="enable">
				<input type=button class="submit"
					name='btBack' value='Back' onclick="actionForm('list')">
			</span></td>
			
			<td align="center"><input type=button class="submit"
				name='btSubmit' value='Send'
				onclick="javascript:actionForm('save')"></td>
		</tr>
	</table>
	</fieldset>			
</html:form>
<!--/////////////////////// END - ALDE INFO //////////////////////////////////////////////////  -->
</body>
</html>

