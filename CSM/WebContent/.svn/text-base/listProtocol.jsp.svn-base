<%@include file="pages/commonHeader.jsp" %>
<html>
<head>
  <title>ALU-Agya</title>
	<link href="css/displaytag.css" 	rel="STYLESHEET" type="text/css">
	<script language="javascript">
  		checkRefresh('<%=null == request.getAttribute("refresh")? false:request.getAttribute("refresh")%>')
		<% request.setAttribute("refresh", Boolean.FALSE);%>
		
		function hideLinks(){
			if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${MASTER_ROLE}', false)){
				HideDivs();
		}
	}
	</script>
</head>
<body onload="hideLinks();">
<%@include file="pages/errorContainer.jsp" %>
<br>
  <h3>Protocols</h3>
  <display:table pagesize="15" name="sessionScope.LIST_PROTOCOL" id="row"  export="true">
    <display:setProperty name="basic.show.header" value="true" /> 
    <display:setProperty name="export.pdf" value="true" />
    <display:column width="30px" sortable="true" property="name" title="Protocol Name" maxLength="30"   />
	<display:column width="40px" sortable="true" property="description" 	title="Description" maxLength="40"   /> 
	<display:column width="10px" sortable="true" property="internalNumber" 	title="Number"/> 
	<display:column style="text-align: center" width="30px" href="protocol.do?action=edit&" paramId="protocol.id" paramProperty="id" media="html"><%="Edit"%></display:column>
	<display:column style="text-align: center" width="40px" media="html"> 
		<div class="hideDiv"><a href="javascript:deleteConfirm('protocol.do?action=delete&protocol.id=${row.id}');">Delete</a></div>
	</display:column> 
  </display:table>
</body>
</html>