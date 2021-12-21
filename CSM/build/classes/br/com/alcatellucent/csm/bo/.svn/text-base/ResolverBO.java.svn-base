package br.com.alcatellucent.csm.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.Domain;
import br.com.alcatellucent.csm.beans.Resolver;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.persistence.hibernate.HbCommonDAO;

public class ResolverBO {
	private static final Logger logger = Logger.getLogger(ResolverBO.class);

	private HbCommonDAO<Resolver> resolverDAO;

	public ResolverBO() {
		logger.debug("Loading ResolverBO..");
		resolverDAO = new HbCommonDAO<Resolver>(Resolver.class);
	}

	@SuppressWarnings("unchecked")
	public void save(Resolver resolver) throws ExceptionSys {

		try {
			if (!resolver.getId().equals("")) {
				resolverDAO.update(resolver);
				logger.info("Modify information of resolver .." + resolver.getName());
			} else {
				resolverDAO.save(resolver);
				logger.info("Save information of resolver .. " 	+ resolver.getName());
			}
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void delete(String id) throws ExceptionSys {
		try {
			Resolver resolver = (Resolver) resolverDAO.findById(id);
			resolverDAO.delete(resolver);
			logger.info("Delete information of resolver .." + id);
		} catch (Exception e) {
			logger.info("ERROR " + e);
			throw new ExceptionSys(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<Resolver> list() throws ExceptionSys {
		Collection<Resolver> listResolver = new ArrayList<Resolver>();
		listResolver = resolverDAO.findAll();
		return listResolver;
	}

	public Resolver edit(String id) throws ExceptionSys {
		Resolver resolver = (Resolver) resolverDAO.findById(id);
		return resolver;
	}

	@SuppressWarnings("unchecked")
	public Collection<Resolver> findByDomain(String domainId)		throws ExceptionSys {

		try {
			Properties criterios = new Properties();
			criterios.put("domain.id", domainId);
			Collection cResolver = (Collection<Resolver>) resolverDAO.findByCriteria(Resolver.class, criterios);
			return cResolver;
		} catch (ExceptionSys e) {
			logger.error("Error finding Resolver by Domain ID ..." + e);
			throw new ExceptionSys(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Resolver> findByDomain(String domainId, Boolean type)	throws ExceptionSys {
		
		try {
			Properties criterios = new Properties();
			criterios.put("domain.id", domainId);
			criterios.put("isSourceAddress", domainId);
			Collection<Resolver> cResolver = (Collection<Resolver>) resolverDAO.findByCriteria(Resolver.class, criterios);
			return cResolver;
		} catch (ExceptionSys e) {
			logger.error("Error finding Resolver by Domain ID ..." + e);
			throw new ExceptionSys(e);
		}
	}


	public void deleteByDomainId(String domainId) throws ExceptionSys {
		Collection<Resolver> cResolver = findByDomain(domainId);
		for (Resolver resolver : cResolver) {
			delete(resolver.getId());
		}
	}
	/**
	 * This methods resolves and saves 
	 * @param domain
	 * @throws NamingException 
	 * @throws ExceptionSys 
	 */
	public HashSet<Resolver> getResolvedAddress(Domain domain, String timeout, String retries) throws NamingException, ExceptionSys{
		HashSet<Resolver> cResolver = new HashSet<Resolver>();
		Hashtable<String, String> env = new Hashtable<String, String>();                
		env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");                
		env.put("com.sun.jndi.dns.timeout.initial", timeout);                
		env.put("com.sun.jndi.dns.timeout.retries", retries);                
		InitialDirContext ctx = new InitialDirContext(env);
		
		
		Attributes res;  
		Resolver actualResolver;
		String[] ips;
		// Pegando os atributos dos sources
		try {
			if(domain.getAnySource()){
				actualResolver = new Resolver();
				actualResolver.setDomain(domain);
				actualResolver.setIpAddress(Resolver.ANY_MASK);
				actualResolver.setIsSourceAddress(true);
				cResolver.add(actualResolver);
				//caso se any não pode ter destinationDomain
				domain.setSourceDomain("");
			}
			if(null != domain.getSourceDomain() && !domain.getSourceDomain().trim().equals("")){
				res = ctx.getAttributes(domain.getSourceDomain(), new String[] { "A" });  
				ips = res.get(res.getIDs().next()).toString().replace("A:", "").split(",");
				for(String ip: ips){
					actualResolver = new Resolver();
					actualResolver.setDomain(domain);
					actualResolver.setIpAddress(ip);
					actualResolver.setIsSourceAddress(true);
					cResolver.add(actualResolver);
				}
			}

		} catch (RuntimeException e) {
			logger.error("Error Resolving Source Domain: " + e);
			throw new ExceptionSys(e.getMessage());
		}
		
		
		try {
			// Pegando os atributos dos destinations
			if(domain.getAnyDestination()){
				actualResolver = new Resolver();
				actualResolver.setDomain(domain);
				actualResolver.setIpAddress(Resolver.ANY_MASK);
				actualResolver.setIsSourceAddress(false);
				cResolver.add(actualResolver);
				//caso se any não pode ter destinationDomain
				domain.setDestinationDomain("");
			}
			if(null != domain.getDestinationDomain() && !domain.getDestinationDomain().trim().equals("")){
				res = ctx.getAttributes(domain.getDestinationDomain(), new String[] { "A" });  
				ips = res.get(res.getIDs().next()).toString().replace("A:", "").split(",");
				for(String ip: ips){
					actualResolver = new Resolver();
					actualResolver.setDomain(domain);
					actualResolver.setIpAddress(ip);
					actualResolver.setIsSourceAddress(false);
					cResolver.add(actualResolver);
				}
			}

		} catch (RuntimeException e) {
			logger.error("Error Resolving Destination Domain: " + e);
			throw new ExceptionSys(e.getMessage());
		}
		return cResolver;
	}

}
