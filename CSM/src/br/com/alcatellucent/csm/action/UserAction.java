package br.com.alcatellucent.csm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.HibernateException;

import br.com.alcatellucent.csm.beans.Contact;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.beans.UserAccessControl;
import br.com.alcatellucent.csm.bo.ContactBO;
import br.com.alcatellucent.csm.bo.PassHistoryBO;
import br.com.alcatellucent.csm.bo.RoleBO;
import br.com.alcatellucent.csm.bo.UserAccessControlBO;
import br.com.alcatellucent.csm.bo.UserBO;
import br.com.alcatellucent.csm.bo.UserProfileBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.facade.UserFacade;
import br.com.alcatellucent.csm.form.FormUser;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;
import br.com.alcatellucent.csm.passwordmanager.PasswordManager;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

public class UserAction extends CsmBaseAction {
	
	public UserAction() {
		super();
	}

    public ActionForward initial(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys {
	String returno = FORWARD_INITIAL;
	FormUser form = (FormUser) actionForm;
	User user = form.getUser();
	if(checkCredentials(request, new String[]{Role.OPERATOR_ROLE,Role.GUEST_ROLE}, false)){
	    user.setUserName("");
	    user.setId("");
	    user.setIsActive(true);
	    user.setName("");
	    form.setNewPassword("");
	    form.setConfirm("");
	    user.setDescription("");
	    user.setContextId(request.getParameter("contextId"));
	    form.setContact(new Contact());
	    form.setUserAccessControl(new UserAccessControl());
	    loadLists(actionForm, request.getParameter("contextId"), request);
	}else{
		returno = FORWARD_FORBIDDEN;
	}

	return actionMapping.findForward(returno);
    }

    @SuppressWarnings("unchecked")
    public ActionForward list(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys {
	String redir = FORWARD_LIST;
	if(checkCredentials(request, new String[]{Role.OPERATOR_ROLE,Role.GUEST_ROLE}, false)){
	    UserBO userBO = new UserBO();
	    HttpSession session = request.getSession();
	    User user = (User) request.getSession().getAttribute("userSession");
	    String contextId = request.getParameter("contextId");
	    //session.setAttribute("listUsers", userBO.findByContextId(contextId == null ? user.getContextId() : contextId));
	    if(checkCredentials(request, new String[]{Role.MASTER_ROLE}, true)){
	    	session.setAttribute("listUsers", userBO.findByContextId(contextId == null ? user.getContextId() : contextId));	// list all Profiles
		}else{
			session.setAttribute("listUsers", userBO.findByContextId(contextId == null ? user.getContextId() : contextId, true)); 	// list only not superUser associated Profiles 
		}

	}else{
		redir=FORWARD_FORBIDDEN;
	}
	return actionMapping.findForward(redir);
    }

    @SuppressWarnings("unchecked")
    public ActionForward edit(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys {
//	HttpSession session = request.getSession();
	String returno = FORWARD_EDIT;
	UserFacade userFacade = (UserFacade) request.getSession()
		.getServletContext().getAttribute("userFacade");
	FormUser form = (FormUser) actionForm;
	String id = form.getUser().getId();
	User user = new User();
	if(checkCredentials(request, new String[]{Role.OPERATOR_ROLE,Role.GUEST_ROLE}, false)){
		form.setNewPassword("");
	    form.setConfirm("");
	    user = userFacade.edit(id);
	    form.setUser(user);
	    UserAccessControlBO userAccessBo = new UserAccessControlBO();
	    UserAccessControl userAccessControl = userAccessBo
		    .findByUserId(user.getId());
	    form.setUserAccessControl(userAccessControl);

	    // Igor-Karine 2007-set-06: tratameno de date
	    if (userAccessControl.getExpirationDate() != null
		    && !(userAccessControl.getExpirationDate().equals(""))) {
		form.setExpirationDateForm(BasicValueCheck
			.dateToString(userAccessControl.getExpirationDate()));
	    }
	    ContactBO contactBO = new ContactBO();

	    Contact contact = contactBO.findByUserId(user.getId());
	    form.setContact(contact);
	    loadLists(actionForm, form.getUser().getContextId(), request);
	}else{
		returno=FORWARD_FORBIDDEN;
	}

	return actionMapping.findForward(returno);
    }
    

    @SuppressWarnings({ "unchecked", "deprecation" })
    public ActionForward save(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys, Exception {

	UserBO userBO = new UserBO();
	ContactBO contactBO = new ContactBO();

	HttpSession session = request.getSession();
	
	PasswordManager passwordManager;
	String redir = FORWARD_LIST;	
	FormUser form = (FormUser) actionForm;
	User user = form.getUser();
	Contact contact = form.getContact();
	contact.setUser(user);
	UserAccessControl userAccessControl = form.getUserAccessControl();
	
	userAccessControl.setUser(user);
	
	// Igor-Karine 2007-set-06: tratameno de date
	if(checkCredentials(request, new String[]{Role.OPERATOR_ROLE,Role.GUEST_ROLE}, false)){
		if (form.getExpirationDateForm() != null&& !(form.getExpirationDateForm().equals(""))) {
			userAccessControl.setExpirationDate(BasicValueCheck.stringToDate(form.getExpirationDateForm()));
		} else {
			if(form.getExpirationDateForm()!=null) {
				if (form.getExpirationDateForm().trim().equals("")) {
					userAccessControl.setExpirationDate(null);
				}
			}
		}

		if (form.getUser().getId() == null || form.getUser().getId().equals("") || !BasicValueCheck.isEmptyString(form.getNewPassword()) || !BasicValueCheck.isEmptyString(form.getConfirm())) {
		/*
		 * Here goes new password for new user
		 */
				try {
					passwordManager = new PasswordManager();
				} catch (ExceptionSys es) {
					throw es;
				}
				String  msg=passwordManager.checkPasswordConfirmed(form.getNewPassword(), form.getConfirm());
				if(msg==null)
					msg = passwordManager.checkPasswordCorrect(userAccessControl , form.getNewPassword());
				if (null == msg ) {
					userAccessControl.setUserPassword(UserAccessControlBO.getPasswd(user.getUserName(), form.getNewPassword()));			
					userAccessControl.setFlChangeNextLogin(true);
				}
				else{
					redir="view";
					loadLists(actionForm, form.getUser().getContextId(), request);
					showMessages(request,msg,MESSAGE_ERROR);
					return actionMapping.findForward(redir);
				}

		}
	
	    try {
	    	userBO.save(user);

	    	this.saveLog(request, "user.title", "key = " + user.getId(),
	    			CsmLogSeverity.LOW_SEVERITY.getValue());

	    	contactBO.save(contact);
	    	passwordManager = new PasswordManager();
	    	passwordManager.setLoginOk(userAccessControl);
			passwordManager.putHistory(userAccessControl);
			
	    	showMessages(request, "default.save.ok", MESSAGE_WARNING);
	    	refresh(request, TYPE_USER, user.getId());
	    	
	    } catch (Exception e) {
	    	this.saveLog(request, "user.title.error", "key = "
	    				+ user.getId() + e.getMessage(),
			CsmLogSeverity.FATAL_SEVERITY.getValue());

		if (e.getCause().getCause() instanceof HibernateException) {
		    showMessages(request, "error.userForm.save.Contraint",
			    CsmBaseAction.MESSAGE_WARNING);
		    redir="view";
		} else {
		    showMessages(request, "default.save.error", MESSAGE_ERROR);
		}
	    }
	    //session.setAttribute("listUsers", userBO.findByContextId(user.getContextId()));
	    if(checkCredentials(request, new String[]{Role.MASTER_ROLE}, true)){
	    	session.setAttribute("listUsers", userBO.findByContextId(user.getContextId()));	// list all Profiles
		}else{
			session.setAttribute("listUsers", userBO.findByContextId(user.getContextId(), true)); 	// list only not superUser associated Profiles 
		}
	}
	else{
		redir=FORWARD_FORBIDDEN;
	}
	return actionMapping.findForward(redir);
    }
    
    @SuppressWarnings({ "unchecked", "deprecation" })
    public ActionForward unblock(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys, Exception {
    	
    HttpSession session = request.getSession();
	UserBO userBO = new UserBO();
	PasswordManager pm=new PasswordManager();

	FormUser form = (FormUser) actionForm;
	User user = form.getUser();
	UserAccessControl userAccessControl = form.getUserAccessControl();
	    try {
		pm.setLoginOk(userAccessControl);
	    } catch (Exception e) {

		// Igor - 2007-10-01 - Inclusão de Log da aplicação.
		this.saveLog(request, "user.title.error", "key = "
			+ user.getId() + e.getMessage(),
			CsmLogSeverity.FATAL_SEVERITY.getValue());

		if (e.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
		    showMessages(request, "error.userForm.save.Contraint",
			    CsmBaseAction.MESSAGE_WARNING);
		} else {
		    showMessages(request, "default.save.error", MESSAGE_ERROR);
		}
	    }
	    loadLists(actionForm, form.getUser().getContextId(), request);

	    	showMessages(request, "default.unblock.ok", MESSAGE_WARNING);	
	    
	    //session.setAttribute("listUsers", userBO.findByContextId(user.getContextId()));
    	 if(checkCredentials(request, new String[]{Role.MASTER_ROLE}, true)){
  	    	session.setAttribute("listUsers", userBO.findByContextId(user.getContextId()));	// list all Profiles
  		}else{
  			session.setAttribute("listUsers", userBO.findByContextId(user.getContextId(), true)); 	// list only not superUser associated Profiles 
  		}	
	return actionMapping.findForward("view");
    }

    @SuppressWarnings("unchecked")
    public ActionForward delete(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys {
	UserBO userBO = new UserBO();
	HttpSession session = request.getSession();
	UserFacade userFacade = (UserFacade) request.getSession()
		.getServletContext().getAttribute("userFacade");
	String redir = "list";
	String contextId;
	String userName=null;
	User user = userBO.edit(request.getParameter("id"));
	contextId = user.getContextId();
	userName=user.getUserName();
	if (user.getUserName().equalsIgnoreCase("admin")) {
	    showMessages(request, "erro.userForm.Admin", MESSAGE_ERROR);
	} else {

		if(checkCredentials(request, new String[]{Role.OPERATOR_ROLE,Role.GUEST_ROLE}, false)){
		try {

		    ContactBO contactBo = new ContactBO();
		    Contact contact = contactBo.findByUserId(request
			    .getParameter("id"));

		    if (!(contact.getId() == null || contact.getId().equals(""))) {
			contactBo.delete(contact.getId());
		    }

		    UserAccessControlBO userAccessControlBo = new UserAccessControlBO();
		    UserAccessControl userAccessControl = userAccessControlBo
			    .findByUserId(request.getParameter("id"));

		    if (!(userAccessControl.getId() == null || userAccessControl
			    .getId().equals(""))) {
			userAccessControlBo.delete(userAccessControl.getId());
		    }

		    userFacade.delete(request.getParameter("id"));
		    PassHistoryBO passHistoryBO=new PassHistoryBO();
		    passHistoryBO.deleteByUserName(userName);
		    showMessages(request, "default.delete.ok", MESSAGE_WARNING);
		    
		  //Karine - refresh tree
		    refresh(request, TYPE_ACCESS_CONTROL, contextId.substring(0, 10));
		} catch (Exception e) {
		    showMessages(request, "default.delete.error", MESSAGE_ERROR);
		    // redir = "falid";
		}
		//session.setAttribute("listUsers", userBO.findByContextId(contextId));
		  if(checkCredentials(request, new String[]{Role.MASTER_ROLE}, true)){
		    	session.setAttribute("listUsers", userBO.findByContextId(contextId)) ;	// list all Profiles
			}else{
				session.setAttribute("listUsers", userBO.findByContextId(contextId, true)); 	// list only not superUser associated Profiles 
			}
	    }
		else{
			redir=FORWARD_FORBIDDEN;
		}
	    return actionMapping.findForward(redir);
	}

	//session.setAttribute("listUsers", userBO.findByContextId(contextId));
		if(checkCredentials(request, new String[]{Role.MASTER_ROLE}, true)){
		    session.setAttribute("listUsers", userBO.findByContextId(contextId)) ;	// list all Profiles
		}else{
			session.setAttribute("listUsers", userBO.findByContextId(contextId, true)); 	// list only not superUser associated Profiles 
		}
	return actionMapping.findForward(redir);
    }
    
    private void loadLists(ActionForm actionForm,String contextId, HttpServletRequest request) {

		RoleBO roleBO= new RoleBO();
		UserProfileBO profileBO = new UserProfileBO();

		FormUser form = (FormUser) actionForm;	
		try {
			form.setLisRole(roleBO.list()); 							// listing Roles
			if(checkCredentials(request, new String[]{Role.MASTER_ROLE}, true)){
				form.setCollProfile(profileBO.list(contextId));  		// list all Profiles
			}else{
				form.setCollProfile(profileBO.list(contextId, true)); 	// list only not superUser associated Profiles 
			}

		} catch (ExceptionSys e) {
			e.printStackTrace();
		}
			
	}
}
