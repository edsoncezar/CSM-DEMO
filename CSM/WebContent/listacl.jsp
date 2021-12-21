<%@include file="pages/commonHeader.jsp" %>
<c:set var="reloaderForm" value="${requestScope.reloaderForm}" scope="page" />
<c:set var="contextId" value="${requestScope.contextId}" scope="page" />
<c:if test="${reloaderForm == 'true'}">    
	<script>
	window.top.location.href='pages/main.jsp';
	</script>
</c:if> 
<html>
<head>
  <title>ALU-Agya</title>
	<link href="css/displaytag.css" 	rel="STYLESHEET" type="text/css">
	<script language="javascript">
	
	function actionForm(action) {
			document.forms[0].action.value = action;
			document.forms[0].submit();
	}
	
	function hideLinks(){
		
			if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE}', true)){
				HideDivs();
			}
		}
	</script>
	
</head>
<body onload="hideLinks();"
	<c:if test="${requestScope.refresh eq 'true'}">
			onload="reloadTree('${requestScope.nodeType}','${requestScope.nodeId}');"
			<c:set var="requestScope.refresh" value="${'false'}"/>
		</c:if>>	
<%@include file="pages/errorContainer.jsp"%>
<br>
  <fieldset style="width:590; height:80;  align:center" >
   <legend>ACL List</legend>
	  <table style="border: 0">
	   <html:form styleId="AclForm" method="post" action="acl.do">
	    <html:hidden property="action"/>
	    <input type="hidden" name="acl.isAldeAcl" value='<%=request.getParameter("acl.isAldeAcl")%>' />
	    <tr>
	        <td style="width:50%">
		        Status
		        <html:select property="acl.status" size="1" >
		 		<html:option value="1">Active</html:option>
				<html:option value="2">Inactive</html:option>
				<html:option value="3">Refused</html:option>
		    	</html:select>
	    	  	<input type=button  class="submit" name='btSubmit'  value='Filter' 	onclick="javascript:actionForm('list')">	    	
	    	</td>	    
	    	<td>
	    	</td>	     
		    <td>
				&nbsp;
		    </td>  
	    </tr>	
	    </html:form> 
	  </table>
  
  </fieldset>
	<br>
  <display:table pagesize="10" name="sessionScope.listAcl" id="row" export="true">
    <display:setProperty name="export.pdf" value="true"/>
    <display:column width="6%" sortable="true" property="priority" title="Priority" maxLength="40"></display:column>
    <display:column width="22%" sortable="true" property="name" title="Name" maxLength="40"  />  
    <display:column width="22%" sortable="true" property="description" title="Description" maxLength="40"  /> 
    <display:column width="15%" sortable="true" property="sourceIp" title="Source IP" maxLength="40"  /> 
    <display:column width="15%" sortable="true" property="destIp" title="Dest IP" maxLength="40"  />  

	<display:column style="text-align:center" width="10%" title="" href="acl.do?action=edit" paramId="acl.id" paramProperty="id" media="html"><%="Edit"%></display:column>
	<display:column style="text-align:center" 	width="10%" title="" paramId="acl.id" paramProperty="id"  media="html">
		<div class="hideDiv">
			<a href="javascript:deleteConfirm('acl.do?action=delete&acl.id=${row.id}&acl.contextId=${row.contextId}');"><%="Delete"%></a>
		</div>	   
 	</display:column>
                 
  </display:table>
  

  <br/>
</body>  
</html>