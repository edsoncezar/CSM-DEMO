<%@include file="pages/commonHeader.jsp" %>
<html>
<head>
  <script language="JavaScript">
  	function admin(action) {
  		var user = document.getElementById("")
  
  	
  	}
  
  
  </script>
  <title>ALU-Agya</title>
	<link href="css/displaytag.css" rel="STYLESHEET" type="text/css">
</head>
<body
	<c:if test="${requestScope.refresh eq 'true'}">
		onload="reloadTree('${requestScope.nodeType}','${requestScope.nodeId}');"
		<c:set var="requestScope.refresh" value="${'false'}"/>
	</c:if>>
<%@include file="pages/errorContainer.jsp"%> 
<br>
  <h3>User List</h3>
  <display:table pagesize="10" name="sessionScope.listUsers" id="row" export="true">
    <display:setProperty name="basic.show.header" value="true" /> 
  
    <display:setProperty name="export.pdf"  value="true" />
    
    <display:column width="80%" sortable="true" property="userName" title="Username" maxLength="10"   />
    
	 	<display:column style="text-align: center"  width="10%" title="" href="user.do?action=edit&" paramId="user.id" paramProperty="id" media="html"><%="Edit"%></display:column>
 	
	 	<display:column style="text-align: center" width="10%" title="" href="user.do?action=delete&" paramId="user.id" paramProperty="id"  media="html">
	 	<c:choose>
	 		<c:when test="${row.userName eq 'admin' or row.userName eq 'aldeadmin'}"></c:when>
	 		<c:when test="${row.userName eq userSession.userName}"></c:when>
	 		<c:otherwise>
	 			<a href="javascript:deleteConfirm('user.do?action=delete&id=${row.id}');" ><%="Delete"%></a>
	 		</c:otherwise>
	 	</c:choose>
		</display:column>
       
  </display:table>
</html>