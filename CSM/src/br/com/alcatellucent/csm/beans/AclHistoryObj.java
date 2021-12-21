package br.com.alcatellucent.csm.beans;

import br.com.alcatellucent.csm.bo.AclHistory;

/**
 * @author Igor Ivanoff Takats
 * 
 * This Java Bean is a custom object for Acl history Search
 */
public class AclHistoryObj {

    private Acl acl;

    private AclHistory aclHistory;

    private Context context;

    private ProcessorPacket processorPacket;

    private Alert alert;

    private User user;

    public AclHistory getAclHistory() {
	return aclHistory;
    }

    public void setAclHistory(AclHistory aclHistory) {
	this.aclHistory = aclHistory;
    }

    public Context getContext() {
	return context;
    }

    public void setContext(Context context) {
	this.context = context;
    }

    public ProcessorPacket getProcessorPacket() {
	return processorPacket;
    }

    public void setProcessorPacket(ProcessorPacket processorPacket) {
	this.processorPacket = processorPacket;
    }

    public Alert getAlert() {
	return alert;
    }

    public void setAlert(Alert alert) {
	this.alert = alert;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public Acl getAcl() {
	return acl;
    }

    public void setAcl(Acl acl) {
	this.acl = acl;
    }

}
