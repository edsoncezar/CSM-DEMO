<%@ page import="br.com.alcatellucent.csm.bo.AclHistoryBO"%>
<%@ page import="br.com.alcatellucent.csm.bo.AclHistory"%>
<%@ page import="br.com.alcatellucent.csm.bo.AclBO"%>
<%@ page import="br.com.alcatellucent.csm.beans.User"%>
<%@ page import="br.com.alcatellucent.csm.beans.Acl"%>
<%@ page import="br.com.alcatellucent.csm.beans.ProcessorPacket"%>
<%@ page import="br.com.alcatellucent.csm.bo.ProcessorPacketBO"%>
<%@ page import="br.com.alcatellucent.csm.bo.UserBO"%>
<%@ page import="br.com.alcatellucent.csm.exception.ExceptionSys"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
	String id = request.getParameter("aclHistory.id");

	AclHistoryBO aclHistoryBo = new AclHistoryBO();
	ProcessorPacketBO processorPacketBO = new ProcessorPacketBO();
	AclBO aclBO = new AclBO();
	UserBO userBO = new UserBO();
	AclHistory aclHistory;
	User user = null;
	ProcessorPacket dppm = null;
	Acl acl;

	try {
		aclHistory = aclHistoryBo.edit(id);
		if (null != aclHistory.getCsmUserId() && aclHistory.getCsmUserId().length()>0) {
			user = userBO.edit(aclHistory.getCsmUserId());
		}
	
		if (null != aclHistory.getDppmId() && aclHistory.getDppmId().length()>0) {
			dppm = processorPacketBO.edit(aclHistory.getDppmId());
		}
			
		acl = aclBO.edit(aclHistory.getAclId());
		
		if (acl == null) { acl = new Acl();}
		if (dppm == null) { dppm = new ProcessorPacket();}
		if (user == null) { user = new User();}
		
	} catch (ExceptionSys e) {
		e.printStackTrace();
		return;
	}
	
	String statusApplied = (Boolean)(aclHistory.getStatusApplied())==false?"Error":"Applied - OK";
	
	long timeZone = ((java.util.GregorianCalendar) aclHistory.getDateTime()).getTimeInMillis();
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy dd:MM:ss");
		
	java.util.Date formatedDate = new java.util.Date();
	formatedDate.setTime(timeZone);
	
%>
<!-- Área de Informações  -->
<html>
<title></title>
<head>
<link href="css/csmlogStyle.css" rel="styleSheet" type="text/css"></link>
<script language="javascript" src="scripts/portal.js"
	type="text/javascript"></script>
<link href="css/portal.css" rel="styleSheet" type="text/css"></link>
</head>
<body>
<%@include file="pages/warning.jsp"%>
<br>
<fieldset style="width: 630px;"><legend>ACL History
Details</legend>
<table border="0" width="80%" height="50 px" cellpadding="0"
	cellspacing="1">
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td style="width: 30%; align: right;"><b>ACL Description:</b>
		</td>
		<td style="width: 70%;"><%=(acl.getDescription() == null?"***":acl.getDescription()) %></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td style="width: 30%; align: right;"><b>ACL Name:</b></td>
		<td style="width: 70%;"><%=(acl.getName()==null?"***":acl.getName()) %></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td style="width: 30%; align: right;"><b>DPPM:</b></td>
		<td style="width: 70%;"><%=(dppm.getName()==null?"***":dppm.getName())%></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td style="width: 30%; align: right;"><b>User:</b></td>
		<td style="width: 70%;"><%=user.getUserName() + " - " + user.getName()%></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td style="width: 30%; align: right;"><b>Date:</b></td>
		<td style="width: 70%;"><%=formatedDate %></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td style="width: 30%; align: right;"><b>Status Applied:</b></td>
		<td style="width: 70%;"><%=statusApplied %></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
</table>
<table border="0" width="98%" " height="50 px" cellpadding="0"
	cellspacing="1">
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr style="width: 100%">
		<td>
		<fieldset style="width: 100%;"><legend>Details</legend> <textarea
			rows="20" cols="300" style="width: 98%">
							<%=aclHistory.getDetails()%>
						</textarea></fieldset>
		</td>
	</tr>
</table>
</fieldset>

<tr>
	<td align="right" colspan="2">
	<table>
		<tr>
			<td align="center"><input type="button" class="submit"
				name='btBack' value='Back' onclick="javascript:history.back()"></td>
		</tr>
	</table>
	</td>
</tr>

</body>
</html>
