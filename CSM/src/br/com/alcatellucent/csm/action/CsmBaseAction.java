package br.com.alcatellucent.csm.action;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.logging.persistence.CSMLogging;
import br.com.alcatellucent.csm.logging.persistence.CsmLog;

/**
 * Classe responsável em efetuar operações comuns em toda hierarquia
 * <p>
 * de Actions.
 */
public class CsmBaseAction extends DispatchAction {

	public static final String MESSAGE_WARNING = "WARNING_MSG";

	public static final String MESSAGE_ERROR = "ERROR_MSG";

	public static final String FORWARD_ERROR = "error";

	public static final String FORWARD_FAILED = "failed";

	public static final String FORWARD_INITIAL = "initial";

	public static final String FORWARD_LIST = "list";

	public static final String FORWARD_EDIT = "edit";

	public static final String FORWARD_FORBIDDEN = "forbidden";

	public static final String FORWARD_SUCCESS = "success";

	protected final Logger logger = Logger.getLogger(this.getClass());

	public static final String TYPE_CONTEXT = "context";
	public static final String TYPE_DEVICE = "device";
	public static final String TYPE_DPPM = "dppm";
	public static final String TYPE_ACCESS_CONTROL = "accessControl";
	public static final String TYPE_USER = "user";
	public static final String TYPE_USERPROFILE = "userProfile";
	public static final String TYPE_PROFILE = "profile";
	public static final String TYPE_TRAFFIC = "traffic";
	public static final String TYPE_TRAFFICPOLICY = "trafficPolicy";
	public static final String TYPE_ACL = "acl";
	public static final String TYPE_ACLPLUS = "AclPlus";
	public static final String TYPE_ALDEPROBE = "aldeprobe";

	// public static final String TYPE_CONTEXT = "context";
	// public static final String TYPE_CONTEXT = "context";

	/**
	 * Operação que envia determinada mensagem à tela da aplicação.
	 * 
	 * @param request
	 * @param key -
	 *           mapeada no arquivo properties correspondente
	 */
	protected void showMessages(final HttpServletRequest request, final String key) {
		final ActionMessages messages = getMessages(request);
		messages.add("org.apache.struts.action.GLOBAL_MESSAGE", new ActionMessage(key));
		saveMessages(request, messages);
	}

	protected void showMessages(HttpServletRequest request, String key, String messageType) {

		if (messageType.equals(MESSAGE_WARNING)) {
			ActionMessages messages = getMessages(request);
			messages.add("org.apache.struts.action.GLOBAL_MESSAGE", new ActionMessage(key));
			saveMessages(request, messages);
		} else {
			ActionMessages errors = getMessages(request);
			errors.add("org.apache.struts.action.GLOBAL_MESSAGE", new ActionMessage(key));
			saveErrors(request, errors);
		}
	}

	protected void saveLog(HttpServletRequest request, String eventKey, String detailsKey, Integer severity)
			throws ExceptionSys, Exception {

		CsmLog csmLog = new CsmLog();
		ResourceBundle bundle;

		try {
			bundle = ResourceBundle.getBundle("CsmLog");
		} catch (Exception e) {
			throw e;
		}
		try {
			csmLog.setDetails(bundle.getString(eventKey));
		} catch (MissingResourceException me) {
			csmLog.setDetails(eventKey);
		}

		try {
			csmLog.setEvent(bundle.getString(eventKey));
		} catch (MissingResourceException me) {
			csmLog.setEvent(eventKey);
		}

		csmLog.setSeverity(severity);
		csmLog.setIpClient(request.getRemoteAddr());
		csmLog.setIpServer(request.getLocalAddr());

		if (request.getSession().getAttribute("userSession") != null) {
			csmLog.setUserId(((User) request.getSession().getAttribute("userSession")).getId());
			try {
				new CSMLogging(request, csmLog).putLog();
			} catch (ExceptionSys e) {
				throw e;
			}
		}

	}

	protected void refresh(HttpServletRequest request, Boolean value, String nodeType, String nodeId) {
		request.setAttribute("refresh", value);
		request.setAttribute("nodeType", nodeType);
		request.setAttribute("nodeId", nodeId);
	}

	protected void refresh(HttpServletRequest request, String nodeType, String nodeId) {
		refresh(request, true, nodeType, nodeId);
	}
	/**
	 * This Methods Checks the profile of user. This profile is set in the session<br>
	 * as the user login the system.
	 * @param request
	 * @param allowedProfiles - String[] with all the allowed profiles.
	 * @param initialLogic - if is true allow profiles in list. If is false not allow profiles in list.
	 * @return true if user is allowed, or false if is not allowed.
	 */
	protected  Boolean checkCredentials(HttpServletRequest request, String[] allowedProfiles, boolean initialLogic){
		Boolean allowed = !initialLogic;
		if(null == request.getSession().getAttribute("userProfile")){
			showMessages(request, "error.checkCredentials.profileNotFound", MESSAGE_ERROR);
		}else{
			UserProfile userProfile = (UserProfile)request.getSession().getAttribute("userProfile");
			for(String profile: allowedProfiles){
				if(initialLogic == true){
					if(userProfile.getUserRole().getName().equals(profile)) 
						allowed = true;
				}else{
					if(userProfile.getUserRole().getName().equals(profile))	
						allowed = false;
				}
			}
		}
		return allowed;
	}
}