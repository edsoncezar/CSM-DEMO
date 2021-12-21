<%@include file="pages/commonHeader.jsp" %>
<html>
<head>
  <title>View Traffic</title>
  <link href="css/displaytag.css" 	rel="STYLESHEET" type="text/css">
  
  <script language="javascript">
	function actionForm(action){
		
		document.forms[0].action.value = action;
		document.forms[0].submit();	
	}
	
	function redirect(url){
		location.href=url
	}
</script>

<c:set var="listScheduling" value="${sessionScope.listScheduling}" scope="page" />


<body>
<fieldset style="width:630; align:center" >
<legend>Traffic Policy</legend>
<html:form styleId="TrafficPolicyForm" method="post" action="trafficPolicy.do">
 <html:hidden property="action"/>
 <html:hidden property="trafficPolicy.contextId" />
 <html:hidden property="trafficPolicy.id" />
  
	<table width ="100%" style="border: 0" cellpadding="0" cellspacing="0">
		<tr>
			<td style="width: 15%">Name</td>
			<td>${trafficPolicy.name}</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>Description</td>
			<td>${trafficPolicy.description}</td>
			<td>&nbsp;&nbsp;&nbsp;</td>
		</tr>
			
		<tr>
			<td colspan="3">			
			<display:table style="width: 100%" name="sessionScope.listScheduling" id="row"  export="false">
	    		  <display:setProperty name="basic.show.header" value="true" />  
	  			  <display:setProperty name="export.pdf" value="false" />	  			  
	  			  <display:setProperty name="basic.msg.empty_list" value="<b>&nbsp;</b>"/>	  			      
				  <display:column sortable="true" property="name" title="Job Name"/>
				  <display:column sortable="true" property="statusQuartz" title="Status"/>
				  <display:column sortable="true" property="dateStart" title="Start date" />
				  <display:column sortable="true" property="timeStart" title="Start Time" />
	    	</display:table>
			</td>
		</tr>
		<c:choose> 
		    <c:when test="${ !empty pageScope.listScheduling }">
		       <tr style="text-align: right">
			       <td colspan="3" style="text-align: right">
			          <input type=button class="submit" name='btBack' value='Back' onclick="redirect('commonTrafficPolicy.jsp?parentId=${trafficPolicy.id}')">
		           </td>
	           </tr>
     			<tr>	       
			        <td colspan="3"></td>
			    </tr>    	           
     			<tr>	       
			        <td colspan="3"></td>
			    </tr>    	           

			  <tr style="background-color:red; text-align: center">	       
			        <td colspan="3"><font color=white face=verdana>Operation not allowed. There are schedules assigned to this traffic policy.</font></td>
		      </tr>
	   
	 	    </c:when>        
	  		<c:otherwise>
	  			<tr style="text-align: right">	       
			        <td colspan="3">
	  					<input type=button class="submit" name='btBack'    value='Back'  onclick="javascript:actionForm('list');" >
						<input type=button class="submit" name='btSubmit'  value='Delete'  onclick="javascript:actionForm('delete')">	
					</td>		 			 	    
		    </c:otherwise>		 
    	</c:choose>
		
	</table>
</html:form>
</fieldset>
</body>
</html>