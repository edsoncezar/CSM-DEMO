/**
 * 
 */
package br.com.alcatellucent.csm.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.beans.Operator;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

/**
 * 
 *@author Edson Moreira Cezar
 *@date   08/18/2007 
 *@version 1.0
 *
 */
public class OperatorForm extends CommonForm {
	
	private static final long serialVersionUID = 3304748189954167567L;
	
	private Operator operator;
	private String date;


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the operator
	 */
	public Operator getOperator() {
		if(null == operator)
			this.operator = new Operator();
		
		
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	
	public void clearForm(){
		
		this.operator.clear();
	}
	
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		boolean emptyForm = true;
	
//Verifica validade das informações digitadas conforme tipo do dado.
		if (((String) request.getParameter("action")).equals("save")) {
		if (this.getOperator().getName()!=null&&this.getOperator().getName()!=""){
			emptyForm = false;
			if(BasicValueCheck.isEmptyString(this.getOperator().getName())) {
				errors.add("operatorForm.name",new ActionMessage("erro.operatorForm.Name"));
			}
			else if(this.getOperator().getName().length()<3){
				errors.add("operatorForm.name",new ActionMessage("erro.operatorForm.NameLength"));
				}
		}
		
		if (this.getOperator().getMobile()!=null&&this.getOperator().getMobile()!=""){
			emptyForm = false;
			if(BasicValueCheck.isEmptyString(this.getOperator().getMobile())==false){
			if(BasicValueCheck.isMobile(this.getOperator().getMobile())==false){
				errors.add("operatorForm.mobile",new ActionMessage("erro.operatorForm.MobileLength"));
				}
			}
		}		

		if (this.getOperator().getEmail()!=null&&this.getOperator().getEmail()!=""){
			emptyForm = false;
			if(BasicValueCheck.isEmail(this.getOperator().getEmail())==false){
				errors.add("operatorForm.email",new ActionMessage("erro.operatorForm.InvalidEmail"));
			}
		}
		}

		if (emptyForm) { 
			return null;
		} else {
			return errors;
		}
	}
	
	@Override
	public void reset(ActionMapping map, HttpServletRequest req) {
		if (((String)req.getParameter("action")).equals("initial")) {
			this.operator = new Operator();
		}
		
	}
	

}
