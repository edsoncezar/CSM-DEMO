<%@include file="pages/commonHeader.jsp"%>
<html>
<link href="css/displaytag.css" rel="STYLESHEET" type="text/css">
<script>
	function actionForm(action){
		document.forms[0].action.value = action;
		document.forms[0].submit();
	}
	
	function checkNew(){
		if($('staticIP.id').value == ""){
			$('btNew').style.visibility =  "hidden";
		}
	}
	
	function protectForm(){
		if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE}', true)){
			protectAllFields(true);
		}
	}
	
	function hideLinks(){
		
			if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE}', true)){
				HideDivs();
			}
		}
</script>

<body onload="protectForm();hideLinks();"	
			<c:if test="${requestScope.refresh eq 'true'}">
				onload="reloadTree('${requestScope.nodeType}','${requestScope.nodeId}');"
				<c:set var="requestScope.refresh" value="${'false'}"/>
		 	</c:if>>
<%@include file="pages/errorContainer.jsp"%>
	<fieldset style="width: 640px;">
		<legend>Static IP Control</legend> 
		<br>
		<html:form styleId="StaticIPControlForm" method="post"  action="staticIPControl.do">
		<html:hidden property="action" /> 
		<html:hidden property="staticIP.id" styleId="staticIP.id"/>
		<html:hidden property="staticIP.contextId" />
				
		<table style="border: none;" align="center">
			<tr>
				<td nowrap="nowrap" style="width:10%;" >IP Address</td>
				<td style="width:50%;" ><html:text property="staticIP.IP" styleId="staticIP.IP" style="width:300px" maxlength="200" /></td>
				<td style="text-align: left" ><font color="red">*</font></td>
			</tr>
			<tr>
				<td nowrap="nowrap" >IP Mask</td>
				<td><html:text property="staticIP.mask" styleId="staticIP.mask"  style="width:300px" maxlength="200" /></td>
				<td><font color="red">*</font></td>
			</tr>
			<tr>
				<td nowrap="nowrap" >Traffic Policy</td>
				<td >
					<html:select property="staticIP.trafficPolicyId" style="width:300px" >
						<c:forEach items="${sessionScope.LIST_TRAFFICPOLICY}" var="trafficPolicy">
							<html:option value="${trafficPolicy.id}">${trafficPolicy.name}</html:option>
						</c:forEach>
					</html:select>
				</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="3" >
					<br>
					<br>
				
					<div style="float: right;">
						<input 	type="button" 
								class="submit" 
								name='btNew'
								id="btNew" 
								value='Add New'
								onclick="javascript:actionForm('initial')" />  
						<input 	type="button" 
								class="submit" 
								name='btSave' 	
								value='Save'	
								align="right" 
								onclick="javascript:actionForm('save')" />

					</div>
					<script>checkNew();</script>
				</td>
			</tr>
				
		</table>
			<hr></hr>
		<table style="border: none; width: 640px;" align="center" >
			<tr>
				<td  align="center">
					<display:table pagesize="15" name="sessionScope.LIST_STATICIPS"   id="row" export="false"  defaultsort="1" defaultorder="ascending" align="center" style="width:620px;"> 
					<display:setProperty name="basic.show.header" value="true" />
					<display:column property="IP" 		title="IP Address"	/>
					<display:column property="mask" 	title="IP Mask"	/>
					<display:column title="Traffic Policy"	>
						<c:forEach items="${sessionScope.LIST_TRAFFICPOLICY}" var="trafficPolicy">
							<c:if test="${trafficPolicy.id eq row.trafficPolicyId}">
								${trafficPolicy.name}
							</c:if>
						</c:forEach>
					</display:column>
					<html:link action="/">
						<display:column style="width:30px;"  href="staticIPControl.do?action=edit" paramId="staticIP.id"
							paramProperty="id">
							Edit
						</display:column>
					</html:link>
					<display:column style="width:50px;" align="center">
						<div class="hideDiv"><a href="javascript:deleteConfirm('staticIPControl.do?action=delete&staticIP.id=${row.id}');">Delete</a></div>
					</display:column>
	
				</display:table>
				</td>
			</tr>
		</table>

	</html:form>
</fieldset>


</body>
</html>