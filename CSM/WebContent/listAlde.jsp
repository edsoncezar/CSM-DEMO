<%@include file="pages/commonHeader.jsp" %>
<html>
<head>
  <title>ALU-Agya</title>
	<link href="css/displaytag.css" 	rel="STYLESHEET" type="text/css">
	<script type="text/JavaScript">

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
  <h3>ALDE List</h3>
  <display:table pagesize="10" name="sessionScope.listAlde" id="row"  export="true">
    <display:setProperty name="export.pdf" value="true"/>
        
    <display:column width="80%" sortable="true" property="name" title="Name" maxLength="40"  />    
	 	<display:column style="text-align: center" width="10%" title="" href="alde.do?action=edit" paramId="alde.id" paramProperty="id" media="html"><%="Edit"%>
	 	</display:column>
	 	<display:column style="text-align: center" width="10%" title="" paramId="alde.id" paramProperty="alde.id" media="html"><div class="hideDiv"><a href="javascript:deleteConfirm('alde.do?action=delete&alde.id=${row.id}');"><%="Delete"%></a></div>
	 	</display:column>
  </display:table>
  <br/>
</body>
</html>
 