<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib 	uri="http://struts.apache.org/tags-bean" 	prefix="bean" %>
<%@ taglib 	uri="http://struts.apache.org/tags-html" 	prefix="html" %>
<%@ taglib 	uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib 	uri="http://java.sun.com/jstl/core_rt" 	prefix="c"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" 	prefix="fmt"%>  
<%@ taglib  uri="http://displaytag.sf.net/el"			prefix="display"%>

<html>
<head>

<title>Account Event History</title>
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

<fieldset style="width:630px;">
<legend>Results</legend>
<display:table id="row" pagesize="10" style="width:100%" name="sessionScope.accountList" export="true">
	<display:setProperty name="basic.show.header" value="true" />
	<display:setProperty name="export.pdf" value="true" />
	<display:column sortable="true" property="name" title="User Id" maxLength="30" />
	<display:column sortable="true" property="userIpAddress" title="User IP" maxLength="30" />
	<display:column sortable="true" property="nasIpAddress" title="Nas IP" maxLength="30" />
	<display:column sortable="true" property="timeSessionBegin" title="Start" maxLength="30" />
	<display:column sortable="true" property="timeSessionEnd" title="Finish" maxLength="30" />
</display:table>
<form method="post" action="Accounting.do">
	<input type="hidden" name="action" value="list" />
	<table style="border: none;" class="tblLogCriteria">
		<tr>
			<td align="left" width="60">
				User ID:&nbsp;
				<input type="text" name="username" id="username" />&nbsp;&nbsp;
				User IP:&nbsp;
				<input type="text" name="userIP" id="userIP" />&nbsp;&nbsp;&nbsp;
				<input type="submit" value="List Users" />
			</td>
		</tr>
	</table>
</form>
</fieldset>
</body>
</html>