<%@include file="pages/commonHeader.jsp" %>
<%@include file="pages/errorContainer.jsp"%>
<br>
<fieldset>
<legend>Actions For Device Manager</legend>
<table>
<c:choose>
	<c:when test="${hasDeviceManager eq false}">	
		<tr>
			<td></td>
			<td></td>
			<td><a href="deviceManager.do?action=initial&deviceManager.device.id=${deviceManager.device.id}">Add new Device Manager</a></td>
			<td></td>
		</tr>
	</c:when>
	<c:otherwise>
		<tr>
			<td></td>
			<td></td>
			<td><a href="deviceManager.do?action=edit&deviceManager.device.id=${deviceManager.device.id}">Edit Device Manager</a></td>
			<td></td>
		</tr>

		<tr>
			<td></td>
			<td></td>
			<td>
			<a href="javascript:deleteConfirm('deviceManager.do?action=delete&deviceManager.device.id=${deviceManager.device.id}');">
			Delete Device Manager</a></td>
<!--			<td><a href="deviceManager.do?action=delete&deviceManager.device.id=${deviceManager.device.id}">Delete Device Manager</a></td>-->
			<td></td>
		</tr>
	</c:otherwise>
</c:choose>
	</table>
	
</fieldset>
