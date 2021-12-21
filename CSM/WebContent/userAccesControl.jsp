<%@include file="pages/commonHeader.jsp"%>

<fieldset>
<legend style="width: 150px;">User AccessControl</legend>
<form>
<table width="250px">
	<tr>
		<td></td>
		<td >Password</td>
		<td><input type="password"></td>
		<td></td>

	</tr>

	<tr>
		<td></td>
		<td >Expiration Date</td>
		<td><input type="text"   maxlength="10" onKeyPress="return(maskEvent(this, '99/99/99',event))"></td>
		<td></td>

	</tr>

	<tr>
		<td></td>
		<td  colspan="2">Last Changed Date: <b>25/07/2007</b></td>
		<td></td>
	</tr>
	
	<tr>
		<td></td>
		<td class="checkbox" colspan="2"><input type="checkbox"  >Change Password Next Login</td>
		<td></td>
	</tr>
	
	<tr>
		<td></td>
		<td colspan="2" align="right"><input type="button" class="submit"  value="Apply"></td>
		
		<td></td>

	</tr>
</table>
</form>
</fieldset>
