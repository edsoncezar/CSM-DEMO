<html>
<%@include file="pages/commonHeader.jsp"%>
<link href="css/displaytag.css" 	rel="STYLESHEET" type="text/css">
<body>
<%@include file="pages/errorContainer.jsp"%>
<br>

<br>
<h3>Applied ACLs for : ${ProcessorPacketForm.processorPacket.name}</h3>
<display:table pagesize="15" name="sessionScope.ACL_LIST" id="row" defaultsort="1" defaultorder="descending">
	<display:column sortable="true" property="aclNumber"		title="#" />
	<display:column sortable="true" property="acl.name"			title="Name" />
	<display:column sortable="true" property="acl.destIp"		title="Detination" />
	<display:column sortable="true" property="acl.destPort"		title="Port" />
	<display:column sortable="true" property="acl.sourceIp"		title="Source" />
	<display:column sortable="true" property="acl.sourcePort"	title="Port" />
	<display:column sortable="true" sortProperty="dateApplied"		title="Date" >
		<fmt:formatDate value="${row.dateApplied}" pattern="dd/MM/yyyy - hh:mm:ss" />
	</display:column>
	<display:column sortable="true" sortProperty="applied"		title="Status" align="center">
		<div align="center">
		<c:choose>
			<c:when test="${row.applied == true}">
				<img src="img/accept.gif" title="Applied">
			</c:when>
			<c:otherwise>
				<img src="img/error.gif"  title="Error has ocurred while appliing">
			</c:otherwise>
		</c:choose>
		</div>
	</display:column>
	<display:column>
		<a href="acl.do?action=edit&acl.id=${row.acl.id}">view</a>
	</display:column>
</display:table>
</body>
</html>