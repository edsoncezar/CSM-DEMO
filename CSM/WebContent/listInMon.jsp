<%@include file="pages/commonHeader.jsp" %>
<html>
<head>
  <title>InMon</title>
	<link href="css/displaytag.css" 	rel="STYLESHEET" type="text/css">
	<script language="javascript">
  	checkRefresh('<%=null == request.getAttribute("refresh")? false:request.getAttribute("refresh")%>')
	<% request.setAttribute("refresh", Boolean.FALSE);%>
</script>
</head>
<body>
<%@include file="pages/errorContainer.jsp"%>
<br>
<h3>InMon List</h3>
  
  <display:table pagesize="10" name="sessionScope.listInMon" id="row" export="true">
    <display:setProperty name="basic.show.header" value="true" />  
    <display:setProperty name="export.pdf" value="true" />
    <display:setProperty name="autolink" value="true" />
    <display:setProperty name="sortable" value="true" />
        
    <display:column width="30%" sortable="true" property="name" title="Name" maxLength="30"/>
    <display:column width="30%" sortable="true" property="url" title="URL" maxLength="30"/>
        
	 	<display:column width="10%" href="inmon.do?action=edit&" paramId="id" paramProperty="id"><%="Edit"%>
	 	</display:column>
 	
	 	<display:column  width="10%"  paramId="id" paramProperty="id" >
	 	<a href="javascript:deleteConfirm('inmon.do?action=delete&id=${row.id}');"><%="Delete"%></a>	   
		</display:column> 
       
  </display:table>
</body>
</html>