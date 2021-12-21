<%@include file="pages/commonHeader.jsp" %>
<html>
<head>
  <title>ALU-Agya</title>

	<link href="css/displaytag.css" 	rel="STYLESHEET" type="text/css">
	<script>
	
	
		
		
		function checkSelected(){
			var startRadioID = "";
			var keyValue = '';
			<c:if test="${fn:length(sessionScope.listSchedules) gt 0}">		
    			startRadioID = "chk_${sessionScope.listSchedules[0].id}";
    			keyValue = $(startRadioID).value;
		  	</c:if>
	  		if (keyValue != '') {
				showPolicyDetails(keyValue);
			}
		}
	
		function showPolicyDetails(id) {
			var iFrame = $('policyDetails');
			iFrame.src = 'choosePolicy.do?action=details&id=' + id ;
		
		}
	
		function actionForm(){		
			document.forms[0].submit();	
		}
	</script>
</head>
<%@ include file="pages/errorContainer.jsp" %>
<br>
<body onload="checkSelected();">
<fieldset style="width:650px;" >
<legend>Choose a traffic rule to be applied</legend>
<html:form method="post"  styleId="proc" action="choosePolicy.do" >
<html:hidden property="action"/>
<%-- Caso Seja uma DPPM --%>
<html:hidden property="processorPacket.id"/>
<html:hidden property="processorPacket.name"/>
<html:hidden property="processorPacket.description"/>
<html:hidden property="processorPacket.number"/>
<html:hidden property="processorPacket.trafficPolicyId"/>
 
<%-- Caso Seja um Device --%>
<html:hidden property="device.id" />

<%-- Caso Seja um Context --%>
<html:hidden property="context.id" />

   <c:set var="checkOnce" value="checked='checked'"/>
   
   <table cellpadding="0" cellspacing="0" style="width:100%;border:none">
   <tr> 
      <td>
   		<display:table style="width:100%" name="sessionScope.listSchedules" id="row" export="false">
	    <display:setProperty name="basic.show.header" value="true" />
    	<display:column width="5px" class="checkbox">
    		<input type="radio" name="scheduleId" id="chk_${row.id}" value="${row.id}" ${checkOnce}
    		onChange="showPolicyDetails('${row.id}');">
     	</display:column>
		<c:set var="checkOnce" value=" "/>
    	<display:column property="trafficPolicyName" title="Traffic Policy"  />
    	<display:column property="name" title="Schedule"  />          
   		</display:table>
      </td>
   </tr>

   <c:if test="${empty sessionScope.listSchedules}">
   		<tr>
   			<td style="text-align: right">
     			<input type=button disabled="disabled" class="submit" name='btSubmit' value='Apply' onclick="javascript:actionForm()">
	   		 </td>
   		</tr>
	</c:if>  

   <c:if test="${not empty sessionScope.listSchedules}">
   		<tr>
   			<td style="text-align: right">
     			<input type=button  class="submit" name='btSubmit' value='Apply' onclick="javascript:actionForm()">
	   		 </td>
   		</tr>
	</c:if>  
	
	 		
   </table>
</html:form>
</fieldset>
<iframe frameborder="0" scrolling="auto" title="Scheduled Protocols" style="width: 100%; height: 400px; display: block;" id="policyDetails" src="">
</iframe>
</body>
</html>