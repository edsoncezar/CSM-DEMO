package br.com.alcatellucent.csm.beans;

import br.com.alcatellucent.csm.beans.common.CustomObject;
/**
 * 
 *@author Fernando Caruso Olívio & Edson Moreira Cezar
 *@date   01/08/2007 
 *@version 1.0
 *
 *@description : This class represents context on csm tree.
 */
public class Context extends CustomObject {
	private String  parentId;
	private Integer treeOrder;
	private String  trafficPolicyId;
	
	/**
	 * @return the id of a parent context 
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the treeOrder
	 */
	public Integer getTreeOrder() {
		return treeOrder;
	}

	/**
	 * @param treeOrder the treeOrder to set
	 */
	public void setTreeOrder(Integer treeOrder) {
		this.treeOrder = treeOrder;
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


	
}
