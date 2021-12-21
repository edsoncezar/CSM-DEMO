<%@include file="pages/commonHeader.jsp" %>
<html>
<head>

<script language="JavaScript">

	function protectForm() {
	
		var status = $('acl.status');
		var isAldeAcl = $('acl.isAldeAcl');

		if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE}', true)){
			protectAllFields(true);
		}
		else if (status.value != 1  && isAldeAcl.checked ) {
			document.getElementById('acl.status').disabled=true;
			document.getElementById('acl.protocol').disabled=true;
			document.getElementById('acl.action').disabled=true;
			document.getElementById('acl.name').readOnly=true;
			document.getElementById('acl.description').readOnly=true;
			document.getElementById('acl.priority').readOnly=true;
			document.getElementById('acl.isAldeAcl').disabled=true;
			document.getElementById('acl.sourceIp').readOnly=true;
			document.getElementById('acl.sourceIpMask').readOnly=true;
			document.getElementById('acl.destIp').readOnly=true;
			document.getElementById('acl.destIpMask').readOnly=true;
			// document.getElementById('acl.sourcePortMask').readOnly=true;
			document.getElementById('acl.destPort').readOnly=true;
			// document.getElementById('acl.destPortMask').readOnly=true;
			document.getElementById('acl.protocolMask').readOnly=true;
			document.getElementById('acl.sourcePort').readOnly=true;
			document.getElementById('acl.threshold').readOnly=true;
			document.getElementById('acl.timeToLive').readOnly=true;
			document.getElementById('acl.sourcePort').readOnly=true;
		}		
		adjustThreshold();
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
	
	function actionList(action) {
			document.forms[0].action.value = action;
			document.forms[0].submit();				
	}
	
	function actionForm(action) {		
		//if(validateForm()){	
			document.forms[0].action.value = action;
			document.forms[0].submit();
		//}	
	}
/*	function validateForm(){
<!--		if(!validateIP($('acl.sourceIp'))){-->
<!--			alert("Enter a valid IP Address");-->
<!--			return false;-->
<!--		}-->
		
		if( $('acl.sourceIpMask').value == ""  ){
			alert("Enter a valid IP Mask");
			return false;		
		}			
		
		if( $('acl.sourceIpMask').value == ""  ){
			alert("Enter a valid IP Mask");
			return false;		
		}
		
		if(!validateIP($('acl.destIp'))){
			alert("Enter a valid destination IP Address");
			return false;
		}
		
		if(!validateIP($('acl.destIp'))){
			alert("Enter a valid destination IP Address");
			return false;
		}
		
		if( $('acl.destIpMask').value == ""  ){
			alert("Enter a valid IP destination Mask");
			return false;		
		}
		
		return true;
	}
	*/
	function confirmRefuse() {
		var refuse = confirm("Do you really want to refuse it ?");
		if (refuse == true) {
			actionForm('refuse');
		} else {
			alert('Action canceled');
		}
	}
</script>
</head>
<!--  <body onLoad="protectForm()"> -->
<body onLoad="adjustForm();protectForm();">
<%@include file="pages/errorContainer.jsp"%>
<br>
<fieldset style="width:550; align:center" >
<legend>Access Control List</legend>
<html:form styleId="AclForm" method="post" action="acl.do">
 <html:hidden property="action"/>
 <html:hidden property="acl.contextId"/>  
 <html:hidden property="acl.id"/>  
 
	<table border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="5">&nbsp;</td>
		</tr>		
		<tr>
			<td>Name</td>
			<td colspan="3"><html:text property="acl.name" styleId="acl.name" size="53" />
			<b style="color: red; ">*</b></td>
			<td style = "text-align: right">Status
			
			<html:select property="acl.status" styleId="acl.status" size="1">
		 		<html:option value="1">Active</html:option>
				<html:option value="2">Inactive</html:option>
				<!-- <html:option value="3">Refused</html:option> -->
			</html:select>			    		
	 		</td>
		</tr>		
		<tr>
			<td style="vertical-align: top;">Description&nbsp;</td>
			<td colspan="3">
				<html:textarea property="acl.description" styleId="acl.description" cols="50"  rows="5"/>
				<b style="color: red; ">*</b>				
			</td>
			<td>&nbsp;</td>
		</tr>		
		<tr>
			<td colspan="5">&nbsp;</td>
		</tr>		
		<tr>
			<td>Priority</td>
			<td colspan="2">
			   <html:text property="acl.priority"  styleId="acl.priority"  size="10"
			   	onkeydown ="checkNumber(this, '${AclForm.acl.priority}');" 
				onblur="checkNumber(this, '${AclForm.acl.priority}');"/><b style="color: red; ">*</b></td>
				
			<td colspan="1">&nbsp;</td>
			<td class="checkbox">
				<html:checkbox disabled="true" property="acl.isAldeAcl" styleId="acl.isAldeAcl"/>ALDE-ACL
			</td>
		</tr>	
			
		<tr>
			<td colspan="5">&nbsp;</td>
		</tr>		
		<tr>
			<td></td>
			<td>Source <b style="color: red; "> *</b></td>
			<td>Mask <b style="color: red; "> *</b></td>
			<td>Destination <b style="color: red; "> *</b></td>
			<td>Mask <b style="color: red; "> *</b></td>
		</tr>		
		<tr>
			<td>IP Address</td>
			<td><html:text property="acl.sourceIp"       styleId="acl.sourceIp"       size="20"/></td>
			<td><html:text property="acl.sourceIpMask"   styleId="acl.sourceIpMask"   size="20"/></td>
			<td><html:text property="acl.destIp"         styleId="acl.destIp"         size="20"/></td>
			<td><html:text property="acl.destIpMask"     styleId="acl.destIpMask"     size="20"/></td>
		</tr>		
		<tr>
			<td>Port</td>
			<td><html:text property="acl.sourcePort"     styleId="acl.sourcePort" size="20"
			 	onkeydown ="checkNumber(this, '${AclForm.acl.sourcePort}');" 
				onblur="checkNumber(this, '${AclForm.acl.sourcePort}');"/></td>
			<td>
			  <!--   <html:text property="acl.sourcePortMask" styleId="acl.sourcePortMask"  size="20"/> -->
		    </td>
			<td><html:text property="acl.destPort"       styleId="acl.destPort"  size="20"
				onkeydown ="checkNumber(this, '${AclForm.acl.destPort}');" 
				onblur="checkNumber(this, '${AclForm.acl.destPort}');"/></td>
			<td>
			  <!-- <html:text property="acl.destPortMask"   styleId="acl.destPortMask"  size="20"/>  -->	
			</td>
		</tr>				
		<tr>
			<td colspan="5">&nbsp;</td>
		</tr>		
		<tr>
			<td>Protocol</td>
			<td colspan="2">
			<html:select property="acl.protocol" styleId="acl.protocol">
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
				<html:option value="0">Accept</html:option>
				<html:option value="1">Shape</html:option>
				<html:option value="2">Drop</html:option>
				<html:option value="3">Capture HTTP Traffic</html:option>
			</html:select>
			</td>
		</tr>
		<tr>
			<td colspan="5">&nbsp;</td>
		</tr>		
		<tr>
			<td>Threshold &nbsp;</td>
			<td colspan="3"><html:text property="acl.threshold" styleId="acl.threshold" size="40"
				onkeydown ="checkNumber(this, '${AclForm.acl.threshold}');" 
				onblur="checkNumber(this, '${AclForm.acl.threshold}');"/> <b style="color: red; ">*</b> 0 - 65535</td>
			<td>&nbsp;</td>
		</tr>		
		<tr>
			<td colspan="5">&nbsp;</td>
		</tr>		
		<tr>
			<td nowrap="nowrap">Time to live&nbsp;</td>
			<td colspan="2"><html:text property="acl.timeToLive" styleId="acl.timeToLive" size="40"
				onkeydown ="checkNumber(this, '${AclForm.acl.timeToLive}');" 
				onblur="checkNumber(this, '${AclForm.acl.timeToLive}');"/><b style="color: red; "> *</b></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>		
		<tr>
			<td colspan="5">&nbsp;</td>
		</tr>		
		<tr>
			<td></td>
			<td align="right" colspan="4">
				<span class="enable">	
					<input type=button  class="submit" name='btBack'    value='Back' 	onclick="javascript:actionForm('list')">
				</span>
				<input type=button  class="submit" name='btSubmit'  value='Send' 	onclick="javascript:actionForm('save')">
			</td>
			<td>&nbsp;</td>	
		</tr>
	</table>
</html:form>
</fieldset>
</body>
</html>