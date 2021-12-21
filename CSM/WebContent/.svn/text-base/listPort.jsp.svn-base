<%@include file="pages/commonHeader.jsp" %>
<head>
  <title>ALU-Agya</title>
  <link rel="stylesheet" href="./css/print.css" type="text/css"  />
  <link rel="stylesheet" href="./css/maven-base.css" type="text/css"  />
  <link rel="stylesheet" href="./css/screen.css" type="text/css" />
</head>
  <br>	
  <script>
	checkRefresh('<%=null == request.getAttribute("refresh")? false:request.getAttribute("refresh")%>')
	<% request.setAttribute("refresh", Boolean.FALSE);%>
</script>
<%@include file="pages/errorContainer.jsp"%>
<br>
  <h3>Ports</h3>
  <display:table name="sessionScope.LIST_PORT"  export="true" id="row">
    <display:setProperty name="basic.show.header" value="true" /> 
    <display:setProperty name="export.pdf" value="true" />
    <display:column property="name" 		title="Port Name" 		maxLength="60"   />
	<display:column property="description" 	title="Description" 	maxLength="60"   />
	<display:column property="portNumber"	title="Port Number" 	maxLength="60"   /> 
    <display:column property="value" 		title="Bit Rate" 		maxLength="60"   /> 
	 	<display:column title="Modify" href="port.do?action=edit&" paramId="port.id" paramProperty="id"><%="<img src='img/edit.png' border='0'>"%></display:column>
	 	<display:column title="Delete" paramId="port.id" paramProperty="id">
	 	<a href="javascript:deleteConfirm('port.do?action=delete&id=${row.id}');">	   
	 	<%="<img src='img/del.png' border='0'>"%></a></display:column>
  </display:table>