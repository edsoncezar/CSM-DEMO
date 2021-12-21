<html>
<script>
	var intervalCount;
	function loadLogin(){
		intervalCount = window.setInterval('count()', 1000);
		
	}
	
	
	function count(){
		
		var cvalue = document.getElementById('counter').innerHTML;
		var cvalue = cvalue - 1 ;
		if(cvalue == "0"){
			//window.clearInterval('intervalCount');
			document.location.href = "./login.jsp";
		}
		document.getElementById('counter').innerHTML=cvalue;
	}
</script>
<body onload="loadLogin();">
<div style="position: relative; top: 150px;" align="center">
<table cellpadding="0" cellspacing="0">	
		<tr>
			<td>
			<table width="600" >
				<tr>
					<td colspan="5">
					<p><img title="Alcatel-Lucent" height="66" alt="Alcatel-Lucent"
						src="img/logo.jpg" border="0"></p>
					<p align="right"><img src="img/logoSmall.gif" border="0"></p>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>				
					<td colspan="3">
						<center>
							<strong><font style="font-size: 20px" color=darkred>Your session has expired. Redirecting to Login page in <span id="counter">10</span> seconds...</font></strong>	
						</center>
					</td>
				</tr>
				<tr>
					<td colspan="5"></td>
				</tr>
				<tr>
					<td colspan="5"></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>				
					<td colspan="3">
						<center>
							<strong><font style="font-size: 15px">If you are not automatically redirected in 10 seconds, please <a href="./login.jsp">click here</a>.</font></strong>	
						</center>
					</td>
				</tr>
				
			</table>
	</td>
	</tr>
</table>
</div>

</body>
</html>
