package br.com.alcatellucent.csm.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.form.common.CommonForm;

public class AclHistoryForm extends CommonForm {

    private static final long serialVersionUID = 9024740358929492705L;

    private Integer statusApplied = 0;

    private String aclId = null;

    private String dateFrom = null;
    
    private Object sort = null;

    public Object getSort() {
		return sort;
	}

	public void setSort(Object sort) {
		this.sort = sort;
	}

	public void reset(ActionMapping map, HttpServletRequest req) {
	this.setStatusApplied(null);
	this.setAclId(null);
	this.setDateFrom(null);
    }

    public Integer getStatusApplied() {
	return statusApplied;
    }

    public void setStatusApplied(Integer statusApplied) {
	this.statusApplied = statusApplied;
    }

    public String getDateFrom() {
	return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
	this.dateFrom = dateFrom;
    }

    public String getAclId() {
	return aclId;
    }

    public void setAclId(String aclId) {
	this.aclId = aclId;
    }

}