<%@include file="pages/commonHeader.jsp" %>
<html>
<head>
	<link href="css/displaytag.css" 	rel="STYLESHEET" type="text/css">
	<script language="javascript">
  		checkRefresh('<%=null == request.getAttribute("refresh")? false:request.getAttribute("refresh")%>')
		<% request.setAttribute("refresh", Boolean.FALSE);%>
	</script>
	<title>ALU-Agya</title>
</head>
<body>
<%@include file="pages/errorContainer.jsp" %>
  <%@include file="pages/errorContainer.jsp"%>
  <br> 	
  <h3>Password Dictionaries</h3>
  <display:table pagesize="10" name="sessionScope.listPassRegex" id="row" export="true">
    <display:setProperty name="export.pdf" value="true"/>
        
    <display:column width="80%" sortable="true"property="literal" title="Literal" maxLength="40"  />    
    <c:choose>
	 		<c:when test="${row.isSystem}"></c:when>
	 		<c:otherwise>
	 			<display:column style="text-align: center" width="10%" title="" href="passRegex.do?action=edit" paramId="passRegex.id" paramProperty="id"  media="html"><%="Edit"%></display:column>
				<display:column style="text-align: center" width="10%" title="" paramId="passRegex.id" paramProperty="id" media="html" ><a href="javascript:deleteConfirm('passRegex.do?action=delete&passRegex.id=${row.id}');" ><%="Delete"%></a></display:column> 
	 		</c:otherwise>
	 	</c:choose>
  </display:table>
  <br/>
</body> 
</html>
