<%@include file="pages/commonHeader.jsp" %>
<html>
<head>
  <title>Schedulings</title>
  <link href="css/displaytag.css" 	rel="STYLESHEET" type="text/css">

  <script>
	checkRefresh('<%=null == request.getAttribute("refresh")? false:request.getAttribute("refresh")%>')
	<% request.setAttribute("refresh", Boolean.FALSE);%>
  </script>
  
  <script>   
	function confirm_( idScheduling,cronExpression,trafficPolicyId ){
		if( confirm("Startup Scheduling ?") ){
			 open("quartzDo?id="+idScheduling+"&cronExpression="+cronExpression+"&trafficPolicyId="+trafficPolicyId , 'new', 'width=300,height=150,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no');			 			
		}
	}	
	function down_( idScheduling ,trafficPolicyId ){
		if( confirm("Pause Scheduling ?") ){
			open("quartzDoDown?id="+idScheduling+"&trafficPolicyId="+trafficPolicyId, 'new', 'width=300,height=150,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no');	 			
	     	 
		}
	}
	
	function reload( idScheduling ){
		if( confirm("Reload Scheduling ?") ){
	     	 window.location="schedulerPanel.do?action=reload&id="+idScheduling;
		}
	}
	
	function delete_( idScheduling ){
		if( confirm("Delete Scheduling ?") ){
	     	 
	     	 open("quartzDoDelete?id="+idScheduling, 'new', 'width=300,height=150,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no');	 				     	 
		}
	}    
	
	function hideLinks(){
		
			if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE}', true)){
				HideDivs();
			}
		}
		
	
  </script>
      
      
</head>

<body onload="hideLinks();">
<tbody>
<%@include file="pages/errorContainer.jsp" %>
<br>
<h3>Scheduler Panel</h3>
  <display:table pagesize="15" name="sessionScope.listScheduling" id="row"  export="true">
    <display:setProperty name="basic.show.header" value="true" />  
    <display:setProperty name="export.pdf" value="true" />
    
    <c:choose>
	    <c:when test="${  row.statusQuartz == 'Disabled'  }">
         	<display:column width="5%" paramId="id" paramProperty="id" media="html" >         	 
         	   <div class="hideDiv"><a href="javascript:confirm_('${row.id}','${row.cronExpression}','${row.trafficPolicyId}')">
         	   		<img src='img/play.jpg' border='0' alt='Start scheduler'>
         	   </a></div>         	   		 	
		 	</display:column>		 	
	    </c:when>        
  		<c:otherwise>
			<display:column width="10%" paramId="id" paramProperty="id" media="html" >
				<div class="hideDiv"><a href="javascript:down_('${row.id}','${row.trafficPolicyId}')">
					<img src='img/pause.jpg' border='0' alt='Stop scheduler'>
				</a></div>					
	 		</display:column>       
	    </c:otherwise>
    </c:choose>
    
    <display:column  sortable="true" property="name" title="Job Name"   />
    <display:column  sortable="true" property="statusQuartz" title="Status"    />
    <display:column  sortable="true" property="dateStart" title="Start date"  style="width 30px"  />
    <display:column  sortable="true" property="timeStart" title="Start Time"  style="width 30px" />
    <html:link  action="/">
	 	<display:column style="text-align: center" width="10%" title="" href="scheduler.do?action=edit&" paramId="scheduling.id" paramProperty="id" media="html"><%="Edit"%></display:column>
 	</html:link>
 	
 	<display:column style="text-align: center" width="10%" title=""  paramId="scheduling.id" paramProperty="id" media="html">
	 	<div class="hideDiv"><a href="javascript:deleteConfirm('scheduler.do?action=delete&scheduling.id=${row.id}');" ><%="Delete"%></a></div>
	</display:column>
	
  </display:table>     
 </tbody>  
</body>   
  <script>
    var table = document.getElementById("row");    
    var tbody = table.getElementsByTagName("tbody")[0];
    var rows = tbody.getElementsByTagName("tr");
    // add event handlers so rows light up and are clickable
    for (i=0; i < rows.length; i++) {
        var value = rows[i].getElementsByTagName("td")[2].firstChild.nodeValue;
        if (value == 'Disabled') {
            rows[i].style.color = "red";
        }
    }
	</script>
</html>
  