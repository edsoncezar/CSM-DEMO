package br.com.alcatellucent.csm.action;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import br.com.alcatellucent.csm.beans.PortProtocolGroup;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.ScheduledGroup;
import br.com.alcatellucent.csm.beans.SchedulerProtocol;
import br.com.alcatellucent.csm.beans.Scheduling;
import br.com.alcatellucent.csm.bo.PortProtocolGroupBO;
import br.com.alcatellucent.csm.bo.QuartzBO;
import br.com.alcatellucent.csm.bo.ScheduledGroupBO;
import br.com.alcatellucent.csm.bo.SchedulingBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.SchedulingForm;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;
import br.com.alcatellucent.csm.utils.DateUtil;

@SuppressWarnings("unchecked")
public class SchedulerAction extends CsmBaseAction {
	// Action nunca pode ter propriedade (manter estado)
	// @SuppressWarnings("unchecked")
	// TrafficPolicyBO trafficPoliceBO = null;
	// QuartzBO quartzBO = null;
	// SchedulingBO schedulingBO = null;
	// GroupTrafficPolicyBO grupoTrafficPolicyBO = null;
	// PortProtocolGroupBO portProtocolGroupBO = null;
	// Map months = null;
	// Map weekOfDays = null;
	// Collection<PortProtocolGroup> col = null;

	private static Map months = null;
	private static Map weekOfDays = null;
	
	static {
		months = new LinkedHashMap();
		months.put("JAN", "January");
		months.put("FEB", "February");
		months.put("MAR", "March");
		months.put("APR", "April");
		months.put("MAY", "May");
		months.put("JUN", "June");
		months.put("JUL", "July");
		months.put("AUG", "August");
		months.put("SEP", "September");
		months.put("OCT", "October");
		months.put("NOV", "November");
		months.put("DEC", "December");

		weekOfDays = new LinkedHashMap();
		weekOfDays.put("MON", "Monday");
		weekOfDays.put("TUE", "Tuesday");
		weekOfDays.put("WED", "Wednesday");
		weekOfDays.put("THU", "Thursday");
		weekOfDays.put("FRI", "Friday");
		weekOfDays.put("SAT", "Saturday");
		weekOfDays.put("SUN", "Sunday");
	}

	public SchedulerAction() {
		super();
	}

	public ActionForward initial(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		String redir = FORWARD_INITIAL;
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			HttpSession session = request.getSession();
			Format formatterCompleto = new SimpleDateFormat("dd-MM-yyyy");
			Calendar c = new GregorianCalendar();
			Date periodo = new Date();
			periodo = c.getTime();
			String dateStart = formatterCompleto.format(periodo);
			SchedulingBO schedulingBO = new SchedulingBO();

			SchedulingForm form = (SchedulingForm) actionForm;
			form.setNewList(new String[] {});
			// LOADING GROUPS LIST ///////////////////////////////
			getGroupList(request);
			// ///////////////////////////////////////////////////

			Scheduling scheduling = new Scheduling();
			scheduling.setDateStart(dateStart);
			// scheduling.setContextId(request.getParameter("contextId"));

			scheduling.setContextId(form.getScheduling().getContextId());
			scheduling.setTrafficPolicyId(form.getScheduling().getTrafficPolicyId());
			form.setScheduling(scheduling);

			// Register trafficPolicyId in Session
			session.setAttribute("trafficPolicyId", form.getScheduling().getTrafficPolicyId());

			session.setAttribute("monthChecked", scheduling.getMonth());
			session.setAttribute("months", months);
			session.setAttribute("weekOfDays", weekOfDays);

			List<SchedulerProtocol> listSchedulerProtocol = new ArrayList();
			session.setAttribute("listSchedulerProtocol",listSchedulerProtocol);

			List<PortProtocolGroup> listGroup = new ArrayList<PortProtocolGroup>();
			session.setAttribute("listGroup", listGroup);
			session.setAttribute("listScheduling", schedulingBO.getSchedByTrafficPolicyId(form.getScheduling().getTrafficPolicyId()));
		} else {
			redir = FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	public ActionForward addGroup(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PortProtocolGroupBO portProtocolGroupBO = new PortProtocolGroupBO();

		HttpSession session = request.getSession();
		SchedulingForm form = (SchedulingForm) actionForm;
		Scheduling schedulerForm = (Scheduling) form.getScheduling();

		PortProtocolGroup objPortProtocol = (PortProtocolGroup) portProtocolGroupBO.edit(schedulerForm.getContextId());

		List<PortProtocolGroup> listGroupTemp = new ArrayList<PortProtocolGroup>();
		List<PortProtocolGroup> listGroup = (List<PortProtocolGroup>) request.getSession().getAttribute("listGroup");
		for (PortProtocolGroup portProtocolGroup : listGroup) {
			listGroupTemp.add(portProtocolGroup);
		}

		listGroupTemp.add(objPortProtocol);
		session.setAttribute("listGroup", listGroupTemp);
		session.setAttribute("monthChecked", schedulerForm.getMonth());
		session.setAttribute("months", months);
		session.setAttribute("weekOfDays", weekOfDays);

		return actionMapping.findForward("initial");
	}

	public ActionForward record(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String redir = FORWARD_LIST;
		if(checkCredentials(request, new String[]{Role.GUEST_ROLE}, false)){
			HttpSession session = request.getSession();

			QuartzBO quartzBO = null;
			SchedulingBO schedulingBO = new SchedulingBO();
			quartzBO = new QuartzBO();

			SchedulingForm form = (SchedulingForm) actionForm;
			Scheduling schedulerForm = (Scheduling) form.getScheduling();
			schedulerForm.setDateStart(DateUtil.getCorrectStringDate(schedulerForm.getDateStart()));
			String trafficPolicyId = (String) session.getAttribute("trafficPolicyId");

			Scheduling schedulingTemp = new Scheduling();
			schedulingTemp.setId(schedulerForm.getId());
			schedulingTemp.setName(schedulerForm.getName());
			schedulingTemp.setDescription(schedulerForm.getDescription());
			schedulingTemp.setDateInc(new Date());
			schedulingTemp.setEvery(schedulerForm.getEvery());
			schedulingTemp.setEveryType(quartzBO.everyType(schedulerForm.getEveryType()));
			String minute = (schedulerForm.getMinute().length() < 2 ? "0" + schedulerForm.getMinute() : schedulerForm
				.getMinute());
			schedulingTemp.setTimeStart(schedulerForm.getHour() + ":" + minute);
			schedulingTemp.setDateStart(schedulerForm.getDateStart());
			schedulingTemp.setYear(schedulerForm.getYear());
			schedulingTemp.setDaysOfWeek(quartzBO.formDaysOfWeek(schedulerForm.getWeek()));
			schedulingTemp.setMonths(quartzBO.formMonths(schedulerForm.getMonth()));
			schedulingTemp.setContextId(schedulerForm.getContextId());
			schedulingTemp.setTrafficPolicyId(schedulerForm.getTrafficPolicyId());

			if (schedulerForm.getCronSubscriber() == null)
				schedulingTemp.setCronExpression(quartzBO.expressionTimerCron(schedulerForm));
			else
				schedulingTemp.setCronExpression(schedulerForm.getCronExpression());

			// groups and protocols///////////////////////////////////////////////////
			ScheduledGroupBO scheduledGroupBO = new ScheduledGroupBO();
			Set<ScheduledGroup> newScheduledGroups = new HashSet<ScheduledGroup>();
			newScheduledGroups = scheduledGroupBO.copyAllPortProtocolGroupById(form.getNewList());

			// Set<ScheduledGroup> oldScheduledGroups =
			// schedulingTemp.getScheduledGroups();
			schedulingTemp.setScheduledGroups((Set<ScheduledGroup>) request.getSession().getAttribute("oldScheduledGroup"));
			scheduledGroupBO.populateCollections(newScheduledGroups, schedulingTemp.getScheduledGroups());

			// schedulingTemp.setScheduledGroups(oldScheduledGroups);
			// ///////////////////////////////////////////////////////////////////////////////
			for (ScheduledGroup sgroup : schedulingTemp.getScheduledGroups()) {
					sgroup.setScheduling(schedulingTemp);
			}
			try {
				schedulingBO.save(schedulingTemp);

				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "scheduler.title", "key = " + schedulingTemp.getId(), CsmLogSeverity.LOW_SEVERITY
					.getValue());

				quartzBO.delJobDetails(schedulingTemp.getId());
				showMessages(request, "default.save.ok", MESSAGE_WARNING);
			} catch (ExceptionSys ex) {

				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "scheduler.title.error", "key = " + schedulingTemp.getId() + " error = "
						+ ex.getMessage(), CsmLogSeverity.LOW_SEVERITY.getValue());

				showMessages(request, "default.save.error", MESSAGE_ERROR);
			}
			// session.setAttribute("listScheduling",schedulingBO.getSchedByTrafficPolicyId(form.getScheduling().getTrafficPolicyId()));
			session.setAttribute("listScheduling", schedulingBO.listSchedulingPanel(trafficPolicyId));
		}else{
			redir=FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	public ActionForward reload(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		QuartzBO quartzBO = null;
		quartzBO = new QuartzBO();
		quartzBO.reloadJobDetails(request.getParameter("jobName"));

		return actionMapping.findForward("initial");
	}

	public ActionForward edit(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		QuartzBO quartzBO = null;
		SchedulingBO schedulingBO = new SchedulingBO();
		quartzBO = new QuartzBO();

		HttpSession session = request.getSession();
		SchedulingForm form = (SchedulingForm) actionForm;
		Scheduling schedulerForm = (Scheduling) form.getScheduling();

		Scheduling scheduling = (Scheduling) schedulingBO.findById(schedulerForm.getId());

		scheduling.setMonth(quartzBO.splitMonths(scheduling.getMonths()));
		scheduling.setWeek(quartzBO.splitWeek(scheduling.getDaysOfWeek()));

		String timeStart[] = quartzBO.splitTimeStart(scheduling.getTimeStart());
		if (timeStart.length > 1) {
			scheduling.setHour(timeStart[0]);
			scheduling.setMinute(timeStart[1]);
		}
		if (null != scheduling.getMinute() && Integer.valueOf(scheduling.getMinute()) < 10) {
			scheduling.setMinute(String.valueOf(Integer.valueOf(scheduling.getMinute())));
		}

		form.setScheduling(scheduling);
		form.setNewList(new String[] {});
		session.setAttribute("months", months);
		session.setAttribute("monthChecked", scheduling.getMonth());
		session.setAttribute("weekOfDays", weekOfDays);
		session.setAttribute("scheduledGroupList", scheduling.getScheduledGroups());
		// Load portProtocolGroupList
		getGroupList(request);

		return actionMapping.findForward("edit");
	}

	public ActionForward delete(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String redir = FORWARD_LIST;
		if(checkCredentials(request, new String[]{Role.GUEST_ROLE}, false)){
			QuartzBO quartzBO = null;
			SchedulingBO schedulingBO = new SchedulingBO();
			quartzBO = new QuartzBO();

			HttpSession session = request.getSession();
			String trafficPolicyId = (String) session.getAttribute("trafficPolicyId");
			SchedulingForm form = (SchedulingForm) actionForm;
			Scheduling schedulingForm = form.getScheduling();

			quartzBO.delJobDetails(schedulingForm.getId());

			Scheduling scheduling = null;

			try {
				scheduling = (Scheduling) schedulingBO.findById(schedulingForm.getId());
			
				//Igor : 2007-10-24 Eliminar job no Quartz
				this.deleteJobScheduled(scheduling.getId(), scheduling.getId());
			
				schedulingBO.delete(scheduling);

				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "scheduler.title.delete", "key = " + scheduling.getId(), CsmLogSeverity.LOW_SEVERITY
						.getValue());

				showMessages(request, "default.delete.ok", MESSAGE_WARNING);
			} catch(SchedulerException ex){
			 
				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "scheduler.title.delete.error", "key = " + scheduling.getId() + " error = " + ex.getMessage(), CsmLogSeverity.FATAL_SEVERITY.getValue());
		 
				showMessages(request, "default.delete.error", MESSAGE_ERROR);	 
			} catch (ExceptionSys ex) {

				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "scheduler.title.delete.error", "key = " + scheduling.getId() + " error = "
						+ ex.getMessage(), CsmLogSeverity.FATAL_SEVERITY.getValue());

				showMessages(request, "default.delete.error", MESSAGE_ERROR);
			} 
			session.setAttribute("listScheduling", schedulingBO.getSchedByTrafficPolicyId(trafficPolicyId));
		}else{
			redir=FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	public ActionForward list(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SchedulingBO schedulingBO = new SchedulingBO();

		HttpSession session = request.getSession();
		String trafficPolicyId = (String) session.getAttribute("trafficPolicyId");
		session.setAttribute("listScheduling", schedulingBO.listSchedulingPanel(trafficPolicyId));

		return actionMapping.findForward("list");
	}

	protected void getGroupList(HttpServletRequest request) throws ExceptionSys {
		PortProtocolGroupBO groupBO = new PortProtocolGroupBO();
		request.getSession().setAttribute("groupList", groupBO.list());
	}

	public void test(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String schID = request.getParameter("schId");
		String devID = request.getParameter("devID");

		SchedulingBO schBO = new SchedulingBO();
		schBO.execute(schID, devID);
	}
	
    @SuppressWarnings("unused")
	private void deleteJobScheduled(String jobName, String groupName) throws SchedulerException, Exception {
    	
    	try {
    		SchedulerFactory sf = new StdSchedulerFactory();
    		Scheduler sch = sf.getScheduler();
    		sch.deleteJob(jobName, groupName);
    	}	catch (SchedulerException se) {
    			throw new SchedulerException("An error has ocurred when deleting a JOB " + se.getMessage());
    	}   catch (Exception e) {
    			throw e;
    	}
    	
    }
}