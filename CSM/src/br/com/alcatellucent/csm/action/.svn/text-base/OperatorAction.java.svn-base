package br.com.alcatellucent.csm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Operator;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.beans.UserProfile;
import br.com.alcatellucent.csm.bo.OperatorBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.OperatorForm;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;
import br.com.alcatellucent.csm.utils.DateUtil;

/**
 * 
 * @author Edson Moreira Cezar
 * @date 08/18/2007
 * @version 1.0
 * 
 */
public class OperatorAction extends CsmBaseAction {

	
	public ActionForward initial(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
	   throws ExceptionSys{
		String retorno=FORWARD_INITIAL;
		if(checkCredentials(request, new String[]{Role.GUEST_ROLE}, false)){
			OperatorForm form = (OperatorForm) actionForm;
			Operator operator = form.getOperator();
			form.clearForm();
			form.setDate(DateUtil.dateToString(DateUtil.getCurrentSQLDate()));
		}else{
			retorno=FORWARD_FORBIDDEN;
		}
		
	   return actionMapping.findForward(retorno);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward save(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
	   throws ExceptionSys, Exception{
		String redir = "list";
		HttpSession session = request.getSession();
		if(checkCredentials(request, new String[]{Role.GUEST_ROLE}, false)){
			OperatorBO operatorBO = new OperatorBO();
		
			OperatorForm form 			= (OperatorForm) actionForm;
			Operator operator 			=  form.getOperator();
		
			if(null == form.getOperator().getLastAlert()) {
				form.getOperator().setLastAlert(DateUtil.getCurrentSQLDate());
			}
			
			try{
				operatorBO.save(operator);
				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "operator.title", "key = " + operator.getId(), CsmLogSeverity.LOW_SEVERITY.getValue());
				showMessages(request, "default.save.ok", MESSAGE_WARNING);
				form.setRefresh("true");
			}catch( Exception e){
				// Igor - 2007-10-01 - Inclusão de Log da aplicação.
				this.saveLog(request, "operator.title.error", "key = " +  operator.getId() + "erro = " + e.getMessage(), CsmLogSeverity.FATAL_SEVERITY.getValue());
				showMessages(request, "default.save.error", MESSAGE_ERROR);
//				redir = "falid";	
			}
			
			session.setAttribute("listOperators", operatorBO.list() );
		}else{
			redir=FORWARD_FORBIDDEN;
		}
	   return actionMapping.findForward(redir);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward list(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
	   throws ExceptionSys{
		
		OperatorBO operatorBO = new OperatorBO();
		HttpSession session = request.getSession();
							
		session.setAttribute("listOperators", operatorBO.list() );
		return actionMapping.findForward("list");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward edit(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
	   throws ExceptionSys{
		
		HttpSession session = request.getSession();	
		String retorno="view";
			Operator operator           = new Operator();
			OperatorBO operatorBO		= new OperatorBO();
			OperatorForm form 			= (OperatorForm) actionForm;
			String operatorId           = request.getParameter("id");
			operator = operatorBO.edit(operatorId);
			form.setOperator(operator);
			session.setAttribute("listOperators", operatorBO.list());	
		return actionMapping.findForward(retorno);
		
	}
	
	
	@SuppressWarnings("unchecked")
	public ActionForward delete(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
	   throws ExceptionSys, Exception{
		HttpSession session = request.getSession();
		OperatorBO operatorBo = new OperatorBO();
		OperatorForm form = (OperatorForm) actionForm;
		String redir = "list";
		if(checkCredentials(request, new String[]{Role.GUEST_ROLE}, false)){
		try{
			String operatorId           = request.getParameter("id");
			operatorBo.delete(operatorId);
			// Igor - 2007-10-01 - Inclusão de Log da aplicação.
			this.saveLog(request, "operator.title.delete", "key = " + form.getOperator().getId(), CsmLogSeverity.LOW_SEVERITY.getValue());
			showMessages(request, "default.delete.ok", MESSAGE_WARNING);
			form.setRefresh("true");
		}catch( Exception e){
			// Igor - 2007-10-01 - Inclusão de Log da aplicação.
			this.saveLog(request, "operator.title.delete.error", "key = " + form.getOperator().getId(), CsmLogSeverity.FATAL_SEVERITY.getValue());
			showMessages(request, "default.delete.error", MESSAGE_ERROR);
//			redir = "falid";
		}
		session.setAttribute("listOperators", operatorBo.list() );
		}else{
			redir=FORWARD_FORBIDDEN;
		}
	   return actionMapping.findForward(redir);
	}
}
