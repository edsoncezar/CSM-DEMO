package br.com.alcatellucent.csm.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.beans.Protocol;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

public class ProtocolForm extends CommonForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3657558851155710298L;
	
	private Protocol protocol = new Protocol();


    public Protocol getProtocol() {

	if (null == protocol) {
	    protocol = new Protocol();
	}
	return protocol;

    }

    public void setProtocol(Protocol protocol) {
	this.protocol = protocol;
    }


    @Override
    public void reset(ActionMapping map, HttpServletRequest req) {

	this.protocol = new Protocol();
    }


    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {

	if (request.getParameter("action").equals("save")) {

	    ActionErrors errors = new ActionErrors();
	    boolean emptyForm = true;

	    // Verifica validade das informações digitadas conforme tipo do
	    // dado.
	    //		
	    if (this.getProtocol().getName() != null) {
		emptyForm = false;
		if (BasicValueCheck.isEmptyString(this.getProtocol().getName())) {
		    errors.add("portForm.nome", new ActionMessage(
			    "erro.protocolForm.Name"));
		}
	    }

	    if (this.getProtocol().getName() != null) {
		emptyForm = false;
		if (BasicValueCheck.isEmptyString(this.getProtocol()
			.getDescription())) {
		    errors.add("portForm.description", new ActionMessage(
			    "erro.protocolForm.Description"));
		}
	    }

	    if (this.getProtocol().getFlowsValues() != null) {
		emptyForm = false;
		if (this.getProtocol().getFlowsValues() < 0) {
		    errors.add("portForm.flowsValues", new ActionMessage(
			    "erro.protocolForm.Flows"));
		}
	    }

	    if (this.getProtocol().getDownStreamValue() != null) {
		emptyForm = false;
		if (Integer.parseInt(this.getProtocol().getDownStreamValue()) < 0) {
		    errors.add("portForm.downStreamValue", new ActionMessage("erro.protocolForm.DownStream"));
		}
	    }

	    if (this.getProtocol().getDownStreamValue() != null) {
		emptyForm = false;
		if (BasicValueCheck.isEmptyString(this.getProtocol().getDownStreamUnit())) {
		    errors.add("portForm.downStreamUnit", new ActionMessage("erro.protocolForm.DownStreamUnit"));
		}
	    }

	    if (this.getProtocol().getUpStreamValue() != null) {
		emptyForm = false;
		if (Integer.parseInt(this.getProtocol().getUpStreamValue()) < 0) {
		    errors.add("portForm.upStreamValue", new ActionMessage("erro.protocolForm.UpStream"));
		}
	    }

	    if (this.getProtocol().getUpStreamValue() != null) {
		emptyForm = false;
		if (BasicValueCheck.isEmptyString(this.getProtocol().getUpStreamUnit())) {
		    errors.add("portForm.upStreamUnit", new ActionMessage("erro.protocolForm.UpStreamUnit"));
		}
	    }
	    
	    if (this.getProtocol().getControl()!= null) {
			emptyForm = false;
			if (BasicValueCheck.isEmptyString(this.getProtocol().getControl())) {
			    errors.add("portForm.control", new ActionMessage("erro.protocolForm.control"));
			}
		    }
	    
	    if (this.getProtocol().getPriority()!= null) {
			emptyForm = false;
			if (BasicValueCheck.isEmptyString(this.getProtocol().getPriority().toString())) {
			    errors.add("portForm.priority", new ActionMessage("erro.protocolForm.priority.notNumber"));
			} else if (this.getProtocol().getPriority() < 0 || this.getProtocol().getPriority() > 63) {
				errors.add("portForm.priority", new ActionMessage("erro.protocolForm.priority.notNumber"));
			}
		    }
	    //
	    // Fim Verifica validade;
	    //		
	    if (emptyForm) {
		return null;
	    } else {
		return errors;
	    }
	}
	return null;
    }

}