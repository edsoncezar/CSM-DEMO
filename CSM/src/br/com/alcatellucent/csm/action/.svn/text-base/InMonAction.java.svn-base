package br.com.alcatellucent.csm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.InMon;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.bo.InMonBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.InMonForm;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

/**
 * 
 * @author Edson Moreira Cezar
 * @date 09/03/2007
 * @version 1.0
 * 
 * @description : This class represents Form of InMon.
 */
public class InMonAction extends CsmBaseAction {

    public ActionForward initial(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys {
	String retorno = "common";
	HttpSession session = request.getSession();
//	if(checkCredentials(request, new String[]{Role.GUEST_ROLE,Role.OPERATOR_ROLE}, false)){
	    InMonBO inMonBO = new InMonBO();
	    InMon inMon = inMonBO.getInMon();
	    if (inMon != null) {
		request.setAttribute("url", inMon.getUrl());
		request.setAttribute("viewMode", inMon.getViewMode());
		request.setAttribute("id", inMon.getId());
		request.setAttribute("credential", "?user=" + inMon.getName()
			+ "&password=" + inMon.getPassword());
	    } else {
		session.setAttribute("url", "#");
	    }
//	}else{
//		retorno=FORWARD_FORBIDDEN;
//	}
	return actionMapping.findForward(retorno);
    }

    @SuppressWarnings("unchecked")
    public ActionForward save(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys {
	String redir = "list";
	HttpSession session = request.getSession();
	if(checkCredentials(request, new String[]{Role.GUEST_ROLE,Role.OPERATOR_ROLE}, false)){
	    InMonBO inMonBO = new InMonBO();

	    InMonForm form = (InMonForm) actionForm;
	    InMon inMon = form.getInMon();

	    try {
		inMonBO.save(inMon);
		// Igor - 2007-10-01 - Inclusão de Log da aplicação.
		this.saveLog(request, "inmon.title", "key = " + inMon.getId(),
			CsmLogSeverity.LOW_SEVERITY.getValue());
		showMessages(request, "default.save.ok", MESSAGE_WARNING);
		form.setRefresh("true");
	    } catch (Exception e) {
		logger.error(e);
		showMessages(request, "default.save.error", MESSAGE_ERROR);
		// redir = "falid";
	    }
	    session.setAttribute("listInMon", inMonBO.list());
	}else{
		redir=FORWARD_FORBIDDEN;
	}
	return actionMapping.findForward(redir);
    }

    @SuppressWarnings("unchecked")
    public ActionForward list(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys {

	InMonBO inMonBO = new InMonBO();
	HttpSession session = request.getSession();

	session.setAttribute("listInMon", inMonBO.list());
	return actionMapping.findForward("list");
    }

    @SuppressWarnings("unchecked")
    public ActionForward edit(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys {
    
    String retorno = "view";
    if(checkCredentials(request, new String[]{Role.GUEST_ROLE,Role.OPERATOR_ROLE}, false)){
	HttpSession session = request.getSession();
	InMon inMon = new InMon();
	InMonBO inMonBO = new InMonBO();
	InMonForm form = (InMonForm) actionForm;
	if (BasicValueCheck.isEmptyString(form.getInMon().getId())) {
		inMon = new InMon();
	} else {
		inMon = inMonBO.edit(form.getInMon().getId());
	}
	form.setInMon(inMon);
	session.setAttribute("listInMon", inMon);
    }else{
    	retorno=FORWARD_FORBIDDEN;
    }
	return actionMapping.findForward(retorno);
    }

    @SuppressWarnings("unchecked")
    public ActionForward delete(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys {
	HttpSession session = request.getSession();
	InMonBO inMonBO = new InMonBO();
	InMonForm form = (InMonForm) actionForm;
	String redir = FORWARD_LIST;
	if(checkCredentials(request, new String[]{Role.GUEST_ROLE,Role.OPERATOR_ROLE}, false)){
	    try {
		String inMonId = request.getParameter("id");
		inMonBO.delete(inMonId);
		// Igor - 2007-10-01 - Inclusão de Log da aplicação.
		this.saveLog(request, "inmon.title.delete", "key = "
			+ form.getInMon().getId(), CsmLogSeverity.LOW_SEVERITY
			.getValue());
		showMessages(request, "default.delete.ok", MESSAGE_WARNING);
		form.setRefresh("true");
	    } catch (Exception e) {
		showMessages(request, "default.delete.error", MESSAGE_ERROR);
		// redir = "falid";
	    }
	    session.setAttribute("listInMon", inMonBO.list());
	}else{
		redir=FORWARD_FORBIDDEN;
	}
	return actionMapping.findForward(redir);
    }

    @SuppressWarnings("unchecked")
    public ActionForward connect(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys {

	HttpSession session = request.getSession();
	String retorno = "conecting";
	// InMonForm form = null;
	    InMonBO inMonBO = new InMonBO();
	    InMon inMon = inMonBO.getInMon();
	    if (inMon != null) {
		request.setAttribute("url", inMon.getUrl());
		request.setAttribute("viewMode", inMon.getViewMode());
		request.setAttribute("id", inMon.getId());
		request.setAttribute("credential", "?user=" + inMon.getName()
			+ "&password=" + inMon.getPassword());
	    } else {
		session.setAttribute("url", "inmon.do?action=edit");
		request.setAttribute("credential", "");
	    }
	return actionMapping.findForward(retorno);
    }
}
