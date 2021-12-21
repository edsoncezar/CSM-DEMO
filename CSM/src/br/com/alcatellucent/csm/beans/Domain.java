package br.com.alcatellucent.csm.beans;

import java.util.HashSet;
import java.util.Set;

import br.com.alcatellucent.csm.beans.common.CustomObject;

public class Domain extends CustomObject {
	public static final String ANY_DOMAIN = "ANY_DOMAIN"; 
	
	private Set<Resolver> resolvers;
	
	private String  contextId;
	private String  destinationDomain;
	private String  sourceDomain;
	private String  trafficPolicyId;
	
	private Boolean anyDestination = false;
	private Boolean anySource	  = false;
	/**
	 * @return the resolvers
	 */
	public Set<Resolver> getResolvers() {
		if(null == resolvers)
			resolvers = new HashSet<Resolver>();
		return resolvers;
	}

	/**
	 * @param resolvers the resolvers to set
	 */
	public void setResolvers(Set<Resolver> resolvers) {
		this.resolvers = resolvers;
	}

	/**
	 * @return the contextId
	 */
	public String getContextId() {
		return contextId;
	}

	/**
	 * @param contextId the contextId to set
	 */
	public void setContextId(String contextId) {
		this.contextId = contextId;
	}

	/**
	 * @return the destinationDomain
	 */
	public String getDestinationDomain() {
		return destinationDomain;
	}

	/**
	 * @param destinationDomain the destinationDomain to set
	 */
	public void setDestinationDomain(String destinationDomain) {
		this.destinationDomain = destinationDomain;
	}

	/**
	 * @return the sourceDomain
	 */
	public String getSourceDomain() {
		return sourceDomain;
	}

	/**
	 * @param sourceDomain the sourceDomain to set
	 */
	public void setSourceDomain(String sourceDomain) {
		this.sourceDomain = sourceDomain;
	}

	/**
	 * @return the trafficPolicyId
	 */
	public String getTrafficPolicyId() {
		return trafficPolicyId;
	}

	/**
	 * @param trafficPolicyId the trafficPolicyId to set
	 */
	public void setTrafficPolicyId(String trafficPolicyId) {
		this.trafficPolicyId = trafficPolicyId;
	}


	/**
	 * @return the anyDestination
	 */
	public Boolean getAnyDestination() {
		return anyDestination;
	}

	/**
	 * @param anyDestination the anyDestination to set
	 */
	public void setAnyDestination(Boolean anyDestination) {
		this.anyDestination = anyDestination;
	}

	/**
	 * @return the anySource
	 */
	public Boolean getAnySource() {
		return anySource;
	}

	/**
	 * @param anySource the anySource to set
	 */
	public void setAnySource(Boolean anySource) {
		this.anySource = anySource;
	}


	
}
