package br.com.alcatellucent.csm.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.PassRegex;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.bo.PassRegexBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.PasswordRegexForm;

public class PasswordRegexAction extends CsmBaseAction {

	public PasswordRegexAction() {
		super();
	}

	public ActionForward initial(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {

		String retorno = "";
		if(checkCredentials(request, new String[]{Role.MASTER_ROLE,Role.ROOT_ADMIN_ROLE}, true)){
			retorno=FORWARD_INITIAL;
		}else{
			retorno=FORWARD_FORBIDDEN;
		}
			return actionMapping.findForward(retorno);
	}

	public ActionForward list(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		HttpSession session = request.getSession();
		PassRegexBO passRegexBO = new PassRegexBO();
		Collection<PassRegex> passRegexList = passRegexBO.list();
		session.setAttribute("listPassRegex", passRegexList);
		return actionMapping.findForward("list");
	}

	public ActionForward edit(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {

		PassRegex passRegex = new PassRegex();
		PasswordRegexForm form = (PasswordRegexForm) actionForm;
		String retorno = "view";
		if(checkCredentials(request, new String[]{Role.MASTER_ROLE,Role.ROOT_ADMIN_ROLE}, true)){
			PassRegexBO passRegexBO = new PassRegexBO();
			try {
				passRegex=passRegexBO.edit(form.getPassRegex().getId());
				form.setPassRegex(passRegex);
			} catch (ExceptionSys ex) {
				ex.printStackTrace();
				retorno = "falid";
			}
		}else{
			retorno=FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(retorno);
	}

	public ActionForward save(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception {
		PassRegexBO passRegexBO = new PassRegexBO();
		HttpSession session = request.getSession();
		PasswordRegexForm form = (PasswordRegexForm) actionForm;
		PassRegex passRegex = form.getPassRegex();
		String retorno =FORWARD_LIST;
		if(checkCredentials(request, new String[]{Role.MASTER_ROLE,Role.ROOT_ADMIN_ROLE}, true)){
			try {
				passRegexBO.save(passRegex);
				showMessages(request, "default.save.ok", MESSAGE_WARNING);
				Collection<PassRegex> passRegexList = passRegexBO.list();
				session.setAttribute("listPassRegex", passRegexList);
			} catch (ExceptionSys e) {
				showMessages(request, "default.save.error", MESSAGE_ERROR);
				retorno = "view";
			}
		}else{
			retorno=FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(retorno);
	}

	public ActionForward delete(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys, Exception {
		PasswordRegexForm form = (PasswordRegexForm) actionForm;
		HttpSession session = request.getSession();
		String retorno = FORWARD_LIST;
		if(checkCredentials(request, new String[]{Role.MASTER_ROLE,Role.ROOT_ADMIN_ROLE}, true)){
			PassRegexBO passRegexBO = new PassRegexBO();
			try {
				passRegexBO.delete(form.getPassRegex().getId());
				showMessages(request, "default.delete.ok", MESSAGE_WARNING);
				Collection<PassRegex> passRegexList = passRegexBO.list();
				session.setAttribute("listPassRegex", passRegexList);
			} catch (ExceptionSys ex) {
				showMessages(request, "default.delete.error", MESSAGE_ERROR);
				logger.error(ex);
				retorno = FORWARD_LIST;
			}
		}else{
			retorno=FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(retorno);
	}
}
