<%@include file="pages/commonHeader.jsp" %>
<html>
<link href="css/displaytag.css" 	rel="STYLESHEET" type="text/css">

<head>
  <title>ALU-Agya</title>
  <script type="text/JavaScript">

		function hideLinks(){
			if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE},${OPERATOR_ROLE}', true)){
				HideDivs();
			}
		}

	</script>
</head>  
<body onload="hideLinks();"	<c:if test="${requestScope.refresh eq 'true'}">
			onload="reloadTree('${requestScope.nodeType}','${requestScope.nodeId}');"
			<c:set var="requestScope.refresh" value="${'false'}"/>
		</c:if>>
<%@include file="pages/errorContainer.jsp" %>

	<display:table pagesize="10" name="sessionScope.listContext"  id="row" export="true">
    	<display:setProperty name="basic.show.header" value="true" /> 
   	 	<display:setProperty name="export.pdf" value="true" />
       
    	<display:column width="80%" property="name" title="Context" maxLength="40"  />    

    		<html:link  action="/" >
	 			<display:column width="10%" title="" href="context.do?action=edit=" paramId="context.id" paramProperty="id" media="html"><%="Edit"%></display:column>
 			</html:link>
 	
 	
 		<display:column width="10%" align="center" media="html" >
 		<%-- Cannot delete context root--%>
 			<c:if test="${row.parentId ne  '0'}">
	 	 		<div class="hideDiv"><a href="javascript:deleteConfirm('context.do?action=delete&context.id=${row.id}');">Delete</a></div>
	 		</c:if>
 		</display:column>               
       
  </display:table>
  <br/>
</body>

