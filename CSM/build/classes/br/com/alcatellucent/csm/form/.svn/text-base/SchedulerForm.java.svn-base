package br.com.alcatellucent.csm.form;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.beans.PortProtocolGroup;
import br.com.alcatellucent.csm.beans.Scheduling;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

public class SchedulerForm  extends CommonForm {
	
	private static final long serialVersionUID = 1200656540647784831L;
	private Collection<PortProtocolGroup> groupList;
	private String selectedGroups;
	private Scheduling scheduling;

	public Scheduling getScheduling() {
		if(null == scheduling)
			this.scheduling = new Scheduling();
		return scheduling;
	}

	public void setScheduling(Scheduling scheduling) {
		this.scheduling = scheduling;
	}
	
	/**
	 * @return the groupList
	 */
	public Collection<PortProtocolGroup> getGroupList() {
		return groupList;
	}
	/**
	 * @param groupList the groupList to set
	 */
	public void setGroupList(Collection<PortProtocolGroup> groupList) {
		this.groupList = groupList;
	}
	/**
	 * @return the selectedGroups
	 */
	public String getSelectedGroups() {
		return selectedGroups;
	}
	/**
	 * @param selectedGroups the selectedGroups to set
	 */
	public void setSelectedGroups(String selectedGroups) {
		this.selectedGroups = selectedGroups;
	}
	
	@Override
	public void reset(ActionMapping map, HttpServletRequest req){
		if (((String)req.getParameter("action")).equals("initial")) {
			scheduling = new Scheduling();
		}
		
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		boolean emptyForm = true;
	
//Verifica validade das informações digitadas conforme tipo do dado.
		if (((String) request.getParameter("action")).equals("record")) {
			
		if (this.getScheduling().getName()!=null&&this.getScheduling().getName()!=""){
			emptyForm = false;
			if(BasicValueCheck.isEmptyString(this.getScheduling().getName())) {
				errors.add("schedulerForm.name",new ActionMessage("erro.pktForm.Name"));
			}
		}
		
		if (this.getScheduling().getDateStart()!=null&&this.getScheduling().getDateStart()!=""){
			emptyForm = false;
			if(BasicValueCheck.isEmptyString(this.getScheduling().getDateStart())) {
				errors.add("schedulerForm.Date",new ActionMessage("erro.schedulerForm.Date"));
			}
		}
		
		if (this.getScheduling().getHour()!=null&&this.getScheduling().getHour()!=""){
			emptyForm = false;
			if(BasicValueCheck.isEmptyString(this.getScheduling().getHour())) {
				errors.add("schedulerForm.Hour",new ActionMessage("erro.schedulerForm.Hour"));
			}
		}
		
		if (this.getScheduling().getMinute()!=null&&this.getScheduling().getMinute()!=""){
			emptyForm = false;
			if(BasicValueCheck.isEmptyString(this.getScheduling().getMinute())) {
				errors.add("schedulerForm.Minute",new ActionMessage("erro.schedulerForm.Minute"));
			}
		}
		
		}

		if (emptyForm) { 
			return null;
		} else {
			return errors;
		}
	}
	
}

