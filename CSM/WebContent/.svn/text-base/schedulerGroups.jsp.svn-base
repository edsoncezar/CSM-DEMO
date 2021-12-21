<%@include file="pages/commonHeader.jsp" %>
<%@page import="br.com.alcatellucent.csm.beans.*" %>
<%@page import="java.util.*"%>

<%@page import="br.com.alcatellucent.csm.bo.PortProtocolGroupBO"%>

<%
	PortProtocolGroupBO groupBO =  new PortProtocolGroupBO();
	Collection<PortProtocolGroup> collGroup;
	
	collGroup = groupBO.list(); 
	Iterator it = collGroup.iterator();
	while(it.hasNext()){
		PortProtocolGroup group =  (PortProtocolGroup)it.next();
		out.print(group.getId());
	}
%>

<table width="400" cellpadding="2" cellspacing="0">
	<tr>
		<th>&nbsp;</th>
		<th>Group</th>
		<th>Description</th>
	</tr>
	<c:forEach items="${collGroup}" var="group">
		<tr>
			<td class="checkbox"><input type="checkbox" value="${group.id}"></td>
			<td>${group.name}</td>
			<td>${group.description}</td>
		</tr>
	</c:forEach>
</table>