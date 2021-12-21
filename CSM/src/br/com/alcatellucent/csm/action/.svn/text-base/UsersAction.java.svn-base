package br.com.alcatellucent.csm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import br.com.alcatellucent.csm.beans.Users;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.facade.UsersFacade;
import br.com.alcatellucent.csm.logging.CsmLogSeverity;

public class UsersAction  extends CsmBaseAction {
	
	public UsersAction(){
		super();
	}
	
	public ActionForward initial(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
	   throws ExceptionSys{	
	   DynaActionForm form = (DynaActionForm) actionForm;
	   form.set("mail", "" );
	   form.set("name", "" );
	   form.set("id", Long.valueOf(0) );
	   form.set("password", "" );
	   
	   return actionMapping.findForward("initial");		
	}
	
	public ActionForward list(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
	   throws ExceptionSys{		
		UsersFacade usersFacade = (UsersFacade) request.getSession().getServletContext().getAttribute("usersFacade");
		HttpSession session = request.getSession();		
							
		session.setAttribute("listUsers", usersFacade.list() );		
		return actionMapping.findForward("list");
	}
	
	public ActionForward edit(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
	   throws ExceptionSys{		
		UsersFacade usersFacade = (UsersFacade) request.getSession().getServletContext().getAttribute("usersFacade");
		DynaActionForm form = (DynaActionForm) actionForm;
	    
		Users users = usersFacade.edit( (Long)form.get("id") );	    
		form.set("mail", users.getMail() );
		form.set("name", users.getName() );
		form.set("id", users.getId() );
		form.set("action", "update" );
		form.set("password", users.getPassword());		
						
		return actionMapping.findForward("view");
	}
	
	public ActionForward save(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
	   throws ExceptionSys, Exception{
		
		UsersFacade usersFacade = (UsersFacade) request.getSession().getServletContext().getAttribute("usersFacade");	
		DynaActionForm form = (DynaActionForm) actionForm;
		HttpSession session = request.getSession();		
		
		Users users= new Users();		
		users.setMail( (String)form.get("mail") );
		users.setName( (String)form.get("name") );
		users.setPassword((String)form.get("password"));
		users.setId((Long)form.get("id"));
		users.setContextId((Long)form.get("contextId"));		
		String redir = "success";
		
		try{		
			usersFacade.save(users);			
			// Igor - 2007-10-01 - Inclusão de Log da aplicação.
			this.saveLog(request, "users.title", "key = " + users.getId(), CsmLogSeverity.LOW_SEVERITY.getValue());			
		}catch( Exception e){			
			// Igor - 2007-10-01 - Inclusão de Log da aplicação.
			this.saveLog(request, "users.title.error", "key = " + users.getId() + " error = " + e.getMessage(), CsmLogSeverity.FATAL_SEVERITY.getValue());
			
			redir = "falid";		
		}		
		session.setAttribute("listUsers", usersFacade.list() );		
		
	   return actionMapping.findForward(redir);		
	}
	
	public ActionForward delete(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response)
	   throws ExceptionSys, Exception{
		UsersFacade usersFacade = (UsersFacade) request.getSession().getServletContext().getAttribute("usersFacade");		
		DynaActionForm form = (DynaActionForm) actionForm;		
		String redir = "success";
		
		try{
			usersFacade.delete((Long)form.get("id"));			
			// Igor - 2007-10-01 - Inclusão de Log da aplicação.
			this.saveLog(request, "users.title.delete", "key = " + ((Long)form.get("id")).toString(), CsmLogSeverity.LOW_SEVERITY.getValue());
			
		}catch( Exception e){
			// Igor - 2007-10-01 - Inclusão de Log da aplicação.
			this.saveLog(request, "users.title.delete.error", "key = " + ((Long)form.get("id")).toString(), CsmLogSeverity.FATAL_SEVERITY.getValue());
			redir = "falid";
		}		
	   return actionMapping.findForward(redir);		
	}
}
	