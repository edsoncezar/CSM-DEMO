<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib 	uri="http://struts.apache.org/tags-bean" 		prefix="bean" %>
<%@ taglib 	uri="http://struts.apache.org/tags-html" 		prefix="html" %>
<%@ taglib 	uri="http://struts.apache.org/tags-logic" 		prefix="logic" %>
<%@ taglib 	uri="http://java.sun.com/jstl/core_rt" 			prefix="c"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" 			prefix="fmt"%>  
<%@ taglib  uri="http://displaytag.sf.net/el"				prefix="display"%>
<%@ taglib  uri="/WEB-INF/AgyaTagLib.tld"					prefix="se"%>

<se:hasSession name="userSession" page="/redirectToLogin.jsp"></se:hasSession>

<c:set var="MASTER_ROLE" 		value="MASTER" 		scope="page"/>
<c:set var="ROOT_ADMIN_ROLE"	value="ROOTADMIN"	scope="page"/>
<c:set var="ADMIN_ROLE" 		value="ADMIN" 		scope="page"/>
<c:set var="GUEST_ROLE" 		value="GUEST" 		scope="page"/>
<c:set var="OPERATOR_ROLE" 		value="OPERATOR" 	scope="page"/>

<html>
<head>
<script language="JavaScript">
// Script utilizado para formatar data

function addLoadEvent(func) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {
    window.onload = function() {
      if (oldonload) {
        oldonload();
      }
      func();
    }
  }
}

addLoadEvent(function() {
  Xaprb.InputMask.setupElementMasks();
});

</script>


<title>Auditing</title>
<link href="css/portal.css" rel="styleSheet" type="text/css"></link>
<link href="dhtmlXtree/css/dhtmlXTree.css" 	rel="STYLESHEET" type="text/css"></link>
<link href="css/displaytag.css" 	rel="STYLESHEET" type="text/css">
<script language="javascript" src="dhtmlXtree/js/dhtmlXCommon.js"></script>
<script language="javascript" src="dhtmlXtree/js/dhtmlXTree.js"></script>
<script language="javascript" src="scripts/prototype.js"></script>
<script language="javascript" src="scripts/portal.js"></script>
<script language="javascript" src="scripts/calendar_date_free.js"></script>
<script language="javascript" src="scripts/logging/html-form-input-mask.js"></script>
<script language="javascript" src="scripts/optionTransfer.js"></script>

<script type="text/JavaScript">

function hideLinks(){

	if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE},${OPERATOR_ROLE}', true)){
		document.location.href ='logSearch.do?action=initial';
	}
}

function actionForm(action){
	document.forms[0].action.value = action; 
	document.forms[0].submit();	
} 
</script>
</head>
<%@include file="pages/errorContainer.jsp"%>
<body onload="hideLinks();">
<fieldset style="width:630px;height: 120px"> 
<legend>Log Search</legend>
<html:form styleId="LogginForm" method="post" action="/logSearch.do?action=list">
		<table style="width:100%;border: none;" class="tblLogCriteria" >
					<tr>
						<th align="left">Start Date</th>
						<th align="left">Start Time</th>
						<th align="left">End Date</th>
						<th align="left">End Time</th>
						<th>User</th>
						<th></th>
						<th></th>

					</tr>
					<tr>
						<td >
							<input name="startDate" type="text" readonly="readonly"
								   style="width: 70px; text-align: right;" 	
								   align="right"
								   maxlength="10" 
								   onfocus="this.select();calendar(this)" 
								   onkeypress="" 
								   onclick="event.cancelBubble=true;this.select();calendar(this)"/>
						</td>
						<td >
						
							<input name="startTime" type="text" readonly="readonly"
								   id="startTime"
								   style="width: 70px; text-align: right;"
								   align="right"
								   maxlength="10"
								   onfocus="this.select();TimePicker('startTime','Time')"
								   onkeypress="" 
								   onclick="event.cancelBubble=true;this.select();TimePicker('startTime','Time');showHide('Time');"/>				
						</td>
						<td >
							<input name="endDate" type="text" readonly="readonly"
								   style="width: 70px; text-align: center;" 
								   maxlength="10" 
								   onfocus="this.select();calendar(this)" 
								   onkeypress=""
								   onclick="event.cancelBubble=true;this.select();calendar(this)"/>
						</td>
						<td >
							<input name="endTime" type="text" readonly="readonly"
								   id="endTime"						
								   style="width: 70px; text-align: right;"
								   align="right"
								   maxlength="10"
								   onfocus="this.select();TimePicker('endTime','EndTime')"
								   onkeypress="" 
								   onclick="event.cancelBubble=true;this.select();TimePicker('endTime','EndTime');showHide('EndTime');"/>
						</td>			

						<td>
							<input name="userId" type="text"
						   		   style="width: 100px; text-align: left;"
						           align="right"
						   		   maxlength="10"
							/>									
						</td>
						<td colspan="2">
						    <input
							type="button" class="submit" name="btSubmit" value="Send"
							onclick="javascript:actionForm('save')">
							
							<input
							type="button" class="submit" name="btClear" value="Clear"
							onclick="javasxcript:history.go(-1);">
												
						</td>																		
					</tr>
					<tr valign="top" >
						<td colspan="3" valign="top">		
							<div  id="Time" style="padding-top: 7px; height:30px; align:center; width:200px; z-index:-1; background:#D8BFD8; border: 1px solid black; display:none;"></div>
						</td>
						<td colspan="4" valign="top">
							<div  id="EndTime" style="padding-top: 7px; height:30px; align:center; width:200px; z-index:-1; background:#D8BFD8; border: 1px solid black; display:none;"></div>
						</td>
					</tr>
					
					<tr>
						<td colspan="4" style="text-align: left"><b>Sort by:</b>
							<input type="radio" name="sort" value="1" checked="checked"/>Descending Date (Default)
							<input type="radio" name="sort" value="2"/>Ascending Date
						</td>
					</tr>					
		</table>
</html:form>

</fieldset>
<br/><br/><br/>
<fieldset style="width:630px;">
<legend>Results</legend>
<display:table pagesize="20"  id="row" style="width:100%" name="sessionScope.LIST_CSM_LOG"  export="true">
    <display:setProperty name="basic.show.header" value="true" /> 
    <display:setProperty name="export.pdf" value="true" />
    <c:choose>
	    <c:when test='${ row.severity == 1 }'>
         	<display:column sortable="true" width="5%" paramId="id" paramProperty="id" media="html">
         		<html:img  align="center" src="img/medium_prty.gif" />		 	
		 	</display:column>		 	
	    </c:when>    
	    <c:when test='${ row.severity == 2 }'>
         	<display:column sortable="true" width="5%" paramId="id" paramProperty="id" media="html">		
         		<html:img  align="center" src="img/high_prty.gif" /> 	
		 	</display:column>		 	
	    </c:when>    
	    <c:when test='${ row.severity == 3 }'>
         	<display:column sortable="true" width="5%" paramId="id" paramProperty="id" media="html">
         		<html:img  align="center" src="img/critical_prty.gif"/>
		 	</display:column>		 	
	    </c:when>    
  		<c:otherwise>
         	<display:column sortable="true" width="5%" paramId="id" paramProperty="id" media="html">
         		<html:img  align="center" src="img/low_prty.gif" />	 	
		 	</display:column>		 		
	    </c:otherwise>
    </c:choose>        	
	<display:column sortable="true" property="eventTime"	title="Date/Time" 		maxLength="40"   />    
	<display:column sortable="true" property="event" 		title="Event"		 	maxLength="20"   />
	<display:column sortable="true" property="userName"		title="U.Name"		 	maxLength="40"  /> 
	<display:column sortable="true" property="user"			title="Name"		 	maxLength="40"  /> 
    <display:column sortable="true" property="ipClient"		title="IP Client" 		maxLength="20"   /> 
    <display:column sortable="true" property="ipServer"		title="IP Server" 		maxLength="20"   />
    <html:link  action="/"  >
		<display:column title="" href="CsmLogDetails.jsp?csmLog.id=" paramId="csmLog.id" paramProperty="id" media="html"><%="View"%></display:column>
    </html:link>
</display:table>

</fieldset>
</body>
</html>

