<%@include file="pages/commonHeader.jsp"%>
<link href="css/error_css.css" type="text/css" rel="styleSheet">

<script> 
	function actionForm(action){
	/*
	if(action=='save'){
		if($('portProtocolGroup.name').value == "" ){
		 	alert('Please enter Traffic Group name');
			return;
			}	
			if($('newProtocolList').value.length < 1 ){
		 	alert('Please enter at least one protocol.');
			return;
			}
		*/
		document.forms[0].action.value = action;
		document.forms[0].submit();	
	} 
	
	function protectForm(){
		if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${MASTER_ROLE},${ROOT_ADMIN_ROLE}', false)){
			protectAllFields(true);
		}
	}
	
	function popCheck() {
		setCheck('portProtocolGroup.flActive', 'flActive');
		
	}
	
</script>
<body onload="opt.init(document.forms[0]);protectForm();popCheck();">
<%@include file="pages/errorContainer.jsp"%>
<br>
<fieldset style="width:640px">
<legend >Traffic Groups</legend>
<html:form method="get" action="/portProtocolGroup.do">
<html:hidden property="action" />
<html:hidden property="portProtocolGroup.id" />
<html:hidden property="portProtocolGroup.flowsValue" value="0"/>
<table border="0" width="600px" cellpadding="3" cellspacing="1" align="center">
	<tr>
		<td></td>
		<td >Name</td>
		<td>
			<html:text property="portProtocolGroup.name" styleId="portProtocolGroup.name" maxlength="45" style="width:90%;"  />
			<b style="color: red; "> *</b>
		</td>
		<td></td>

	</tr>

	<tr>
		<td></td>
		<td >Description</td>
		<td><html:textarea property="portProtocolGroup.description" style="width:90%; height:50px"/></td>
		<td></td>

	</tr>
	<!-- 
	<tr>
		<td align="left"></td>
		<td></td>
		<td align="left" colspan="2">
		<html:checkbox property="portProtocolGroup.flActive" value="${portProtocolGroup.flActive}"/>Active</td>
		<td></td>
	</tr>
	 -->
		
	<tr>
		<td align="left"></td>
		<td></td>
		<td align="left" colspan="2" class="checkbox">
		<html:hidden   property="portProtocolGroup.flActive"  styleId="portProtocolGroup.flActive" />
		<html:checkbox property="flActive" styleId="flActive" onclick="verifyChecked('flActive','portProtocolGroup.flActive')" />Active</td>
		<td></td>
	</tr>
	
	<tr>
		<td></td>
		<td>Downstream</td>
		<td>
			<html:text property="portProtocolGroup.downStreamValue" style="width:100px;" 
			onkeydown ="checkNumber(this, '${PortProtocolGroupForm.portProtocolGroup.downStreamValue}');" 
			onblur="checkNumber(this, '${PortProtocolGroupForm.portProtocolGroup.downStreamValue}');"></html:text>
			<html:select property="portProtocolGroup.downStreamUnit" style="width=50">
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
			<html:text property="portProtocolGroup.upStreamValue" style="width:100px;" 
			onkeydown ="checkNumber(this, '${PortProtocolGroupForm.portProtocolGroup.upStreamValue}');" 
			onblur="checkNumber(this, '${PortProtocolGroupForm.portProtocolGroup.upStreamValue}');"></html:text>
			<html:select property="portProtocolGroup.upStreamUnit" style="width=50">
				<html:option value="Mb">Mbit/s</html:option>[Kbits/Mbits]
				<html:option value="Kb">Kbit/s</html:option>[Kbits/Mbits]
			</html:select>
		</td>
		<td></td>
	</tr>
	
	
		
	
	<tr>
		<td></td>
		<!-- Association Tables //////////////////////////////////////////////////////------->
		<td colspan="2"> 
			<script language="JavaScript">
					// var opt = new OptionTransfer("portList","portProtocolGroup.ports");
 					// var opt = new OptionTransfer("avPorts","portProtocolGroup.ports");
 					// opt.setAutoSort(true);
 					// opt.setDelimiter(",");
 					// opt.saveNewRightOptions("newPortList");
 					 
 					 var opt = new OptionTransfer("avProtocol","portProtocolGroup.protocols");
 					 opt.setAutoSort(true);
 					 opt.setDelimiter(",");
 					 opt.saveNewRightOptions("newProtocolList");
					
					// var opt2 = new OptionTransfer("avProtocol","portProtocolGroup.protocols");
					// opt2.setAutoSort(true);
					// opt2.setDelimiter(",");
					// opt2.saveNewRightOptions("newProtocolList");
					
					
			</script>
			
<!--
		Inclusão de portas foi inibido conforme solicitação de Cristiano/
		Alexandre em 22-ago-2007
			
 -->			
			<table>
				<tr>
					<td></td>
<%--					<td >Available Ports</td>
					<td><input type="hidden" name="newPortList" style="width: 250px;"/></td>
					<td >Grouped Ports</td>
					<td></td>
				</tr>
			<tr>
					<td></td>
					<td>
						<!-- Portas disponíveis -->
						<html:select property="avPorts" multiple="yes" size="10" style="width:200px;" ondblclick="opt.transferRight()"> 
							<c:forEach items="${PortProtocolGroupForm.avPorts}" var="port">
								  <c:if test="${port.value != 999}">
								  	 <html:option value="${port.id}">${port.name}</html:option>
								  </c:if>
							</c:forEach>
						</html:select>
					</td>					
					<td>
						<INPUT TYPE="button" class="submit" NAME="right" 	VALUE="&gt;" 			ONCLICK="opt.transferRight()"		style="width: 30px;"><BR><BR>
						<INPUT TYPE="button" class="submit" NAME="right" 	VALUE="&gt;&gt;" 		ONCLICK="opt.transferAllRight()" 	style="width: 30px;"><BR><BR>
						<INPUT TYPE="button" class="submit" NAME="left" 	VALUE="&lt;" 			ONCLICK="opt.transferLeft()"		style="width: 30px;"><BR><BR>
						<INPUT TYPE="button" class="submit" NAME="left" 	VALUE="&lt;&lt;" 		ONCLICK="opt.transferAllLeft()"		style="width: 30px;">
					</td>
					<td>
					<!-- Portas Selecionadas -->
						<html:select property="portProtocolGroup.ports" multiple="yes" size="10" style="width:200px;" ondblclick="opt.transferLeft()">
							<c:forEach items="${PortProtocolGroupForm.portProtocolGroup.ports}" var="port">
								<html:option value="${port.id}">${port.name}</html:option>
							</c:forEach>
						</html:select>
					
					</td>
					<td></td>
					<td></td>
				</tr>   --%>
<!-- ////////////////////////////////////////////////////////////////////////// -->
				
				<tr>
					<td></td>
					<td >Available Protocols</td>
					<td><input type="hidden" id="newProtocolList" name="newProtocolList" style="width: 300px;"/></td>
					<td >Grouped Protocols<b style="color: red; ">*</b></td>
					<td></td>
				</tr>
				<tr>
					<td></td>

					<!-- Protocolos disponíveis -->
					<td>
						<html:select property="avProtocol" multiple="yes" size="10" style="width:265px;">
						
							<c:forEach items="${PortProtocolGroupForm.avProtocol}" var="protocol">
							    <html:option value="${protocol.id}">${protocol.name}</html:option>
							</c:forEach>
						</html:select>
					</td>

					<!-- Botoes de controle de transferencia entre boxes -->	
                    <td>
					 	<INPUT TYPE="button" NAME="right" class="submit"	VALUE="&gt;" 			ONCLICK="opt.transferRight()"		style="width: 30px;"><BR><BR>
						<INPUT TYPE="button" NAME="right" class="submit"	VALUE="&gt;&gt;" 		ONCLICK="opt.transferAllRight()" 	style="width: 30px;"><BR><BR>
						<INPUT TYPE="button" NAME="left"  class="submit"	VALUE="&lt;" 			ONCLICK="opt.transferLeft()"		style="width: 30px;"><BR><BR>
						<INPUT TYPE="button" NAME="left"  class="submit"	VALUE="&lt;&lt;" 		ONCLICK="opt.transferAllLeft()"	style="width: 30px;"> 
				   	</td>

					<!-- Protocolos selecionados -->
					<td>
						<html:select property="portProtocolGroup.protocols" multiple="yes" size="10" style="width:265px;">
							<c:forEach items="${PortProtocolGroupForm.portProtocolGroup.protocols}" var="protocol">
								<html:option value="${protocol.id}">${protocol.name}</html:option>
							</c:forEach>
						</html:select>					
					</td>

				</tr>

	
				
			</table>	
		
		</td>	
		<!-- Association Tables End  /////////////////////////////////////////////////------->
		<td></td>
		
		<tr>
			<td></td>
			<td align="right" colspan="2">
			<span class="enable">
				<input type=button class="submit" name='btBack'    value='Back'  onclick="javascript:actionForm('list')" >
			</span>
				<input type=button class="submit" name='btSubmit'  value='Send'  onclick="javascript:actionForm('save')">
			</td>
			<td>&nbsp;</td>	
		</tr>
				
	<!-- </tr>  -->
	<!--  <tr>
		<td></td>
		<td class="submit" colspan="2" align="right"><input type="button" value="Apply" onclick="actionForm('save');"></td>	
		<td></td>
	</tr> -->

</table>
</html:form>
</fieldset>
