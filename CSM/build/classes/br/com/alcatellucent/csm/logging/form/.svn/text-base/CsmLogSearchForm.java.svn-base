package br.com.alcatellucent.csm.logging.form;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.DateUtil;

public class CsmLogSearchForm extends CommonForm {

    private static final long serialVersionUID = 2292847423943145343L;
    
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String userId;
    private Object sort;

    public Object getSort() {
		return sort;
	}

	public void setSort(Object sort) {
		this.sort = sort;
	}

	public String getStartDate() {
	return startDate;
    }

    public void setStartDate(String startDate) {
	this.startDate = startDate;
    }

    public String getStartTime() {
	return startTime;
    }

    public void setStartTime(String startTime) {
	this.startTime = startTime;
    }

    public String getEndDate() {
	return endDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public String getEndTime() {
	return endTime;
    }

    public void setEndTime(String endTime) {
	this.endTime = endTime;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
    	
		ActionErrors errors = new ActionErrors();
		boolean emptyForm = true;
    	
		if ((null!=this.getStartDate() && this.getStartDate().trim().length() > 0) && 
		   (null!=this.getEndDate()   && this.getEndDate().trim().length() > 0)) {
			
			Calendar calStart = Calendar.getInstance();
			Calendar calEnd = Calendar.getInstance();
			
			String wStartTime = null;
			String wEndTime = null;
			String wStartDate = null;
			String wEndDate = null;
			
			emptyForm = false;
			
			wStartDate = (null == this.getStartDate() || !(this.getStartDate().length() > 0)) ?
					    "00/00/0000":this.getStartDate();
			
			wEndDate = (null == this.getEndDate() || !(this.getEndDate().length() > 0)) ?
					"00/00/0000":this.getEndDate();
			
			wStartTime = (null == this.getStartTime() || !(this.getStartTime().length() > 0)) ?
				    	"00:00:00":this.getStartTime();
		
			wEndTime = (null == this.getEndTime() || !(this.getEndTime().length() > 0)) ?
						"00:00:00":this.getEndTime();

			try {
				
				calStart = DateUtil.StringToCalendar(wStartDate, wStartTime, DateUtil.DATE_FORMAT_DDMMAAAA, DateUtil.TIME_FORMAT_HHMMSS);
				calEnd = DateUtil.StringToCalendar(wEndDate, wEndTime, DateUtil.DATE_FORMAT_DDMMAAAA, DateUtil.TIME_FORMAT_HHMMSS);
				
				if (calEnd.compareTo(calStart)<0) {
					errors.add("error.log.date",new ActionMessage("error.log.date"));
				}
				
			} catch (ExceptionSys e) {
				errors.add("error.log.date.error",new ActionMessage("error.log.date.error"));
			}
		}
		
		if (emptyForm) { 
			return null;
		} else {
			return errors;
		}
	}
}