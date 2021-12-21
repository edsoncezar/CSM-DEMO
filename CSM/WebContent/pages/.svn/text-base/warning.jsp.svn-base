	
	<!--/////////////////  BEGIN OF ERRORS AND ALERTS AREA ///////////////////////////////-->
	<!-- <a name="warningDiv"></a>
	<a name="errorsDiv"></a> -->
	<div class="warningDiv" id="warningDiv" style="display:none; z-index: 100001;  position: absolute; top:40%; left: 20%;" >
		<table width="100%" style="border:none;" >
			<tr>
				<td width="70" valign="middle" style="background-color: #FFE8B3;" align="center" rowspan="2">
					<img src="img/icons/dialog/success_transp.gif"> 
				</td>
				<td valign="middle" id="warning_area_txt" style="font-size: 12px; height: 60px">		
					<html:messages id="message" message="true">${message}</html:messages>
				</td>
			</tr>
			<tr align="right">
				<td><b style="float: right; cursor: pointer; font-size: 12px;" onclick="showHide('warningDiv');hideModal();">close<b></td>
			</tr>
		</table>
	</div>
	
	<div class="errorsDiv" id="errorsDiv" style="display:none; z-index: 100002;  position: absolute;  top:40%; left: 20%;">
		<table width="100%"  style="border: none;">
			<tr>
				<td width="70" valign="middle" style="background-color: #FF7142;" align="center"  rowspan="2">
					<img src="img/icons/dialog/dialog-warning.png">
				</td>
				<td valign="middle" id="error_area_txt" style="font-size: 12px; height: 60px">
					<html:errors/>
				</td>
			</tr>
			<tr align="right">
				<td><b style="float: right; cursor: pointer; font-size: 12px;" onclick="showHide('errorsDiv');hideModal();">close<b></td>
			</tr>
		</table>
	</div>

	<div class="confirmDiv" id="confirmDiv" style="display:none; z-index: 100000;  position: absolute; top:40%; left: 20%;" >
		<input type="hidden" id="confirmFlag" value="false">
		<input type="hidden" id="confirmed" value="false">
		<table width="100%" style="border:none;" >
			<tr>
				<td width="70" valign="middle" style="background-color: #D7A8FF;" align="center"  rowspan="2">
					<img src="img/icons/dialog/info_transp.gif"> 
				</td>
				<td valign="middle" id="confirm_area_txt" style="font-size: 12px; height: 60px">&nbsp;</td>
			</tr>
			<tr align="right">
				<td >
					<b style="cursor: pointer; font-size: 12px;" id="confirmDiv_okFunction">OK</b>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<b style="cursor: pointer; font-size: 12px;" id="confirmDiv_cancelFunction">Cancel</b>
				</td>
			</tr>
		</table>
	</div>
	
	<script>
		var requestObjectWarning 	= '<html:messages id="message" message="true">${message}</html:messages>';
		var requestObjectErrors 	= '<html:errors/>';
		var flagShowOthers 			= false;
		if(requestObjectWarning.length > 0 || requestObjectErrors.length > 0){
			//document.all().getElementById('modalBackgroundDiv').style.display = "block"
			//window.parent.document.getElementById('modalBackgroundDiv').style.display = "block";
			if($_ALL('modalBackgroundDiv')!= null){
				$_ALL('modalBackgroundDiv').style.display = "block";
				flagShowOthers = true;
			}
		}
		if(requestObjectWarning.length > 0 && flagShowOthers){
			showHide('warningDiv');
		}
		if(requestObjectErrors.length > 0 && flagShowOthers){
			showHide('errorsDiv');
		}
		
	</script>
	
	<!--/////////////////  END OF ERRORS AND ALERTS AREA ////////////////////////////// -->