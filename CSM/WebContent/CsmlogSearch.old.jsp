<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib 	uri="http://struts.apache.org/tags-bean" 		prefix="bean" %>
<%@ taglib 	uri="http://struts.apache.org/tags-html" 		prefix="html" %>
<%@ taglib 	uri="http://struts.apache.org/tags-logic" 	prefix="logic" %>
<%@ taglib 	uri="http://java.sun.com/jstl/core_rt" 		prefix="c"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" 		prefix="fmt"%>  
<%@ taglib  uri="http://displaytag.sf.net/el"				prefix="display"%>

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


<title>Log Search</title>
<link href="css/portal.css" rel="styleSheet" type="text/css"></link>
<link href="dhtmlXtree/css/dhtmlXTree.css" 	rel="STYLESHEET" type="text/css">
<script language="javascript" src="dhtmlXtree/js/dhtmlXCommon.js"></script>
<script language="javascript" src="dhtmlXtree/js/dhtmlXTree.js"></script>
<script language="javascript" src="scripts/prototype.js"></script>
<script language="javascript" src="scripts/portal.js"></script>
<script language="javascript" src="scripts/calendar_date_free.js"></script>
<script language="javascript" src="scripts/logging/html-form-input-mask.js"></script>
<script language="javascript" src="scripts/optionTransfer.js"></script>

<style>
	
</head>
<body>

<script> function actionForm(action){

document.forms[0].action.value = action; document.forms[0].submit();	} </script>

<fieldset style="width:800px; height: 160px"> 
<legend>Log Search</legend>
<html:form styleId="LogginForm" method="post" action="/logSearch?action=list">
		<table class="tblLogCriteria">
			   <tbody>
					<tr>
						<th align="left">Start Date</th>
						<th align="left">Start Time</th>
						<th align="left">End Date</th>
						<th align="left">End Time</th>
						<th>User Id</th>
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
						
							<input name="startTime" type="text"
								   id="input_time"
								   class="text input_mask mask_time"
								   style="width: 70px; text-align: right;"
								   align="right"
								   maxlength="10"
							/>						
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
							<input name="endTime" type="text"
								   id="input_time"
								   class="text input_mask mask_time"							
								   style="width: 70px; text-align: right;"
								   align="right"
								   maxlength="10"
							/>
						<td colspan="2">
							<input name="userId" type="text"
						   		   style="width: 200px; text-align: left;"
						           align="right"
						   		   maxlength="10"
							/>									
						</td>							
					</tr>
				</tbody>
			</table><br>
	<br/>
	<table>
		<tr>
			<td  align="right" colspan="2">
				<table >
					<tr>
						<td align="center"><input type=button class="submit" name='btClear'    value='Clear'  onclick="javascript:history.go(-1);" ></td>
	   		    		<td align="center"><input type=button class="submit" name='btBack'    value='Back'  onclick="javascript:actionForm('list')" ></td>
						<td align="center"><input type=button class="submit" name='btSubmit'  value='Send'  onclick="javascript:actionForm('save')"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</html:form>
</fieldset>
<br/>
<fieldset style="width:800px;">
<legend>Resultados</legend>
<display:table name="sessionScope.LIST_CSM_LOG"  export="true">
    <display:setProperty name="basic.show.header" value="true" /> 
    <display:setProperty name="export.pdf" value="true" />
    <display:column property="severity"		title="Severity" 		maxLength="60"   align="center"/>
	<display:column property="eventTime"	title="Event Date" 		maxLength="60"   />    
	<display:column property="event" 		title="Event"		 	maxLength="60"   />
	<display:column property="userId"		title="User ID"		 	maxLength="60"   /> 
    <display:column property="ipClient"		title="IP Client" 		maxLength="60"   /> 
    <display:column property="ipServer"		title="IP Server" 		maxLength="60"   />
    <display:column width="10%" title=""    href="CsmLogDetail.do?action=list&" paramId="csmLog.id" paramProperty="id"><%="View"%></display:column>
</display:table>

</fieldset>
</body>
</html>

