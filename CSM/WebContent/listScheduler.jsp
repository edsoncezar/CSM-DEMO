<%@include file="pages/commonHeader.jsp" %>
<head>
  <title>ALU-Agya</title>
	<link href="css/displaytag.css" 	rel="STYLESHEET" type="text/css">
	<script language="javascript">
  		checkRefresh('<%=null == request.getAttribute("refresh")? false:request.getAttribute("refresh")%>')
		<% request.setAttribute("refresh", Boolean.FALSE);%>
	</script>		
</head> 	

  <h3>Scheduler List</h3>
  <display:table pagesize="10" name="sessionScope.listScheduling" id="row" export="true">
    <display:setProperty name="export.pdf" value="true"/>
        
    <display:column width="40%" sortable="true" property="name" title="Name" maxLength="40"  />    
    <display:column width="40%" sortable="true" property="dateInc" title="Date" maxLength="30"  />    
        
   
	 	<display:column width="10%" title="" href="scheduler.do?action=edit" paramId="scheduling.id" paramProperty="id"><%="Edit"%></display:column>
	 	<display:column width="10%" title="" paramId="scheduling.id" paramProperty="id">
	 	<a href="javascript:deleteConfirm('scheduler.do?action=delete&id=${row.id}');"><%="Delete"%></a>	   
	 	</display:column>
                 
       
  </display:table>
  <br/>
 