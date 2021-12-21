<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Teste de checkbox</title>

<script language="JavaScript">
function check() {
	var obj = document.getElementById('chk');
	
	if (obj.checked == true) {
		obj.checked = false;
	} else {
		obj.checked = true;
	}

}
function init() {

	var obj=document.getElementById('chk');
	alert(obj.value);
	obj.checked = true;

}


</script>

</head>
<body onLoad="init()">
<form id=frm>
<input type="checkbox" id="chk" value="on"  /> Teste
</form>

<br />
<a href="#" onclick="check()">Check/Uncheck</a>
<br />
</body>
</html>