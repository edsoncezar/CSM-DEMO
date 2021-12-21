package br.com.alcatellucent.csm.form;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Context;
import br.com.alcatellucent.csm.beans.ProcessorPacket;
import br.com.alcatellucent.csm.beans.TrafficPolicyHistory;
import br.com.alcatellucent.csm.form.common.CommonForm;

public class TrafficPolicyHistoryForm extends CommonForm {
	
	private static final long serialVersionUID = 1L;

	private Integer statusApplied;
	
	private Integer mode;
	
	private String dateFrom;
	
	private Object sort;

	@Override
	public void reset(ActionMapping map, HttpServletRequest req) {
		
		this.setStatusApplied(null);
		this.setMode(null);
		this.setDateFrom(null);
		
	}


	public Integer getStatusApplied() {
		return statusApplied;
	}


	public void setStatusApplied(Integer statusApplied) {
		this.statusApplied = statusApplied;
	}


	public Integer getMode() {
		return mode;
	}


	public void setMode(Integer mode) {
		this.mode = mode;
	}


	public String getDateFrom() {
		return dateFrom;
	}


	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}


	public Object getSort() {
		return sort;
	}


	public void setSort(Object sort) {
		this.sort = sort;
	}


}