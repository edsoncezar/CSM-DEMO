package br.com.alcatellucent.csm.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.beans.Port;
import br.com.alcatellucent.csm.beans.TraficValues;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

public class PortForm extends CommonForm {
	private static final long serialVersionUID = 1L;
	private Port port = new Port();
	private TraficValues traficValues = new TraficValues();

	public Port getPort() {
		if(null == port)
			port = new Port();
		return port;
	}

	public void setPort(Port port) {
		this.port = port;
	}
	
	public TraficValues getTraficValues() {
		
		if (null == traficValues) {
			traficValues = new TraficValues();
		}
				
		return traficValues;
	}

	public void setTraficValues(TraficValues traficValues) {
		this.traficValues = traficValues;
	}

	
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		
		if (((String) request.getParameter("action")).equals("save")) {
			if(BasicValueCheck.isEmptyString(this.getPort().getName())) {
				errors.add("portForm.nome",new ActionMessage("erro.portFormNome"));
			}
			
			if(BasicValueCheck.isEmptyString(this.getPort().getName())) {
				errors.add("portForm.nome",new ActionMessage("erro.portFormNome"));
			}
		
		
//			if(null == getPort().getName() || getPort().getName().trim().length() <= 0){
//				errors.add("portForm.nome",new ActionMessage("erro.portFormNome"));
//			}
		}
			
		if (errors.isEmpty()) { 
			return null;
		} else {
			return errors;
		}
					
		
	}

}