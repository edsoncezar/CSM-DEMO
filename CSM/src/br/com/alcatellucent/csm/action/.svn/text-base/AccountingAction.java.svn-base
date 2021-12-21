package br.com.alcatellucent.csm.action;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Accounting;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.bo.AccountingBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
// Teste 
public class AccountingAction extends CsmBaseAction {

	public ActionForward list(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		String redir=FORWARD_LIST;
		if (checkCredentials(request, new String[] { Role.OPERATOR_ROLE,
				Role.GUEST_ROLE }, false)) {
			HttpSession session = request.getSession();
			AccountingBO accBo = new AccountingBO();
			String userNameToFilter = request.getParameter("username");
			String userIpAddress = request.getParameter("userIP");
			Collection<Accounting> accList = null;

			accList = accBo.list(userNameToFilter, userIpAddress);
			session.setAttribute("accountList", accList);
		} else {
			redir=FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

}
