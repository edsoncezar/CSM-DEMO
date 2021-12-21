package br.com.alcatellucent.csm.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.beans.ProcessorPacket;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

/**
 * @author Fernando Caruso Olívio
 * 
 */
public class ProcessorPacketForm extends CommonForm {

	private static final long serialVersionUID = 7407875955746044280L;

	private ProcessorPacket processorPacket;

	/**
	 * @return the processorPacket
	 */
	public ProcessorPacket getProcessorPacket() {
		if (null == processorPacket) {
			this.processorPacket = new ProcessorPacket();
		}
		return processorPacket;
	}

	/**
	 * @param processorPacket
	 *           the processorPacket to set
	 */
	public void setProcessorPacket(ProcessorPacket processorPacket) {
		this.processorPacket = processorPacket;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		boolean emptyForm = true;

		// Verifica validade das informações digitadas conforme tipo do dado.
		if (((String) request.getParameter("action")).equals("save")) {
			if (this.getProcessorPacket().getName() != null && this.getProcessorPacket().getName() != "") {
				emptyForm = false;
				if (BasicValueCheck.isEmptyString(this.getProcessorPacket().getName())) {
					errors.add("processorForm.name", new ActionMessage("erro.pktForm.Name"));
				}
			}
			if (this.getProcessorPacket().getNumber() != 0) {
				emptyForm = false;
				if (this.getProcessorPacket().getNumber() <= 0) {
					errors.add("processorForm.number", new ActionMessage("erro.pktForm.Number"));
				} else if (this.getProcessorPacket().getNumber() <= 0) {
					errors.add("processorForm.number", new ActionMessage("erro.pktForm.Number"));
				}
			}
			Integer sampleThreshold = this.getProcessorPacket().getSampleThreshold();
			if (sampleThreshold < 0) {
				errors.add("processorForm.samplethreshold", new ActionMessage("erro.pktForm.SampleThreshold"));
			}
		}
		if (emptyForm) {
			return null;
		} else {
			return errors;
		}
	}

}
