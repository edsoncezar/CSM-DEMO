<%@include file="pages/commonHeader.jsp"%>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
<script>
	
	function actionForm(action){
		
		document.forms[0].action.value = action;
		document.forms[0].submit();	
	}
	
	function convertDate(){
	/*if($('operator.lastAlert').value!=""){
		var diveded = $('operator.lastAlert').value.split("-"); 
		var dataRight = diveded[2] + '/' + diveded[1] + '/' + diveded[0];
		$('operator.lastAlert').value = dataRight
		}else{
		dataRight="";
		}
		return dataRight;*/
		return $('operator.lastAlert').value;
	}
	
	function protectForm(){
		if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE}', true)){
			protectAllFields(true);
		}
	}
		
</script>

</head>
<body onload="convertDate();protectForm()">
<%@include file="pages/errorContainer.jsp"%>
<br>
<br>
<br>
<!--/////////////////////// OPERATOR INFO //////////////////////////////////////////////////  -->

<fieldset style="width: 640px;"><legend style="right: 150px">Operator</legend>

<table cellspacing="3" width="100%">	
	<tbody id="operatorInfo">
		
		<html:form styleId="formOperator" method="post" action="operator.do">
			<html:hidden property="action" />
			<tr>
				<td width=5%></td>
				<td width=20%>&nbsp;</td>
				<td align="left">&nbsp;</td>
				<td width=60%></td>				
			</tr>
			<tr>
				<td></td>
				<td>Name</td>	
				<td align="left"><html:text property="operator.name" styleId="operator.name"
					maxlength="40" style="width:450px;"/></td>
				<td></td>
			</tr>

			<tr>
				<td></td>
				<td>Email</td>	
				<td><html:text property="operator.email"
					styleId="operator.email" maxlength="60" style="width:450px;" /></td>
				<td></td>
			</tr>

			<tr>
				<td></td>
				<td>Mobile</td>			
				<td><html:text property="operator.mobile"
					styleId="operator.mobile" maxlength="30" style="width:350px;" /></td>
				<td></td>
			</tr>
			
			
			<tr>
				<td></td>
				<td nowrap="nowrap">Last Alert</td>			
				<td><html:hidden property="date" styleId="operator.lastAlert" /> 
					
					<html:text	readonly="true" property="date" maxlength="20"/>
					</td>
				<td></td>					
			</tr>
			

			<tr>
			  <td colspan="4">
			  <table width="100%" cellpadding="0" cellspacing="0">
				<tr>
				   	<td align="right">
				   	<span class="enable">	
				   		<input type=button class="submit" name='btBack'
							value='Back' onclick="javascript:actionForm('list')">&nbsp;
						</span>
						<input type=button class="submit" name='btSubmit'
						value='Send' onclick="javascript:actionForm('save')">
					</td>
				</tr>
			  </table>		
			 </td> 
			</tr>

		</html:form>
</table>	
</fieldset>	
</body>
</html>

