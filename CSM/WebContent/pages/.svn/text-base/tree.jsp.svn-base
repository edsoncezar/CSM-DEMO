<!-- /////////////////////////// XTREE  ///////////////////////////////// -->
<%--<style>
	body {font-size:12px}
	.{font-family:arial;font-size:12px}
	h1 {cursor:hand;font-size:16px;margin-left:10px;line-height:10px}
	xmp {color:green;font-size:12px;margin:0px;font-family:courier;background-color:#e6e6fa;padding:2px}
	.hdr{
		background-color:lightgrey;
		margin-bottom:10px;
		padding-left:10px"
	}
</style>
		<div align="left" id="treeboxbox_tree" style=" 	width:256px; height:300px; 
													 	background-image: url('img/treeBack.jpg');	
													 	overflow:auto; 
													 	position:absolute; 
													 	margin-top: 0px;
													 	margin-left:0px;
													 	"></div>



		<div style="direction: rtl;	width:256px; height:300px; 
					background-image: url('img/treeBack.jpg');
					overflow:auto; 
				 	position:absolute; 
				 	margin-top: 0px;
				 	margin-left:0px;">
		<div align="left" id="treeboxbox_tree" style="  direction:ltr; 
													 	position:absolute; 
													 	margin-top: 0px;
													 	margin-left:0px;
													 	"></div>
		</div>--%> 
		<div align="left"  style=" 	width:256px; height:300px; 
													 	background-image: url('img/home/design/backTree.jpg');	
													 	overflow:auto; 
													 	position:absolute; 
													 	margin-top: 0px;
													 	margin-left:0px;
													 	">
													 		<div 	id="treeboxbox_tree" 
													 				style="width:225px; height:300px;" >
													 		</div>
													 	</div>
<script>
		
		tree = new dhtmlXTreeObject("treeboxbox_tree","100%","100%",0);
		tree.setImagePath("dhtmlXtree/imgs/");
		tree.loadXML("buildTree"); 
		tree.setOnClickHandler(doOnClick);
		
		function doOnClick(nodeId){
			var myUrl = tree.getUserData(nodeId,"myurl");
			var bodyContent  = document.getElementById("bodyContent"); 
			bodyContent.src = myUrl+'&nodeId='+nodeId;
			resetMenuColor('false');
			//window.parent.document.getElementById('actualNodeId').value = nodeId;
			// todo: ajax que verifica sessão.
			checkSession();
		}
		
		function doOnDblClick(nodeId){
			var myUrl = tree.getUserData(nodeId,"myurl");
			var bodyContent  = document.getElementById("bodyContent"); 
			// todo: ajax que verifica sessão.
			checkSession();
		}
		
		
		
	var url = 'sessionControl.do?action=checkSession';
	function checkSession(){
		  var myAjax = new Ajax.Request( url, {	method: 'get', 
											    parameters: '', 
											    onComplete: showStatusSession,
											    onSuccess: showSuccessSession,
											    onFailure: showError} ); 

 }
 	
	function showSuccessSession(obreq){
		//alert(obreq.responseText);
	}
	//function showError(obreq){
		//alert('erro');
//	}
	
	function showStatusSession(obreq){
		if(obreq.responseText=='redirect'){
			top.location.href = './redirSession.jsp';
		 }
	}
	
</script>


<!-- /////////////////////////// XTREE END ///////////////////////////////// -->

