package teste;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alcatellucent.csm.logging.CsmLogSeverity;
import br.com.alcatellucent.csm.logging.persistence.CSMLogging;
import br.com.alcatellucent.csm.logging.persistence.CsmLog;

public class TestLog extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
		
		CsmLog log = new CsmLog();
		
		log.setDetails("Teste de logging - \nDetails");
		log.setEvent("Teste de log Event");
		log.setIpClient(req.getRemoteAddr());
		log.setIpServer(req.getLocalAddr());
		// log.setLogDate(
		log.setSeverity(CsmLogSeverity.FATAL_SEVERITY.getValue());
		
		try { 
			CSMLogging csmLogging = new CSMLogging(req, log);
			csmLogging.putLog();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
