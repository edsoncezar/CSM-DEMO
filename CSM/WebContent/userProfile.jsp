<%@include file="pages/commonHeader.jsp"%>

<fieldset>
<legend style="width: 150px;">User Profile</legend>
<form>
<table width="350px">
	<tr>
		<td></td>
		<td >Name</td>
		<td><input type="text" style="width: 200px;"></td>
		<td></td>

	</tr>

	<tr>
		<td></td>
		<td >Description</td>
		<td><textarea rows="4" cols="10"  style="width: 200px; height: 100px;"></textarea> </td>
		<td></td>

	</tr>
	<tr>
		<td></td>
		<td >Mobile</td>
		<td><input type="text" style="width: 200px;"></td>
		<td></td>
	</tr>
	
	<tr>
		<td></td>
		<td >E-Mail</td>
		<td><input type="text" style="width: 200px;"></td>
		<td></td>
	</tr>
	

	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="4"><a onclick="showHide('deniedList');"><b />&gt;&gt; Load Denied List</b></a></td>
	</tr>
	
	<tr id="deniedList" style="display: none;">
		<td colspan="4">
			<table class="commonTable" width="100%">
				<tr  class="commonHeader">
					<td width="25" >Deny</td><td>Device</td>
				</tr>
				<c:forEach begin="1" end="5" varStatus="i">
				<tr>
					<td class="checkbox" align="center"><input type="checkbox" checked="checked"></td><td>CS 123121</td>
				</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
	
	<tr>
		<td></td>
		<td colspan="2" align="right"><input type="button" class="submit" value="Apply"></td>
		
		<td></td>

	</tr>
</table>
</form>
</fieldset>
