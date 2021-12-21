package br.com.alcatellucent.csm.form;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.beans.Acl;
import br.com.alcatellucent.csm.beans.Protocol;
import br.com.alcatellucent.csm.bo.AclBO;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

/**
 * @author Edson
 */
public class AclForm extends CommonForm {

	private static final long serialVersionUID = 6602224312332906190L;

	private Acl acl = null;

	private Collection<Protocol> listProtocol = null;

	/**
	 * @return the acl
	 */
	public Acl getAcl() {
		if (null == acl) {
			acl = new Acl();
		}
		return acl;
	}

	/**
	 * @param acl
	 *           the acl to set
	 */
	public void setAcl(Acl acl) {
		this.acl = acl;
	}

	/**
	 * @return the listProtocol
	 */
	public Collection<Protocol> getListProtocol() {
		return listProtocol;
	}

	/**
	 * @param listProtocol
	 *           the listProtocol to set
	 */
	public void setListProtocol(Collection<Protocol> listProtocol) {
		this.listProtocol = listProtocol;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		boolean emptyForm = true;

		// Verifica validade das informações digitadas conforme tipo do dado.
		if (((String) request.getParameter("action")).equals("save")) {
			if (null != this.getAcl().getName() && this.getAcl().getName() != "") {
				emptyForm = false;
				if (BasicValueCheck.isEmptyString(this.getAcl().getName())) {
					errors.add("aclForm.name", new ActionMessage("erro.aclForm.Name"));
				} else if (this.getAcl().getName().length() < 3) {
					errors.add("acl.name", new ActionMessage("erro.aclForm.NameLength"));
				}
			}

			if (null != this.getAcl().getPriority()) {
				emptyForm = false;
				if (!BasicValueCheck.isNumericNoSignal(this.getAcl().getPriority().toString())) {
					errors.add("aclForm.priority", new ActionMessage("erro.aclForm.Priority"));
				} else if ((!this.getAcl().getIsAldeAcl() && this.getAcl().getPriority() < 10)
						|| (this.getAcl().getIsAldeAcl() && this.getAcl().getPriority() >= 10)) {
					errors.add("aclForm.priority", new ActionMessage("erro.aclForm.PriorityLength"));
				}
			}

			if (null != this.getAcl().getStatus()) {
				emptyForm = false;
				if (this.getAcl().getStatus() != 1 && this.getAcl().getStatus() != 2 && !this.getAcl().getIsAldeAcl()) {
					errors.add("aclForm.priority", new ActionMessage("erro.aclForm.Status"));
				}
			}

			if (null != this.getAcl().getSourceIp() && this.getAcl().getSourceIp() != "") {
				emptyForm = false;
				if (BasicValueCheck.isEmptyString(this.getAcl().getSourceIp())) {
					errors.add("aclForm.sourceIP", new ActionMessage("erro.aclForm.SourceIP"));
				} else if (acl.getSourceIp().equals("IPSPOOF")) {
					this.acl.setSourceIp("0.0.0.0");
				} else if (BasicValueCheck.isIP(this.getAcl().getSourceIp()) == false) {
					errors.add("aclForm.sourceIP", new ActionMessage("erro.aclForm.SourceIPCheck"));
				}
			}

			if (null != this.getAcl().getSourceIpMask() && this.getAcl().getSourceIpMask() != "") {
				emptyForm = false;
				if (BasicValueCheck.isEmptyString(this.getAcl().getSourceIpMask())) {
					errors.add("aclForm.maskIP", new ActionMessage("erro.aclForm.MaskIP"));
				} else if (BasicValueCheck.isIP(this.getAcl().getSourceIpMask()) == false) {
					errors.add("aclForm.maskIP", new ActionMessage("erro.aclForm.MaskIPCheck"));
				}
			}

			if (null != this.getAcl().getDestIp() && this.getAcl().getDestIp() != "") {
				emptyForm = false;
				if (BasicValueCheck.isEmptyString(this.getAcl().getDestIp())) {
					errors.add("aclForm.destIP", new ActionMessage("erro.aclForm.DestIP"));
				} else if (BasicValueCheck.isIP(this.getAcl().getDestIp()) == false) {
					errors.add("aclForm.maskIP", new ActionMessage("erro.aclForm.DestIPCheck"));
				}
			}

			if (null != this.getAcl().getDestIpMask() && this.getAcl().getDestIpMask() != "") {
				emptyForm = false;
				if (BasicValueCheck.isEmptyString(this.getAcl().getDestIpMask())) {
					errors.add("aclForm.destIP", new ActionMessage("erro.aclForm.DestMaskIP"));
				} else if (BasicValueCheck.isIP(this.getAcl().getDestIpMask()) == false) {
					errors.add("aclForm.maskIP", new ActionMessage("erro.aclForm.DestMaskIPCheck"));
				}
			}

			if (this.getAcl().getProtocol() != null && this.getAcl().getProtocol() != "") {
				emptyForm = false;
				if (BasicValueCheck.isEmptyString(this.getAcl().getProtocol())) {
					errors.add("aclForm.protocol", new ActionMessage("erro.aclForm.protocol"));
				}
			}
			if (this.getAcl().getThreshold() != null
					&& (this.getAcl().getThreshold() < AclBO.SHAPE_MIN_VALUE || this.getAcl().getThreshold() > AclBO.SHAPE_MAX_VALUE)) {
				errors.add("aclForm.threshold", new ActionMessage("erro.aclForm.threshold"));
			}
		}

		if (emptyForm) {
			return null;
		} else {
			if (errors.size() > 0) {
				if (this.acl.getSourceIp().equals("0.0.0.0")) {
					this.acl.setSourceIp("IPSPOOF");
				}
			}
			return errors;
		}
	}

	@Override
	public void reset(ActionMapping map, HttpServletRequest req) {
		if (((String) req.getParameter("action")).equals("initial")) {
			this.acl = new Acl();
		}
	}

}
