package br.com.alcatellucent.csm.action;

import java.util.Set;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.alcatellucent.csm.beans.Domain;
import br.com.alcatellucent.csm.beans.Resolver;
import br.com.alcatellucent.csm.beans.Role;
import br.com.alcatellucent.csm.bo.DomainBO;
import br.com.alcatellucent.csm.bo.ResolverBO;
import br.com.alcatellucent.csm.bo.TrafficPolicyBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.DomainControlForm;

public class DomainControlAction extends CsmBaseAction {

	public ActionForward initial(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		DomainControlForm form = (DomainControlForm)actionForm;
		Domain domain =  form.getDomain();
		form.setDomain(new Domain());
		form.getDomain().setContextId(domain.getContextId());
		loadLists(request, actionForm);
		return actionMapping.findForward(FORWARD_INITIAL);
	}

	public ActionForward save(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		String redir=FORWARD_INITIAL;
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			DomainBO domainBO = new DomainBO();
			ResolverBO resolverBO = new ResolverBO();

			DomainControlForm form = (DomainControlForm) actionForm;
			Domain domain = form.getDomain();

			Set<Resolver> resolvers;
			try {
				// faz a remoção e atualiza todos os resolvers toda vez que for
				// salvo.
				if (!domain.getId().equals("")) {
					domainBO.delete(domain.getId());
					domain.setId("");
				}

				resolvers = resolverBO.getResolvedAddress(domain, "2000", "3");
				domain.setResolvers(resolvers);
				domainBO.save(domain);

				showMessages(request, "default.save.ok", MESSAGE_WARNING);
			} catch (NamingException e) {
				showMessages(request, "error.resolver.failFindDNS",
						MESSAGE_ERROR);
			}
			loadLists(request, actionForm);
		} else {
			redir=FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}

	
	public ActionForward edit(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		
		DomainBO domainBO =  new DomainBO();
		DomainControlForm form = (DomainControlForm)actionForm;
		Domain domain =  form.getDomain();
		form.setDomain(domainBO.edit(domain.getId()));
		request.getSession().setAttribute("LIST_RESOLVERS", domain.getResolvers());
		loadLists(request, actionForm);
		return actionMapping.findForward(FORWARD_INITIAL);
	}
//TODO: O que este metodo faz???	
	public ActionForward list(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		
		DomainBO domainBO =  new DomainBO();
		DomainControlForm form = (DomainControlForm)actionForm;
		Domain domain =  form.getDomain();
		loadLists(request, actionForm);
		return actionMapping.findForward(FORWARD_EDIT);
	}
	
	public ActionForward delete(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws ExceptionSys {
		String redir = FORWARD_INITIAL;
		if (checkCredentials(request, new String[] { Role.GUEST_ROLE }, false)) {
			DomainBO domainBO = new DomainBO();
			DomainControlForm form = (DomainControlForm) actionForm;
			Domain domain = form.getDomain();
			domainBO.delete(domain.getId());
			String ctxId = domain.getContextId();
			domain.setId("");
			form.setDomain(new Domain());
			form.getDomain().setContextId(ctxId);

			loadLists(request, actionForm);
		} else {
			redir=FORWARD_FORBIDDEN;
		}
		return actionMapping.findForward(redir);
	}
	
	
	protected void loadLists(HttpServletRequest request, ActionForm actionForm){
		TrafficPolicyBO trafficPolicyBO =  new TrafficPolicyBO();
		DomainBO domainBO =  new DomainBO();
		DomainControlForm form = (DomainControlForm)actionForm;
		try {
			request.getSession().setAttribute("LIST_DOMAIN", domainBO.getDomainsByContextId(form.getDomain().getContextId()));
			request.getSession().setAttribute("LIST_TRAFFICPOLICY", trafficPolicyBO.listTraffic(form.getDomain().getContextId()));
		} catch (ExceptionSys e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
