<%@include file="pages/commonHeader.jsp"%>
<html>
<head>
  <title>Traffic Group Description</title>
  
	<link href="css/displaytag.css" 	rel="STYLESHEET" type="text/css">
<script language="javascript">
  	checkRefresh('<%=null == request.getAttribute("refresh")? false:request.getAttribute("refresh")%>')
	<% request.setAttribute("refresh", Boolean.FALSE);%> 

	function edit(id){
		var schGroupId = $('scheduledGroup.id').value;
		document.location.href = "scheduledProtocol.do?action=edit&scheduledGroup.id="+ schGroupId + "&scheduledProtocol.id=" + id;		
	}
	
	function protectForm(){
		if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE}', true)){
			protectAllFields(true);
		}
	} 
	
	function sendTo(url){
		document.location.href = url;
	}
	
	function actionForm(action){
		if(action == 'save'){
			if(!submitMultiple()){
				return false;
			}
		}else if(action == 'addProtocol' && checkSelected('newProtocols') == false){
			message('Select a protocol to add.', 'ERR');
			return false;
		}else if(action == 'removeProtocol' && checkSelected('selectedProtocols') == false){
			message('Select a protocol to remove.', 'ERR');
			return false;
		}
		document.forms[0].action.value = action;
		document.forms[0].submit();	
	}

	var urlToSend = "scheduler.do?action=edit&scheduledGroup.id" + $('scheduledGroup.id');
	
	// loading all ids in a array --------------------
	var arrFlowsValues 	= new Array();
	var arrUpValues 	= new Array();
	var arrUpUnit		= new Array();
	var arrDownValues 	= new Array();
	var arrDownUnit		= new Array();
	var arrControl		= new Array();
	var arrControlUnit  = new Array();
	var arrPriority		= new Array();
	var arrId			= new Array();
	
	<c:forEach items="${sessionScope.LIST_PROTOCOLS}" var="protocol" varStatus="i">
		arrId[${i.index}] = "${protocol.id}";
	</c:forEach>
	//------------------------------------------------
	//
	function populateArrays(){
		var valueError = true;
		for(var i = 0; i < arrId.length; i++){ 
			arrFlowsValues[i]	= $(arrId[i]+'_flowsValues').value;
			arrUpValues[i] 		= $(arrId[i]+'_upStreamValue').value;
			arrUpUnit[i]		= $(arrId[i]+'_upStreamUnit').value;
			arrDownValues[i] 	= $(arrId[i]+'_downStreamValue').value;
			arrDownUnit[i]		= $(arrId[i]+'_downStreamUnit').value;
			arrControl[i]		= $(arrId[i]+'_control').value;
			arrControlUnit[i]	= $(arrId[i]+'_controlUnit').value;
			arrPriority[i]		= $(arrId[i]+'_priority').value;
			
			if(!validateFlows($(arrId[i]+'_flowsValues'))){valueError = false;}
			if(!validateUpstream($(arrId[i]+'_upStreamValue'))){valueError = false;}
			if(!validateDownstream($(arrId[i]+'_downStreamValue'))){valueError = false;}
			if(!validateControl($(arrId[i]+'_control'))){valueError = false;}
			if(!validatePriority($(arrId[i]+'_priority'))){valueError = false;}
		}
		return valueError;
	}
	function createSet(objContainer){
		var valueList = "";
		for(var i = 0; i < arrId.length; i++){
			valueList += arrId[i] + "," + arrFlowsValues[i] + ","  + arrUpValues[i] + "," + arrUpUnit[i] + "," + arrDownValues[i] + "," + arrDownUnit[i] + "," + arrControl[i] + "," + arrControlUnit[i]+ "," + arrPriority[i] + ";";
		}
		
		objContainer.value = valueList;
	}
	
	function submitMultiple(){
		if(populateArrays()){
			createSet($('protocolValues'));
			return true;
		}else{
			return false;
		}
		
	}
	//------------------------------------------------
	function validateFlows(obj){	
		if(	obj.value.length < 1 || obj.value < 0 || obj.value > 255 || !isNumber(obj.value)){
			message('Insert a valid <b>Flows</b> value <b>(between 0 - 255)</b>', 'ERR');
			return false;
		}else{
			return true;
		}
	}
	function validateDownstream(obj){	
		if(	obj.value.length < 1 || obj.value < 0 || obj.value > 4294967295 || !isNumber(obj.value)){
			message('Insert a valid <b>Downstream</b> value <b>(between 0 - 4294967295)</b>', 'ERR');
			return false;
		}else{
			return true;
		}
	}
	
	function validateControl(obj){	
		if(	obj.value.length < 1 || obj.value < 0 || obj.value > 4294967295 || !isNumber(obj.value)){
			message('Insert a valid <b>Control</b> value <b>(between 0 - 4294967295)</b>', 'ERR');
			return false;
		}else{
			return true;
		}
	}
	
	function validatePriority(obj){	
		if(	obj.value.length < 1 || obj.value < 0 || obj.value > 63 || !isNumber(obj.value)){
			message('Insert a valid <b>Priority</b> value <b>(between 0 - 63)</b>', 'ERR');
			return false;
		}else{
			return true;
		}
	} 
	
	function validateUpstream(obj){	
		if(	obj.value.length < 1 ||  obj.value < 0 || obj.value > 4294967295 || !isNumber(obj.value)){
			message('Insert a valid <b>Upstream</b> value <b>(between 0 - 4294967295)</b>', 'ERR');
			return false;
		}else{
			return true;
		}
	}
	
	function changeUnit(objId, bContainer){
		obj = $(objId);
		if(obj.value.toUpperCase() == 'MB'){
			obj.value = 'Kb';
			bContainer.innerHTML = 'Kbit/s'; 
		}else{
			obj.value = 'Mb';
			bContainer.innerHTML = 'Mbit/s';
		}
	}
	
	function showProtocols(){
		showHide('divActualProtocols');
		showHide('showDefaultButton');
		if($('arrowActualProtocols').src.indexOf("arrow_down") == -1){
			$('arrowActualProtocols').src = "img/arrow_down.png";
		}else{
			$('arrowActualProtocols').src = "img/arrow_up.png";
		} 

	}
	function setDefaulfValues(){
		confirmDialog("Do want apply this configuration for all protocols in this Scheduled Group ?", "defaultValues()", "");
	}
	
	//aqui
	function defaultValues(){
			var upUnitVal;
			var downUnitVal;
			var controlUnitVal;
			if(	validateFlows($('defaultFlows')) && 
				validateUpstream($('defaultUpstream')) &&
				validateDownstream($('defaultDownstream'))&& validateControl($('defaultControl'))){
			
				for(var i = 0; i < arrId.length; i++){
					$(arrId[i]+"_flowsValues").value 			= $('defaultFlows').value; 
					$(arrId[i]+"_upStreamValue").value 			= $('defaultUpstream').value;
					if($('defaultUpUnit').value.toUpperCase()=='MB') 
					{
						upUnitVal='Mbit/s';
					}
					else if($('defaultUpUnit').value.toUpperCase()=='KB'){
						upUnitVal='Kbit/s';
					}
					if($('defaultDownUnit').value=='Mb') 
					{
						downUnitVal='Mbit/s';
					}
					else if($('defaultDownUnit').value=='Kb'){
						downUnitVal='Kbit/s';
					}
					if($('defaultControlUnit').value.toUpperCase()=='MB') 
					{
						controlUnitVal='Mbit/s';
					}
					else if($('defaultControlUnit').value.toUpperCase()=='KB'){
						controlUnitVal='Kbit/s';
					}
					$(arrId[i]+"_upStreamUnit").value 			= $('defaultUpUnit').value; 
					$(arrId[i]+"_UpUnitContainer").innerHTML  	= upUnitVal; 
					$(arrId[i]+"_downStreamValue").value 		= $('defaultDownstream').value; 
					$(arrId[i]+"_downStreamUnit").value			= $('defaultDownUnit').value; 
					$(arrId[i]+"_DownUnitContainer").innerHTML  = downUnitVal; 
					$(arrId[i]+"_control").value  				= $('defaultControl').value;
					$(arrId[i]+"_ControlUnitContainer").innerHTML  	= controlUnitVal;
					$(arrId[i]+"_controlUnit").value  			= $('defaultControlUnit').value;
					$(arrId[i]+"_priority").value  				= $('defaultPriority').value;
				}
			}
	}

	function showDefaultValuesTable(){
		showHide('divDefaltValues');
		if($('arrowDefaultValues').src.indexOf("arrow_down") == -1){
			$('arrowDefaultValues').src = "img/arrow_down.png";
		}else{
			$('arrowDefaultValues').src = "img/arrow_up.png";
		}  
	}
	
	function loadUnit(obj,val){
		if(val=='Mb'){
			$(obj).innerHTML='Mbit/s';
		}else if(val=='Kb'){
			$(obj).innerHTML='Kbit/s';
		}else 
			$(obj).innerHTML=val;
			
	}
</script>
</head>

<body onload="protectForm();">
<%@include file="pages/errorContainer.jsp"%> 
<br>
<fieldset style="width:640px; align:center;" >
<legend >Group Description</legend>
<html:form method="post" action="/scheduledGroup.do" > 
<html:hidden property="action" />
<html:hidden property="scheduledGroup.id" styleId="scheduledGroup.id" />
<html:hidden property="scheduledGroup.flowsValue" value="0" />
<html:hidden property="protocolValues" styleId="protocolValues" />
	<table  align="center" cellpadding="4" cellspacing="0" style="border-style: none; width: 600px">
		
		<tr>
		<td colspan="4">
			<table style="border-style: none;" cellpadding="3" cellspacing="0">
				<tr>
					<td >&nbsp;</td>
					<td style="width: 150px;">Name:</td>
					<td>
						<html:text property="scheduledGroup.name" style="width:400px;" readonly="true"/>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td></td>
					<td>Description:</td>
					<td>
						<html:textarea property="scheduledGroup.description" style="width:400px; height:50px" readonly="true"/>
					</td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td>Downstream</td>
					<td>
						<html:text property="scheduledGroup.downStreamValue" style="width:100px;"
						onkeydown ="checkNumber(this, '${ScheduledGroupForm.scheduledGroup.downStreamValue}');" 
						onblur="checkNumber(this, '${ScheduledGroupForm.scheduledGroup.downStreamValue}');"></html:text>
						<html:select property="scheduledGroup.downStreamUnit" style="width=50">
							<html:option value="Mb">Mbit/s</html:option>[Kbits/Mbits]
							<html:option value="Kb">Kbit/s</html:option>[Kbits/Mbits]
						</html:select>
					</td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td>Upstream</td>
					<td>
						<html:text property="scheduledGroup.upStreamValue" style="width:100px;"
							onkeydown ="checkNumber(this, '${ScheduledGroupForm.scheduledGroup.upStreamValue}');" 
							onblur="checkNumber(this, '${ScheduledGroupForm.scheduledGroup.upStreamValue}');"></html:text>
						<html:select property="scheduledGroup.upStreamUnit" style="width=50">
							<html:option value="Mb">Mbit/s</html:option>[Kbits/Mbits]
							<html:option value="Kb">Kbit/s</html:option>[Kbits/Mbits]
						</html:select>
					</td>
					<td></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr height="30px" valign="top">
			<td colspan="4" valign="top" align="center" style="width: 550px; height: 40px; " >
				<table width="500" style="border-style: none;" height="40" cellpadding="0" cellspacing="0">
					<tr>
						<td>
						<div onclick="showProtocols(this)" style="cursor: pointer; height: 30px; width: 200px;" >
							<table style="width:200px;" cellpadding="0" cellspacing="0">
								<tr>
									<td>Show Protocols<td>
									<td><img src="img/arrow_down.png" style="float: right; top: 0px;" border="0" id="arrowActualProtocols"></td>
								</tr>
							</table>
						</div > 
						</td>							
						<td align="left">
							<div  onclick="showDefaultValuesTable()" style="display:block; cursor: pointer; height: 30px; width: 200px;" id="showDefaultButton">
								<table style="width:200px;" cellpadding="0" cellspacing="0">
									<tr>
										<td>Set Default Values<td>
										<td><img src="img/arrow_down.png" style="float: right; top: 0px;" border="0" id="arrowDefaultValues"></td>
									</tr>
								</table>
							</div >
						</td>
					</tr>
				</table>
			</td>
		</tr>
			
		<tr align="center" >
			<td colspan="4" valign="top">
				<div id="divActualProtocols" style="display: block;">
				<div id="divDefaltValues" style="display: none; top: 2px;">
				<table style="width:590px">
					<thead>
						<tr>
							<td></td>
							<td>Flow</td>
							<td>Up</td>
							<td>Down</td>
							<td>Ctrl</td>
							<td>Prty</td>
							
						</tr>
						<tr>
							<td><input type="button" value="Apply to All" onclick="setDefaulfValues()"></td>
							<td width="30px"><input type="text" id="defaultFlows" value="0" style="width: 30px;"  maxLength="3" onblur="validateFlows(this)"></td>
							<td width="100px">
								<input 	type="text" id="defaultUpstream" 
										value="0" 
										style="width: 75px;" 
										maxlength="10"
										onblur="validateUpstream(this)">
								<b onclick="changeUnit('defaultUpUnit', this)" style="cursor: pointer;">Mbit/s</b>
								<input type="hidden" id="defaultUpUnit" value="Mb">
							</td>
							<td width="100px">
								<input 	type="text" id="defaultDownstream" 	
										value="0" 
										style="width: 75px;" 
										maxlength="10"
										onblur="validateDownstream(this)">
								<b onclick="changeUnit('defaultDownUnit', this)" style="cursor: pointer;">Mbit/s</b>
								<input type="hidden" id="defaultDownUnit" value="Mb">
							</td>
							<td width="100px">
								<input 	type="text" id="defaultControl" 	
										value="0" 
										style="width: 75px;" 
										maxlength="10"
										onblur="validateControl(this)">
								<b onclick="changeUnit('defaultControlUnit', this)" style="cursor: pointer;">Mbit/s</b>
								<input type="hidden" id="defaultControlUnit" value="Mb">
							</td>
							<td width="100px">
								<input 	type="text" id="defaultPriority" 	
										value="0" 
										style="width: 75px;" 
										maxlength="10"
										onblur="validatePriority(this)">
<!--								<b onclick="changeUnit('defaultDownUnit', this)" style="cursor: pointer;">Mbit/s</b>-->
<!--								<input type="hidden" id="defaultDownUnit" value="Mb">-->
							</td>							
						</tr>
					</thead>
				</table>
				
				</div><br>
				<display:table name="sessionScope.LIST_PROTOCOLS"  htmlId="tbActualProtocols" id="row" style="width:590px; align:center;" >
					<display:column class="checkbox"  width="20" title="<input style=\"color:white;font-weight: bold;border: none;font-size: 10px;vertical-align: middle; \" type=\"checkbox\" onclick=\"checkAll(this,'selectedProtocols')\">" >
						<html:multibox property="selectedProtocols" value="${row.id}" styleId="selectedProtocols_${row.id}"/>
					</display:column>
					<display:column sortable="true" property="name" title="Protocol" maxLength="15" />       
					<display:column sortable="true" property="internalNumber" title="#" maxLength="3" width="20" />
					<display:column sortable="true" sortProperty="scheduledTrafficValues.flowsValues" title="Flow" width="30">
						<input type="hidden" id="${row.scheduledTrafficValues.id}_id"  value="${row.scheduledTrafficValues.id}" >
						<input 	type="text"
								id="${row.id}_flowsValues" 
								value="${row.scheduledTrafficValues.flowsValues}" 
								style="width: 30px;"  
								maxLength="3" 
								onblur="validateFlows(this)">
					</display:column> 
					<display:column sortable="true" sortProperty="scheduledTrafficValues.upStreamValue" title="Up"  width="75" nowrap="nowrap">
						<input 	type="text"
								id="${row.id}_upStreamValue" 
								value="${row.scheduledTrafficValues.upStreamValue}" 
								style="width: 75px;" 
								maxlength="10"
								onblur="validateUpstream(this)">
								<b 	onclick="changeUnit('${row.id}_upStreamUnit', this)"
									id="${row.id}_UpUnitContainer" 
									style="cursor: pointer;"><script>loadUnit('${row.id}_UpUnitContainer','${row.scheduledTrafficValues.upStreamUnit}');</script></b>
						<input type="hidden" id="${row.id}_upStreamUnit" value="${row.scheduledTrafficValues.upStreamUnit}">
					</display:column>	
					<display:column sortable="true" sortProperty="scheduledTrafficValues.downStreamValue" title="Down"  width="75">
						<input type="text"
								id="${row.id}_downStreamValue" 
								value="${row.scheduledTrafficValues.downStreamValue}" 
								style="width: 75px;"  
								maxLength="10" 
								onblur="validateDownstream(this)">
								<b 	onclick="changeUnit('${row.id}_downStreamUnit', this)"
									id="${row.id}_DownUnitContainer"
									style="cursor: pointer;"><script>loadUnit('${row.id}_DownUnitContainer','${row.scheduledTrafficValues.downStreamUnit}');</script></b>
						<input type="hidden" id="${row.id}_downStreamUnit" value="${row.scheduledTrafficValues.downStreamUnit}">
					</display:column>
					<display:column sortable="true" sortProperty="scheduledTrafficValues.control" title="Ctrl"  width="75">
						<input 	type="text"
								id="${row.id}_control" 
								value="${row.scheduledTrafficValues.control}" 
								style="width: 75px;"  
								maxLength="10" 
								onblur="validateControl(this)">
								<b onclick="changeUnit('${row.id}_controlUnit', this)"
									id="${row.id}_ControlUnitContainer"
									style="cursor: pointer;"><script>loadUnit('${row.id}_ControlUnitContainer','${row.scheduledTrafficValues.controlUnit}');</script></b>
						<input type="hidden" id="${row.id}_controlUnit" value="${row.scheduledTrafficValues.controlUnit}">
					</display:column>
					<display:column sortable="true" sortProperty="scheduledTrafficValues.priority" title="Prty"  width="75">
						<input 	type="text"
								id="${row.id}_priority" 
								value="${row.scheduledTrafficValues.priority}" 
								style="width: 75px;"  
								maxLength="10" 
								onblur="validatePriority(this)">
						<input type="hidden" id="${row.id}_priority" value="${row.scheduledTrafficValues.priority}">
					</display:column>					
				</display:table>

				<input type="button" style="float: right;" onclick="javascript:actionForm('removeProtocol')" value="remove selected">
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="1"></td>
			<td colspan="3" align="right">
				
			</td>
		</tr>
	</table>
	
	<b onclick="showHide('listGroup');" style="cursor: pointer;">Add New Protocol</b>

	<div style="display: none;" id="listGroup" align="center">
	<table width="400">
		<thead>
			<tr>
				<td class="checkbox" width="10"><input type='checkbox' onclick="checkAll(this, 'newProtocols')"></td>
				<td width="370">Protocol</td>
				<td width="20" align="center" onclick="showHide('listGroup');" style="cursor: pointer;">[-]</td>
			</tr>	
		</thead>
		<tbody style="height: 100px; width:370px; overflow: auto;"  >
			<c:if test="${empty LIST_AVAILABLE_PROTOCOLS}">
				<tr height="20px">
					<td colspan="2" align="center">All available protocols is already binded to this Group</td>
				</tr>
			</c:if>
			<c:forEach items="${LIST_AVAILABLE_PROTOCOLS}" var="availableProtocol">
			<tr height="20px">
				<td class="checkbox" width="10"><html:multibox property="newProtocols" value="${availableProtocol.id}" /></td>
				<td width="340">${availableProtocol.name}</td>
			</tr>							
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="3" style="border-top: 1px solid silver;">
					<input 	type="button" 
							style="float: right;" 
							onclick="actionForm('addProtocol');" 
							value="add selected">
				</td>
			</tr>
		</tfoot>
	</table>
	</div>
	
	
	

	<table style="border: none;" width=100%>
		<tr align="right">		
			<td style="text-align: right" nowrap="nowrap">
				<span class="enable">
					<input   class="submit"  type="button" value="Back" onclick="javascript:sendTo(urlToSend);">&nbsp;
				</span>
				<input   class="submit"  type="button" value="Save" onclick="javascript:actionForm('save');">
			</td>
		</tr>
	</table>
 </html:form>
</fieldset>
</body>  
</html> 