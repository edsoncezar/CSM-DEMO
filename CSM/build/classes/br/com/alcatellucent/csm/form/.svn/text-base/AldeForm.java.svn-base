package br.com.alcatellucent.csm.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import br.com.alcatellucent.csm.beans.Alde;
import br.com.alcatellucent.csm.beans.AldeConfiguration;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

/**
 * 
 * @author Edson Moreira Cezar
 * @date 08/09/2007
 * @version 1.0
 * 
 * @description : This class represents forms for AlDE.
 */
public class AldeForm extends CommonForm {

	private static final long serialVersionUID = 1342027569641025267L;

	private Alde alde = null;

	private AldeConfiguration aldeConfig = null;

	private String aba;

	public String getAba() {
		return aba;
	}

	public void setAba(String aba) {
		this.aba = aba;
	}

	/**
	 * @return the aldeConfig
	 */
	public AldeConfiguration getAldeConfig() {
		if (null == aldeConfig) {
			aldeConfig = new AldeConfiguration();
		}
		return aldeConfig;
	}

	/**
	 * @param aldeConfig
	 *           the aldeConfig to set
	 */
	public void setAldeConfig(AldeConfiguration aldeConfig) {
		this.aldeConfig = aldeConfig;
	}

	/**
	 * @return the alde
	 */
	public Alde getAlde() {
		if (null == alde) {
			this.alde = new Alde();
		}
		return alde;
	}

	/**
	 * @param alde
	 *           the alde to set
	 */
	public void setAlde(Alde alde) {
		this.alde = alde;
	}

	public void reset(ActionMapping map, HttpServletRequest req) {
		alde = new Alde();
		aldeConfig = new AldeConfiguration();
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = null;
		ActionErrors errorAlde = null;
		ActionErrors errorAldeConfig = null;
		if (request.getParameter("action").equalsIgnoreCase("save")
				|| request.getParameter("action").equalsIgnoreCase("applySettings")
				|| request.getParameter("action").equalsIgnoreCase("saveMaster")) {
			errors = new ActionErrors();
			errorAlde = new ActionErrors();
			// Verifica validade das informações digitadas conforme tipo do
			// dado.
			if (this.getAlde().getName() != null && this.getAlde().getName() != "") {
				if (BasicValueCheck.isEmptyString(this.getAlde().getName())) {
					errorAlde.add("aldeForm.name", new ActionMessage("erro.aldeForm.Name"));
				} else if (this.getAlde().getName().length() < 3) {
					errorAlde.add("aldeForm.namelength", new ActionMessage("erro.aldeForm.NameLength"));
				}
			}

			if (this.getAlde().getHost() != null && this.getAlde().getHost() != "") {
				if (BasicValueCheck.isEmptyString(this.getAlde().getHost())) {
					errorAlde.add("aldeForm.host", new ActionMessage("erro.aldeForm.Host"));
				}

			}

			if (this.getAlde().getUserName() != null && this.getAlde().getUserName() != "") {
				if (BasicValueCheck.isEmptyString(this.getAlde().getUserName())) {
					errorAlde.add("aldeForm.user", new ActionMessage("erro.aldeForm.User"));
				} else if (this.getAlde().getUserName().length() < 3) {
					errorAlde.add("aldeForm.userlength", new ActionMessage("erro.aldeForm.UserLength"));
				}
			}

			if (this.getAlde().getUserPass() != null && this.getAlde().getUserPass() != "") {
				if (BasicValueCheck.isEmptyString(this.getAlde().getUserPass())) {
					errorAlde.add("aldeForm.password", new ActionMessage("erro.aldeForm.Pass"));
				} else if (this.getAlde().getUserPass().length() < 6) {
					errorAlde.add("aldeForm.passlength", new ActionMessage("erro.aldeForm.PassLength"));
				}
			}

			if (this.getAlde().getMaster() == false) {
				errorAldeConfig = new ActionErrors();
				validateAldeConfig(errorAldeConfig);
				if (errorAldeConfig != null && !errorAldeConfig.isEmpty()) {
					request.setAttribute("ValidateFrom", "AldeConfig");
					errors.add(errorAldeConfig);
				}
			}

			if (errorAlde != null && !errorAlde.isEmpty()) {
				request.setAttribute("ValidateFrom", "Alde");
				errors.add(errorAlde);
			}

		}

		return errors;
	}

	private void validateAldeConfig(ActionErrors errors) {

//		System.out.println("Aba: " + this.getAba());

		if (this.getAldeConfig().getMode() != null && this.getAldeConfig().getMode() != "") {

			if (BasicValueCheck.isEmptyString(this.getAldeConfig().getMode())) {
				errors.add("aldeForm.mode", new ActionMessage("erro.aldeForm.Mode"));
			}

			if (this.getAldeConfig().getMode().trim().equals("1") || this.getAldeConfig().getMode().trim().equals("2")) {
				if (this.getAldeConfig().getBaselinetime() != 0 && this.getAldeConfig().getBaselinetime() != null) {
					int safelimit = 2678400 / (this.getAldeConfig().getBaselinetime());
					if (this.getAldeConfig().getSafepollmode() != null) {

						if (!BasicValueCheck.isNumericNoSignal(this.getAldeConfig().getSafepollmode().toString())) {
							errors.add("aldeForm.safeMode", new ActionMessage("erro.aldeForm.OldNet"));
						} else if (this.getAldeConfig().getSafepollmode() < 1
								|| this.getAldeConfig().getSafepollmode() > safelimit) {
							errors.add("aldeForm.safeMode", new ActionMessage("erro.aldeForm.SafeMode", safelimit));
						}
					}
				}

				if (this.getAldeConfig().getOldbaseline() != null) {

					if (!BasicValueCheck.isNumericNoSignal(this.getAldeConfig().getOldbaseline().toString())) {
						errors.add("aldeForm.oldBase", new ActionMessage("erro.aldeForm.OldBaseLine"));
					} else if (this.getAldeConfig().getOldbaseline() < 5 || this.getAldeConfig().getOldbaseline() > 95) {
						errors.add("aldeForm.oldBase", new ActionMessage("erro.aldeForm.OldBaseLine"));
					}
				}

				if (this.getAldeConfig().getBaselinetime() != null) {

					if (!BasicValueCheck.isNumericNoSignal(this.getAldeConfig().getBaselinetime().toString())) {
						errors.add("aldeForm.baseLine", new ActionMessage("erro.aldeForm.BaseLine"));
					} else if (this.getAldeConfig().getBaselinetime() < 10
							|| this.getAldeConfig().getBaselinetime() > 2678400) {
						errors.add("aldeForm.baseLine", new ActionMessage("erro.aldeForm.BaseLine"));
					}
				}
			}
			if (this.getAldeConfig().getMode().trim().equals("3") || this.getAldeConfig().getMode().trim().equals("4")) {
				this.getAldeConfig().setSafepollmode(0);
				this.getAldeConfig().setBaselinetime(0);
				this.getAldeConfig().setOldbaseline(0);
				this.getAldeConfig().setCurrentbaseline(0);
			}

		}

		if (this.getAldeConfig().getAttacktolerance() != null) {

			if (!BasicValueCheck.isNumericNoSignal(this.getAldeConfig().getAttacktolerance().toString())) {
				errors.add("aldeForm.attacktolerance", new ActionMessage("erro.aldeForm.Attacktolerance"));
			} else if (this.getAldeConfig().getAttacktolerance() < 2 || this.getAldeConfig().getAttacktolerance() > 10000) {
				errors.add("aldeForm.attacktolerance", new ActionMessage("erro.aldeForm.Attacktolerance"));
			}
		}

		if (this.getAldeConfig().getWaitalert() != null) {

			if (!BasicValueCheck.isNumericNoSignal(this.getAldeConfig().getWaitalert().toString())) {
				errors.add("aldeForm.wait", new ActionMessage("erro.aldeForm.Waitalert"));
			} else if (this.getAldeConfig().getWaitalert() < 1 || this.getAldeConfig().getWaitalert() > 100) {
				errors.add("aldeForm.wait", new ActionMessage("erro.aldeForm.Waitalert"));
			}
		}

		if (this.getAldeConfig().getSample() != null) {

			if (!BasicValueCheck.isNumericNoSignal(this.getAldeConfig().getSample().toString())) {
				errors.add("aldeForm.simple", new ActionMessage("erro.aldeForm.Simple"));
			} else if (this.getAldeConfig().getSample() < 1 || this.getAldeConfig().getSample() > 10000000) {
				errors.add("aldeForm.simple", new ActionMessage("erro.aldeForm.Simple"));
			}
		}

		if (this.getAldeConfig().getOldnet() != null) {

			if (!BasicValueCheck.isNumericNoSignal(this.getAldeConfig().getOldnet().toString())) {
				errors.add("aldeForm.oldnet", new ActionMessage("erro.aldeForm.OldNet"));
			} else if (this.getAldeConfig().getOldnet() < this.getAldeConfig().getBaselinetime()
					|| this.getAldeConfig().getOldnet() > 2678400) {
				errors.add("aldeForm.oldnet", new ActionMessage("erro.aldeForm.OldNet"));
			}
		}
		// If the configuration is dynamic, we need to validate the Min and Max
		// Adjustment, otherwise, accept all
		if (Integer.valueOf(this.getAldeConfig().getMode()) == AldeConfiguration.CONFIG_DYNAMIC) {
			if (this.getAldeConfig().getAdjustmentmin() != null) {

				if (!BasicValueCheck.isNumericNoSignal(this.getAldeConfig().getAdjustmentmin().toString())) {
					errors.add("aldeForm.adjMin", new ActionMessage("erro.aldeForm.AdjustMin"));
				} else if (this.getAldeConfig().getAdjustmentmin() < 1 || this.getAldeConfig().getAdjustmentmin() > 90) {
					errors.add("aldeForm.adjMin", new ActionMessage("erro.aldeForm.AdjustMin"));
				}
			}

			if (this.getAldeConfig().getAdjustmentmax() != null) {

				if (!BasicValueCheck.isNumericNoSignal(this.getAldeConfig().getAdjustmentmax().toString())) {
					errors.add("aldeForm.adjMax", new ActionMessage("erro.aldeForm.AdjustMax"));
				} else if (this.getAldeConfig().getAdjustmentmax() < 1
						|| this.getAldeConfig().getAdjustmentmax() > 10000000) {
					errors.add("aldeForm.adjMax", new ActionMessage("erro.aldeForm.AdjustMax"));
				}
			}
		}

		if (this.getAldeConfig().getCsmip() != null && this.getAldeConfig().getCsmip() != "") {

			if (BasicValueCheck.isEmptyString(this.getAldeConfig().getCsmip())) {
				errors.add("aldeForm.IP", new ActionMessage("erro.aldeForm.IP"));
			} else if (BasicValueCheck.isIP(this.getAldeConfig().getCsmip()) == false) {
				errors.add("aldeForm.IP", new ActionMessage("erro.aldeForm.IP"));
			}

		}

		if (this.getAldeConfig().getCsmport() != null) {

			if (!BasicValueCheck.isNumericNoSignal(this.getAldeConfig().getCsmport().toString())) {
				errors.add("aldeForm.port", new ActionMessage("erro.aldeForm.Port"));
			} else if (this.getAldeConfig().getCsmport() < 1 || this.getAldeConfig().getCsmport() > 65535) {
				errors.add("aldeForm.port", new ActionMessage("erro.aldeForm.Port"));
			}
		}
		
		if (this.getAldeConfig().getFilter() != null) {
			if (this.getAldeConfig().getFilter().length() > 255) {
				errors.add("aldeForm.filter", new ActionMessage("erro.aldeForm.Filter"));
			}
		}

	}
}
