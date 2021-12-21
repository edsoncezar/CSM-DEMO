<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib 	uri="http://struts.apache.org/tags-bean" 		prefix="bean" %>
<%@ taglib 	uri="http://struts.apache.org/tags-html" 		prefix="html" %>
<%@ taglib 	uri="http://struts.apache.org/tags-logic" 		prefix="logic" %>
<%@ taglib 	uri="http://java.sun.com/jstl/core_rt" 			prefix="c"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" 			prefix="fmt"%>  
<%@ taglib  uri="http://displaytag.sf.net/el"				prefix="display"%>
<%@ taglib 	uri="http://java.sun.com/jsp/jstl/functions"	prefix="fn"%>
<%@ taglib  uri="/WEB-INF/AgyaTagLib.tld"					prefix="se"%>

<%-- <se:hasSession name="userSession" page="/redirectToLogin.jsp"></se:hasSession> --%>

<link rel="icon" href="img/favicon.ico" type="image/vnd.microsoft.icon">

<link href="css/portal.css" rel="styleSheet" type="text/css"></link>
<link href="dhtmlXtree/css/dhtmlXTree.css" 	rel="STYLESHEET" type="text/css">

<script language="javascript" src="dhtmlXtree/js/dhtmlXCommon.js"></script>
<script language="javascript" src="dhtmlXtree/js/dhtmlXTree.js"></script>
<script language="javascript" src="scripts/prototype.js"></script>
<script language="javascript" src="scripts/portal.js"></script>
<script language="javascript" src="scripts/calendar.js"></script>
<script language="javascript" src="scripts/optionTransfer.js"></script>

<script>

function reloadTree(nodeType, nodeId){
try{
	window.parent.document.getElementById('treeboxbox_tree').innerHTML = "";
	window.parent.document.getElementById('treeboxbox_tree').innerHTML = "LOADING TREE<BR>PLEASE WAITING ... "; 
	window.setTimeout("window.parent.checkRefresh('true','" + nodeType + "','" + nodeId +"' )",1000);
	}catch (e){
		message("ERROR Reloading Tree : "+e, "ERR");
	}
}
</script>


<c:set var="MASTER_ROLE" 		value="MASTER" 		scope="page"/>
<c:set var="ROOT_ADMIN_ROLE"	value="ROOTADMIN"	scope="page"/>
<c:set var="ADMIN_ROLE" 		value="ADMIN" 		scope="page"/>
<c:set var="GUEST_ROLE" 		value="GUEST" 		scope="page"/>
<c:set var="OPERATOR_ROLE" 		value="OPERATOR" 	scope="page"/>
