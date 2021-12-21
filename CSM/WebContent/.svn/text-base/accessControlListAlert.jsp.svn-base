<%@include file="pages/commonHeader.jsp" %>
<html>
<head>
<link href="css/displaytag.css" 	rel="STYLESHEET" type="text/css">
<script>
	function actionForm(action){
		
		document.forms[0].action.value = action;
		document.forms[0].submit();	
	}

	function adjustThreshold() {	
		if (document.getElementById('acl.action').value == 0) {
			document.getElementById('acl.threshold').value = 65535;
			document.getElementById('acl.threshold').readOnly = true;
		} else if (document.getElementById('acl.action').value == 2) {
			document.getElementById('acl.threshold').value = 0;
			document.getElementById('acl.threshold').readOnly = true;
		} else {
			document.getElementById('acl.threshold').readOnly = false;
		}
	}
	
	function adjustForm() {
		adjustThreshold();
	}
	
	function protectForm(){
		if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE}', true)){
			protectAllFields(true);
		}
	}

</script>
<!-- Script incluido por Igor Takats em 2007-set-04 -->

<script language="JavaScript">
function confirmRefuse() {

	var refuse = confirm("You really want to ignore the ACL ?");

	if (refuse == true) {
		actionForm('refuse');
	} else {
		alert('Action canceled');
	}
}
</script>
</head>
<body onLoad="javascript:adjustForm();protectForm();">
<%@include file="pages/errorContainer.jsp"%>
<br>
<fieldset style="width:580; align:center" >
<legend>Access Control List - Alerts</legend>
<html:form styleId="AclForm" method="post" action="acl.do">
 <html:hidden property="action"/>
 <html:hidden property="acl.contextId"/>
  
	<table border="0" cellpadding="0" cellspacing="0" width="300" style="border : none;">		
		<tr>
			<td colspan="5">&nbsp;</td>
		</tr>
		
		<tr>
			<td>Name</td>
			<td><html:text property="acl.name" size="20"/></td>
			<td colspan="2"></td>
			<td colspan="2">Status
			<html:select disabled="true" property="acl.status" size="1" >
	 		<html:option value="1">Active</html:option>
			<html:option value="2">Inactive</html:option>
			<html:option value="3">Ignored</html:option>
	 		</html:select>
	 		</td>
		</tr>
	
		
		<tr>
			<td>Description&nbsp;</td>
			<td colspan="3">
				<html:textarea property="acl.description" cols="50"  rows="5"/>				
			</td>
			<td>&nbsp;&nbsp;&nbsp;</td>
		</tr>
		
		<tr>
			<td colspan="5">&nbsp;</td>
		</tr>
		
		<tr>
			<td>Priority</td>
			<td><html:text disabled="true" property="acl.priority" size="20"/></td>
			<td colspan="2">&nbsp;</td>
			<td class="checkbox"><html:checkbox disabled="true" value="on" property="acl.isAldeAcl" />ALDE-ACL</td>
			<td ></td>
		</tr>
		
		<tr>
			<td colspan="5">&nbsp;</td>
		</tr>
		
		<tr>
			<td></td>
			<td>Source</td>
			<td>Mask</td>
			<td>Destination</td>
			<td>Mask</td>
		</tr>
		
		<tr>
			<td>IP Address</td>
			<td><html:text property="acl.sourceIp" size="20"/></td>
			<td><html:text property="acl.sourceIpMask" size="20"/></td>
			<td><html:text property="acl.destIp" size="20"/></td>
			<td><html:text property="acl.destIpMask" size="20"/></td>
		</tr>
		
		<tr>
			<td>Port</td>
			<td><html:text property="acl.sourcePort" size="20"/></td>
			<td>&nbsp;</td>
			<!--  
			<td><html:text property="acl.sourcePortMask" size="20"/></td>
			 -->
			<td><html:text property="acl.destPort" size="20"/></td>
			<td>&nbsp;</td>
			<!--  
			<td><html:text property="acl.destPortMask" size="20"/></td>
			 -->
		</tr>
				
		<tr>
			<td colspan="5">&nbsp;</td>
		</tr>
		
		<tr>
			<td>Protocol</td>
			<td colspan="2">
			<html:select property="acl.protocol">
				<html:option value="ANY">ANY</html:option>
		        	<c:forEach var="protocol" items="${sessionScope.listProtocol}">
						<html:option value="${protocol.id}">${protocol.name}</html:option>
					</c:forEach>
		    </html:select>	
			</td>
			<td colspan="2">&nbsp;</td>
		</tr>
		
		<tr>
			<td colspan="5">&nbsp;</td>
		</tr>
		<tr>
			<td>Action</td>
			<td colspan="4">
			<html:select property="acl.action" styleId="acl.action" size="1" style="width:450" onchange="javascript:adjustThreshold();">
			<!-- <html:option value="0">Accept</html:option> -->			
			<html:option value="1">Shape</html:option>
			<html:option value="2">Drop</html:option>
			</html:select>
			</td>
		</tr>
		<tr>
			<td colspan="5">&nbsp;</td>
		</tr>
		
		<tr>
			<td>Threshold</td>
			<td colspan="3"><html:text property="acl.threshold" styleId="acl.threshold" size="40"/> 0 - 65535</td>
			<td>&nbsp;</td>
		</tr>
		
		<tr>
			<td colspan="5">&nbsp;</td>
		</tr>
		
		<tr>
			<td nowrap="nowrap">Time to live</td>
			<td colspan="2"><html:text property="acl.timeToLive" size="40"/></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		
		<tr>
			<td colspan="5">&nbsp;</td>
		</tr>
		
		<tr>
			<td colspan="4">
				<a href="#" onclick="showHide('listDeviceDppm');"><b>View Devices and DPPMs</b></a>
			</td>
		</tr>
		
		<tr>
			<td colspan="5">
				<display:table style="display: none" id="listDeviceDppm" name="requestScope.LIST_DPPM_ALERT" defaultsort="1">
					<display:column property="device.name" title="Device" width="10%" media="html"/>					
					<display:column property="name" title="DPPM" width="10%" media="html"/>
				</display:table>
			</td>
		</tr>
		
		<tr>
			<td>&nbsp;</td>
			<td align="right" colspan="4">
				<p align="right">
					<input type=button  class="submit" name='btRefuse'  value='Ignore' 	onclick="javascript:confirmRefuse()">	
					<span class="enable">	
						<input type=button  class="submit" name='btBack'    value='Back' 	onclick="javascript:actionForm('list')">
					</span>
					<input type=button  class="submit" name='btSubmit'  value='Send' 	onclick="javascript:actionForm('save')">
				</p>
			</td>
		</tr>
		
	</table>
</html:form>
</fieldset>
</body>
</html>