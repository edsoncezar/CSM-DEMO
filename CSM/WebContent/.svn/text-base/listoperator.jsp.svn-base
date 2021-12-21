<%@include file="pages/commonHeader.jsp" %> 
<html>
<head>
  <title>Operators</title>
	<link href="css/displaytag.css" 	rel="STYLESHEET" type="text/css">
	<script language="javascript">
  	checkRefresh('<%=null == request.getAttribute("refresh")? false:request.getAttribute("refresh")%>')
	<% request.setAttribute("refresh", Boolean.FALSE);%>
	
	function hideLinks(){
		
			if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE}', true)){
				HideDivs();
			}
		}
</script>
</head>
<body onload="hideLinks();">
<%@include file="pages/errorContainer.jsp"%>
<br>
<h3>Operators List</h3>
  
  <display:table pagesize="10" name="sessionScope.listOperators" id="row" export="true">
    <display:setProperty name="basic.show.header" value="true" />  
    <display:setProperty name="export.pdf" value="true" />
    <display:setProperty name="autolink" value="true" />
    <display:setProperty name="sortable" value="true" />
        
    <display:column width="30%" sortable="true" property="name" title="Name" maxLength="30"/>
    <display:column width="30%" sortable="true" property="email" title="Mail" maxLength="30"/>
    <display:column width="20%" sortable="true" property="mobile" title="Mobile" maxLength="30"/>
        
	 	<display:column style="text-align: center;" width="10%" href="operator.do?action=edit&" paramId="id" paramProperty="id" media="html"><%="Edit"%>
	 	</display:column>
 	
	 	<display:column style="text-align: center;" width="10%"  paramId="id" paramProperty="id" media="html" >
	 		<div class="hideDiv"><a href="javascript:deleteConfirm('operator.do?action=delete&id=${row.id}');"><%="Delete"%></a></div>	   
		</display:column> 
       
  </display:table>
</body>
</html>