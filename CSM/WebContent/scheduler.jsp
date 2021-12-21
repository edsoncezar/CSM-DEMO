<%@include file="pages/commonHeader.jsp" %>
<html>
<head>
<script>
	function checkAllMonths(objCheckRuler, names,spanId)
	{
	 checkAll(objCheckRuler, names);
	 if(objCheckRuler.checked == true){
	 	$(spanId).innerHTML='None';
	 }else{
	 	$(spanId).innerHTML='All';
	 }
	}
	/*
	function changeTo(tab){
		if(tab == 'Registration'){ 
			showHide('groupsSet');
			showHide('registrationSet');
			$('registrationTab').onclick = null;
			$('registrationTab').innerHTML = "<b>Registration</b>";
			$('groupsTab').innerHTML = "Groups";
			$('groupsTab').onclick= changeToGroups;
		}else{
			showHide('registrationSet');
			showHide('groupsSet');
			$('groupsTab').onclick = null;
			$('groupsTab').innerHTML = "<b>Groups</b>";
			$('registrationTab').innerHTML = "Registration";
			$('registrationTab').onclick= changeToRegistration;
		}
	}
	
	function changeToGroups(){
		changeTo("Groups")
	}
	
	function changeToRegistration(){
		changeTo("Registration");
	}*/
	
	function protectForm(){
		if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE}', true)){
			protectAllFields(true);
		}
	}
	</script>
</head>

<body onload="startCheckbox();protectForm();">
<%@include file="pages/errorContainer.jsp" %>
<br>
<html:form styleId="schedulerForm" method="post" action="scheduler.do">
<html:hidden property="action" value="record"/>
<html:hidden property="scheduling.id"/>
<html:hidden property="scheduling.contextId"/> 
<html:hidden property="scheduling.trafficPolicyId"/> 
<c:set var="checkOnce" value="checked='checked'"/>
<fieldset style="width: 610px">
<legend>Scheduler</legend>
<br>
<table width="100%" cellpadding="4" border="0">
<!--		<tr>-->
<!--			<td style="width: 5px;"></td>-->
<!--			<td align="center" valign="middle"-->
<!--				style="width: 150px; text-align: center; border: 1px solid silver; border-bottom: none;"-->
<!--				id="registrationTab"><b>Registration</b></td>-->
<!--			<td style="width: 2px;"></td>-->
<!--			<td align="center" valign="middle"-->
<!--				style="width: 150px; text-align: center; border: 1px solid silver; border-bottom: none;"-->
<!--				onclick="changeToGroups();"-->
<!--				id="groupsTab">Groups</td>-->
<!--			<td style="border:none;">&nbsp;</td>-->
<!--		</tr>-->
<!--		-->
<tr>
			<table width="100%">
				<tr>
					<td><jsp:include page="tabMenu.jsp">
						<jsp:param name="tabNames" value="registrationSet,groupsSet" />
						<jsp:param name="tabDescription" value="Registration,Groups" />
						<jsp:param name="tabID" value="1" />
						<jsp:param name="currentTab" value="registrationSet" />
					</jsp:include></td>
				</tr>
			</table>
		</tr>
		<tr>
			<td colspan="5" style="border: 1px solid silver; height: 400px; vertical-align: top;">
 

<div id="registrationSet" style="height: 500px; width: 600px;">
   <table width="100%">   
	    <tr>
	  	    <td>Name</td>  
			<td colspan="3">
			   <html:text property="scheduling.name" styleId="scheduling.name" maxlength="60" size="50 px"/>
			   <b style="color: red;">*</b>
			</td>
	    </tr>
        
	  	<tr>		
			<td style="vertical-align: top;">Description</td>
			<td colspan="3" style="vertical-align: top;">
				<html:textarea  property="scheduling.description"  styleId="scheduling.description"  cols="90"  rows="3" style="width: 515px;"/>
				<b style="color: red;">*</b>				
			</td>		
		</tr>
	   
	    <tr>
	        <td></td>
			<td colspan="3">
			<table>
				<tr>
					<td>Start Date</td>  
					<td>
					   <html:text property="scheduling.dateStart" value="${schedulerForm.scheduling.dateStart}" maxlength="10" onfocus="this.select();calendar(this)" onkeypress="" onclick="event.cancelBubble=true;this.select();calendar(this)"/>
					</td>
	    
					<td>Start Hour</td>				
					<td> 
					 	<html:select property="scheduling.hour"  styleId="scheduling.hour">	
					 		<html:option value=""></html:option>	 
							 <c:forEach var="i" begin="0" end="9" step="1">
						   		 <html:option value="0${i}"> 0${i}</html:option>
						    </c:forEach>  
						     <c:forEach var="i" begin="10" end="23" step="1">
						   		 <html:option value="${i}"> ${i}</html:option>
						    </c:forEach>      
						</html:select>:	
						<html:select property="scheduling.minute" styleId="scheduling.minute">		 	
							 <html:option value=""></html:option>	 
							 <c:forEach var="i" begin="0" end="9" step="1">
						   		 <html:option value="${i}"> 0${i}</html:option>
						    </c:forEach>  		 	
							 <c:forEach var="i" begin="10" end="59" step="1">
						   			 <html:option value="${i}">${i}</html:option>
						    </c:forEach>    
						</html:select> 
						<b style="color: red;">*</b>
					</td>
			   </tr>
		  </table>
		  </td>			   							
		</tr>				
	<tr>
		<td></td>
		<td colspan = "3" align="left" valign="top">   					
			    <table border="0"  height="100%">
			      <tr>
			        <td>Every</td>
			        <td><html:text property="scheduling.every" maxlength="3" size="10 px"
			        	onkeydown ="checkNumber(this, '${schedulerForm.scheduling.every}');" 
						onblur="checkNumber(this, '${schedulerForm.scheduling.every}');"/> </td>	        
 		        	<td class="checkbox"><html:radio property="scheduling.everyType" value="H" /></td>
		        	<td>Hour(s)</td>
			        <td class="checkbox"><html:radio property="scheduling.everyType" value="D"/></td>
			        <td>Day(s)</td>
			        <td class="checkbox"><html:radio property="scheduling.everyType" value="W"/></td>
			        <td>Week(s)</td>
			        <td class="checkbox"><html:radio property="scheduling.everyType" value="M"/></td>
			        <td>Month(s)</td>
			        <td class="checkbox"><html:radio property="scheduling.everyType" value="Y"/></td>
			        <td>Year(s)</td>
			      </tr>		      
			      <tr> 
	      		 </tr>
				</table>			
		</td>  
   </tr>  

   <tr>
   		<td></td>
		<td colspan = "3" align="left" valign="top">			
     		<fieldset style="width: 500px;align=left" >
     		  <legend>Months</legend>
		   		 <table border="0" width="100%" align="Left">
		   		 <tr>
		   		 	<td colspan="3"  class="checkbox">
		   		 		
		   		 		<b><input type='checkbox' onclick="checkAllMonths(this,'scheduling.month','checkLable');">
		   		 		&nbsp;Select: <span id="checkLable">All</span></b>
		   		 		<hr></hr>
		   		 	</td>
		   		 </tr>
		   		 <tr>
			        <c:set var="x" value="1" scope="page" />		        	        
			        <c:forEach var="var" items="${sessionScope.months}" >
			        
			        	<c:set var="monthsKey" value="${var.key}"/>
						<c:set var="checked" value=""/>
						
						<!-- Find months saved in database -->
			        	<c:forEach var="check" items="${sessionScope.monthChecked}">
			        		<c:set var="monthsChecked" value="${check}"/>
					    			<c:choose>
					    			 	<c:when test="${monthsKey==monthsChecked}">
					    			 			<c:set var="checked" value="checked='checked'"/>
					    			 	</c:when>
					    			</c:choose>		
					    </c:forEach>	
					    		    
					    <c:if test="${x=='1'}"><tr></c:if>
					   				 <td class="checkbox" align="left">	
					    <input type="checkbox" name="scheduling.month" value="${var.key}" ${checked}>&nbsp;${var.value}
					    </td>			         
				        
				        <c:choose> 
					      	<c:when test="${x=='3'}">
					      		<c:set var="x" value="1" scope="page"  />				      
					      	</c:when>
					      	<c:when test="${x=='2'}">
					      		<c:set var="x" value="3" scope="page" />				      
					      	</c:when>
					      	<c:when test="${x=='1'}">
					      		<c:set var="x" value="2" scope="page" />			      			      
					      	</c:when>    
					    </c:choose>
					</c:forEach>	
				</tr>		
			</table>
			</fieldset>			
		   </td>   
   </tr> 
	
   <tr>
   		<td></td>
		<td colspan="3" valign="top"  align="left">
     		<fieldset style="width: 500px;align=left" >
     		   <legend>Days</legend>
				  <table>
				   <tr>
		   		 	<td colspan="${fn:length(sessionScope.weekOfDays)}"  class="checkbox">
		   		 		
		   		 		<b><input type='checkbox' onclick="checkAllMonths(this,'scheduling.week','checkLable2');">
		   		 		&nbsp;Select: <span id="checkLable2">All</span></b>
		   		 		<hr></hr>
		   		 	</td>
		   		 </tr>
			        <c:set var="x" value="1" scope="page" />
			        	        
			        <c:forEach var="var" items="${sessionScope.weekOfDays}" >
					    <td class="checkbox" align="center"><html:multibox property="scheduling.week" value="${var.key}" />&nbsp;${var.value}</td>
					</c:forEach>			
				  </table>
			</fieldset>			   		
   		</td>
   </tr>

   <tr>
   		<td></td>
		<td>Year</td>
		<td>
		 	<html:select property="scheduling.year">	
		 		<html:option value=""></html:option>	 
				 <c:forEach var="i" begin="2007" end="2010" step="1">
		   		 <html:option value="${i}"> ${i}</html:option>
			    </c:forEach>    
			</html:select> 
		</td>
		<td></td>
	</tr>
	
	<tr>
  		<td></td>
		<td  valign="middle">Cron Expression</td>
		<td colspan="2" valign="middle">
			<html:text property="scheduling.cronExpression" maxlength="80" style="width:180px;"/>
		</td>
	</tr>
	
	<tr>
  		<td></td>
		<td></td>
		<td class="checkbox" valign="middle"><html:checkbox property="scheduling.cronSubscriber" value='Y'/>Override cron expression</td>
		<td></td>
	</tr>	
	</table>
</div>		


<c:set var="viewGroup" value="${sessionScope.viewGroup}" scope="page" />


<div id="groupsSet" style="display:none; vertical-align: top;" >
<script>
	function startCheckbox(){
			
				<c:forEach var="scheduledGroup" items="${schedulerForm.scheduling.scheduledGroups}" >
				try{	
					$('${scheduledGroup.originalId}').checked = true;
					$('td_${scheduledGroup.originalId}').innerHTML = "<a href='scheduledGroup.do?action=edit&scheduledGroup.id=${scheduledGroup.id}'>edit</a>";
				}catch(e){
					//message("erro: "+e, "ERR");
				}
				</c:forEach>

	}
</script>
<table cellpadding="5" cellspacing="0" width="390px;">
   <html:hidden property="newList" value="${scheduledGroup.id}"/>
   <c:forEach var="group" items="${sessionScope.groupList}">
	<tr>
		<td class="checkbox" width="20px"><html:checkbox property="newList" value="${group.id}" styleId="${group.id}"/></td>
		<td align="left" nowrap="nowrap" width="240px">${group.name}</td>
		<td id="td_${group.id}" align="right">&nbsp;</td>			
	</tr>	
   </c:forEach>	
   </table> 
<c:set var="oldScheduledGroup" scope="session" value="${schedulerForm.scheduling.scheduledGroups}"/>
</div>

</td>
</tr>
</table>

<br> 

  <table width="600 px">	
    <tr>
    <td width="90%"></td>	  	
	<td align="center">
	<span class="enable">	
		<input type=button class="submit" name='btBack'    value='Back'  onclick="javascript:actionForm('list')" >	
	</span>
	</td>
	
	<td  align="center">
		<input type=button class="submit" name='btSubmit'  value='Send'  onclick="javascript:actionForm('record')">
	</td>
	</tr>
  </table>  




</fieldset>
</html:form>

<br/><br/>
	
<script>

	function actionForm(action){	
		
		document.forms[0].action.value = action;
		document.forms[0].submit();	
	}
	
	
	function actionForm(action) {
		
		if(validateForm()){	
			document.forms[0].action.value = action;
			document.forms[0].submit();
		}	
	}
	
	
	function validateForm(){	
		if( $('scheduling.name').value == ""  ){
			//alert("Enter a scheduler name");
			message("Enter a scheduler <b>name</b>", "ERR");
			return false;		
		}	
		if( $('scheduling.description').value == ""  ){
			//alert("Enter a scheduler name");
			message("Enter a scheduler <b>description</b>", "ERR");
			return false;		
		}
		
		if( $('scheduling.hour').value == ""  ){
			//alert("Enter a scheduler hour");
			message("Enter a scheduler <b>hour</b>", "ERR");
			return false;		
		}
		
		if( $('scheduling.minute').value == ""  ){
			//alert("Enter a scheduler minute");
			message("Enter a scheduler <b>minute</b>", "ERR");
			return false;		
		}	
		
		return true;	
	}
	
	
	function disable(){	
		var i ;
	    for (i=0;i< document.forms[0].everyType.length;i++){ 
	       if (document.forms[0].everyType[i].checked){
	       	  for (x=0;x< document.forms[0].month.length;x++){
	       	  		document.forms[0].month[x].disabled = true;
	       	  }
	       	  
	       	  for (x=0;x< document.forms[0].week.length;x++){
	       	  		document.forms[0].week[x].disabled = true;
	       	  }
	          break; 
	       }   
	    }	
	}
	
	function enabled(){	
       	  for (x=0;x< document.forms[0].month.length;x++){
       	  		document.forms[0].month[x].disabled = false;
       	  }
       	  
       	  for (x=0;x< document.forms[0].week.length;x++){
       	  		document.forms[0].week[x].disabled = false;
       	  }
	}
	
	 function new_window(url) {
		xyz="open('test.htm', 'new', 'width=300,height=150,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no')"
		// sem espaços nem passagem de linha
	 }
	
	
</script>
</body>
</html>