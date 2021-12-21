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


<title>ACL History</title>
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
</head>

<body>

<script> function actionForm(action){

document.forms[0].action.value = action; document.forms[0].submit();	} </script>

<fieldset style="width:650px;height: 130px"> 
<legend>ACL History Search</legend>
<html:form method="post" action="/AclHistory?action=list">
		<table style="border: none;" class="tblLogCriteria">
					<tr>
						<th align="left">Status</th>
						<th align="left">ACL</th>
						<th align="left">From date</th>
					</tr>
					<tr>
						
						<td >
							<html:select property="statusApplied" style="width:150px">
								<html:option value="0">Error</html:option>
								<html:option value="1">Applied</html:option>
							</html:select>
						</td>
						
						<td>
							<html:select property="aclId" style="width:250px">
								<html:option value="0">----</html:option>
								<c:forEach var="acl" items="${LIST_ACL}">
									<html:option value="${acl.id}">${acl.name}</html:option>
								</c:forEach>
							</html:select>
						</td>						
						
						<td >
							<input name="dateFrom" type="text" readonly="readonly"
								   style="width: 70px; text-align: center;" 
								   maxlength="10" 
								   onfocus="this.select();calendar(this)" 
								   onkeypress=""
								   onclick="event.cancelBubble=true;this.select();calendar(this)"/>
						</td>
					
						<td colspan="4"  style="text-align: right" >
						    <input
							type="button" class="submit" name="btSubmit" value="Send"
							onclick="javascript:actionForm('save')" />						
					  	</td>
					  	
					  	<td>
							<input
							type="button" class="submit" name="btClear" value="Clear"
							onclick="javascript:history.go(-1);" />&nbsp;  
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
<br/>
<fieldset style="width:630px;">
<legend>Results</legend>
<display:table id="row" pagesize="15" style="width:100%" name="sessionScope.LIST_ACL_HISTORY"  export="true">
    <display:setProperty name="basic.show.header" 	value="true" /> 
    <display:setProperty name="export.pdf" 			value="true" />
	<display:column sortable="true" property="acl.name" 	title="ACL"		maxLength="30"   />
	<display:column sortable="true" property="context.name"	        title="Context"			maxLength="20"   />
	<display:column sortable="true" property="processorPacket.name"   	    
																	title="P.Packet"		maxLength="20"   />
	<display:column sortable="true" property="aclHistory.dateTime"   decorator="br.com.alcatellucent.csm.utils.displaytag.DateDecorator"    
																	title="Date"			maxLength="20"   />																		
	<display:column sortable="true" property="aclHistory.dateTime"   decorator="br.com.alcatellucent.csm.utils.displaytag.TimeDecorator"    
																	title="Time"			maxLength="20"   />																	
	<display:column sortable="true" property="aclHistory.statusApplied"  decorator="br.com.alcatellucent.csm.utils.displaytag.StatusDecorator"   
																   	title="Status App"	maxLength="20"   />
    <html:link  action="/"  >
		<display:column title="" href="aclHistoryDetails.jsp?AclHistory.id=" paramId="aclHistory.id" paramProperty="aclHistory.id" media="html"><%="View"%></display:column>
    </html:link>
</display:table>

</fieldset>
</body>
</html>