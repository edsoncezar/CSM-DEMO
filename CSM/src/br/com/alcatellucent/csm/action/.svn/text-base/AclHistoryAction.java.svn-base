package br.com.alcatellucent.csm.action;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Acl;
import br.com.alcatellucent.csm.beans.AclHistoryObj;
import br.com.alcatellucent.csm.beans.Context;
import br.com.alcatellucent.csm.beans.ProcessorPacket;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.bo.AclBO;
import br.com.alcatellucent.csm.bo.AclHistory;
import br.com.alcatellucent.csm.bo.AclHistoryBO;
import br.com.alcatellucent.csm.bo.ContextBO;
import br.com.alcatellucent.csm.bo.ProcessorPacketBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.AclHistoryForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

public final class AclHistoryAction extends CsmBaseAction {
// Action nunca pode ter propriedade (manter estado)
//    private String retorno;
//    Collection<AclHistory> listAclHistory;

    public ActionForward initial(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys {

	String redir = FORWARD_INITIAL;
	if (checkCredentials(request, new String[] { Role.OPERATOR_ROLE,Role.GUEST_ROLE }, false)) {
			try {
				this.popAclCombo(actionForm, request, response);
			} catch (Exception e) {
				throw new ExceptionSys(e);
			}
		} else {
			redir = FORWARD_FORBIDDEN;
		}
	return actionMapping.findForward(redir);
    }

    public ActionForward list(ActionMapping actionMapping,
	    ActionForm actionForm, HttpServletRequest request,
	    HttpServletResponse response) throws ExceptionSys {

	String retorno = FORWARD_LIST;
	if(checkCredentials(request, new String[]{Role.OPERATOR_ROLE,Role.GUEST_ROLE}, false)){
		try {
				this.popAclCombo(actionForm, request, response);
			} catch (Exception e) {
				throw new ExceptionSys(e);
			}

			HttpSession session = request.getSession();

			AclHistoryBO aclHistoryBO = new AclHistoryBO();

			String string = this.buildSearchCriteria(actionForm);

			Collection<AclHistory> listAclHistory = new ArrayList<AclHistory>();

			try {
				if (string.length() == 0) {
					listAclHistory = aclHistoryBO.list();
				} else {
					listAclHistory = aclHistoryBO.findByQuery(string);
				}
				session.setAttribute("LIST_ACL_HISTORY",
						buildHistoryCollection(listAclHistory));

			} catch (ExceptionSys ex) {
				ex.printStackTrace();
				retorno = FORWARD_FAILED;
			}
		} else {
			retorno = FORWARD_FORBIDDEN;
	}
	return actionMapping.findForward(retorno);
    }

    private String buildSearchCriteria(ActionForm actionForm) {

	AclHistoryForm form = (AclHistoryForm) actionForm;

	Date dateFrom = null;

	StringBuffer hql = new StringBuffer();

	// - Verifica digitacao da data inicial
	// -------------------------------------------------------------------------
	if ((form.getDateFrom() != null)
		&& (form.getDateFrom().trim().length() > 0)) {
	    dateFrom = BasicValueCheck.getSQLDate(BasicValueCheck
		    .stringToStringDate(form.getDateFrom()));
	    hql.append("dateTime >= TIMESTAMP('" + dateFrom.toString()
		    + " 00:00:00')");
	}

	// - Verifica seleção do Mode
	// -------------------------------------------------------------------------
	if (form.getAclId() != null && !form.getAclId().trim().equals("0")) {
	    if (hql.length() > 0) {
		hql.append(" and ");
	    }

	    hql.append("aclId = '" + form.getAclId().toString() + "'");
	}

	// - Verifica seleção do Status
	// -------------------------------------------------------------------------
	if (form.getStatusApplied() != null) {
	    if (hql.length() > 0) {
		hql.append(" and ");
	    }
	    hql.append("statusApplied = " + form.getStatusApplied());
	}

	String string = "";

	if (hql.length() > 0) {
	    string = hql.toString();
	    string = "from AclHistory where " + string;
	}
	
	switch (Integer.parseInt((String)form.getSort())) {
	case 2:
		string = string + " order by dateTime";
		break;

	default:
		string = string + " order by dateTime desc";
		break;
	}

	return string;
    }

    private Collection<AclHistoryObj> buildHistoryCollection(Collection<AclHistory> listAclHistory)
	    throws ExceptionSys {

	Collection<AclHistoryObj> myListAclHistory = new ArrayList<AclHistoryObj>();

	String errorMsg = "";

	ContextBO contextBO = new ContextBO();
	AclBO AclBO = new AclBO();
	ProcessorPacketBO processorPacketBO = new ProcessorPacketBO();

	AclHistoryObj aclObj = null;

	try {
	    for (AclHistory acl : listAclHistory) {

		aclObj = new AclHistoryObj();
		aclObj.setAclHistory(acl);

		// ---- Getting Acl for the current AclHistory
		errorMsg = "Getting Acl.";
		aclObj.setAcl(AclBO.edit(acl.getAclId()));

		// ---- Getting Context for the current Acl
		if (aclObj.getAcl() != null) {
		    errorMsg = "Getting Context "
			    + aclObj.getAcl().getContextId().toString();
		    aclObj.setContext(contextBO.edit(aclObj.getAcl()
			    .getContextId()));
		} else {
		    aclObj.setContext(new Context());
		}

		// ---- Getting Processor Package
		if (aclObj.getAcl() != null) {
		    errorMsg = "Getting ProcessorPackage.";
		    aclObj.setProcessorPacket(processorPacketBO.edit(aclObj
			    .getAclHistory().getDppmId()));
		} else {
		    aclObj.setProcessorPacket(new ProcessorPacket());
		}

		// ---- loading into List Object
		myListAclHistory.add(aclObj);
	    }

	} catch (ExceptionSys e) {
	    throw new ExceptionSys("Build Failed " + errorMsg + " " + e);
	}

	return myListAclHistory;
    }

    @SuppressWarnings("unchecked")
    private final void popAclCombo(ActionForm form, HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException,
	    ExceptionSys {

	HttpSession session = request.getSession();
	Collection<Acl> aclList = (Collection<Acl>) session
		.getAttribute("LIST_ACL");

	AclBO aclBO = new AclBO();

	if (aclList == null || aclList.size() == 0) {
	    try {
		aclList = new ArrayList<Acl>();
		aclList = aclBO.list();
		session.setAttribute("LIST_ACL", aclList);
	    } catch (ExceptionSys es) {
		throw new ExceptionSys(es);
	    }
	}
	return;
    }
}
