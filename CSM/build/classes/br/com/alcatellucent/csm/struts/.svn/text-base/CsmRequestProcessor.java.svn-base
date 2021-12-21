package br.com.alcatellucent.csm.struts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.RequestProcessor;

/**
 * Este é o Request Processor usado no Xpat. Ele serve para realizar algumas
 * operações específicas da aplicação.
 * 
 */
public class CsmRequestProcessor extends RequestProcessor {

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.action.RequestProcessor#processPreprocess(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected boolean processPreprocess(HttpServletRequest request,
	    HttpServletResponse response) {

	HttpSession session = request.getSession(false);
	if (session == null) { // New session
	    try {
		request.getRequestDispatcher("/login.jsp").forward(request,
			response);
	    } catch (Exception e) {
	    }
	} else { // Existing session
	    if (session.getAttribute("userSession") != null) {
		return true;
	    } else {
		if (request.getServletPath().equals("/login.do")
			|| request.getServletPath().equals("/aldeAjax.do")) {
		    // Must ignore errors for Ajax and Login
		    // Ajax: Error rendering the screen
		    // Login: MUST be allowed for login process
		    session = request.getSession(true);
		    return true;
		} else {
		    try {
			// Not in the login page, must be redirected to it
			request.getRequestDispatcher("/login.jsp").forward(
				request, response);
		    } catch (Exception e) {
		    }
		}
	    }
	}
	return false;
    }

    /* (non-Javadoc)
     * @see org.apache.struts.action.RequestProcessor#processRoles(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.apache.struts.action.ActionMapping)
     */
    @Override
    //TODO Need to process roles by Struts or "by hand"
    protected boolean processRoles(HttpServletRequest arg0,
	    HttpServletResponse arg1, ActionMapping arg2) throws IOException,
	    ServletException {
	return super.processRoles(arg0, arg1, arg2);
    }
}