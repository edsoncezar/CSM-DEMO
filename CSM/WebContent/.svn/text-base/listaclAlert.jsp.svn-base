<%@include file="pages/commonHeader.jsp" %>
<html>
<head>
  <title>ALU-Agya</title>
  <link rel="stylesheet" href="./css/print.css" type="text/css"  />
  <link rel="stylesheet" href="./css/maven-base.css" type="text/css"  />
  <link rel="stylesheet" href="./css/screen.css" type="text/css" />
  <script type="text/JavaScript">

		function hideLinks(){
		
			if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE}', true)){
				HideDivs();
			}
		}

</script>
</head>
<body onload="hideLinks();">
   	
  <h3>ACL List - Alerts	</h3>
  <display:table name="sessionScope.listAcl"  export="true">
    <display:setProperty name="export.pdf" value="true"/>
        
    <display:column property="name" title="Name" maxLength="40"  />  
    <display:column property="description" title="Description" maxLength="40"  /> 
    <display:column property="sourceIp" title="Source IP" maxLength="40"  /> 
    <display:column property="destIp" title="Dest IP" maxLength="40"  />  
     
    <html:link  action="/" >
	 	<display:column title="" href="aclAlert.do?action=edit" paramId="acl.id" paramProperty="id"><%="Edit"%></display:column>
 	</html:link>
 	
 	<html:link  action="/" >
	 	<display:column title="" href="aclAlert.do?action=delete" paramId="acl.id" paramProperty="id">
	 	<div class="hideDiv"><%="Remove"%></div>
	 </display:column>
 	</html:link>  
                 
  </display:table>
  <br/>
  </body>
  </html>