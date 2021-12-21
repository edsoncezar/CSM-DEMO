<%@include file="pages/commonHeader.jsp" %>

<c:set var="reloaderForm" value="${requestScope.reloaderForm}" scope="page" />
<c:set var="contextId" value="${requestScope.contextId}" scope="page" />
<!--  
<c:if test="${reloaderForm == 'true'}">    
	<script>
	window.top.location.href='pages/main.jsp';
	</script>
</c:if> 
-->

<html>
<head>
  <title>List TrafficPolicy</title>
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
  <h3>Traffic Policy List</h3>
  <display:table pagesize="10" name="sessionScope.listTraffic" id="row" export="true">
    <display:setProperty name="export.pdf" value="true"/>
        
    <display:column width="50%"  sortable="true" property="name" title="Name" maxLength="40"  />    
    <display:column width="30%" sortable="true" property="description" title="Description" maxLength="40"  />    
        
    <html:link  action="/" >
	 	<display:column style="text-align: center;" width="10%"  title="" href="trafficPolicy.do?action=edit" paramId="trafficPolicy.id" paramProperty="id" media="html"><%="Edit"%></display:column>
 	</html:link>
 	 	
 	<display:column style="text-align: center;" width="10%"  paramId="id" paramProperty="id"  media="html">
		<div class="hideDiv"><a href="javascript:deleteConfirm('trafficPolicy.do?action=view&trafficPolicy.id=${row.id}&trafficPolicy.contextId=${row.contextId}');">Delete</a></div>	
 	</display:column>
 	               
       
  </display:table>
  <br/>
</html>