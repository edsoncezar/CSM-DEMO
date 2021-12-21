<%@include file="pages/commonHeader.jsp"%>
<script> 
	function actionForm(action){
		document.forms[0].action.value = action; document.forms[0].submit();	
	} 
	
	function showErrors(errors){
		 var obj = $('errorDiv');
		 obj.style.display = "block"; 
		 obj.innerHTML = errors;
	}
	
	
</script>
<div  id="errorDiv" style="	display: none; width:100%; 
							min-height: 50px; 
							background-color: #FFE5F3; 
							color: black;
							font-weight: bold;
							border: thin dashed silver; "></div>

<script>showErrors('<html:errors/>');</script>

<fieldset style="width=50em">
<legend style="width: 150px;">Ports</legend>
<html:form method="post" action="/port.do">

<html:hidden property="action" />
<html:hidden property="port.id" />
<html:hidden property="port.unit" />

<!--  Estes campos estão reservados para uso futuro -->
<html:hidden property="traficValues.outputVlanValue" value="0"/>
<html:hidden property="traficValues.outputVlanUnit" value="0"/>
<html:hidden property="traficValues.outputmPlsValue" value="0"/>
<html:hidden property="traficValues.outputmPlsUnit" value="0"/>

<table border="0" width="600px" cellpadding="3" cellspacing="1" align="center">
	<tr>
		<td></td>
		<td >Name</td>
		<td><html:text property="port.name" size="50px" />
			<html:errors property="portFormNome"/>
		</td>
		<td></td>

	</tr>
	<tr>
		<td></td>
		<td >Number</td>
		<td><html:text property="port.portNumber" size="50px"/></td>
		<td></td>
	</tr>
	<tr style="display:none;">
		<td></td>
	<!-- 	<td >Value</td>  -->
		<td><html:hidden property="port.value" /></td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td >Description</td>
		<td>
			<html:textarea property="port.description"  style="width:260px; height=260px;"/>
		</td>
		<td></td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>

</table>
</fieldset>
<br/>

<fieldset>
<legend style="width: 150px;">Default Values</legend>
<table border="0" width="250px" cellpadding="3" cellspacing="1" align="center">	
	<!-- Default Values -->
	
	<tr>
		<td >Flows:</td>
		<td>
			<html:text property="traficValues.flowsValues" style="width=45px"></html:text>
		</td>
		<td colspan="2">
			<html:select property="traficValues.flowsUnit" style="width=300px">
				<html:option value="MB">MB</html:option>[Kbits/Mbits]
				<html:option value="kB">KB</html:option>[Kbits/Mbits]
			</html:select>
		</td>
	</tr>
	<tr>
		<td >Downstream:</td>
		<td>
			<html:text property="traficValues.downStreamValue" style="width=45px"></html:text>
		</td>
		<td width="100px" align="left">
			<html:select property="traficValues.downStreamUnit" style="width=300px">
				<html:option value="MB">MB</html:option>[Kbits/Mbits]
				<html:option value="kB">KB</html:option>[Kbits/Mbits]
			</html:select>
		</td>
	</tr>
	<tr>
		<td >Upstream:</td>
		<td>
			<html:text property="traficValues.upStreamValue" style="width=45px"></html:text>
		</td>
		<td>
			<html:select property="traficValues.upStreamUnit" style="width=45px">
				<html:option value="MB">MB</html:option>[Kbits/Mbits]
				<html:option value="kB">KB</html:option>[Kbits/Mbits]
			</html:select>
		</td>
	</tr>
	<tr>
		<td></td>
		<td colspan="2" align="right">
			<input type="button" class="submit" value="Apply" onclick="actionForm('save');">
		</td>	
		<td></td>
	</tr>
</table>

</html:form>
</fieldset>
