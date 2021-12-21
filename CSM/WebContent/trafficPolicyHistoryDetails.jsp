<%@ page import="br.com.alcatellucent.csm.bo.TrafficPolicyHistoryBO"%>
<%@ page import="br.com.alcatellucent.csm.beans.TrafficPolicyHistory"%>
<%@ page import="br.com.alcatellucent.csm.beans.TrafficPolicy"%>
<%@ page import="br.com.alcatellucent.csm.beans.User"%>
<%@ page import="br.com.alcatellucent.csm.beans.ProcessorPacket"%>
<%@ page import="br.com.alcatellucent.csm.bo.ProcessorPacketBO"%>
<%@ page import="br.com.alcatellucent.csm.bo.UserBO"%>
<%@ page import="br.com.alcatellucent.csm.exception.ExceptionSys"%>
<%@ page import="br.com.alcatellucent.csm.bo.TrafficPolicyBO"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ include file="pages/commonHeader.jsp"%>
<%
	String id = request.getParameter("trafficPolicyHistory.id");

	TrafficPolicyHistoryBO trafficPolicyHistoryBo = new TrafficPolicyHistoryBO();
	ProcessorPacketBO processorPacketBO = new ProcessorPacketBO();
	TrafficPolicyBO trafficPolicyBO = new TrafficPolicyBO();
	UserBO userBO = new UserBO();
	TrafficPolicyHistory trafficPolicyHistory;
	User user;
	ProcessorPacket dppm;
	TrafficPolicy trafficPolicy;

	try {
		trafficPolicyHistory = trafficPolicyHistoryBo.edit(id);
		if (trafficPolicyHistory.getCsmUserId() != null) {
			user = userBO.edit(trafficPolicyHistory.getCsmUserId());
		} else {
			user = null;
		}
		dppm = processorPacketBO.edit(trafficPolicyHistory.getDppmId());
		trafficPolicy = trafficPolicyBO.findById(trafficPolicyHistory.getTrafficPolicyId());
	} catch (ExceptionSys e) {
		e.printStackTrace();
		return;
	}
	
	String statusApplied = (Boolean)(trafficPolicyHistory.getStatusApplied())==false?"Error":"Applied - OK";
	
	long timeZone = ((java.util.GregorianCalendar) trafficPolicyHistory.getDate()).getTimeInMillis();
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
	java.util.Date formatedDate = new java.util.Date();
	formatedDate.setTime(timeZone);
	String mode = ((Integer)(trafficPolicyHistory.getMode())==0?"Manual":"Schedulled");
	
	
%>
<!-- Área de Informações  -->
<html>
	<title></title>
	<head>
	<link href="css/csmlogStyle.css" rel="styleSheet" type="text/css"></link>
	<script language="javascript" src="scripts/portal.js"></script>
	<link href="css/portal.css" rel="styleSheet" type="text/css"></link>
	</head>
	<body>
	<%@include file="pages/warning.jsp"%>
	<br>
	<fieldset style="width:630px;">
		<legend>Traffic Policy History Details</legend>
		<table border="0" width="80%" height="50 px" cellpadding="0" cellspacing="1">
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td style="width: 30%; align: right;"><b>History Description:</b> </td>
				<td style="width: 70%;" >
				<%String trafficPolicyName="";
				if(null!=trafficPolicy)
						{
							if(null!=trafficPolicy.getDescription())
								trafficPolicyName=trafficPolicy.getDescription();
						}
						else{
							trafficPolicyName="Traffic Policy was deleted. Traffic Policy ID= "+trafficPolicyHistory.getId();
						}
						%>
				<%=trafficPolicyName %></td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td style="width: 30%; align: right;"><b>Traffic Policy:</b> </td>
				<td style="width: 70%;" >
				<%=trafficPolicyName %></td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td style="width: 30%; align: right;" ><b>DPPM:</b></td>
				<td style="width: 70%;" >		
				<%String dppmName="";
				if(null!=dppm)
						{
							if(null!=dppm.getName())
								dppmName=dppm.getName();
						}
						else{
							dppmName="DPPM was deleted. DPPM ID= "+trafficPolicyHistory.getDppmId();
						}
						%><%=dppmName %></td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td style="width: 30%; align: right;" ><b>User:</b></td>
				<td style="width: 70%;" >
				<%
				String userName;
				if (trafficPolicyHistory.getMode() != 0) {
					userName = "";
				} else {
					userName="";
					if(null!=user) {
							if(null!=user.getUserName())
								userName=user.getUserName();
							if(null!=user.getName())
								userName=userName+"-"+user.getName();
						}
						else{
							userName="User was deleted. User ID= "+trafficPolicyHistory.getCsmUserId();
						}
				}
				%><%=userName%></td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td style="width: 30%; align: right;" ><b>Date:</b></td>
				<td style="width: 70%;" ><%=formatedDate %></td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td style="width: 30%; align: right;" ><b>Status Applied:</b></td>
				<td style="width: 70%;" ><%=statusApplied %></td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td style="width: 20%; align: right;" ><b>mode:</b></td>
				<td style="width: 70%;" ><%=mode %></td>
			</tr>
		</table>
		<table border="0" width=98%" height="50 px" cellpadding="0" cellspacing="1">
			<tr><td>&nbsp;</td></tr>
			<tr style="width: 100%">
				<td>
					<fieldset style="width: 100%;">
					<legend>Details</legend>
						<textarea rows="20" cols="300" style="width: 98%">
							<%=trafficPolicyHistory.getDetails()%>
						</textarea>
					</fieldset>
				</td>
			</tr>			
		</table>
	</fieldset>	

	<tr>	
		<td  align="right" colspan="2">
			<table >
				<tr>
	   			    <td align="center"><input type=button class="submit" name='btBack'    value='Back'  onclick="javascript:history.back()" ></td>
				</tr>
			</table>
		</td>
	</tr>
</body>
</html>
