
function showHide(objName){
		var obj =  $(objName);
		
		if(obj.style.display == "none"){
			obj.style.display = "block";
			return;
		}
		if(obj.style.display == "block" || obj.style.display == ""){
			obj.style.display = "none";
			return;
		}
	
}
// Function showHide com o style.displayType definido
function showHideDType(objName, typeShow){
		var obj =  $(objName);
		
		if(obj.style.display != "none" || obj.style.display == ""){
			obj.style.display = "none";
			return;
		}else{
			obj.style.display = typeShow;
			return;
		}
	
}

var msg="ALU-Agya";
function hidestatus(){

self.status=msg;
return true;
}
hidestatus();
if (document.layers)
	document.captureEvents(Event.MOUSEOVER | Event.MOUSEOUT | Event.CLICK)

document.onmouseover=hidestatus
document.onmouseout=hidestatus
document.onclick=hidestatus


var g_nodeId = "";
function checkRefresh(reload, nodeType, nodeId){
	g_nodeId = nodeId;
	if(reload == 'true'){
		try{
			$('treeboxbox_tree').innerHTML = "";
		}catch (e){
			window.parent.document.getElementById('treeboxbox_tree');
			//alert(e)
		}
		tree = new dhtmlXTreeObject("treeboxbox_tree","100%","100%",0);
		tree.setImagePath("dhtmlXtree/imgs/");
		tree.loadXML("buildTree?refresh=true&nodeType="+nodeType+"&nodeId="+nodeId);
		tree.setOnClickHandler(doOnClick);
		g_flagOpen = true;
	}
		
}

var g_flagOpen = false;
function openActualNode(){
  	if(g_flagOpen == true){
	var complete = false
	while(complete == false){
		try{
			var actualID = g_nodeId;
			tree.openItem(actualID);
			actualID = tree.getParentId(actualID);
			
			while(tree.getParentId(actualID)!= ""){
				tree.openItem(actualID);
				actualID = tree.getParentId(actualID);
			}
			complete = true;
		}catch(e){
			alert('e');
		}
	}
	g_flagOpen = false;
	}
}


function validateIP(objValidate){
	
	var partes = objValidate.value.split('.');
	
  	if (partes.length!=4) {
      	return false;
  	}
	  
 	for (i=0;i<4;i++) { 
     	num=partes[i];
     	if (num>255 || num<0 || num.length==0 || isNaN(num)){     	
     		return false;
    	}
 	}
 	return true;
}

function changeMenuColor(name_Menu, onParent){
	resetMenuColor(onParent);
	if(onParent == 'true'){
		window.parent.document.getElementById(name_Menu).style.color = "#75D1D1";
	}else{
		$(name_Menu).style.color = "#75D1D1";
	}
}

function resetMenuColor(onParent){
	if(onParent == 'true'){
		window.parent.document.getElementById('tabDashBoard').style.color 	= "#554295";
		window.parent.document.getElementById('tabAnalysis').style.color 	= "#554295";
		window.parent.document.getElementById('tabAlerts').style.color 		= "#554295";
		window.parent.document.getElementById('tabReport').style.color 		= "#554295";
		window.parent.document.getElementById('tabMyAccount').style.color 	= "#554295";
	}else{
		$('tabDashBoard').style.color 	= "#554295";
		$('tabAnalysis').style.color 	= "#554295";
		$('tabAlerts').style.color 		= "#554295";
		$('tabReport').style.color 		= "#554295";
		$('tabMyAccount').style.color 	= "#554295";
	}
}


function message(text, type){
	var textArea;
	var containerDiv;
	if(type == 'ERR'){
		//$('errorsDiv').style.display = "none";
		//textArea 		= $('error_area_txt');
		window.parent.document.getElementById('errorsDiv').style.display = "none";
		textArea 		= window.parent.document.getElementById('error_area_txt');
		textArea.innerHTML = "";
		textArea.innerHTML = text;
		showModal();
		//showHide('errorsDiv');
		showHideInParent('errorsDiv');

		//	document.location.href="#errorsDiv";
	}else{
		//$('warningDiv').style.display = "none";
		//textArea = $('warning_area_txt');
		window.parent.document.getElementById('warningDiv').style.display = "none";
		textArea = window.parent.document.getElementById('warning_area_txt');
		textArea.innerHTML = "";
		textArea.innerHTML = text;
		showModal();
		//showHide('warningDiv');
		showHideInParent('warningDiv');
		//document.location.href="#warningDiv";
	}
}

/// Objects of the dialogConfirm ////////////////////////////////////////////////////
var functionOK="";
var functionCancel="";

function confirmDialog(text, ok, cancel){
	functionOK 		= ok
	functionCancel 	= cancel;
	var textArea;
	var containerDiv;
	window.parent.document.getElementById('confirmDiv').style.display = "none";
	textArea 		= window.parent.document.getElementById('confirm_area_txt');
	textArea.innerHTML = "";
	textArea.innerHTML = text;
	showModal();
	showHideInParent('confirmDiv');
	window.parent.document.getElementById('confirmDiv_okFunction').onclick = okFunction;
	window.parent.document.getElementById('confirmDiv_cancelFunction').onclick = cancelFunction;
}

function okFunction(){
	showHideInParent('confirmDiv');
	hideModal();
	eval(functionOK);
	functionOK="";
}
function cancelFunction(){
	showHideInParent('confirmDiv');
	hideModal();
	eval(functionCancel);
	functionCancel="";
}
/////////////////////////////////////////////////////////////////////////////////////////////

function showHideModal(){
	var obj = window.parent.document.getElementById('modalBackgroundDiv');	
	if(obj.style.visibility == "hidden"){
		obj.style.visibility = "visible"
		return;
	}
	if(obj.style.visibility == "visible" || obj.style.visibility == ""){
		obj.style.visibility = "hidden"
		return;
	}	
}
function showModal(){
	var obj = window.parent.document.getElementById('modalBackgroundDiv');
	obj.style.visibility = "visible"
}

function hideModal(){
	var obj = window.parent.document.getElementById('modalBackgroundDiv');
	obj.style.visibility = "hidden"
}
function showHideInParent(objId){
	var obj = window.parent.document.getElementById(objId);	
	if(obj.style.display == "none"){
		obj.style.display = "block";
		return;
	}
	if(obj.style.display == "block" || obj.style.display == ""){
		obj.style.display = "none";
		return;
	}	
}
function toUppercase(object) {
	object.value = object.value.toUpperCase();
}


function isNumber(value){
        var reDigits = /(^-?\d\d*$)/;
        if (reDigits.test(value)) {
               return true; 
        } else {
               return false; 
        }
}

function checkAll(objCheckRuler, txtNameToCheck){
	var checks = document.getElementsByName(txtNameToCheck);
	for(var i=0; i< checks.length; i++){
			checks[i].checked = objCheckRuler.checked;
	}
}

function checkSelected(checkboxName){
	var checks = $A(document.getElementsByName(checkboxName));
	var isChecked = false;
	checks.each(function(checks){
		if(checks.checked){isChecked = true;}			
	});
	return isChecked;
}

function checkNumber(number, initialValue){
	if(number.value.length > 0){
		if(!isNumber(number.value)){
			message("<br>Please insert a <b>number</b>.", "ERR");
			number.value = initialValue;
		}else{
			hideModal()
			window.parent.document.getElementById('errorsDiv').style.display = "none";
		}
	}
}
////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////// MASKS //////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////
/**
 *
 * function mask(_mask, val)
 *
 * _mask = Mascara Exemplo: ##/##/#### ou ###.###.###-##
 * val   = Valor a ser formatado.
 *
 * Formata um valor  para a mascara definida.
 *
 * pedro.leao@ig.com.br 2003/08/16
 */
function mask(_mask, val) {
	var i, mki;
	var aux="";
	
	for(i=mki=0; i<val.length; i++, mki++) {
		if(_mask.charAt(mki)=='' || _mask.charAt(mki)=='#' || _mask.charAt(i)==val.charAt(i)) {
			aux+=val.charAt(i);
		} else {
			aux+=_mask.charAt(mki)+val.charAt(i);
			mki++;
		}
	}
	return aux;
}

/**
 * function maskEvent(field, _mask, event)
 *
 * field = Objeto que esta enviando o evendo onKeyPress()
 * _mask = Mascara Exemplo: ##/##/#### ou ###.###.###-##
 * event = Evento a ser observado.
 *
 * Formata um valor para a mascara definida conforma o valor vai sendo digitado.
 *
 * pedro.leao@ig.com.br 2003;08/16
 */
function maskEvent(field, _mask, event) {
	var key ='';
	var aux='';
	var len=0;
	var i=0;
	var strCheck = '0123456789';
	var rcode = (window.Event) ? event.which : event.keyCode;
	
	if(rcode == 13) {
		//Enter
		return true;
	}
	
	//Get key value from key code
	key=String.fromCharCode(rcode);
	
	if(strCheck.indexOf(key)==-1) {
		//Not a valid key
		return false;
	}
	
	aux=field.value+key;
	//window.alert(aux);
	aux=mask(_mask,aux);
	//window.alert(aux);
	field.value=aux;	
	return false;
}

/**
 *
 * function currencyFormat(fld, milSep, decSep, e)
 *
 * fld    = Objeto a ser verficado.
 * milSep = Separador para milhar.
 * decSep = Separador para decimal.
 * e      = Evento.
 *
 * Formata um valor decimal conforme for digitado no box.
 * Criação: Anonima (coletada em http://http://www.scriptbrasil.com/?class=2&secao=javascript&categoria=Formulários&menu=javascript&ini=1
 * Revisao: pedro.leao@ig.com.br	2003/08/16
 */
function currencyFormat(fld, milSep, decSep, e) {
	var sep = 0;
	var key = '';
	var i = j = 0;
	var len = len2 = 0;
	var strCheck = '0123456789';
	var aux = aux2 = '';
	var whichCode = (window.Event) ? e.which : e.keyCode;

	if (whichCode == 13) {
		return true;  // Enter
	}
	key = String.fromCharCode(whichCode);  // Get key value from key code
	if (strCheck.indexOf(key) == -1) {
		return false;  // Not a valid key
	}
	len = fld.value.length;
	for(i = 0; i < len; i++) {
		if ((fld.value.charAt(i) != '0') && (fld.value.charAt(i) != decSep)){
			 break;
		}
	}
	
	aux = '';
	for(; i < len; i++) {
		if (strCheck.indexOf(fld.value.charAt(i))!=-1){
			aux += fld.value.charAt(i);
		}
	}
	aux += key;
			
	len = aux.length;
	if (len == 0) {
		fld.value = '';
	} else if (len == 1) {
		fld.value = '0'+ decSep + '0' + aux;
	} else if (len == 2) {
		fld.value = '0'+ decSep + aux;
	} else if (len > 2) {
		aux2 = '';

		for (j = 0, i = len - 3; i >= 0; i--) {
			if (j == 3) {
				aux2 += milSep;
				j = 0;
			}
			aux2 += aux.charAt(i);
			j++;
		}
		fld.value = '';
		len2 = aux2.length;
		for (i = len2 - 1; i >= 0; i--) {
			fld.value += aux2.charAt(i);
		}
		
		fld.value += decSep + aux.substr(len - 2, len);
	}
	return false;
	
}

function deleteConfirm(action){
	var message = "Are you sure you want to delete this record ?";
	confirmDialog(message, "sendToUrl('"+action+"')", "");
}

function sendToUrl(action){
	var sendTo = window.parent.document.getElementById('bodyContent').src = action;	
}

/* Função que procura um id em qualquer window
 * tanto na propria window quanto nos parents dela
 * faz chamada da função searchElementById
*/
function $_ALL(objectId){
	return searchElementById(objectId);
}

/* Função que procura um id em qualquer window
 * tanto na propria window quanto nos parents dela.
 *  
 * Returns null, se o objeto não existir.
*/
function searchElementById(objectId){
	var wd = window.opener;
	var objectFind = "undefined";
	// Primeiro procura no proprio documento  e caso não encontre busca nos pais sucessivamente.
	if(document.getElementById(objectId)!="undefined"){
		objectFind = document.getElementById(objectId);
	}else{
		while(typeof(wd)!="undefined" && objectFind != "undefined"){
			alert(count+"::"+window.parent.document);
			try{
				if(wd.getElementById(objectId)!= "undefined"){
					objectFind = wd.getElementById(objectId);
				}
			}catch (e){
				// não faz nada 
			}
			wd = wd.window.opener;
		}
	}
	return objectFind;
}

/**
* Work as the CSMBaseAction.java -> checkCredentials method
*/
function checkCrendentials(userProfile,allowedProfiles, initialLogic){
	var allowed = !initialLogic;
	if(userProfile == "" ||  userProfile.length <= 0){
		message("Can not find your profile in session.<br>Please try login the system again", "ERR");
	}else{
		var arrProfiles;
		if(allowedProfiles.replace(" ","").indexOf(",")!= -1){
			arrProfiles = allowedProfiles.replace(" ","").split(",");
		}else{
			arrProfiles = new Array();
			arrProfiles[0] = allowedProfiles.replace(" ","");
		}
		 
		for(var i = 0; i < arrProfiles.length; i++){
			if(initialLogic == true){
				if(userProfile == arrProfiles[i]){
					allowed = true;
				}
			}else{
				if(userProfile == arrProfiles[i]){	
					allowed = false;
				}
			}
		}
	}
	return allowed;
}
	
/**
* Set readOnly or disabled to all objects in the page.
* It´s also removes all the scripts linked to this objects event observers. e.g.: onclick, onmousedown, etc..
* If you need to avoid this function in a particular object use:
* <span class="noDisable"> your object that will be not protected </span> 
*/

function protectAllFields(block){

	var arrTextArea = document.getElementsByTagName('textarea');
	for(var i=0; i < arrTextArea.length; i ++ ){
		arrTextArea[i].readOnly = block;
		if(block == 'true'){
			arrTextArea[i].style.background = "#EFEFEF";
		}else{
			arrTextArea[i].style.background = "";
		}
	}
	
	var arrSelect = document.getElementsByTagName('select');
	for(var i=0; i < arrSelect.length; i ++ ){
		arrSelect[i].disabled = block;
		if(block == 'true'){
			arrSelect[i].style.background = "#EFEFEF";
		}else{
			arrSelect[i].style.background = "";
		}
	}
	
	var arrInput = document.getElementsByTagName('input');
	for(var i=0; i < arrInput.length; i ++ ){
		if(arrInput[i].parentNode.className != 'enable' ){
							
			arrInput[i].onfocusin 	= null;
			arrInput[i].onfocusout 	= null;
			arrInput[i].onclick 	= null;
			arrInput[i].ondblclick 	= null;
			arrInput[i].onclick 	= null;
			arrInput[i].onmousedown	= null;
			arrInput[i].onmouseup 	= null;
			arrInput[i].ondblclick 	= null;
			arrInput[i].onkeypress 	= null;
			arrInput[i].onkeydown 	= null;
			arrInput[i].onkeyup 	= null;
			
			
			if(arrInput[i].type == 'text' || arrInput[i].type == 'password'){
				arrInput[i].readOnly = block;
				if(block == 'true'){
					arrInput[i].style.background = "#EFEFEF";
				}else{
					arrInput[i].style.background = "";
				}
			}
			if(arrInput[i].type == 'checkbox'){
				arrInput[i].disabled = block;
			}
			if(arrInput[i].type == 'radio'){
				arrInput[i].disabled = block;
			}
			if(arrInput[i].type == 'button'){
				arrInput[i].disabled = block;
				arrInput[i].style.backgroundImage = "url('img/buttonBackgroundDisabled.gif')";
			}
		}
	}
}
function verifyChecked(objStart, objEnd) {
	
	var checkStart = document.getElementById(objStart);
	var checkEnd = document.getElementById(objEnd);
	
	if (checkStart.checked) {
		checkEnd.value='true';
	} else {
		checkEnd.value='false';
	}
	
	return;
}

function setCheck(objHidden, objCheck) {

	var oHidden = document.getElementById(objHidden);
	var oCheck  = document.getElementById(objCheck);

	if (oHidden.value == 'true' ) {
	   oCheck.checked = true;
	} else {
	   oCheck.checked = false;
	}
}

/** 
*esta função deve ser usada para abrir e fechar as tabs 
*o parametro tabList deve conter todas os ids dos itens que devem 
*ser exibidos quando a tab for acessada	
**/
function openTab(object, toBeOpened, tabList){
	var arrTabList = tabList.replace(" ","").split(",");
	// limpa o id da tab atual
	try{
	$("current").id = "";
	}catch (e){}
	object.id = "current";
	for(var i=0; i < arrTabList.length; i++){
		if(arrTabList[i] != toBeOpened){
			$(arrTabList[i]).hide();
		}else{
			$(arrTabList[i]).show();
		}
	}
}

function HideDivs(){
	try{
	var arrDiv = document.getElementsByTagName('div');
	for(var i = 0; i < arrDiv.length; i++){
		if(arrDiv[i].className == "hideDiv"){
			arrDiv[i].style.display="none";
		}
	}
	var arrTr = document.getElementsByTagName('tr');
	for(var i = 0; i < arrTr.length; i++){
		if(arrTr[i].className == "hideDiv"){
			arrTr[i].style.display="none";
		}
	}
	}catch (e){
		//alert('error: '+ e);
	}
}

