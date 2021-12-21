package br.com.alcatellucent.csm.action;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.exception.ConstraintViolationException;

import br.com.alcatellucent.csm.beans.Device;
import br.com.alcatellucent.csm.beans.DeviceDenied;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.bo.DeviceDeniedBO;
import br.com.alcatellucent.csm.bo.RoleBO;
import br.com.alcatellucent.csm.bo.UserProfileBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.facade.DeviceFacade;
import br.com.alcatellucent.csm.facade.UserProfileFacade;
import br.com.alcatellucent.csm.form.UserProfileForm;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;

public class UserProfileAction extends CsmBaseAction {

    public ActionForward initial(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys, Exception {

	UserProfileFacade userProfileFacade = (UserProfileFacade) request
		.getSession().getServletContext().getAttribute(
			"userProfileFacade");

	DeviceFacade deviceFacade = (DeviceFacade) request.getSession()
		.getServletContext().getAttribute("deviceFacade");

	RoleBO roleBO = (RoleBO) request.getSession().getServletContext()
		.getAttribute("roleBO");

	HttpSession session = request.getSession();

	String returno = FORWARD_INITIAL;

	if(checkCredentials(request, new String[]{Role.OPERATOR_ROLE,Role.GUEST_ROLE}, false)){
	    String contextId = request.getParameter("contextId");

	    // Load Device
	    List<Device> listDevice = deviceFacade
		    .getListDeviceByHierarchy(contextId);

	    session.setAttribute("contextId", contextId);
	    session.setAttribute("listDevice", listDevice);
	    session.setAttribute("listDeviceDenied", "");
	    session.setAttribute("collRole", roleBO.list());
	    //session.setAttribute("listUserProfile", userProfileFacade.list(contextId));
	    UserProfileBO profileBO =  new UserProfileBO();
	    if(checkCredentials(request, new String[]{Role.MASTER_ROLE}, true)){
	    	session.setAttribute("listUserProfile", profileBO.list(contextId));	// list all Profiles
		}else{
			session.setAttribute("listUserProfile", profileBO.list(contextId, true)); 	// list only not superUser associated Profiles 
		}
	}
	else{
		returno=FORWARD_FORBIDDEN;
	}

	return actionMapping.findForward(returno);
    }

    @SuppressWarnings("unchecked")
    public ActionForward list(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys {
	String redir = FORWARD_LIST;
	if(checkCredentials(request, new String[]{Role.OPERATOR_ROLE,Role.GUEST_ROLE}, false)){
	    UserProfileFacade userProfileFacade = (UserProfileFacade) request.getSession().getServletContext().getAttribute("userProfileFacade");
	    HttpSession session = request.getSession();
	    User user = (User) request.getSession().getAttribute("userSession");
	    String contextId = request.getParameter("contextId");
	    UserProfileBO profileBO =  new UserProfileBO();
	    //session.setAttribute("listUserProfile", userProfileFacade.list(contextId == null ? user.getContextId() : contextId));
	    if(checkCredentials(request, new String[]{Role.MASTER_ROLE}, true)){
	    	session.setAttribute("listUserProfile", profileBO.list(contextId == null ? user.getContextId() : contextId));	// list all Profiles
		}else{
			session.setAttribute("listUserProfile", profileBO.list(contextId == null ? user.getContextId() : contextId, true)); 	// list only not superUser associated Profiles 
		}
	}
	else{
		redir=FORWARD_FORBIDDEN;
	}
	return actionMapping.findForward(redir);
    }

    public ActionForward edit(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys {

	UserProfileFacade userProfileFacade = (UserProfileFacade) request
		.getSession().getServletContext().getAttribute(
			"userProfileFacade");

	DeviceFacade deviceFacade = (DeviceFacade) request.getSession()
		.getServletContext().getAttribute("deviceFacade");

	UserProfileForm form = (UserProfileForm) actionForm;
	HttpSession session = request.getSession();
	String ret = "view";
	UserProfile userProfile = new UserProfile();

	if(checkCredentials(request, new String[]{Role.OPERATOR_ROLE,Role.GUEST_ROLE}, false)){
	    // Load Profile
	    userProfile = userProfileFacade.edit(request.getParameter("id"));
	    form.setUserProfile(userProfile);
	    RoleBO roleBO = (RoleBO) request.getSession().getServletContext()
		    .getAttribute("roleBO");
	    // Load Device Denied
	    DeviceDeniedBO deviceDeniedBO = new DeviceDeniedBO();
	    List<Device> listDevice = deviceFacade
		    .getListDeviceByHierarchy(userProfile.getContextId());

	    // form.set("id", userProfile.getId() );
	    // form.set("name", userProfile.getName() );
	    // form.set("description", userProfile.getDescription());
	    // form.set("parentId", userProfile.getContextId());
	    // form.set("action", "update" );
	    // form.set("role", userProfile.getRole());

	    // Igor 2007-09-19
	    session.setAttribute("contextId", userProfile.getContextId());

	    Collection<DeviceDenied> listDeviceDenied = deviceDeniedBO
		    .findIdProfile(userProfile.getId());

	    String[] tempDeviceDenied = new String[listDeviceDenied.size()];
	    int i = -1;

	    for (DeviceDenied d : listDeviceDenied) {
		tempDeviceDenied[++i] = d.getPrfid();
	    }

	    form.setDeviceDenied(tempDeviceDenied);
	    tempDeviceDenied = null;

	    session.setAttribute("collRole", roleBO.list());
	    request.setAttribute("parentId", userProfile.getContextId());
	    session.setAttribute("listDevice", listDevice);
	    session.setAttribute("listDeviceDenied", listDeviceDenied);
	}
	else{
		ret=FORWARD_FORBIDDEN;
	}
	return actionMapping.findForward(ret);
    }

    @SuppressWarnings("unchecked")
    public ActionForward save(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys, Exception {

	UserProfileFacade userProfileFacade = (UserProfileFacade) request
		.getSession().getServletContext().getAttribute(
			"userProfileFacade");
	UserProfileForm form = (UserProfileForm) actionForm;
	HttpSession session = request.getSession();
	String redir = FORWARD_LIST;
	if(checkCredentials(request, new String[]{Role.OPERATOR_ROLE,Role.GUEST_ROLE}, false)){
	    // userProfile.setId((String)form.get("id"));
	    // userProfile.setDescription((String)form.get("description") );
	    // userProfile.setName((String)form.get("name"));
	    // userProfile.setRole((String)form.get("role"));
	    // userProfile.setContextId((String)form.get("parentId"));
	    // String[] device = (String[])form.get("device");
	    // form.getUserProfile().setContextId(session.getAttribute(arg0))

	    // Igor 2007-07-19
	    form.getUserProfile().setContextId(
		    (String) session.getAttribute("contextId"));

	    try {
	    	
	    	if(userProfileFacade.nameExist(form.getUserProfile()) ){
	    		userProfileFacade.save(form.getUserProfile());

	    		// Igor - 2007-10-01 - Inclusão de Log da aplicação.
	    		this.saveLog(request, "user_profile.title", "key = "
	    			+ form.getUserProfile().getId(),
	    			CsmLogSeverity.LOW_SEVERITY.getValue());

	    		DeviceDeniedBO deviceDeniedBO = new DeviceDeniedBO();
	    		if (null == form.getDeviceDenied()) {
	    			form.setDeviceDenied(new String[0]);
	    		}
	    		deviceDeniedBO.saveDeviceDenied(form.getUserProfile(), form
	    			.getDeviceDenied());
	    		form.setRefresh("true");
	    		showMessages(request, "default.save.ok", MESSAGE_WARNING);
		
	    		//Karine - refresh tree
	    		refresh(request, TYPE_USERPROFILE, form.getUserProfile().getId());
	    	}else
	    	{
	    		showMessages(request, "erro.profileForm.DuplicateName", MESSAGE_ERROR);
	    		redir="view";
	    	}

	    } catch (Exception e) {

		// Igor - 2007-10-01 - Inclusão de Log da aplicação.
		this.saveLog(request, "user_profile.title.error", "key = "
			+ form.getUserProfile().getId(),
			CsmLogSeverity.FATAL_SEVERITY.getValue());

		showMessages(request, "default.save.error", MESSAGE_ERROR);
		// redir = "falid";
	    }
	    //session.setAttribute("listUserProfile", userProfileFacade.list(form.getUserProfile().getContextId()));
	    UserProfileBO profileBO =  new UserProfileBO();
	    if(checkCredentials(request, new String[]{Role.MASTER_ROLE, }, true)){
	    	session.setAttribute("listUserProfile", profileBO.list(form.getUserProfile().getContextId()));	// list all Profiles
		}else{
			session.setAttribute("listUserProfile", profileBO.list(form.getUserProfile().getContextId(), true)); 	// list only not superUser associated Profiles 
		}
	}
	else{
		redir=FORWARD_FORBIDDEN;
	}
	return actionMapping.findForward(redir);
    }

    public ActionForward delete(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys, Exception {

	UserProfileFacade userProfileFacade = (UserProfileFacade) request
		.getSession().getServletContext().getAttribute(
			"userProfileFacade");

	UserProfileForm form = (UserProfileForm) actionForm;
	// String userIdToDelete = request.getParameter("id");
	HttpSession session = request.getSession();
	String redir = FORWARD_LIST;

	if(checkCredentials(request, new String[]{Role.OPERATOR_ROLE,Role.GUEST_ROLE}, false)){
	    try {

		// UserProfile userProf=new UserProfile();
		// userProf.setId((String)form.get("id"));
		// Delete Devices
		DeviceDeniedBO deviceDeniedBo = new DeviceDeniedBO();

		UserProfileBO userProfileBO = new UserProfileBO();

		UserProfile userProfilex = (UserProfile) userProfileBO
			.findById(request.getParameter("id"));
		form.setUserProfile(userProfilex);

		if (null != form.getUserProfile().getId()) {
		    deviceDeniedBo.deleteDeviceDenied(form.getUserProfile()
			    .getId());
		    // Delete Profile
		    userProfileFacade.delete(form.getUserProfile().getId());

		    // Igor - 2007-10-01 - Inclusão de Log da aplicação.
		    this.saveLog(request, "user_profile.title.delete", "key = "
			    + form.getUserProfile().getId(),
			    CsmLogSeverity.LOW_SEVERITY.getValue());

		    showMessages(request, "default.delete.ok", MESSAGE_WARNING);
		    
		  //Karine - refresh tree
		    refresh(request, TYPE_PROFILE, form.getUserProfile().getContextId().substring(0, 10));
		}
		// deviceDeniedBo.delete(prfid)

	    } catch (Exception e) {

			// Igor - 2007-10-01 - Inclusão de Log da aplicação.
			this.saveLog(request, "user_profile.title.delete.error",
				"key = " + form.getUserProfile().getId() + " error = "
					+ e.getMessage(), CsmLogSeverity.FATAL_SEVERITY
					.getValue());
	
			showMessages(request, "default.delete.error", MESSAGE_ERROR);
			if(e.getCause().getCause() instanceof  ConstraintViolationException){
				showMessages(request, "error.profile.delete.Constraint", MESSAGE_ERROR);
			}

	    }
	    
	    //session.setAttribute("listUserProfile", userProfileFacade.list(form.getUserProfile().getContextId()));
	    UserProfileBO profileBO =  new UserProfileBO();
	    if(checkCredentials(request, new String[]{Role.MASTER_ROLE}, true)){
	    	session.setAttribute("listUserProfile", profileBO.list(form.getUserProfile().getContextId()));	// list all Profiles
		}else{
			session.setAttribute("listUserProfile", profileBO.list(form.getUserProfile().getContextId(), true)); 	// list only not superUser associated Profiles 
		}
	}
	else{
		redir=FORWARD_FORBIDDEN;
	}
	return actionMapping.findForward(redir);
    }

//FIXME The return logic of this code is really strange
    public ActionForward deleteDevice(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys {
    	String redir = FORWARD_LIST;
	try {
	    DeviceDeniedBO deviceDeniedBO = new DeviceDeniedBO();
	    deviceDeniedBO.delete(request.getParameter("prfid"));
	} catch (Exception e) {
	    redir = "falid";
	}
	// return actionMapping.findForward(redir);
	return edit(actionMapping, actionForm, request, response);
    }

}
