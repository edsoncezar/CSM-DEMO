<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%
// data final igual a hoje  
Date dataFinal = new Date();  
  
// usa calendar para subtrair data  
Calendar calendarData = Calendar.getInstance();  
calendarData.setTime(dataFinal); 
out.print(dataFinal + ":"+ dataFinal.getTime() +"<br>");
  
int numeroDiasParaSubtrair = -2;  
  
// achar data de início  
calendarData.add(Calendar.DATE,numeroDiasParaSubtrair);  
Date dataInicial = calendarData.getTime();
out.print(dataInicial + ":"+ dataInicial.getTime() +"<br>");

%>