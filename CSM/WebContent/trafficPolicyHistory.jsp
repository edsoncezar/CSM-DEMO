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

function hideLinks(){

	if(checkCrendentials('${sessionScope.userProfile.userRole.name}', '${GUEST_ROLE},${OPERATOR_ROLE}', true)){
		document.location.href ='trafficPolicyHistory.do?action=list';
	}
}
</script>


<title>Traffic Police History</title>
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

<body onload=" hideLinks();">

<script> 

function actionForm(action){

	document.forms[0].action.value = action; document.forms[0].submit();	
}
</script>

<fieldset style="width:630px;height: 120px"> 
<legend>Traffic Policy History Search</legend>
<html:form method="post" action="/trafficPolicyHistory?action=list">
		<table style="width:100%;border: none;" class="tblLogCriteria" >
					<tr>
						<th align="left">Status</th>
						<th align="left">Mode</th>
						<th align="left">From date</th>
					</tr>
					<tr>
						
						<td >
							<html:select property="statusApplied" style="width:70px">
								<html:option value="0">Error</html:option>
								<html:option value="1">Applied</html:option>
							</html:select>
						</td>
						
						<td >
							<html:select property="mode" style="width:100px">
								<html:option value="0">Manual</html:option>
								<html:option value="1">Scheduled</html:option>
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
					<tr>
						<td>&nbsp;</td>
					</tr>

		</table>
</html:form>
</fieldset>
<br/>
<fieldset style="width:650px;">
<legend>Results</legend>
<display:table id="row" pagesize="15" style="width:100%" name="sessionScope.LIST_TRAFFIC_HISTORY"  export="true">
    <display:setProperty name="basic.show.header" value="true" /> 
    <display:setProperty name="export.pdf" value="true" />
	<display:column sortable="true" property="trafficPolicy.name" 	title="Traf.Policy"	maxLength="30"   />
	<display:column sortable="true" property="context.name"	        title="Context"			maxLength="20"   />
	<display:column sortable="true" property="device.name"	        title="Device"			maxLength="20"   />
	<display:column sortable="true" property="processorPacket"   	decorator="br.com.alcatellucent.csm.utils.displaytag.ProcessorPacketDecorator"    
																	title="P.Packet"			maxLength="20"   />
	<display:column sortable="true" property="trafficPolicyHistory.date"   decorator="br.com.alcatellucent.csm.utils.displaytag.DateDecorator"    
																	title="Date"			maxLength="20"   />																		
	<display:column sortable="true" property="trafficPolicyHistory.date"   decorator="br.com.alcatellucent.csm.utils.displaytag.TimeDecorator"    
																	title="Time"			maxLength="20"   />																	
	<display:column sortable="true" property="trafficPolicyHistory.statusApplied"  decorator="br.com.alcatellucent.csm.utils.displaytag.StatusDecorator"   
																   	title="Status App"	maxLength="20"   />
	<display:column sortable="true" property="trafficPolicyHistory.mode"       	   decorator="br.com.alcatellucent.csm.utils.displaytag.ModeDecorator"
																	title="Mode"			maxLength="20"   />
    <html:link  action="/"  >
		<display:column title="" href="trafficPolicyHistoryDetails.jsp?trafficPolicyHistory.id=" paramId="trafficPolicyHistory.id" paramProperty="trafficPolicyHistory.id" media="html"><%="View"%></display:column>
    </html:link>
</display:table>

</fieldset>
</body>
</html>