package br.com.alcatellucent.csm.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class SessionControlAction extends CsmBaseAction{
	
	public void checkSession(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		boolean valid = false;  
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
        if ((session != null) && (session.getAttribute("userSession") != null)) {
            valid = true;
        }
        
        if(!valid)
        {
        	out.print("redirect");
        }
	}

}
