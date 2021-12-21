package br.com.alcatellucent.csm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Scheduling;
import br.com.alcatellucent.csm.bo.QuartzBO;
import br.com.alcatellucent.csm.bo.SchedulingBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;


public class SchedulerPanelAction  extends CsmBaseAction{
//	Action nunca pode ter propriedade (manter estado)
//	private QuartzBO quartzBO = null;
//	private SchedulingBO  schedulingBO = null;
	
	public SchedulerPanelAction(){
		super();	
	}
	
	public ActionForward list(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
	   throws ExceptionSys{
		HttpSession session = request.getSession();	
		String  trafficPolicyId      = (String) session.getAttribute("trafficPolicyId");
		String ahrefTrafficPolicyId  = request.getParameter("scheduling.trafficPolicyId");		
		if( ahrefTrafficPolicyId != null ){			
			session.setAttribute("trafficPolicyId",ahrefTrafficPolicyId);
			trafficPolicyId = ahrefTrafficPolicyId;
		}		
		SchedulingBO schedulingBO = new SchedulingBO(); 
		session.setAttribute("listScheduling",  schedulingBO.listSchedulingPanel(trafficPolicyId) );
		return actionMapping.findForward("list");
	}
	
	public ActionForward delete(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
	   throws ExceptionSys, Exception{
		HttpSession session = request.getSession();
		String  trafficPolicyId      = (String) session.getAttribute("trafficPolicyId");				
		QuartzBO quartzBO = new QuartzBO();
		SchedulingBO schedulingBO = new SchedulingBO();
		String schedulingId =  request.getParameter("scheduling.id");		
		quartzBO.delJobDetails(schedulingId);
		
		Scheduling   scheduling = (Scheduling)schedulingBO.findById(schedulingId);
		schedulingBO.delete(scheduling);
		
		// Igor - 2007-10-01 - Inclusão de Log da aplicação.
		this.saveLog(request, "schedulerPanel.title.delete", "key = " + scheduling.getId(), CsmLogSeverity.LOW_SEVERITY.getValue());
		
		session.setAttribute("listScheduling",  schedulingBO.listSchedulingPanel(trafficPolicyId) );
		return actionMapping.findForward("list");
	}
	
}	
	