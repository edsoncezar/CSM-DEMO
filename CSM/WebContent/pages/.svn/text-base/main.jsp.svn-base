<%@include file="commonHeader.jsp" %>
<html>
<head>
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
</head>
<script>
  	/// This function load information from alde Status via Ajax /////////////////
	
	function loadDDOSStatus(){
		  var url = 'aldeAjax.do?action=PoolingALDEStatus';
		  var myAjax = new Ajax.Request( url, {	method: 'get', 
											    parameters: '', 
											    onComplete: showStatus,
											    onSuccess: showSuccess,
											    onFailure: showError} ); 

 	}
 	var statusTransport = 1
	function showSuccess(obreq){
		statusTransport = 1;
	}
	function showError(obreq){
		statusTransport = 0;
	}

	function showStatus(obreq){
	
		if (statusTransport == 1){
			$('DDOSStatus').innerHTML =  obreq.responseText;
		}else{
			$('DDOSStatus').innerHTML =  "Error fetching <br> Information...";
		}
	}
	
	function showDDOSAlerts(){
		
		var url = 'aldeAjax.do?action=alertList&' + Math.random();
		var myAjax = new Ajax.Request( url, {	method: 'get', 
											    parameters: '', 
											    onComplete: showAlertList,
											    onSuccess: showSuccess,
											    onFailure: showError} ); 
												    
											    
	}
	
	
	function showAlertList(obreq){

		var divAlertList = $('DDOSListDiv');
		divAlertList.style.position = 'relative';
		divAlertList.style.display = 'block';
		// Igor 2007-10-01
		divAlertList.style.height = '130px';
		divAlertList.style.overflow = 'auto';
		
		divAlertList.innerHTML =  obreq.responseText;
		 
	}
		
	window.setInterval("loadDDOSStatus()", 30000);
</script>

<body topmargin="0" leftmargin="0" onload="loadDDOSStatus();" >
<%@include file="warning.jsp" %>
<div id="modalBackgroundDiv"
	style="position: absolute; opacity:0.7; left:0px; top: 0px; z-index: 10003; width: 100%; height: 790px; visibility: hidden;"
	class="modalBackground">
</div>
<table border="0" cellpadding="0" cellspacing="0" style="width:990px; align:center; border: solid 1px black;" align="center">
	<tr height="79px" >
	
		<td align="left" colspan="3" width="990" valign="top" background="img/home/design/header2.jpg" style="" align="right" >
			<div style="position:absolute; float:left; margin-top:0px;
						vertical-align: bottom;
						background-repeat: no-repeat;
						background-image: url('img/home/design/teste.gif'); 
						width:256px; height:79px;">
				<div style="position: relative; margin-top: 59px;">
				</div>
			</div>
			<div style="position:absolute; float:left; margin-top:45px;">
				<table border="0" cellpadding="5" cellspacing="2" >
					<tr>
						<td align="center" width="390">&nbsp;<td>
						<td align="center" style="width:70px;">&nbsp;&nbsp;&nbsp;<a href="dashBoard.do?action=initial" target="bodyContent"><b class="testelink" id="tabDashBoard">DashBoard</b></a><td>
						<td align="center" style="width:60px;"><a href="inmon.do?action=connect" target="bodyContent" ><b class="testelink" id="tabAnalysis">Analysis</b></a><td>
						<td align="center" style="width:70px;"><b class="testelink" id="tabAlerts">Alerts</b><td>
						<td align="center" style="width:70px;"><b class="testelink" id="tabReport">Report</b><td>
						<td align="center" style="width:70px; margin-right: 0px;">
						<a href="myAccount.do?action=initial&acl.contextId=${param.parentId}" target="bodyContent" ><b class="testelink" id="tabMyAccount">MyAccount</b></a><td>
					</tr>
				</table>
			</div>
			<div style="float:right; margin-top: 5px; margin-left: 5px;" align="right">
				<strong >Help | <a href="about.jsp" target="bodyContent">About</a><strong> | <a href="submitLogOut.do" >Logout</a>&nbsp;&nbsp;&nbsp;<strong>
				<div id="DDOSStatus" style="margin-top: 25px; float:left; margin-left: 45px;">
					<a href="#" onclick='loadDDOSStatus()'>DDOS&nbsp;Status...</a>
				</div>
			</div>				 
		</td>
		
	</tr>
	<tr height="689px;" >
		<td width="250px" bgcolor="#FEFAFD" style="border: none; vertical-align: top;" align="left" >
			<!-- ///////////////////////// MENU LATERAL     ///////////////////////////////////// -->
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<%@include file="tree.jsp" %>
						<%@include file="leftDown.jsp" %>
					</td>
				</tr>
			</table>
			
			<!-- ///////////////////////// MENU LATERAL END ///////////////////////////////////// -->
		</td>
		<td bgcolor="#FFFFFF"  style="vertical-align: top;" id="tdFrmAlert">
			<!--   input type="text" id="actualNodeId" />	-->
			<div id="DDOSListDiv" style="position: absolute;"></div>
			<!-- ///////////////////////// BODY 1      ////////////////////////////////////////// -->
			<iframe  frameborder="0" style="float:left; margin-top: 15px; margin-left:40px;"  id="bodyContent"   name="bodyContent" 
					 width="700px" height="640px" scrolling="auto" src="dashBoard.do?action=initial"></iframe>
			<!-- ///////////////////////// BODY 1  END ////////////////////////////////////////// -->
		</td>
	</tr>
</table>
</body>
</html>