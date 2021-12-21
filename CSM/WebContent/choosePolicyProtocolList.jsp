<%@include file="pages/commonHeader.jsp" %>
<html>
<head>
<title>ALU-Agya</title>

<link href="css/displaytag.css" 	rel="STYLESHEET" type="text/css">
	
<script language="JavaScript">
	
	var urlToSend = "scheduler.do?action=edit&scheduledGroup.id" + $('scheduledGroup.id');
	
	// loading all protocol ids in a array --------------------
	var protocols_arrFlowsValues = new Array();
	var protocols_arrUpValues 	 = new Array();
	var protocols_arrUpUnit		 = new Array();
	var protocols_arrDownValues  = new Array();
	var protocols_arrDownUnit	 = new Array();
	var protocols_arrId			 = new Array();
	
	<c:forEach items="${sessionScope.LIST_PROTOCOL_DETAILS}" var="protocol" varStatus="i">
		protocols_arrId[${i.index}] = "${protocol.scheduledProtocol.scheduledTrafficValues.id}";
	</c:forEach>
	
	// loading all groups ids in a array --------------------
	var groups_arrFlowsValues 	= new Array();
	var groups_arrUpValues 		= new Array();
	var groups_arrUpUnit		= new Array();
	var groups_arrDownValues 	= new Array();
	var groups_arrDownUnit		= new Array();
	var groups_arrId			= new Array();
	
	<c:forEach items="${sessionScope.LIST_PROTOCOL_DETAILS}" var="group" varStatus="i">
		groups_arrId[${i.index}] = "${group.scheduledGroup.id}";
	</c:forEach>	
	
	groups_arrId.sort();
	
	var aTemp = new Array();
	var aId = '';
	var j = 0;
	
	for(i=0; i<groups_arrId.length; i++) {
		if (groups_arrId[i] != aId) {
			aId = groups_arrId[i];
			aTemp[j] = aId;
			j++;
			//document.write('<br>' + aTemp[j]);	
		}
	}
	
	groups_arrId = aTemp;
		
	atemp = [];


// ------------- Inicio das funções de manipulação ---------------------------------------------------

	function confirmIt() {
	
		var msg = "The groups and/or protocols values will be saved on disc. Are you sure ?";
		
		var ok = confirm(msg);
		
		if (ok) {
			return true;
		} else {
			message('Update aborted !', 'WAR');
			return false;
		}
	}
	
	
	
	function actionForm(action){
		
		if (!confirmIt()) return false;
		
	//	if(action == 'save'){
			if(!submitMultiple()){
				return false;
			}
	//	}	
	
	//	document.forms[0].action='testeTabela.jsp';
	//	document.forms[0].target='_blank';
		
		document.forms[0].action.value = action;
		document.forms[0].submit();	
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
	function verifyUnit(unit) {

		// var wUnit = (unit=='MB' || unit=='KB') ? unit : 'MB'; 
	
		return ((unit=='Mb' || unit=='Kb') ? unit : 'Mb'); 
	}
		
	function populateArrays_protocols(){
		var valueError = true;
		for(var i = 0; i < protocols_arrId.length; i++){ 
		 	protocols_arrFlowsValues[i]	= $(protocols_arrId[i]+'_flowsValues').value;
			protocols_arrUpValues[i] 	= $(protocols_arrId[i]+'_upStreamValue').value;
			protocols_arrUpUnit[i]		= verifyUnit($(protocols_arrId[i]+'_upStreamUnit').value);
			protocols_arrDownValues[i] 	= $(protocols_arrId[i]+'_downStreamValue').value;
			protocols_arrDownUnit[i]	= verifyUnit($(protocols_arrId[i]+'_downStreamUnit').value);
			
			if(!validateFlows($(protocols_arrId[i]+'_flowsValues'))){valueError = false;}
			if(!validateUpstream($(protocols_arrId[i]+'_upStreamValue'))){valueError = false;}
			if(!validateDownstream($(protocols_arrId[i]+'_downStreamValue'))){valueError = false;}
			
		} 
		return valueError;
	}

	function populateArrays_groups(){
		var valueError = true;
		for(var i = 0; i < groups_arrId.length; i++){ 
			groups_arrUpValues[i] 	= $(groups_arrId[i]+'_upStreamValue').value;
			groups_arrUpUnit[i]		= $(groups_arrId[i]+'_upStreamUnit').value;
			groups_arrDownValues[i] = $(groups_arrId[i]+'_downStreamValue').value;
			groups_arrDownUnit[i]	= $(groups_arrId[i]+'_downStreamUnit').value;
			
			if(!validateUpstream($(groups_arrId[i]+'_upStreamValue'))){valueError = false;}
			if(!validateDownstream($(groups_arrId[i]+'_downStreamValue'))){valueError = false;}
			
		}
		return valueError;
	}
	
	function submitMultiple(){
	
		if(populateArrays_groups() && populateArrays_protocols()){
			createSet_groups($('groupslValues'));
			createSet_protocols($('protocolValues'));
			return true;
		}else{
			return false;
		}
		
	}
	
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
	
	function validateUpstream(obj){	
		if(	obj.value.length < 1 ||  obj.value < 0 || obj.value > 4294967295 || !isNumber(obj.value)){
			message('Insert a valid <b>Upstream</b> value <b>(between 0 - 4294967295)</b>', 'ERR');
			return false;
		}else{
			return true;
		}
	}
	
	function createSet_groups(objContainer){
		var valueList = "";
		for(var i = 0; i < groups_arrId.length; i++){
			valueList += groups_arrId[i] + ','  + ','  + groups_arrUpValues[i] + ',' + groups_arrUpUnit[i] + ',' + groups_arrDownValues[i] + ',' + groups_arrDownUnit[i] + ',end' + ';';
		}
		
		objContainer.value = valueList;
		
		var a = 1;
	}
	
	function createSet_protocols(objContainer){
		var valueList = "";
		for(var i = 0; i < protocols_arrId.length; i++){
			valueList += protocols_arrId[i] + ',' + protocols_arrFlowsValues[i] + ','  + protocols_arrUpValues[i] + ',' + protocols_arrUpUnit[i] + ',' + protocols_arrDownValues[i] + ',' + protocols_arrDownUnit[i] + ',end' + ';';
		}
		
		objContainer.value = valueList;
	}
	
	function loadUnit(obj,val){
		if(val.toUpperCase()=='MB'){
			$(obj).innerHTML='Mbit/s';
		}else if(val.toUpperCase()=='KB'){
			$(obj).innerHTML='Kbit/s';
		}else 
			$(obj).innerHTML=val;
			
	}
</script>
</head>
<%@ include file="pages/errorContainer.jsp" %>
<br>
<body>
<fieldset style="width:650px; align:center" >
<legend>Available Protocols</legend>
<html:form method="post" action="choosePolicy.do" >
<html:hidden property="action"/>

<%-- Caso Seja uma DPPM --%>
<html:hidden property="processorPacket.id"/>
<html:hidden property="processorPacket.name"/>
<html:hidden property="processorPacket.description"/>
<html:hidden property="processorPacket.number"/>
<html:hidden property="processorPacket.trafficPolicyId"/>
 
<%-- Caso Seja um Device --%>
<html:hidden property="device.id" />

<%-- Caso Seja um Context --%>
<html:hidden property="context.id" />

<%-- Lista de Protocolos que serão alterados --%>
<html:hidden property="protocolValues" styleId="protocolValues" />

<%-- Lista de Grupos que serão alterados --%>
<html:hidden property="groupsValues" styleId="groupslValues" />

   
   <table cellpadding="0" style="width:100%; height: 100%; border: none;" cellspacing="0" style="">
   <tr> 
      <td>
   		<display:table style="width:100%; height: 80%;" name="sessionScope.LIST_PROTOCOL_DETAILS" id="row" export="false">
	    <display:setProperty name="basic.show.header" value="true" />
    	<display:column width="20%" property="scheduledGroup.name" group="1" title="Group name" maxLength="50"   />
    	
    	<display:column width="20%"  group="2" title="DWStream" >
    	    	<input 	type="text"
					id="${row.scheduledGroup.id}_downStreamValue" 
					value="${row.scheduledGroup.downStreamValue}" 
					style="width: 70%;"  
					maxLength="10" 
					onblur="validateFlows(this)">
					
					<b 	onclick="changeUnit('${row.scheduledGroup.id}_downStreamUnit', this)" 
						style="cursor: pointer;" id="${row.scheduledGroup.id}_GroupDownUnitContainer">
						<script>loadUnit('${row.scheduledGroup.id}_GroupDownUnitContainer','${row.scheduledGroup.downStreamUnit}');
						</script>
					</b>
										
					<input type="hidden" id="${row.scheduledGroup.id}_downStreamUnit" value="${row.scheduledGroup.downStreamUnit}">											
    	</display:column>
		
	   	<display:column width="20%"  group="3" title="UPStream">
    			<input 	type="text"
					id="${row.scheduledGroup.id}_upStreamValue" 
					value="${row.scheduledGroup.upStreamValue}" 
					style="width: 70%;"  
					maxLength="10" 
					onblur="validateFlows(this)"/>
				<b 	onclick="changeUnit('${row.scheduledGroup.id}_upStreamUnit', this)" 
					style="cursor: pointer;" id="${row.scheduledGroup.id}_GroupUpUnitContainer">
					<script>loadUnit('${row.scheduledGroup.id}_GroupUpUnitContainer','${row.scheduledGroup.upStreamUnit}');
					</script>
				</b>
				
				<input type="hidden" id="${row.scheduledGroup.id}_upStreamUnit" value="${row.scheduledGroup.upStreamUnit}">
    	</display:column>
    	
    	<display:column width="1%" property="scheduledProtocol.name" title="Protocol"></display:column>
    	
    	<display:column width="20%"   title="UPStream" >
    			<input 	type="text"
					id="${row.scheduledProtocol.scheduledTrafficValues.id}_upStreamValue" 
					value="${row.scheduledProtocol.scheduledTrafficValues.upStreamValue}" 
					style="width: 70%;"  
					maxLength="10" 
					onblur="validateFlows(this)"/>
					
				<b 	onclick="changeUnit('${row.scheduledProtocol.scheduledTrafficValues.id}_upStreamUnit', this)" 
					style="cursor: pointer;" id="${row.scheduledProtocol.scheduledTrafficValues.id}_ProtocolUpUnitContainer">
					<script>loadUnit('${row.scheduledProtocol.scheduledTrafficValues.id}_ProtocolUpUnitContainer','${row.scheduledProtocol.scheduledTrafficValues.upStreamUnit}');
					</script>
				</b>
									
				<input type="hidden" id="${row.scheduledProtocol.scheduledTrafficValues.id}_upStreamUnit" value="${row.scheduledProtocol.scheduledTrafficValues.upStreamUnit}">															
    	</display:column>
    	
    	<display:column width="20%"  title="DWStream" >
    			<input 	type="text"
					id="${row.scheduledProtocol.scheduledTrafficValues.id}_downStreamValue" 
					value="${row.scheduledProtocol.scheduledTrafficValues.downStreamValue}" 
					style="width: 70%; "  
					maxLength="10" 
					onblur="validateFlows(this)"/>  
				<b 	onclick="changeUnit('${row.scheduledProtocol.scheduledTrafficValues.id}_downStreamUnit', this)" 
					style="cursor: pointer;" id="${row.scheduledProtocol.scheduledTrafficValues.id}_ProtocolDownUnitContainer">
					<script>loadUnit('${row.scheduledProtocol.scheduledTrafficValues.id}_ProtocolDownUnitContainer','${row.scheduledProtocol.scheduledTrafficValues.downStreamUnit}');
					</script>
				</b>
				<input type="hidden" id="${row.scheduledProtocol.scheduledTrafficValues.id}_downStreamUnit" value="${row.scheduledProtocol.scheduledTrafficValues.downStreamUnit}">															  	
    	</display:column>
    	
    	<display:column width="10%" title="Flows" maxLength="20">
    			<input 	type="text"
					id="${row.scheduledProtocol.scheduledTrafficValues.id}_flowsValues" 
					value="${row.scheduledProtocol.scheduledTrafficValues.flowsValues}" 
					style="width: 100%;"  
					maxLength="3" 
					onblur="validateFlows(this)"/>
    	</display:column>
    	
   		</display:table>
      </td>
   </tr>
   <tr><td style="text-align: right">
     	<input type=button  class="submit" name='btSubmit' value='Save Protocol/Group Settings' onclick="javascript:actionForm('save')">
	    </td>
   </tr>
   </table>
</html:form>

</fieldset>
</body>
</html>