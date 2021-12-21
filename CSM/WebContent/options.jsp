<%@include file="pages/commonHeader.jsp" %>

<br><br><br><br>
<center>

<fieldset>
<legend>Actions</legend>


<table  align="center">
 	
	<tr id="trActions">
		<td>
			<table>
				<tr>
					<td></td>
					<td><a href='context.do?action=initial&parentid=<%=request.getParameter("idroot")%>'>ADD</a></td>
				</tr>				
				<tr>
					<td></td>
					<td><a href='context.do?action=list'>LIST t</a></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</fieldset>

</center>