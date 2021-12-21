<%@include file="pages/commonHeader.jsp" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script language="JavaScript">
	
	
	function protectForm() {
		if (document.getElementById('action').value=='edit') {
			document.getElementById('user.userName').readOnly=true;		
			if ( (($('user.name').value.toUpperCase() == 'ADMIN' && checkCrendentials('${sessionScope.userProfile.userRole.name}', '${MASTER_ROLE},${ROOT_ADMIN_ROLE}', false)))
			||   ($('user.name').value.toUpperCase() == 'ALDEADMIN')) {
				//document.getElementById('user.name').readOnly=true;
				protectAllFields(true);
			}	
	 	} 		
	}
	
	var arrRoles = new Array();
	var ArrayProfile = new Array();
	var adminId='';
	var adminName='';
	var arrProfile='' ;
	function loadRoles(){
	<c:forEach items="${formUser.lisRole}" var="lisRole" varStatus="i">	
		<c:if test="${lisRole.name eq 'ADMIN' or lisRole.name eq 'admin'}" > 
			<c:set var="adminRoleID" value="${lisRole.id}"/>
			<c:set var="adminRoleName" value="${lisRole.name}"/>
		 adminId="${lisRole.id}";
		 adminName="${lisRole.name}"
		</c:if>	
	</c:forEach>
	
	<c:forEach items="${formUser.collProfile}" var="lisProfile">
		ArrayProfile["${lisProfile.id}"] = "${lisProfile.role}"; 
	</c:forEach>
	}
	

	/*function isAdmin(){
		
		//var myobj=$('profileId').value;
		//arrProfile = ArrayProfile[myobj];
		if( $('user.userName').value.toUpperCase()== 'ADMIN' ){
			$('dtEx').style.display = "none";
			$('expDate').style.display = "none";
			
		}else {
		$('expDate').style.display = "block";
		$('dtEx').style.display = "block";
		}
	}*/
	function actionForm(action){
			
		document.forms[0].action.value = action;
		if( $('user.userName').value.toUpperCase()!= 'ADMIN' ){
			var expirationDate		= $('userAccessControl.expirationDate');
			var fakeExpirationDate 	= $('expDate');
			expirationDate.value = fakeExpirationDate.value;
		
			var diveded = $('userAccessControl.expirationDate').value.split("/"); 
			if(diveded[2]!=null && diveded[1]!=null && diveded[0]!=null){
				var dataRight = diveded[2] + '-' + diveded[1] + '-' + diveded[0];
			}else{
				var dataRight = '';
			}
			$('userAccessControl.expirationDate').value = dataRight;
		}
		document.forms[0].submit();	
	}
	
	
	function convertDate(){
		if( $('user.userName').value.toUpperCase()!= 'ADMIN' ){
		if($('expDate').value!=""){
		var diveded = $('expDate').value.split("-"); 
		var dataRight = diveded[2] + '/' + diveded[1] + '/' + diveded[0];
		$('expDate').value = dataRight
		}
		else{
		dataRight="";
		}
		if($('dateTimeBlock').value!=""){
		var diveded2 = $('dateTimeBlock').value.split("-"); 
		var time=diveded2[2].split(" ");
		var dataRight2 = time[0] + '/' + diveded2[1] + '/' + diveded2[0]+ ' '+time[1];
		$('dateTimeBlock').value = dataRight2
		}
		}
		return dataRight;
	}

	function popCheck() {
	
		setCheck('userAccessControl.flChangeNextLogin', 'flChangeNextLogin');
		setCheck('user.isActive', 'flActive');
		
	}
</script>	
</head>
<c:if test="${sessionScope.userSession.userName eq 'admin' or sessionScope.userSession.userName eq 'ADMIN'}">
<c:out value=""></c:out> 
</c:if> 
<body  onload="loadRoles();convertDate(); protectForm(); popCheck();"> 
<c:set var="checked" value="checked"/>
<%@include file="pages/errorContainer.jsp"%>
<br>

<html:form styleId="formUser" method="post" action="user.do">
<fieldset style="width: 590px">
<legend>User Login</legend>

<table width="100%">
	<tr>
		<td>
			<jsp:include page="tabMenu.jsp">
			 	<jsp:param name="tabNames" value="user,Password,Contact,Access"/>
			 	<jsp:param name="tabDescription" value="User Data,Password Policy,Contact,Access Control"/>
			 	<jsp:param name="tabID" value="1"/>
			 	<jsp:param name="currentTab" value="user"/>
			</jsp:include>
		</td>
	</tr>
</table>
<br>
<table border="0" width="100%" cellpadding="3" cellspacing="1" align="center" style="height: 150px;">
 <html:hidden property="action" styleId="action"/>
 <html:hidden property="user.id"/> 
 <html:hidden property="user.contextId"/>
 <html:hidden property="user.createdBy"/>
 <html:hidden property="userAccessControl.id"/>
	<tr id="user" >
	<td colspan="2" valign="top">
		
	<table border="0" width="100%" cellpadding="3" cellspacing="1" align="left" >
		<tr>
			<td style="width: 15%" >Login</td>
			<td style="text-align: left" colspan="3">
			     <html:text style="text-align: left" property="user.userName" styleId="user.userName" maxlength="60"/>
			     <b style="color: red; ">*</b>
			 </td>
		</tr>
		<tr>
			<td>Name</td>
			<td colspan="3">
			     <html:text property="user.name" styleId="user.name" maxlength="60" style="width:90%;"/>
			     <b style="color: red; ">*</b>
			 </td>
		</tr>
		<tr style="vertical-align: top;">
			<td>Description</td>
			<td colspan="2">
			     <html:textarea property="user.description" 
			     				styleClass="textarea" 
			     				style="width:90%; height:50px;"/>
			 </td>
		</tr>
		<tr >
			<td align="left"  >
				Profile
			</td>
			<td align="left" colspan="3">
		        <c:choose>
		        	<c:when test="${sessionScope.userSession.id eq formUser.user.id}">
		        		<b>${sessionScope.userProfile.name}</b>
		        	</c:when>
		        	<c:when test="${formUser.user.name eq 'admin' or formUser.user.name eq 'ADMIN'}">
		        		<html:hidden property="user.userProfileId" styleId="profileId"/>
		        		<b>${adminRoleName}</b>
		        	</c:when>

					<c:otherwise>
		 			<html:select property="user.userProfileId" styleId="profileId">
						<html:option value="">---</html:option>
			        		<c:forEach var="profile" items="${formUser.collProfile}">
					  			<html:option value="${profile.id}">${profile.name}</html:option>
							</c:forEach>
			        </html:select>
			        <b style="color: red; "> *</b>
			        </c:otherwise>
				</c:choose>
			</td>			
		</tr>
		<tr><td colspan="4"></td></tr>
	</table>

	</td>
</tr>


<!--/////////////////////// user. CONTACT INFO //////////////////////////////////////////////////  -->
<tr id="Contact" style="display: none;">
<td colspan="2" valign="top">
<table width="100%" cellpadding="3" cellspacing="1" align="left"  >
	<html:hidden property="contact.id"/>
	<tbody id="usrContactInfo">
	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td></td>
		<td >Area</td>
		<td><html:text property="contact.area" style="width:200px;"/></td>
		<td></td>

	</tr>

	<tr>
		<td></td>
		<td >Phone</td>
		<td><html:text property="contact.phone" style="width:200px;"/></td>
		<td></td>

	</tr>
	<tr>
		<td></td>
		<td >Mobile</td>
		<td><html:text property="contact.mobile" style="width:200px;"/></td>
		<td></td>
	</tr>
	
	<tr>
		<td></td>
		<td >E-Mail</td>
		<td><html:text property="contact.email" style="width:200px;"/></td>
		<td></td>
	</tr>
	

	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>

	</tbody>
</table>


</td>

<!--/////////////////////// user. CONTACT INFO END //////////////////////////////////////////////////  -->
</tr>

<!-- <td>&nbsp;</td> -->

<tr id="Access" style="display: none;" >
<!--/////////////////////// user. ACCESS INFO      //////////////////////////////////////////////////  -->
<td    colspan="2" valign="top">

<c:if test="${formUser.user.name ne 'admin' and formUser.user.name ne 'ADMIN' or sessionScope.userProfile.userRole.name eq MASTER_ROLE}">

<table width="100%"  cellpadding="3" cellspacing="1" align="left" >
	<tbody id="user.ACLInfo">
	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>

	<tr>
		<td></td>
		<td><div id="dtEx">Expiration Date :</div></td>
		<td>
		<html:hidden property="expirationDateForm" styleId="userAccessControl.expirationDate" />
		<html:text   readonly="false" styleId="expDate" property="expirationDateForm" maxlength="10" onfocus="this.select();calendar(this)" onkeypress="" onclick="event.cancelBubble=true;this.select();calendar(this)"/>
		</td>
		<td></td>

	</tr>
	
	<tr>
		<td></td>
		<td class="checkbox" colspan="3" align="left">
			<html:hidden property="userAccessControl.flChangeNextLogin"  styleId="userAccessControl.flChangeNextLogin"/>
			<html:checkbox property="flChangeNextLogin"  styleId="flChangeNextLogin" onclick="verifyChecked('flChangeNextLogin','userAccessControl.flChangeNextLogin')"/>Change Password Next Login
		</td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td class="checkbox" align="left">
			<html:hidden   property="user.isActive"  styleId="user.isActive" />
			<input type="checkbox" name="flActive" id="flActive" onclick="verifyChecked('flActive','user.isActive')" id="flActive">Active
		</td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td colspan="3">&nbsp;</td>
		<td></td>
	</tr>
	
	<tr>
		<td colspan="3">&nbsp;</td>
		<td></td>
	</tr>
	</tbody>
</table>


</c:if>
</td>
<!--/////////////////////// user. ACCESS INFO  END    //////////////////////////////////////////////////  -->
</tr>
<tr id="Password" style="display: none;" valign="top">

<!--/////////////////////// user. PASSWORD POLICY INFO //////////////////////////////////////////////////  -->
<td colspan="2" >
<c:if test="${formUser.user.name ne 'admin' and formUser.user.name ne 'ADMIN' or sessionScope.userProfile.userRole.name eq MASTER_ROLE}">


<table width="100%" cellpadding="3" cellspacing="1" align="left" >
	<tbody id="usrPasswordPolicy" align="left">
	<tr>
		<td></td>
		<td style="width: 150px;">Password</td>
		<td align="left"><html:password property="newPassword" style="width:150px;"/></td>
		<td></td>

	</tr>

	<tr>
		<td></td>
		<td >Confirm</td>
		<td align="left"><html:password property="confirm" style="width:150px;"/></td>
		<td></td>

	</tr>
	<tr>
		<td></td>
		<td>Date/Time Block</td>
		<c:choose>
			<c:when test="${formUser.userAccessControl.wrongPassCount ne 0}">
				<td><html:text readonly="true" property="userAccessControl.dateTimeBlock" styleId="dateTimeBlock" style="width:150px;"/></td>
			</c:when>
			<c:otherwise>
				<td><input type="text" readonly="readonly" id="dateTimeBlock" style="width:150px;"></td>
			</c:otherwise>
		</c:choose>
		<td></td>
	</tr>
	
	<tr>
		<td></td>
		<td style="width: 150px;">Unsuccessful Logins</td>
		<td align="left"><html:text readonly="true" property="userAccessControl.wrongPassCount" style="width:150px;"/></td>
		<td></td>
	</tr>
	

	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>

	</tbody>
</table>


</c:if>
</td>

<!--/////////////////////// user. PASSWORD POLICYS INFO END //////////////////////////////////////////////////  -->
</tr>	
</table>

<table style="text-align: right; width: 100%;">
<tr>
	
	<td  style="text-align: right; colspan="2">
		<table  style="text-align: left; width: 98%" >
			<tbody>
			<tr>
				<td style="text-align: right; width: 95%">
					<input type=button class="submit" name='btSubmit'  value='Unblock'  onclick="javascript:actionForm('unblock')">
				</td>
	   		    <td style="text-align: right; width: 90%">
	   		    <span class="enable">
	   		    	<input type=button class="submit" name='btBack'    value='Back'  onclick="javascript:actionForm('list')" >
	   		   	</span>
	   		    </td>
				<td style="text-align: right; width: 30%">
					<input type=button class="submit" name='btSubmit'  value='Send'  onclick="javascript:actionForm('save')">
				</td>
				
			</tr>
			</tbody>
		</table>
	</td>
	
</tr>
</table>
</fieldset>
</html:form>
</body>
</html>

