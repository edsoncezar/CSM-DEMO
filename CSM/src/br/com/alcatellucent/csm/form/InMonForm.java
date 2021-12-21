package br.com.alcatellucent.csm.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.beans.InMon;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

/**
 * 
 * @author Edson Moreira Cezar
 * @date 09/03/2007
 * @version 1.0
 * 
 * @description : This class represents Form of InMon.
 */

public class InMonForm extends CommonForm {

    private static final long serialVersionUID = -7509516517237246068L;
    
    private InMon inMon = null;

    /**
     * @return the inMon
     */
    public InMon getInMon() {
	if (null == inMon) {
	    this.inMon = new InMon();
	}
	return inMon;
    }

    /**
     * @param inMon
     *                the inMon to set
     */
    public void setInMon(InMon inMon) {
	this.inMon = inMon;
    }

    public void clearForm() {
	this.inMon.clear();
    }

    public ActionErrors validate(ActionMapping mapping,
	    HttpServletRequest request) {

	ActionErrors errors = new ActionErrors();
	boolean emptyForm = true;

	// Verifica validade das informações digitadas conforme tipo do dado.
	if (((String) request.getParameter("action")).equals("save")) {
	    if (this.getInMon().getName() != null
		    && this.getInMon().getName() != "") {
		emptyForm = false;
		if (BasicValueCheck.isEmptyString(this.getInMon().getName())) {
		    errors.add("inmonForm.name", new ActionMessage(
			    "erro.inmonForm.Name"));
		}
	    }

	    if (this.getInMon().getUrl() != null
		    && this.getInMon().getUrl() != "") {
		emptyForm = false;
		if (BasicValueCheck.isEmptyString(this.getInMon().getUrl())) {
		    errors.add("inmonForm.URL", new ActionMessage(
			    "erro.inmonForm.URL"));
		} else if (BasicValueCheck.isURL(this.getInMon().getUrl()) == false) {
		    errors.add("inmonForm.URL", new ActionMessage(
			    "erro.inmonForm.URLCheck"));
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
