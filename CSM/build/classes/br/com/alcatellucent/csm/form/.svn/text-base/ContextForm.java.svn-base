package br.com.alcatellucent.csm.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.beans.Context;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

/**
 * @author fernando
 * 
 */
public class ContextForm extends CommonForm {

    private static final long serialVersionUID = 7796195746822084054L;
    
    Context context = null;

    /**
     * @return the context
     */
    public Context getContext() {
	if (null == context) {
	    context = new Context();
	}
	return context;
    }

    /**
     * @param context
     *                the context to set
     */
    public void setContext(Context context) {
	this.context = context;
    }

    public void reset() {
	String parentId = context.getParentId();
	context = new Context();
	context.setParentId(parentId);
    }

    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {

	ActionErrors errors = new ActionErrors();
	boolean emptyForm = true;

	// Verifica validade das informações digitadas conforme tipo do dado.
	if (((String) request.getParameter("action")).equals("save")) {
	    if (this.getContext().getName() != null
		    && this.getContext().getName() != "") {
		emptyForm = false;
		if (BasicValueCheck.isEmptyString(this.getContext().getName())) {
		    errors.add("contextForm.name", new ActionMessage(
			    "erro.ContextForm.Name"));
		}
		
		if (this.getContext().getParentId().equalsIgnoreCase("0")) {
		    errors.add("contextForm.ROOT", new ActionMessage(
			    "erro.ContextForm.ROOT"));
		}
	    }
	}

	if (emptyForm) {
	    return null;
	} else {
	    return errors;
	}
    }
}
