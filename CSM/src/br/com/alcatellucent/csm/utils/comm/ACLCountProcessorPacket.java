package br.com.alcatellucent.csm.utils.comm;

import java.util.ArrayList;
import java.util.List;

import br.com.alcatellucent.csm.beans.AclRecipient;

public class ACLCountProcessorPacket {

	public static final int MAX_COUNT = 4;

	protected long aclCountValues[][] = null;
	
	protected long tempAclCountValues[][] = null;

	protected long aclCountTimestamp[] = null;

	private String dppmId = null;

	private int number;

	private List<AclRecipient> aclList = null;

	private List<AclRecipient> aclListToBeApplied = null;

	private boolean updated = false;

	private int atualCounter = 0;

	public ACLCountProcessorPacket(int aclListSize, int aclListToApply) {
		int size = (aclListSize > aclListToApply) ? aclListSize : aclListToApply;
		aclCountValues = new long[MAX_COUNT][size];
		aclCountTimestamp = new long[MAX_COUNT];
		aclListToBeApplied = new ArrayList<AclRecipient>();
	}

	/**
	 * @return the atualCounter
	 */
	public int getAtualCounter() {
		return atualCounter;
	}

	/**
	 * @param atualCounter
	 *           the atualCounter to set
	 */
	public void addAtualCounter() {
		this.atualCounter = ((this.atualCounter + 1) % MAX_COUNT);
	}

	/**
	 * @return the updated
	 */
	public boolean isUpdated() {
		return updated;
	}

	/**
	 * @param updated
	 *           the updated to set
	 */
	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

	/**
	 * @return the dppmId
	 */
	public String getDppmId() {
		return dppmId;
	}

	/**
	 * @param dppmId
	 *           the id to set
	 */
	public void setDppmId(String dppmId) {
		this.dppmId = dppmId;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number
	 *           the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the aclList
	 */
	public List<AclRecipient> getAclList() {
		return aclList;
	}

	/**
	 * @return
	 */
	public String[][] getNewCounterArray() {
		// int counterSize = (aclListToBeApplied.size() > aclList.size()) ?
		// aclListToBeApplied.size() : aclList.size();
		String[][] counter = new String[aclListToBeApplied.size()][2];
		tempAclCountValues = new long[MAX_COUNT][aclListToBeApplied.size()];
		int i, j, k;
		// i: new index, j: old index, k: all counters (time shift)
		for (i = 0; i < aclListToBeApplied.size(); i++) {
			for (j = 0; j < aclList.size(); j++) {
				if (aclList.get(j).getAcl().getId().equals(aclListToBeApplied.get(i).getAcl().getId())) {
					for (k = 0; k < MAX_COUNT; k++) {
						tempAclCountValues[k][i] = aclCountValues[k][j];
					}
				}
			}
		}
		for (i = 0; i < aclListToBeApplied.size(); i++) {
			counter[i][0] = String.valueOf(tempAclCountValues[atualCounter][i] & 0x00000000ffffffffL);
			counter[i][1] = String.valueOf(tempAclCountValues[atualCounter][i] & 0xffffffff00000000L);
		}
		return counter;
	}

	public synchronized void setAclListToBeApplied(List<AclRecipient> aclListToApply) {
		synchronized (aclListToBeApplied) {
			if (aclListToApply == null) {
				aclListToBeApplied = new ArrayList<AclRecipient>();
			} else {
				aclListToBeApplied = aclListToApply;
			}
		}
	}

	/**
	 * @return the aclListToBeApplied
	 */
	public List<AclRecipient> getAclListToBeApplied() {
		return aclListToBeApplied;
	}

	public void setApplied() {
		synchronized (this) {
			updated = false;
			aclList = aclListToBeApplied;
			aclListToBeApplied = new ArrayList<AclRecipient>();
			// aclCountValues[atualCounter] = new long[newCounter.length];
			aclCountValues = tempAclCountValues;
		}
	}

	/**
	 * @param aclList
	 *           the aclList to set
	 */
	public void setAclList(List<AclRecipient> aclList) {
		this.aclList = aclList;
	}

}