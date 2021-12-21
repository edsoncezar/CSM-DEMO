package br.com.alcatellucent.csm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.alcatellucent.csm.beans.Acl;
import br.com.alcatellucent.csm.beans.Alde;
import br.com.alcatellucent.csm.beans.Context;
import br.com.alcatellucent.csm.beans.Device;
import br.com.alcatellucent.csm.beans.DeviceDenied;
import br.com.alcatellucent.csm.beans.ProcessorPacket;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.TrafficPolicy;
import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.bo.AldeBO;
import br.com.alcatellucent.csm.bo.DeviceDeniedBO;
import br.com.alcatellucent.csm.bo.UserBO;
import br.com.alcatellucent.csm.bo.UserProfileBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.util.hibernate.HibernateUtil;

/**
 * Servlet implementation class for Servlet: BuildTreeServlet
 */
public class BuildTreeServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2069932099882855943L;

	private static final Logger logger = Logger.getLogger(BuildTreeServlet.class);
	
	private int countFirstOpen = 0; // For open the first loaded Context
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public BuildTreeServlet() {
		super();
	}

	// The "private HttpServletRequest request" was removed to prevent
	// this code from concurrency problems

	private void doWork(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		countFirstOpen = 0;
		StringBuilder sb = new StringBuilder();
		User user = (User) request.getSession().getAttribute("userSession");
		Collection<Context> col = null;
		Context context = null;
		Session session = null;
		PrintWriter out = response.getWriter();
		String hql = null;
		sb.append("<?xml version=\"1.0\" encoding=\"iso-8859-1\" ?><tree id='0' >");

		try {
			hql = "from Context where id = '" + user.getContextId() + "' order by treeOrder";
			session = HibernateUtil.currentSession();
			Query query = session.createQuery(hql);
			col = query.list();
		} catch (Exception e) {
			logger.fatal("Could not retrieve the user context", e);
			throw new ServletException("Could not retrieve the user context", e);
		} finally {
			HibernateUtil.closeSession();
		}
		int countFirstOpen = 0;
		String opened = "";
		if (col != null) {
			Iterator<Context> iter = col.iterator();
			if (iter.hasNext()) {
				context = (Context) iter.next();
				if (context.getParentId().equalsIgnoreCase("0")) {
					sb.append("<item  text='ROOT' id='" + context.getId()
							+ "' open='1' im0='root.jpg' im1='root.jpg' im2='root.jpg' child='1'");
					sb.append(openItem(request, context.getId(), "context") + " >");
					sb.append("<userdata name='myurl'><![CDATA[commonActions.jsp?parentId=");
					sb.append(context.getId() + "&isroot=true]]></userdata>");
					assemblyChildTree(request, sb, context.getId());
					getPolicies(request, sb, context.getId());
					getDevices(request, sb, context.getId());
					getAlerts(sb, context.getId());
					getUsers(request, sb, context.getId());
					getSettings(sb, context.getId());
					// Incluido por Igor -Link Audit -2007-set-03
					getAudit(sb, context.getId());
				} else {
					assemblyTreeFromThisContext(request, sb, context);
				}
				if (context.getParentId().equals("0")) {
					sb.append("</item>");
				}
			}
		}
		sb.append("</tree> ");
		response.setContentType("text/xml");
		out.println(sb.toString());
		out.flush();
	}

	/**
	 * Método que retorna a String contendo os contextos
	 * 
	 * @param contextId
	 * @param treeDaughter
	 * @return
	 */
	private void assemblyChildTree(HttpServletRequest request, StringBuilder sb, String contextId) {
		int originalSize = sb.length();
		try {
			Session session = null;
			Collection<Context> col2 = null;
			Context cont = null;
			String hql = "from Context where parentId= '" + contextId + "' order by name";
			session = HibernateUtil.currentSession();
			Query query = session.createQuery(hql);
			col2 = query.list();
			
			Iterator<Context> iterator = col2.iterator();
			while (iterator.hasNext()) {
				cont = (Context) iterator.next();
				assemblyTreeFromThisContext(request, sb, cont);
			}
		} catch (Exception e) {
			sb.setLength(originalSize);
			logger.error("Error in assemblyChildTree for context " + contextId, e);
		} finally {
			HibernateUtil.closeSession();
		}
	}

	private void assemblyTreeFromThisContext(HttpServletRequest request, StringBuilder sb, Context cont) {
		// request #69 - Fernando   //////////////////////////////////////////////////////
		// open the first context level or a requested level /////////////////////////////
		String openItem = "";
		if(countFirstOpen == 0 ){
			openItem = " open='1' ";
			countFirstOpen ++;
		}
		// in case of a request demands open context and the context is not already opened 
		if(openItem.trim().length() == 0){
			openItem = openItem(request, cont.getId(), "context");
		}
		/////////////////////////////////////////////////////////////////////////////////
		sb.append("<item  text='");
		sb.append(cont.getName());
		sb.append("' id='");
		sb.append(cont.getId());
		sb.append("' im0='contexto.jpg' im1='contexto.jpg' im2='contexto.jpg' child='");
		sb.append(cont.getTreeOrder());
		// sb.append("'>");
		//sb.append("' " + openItem(request, cont.getId(), "context") + " >");
		sb.append("' " + openItem + " >");
		sb.append("<userdata name='myurl'><![CDATA[commonActions.jsp?parentId=");
		sb.append(cont.getId() + "]]></userdata>");
		getPolicies(request, sb, cont.getId());
		getDevices(request, sb, cont.getId());
		getUsers(request, sb, cont.getId());
		assemblyChildTree(request, sb, cont.getId());
		sb.append("</item>");
	}

	/**
	 * Método utilizado para retornar a String de usuários
	 * 
	 * @param userId
	 * @param treeUser
	 * @return
	 */
	private void getUsers(HttpServletRequest request, StringBuilder sb, String userId) {
		int originalSize = sb.length();
		try {
			Session session = null;
			Collection<User> col2 = null;
			User user = null;
//			String hql = "from User where context_id = '" + userId + "' order by name";
//			session = HibernateUtil.currentSession();
//			Query query = session.createQuery(hql);
//			col2 = query.list();
			
			CsmBaseAction csmBase = new CsmBaseAction();
			UserBO userBO =  new UserBO(); 
			if(csmBase.checkCredentials(request, new String[]{Role.MASTER_ROLE}, true)){
				col2 = userBO.findByContextId(userId);  			// list all Users
			}else{
				col2 = userBO.findByContextId(userId, true); 	// list only not superUser associated Profiles 	
			}
			
			
			
			Iterator<User> iterator = col2.iterator();
			
			sb.append("<item  text='ACCESS CONTROL' id='");
			sb.append(userId.substring(0, 10));
			sb.append("' im0='accesscontrol.bmp' im1='accesscontrol.bmp' im2='accesscontrol.bmp' child='5'");
			sb.append(openItem(request, userId.substring(0, 10), CsmBaseAction.TYPE_ACCESS_CONTROL) + ">");
			sb.append("<userdata name='myurl'><![CDATA[commonAccessControl.jsp?parentId=");
			sb.append(userId);
			sb.append("]]></userdata>");
			getProfile(request, sb, userId);

			while (iterator.hasNext()) {
				user = (User) iterator.next();
				sb.append("<item  text='");
				sb.append(user.getName());
				sb.append("' id='");
				// sb.append(user.getId().substring(0, 15));
				sb.append(user.getId());
				sb.append("' im0='Usuario_16.bmp' im1='Usuario_16.bmp' im2='Usuario_16.bmp' child='");
				sb.append(user.getUserProfileId());
				sb.append("' " + openItem(request, user.getId(), CsmBaseAction.TYPE_USER) + ">");
				sb.append("<userdata name='myurl'><![CDATA[commonUser.jsp?parentId=");
				sb.append(user.getId());
				sb.append("&userName=");
				sb.append(user.getUserName());
				sb.append("]]></userdata></item>");
			}
			sb.append("</item>");
		} catch (Exception e) {
			sb.setLength(originalSize);
			logger.error("Error in getUsers for user " + userId, e);
		}
	}

	/**
	 * Método utilizado para retornar a String de devices
	 * 
	 * @param contextId
	 * @param treeDevices
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private void getDevices(HttpServletRequest request, StringBuilder sb, String contextId) {
		int originalSize = sb.length();
		
		HttpSession httpSession = request.getSession();
		UserProfile userProfile = (UserProfile)httpSession.getAttribute("userProfile");
		DeviceDeniedBO deviceDeniedBO = new DeviceDeniedBO();
		Collection<DeviceDenied> listDeviceDenied = new ArrayList<DeviceDenied>();
		StringBuffer devDenied = new StringBuffer();
		String in = null;
		
		try {

			listDeviceDenied = deviceDeniedBO.findIdProfile(userProfile.getId());
			devDenied = new StringBuffer();
			devDenied.append("(");
			
			for (DeviceDenied d : listDeviceDenied) {
				devDenied.append("'" + d.getCsmdeviceId() + "',");
			}
			
			in = devDenied.toString();
			in = (in.substring(0,(in.length() - 1))) + ")";
			
		} catch (ExceptionSys e) {
			logger.error("Error in getDevices for context " + contextId, e);
		}
		
		try {
			Session session = null;
			Collection<Device> col2 = null;
			Device device = null;
			String hql = "from Device where contextId = '" + contextId + "'";
			hql +=  in.length()>2 ? " and id not in " + in : "";
			session = HibernateUtil.currentSession();
			Query query = session.createQuery(hql);
			col2 = query.list();

			Iterator<Device> iterator = col2.iterator();

			sb.append("<item  text='DEVICES' id='");
			sb.append(contextId.substring(0, 12));
			sb.append("' im0='ContextoProp_20.bmp' im1='ContextoProp_20.bmp' im2='ContextoProp_20.bmp' child='0' ");
			// don't change the substring method!!!
			sb.append(openItem(request, contextId.substring(0, 12), CsmBaseAction.TYPE_DEVICE) + ">");
			sb.append("<userdata name='myurl'><![CDATA[commonDeviceGroup.jsp?parentId=");
			sb.append(contextId + "]]></userdata>");

			getAlde(request, sb, contextId);

			while (iterator.hasNext()) {
				device = (Device) iterator.next();
				sb.append("<item  text='");
				sb.append(device.getName());
				sb.append("' id='");
				// sb.append(device.getId().substring(0, 12));
				sb.append(device.getId());
				sb.append("' im0='alde.bmp' im1='alde.bmp' im2='alde.bmp' child='");
				sb.append(device.getId());
				sb.append("'" + openItem(request, device.getId(), CsmBaseAction.TYPE_DEVICE) + ">");
				sb.append("<userdata name='myurl'><![CDATA[commonDevice.jsp?parentId=");
				sb.append(device.getContextId());
				sb.append("&deviceId=");
				sb.append(device.getId());
				sb.append("]]></userdata>");
				getProcessorPacket(request, sb, device.getId());
				sb.append("</item>");
			}
			sb.append("</item>");
		} catch (Exception e) {
			sb.setLength(originalSize);
			logger.error("Error in getDevices for context " + contextId, e);
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * Método utilizado para retornar a String de devices
	 * 
	 * @param deviceId
	 * @param treeDevices
	 * @return
	 */
	private void getProcessorPacket(HttpServletRequest request, StringBuilder sb, String deviceId) {
		int originalSize = sb.length();
		try {
			Session session = null;
			Collection<ProcessorPacket> col2 = null;
			ProcessorPacket dppm = null;
			String hql = "from ProcessorPacket where csmdevice_id = '" + deviceId + "'";
			session = HibernateUtil.currentSession();
			Query query = session.createQuery(hql);
			col2 = query.list();

			Iterator<ProcessorPacket> iterator = col2.iterator();

			while (iterator.hasNext()) {
				dppm = (ProcessorPacket) iterator.next();
				sb.append("<item  text='");
				sb.append(dppm.getName());
				sb.append("' id='");
				// sb.append(dppm.getId().substring(0, 12));
				sb.append(dppm.getId());
				sb.append("' im0='dppm.bmp' im1='dppm.bmp' im2='dppm.bmp' child='");
				sb.append(dppm.getId());
				sb.append("'" + openItem(request, dppm.getId(), CsmBaseAction.TYPE_DPPM) + ">");
				sb.append("<userdata name='myurl'><![CDATA[commonProcessorPacket.jsp?parentId=");
				sb.append(dppm.getId());
				sb.append("&deviceId=");
				// sb.append(dppm.getDeviceId());
				sb.append(dppm.getDevice().getId());
				sb.append("&name=");
				sb.append(dppm.getName());
				sb.append("]]></userdata></item>");
			}
		} catch (Exception e) {
			sb.setLength(originalSize);
			logger.error("Error in getProcessorPacket for device " + deviceId, e);
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * Método utilizado para retornar a String de ALDEs
	 * 
	 * @param contextId
	 * @param treeDevices
	 * @return
	 */
	private void getAlde(HttpServletRequest request, StringBuilder sb, String contextId) {

		
		//Igor 2007-10-26: FX#120
		HttpSession httpSession = request.getSession();
		UserProfile userProfile = (UserProfile)httpSession.getAttribute("userProfile");
		
		int originalSize = sb.length();
		Alde alde = null;
		Collection<Alde> aldeProbeList = null;
		Iterator<Alde> iter = null;
		AldeBO aldeBO = new AldeBO();

		try {
			aldeProbeList = aldeBO.list(contextId, false);
			iter = aldeProbeList.iterator();
			while (iter.hasNext()) {
				alde = (Alde) iter.next();
				sb.append("<item  text='");
				sb.append(alde.getName());
				sb.append("' id='");
				sb.append(alde.getId().substring(0, 12));
				sb.append("' im0='aldeprobe.jpg' im1='aldeprobe.jpg' im2='aldeprobe.jpg' child='");
				sb.append(alde.getId());
				sb.append("' " + openItem(request, alde.getId(), CsmBaseAction.TYPE_ALDEPROBE) + ">");
				sb.append("<userdata name='myurl'><![CDATA[commonAlde.jsp?action=edit&id=");
				sb.append(alde.getId());
				sb.append("]]></userdata></item>");
			}
		} catch (Exception e) {
			sb.setLength(originalSize);
			logger.error("Error in getAlde for device " + contextId, e);
		}
	}

	/**
	 * Método utilizado para retornar a String de Ports e Protocols
	 * 
	 * @param contextId
	 * @param treeSettings
	 * @return
	 */
	private void getSettings(StringBuilder sb, String contextId) {
		int originalSize = sb.length();
		try {

			sb
					.append("<item  text='SETTINGS' id='SETTINGS' im0='settings.bmp' im1='settings.bmp' im2='settings.bmp' child='0'>");
			sb.append("<userdata name='myurl'><![CDATA[commonSettings.jsp?parentId=");
			sb.append(contextId);
			sb.append("]]></userdata>");

			sb
					.append("<item  text='GROUPS' id='GROUPS' im0='ServidorProp_20.bmp' im1='ServidorProp_20.bmp' im2='ServidorProp_20.bmp' child='1'>");
			sb.append("<userdata name='myurl'><![CDATA[commonPortProtocolGroup.jsp?parentId=");
			sb.append(contextId);
			sb.append("]]></userdata></item>");

			sb
					.append("<item  text='PROTOCOLS' id='PROTOCOLS' im0='ServidorProp_20.bmp' im1='ServidorProp_20.bmp' im2='ServidorProp_20.bmp' child='3'>");
			sb.append("<userdata name='myurl'><![CDATA[commonProtocols.jsp?parentId=");
			sb.append(contextId);
			sb.append("]]></userdata></item>");

			sb
					.append("<item  text='INMON' id='INMON' im0='ServidorProp_20.bmp' im1='ServidorProp_20.bmp' im2='ServidorProp_20.bmp' child='2'>");
			sb.append("<userdata name='myurl'><![CDATA[inmon.do?action=initial");
			sb.append("]]></userdata></item>");
			sb
			.append("<item  text='PASSWORD POLICES' id='PASSWORD POLICES' im0='ServidorProp_20.bmp' im1='ServidorProp_20.bmp' im2='ServidorProp_20.bmp' child='4'>");
				sb.append("<userdata name='myurl'><![CDATA[commonPassword.jsp?]]></userdata></item></item>");
		} catch (Exception e) {
			sb.setLength(originalSize);
			logger.error("Error in getSettings for context " + contextId, e);
		}
	}

	/**
	 * Método utilizado para retornar a String de Alerts
	 * 
	 * @param contextId
	 * @param treeDevices
	 * @return
	 */
	private void getAlerts(StringBuilder sb, String contextId) {
		int originalSize = sb.length();
		try {
			sb.append("<item  text='ALERTS' id='");
			sb.append(contextId.substring(0, 8));
			sb.append("' im0='alerts.bmp' im1='alerts.bmp' im2='alerts.bmp' child='1'>");
			sb.append("<userdata name='myurl'><![CDATA[commonAldeMaster.jsp?parentId=");
			sb.append(contextId);
			sb.append("&acl.isAldeAcl=true&acl.status=1]]></userdata>");
			getAldeMasterList(sb, contextId);

			sb.append("<item  text='ALDE ACL' id='");
			sb.append(contextId.substring(0, 11));
			sb.append("' im0='alerts.bmp' im1='alerts.bmp' im2='alerts.bmp' child='4'>");
			sb.append("<userdata name='myurl'><![CDATA[acl.do?action=list&ini=yes&acl.isAldeAcl=alert&acl.contextId=");
			sb.append(contextId);
			sb.append("]]></userdata></item>");

			sb.append("<item  text='OPERATORS' id='");
			sb.append(contextId.substring(0, 7));
			sb.append("' im0='operator.bmp' im1='operator.bmp' im2='operator.bmp' child='5'>");
			sb.append("<userdata name='myurl'><![CDATA[commonOperator.jsp?parentId=");
			sb.append(contextId);
			sb.append("&deviceId=");
			sb.append(contextId);
			sb.append("]]></userdata></item></item>");
		} catch (Exception e) {
			sb.setLength(originalSize);
			logger.error("Error in getAlerts for context " + contextId, e);
		}
	}

	private void getAldeMasterList(StringBuilder sb, String contextId) {
		int originalSize = sb.length();
		Alde alde = null;
		Collection<Alde> aldeMasterList = null;
		Iterator<Alde> iter = null;
		AldeBO aldeBO = new AldeBO();
		try {
			aldeMasterList = aldeBO.list(contextId, true);
			if (aldeMasterList.size() == 0) { // There is no Master, yet
				sb.append("<item  text='ALDE MASTER' id='");
				sb.append(contextId.substring(0, 6));
				sb.append("' im0='aldemaster.jpg' im1='aldemaster.jpg' im2='aldemaster.jpg' child='5'>");
				sb.append("<userdata name='myurl'><![CDATA[aldeMaster.do?action=edit&alde.contextId=");
				sb.append(contextId);
				sb.append("&alde.master=true");
				sb.append("]]></userdata></item>");
			}
			iter = aldeMasterList.iterator();
			while (iter.hasNext()) {
				alde = iter.next();
				sb.append("<item  text='ALDE MASTER' id='");
				sb.append(contextId.substring(0, 6));
				sb.append("' im0='aldemaster.jpg' im1='aldemaster.jpg' im2='aldemaster.jpg' child='5'>");
				sb.append("<userdata name='myurl'><![CDATA[aldeMaster.do?action=edit&alde.contextId=");
				sb.append(contextId);
				sb.append("&alde.id=");
				sb.append(alde.getId());
				sb.append("]]></userdata></item>");
			}
		} catch (Exception e) {
			sb.setLength(originalSize);
			logger.error("Error in getAldeMasterList for context " + contextId, e);
		}
	}

	/**
	 * Método utilizado para retornar a String de Alerts
	 * 
	 * @param contextId
	 * @param treeDevices
	 * @return
	 */
	private void getPolicies(HttpServletRequest request, StringBuilder sb, String contextId) {
		int originalSize = sb.length();
		try {
			sb.append("<item  text='POLICIES' id='");
			sb.append(contextId.substring(0, 9));
			sb.append("' im0='policies.bmp' im1='policies.bmp' im2='policies.bmp' child='1'>");
			sb.append("<userdata name='myurl'><![CDATA[commonTrafficPolicies.jsp?parentId=");
			sb.append(contextId);
			sb.append("&deviceId=");
			sb.append(contextId);
			sb.append("]]></userdata>");
			getTrafficPolicy(request, sb, contextId);
			getAclPlus(request, sb, contextId);
			getStaticDomainController(sb, contextId);
			getStaticIP(sb, contextId);
			sb.append("</item>");
		} catch (Exception e) {
			sb.setLength(originalSize);
			logger.error("Error in getPolices for context " + contextId, e);
		}
	}

	/**
	 * Método utilizado para retornar a String de Profile
	 * 
	 * @param profileId
	 * @param treeDevices
	 * @return
	 */
	private void getProfile(HttpServletRequest request, StringBuilder sb, String profileId) {
		int originalSize = sb.length();
		
		try {
			Session session = null;
			Collection<UserProfile> col2 = null;
			UserProfile userProfile = null;
//			String hql = "from UserProfile where contextId = '" + profileId + "'";
//			session = HibernateUtil.currentSession();
//			Query query = session.createQuery(hql);
			CsmBaseAction csmBase = new CsmBaseAction();
			UserProfileBO profileBO =  new UserProfileBO(); 
			if(csmBase.checkCredentials(request, new String[]{Role.MASTER_ROLE}, true)){
				col2 = profileBO.list(profileId);  			// list all Profiles
			}else{
				col2 = profileBO.list(profileId, true); 	// list only not superUser associated Profiles 	
			}
			
			//col2 = query.list();

			Iterator<UserProfile> iterator = col2.iterator();
			sb.append("<item  text='PROFILES' id='");
			sb.append(profileId.substring(0, 27));
			sb.append("' im0='profile.bmp' im1='profile.bmp' im2='profile.bmp' child='3'");
			sb.append(openItem(request, profileId.substring(0, 10), CsmBaseAction.TYPE_PROFILE) + ">");
			sb.append("<userdata name='myurl'><![CDATA[commonProfile.jsp?parentId=");
			sb.append(profileId);
			sb.append("]]></userdata>");
			while (iterator.hasNext()) {
				userProfile = (UserProfile) iterator.next();
				sb.append("<item  text='");
				sb.append(userProfile.getName());
				sb.append("' id='");
				sb.append(userProfile.getId());
				sb.append("' im0='profile.bmp' im1='profile.bmp' im2='profile.bmp' child='10'");
				sb.append(openItem(request, userProfile.getId(), CsmBaseAction.TYPE_USERPROFILE) + ">");
				// sb.append("<userdata
				// name='myurl'><![CDATA[commonProfile.jsp?parentId=");
				sb.append("<userdata name='myurl'><![CDATA[commonSingleProfile.jsp?id=");
				sb.append(userProfile.getId());
				sb.append("&name=");
				sb.append(userProfile.getName());
				sb.append("]]></userdata></item>");
			}
			sb.append("</item>");
		} catch (Exception e) {
			sb.setLength(originalSize);
			logger.error("Error in getProfile for profile " + profileId, e);
		}
	}

	/**
	 * Método utilizado para retornar a String de Traffic Policy
	 * 
	 * @param contextId
	 * @param treeDevices
	 * @return
	 */
	private void getTrafficPolicy(HttpServletRequest request, StringBuilder sb, String contextId) {
		int originalSize = sb.length();
		try {

			Session session = null;
			Collection<TrafficPolicy> col2 = null;
			TrafficPolicy policy = null;
			String hql = "from TrafficPolicy where contextid = '" + contextId + "'";
			session = HibernateUtil.currentSession();
			Query query = session.createQuery(hql);
			col2 = query.list();

			Iterator<TrafficPolicy> iterator = col2.iterator();

			sb.append("<item  text='TRAFFIC' id='");
			sb.append(contextId.substring(0, 3));
			sb.append("' im0='trafficanalisys.bmp' im1='trafficanalisys.bmp' im2='trafficanalisys.bmp' child='2'");
			sb.append(openItem(request, contextId.substring(0, 3), CsmBaseAction.TYPE_TRAFFIC) + ">");
			sb.append("<userdata name='myurl'><![CDATA[commonTraffic.jsp?parentId=");
			sb.append(contextId);
			sb.append("&deviceId=");
			sb.append(contextId);
			sb.append("]]></userdata>");

			while (iterator.hasNext()) {
				policy = (TrafficPolicy) iterator.next();
				sb.append("<item  text='");
				sb.append(policy.getName());
				sb.append("' id='");
				sb.append(policy.getId().substring(0, 12));
				sb.append("' im0='alde.bmp' im1='alde.bmp' im2='alde.bmp' child='");
				sb.append(policy.getId());
				sb.append("' " + openItem(request, policy.getId(), CsmBaseAction.TYPE_TRAFFICPOLICY) + ">");
				sb.append("<userdata name='myurl'><![CDATA[commonTrafficPolicy.jsp?parentId=");
				sb.append(policy.getId());
				sb.append("&contextId=");
				sb.append(contextId);
				sb.append("]]></userdata></item>");
			}
			sb.append("</item>");

		} catch (Exception e) {
			sb.setLength(originalSize);
			logger.error("Error in getTrafficPolicy for context " + contextId, e);
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * Método utilizado para retornar a String de ACL+
	 * 
	 * @param contextId
	 * @param treeDevices
	 * @return
	 */
	private void getAclPlus(HttpServletRequest request, StringBuilder sb, String contextId) {
		int originalSize = sb.length();
		try {
			Session session = null;
			Collection<Acl> col2 = null;
			Acl acl = null;
			String hql = "from Acl where contextid = '" + contextId + "' and alert_id is null order by priority";
			session = HibernateUtil.currentSession();
			Query query = session.createQuery(hql);
			col2 = query.list();

			Iterator<Acl> iterator = col2.iterator();

			sb.append("<item  text='ACL+' id='");
			sb.append(contextId.substring(0, 4));
			sb.append("' im0='acl.bmp' im1='acl.bmp' im2='acl.bmp' child='3'");
			sb.append(openItem(request, contextId.substring(0, 4), CsmBaseAction.TYPE_ACL) + ">");
			sb.append("<userdata name='myurl'><![CDATA[commonAcl.jsp?parentId=");
			sb.append(contextId);
			sb.append("&deviceId=");
			sb.append(contextId);
			sb.append("]]></userdata>");

			while (iterator.hasNext()) {
				acl = (Acl) iterator.next();
				sb.append("<item  text='");
				sb.append(acl.getName());
				sb.append("' id='");
				sb.append(acl.getId().substring(0, 12));
				sb.append("' im0='alde.bmp' im1='alde.bmp' im2='alde.bmp' child='");
				sb.append(acl.getId());
				sb.append("' " + openItem(request, acl.getId(), CsmBaseAction.TYPE_ACLPLUS) + ">");
				sb.append("<userdata name='myurl'><![CDATA[commonAclPlus.jsp?parentId=");
				sb.append(acl.getId());
				sb.append("]]></userdata></item>");
			}
			sb.append("</item>");

		} catch (Exception e) {
			sb.setLength(originalSize);
			logger.error("Error in getAclPlus for context " + contextId, e);
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * Método utilizado para retornar link Auditing
	 * 
	 * @param deviceId
	 * @param treeDevices
	 * @return Incluido por Igor 2007-09-03
	 */

	private void getAudit(StringBuilder sb, String contextId) {

		sb.append("<item  text='AUDITING' id='");
		sb.append(contextId.substring(0, 5));
		sb.append("' im0='acl.bmp' im1='acl.bmp' im2='acl.bmp' child='3'>");
		sb.append("<userdata name='myurl'><![CDATA[commonAuditing.jsp?parentId=");
		sb.append(contextId);
		sb.append("&deviceId=");
		sb.append(contextId);
		sb.append("]]></userdata>");

		sb.append("<item  text='LOG' id='");
		sb.append(contextId.substring(0, 18));
		sb.append("' im0='history.bmp' im1='history.bmp' im2='history.bmp' child='4'>");
		sb.append("<userdata name='myurl'><![CDATA[CsmlogSearch.jsp?parentId=");
		sb.append(contextId);
		sb.append("&deviceId=");
		sb.append(contextId);
		sb.append("]]></userdata>");
		sb.append("</item>");

		sb.append("<item  text='TRAF.POLICY HISTORY' id='");
		sb.append(contextId.substring(0, 17));
		sb.append("' im0='history.bmp' im1='history.bmp' im2='history.bmp' child='4'>");
		sb.append("<userdata name='myurl'><![CDATA[trafficPolicyHistory.jsp?parentId=");
		sb.append(contextId);
		sb.append("&deviceId=");
		sb.append(contextId);
		sb.append("]]></userdata>");
		sb.append("</item>");

		sb.append("<item  text='ACL HISTORY' id='");
		sb.append(contextId.substring(0, 19));
		sb.append("' im0='history.bmp' im1='history.bmp' im2='history.bmp' child='4'>");
		sb.append("<userdata name='myurl'><![CDATA[AclHistory.do?action=initial&parentId=");
		sb.append(contextId);
		sb.append("&deviceId=");
		sb.append(contextId);
		sb.append("]]></userdata>");
		sb.append("</item>");

		sb.append("<item  text='AAA' id='aaa");
		sb.append(contextId.substring(0, 6));
		sb.append("' im0='history.bmp' im1='history.bmp' im2='history.bmp' child='4'>");
//		sb.append("<userdata name='myurl'><![CDATA[Accounting.do?action=list");
//		sb.append("]]></userdata></item>");
		sb.append("<userdata name='myurl'><![CDATA[#");
		sb.append("]]></userdata></item>");

		sb.append("</item>");

		/*
		 * int originalSize = sb.length(); try { Session session = null;
		 * Collection<Acl> col2 = null; Acl acl = null; String hql = "from Acl
		 * where contextid = '" + contextId + "' and alert_id is not null";
		 * session = HibernateUtil.currentSession(); Query query =
		 * session.createQuery(hql); col2 = query.list();
		 * 
		 * Iterator<Acl> iterator = col2.iterator();
		 * 
		 * sb.append("<item text='ACL HISTORY' id='");
		 * sb.append(contextId.substring(0, 19)); sb.append("' im0='history.bmp'
		 * im1='history.bmp' im2='history.bmp' child='4'>"); sb.append("<userdata
		 * name='myurl'><![CDATA[AclHistory.jsp??action=initial&parentId=");
		 * sb.append(contextId); sb.append("&deviceId="); sb.append(contextId);
		 * sb.append("]]></userdata>"); sb.append("</item>");
		 * 
		 * while (iterator.hasNext()) { acl = (Acl) iterator.next(); sb.append("<item
		 * text='"); sb.append(acl.getName()); sb.append("' id='");
		 * sb.append(acl.getId().substring(0, 12)); sb.append("' im0='alde.bmp'
		 * im1='alde.bmp' im2='alde.bmp' child='"); sb.append(acl.getId());
		 * sb.append("'>"); sb.append("<userdata name='myurl'><![CDATA[CsmlogSearch.jsp?parentId=");
		 * sb.append(acl.getId()); sb.append("]]></userdata></item>"); }
		 * sb.append("</item>"); } catch (Exception e) {
		 * sb.setLength(originalSize); logger.error("Error in getAudit for context " +
		 * contextId, e); }
		 */
	}

	protected String openItem(HttpServletRequest request, String ActualNodeId, String type) {
		String retorno = "";
		if (null != request.getParameter("refresh") && null != request.getParameter("nodeType")
				&& null != request.getParameter("nodeId")) {

			Boolean refresh = Boolean.parseBoolean(request.getParameter("refresh"));
			String selectedType = (String) request.getParameter("nodeType");
			String selectedNodeId = (String) request.getParameter("nodeId");

			if (type.equalsIgnoreCase(selectedType) && refresh == true && selectedNodeId.equals(ActualNodeId)) {

				retorno = " open='1' ";
			}

		}
		return retorno;
	}
	
	
	private void getStaticDomainController(StringBuilder sb, String contextId) {

		sb.append("<item  text='URL Control' id='");
		sb.append(contextId.substring(0, 6)+"domain");
		sb.append("' im0='acl.bmp' im1='acl.bmp' im2='acl.bmp' child='3'>");
		sb.append("<userdata name='myurl'><![CDATA[commonDomain.jsp?parentId=");
		sb.append(contextId);
		sb.append("]]></userdata>");
		sb.append("</item>");
	}
	
	
	private void getStaticIP(StringBuilder sb, String contextId) {

		sb.append("<item  text='Static IP' id='");
		sb.append(contextId.substring(0, 6)+"staticIP");
		sb.append("' im0='acl.bmp' im1='acl.bmp' im2='acl.bmp' child='4'>");
		sb.append("<userdata name='myurl'><![CDATA[commonStaticIP.jsp?parentId=");
		sb.append(contextId);
		sb.append("]]></userdata>");
		sb.append("</item>");
	}
	
	
	
	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doWork(request, response);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doWork(request, response);
	}

}