<%@page isErrorPage="true" %>
<%@include file="/pages/commonHeader.jsp"%>
<html>
	<body >
		<div class="errorsDiv" id="errorsDiv" style="display:block">
			<table width="100%"  style="border: none;">
				<tr>
					<td width="60" valign="middle">
						<img src="img/error.gif">
					</td>
					<td valign="middle" id="error_area_txt">
						<br>
						<h3>Unable to fulfill your request.</h3>
						<br>
						<b>Error description: </b> <%=exception.getMessage()%>
						<br>
					</td>
				</tr>
				<tr>
					<td colspan="2" width="60" valign="middle" align="right" nowrap="nowrap">
						<b style="" onclick="showHide('details')">Show Details..<b>
					</td>
				</tr>
			</table>
		</div>
		<br>
		<div id="details" style="display:none; border: 1px solid black; width: 95%"  > 
		<% 	
			out.flush(); 
			exception.printStackTrace(response.getWriter());
		%>
		</div>
	</body>
</html>
