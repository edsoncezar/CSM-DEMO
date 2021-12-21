package br.com.alcatellucent.csm.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.bo.UserBO;
import br.com.alcatellucent.csm.form.LoginForm;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;

public final class LogOutAction extends CsmBaseAction {

    public LogOutAction() {
    }

    @SuppressWarnings("unchecked")
    public ActionForward execute(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {

	  HttpSession session = request.getSession();
	//User user = (User) request.getSession().getAttribute("userSession");
	
	// Igor - 2007-10-02 - Inclusão de Log da aplicação.
	this.saveLog(request, "logout.title", "logout.details", CsmLogSeverity.LOW_SEVERITY.getValue());
	
	// I think this process is not really needed, but... to be sure	
	Enumeration<String> enumAtt = session.getAttributeNames();
	while (enumAtt.hasMoreElements()) {
	    try {
		session.removeAttribute(enumAtt.nextElement());
	    } catch (Throwable t) {
	    }
	}

	// Finally, invalidate the session
	session.invalidate();

	LoginForm form = (LoginForm) actionForm;
	form.getUser().setUserName("");
	form.getUserAccessControl().setUserPassword("");
	form.setConfirmNewPassword("");
	form.setNewPassword("");

	return actionMapping.findForward("outSession");
    }

}
