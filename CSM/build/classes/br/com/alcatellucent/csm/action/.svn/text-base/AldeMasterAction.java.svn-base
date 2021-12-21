package br.com.alcatellucent.csm.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Alde;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.bo.AldeBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.AldeForm;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;

public class AldeMasterAction extends CsmBaseAction {

    public ActionForward edit(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) {

	String redir = FORWARD_EDIT;
	if(checkCredentials(request, new String[]{Role.GUEST_ROLE}, false)){
	    final AldeForm form = (AldeForm) actionForm;
	    final AldeBO aldeBO = new AldeBO();
	    Alde alde = form.getAlde();
	    Collection<Alde> aldeList;
	    try {
		aldeList = aldeBO.list(alde.getContextId(), true);
		if (!aldeList.isEmpty()) { // If found, here it is
		    alde = (Alde) aldeList.toArray()[0];
		    form.setAlde(alde);
		}
	    } catch (ExceptionSys e) {
		redir = FORWARD_ERROR;
		logger.error("Error listing ALDEs in context "
			+ alde.getContextId());
	    }
	}else{
		redir=FORWARD_FORBIDDEN;
	}
	return actionMapping.findForward(redir);
    }

    public ActionForward saveMaster(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys, Exception{

	String redir = FORWARD_SUCCESS;
	if(checkCredentials(request, new String[]{Role.GUEST_ROLE}, false)){
	    final AldeForm form = (AldeForm) actionForm;
	    final AldeBO aldeBO = new AldeBO();
	    try {
		aldeBO.savePlusConfig(form.getAlde(), form.getAldeConfig());
		logger.debug("ALDE Master successfully saved in context "
			+ form.getAlde().getContextId());
		showMessages(request, "default.save.ok", MESSAGE_WARNING);
		request.setAttribute("parentId", form.getAlde().getContextId());
		// Igor - 2007-10-01 - Inclusão de Log da aplicação.
		this.saveLog(request, "alde_master.title", "key = " + form.getAlde().getId(), CsmLogSeverity.LOW_SEVERITY.getValue());
	    } catch (ExceptionSys e) {
	    	showMessages(request, "default.save.error", MESSAGE_ERROR);
	    	redir = FORWARD_FAILED;
	    	logger.error("Error saving ALDE Master in context "
			+ form.getAlde().getContextId());
	    }
	}else{
		redir=FORWARD_FORBIDDEN;
	}
	return actionMapping.findForward(redir);
    }
    
    public ActionForward back(ActionMapping actionMapping,
    	    ActionForm actionForm, HttpServletRequest request,
    	    HttpServletResponse response) throws ExceptionSys, Exception{

    	return actionMapping.findForward("back");
        }

}
