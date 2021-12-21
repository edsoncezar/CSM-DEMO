<?xml version='1.0' encoding='iso-8859-1'?>
<%response.setContentType("text/xml");%> 

<%	
	//////// TREE ROOT ALWAYS INITILIZE ID = 0; //////////////////
	//////// NODE CHILDS DESC ORDER ON PROPERTY CHILD ///////////
	
	String saida = "<tree id='0' >" + 
						"<item  text='ROOT' id='a1' im0='noLicence/icon_earth2.gif' im1='noLicence/icon_earth2.gif' im2='noLicence/icon_earth2.gif' child='1'>"+
						"	<item  text='BR' id='a2' im0='book.gif' im1='book.gif' im2='book.gif' child='2'>"+
						"		<item  text='CS 2000' id='a2_1' im0='noLicence/icon_mini_server.gif' im1='noLicence/icon_mini_server.gif' im2='noLicence/icon_mini_server.gif' child='2'>"+
						"			<userdata name='myurl'>../context.do?action=list</userdata>" +
						"		</item>" +
						"	</item>" +
						"</item>" +	
					"</tree>";
								   
	out.print(saida);
%>


