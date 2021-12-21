<!-- /////////////////////// TabMenu //////////////////////////////////////////////////////////////// -->
<%@ taglib 	uri="http://java.sun.com/jstl/core_rt" 			prefix="c"%>
<%@ taglib 	uri="http://java.sun.com/jsp/jstl/functions"	prefix="fn"%>
<link href="css/portal.css" rel="styleSheet" type="text/css"></link>

<c:set var="tabName" 			value="${fn:trim(param.tabNames)}" />
<c:set var="arrNames" 			value="${fn:split(tabName, \",\")}" />
<c:set var="arrDescriptions" 	value="${fn:split(param.tabDescription, \",\")}" />
<c:set var="divID"				value="${param.tabID}" />
<c:set var="currentTab"			value="${param.currentTab}" />

<div id="tabs4" align="left">
	<ul id="${divID}" style="float: left;">
	<c:forEach items="${arrNames}" var="actualTabName" varStatus="i">
		 <c:set var="isCurrent" value="" />
		 <c:if test="${actualTabName eq currentTab}">
		 	<c:set var="isCurrent" value="id='current'" />
		 </c:if> 
		<li ${isCurrent} onclick="openTab(this,'${actualTabName}','${param.tabNames}');"><a href="#"><span>${arrDescriptions[i.index]}</span></a></li>
	</c:forEach>
	</ul>
</div>

<!-- /////////////////////// TabMenu //////////////////////////////////////////////////////////////// -->