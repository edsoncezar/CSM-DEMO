function TimePicker(ielem) {
	newwindow2=window.open('','name','height=10px,width=170px');
	var tmp = newwindow2.document;
	tmp.writeln('<script>function setTime() {var time=document.getElementById("ddlHour").value+":"+document.getElementById("ddlMinutes").value+":"+document.getElementById("ddlSeconds").value;')
	tmp.writeln('opener.document.getElementById("'+ielem.id+'").value=time}'+ '<\/script>');
	tmp.writeln('<table class="table1" border="0">');
	tmp.writeln('<tr><td><select name="ddlHour" id="ddlHour" onChange="javascript:setTime();">');
	for(var h=0;h<=9;h++)
	{
		tmp.writeln('<option value="'+0+h+'" >'+0+h+'</option>');
	}
	for(var hh=10;hh<=23;hh++)
	{
		tmp.writeln('<option value="'+hh+'" >'+hh+'</option>');
	}

	tmp.writeln('</select>:<select name="ddlMinutes" id="ddlMinutes" onChange="javascript:setTime();">');
	for(var m=0;m<=9;m++)
	{
		tmp.writeln('<option value="'+0+m+'" >'+0+m+'</option>');
	}
	for(var mm=10;mm<=59;mm++)
	{
		tmp.writeln('<option value="'+mm+'" >'+mm+'</option>');
	}
	tmp.writeln('</select>:<select name="ddlSeconds" id="ddlSeconds" onChange="javascript:setTime();">');
	for(var s=0;s<=9;s++)
	{
		tmp.writeln('<option value="'+0+s+'" >'+0+s+'</option>');
	}
	for(var ss=10;ss<=59;ss++)
	{
		tmp.writeln('<option value="'+ss+'" >'+ss+'</option>');
	}
	//tmp.writeln('</select><select name="ddlAMPM" id="ddlAMPM" onChange="javascript:setTime();">');
	//tmp.writeln('<option value="AM">AM</option>');
	//tmp.writeln('<option selected="selected" value="PM">PM</option>');
	tmp.writeln('</select></td></tr></table>');
	tmp.close();
}

/**
* @fileoverview Calendar Popup-indique um calendário para que o usuário selecione uma data
*
* @author klaure
* @since 14/2/2007
*/



var scfd=1;


/**
* verifica se o valor da entrada for válido 
* e faça o calendário e marque a data.
*
* @param {String} valor da entrada.
*/
function calendar(ielem) {
	updobj=ielem;
	getObj('fc').style.left=Left(ielem);
	getObj('fc').style.top=Top(ielem)+ielem.offsetHeight;
	getObj('fc').style.display='';
	
	// primeira verificação é válida
	curdt=ielem.value;
	curdtarr=curdt.split('/');
	isdt=true;
	//se a disposição da data não for isdt=false do número assim
	for(var k=0;k<curdtarr.length;k++) {
		if ((isNaN(curdtarr[k])))
		{
			isdt=false;
		}
	}
	if((isdt)&&(curdtarr.length<=3))
	{
	 if(((curdtarr[1]!=null)&&(curdtarr[1]!=''))){
	 var mcheck=parseInt(curdtarr[1]);
	  if(mcheck <0 && mcheck>13){
	 ccm=curdtarr[1]-1;
	 	}
	 	else
	 	curdtarr[1]=sccm+1;
	
	 	}
	 else {
	 	curdtarr[1]=sccm+1;	}
	 if((curdtarr[2]!=null)&&(curdtarr[2]!='')){
	 	ccy=curdtarr[2];}
	 else
	 	curdtarr[2]=sccy;
	 prepcalendar(curdtarr[0],curdtarr[1]-1,curdtarr[2]);
	
	}	
}
/**
* Obtém (getting) o objeto do formulário
* @param {String} valor do ID do objeto.
* @return  todos os objetos do elemento. 
*/
function getObj(objID)
{
    if (document.getElementById) {return document.getElementById(objID);}
    else if (document.all) {return document.all[objID];}
    else if (document.layers) {return document.layers[objID];}
}

/**
* verifica se o objeto que você clica é filho do formulário
* @param {String} event.
*/
function checkClick(e) {
	e?evt=e:evt=event;
	CSE=evt.target?evt.target:evt.srcElement;
	if (getObj('fc'))
		if (!isChild(CSE,getObj('fc')))
			getObj('fc').style.display='none';
}
/**
* verifica se o objeto é filho do formulário
* @param {String} event.
* @param {String} objeto.
*/
function isChild(s,d) {
	while(s) {
		if (s==d) 
			return true;
		s=s.parentNode;
	}
	return false;
}

/**
* posição na página do elemento
* @param {String} objeto.
* @return posição. 
*/
function Left(obj)
{
	var curleft = 0;
	if (obj.offsetParent)
	{
		while (obj.offsetParent)
		{
			curleft += obj.offsetLeft
			obj = obj.offsetParent;
		}
	}
	else if (obj.x)
		curleft += obj.x;
	return curleft;
}

/**
* posição na página do elemento
* @param {String} objeto.
* @return posição.
*/
function Top(obj)
{
	var curtop = 0;
	if (obj.offsetParent)
	{
		while (obj.offsetParent)
		{
			curtop += obj.offsetTop
			obj = obj.offsetParent;
		}
	}
	else if (obj.y)
		curtop += obj.y;
	return curtop;
}
	
document.write('<table id="fc" style="position:absolute;border-collapse:collapse;background:#FFFFFF;border:1px solid #ABABAB;display:none" cellpadding=2>');
document.write('<tr><td style="cursor:pointer" onclick="csubm()"><img src="img/arrowleftmonth.gif"></td><td colspan=5 id="mns" align="center" style="font:bold 13px Arial"></td><td align="right" style="cursor:pointer" onclick="caddm()"><img src="img/arrowrightmonth.gif"></td></tr>');
document.write('<tr><td align=center style="background:#ABABAB;font:12px Arial">S</td><td align=center style="background:#ABABAB;font:12px Arial">M</td><td align=center style="background:#ABABAB;font:12px Arial">T</td><td align=center style="background:#ABABAB;font:12px Arial">W</td><td align=center style="background:#ABABAB;font:12px Arial">T</td><td align=center style="background:#ABABAB;font:12px Arial">F</td><td align=center style="background:#ABABAB;font:12px Arial">S</td></tr>');
for(var kk=1;kk<=6;kk++) {
	document.write('<tr>');
	for(var tt=1;tt<=7;tt++) {
		num=7 * (kk-1) - (-tt);
		document.write('<td id="v' + num + '" style="width:18px;height:18px">&nbsp;</td>');
	}
	document.write('</tr>');
}
document.write('</table>');

document.all?document.attachEvent('onclick',checkClick):document.addEventListener('click',checkClick,false);

// Calendar script
var now = new Date;
var sccm=now.getMonth();
var sccy=now.getFullYear();
var ccm=now.getMonth();
var ccy=now.getFullYear();

var updobj;
/**
* verifica o lugar do evento.
* @param {String} evento.
* @return posição.
*/
function evtTgt(e)
{
	var el;
	if(e.target)el=e.target;
	else if(e.srcElement)el=e.srcElement;
	if(el.nodeType==3)el=el.parentNode; // defeat Safari bug
	return el;
}

/**
* verifica o tipo do evento.
* @param {String} evento.
* @return evento.
*/
function EvtObj(e){
if(!e)
e=window.event;
var key=e.keyCode;
if(key==13)
document.thisform.action="";
return e;
}
/**
* cor da data quando o foco está nele
* @param {String} evento.
*/
function cs_over(e) {
if(((evtTgt(EvtObj(e)).style.font).match('bold'))== null)
{
	evtTgt(EvtObj(e)).style.background='#ADD8E6';//azul
}
}
/**
* cor da data quando o foco saiu dele
* @param {String} evento.
*/
function cs_out(e) {

if(((evtTgt(EvtObj(e)).style.font).match('bold'))== null)
	evtTgt(EvtObj(e)).style.background='#D8BFD8';
}
/**
* obtem a data que foi clicada
* @param {String} evento.
*/
function cs_click(e) {
	updobj.value=calvalarr[evtTgt(EvtObj(e)).id.substring(1,evtTgt(EvtObj(e)).id.length)];
	getObj('fc').style.display='none';
	
}

var mn=new Array('JAN','FEB','MAR','APR','MAY','JUN','JUL','AUG','SEP','OCT','NOV','DEC');
var mnn=new Array('31','28','31','30','31','30','31','31','30','31','30','31');
var mnl=new Array('31','29','31','30','31','30','31','31','30','31','30','31');//ano bisexto
var calvalarr=new Array(42);

/**
* pinta as datas que existem no calendário
* @param {String} objeto.
*/
function f_cps(obj) {
if(((obj.style.font).match('bold'))== null)
{
	obj.style.background='#D8BFD8';//
}
	obj.style.font='10px Arial';
	obj.style.color='#333333';
	obj.style.textAlign='center';
	obj.style.textDecoration='none';
	obj.style.border='1px solid #6487AE';
	obj.style.cursor='pointer';
}
/**
* pinta os espaços que existem no calendário
* @param {String} objeto.
*/
function f_cpps(obj) {
	obj.style.background='#D8BFD8';//
	obj.style.font='10px Arial';
	obj.style.color='#333333';
	obj.style.textAlign='center';
	obj.style.textDecoration='line-through';
	obj.style.border='1px solid #6487AE';
	obj.style.cursor='default';

}
/**
* pinta a data escolhida no calendário
* @param {String} objeto.
*/
function f_hds(obj) {
	obj.style.background='#FFC0CB';//yellow
	obj.style.font='bold 10px Arial';
	obj.style.color='#333333';
	obj.style.textAlign='center';
	obj.style.border='1px solid #6487AE';
	obj.style.cursor='pointer';
}

/**
* prepara o calendário e pinta ele
* @param {String} dia.
* @param {String} mês.
* @param {String} ano.
*/
function prepcalendar(hd,cm,cy) {
	now=new Date();
	sd=now.getDate();
	td=new Date();
	td.setDate(1);
	td.setFullYear(cy);
	td.setMonth(cm);
	cd=td.getDay();
	getObj('mns').innerHTML=mn[cm]+ ' ' + cy;
	marr=((cy%4)==0)?mnl:mnn;
	for(var d=1;d<=42;d++) {
		f_cps(getObj('v'+parseInt(d)));
		if ((d >= (cd -(-1))) && (d<=cd-(-marr[cm]))) {
			dip=((d-cd < sd)&&(cm==sccm)&&(cy==sccy));
			htd=((hd!='')&&(d-cd==hd));
			if (dip)
				f_cpps(getObj('v'+parseInt(d)));
			else if (htd)
				f_hds(getObj('v'+parseInt(d)));
			else
				f_cps(getObj('v'+parseInt(d)));

			getObj('v'+parseInt(d)).onmouseover=(dip)?null:cs_over;
			getObj('v'+parseInt(d)).onmouseout=(dip)?null:cs_out;
			getObj('v'+parseInt(d)).onclick=(dip)?null:cs_click;
			
			getObj('v'+parseInt(d)).innerHTML=d-cd;	
			calvalarr[d]=''+(d-cd)+'/'+(cm-(-1))+'/'+cy;
		}
		else {
			getObj('v'+d).innerHTML='&nbsp;';
			getObj('v'+parseInt(d)).onmouseover=null;
			getObj('v'+parseInt(d)).onmouseout=null;
			getObj('v'+parseInt(d)).style.cursor='default';
			}
	}
}

prepcalendar('',ccm,ccy);
/**
* verifica ano bisexto
*/
function caddm() {
	marr=((ccy%4)==0)?mnl:mnn;
	
	ccm+=1;
	if (ccm>=12) {
		ccm=0;
		ccy++;
	}
	cdayf();
	prepcalendar('',ccm,ccy);
}
function csubm() {
marr=((ccy%4)==0)?mnl:mnn;

ccm-=1;
if (ccm<0) {
ccm=11;
ccy--;
}
cdayf();
prepcalendar('',ccm,ccy);
}

function cdayf() {
if ((ccy>sccy)|((ccy==sccy)&&(ccm>=sccm)))
	return;
else {
	ccy=sccy;
	ccm=sccm;
	cfd=scfd;
	}
}


	