<%@include file="pages/commonHeader.jsp"%>
<html>
<head>
<script>
	function actionForm(action) {		
		document.forms[0].action.value = action;
		document.forms[0].submit();	
	}
</script>
</head>
<body>
<%@include file="pages/errorContainer.jsp"%>
<br>
<fieldset style="width: 640px;; align: center"><legend>InMon</legend>
<html:form styleId="InMonForm" method="post" action="inmon.do">
	<html:hidden property="action" />
	<table width=100% border="0" cellpadding="0" cellspacing="0" align="left">
		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>URL</td>
			<td><html:text property="inMon.url" style="width:200px;" /></td>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>

		<tr>
			<td>&nbsp;</td>
			<td>Name</td>
			<td><html:text property="inMon.name" style="width:200px;" /></td>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>

		<tr>
			<td>&nbsp;</td>
			<td>Password&nbsp;&nbsp;</td>
			<td><html:password property="inMon.password" style="width:200px;" /></td>
			<td>&nbsp;</td>
		</tr>

		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>

		<tr>
			<td>&nbsp;</td>
			<td>View Mode&nbsp;&nbsp;</td>
			<td>
			    <html:select
				property="inMon.viewMode" size="1" styleId="inmon.viewMode">
				<html:option value="1">Frame</html:option>
				<html:option value="2">New Window</html:option>
			</html:select>
			</td>
			<td>&nbsp;</td>			
		</tr>

		<tr>
			<td colspan="4">&nbsp;</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width=100% style="text-align:right"><tr style="text-align:right">
				<td>
			  	  <input type=button class="submit"
			 		name='btBack' value='Back' onclick="javascript:actionForm('list')">
		    		<input type=button class="submit" name='btSubmit' value='Send'
					onclick="javascript:actionForm('save')">
				</td>
			    </tr>
			 </table>			
			</td>
		</tr>

	</table>
</html:form></fieldset>
</body>
</html>