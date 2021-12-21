<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@page import="org.quartz.Trigger"%>
<%@page import="org.quartz.Scheduler"%>
<%@page import="org.quartz.impl.StdSchedulerFactory"%>
<%@page import="org.quartz.SchedulerFactory"%>


<%@ taglib 	uri="http://struts.apache.org/tags-bean" 		prefix="bean" %>
<%@ taglib 	uri="http://struts.apache.org/tags-html" 		prefix="html" %>
<%@ taglib 	uri="http://struts.apache.org/tags-logic" 		prefix="logic" %>
<%@ taglib 	uri="http://java.sun.com/jstl/core_rt" 			prefix="c"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" 			prefix="fmt"%>  
<%@ taglib  uri="http://displaytag.sf.net/el"				prefix="display"%>
<%@ taglib 	uri="http://java.sun.com/jsp/jstl/functions"	prefix="fn"%>

<link rel="icon" href="img/favicon.ico" type="image/vnd.microsoft.icon">

<link href="css/portal.css" rel="styleSheet" type="text/css"></link>
<script language="javascript" src="scripts/prototype.js"></script>
<script language="javascript" src="scripts/portal.js"></script>
<script language="javascript" src="scripts/calendar.js"></script>



<html>
<title> ALU-AGYA : SCHEDULER MONITORING TOOL </title>

<table width="600">
	<tr style="border: 1px solid silver;">
		<td>Actual Machine Time</td>
		<td><%=new java.util.Date()%></td>
	</tr>
</table>

<table style="border: 1px solid black;">
<tr style="background-color: silver;">
	<td>#</td>
	<td>Job ID</td>
	<td>Previous Run</td>
	<td>Next Run</td>	
</tr>
	
<%
// Initiate a Schedule Factory
SchedulerFactory schedulerFactory = new StdSchedulerFactory();
// Retrieve a scheduler from schedule factory

try{
Scheduler scheduler = schedulerFactory.getScheduler();

String[] triggerGroups;
String[] triggers;
String msg = "";

triggerGroups = scheduler.getTriggerGroupNames();
for (int i = 0; i < triggerGroups.length; i++) {
   triggers = scheduler.getTriggerNames(triggerGroups[i]);
   for (int j = 0; j < triggers.length; j++) {
      Trigger tg = scheduler.getTrigger(triggers[j], triggerGroups[i]);
      
      out.println("<tr><td>"+ j +"</td><td>" + tg.getName()+"</td><td>"+ tg.getPreviousFireTime() +"</td><td>"+  tg.getNextFireTime()+"</td></tr>");
   }
}        

}catch (Exception e) {
	// TODO: handle exception
}
%>
</table>
</html>