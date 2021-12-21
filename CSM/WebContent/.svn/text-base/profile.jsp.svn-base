<%@include file="pages/commonHeader.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ALU-Agya</title>
</head>
<script>
	function protectForm() {
	
		if ($('userProfile.name').value=="ADMIN" || $('userProfile.name').value=="admin" || 
			$(userProfile.id).value == "${sessionScope.userProfile.id}") {
			$('userProfile.name').readOnly = true;
			$('role').disabled = true;
			$('userProfile.description').disabled = true;
			$('deviceDenied').style.display="none";
			$('btSubmit').disabled = true;
		}
		
	}
	
	function actionForm(action){
		$('role').disabled = false;
		$('userProfile.description').disabled = false;
		document.forms[0].action.value = action;
		document.forms[0].submit();
			
	}
	
	
</script>
<body onload="protectForm();"> 
<%@include file="pages/errorContainer.jsp"%>
<br>
<fieldset style="width:550px;">
<legend>User Profile</legend>
<html:form styleId="formProfile" method="post" action="profile.do">
 <html:hidden property="action"/>
 <html:hidden property="userProfile.contextId"/>
 <html:hidden property="userProfile.id" styleId="userProfile.id"/>
   
	<table style="width:100%;"  border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>&nbsp;</td>
			<td>Name</td>
			<td style="width:25px;">&nbsp;</td>
			<td>
			     <html:text property="userProfile.name" styleId="userProfile.name" maxlength="45" style="width:400px;" />
			     <b style="color: red; "> *</b>
			 </td>
			 <td>&nbsp;</td>
		</tr>
		<tr>
			<td colspan="5">&nbsp;</td>
		</tr>		
		<tr>
			<td>&nbsp;</td>
			<td style="vertical-align: top;">Description</td>
			<td style="width:25px;">&nbsp;</td>
			<td>
				<html:textarea  property="userProfile.description" styleId="userProfile.description" 
								cols="50"  rows="10" style="width:400px;" />				
			</td>
			<td>&nbsp;&nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td colspan="5">&nbsp;</td>
		</tr>		
		
		<tr>
			<td>&nbsp;</td>
			<td>Role</td>
			<td style="width:25px;">&nbsp;</td>			
			<td>
				<html:select property="userProfile.role" styleId="role">
		            <html:option value=""></html:option>
		        	<c:forEach var="role" items="${collRole}">
				          <html:option value="${role.id}">${role.name} </html:option>
					</c:forEach>
		        </html:select>	 
		        <b style="color: red; "> *</b>
			 </td>
			 <td></td>
		</tr>
		<tr>			
			<td colspan="5" >&nbsp;</td>
		</tr>		
		<tr>			
			<td colspan="5" align="center" >
				<div id="deviceDenied" >
				<fieldset style="width:500px;">
					<legend>Denied devices</legend>
						<div style="height: 100px; overflow: auto;" align="center">												 
					    <table cellpadding="1" cellspacing="3" border="0" align="left">
					       <!--      List devices      -->
					       
					       <c:forEach var="dev" items="${sessionScope.listDevice}">						   
					   			<c:set var="deviceId" value="${dev.id}" scope="page" />
					         	<c:set var="prfid" value=""  scope="page" />					       
					            <!--   List devices Denied     -->
						        <c:forEach var="deviceDenied" items="${sessionScope.listDeviceDenied}">						       
									<c:choose>	
										<c:when test="${ deviceId  ==   deviceDenied.csmdeviceId }">
											<c:set var="prfid" value="${deviceDenied.prfid}"  scope="page" />
											<c:set var="userProfileId" value="${deviceDenied.userProfileId}"  scope="page" />
								        </c:when> 	
									</c:choose>
			  					</c:forEach>
			  					
						   		<tr>
			  					    <td class="checkbox" align="right">
			  					    	<c:choose>	
									   		<c:when  test="${empty prfid}">
<!--				  					   			<input type="checkbox" name=device[] value="<c:out value="${dev.id}" />">-->
				  					   			<html:checkbox property="deviceDenied" value="${dev.id}"></html:checkbox>
				  					   		</c:when>
				  					   		<c:otherwise>
				  					   			<a href="profile.do?action=deleteDevice&prfid=<c:out value="${prfid}"/>&id=<c:out value="${userProfileId}"/>&parentId=${parentId}"><img src='img/del.png' border='0' alt="Delete Device"></a>
				  					   		</c:otherwise>
			  			            	  </c:choose>
			  					        
			  					    </td>		  					 
			  						 <td align="left">
										<c:out value="${dev.name}"/>
			  					 	</td>
			  				    </tr>	  		  					   
						   </c:forEach>
						</table>
						</div>				
				</fieldset>	
				</div>							
			</td>
		</tr>
		
		<tr>
			<td colspan="5">&nbsp;</td>
		</tr>
		
		<tr>	
			<td colspan="5" align="right">
				<input type=button class="submit" name='btBack'   value='Back'  onclick="javascript:actionForm('list');">
				<input type=button class="submit" name='btSubmit' value='Send'  id='btSubmit' onclick="javascript:actionForm('save');">
			</td>	
		</tr>
	</table>
</html:form>
</fieldset>
</body>
</html>

