<%@include file="pages/commonHeader.jsp"%>
<html>
<link href="css/displaytag.css" rel="STYLESHEET" type="text/css">
<script>
	function actionForm(action){
		document.forms[0].action.value = action;
		document.forms[0].submit();
	}
	
	function checkNew(){
		if($('domain.id').value == ""){
			$('btNew').style.visibility =  "hidden";
		}
	}
	
	function change(){
		var anySourceChk 		 = $('domain.anySource');
		var anyDestinationChk 	 = $('domain.anyDestination');
		var sourceDomainTXT		 = $('domain.sourceDomain');
		var destinationDomainTXT = $('domain.destinationDomain');
		if(anySourceChk.checked == true){
			sourceDomainTXT.disabled = true;
		}else{
			sourceDomainTXT.disabled = false;
		}
		
		if(anyDestinationChk.checked == true){
			destinationDomainTXT.disabled = true;
		}else{
			destinationDomainTXT.disabled = false;
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

<body onload="protectForm();hideLinks();"	<c:if test="${requestScope.refresh eq 'true'}">
				onload="reloadTree('${requestScope.nodeType}','${requestScope.nodeId}');"
				<c:set var="requestScope.refresh" value="${'false'}"/>
		 	</c:if>>
<%@include file="pages/errorContainer.jsp"%>
	<fieldset style="width: 640px;">
		<legend>Static Domain Control</legend> 
		<br>
		<html:form styleId="DomainControlForm" method="post"  action="domainControl.do">
		<html:hidden property="action" /> 
		<html:hidden property="domain.id" styleId="domain.id"/>
		<html:hidden property="domain.contextId" />
				
		<table style="border: none;" align="center">
			<tr>
				<td>Source Domain</td>
				<td ><html:text property="domain.sourceDomain" styleId="domain.sourceDomain" style="width:300px" maxlength="200" /></td>
				<td class="checkbox">
					<html:checkbox  property="domain.anySource"
									styleId="domain.anySource" 
									onchange="change();"/> any
				</td>
				<td><font color="red">*</font></td>
			</tr>
			<tr>
				<td>Destination Domain</td>
				<td><html:text property="domain.destinationDomain" styleId="domain.destinationDomain"  style="width:300px" maxlength="200" /></td>
				<td class="checkbox">
					<html:checkbox 	property="domain.anyDestination" 
									styleId="domain.anyDestination"
									onchange="change();"/> any
				</td>
				<td><font color="red">*</font></td>
			</tr>
			<tr>
				<td>Traffic Policy</td>
				<td >
					<html:select property="domain.trafficPolicyId" style="width:300px" >
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
					<script>checkNew();change();</script>
				</td>
			</tr>
				
		</table>
			<hr></hr>
		<table style="border: none; width: 640px;" align="center" >
			<tr>
				<td  align="center">
					<display:table pagesize="15" name="sessionScope.LIST_DOMAIN"   id="row" export="false"  defaultsort="1" defaultorder="ascending" align="center" style="width:620px;"> 
					<display:setProperty name="basic.show.header" value="true" />
					<display:column sortProperty="id" title="Source Domain"	>
						<c:choose>
							<c:when test="${empty row.sourceDomain}">
								ANY
							</c:when>
							<c:otherwise>
								${row.sourceDomain}
							</c:otherwise>
						</c:choose>
					</display:column>
					<display:column sortProperty="destinationDomain" 	title="Destination Domain"	>
						<c:choose>
							<c:when test="${empty row.destinationDomain}">
								ANY
							</c:when>
							<c:otherwise>
								${row.destinationDomain}
							</c:otherwise>
						</c:choose>
					</display:column>
					<display:column title="Traffic Policy"	>
						<c:forEach items="${sessionScope.LIST_TRAFFICPOLICY}" var="trafficPolicy">
							<c:if test="${trafficPolicy.id eq row.trafficPolicyId}">
								${trafficPolicy.name}
							</c:if>
						</c:forEach>
					</display:column>
					<html:link action="/">
						<display:column style="width:30px;"  href="domainControl.do?action=edit=" paramId="domain.id"
							paramProperty="id">
							Edit
						</display:column>
					</html:link>
					<display:column style="width:50px;" align="center">
						<div class="hideDiv"><a href="javascript:deleteConfirm('domainControl.do?action=delete&domain.id=${row.id}');">Delete</a></div>
					</display:column>
	
				</display:table>
				</td>
			</tr>
		</table>

	</html:form>
</fieldset>


</body>
</html>