<%@include file="pages/commonHeader.jsp" %>
<html>
<head>
	<link href="css/displaytag.css" 	rel="STYLESHEET" type="text/css">
	<script language="javascript">
  		checkRefresh('<%=null == request.getAttribute("refresh")? false:request.getAttribute("refresh")%>')
		<% request.setAttribute("refresh", Boolean.FALSE);%>
		
		function hideLinks(){

			if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${MASTER_ROLE},${ROOT_ADMIN_ROLE}', false)){
				HideDivs();
		}
}
	</script>
	<title>ALU-Agya</title>
</head>
<body onload="hideLinks();">
<%@include file="pages/errorContainer.jsp" %>
<br>
  <h3>Traffic Groups</h3>
  <display:table id="row" pagesize="10" name="sessionScope.LIST_PORT_PROTOCOL_GROUP"  export="true">
    <display:setProperty name="basic.show.header" value="true" /> 
    <display:setProperty name="export.pdf" value="true" />
    <display:column width="30%" sortable="true"  property="name" 		title="Group Name" 	maxLength="30"   />
	<display:column width="50%" sortable="true" property="description" 	title="Description" 	maxLength="40"   />
	<display:column style="text-align: center" width="10%" href="portProtocolGroup.do?action=edit&" paramId="portProtocolGroup.id" paramProperty="id" media="html"><%="Edit"%></display:column>
	<display:column style="text-align: center" width="10%" paramId="portProtocolGroup.id" paramProperty="id" media="html">
	 	 <div class="hideDiv"><a href="javascript:deleteConfirm('portProtocolGroup.do?action=delete&id=${row.id}');" ><%="Delete"%></a></div>
    </display:column>
  </display:table>
</body>  
</html>  