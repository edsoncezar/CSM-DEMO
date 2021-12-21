package br.com.alcatellucent.csm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Device;
import br.com.alcatellucent.csm.beans.ProcessorPacket;
import br.com.alcatellucent.csm.beans.TrafficPolicy;
import br.com.alcatellucent.csm.beans.TrafficPolicyHistory;
import br.com.alcatellucent.csm.beans.User;
import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.bo.AlertBO;
import br.com.alcatellucent.csm.bo.DeviceBO;
import br.com.alcatellucent.csm.bo.DeviceManagerBO;
import br.com.alcatellucent.csm.bo.PoolingHistoryBO;
import br.com.alcatellucent.csm.bo.ProcessorPacketBO;
import br.com.alcatellucent.csm.bo.TrafficPolicyBO;
import br.com.alcatellucent.csm.bo.TrafficPolicyHistoryBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;

/**
 * @author Fernando Caruso Olívio
 * 
 */
public class DashBoardAction extends CsmBaseAction {

    public ActionForward initial(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys {

//	User user = (User) request.getSession().getAttribute("userSession");
//
//	DeviceBO deviceBO = new DeviceBO();
//
//	List<Device> devices = deviceBO.getListDeviceByHierarchy(user
//		.getContextId());
//	ProcessorPacketBO pPacketBO = new ProcessorPacketBO();
//	DeviceManagerBO dManagerBO = new DeviceManagerBO();
//
//	TrafficPolicyHistoryBO tHistoryBO = new TrafficPolicyHistoryBO();
//	TrafficPolicyBO tPolicyBO = new TrafficPolicyBO();
//	Collection<TrafficPolicyHistory> collTrafficHistory = new HashSet<TrafficPolicyHistory>();
//	Collection<TrafficPolicy> collTrafficPolicy = new HashSet<TrafficPolicy>();
//	AlertBO alertBO = new AlertBO();
//
//	Collection<ProcessorPacket> collProcessorPacket;
//
//	for (Device device : devices) {
//	    collProcessorPacket = pPacketBO.findByDeviceId(device.getId());
//
//	    for (ProcessorPacket pPacket : collProcessorPacket) {
//		if (null != pPacket.getTrafficPolicyId()
//			&& !pPacket.getTrafficPolicyId().equals("")) {
//		    collTrafficPolicy.add(tPolicyBO.findById(pPacket
//			    .getTrafficPolicyId()));
//		    collTrafficHistory.add(tHistoryBO.actualOnHistory(pPacket
//			    .getId(), pPacket.getTrafficPolicyId()));
//		}
//	    }
//
//	    device.setProcessorPacketList(collProcessorPacket);
//	    device.setDeviceManager(dManagerBO.findByDeviceId(device.getId()));
//
//	}
//
//	request.setAttribute("DEVICE_LIST", devices);
//	request.setAttribute("TRAFFIC_HISTORY_LIST", collTrafficHistory);
//	request.setAttribute("TRAFFIC_POLICY_LIST", collTrafficPolicy);
//	request.setAttribute("listTop10AldeAlerts", alertBO.getActiveAlerts(new Date(), 0));

	return actionMapping.findForward(FORWARD_INITIAL);
    }
    
    public ActionForward initial2(ActionMapping actionMapping,
    	    ActionForm actionForm, HttpServletRequest request,
    	    HttpServletResponse response) throws ExceptionSys {

    	User user = (User) request.getSession().getAttribute("userSession");

    	DeviceBO deviceBO = new DeviceBO();

    	List<Device> devices = deviceBO.getListDeviceByHierarchy(user
    		.getContextId());
    	ProcessorPacketBO pPacketBO = new ProcessorPacketBO();
    	DeviceManagerBO dManagerBO = new DeviceManagerBO();

    	TrafficPolicyHistoryBO tHistoryBO = new TrafficPolicyHistoryBO();
    	TrafficPolicyBO tPolicyBO = new TrafficPolicyBO();
    	Collection<TrafficPolicyHistory> collTrafficHistory = new HashSet<TrafficPolicyHistory>();
    	Collection<TrafficPolicy> collTrafficPolicy = new HashSet<TrafficPolicy>();
    	AlertBO alertBO = new AlertBO();

    	Collection<ProcessorPacket> collProcessorPacket;

    	for (Device device : devices) {
    	    collProcessorPacket = pPacketBO.findByDeviceId(device.getId());

    	    for (ProcessorPacket pPacket : collProcessorPacket) {
    		if (null != pPacket.getTrafficPolicyId()
    			&& !pPacket.getTrafficPolicyId().equals("")) {
    		    collTrafficPolicy.add(tPolicyBO.findById(pPacket
    			    .getTrafficPolicyId()));
    		    collTrafficHistory.add(tHistoryBO.actualOnHistory(pPacket
    			    .getId(), pPacket.getTrafficPolicyId()));
    		}
    	    }

    	    device.setProcessorPacketList(collProcessorPacket);
    	    device.setDeviceManager(dManagerBO.findByDeviceId(device.getId()));

    	}

    	request.setAttribute("DEVICE_LIST", devices);
    	return actionMapping.findForward("initial2");
        }
    
    
    public ActionForward list(ActionMapping actionMapping,
    	    ActionForm actionForm, HttpServletRequest request,
    	    HttpServletResponse response) throws ExceptionSys {
    	
    	DeviceBO deviceBO = new DeviceBO();
    	Device deviceTraffic = deviceBO.edit(request.getParameter("traffic"));
    	List<Device> devices = new ArrayList<Device>();
    	devices.add(deviceTraffic);
    	
    	ProcessorPacketBO pPacketBO = new ProcessorPacketBO();
    	DeviceManagerBO dManagerBO = new DeviceManagerBO();

    	TrafficPolicyHistoryBO tHistoryBO = new TrafficPolicyHistoryBO();
    	TrafficPolicyBO tPolicyBO = new TrafficPolicyBO();
    	Collection<TrafficPolicyHistory> collTrafficHistory = new HashSet<TrafficPolicyHistory>();
    	Collection<TrafficPolicy> collTrafficPolicy = new HashSet<TrafficPolicy>();
    	AlertBO alertBO = new AlertBO();

    	Collection<ProcessorPacket> collProcessorPacket;

    	for (Device device : devices) {
    	    collProcessorPacket = pPacketBO.findByDeviceId(device.getId());

    	    for (ProcessorPacket pPacket : collProcessorPacket) {
    		if (null != pPacket.getTrafficPolicyId()
    			&& !pPacket.getTrafficPolicyId().equals("")) {
    		    collTrafficPolicy.add(tPolicyBO.findById(pPacket
    			    .getTrafficPolicyId()));
    		    collTrafficHistory.add(tHistoryBO.actualOnHistory(pPacket
    			    .getId(), pPacket.getTrafficPolicyId()));
    		}
    	    }

    	    device.setProcessorPacketList(collProcessorPacket);
    	    device.setDeviceManager(dManagerBO.findByDeviceId(device.getId()));

    	}

    	request.setAttribute("DEVICE_LIST", devices);
    	request.setAttribute("TRAFFIC_HISTORY_LIST", collTrafficHistory);
    	request.setAttribute("TRAFFIC_POLICY_LIST", collTrafficPolicy);
    	request.setAttribute("listTop10AldeAlerts", alertBO.getActiveAlerts(
    		new Date(), 0));

    	return actionMapping.findForward(FORWARD_LIST);
        }
    
    /**
     * This methods return the last seven days devices availability 
     * @param request - deviceId
     * @param response
     * @throws ExceptionSys 
     */
    public void lastWeekvailability(ActionMapping actionMapping, ActionForm actionForm,
    	    HttpServletRequest request, HttpServletResponse response)
    throws IOException {
    	
			try {
				
				PrintWriter out =  response.getWriter();
				
				PoolingHistoryBO poolingHistoryBO = new PoolingHistoryBO();
				UserProfile userProfile =  (UserProfile)request.getSession().getAttribute("userProfile");
				Double[] percent = poolingHistoryBO.getAvailabilityReportByUser(userProfile, -7);
				out.println("<chart palette=\"1\" 	caption='Last Week Availability'" +
							"						decimals='2' " +
							"						animation='1' "+
							" 					  	enableSmartLabels='1' " +
							"						enableRotation='1' " +
							"						showBorder='0' " +
							"						startingAngle=\"70\">");
				out.println("<set color='33CCFF' label='Fail' value=\""+percent[0]*100+"\"/>");			
				out.println("<set color='33CC00' label='Running' value=\""+percent[1]*100+"\"/>");
				out.println("</chart>");
					
			} catch (ExceptionSys e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
    /**
     * This methods return the last seven days devices availability 
     * @param request - deviceId
     * @param response
     * @throws ExceptionSys 
     */
    public void lastMonthAvailability(ActionMapping actionMapping, ActionForm actionForm,
    	    HttpServletRequest request, HttpServletResponse response)
    throws IOException {
    	
			try {
				
				PrintWriter out =  response.getWriter();
				
				PoolingHistoryBO poolingHistoryBO = new PoolingHistoryBO();
				UserProfile userProfile =  (UserProfile)request.getSession().getAttribute("userProfile");
				Double[] percent = poolingHistoryBO.getAvailabilityReportByUser(userProfile, -30);
				out.println("<chart palette=\"1\" 	caption='Last Month Availability'" +
							"						decimals='2' " +
							"						animation='1' "+
							" 					  	enableSmartLabels='1' " +
							"						enableRotation='1' " +
							"						showBorder='0' " +
							"						startingAngle=\"70\">");
				out.println("<set color='33CCFF' label='Fail' value=\""+percent[0]*100+"\"/>");			
				out.println("<set color='33CC00' label='Running' value=\""+percent[1]*100+"\"/>");
				out.println("</chart>");
					
			} catch (ExceptionSys e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
}