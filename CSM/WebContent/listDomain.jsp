<%@include file="pages/commonHeader.jsp"%>
<html>
<head>
<title>ALU-Agya</title>
<link href="css/displaytag.css" rel="STYLESHEET" type="text/css">
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


<display:table pagesize="10" name="requestScope.LIST_DOMAIN" id="row" export="true">
	<display:setProperty name="basic.show.header" value="true" />
	<display:setProperty name="export.pdf" value="true" />

	<display:column width="80%" property="name" title="Domain" maxLength="40" />

	<html:link action="/">
		<display:column width="10%" 
						title="" 
						href="domainControl.do?action=edit="	
						paramId="domain.id" 
						paramProperty="id"><%="Edit"%>
		</display:column>
	</html:link>


	<display:column width="10%" align="center">
		<div class="hideDiv"><a href="javascript:deleteConfirm('domainControl.do?action=delete&domain.id=${row.id}');">Delete</a></div>
	</display:column>

</display:table>

</body>
</html>