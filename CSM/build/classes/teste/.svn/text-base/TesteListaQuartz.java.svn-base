package teste;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class TesteListaQuartz extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private String[] jobGroups;
	private String[] jobsInGroup;
	private static PrintWriter out;
	
	Scheduler scheduler;

	public void service(HttpServletRequest req, HttpServletResponse res)
				throws IOException, ServletException {

		res.setContentType("text/html");
		out = res.getWriter();
		
		int cGroups = 0;
		int cJobs   = 0;

		SchedulerFactory sf = new StdSchedulerFactory();
		
		try {
			scheduler = sf.getScheduler();
			
			jobGroups = scheduler.getJobGroupNames();
			
			sop("<html>");
			sop("<head>" +
					"<title>:: Quartz Listing ::</title>" +
					"<h1 align=\"center\"> *** Lista de Jobs no Quartz ***" +
					"<br/><br />" +
					"</head>");
			sop("<body>");
			sop("<table border=\"1\" celpadding=\"1\" celllspacing=\"1\">");
			sop("<th>Grupo</th>");
			sop("<th>Job name</th>");
			sop("<th>Observation</th>");
			
			for(String name : jobGroups) {
				sop("<tr>");
				sop("<td>" + name + "</td>");
				
				jobsInGroup = scheduler.getJobNames(name);
				
				cGroups++;
				
				for (String gName : jobsInGroup) {
					sop("<td>" + gName + "</td>");
					cJobs++;
					
					if (name.equalsIgnoreCase(gName)) {
						sop("<td>Equal</td>");
					} else {
						sop("<td><b>Not Equal</b></td");
					}
					
				}
			}

		} catch (SchedulerException se) {
			System.out.println("*** Erro *** ao tentar obter scheduler, erro: " + se.getMessage());
			se.printStackTrace();
			return;
		} catch (Exception e) {
			System.out.println("*** Erro *** indefinido, erro: " + e.getMessage());
			e.printStackTrace();
			return;
		}
		
		sop("<tr>");
		sop("<td><b> Total: " + cGroups + "</b></td>");
		sop("<td><b> Total: " + cJobs + "</b></td>");
		sop("<td>&nbsp;</td>");
		sop("</tr>");
		sop("</body></table></html>");
	}
	
	private void sop(String value) {
		
		out.println(value);
		
	}
}
