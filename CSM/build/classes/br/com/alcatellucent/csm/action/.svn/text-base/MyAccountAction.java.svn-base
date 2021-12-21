/**
 * 
 */
package br.com.alcatellucent.csm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Contact;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.beans.UserAccessControl;
import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.bo.ContactBO;
import br.com.alcatellucent.csm.bo.UserAccessControlBO;
import br.com.alcatellucent.csm.bo.UserBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.MyAccountForm;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;
import br.com.alcatellucent.csm.passwordmanager.PasswordManager;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

/**
 * 
 * @author Edson Moreira Cezar
 * @date 09/04/2007
 * @version 1.0
 * 
 * @description : This class represents actions for MyAccount.
 */
public class MyAccountAction extends CsmBaseAction {


	public ActionForward initial(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		User user = (User) request.getSession().getAttribute("userSession");
		ContactBO contactBO = new ContactBO();

		String retorno = "initial";
		MyAccountForm form = (MyAccountForm) actionForm;
		form.setUser(user);
		form.setContact(contactBO.findByUserId(user.getId()));
		form.setNewPassword("");
		form.setConfirmNewPassword("");
		return actionMapping.findForward(retorno);
	}

	public ActionForward authentication(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String redir = "successAccount";

		MyAccountForm form = (MyAccountForm) actionForm;
				
		UserBO userBO = new UserBO();
		ContactBO contactBO = new ContactBO();
		UserAccessControlBO userAccessControlBO = new UserAccessControlBO();

		HttpSession session = request.getSession();

		// update user and contact information
		User user = form.getUser();
		Contact contact = form.getContact();
		contact.setUser(user);

		UserAccessControl userAccessControl = userAccessControlBO.findByUserId(user.getId());
		userAccessControl.setUserPassword(form.getUserAccessControl().getUserPassword());
		userAccessControl.setUser(user); 
			
		PasswordManager passwordManager;
		
		try {
			passwordManager = new PasswordManager();
		} catch (ExceptionSys es) {
			throw es;
		}

		String msg = null;
		msg=passwordManager.checkPasswordConfirmed(form.getNewPassword(), form.getConfirmNewPassword());
		if(msg==null){
			msg = passwordManager.checkPasswordCorrect(userAccessControl, form.getNewPassword());
		}
		
		if (null == msg ) {
			
	        try {
	        	userBO.save(user);
	        	contactBO.save(contact);
	        	showMessages(request, "default.save.ok", MESSAGE_WARNING);
	        } catch (Exception e) {
	        	showMessages(request, "default.save.error", MESSAGE_ERROR);
	        	redir = "falid";
		    }
			
			if (userAccessControlBO.authenticate(userAccessControl))  {
				userAccessControl.setUserPassword(UserAccessControlBO.getPasswd(user.getUserName(), form.getNewPassword()));			
				try {
					// Igor - 2007-10-01 - Inclusão de Log da aplicação.
					this.saveLog(request, "myaccount.title", "key = " + user.getId(), CsmLogSeverity.LOW_SEVERITY.getValue());
					userAccessControlBO.save(userAccessControl);
					passwordManager.putHistory(userAccessControl);
					this.saveLog(request, "myaccount.title.set_password", "key = " + user.getId(), CsmLogSeverity.LOW_SEVERITY.getValue());
					session.setAttribute("listUsers", userBO.findByContextId(user.getContextId()));
					showMessages(request, "default.save.ok", MESSAGE_WARNING); // Igor 2007-10-16: A versão original estava em local errado.
					form.setRefresh("true");
				} catch (ExceptionSys es) {
					redir = "falid";
				} catch (Exception e) {
					redir = "falid";
				}
			} else {
				redir = "errorPassword";
				showMessages(request, "error.password",MESSAGE_ERROR);
			}
		} else {
				redir = "errorPassword";
				showMessages(request, msg ,MESSAGE_ERROR);
		}

		return actionMapping.findForward(redir);
	}
	
    @SuppressWarnings("unchecked")
    public ActionForward list(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys {
	String redir = "list";
	MyAccountForm form = (MyAccountForm) actionForm;
	
	if (!(((UserProfile) request.getSession().getAttribute("userProfile"))
		.getUserRole().getName().equals(Role.ADMIN_ROLE))) {
	    redir = "forbidden";
	} else {
	    UserBO userBO = new UserBO();
	    HttpSession session = request.getSession();

	    session.setAttribute("listUsers", userBO.findByContextId(form.getUser().getContextId()));
	}
	return actionMapping.findForward(redir);
    }
}
