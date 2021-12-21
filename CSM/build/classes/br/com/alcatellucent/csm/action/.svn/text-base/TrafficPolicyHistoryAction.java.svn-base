package br.com.alcatellucent.csm.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Context;
import br.com.alcatellucent.csm.beans.Device;
import br.com.alcatellucent.csm.beans.ProcessorPacket;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.TrafficPolicyHistory;
import br.com.alcatellucent.csm.beans.TrafficPolicyHistoryObj;
import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.bo.ContextBO;
import br.com.alcatellucent.csm.bo.ProcessorPacketBO;
import br.com.alcatellucent.csm.bo.TrafficPolicyBO;
import br.com.alcatellucent.csm.bo.TrafficPolicyHistoryBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.TrafficPolicyHistoryForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

public final class TrafficPolicyHistoryAction extends CsmBaseAction {

	public ActionForward initial(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {

		String redir = "";

		UserProfile userProfile = (UserProfile) request.getSession()
				.getAttribute("userProfile");

		if (checkCredentials(request, new String[] { Role.OPERATOR_ROLE,
				Role.GUEST_ROLE }, false)) {
			redir = FORWARD_INITIAL;
		} else {
			redir = FORWARD_FORBIDDEN;
		}

		return actionMapping.findForward(redir);

	}

	public ActionForward list(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) {

		
	@SuppressWarnings("unused")
	String qString = request.getQueryString();	
		
		
	String retorno = FORWARD_LIST;
	if (checkCredentials(request, new String[] { Role.OPERATOR_ROLE,Role.GUEST_ROLE }, false)) {
			HttpSession session = request.getSession();
			Collection<TrafficPolicyHistory> listTrafficPoliceHistory;
			TrafficPolicyHistoryBO trafficPoliceHistoryBO = new TrafficPolicyHistoryBO();

			String string = this.buildSearchCriteria(actionForm);

			listTrafficPoliceHistory = new ArrayList<TrafficPolicyHistory>();

			try {

				if (string.length() == 0) {
					listTrafficPoliceHistory = trafficPoliceHistoryBO.list();
				} else {
					listTrafficPoliceHistory = trafficPoliceHistoryBO
							.findByQuery(string);
				}
				session.setAttribute("LIST_TRAFFIC_HISTORY", this
						.buildHistoryCollection(listTrafficPoliceHistory));

			} catch (ExceptionSys ex) {
				ex.printStackTrace();
				retorno = "falid";
			}
		}else{
			retorno=FORWARD_FORBIDDEN;
		}
	return actionMapping.findForward(retorno);
    }

	@SuppressWarnings("deprecation")
	private String buildSearchCriteria(ActionForm actionForm) {

		TrafficPolicyHistoryForm form = (TrafficPolicyHistoryForm) actionForm;

		Date dateFrom = null;

		StringBuffer hql = new StringBuffer();

		// - Verifica digitacao da data inicial
		// -------------------------------------------------------------------------
		if ((form.getDateFrom() != null)
				&& (form.getDateFrom().trim().length() > 0)) {
			dateFrom = BasicValueCheck.getSQLDate(BasicValueCheck
					.stringToStringDate(form.getDateFrom()));
			hql.append("date_time >= TIMESTAMP('" + dateFrom.toString()
					+ " 00:00:00')");
		}

		// - Verifica seleção do Mode
		// -------------------------------------------------------------------------
		if (form.getMode() != null) {
			if (hql.length() > 0) {
				hql.append(" and ");
			}

			hql.append("mode = " + form.getMode().toString());
		}
		// - Verifica seleção do Status
		// -------------------------------------------------------------------------
		if (form.getStatusApplied() != null) {
			if (hql.length() > 0) {
				hql.append(" and ");
			}
			hql.append("status_applied = " + form.getStatusApplied());
		}

		String string = "";

		if (hql.length() > 0) {
			string = hql.toString();
			string = "from TrafficPolicyHistory where " + string;
		}
		
		switch (Integer.parseInt((String)form.getSort())) {
		case 2:
			string = string + " order by date";
			break;

		default:
			string = string + " order by date desc";
			break;
		}
		
		
		

		System.out.println(string);

		return string;
	}

	private Collection<TrafficPolicyHistoryObj> buildHistoryCollection(
			Collection<TrafficPolicyHistory> oldListTrafficPoliceHistory)
			throws ExceptionSys {

		Collection<TrafficPolicyHistoryObj> newListTrafficPoliceHistory = new ArrayList<TrafficPolicyHistoryObj>();
		ContextBO contextBO = new ContextBO();
		TrafficPolicyBO trafficPolicyBO = new TrafficPolicyBO();
		// DeviceBO deviceBO = new DeviceBO();
		Device device = null;
		ProcessorPacketBO processorPacketBO = new ProcessorPacketBO();
		ProcessorPacket dppm = null;
		TrafficPolicyHistoryObj trafficObj = null;
		String errorMsg = "";

		try {
			for (TrafficPolicyHistory tr : oldListTrafficPoliceHistory) {

				trafficObj = new TrafficPolicyHistoryObj();
				trafficObj.setTrafficPolicyHistory(tr);
				dppm = processorPacketBO.edit(trafficObj
						.getTrafficPolicyHistory().getDppmId());

				// ---- Getting TrafficPolice for the current
				// TrafficPolicyHistory
				errorMsg = "Getting Traffic Policy.";
				trafficObj.setTrafficPolicy(trafficPolicyBO.findById(tr
						.getTrafficPolicyId()));

				// ---- Getting Context for the current TrafficPolicy
				if (trafficObj.getTrafficPolicy() != null) {
					errorMsg = "Getting Context "
							+ trafficObj.getTrafficPolicy().getContextId()
									.toString();
					trafficObj.setContext(contextBO.edit(trafficObj
							.getTrafficPolicy().getContextId()));
				} else {
					trafficObj.setContext(new Context());
				}

				// ---- Getting device for the current Traffic Policy
				if (trafficObj.getTrafficPolicyHistory() != null) {
					errorMsg = "Getting Device.";
					if (dppm == null) {
						dppm = new ProcessorPacket();
						device = new Device();
						device.setName("Deleted Device");
						dppm.setName("Deleted DPPM");
						dppm.setDevice(device);
					} else {
						device = dppm.getDevice();
						if (BasicValueCheck.isEmptyString(device.getId())) {
							device.setName("Deleted Device");
						}
					}
					trafficObj.setDevice(device);
				} else {
					trafficObj.setDevice(new Device());
				}

				// ---- Getting Processor Package
				if (trafficObj.getTrafficPolicy() != null) {
					errorMsg = "Getting ProcessorPackage.";
					trafficObj.setProcessorPacket(dppm);
				} else {
					trafficObj.setProcessorPacket(new ProcessorPacket());
				}

				// ---- loading into List Object
				newListTrafficPoliceHistory.add(trafficObj);

			}
		} catch (ExceptionSys e) {
			throw new ExceptionSys("Build Failed " + errorMsg + " " + e);
		}
		return newListTrafficPoliceHistory;
	}
}
