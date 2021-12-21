<%@include file="pages/commonHeader.jsp"%>
<html>
<head>
<title>DPPM</title>
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
<br>
<h3>Packet Processor List</h3>
<display:table pagesize="10" name="sessionScope.listProcessorPacket" id="row" export="true">
	<display:setProperty name="export.pdf" value="true" />

	<display:column width="80%" sortable="true" property="name"	title="Name" maxLength="40" />
	<display:column width="10%" title="" href="processorPacket.do?action=edit" 
					paramId="processorPacket.id" paramProperty="id" media="html"><%="Edit"%>
					<c:set var="deviceId" value="${row.device.id}"/>
	</display:column>
	<display:column width="10%" title="" paramId="processorPacket.id" paramProperty="id" media="html">
		<div class="hideDiv"><a href="javascript:deleteConfirm('processorPacket.do?action=delete&processorPacket.id=${row.id}');"><%="Delete"%></a></div>
	</display:column>


</display:table>
<br />
</body>
</html>

