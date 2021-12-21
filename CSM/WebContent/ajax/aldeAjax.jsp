<%@include file="/../pages/commonHeader.jsp"%>

<h2>ALDE STATUS</h2> 
<div id="aldeStatus"></div> 
<script>

	var url = 'aldeAjax.do?action=PoolingALDEStatus';
	function loadDDOSStatus(){
		  var myAjax = new Ajax.Request( url, {	method: 'get', 
											    parameters: '', 
											    onComplete: showStatus,
											    onSuccess: showSuccess,
											    onFailure: showError} ); 

 }
 	
	function showSuccess(obreq){
		//alert(obreq.responseText);
	}
	function showError(obreq){
		//alert('erro');
	}

	function showStatus(obreq){
		$('aldeStatus').innerHTML =  obreq.responseText; 
	}
	
	window.setInterval("loadDDOSStatus()", 5000);
</script>