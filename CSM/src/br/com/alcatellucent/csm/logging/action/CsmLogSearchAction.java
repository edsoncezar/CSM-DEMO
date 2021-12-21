package br.com.alcatellucent.csm.logging.action;

import java.sql.Date;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.action.CsmBaseAction;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.logging.form.CsmLogSearchForm;
import br.com.alcatellucent.csm.logging.persistence.CsmLog;
import br.com.alcatellucent.csm.logging.persistence.CsmLogBO;
import br.com.alcatellucent.csm.utils.BasicValueCheck;
import br.com.alcatellucent.csm.utils.DateUtil;

public final class CsmLogSearchAction extends CsmBaseAction {

    private String retorno;

    public ActionForward initial(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) {
    	String redir="";
    	if(checkCredentials(request, new String[]{Role.OPERATOR_ROLE,Role.GUEST_ROLE}, false)){
    		redir=FORWARD_INITIAL;
    	}else{
    		redir=FORWARD_FORBIDDEN;
    	}
	return actionMapping.findForward(redir);
    }

    public ActionForward list(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) {

	retorno = FORWARD_LIST;
	Collection<CsmLog> csmLogList;
	if (checkCredentials(request, new String[] { Role.OPERATOR_ROLE,Role.GUEST_ROLE }, false)) {
			HttpSession session = request.getSession();
			CsmLogBO csmLogBO = new CsmLogBO();
			StringBuffer hql = new StringBuffer();

			CsmLogSearchForm logSearchForm = (CsmLogSearchForm) actionForm;

			String string = buildCriteria(actionForm);

			try {
				if (string.length() == 0) {
					csmLogList = csmLogBO.list();
				} else {
					csmLogList = csmLogBO.findByQuery(string);
				}
				session.setAttribute("LIST_CSM_LOG", csmLogList);

			} catch (ExceptionSys ex) {
				ex.printStackTrace();
				retorno = "falid";
			}
		}
	else{
		retorno=FORWARD_FORBIDDEN;
	}
	return actionMapping.findForward(retorno);
    }

    private String buildCriteria(ActionForm actionForm) {

	Date dateIn = null;
	Date dateFin = null;
	String timeIn = "00:00:00";
	String timeFin = "00:00:00";

	StringBuffer hql = new StringBuffer();
	CsmLogSearchForm logSearchForm = (CsmLogSearchForm) actionForm;

	// sample java.sql.Timestamp ts2 =
	// java.sql.Timestamp.valueOf("2005-04-06 09:01:10");

	// - Verifica digitacao da hora inicial
	// -------------------------------------------------------------------------
	if ((logSearchForm.getStartTime() != null)
		&& (logSearchForm.getStartTime().trim().length() > 0)) {
	    timeIn = logSearchForm.getStartTime().toString();
	}

	// - Verifica digitacao da hora final
	// -------------------------------------------------------------------------
	if ((logSearchForm.getEndTime() != null)
		&& (logSearchForm.getEndTime().trim().length() > 0)) {
	    timeFin = logSearchForm.getEndTime().toString();
	}

	// - Verifica digitacao da data inicial
	// -------------------------------------------------------------------------
	if ((logSearchForm.getStartDate() != null)
		&& (logSearchForm.getStartDate().trim().length() > 0)) {
	    dateIn = DateUtil.getSQLDate(stringToDate(logSearchForm
		    .getStartDate()));
	    hql.append("eventTime >= TIMESTAMP('" + dateIn.toString() + " "
		    + timeIn + "')");
	}

	// - Verifica digitacao da data final
	// -------------------------------------------------------------------------
	if ((logSearchForm.getEndDate() != null)
		&& (logSearchForm.getEndDate().trim().length() > 0)) {
	    dateFin = DateUtil.getSQLDate(DateUtil
		    .stringToStringDate(logSearchForm.getEndDate()));
	    if (hql.length() > 0) {
		hql.append(" and ");
	    }
	    hql.append("eventTime <= TIMESTAMP('" + dateFin.toString() + " "
		    + timeFin + "')");
	}

	// - Verifica digitacao do User Id
	// -------------------------------------------------------------------------
	if ((logSearchForm.getUserId() != null)
		&& (logSearchForm.getUserId().trim().length() > 0)) {
	    if (hql.length() > 0) {
		hql.append(" and ");
	    }
	    hql.append("userName like '%" + logSearchForm.getUserId().toString()
		    + "%'");

	}

	String string = "";

	if (hql.length() > 0) {
	    string = hql.toString();
	    string = "from CsmLog where " + string;
	} else {
	    string = "from CsmLog ";
	}

	switch (Integer.parseInt((String)logSearchForm.getSort())) {
	
	case 2:
		string = string + " order by dateEvent, eventTime";
		break;

	default:
		string = string + " order by dateEvent desc, eventTime desc";
		break;
	}
	
	return string;

    }

    private String stringToDate(String date) {

	String[] dt = date.split("/");

	date = (dt[0].length() < 2 ? ("0" + dt[0]) : dt[0]) + "/"
		+ (dt[1].length() < 2 ? ("0" + dt[1]) : dt[1]) + "/"
		+ (dt[2].length() < 2 ? ("0" + dt[2]) : dt[2]);

	return (date);
    }

}
