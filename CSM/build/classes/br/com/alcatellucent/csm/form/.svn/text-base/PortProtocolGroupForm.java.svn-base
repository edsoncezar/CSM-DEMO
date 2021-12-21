package br.com.alcatellucent.csm.form;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
 
import br.com.alcatellucent.csm.beans.Port;
import br.com.alcatellucent.csm.beans.PortProtocolGroup;
import br.com.alcatellucent.csm.beans.Protocol;
import br.com.alcatellucent.csm.bo.ProtocolBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;
import br.com.alcatellucent.csm.form.common.CommonForm;
import br.com.alcatellucent.csm.utils.BasicValueCheck;

public class PortProtocolGroupForm extends CommonForm {
	private static final long serialVersionUID = 1L;
	
	private PortProtocolGroup portProtocolGroup;
	private Collection<Port> portList;
	private Collection<Protocol> protocolList;
	private String newPortList;
	private String newProtocolList;
	private Boolean flActive=false;
	
	private Collection<Port> avPorts;
	private Collection<Protocol> avProtocol;
	
	
	public PortProtocolGroup getPortProtocolGroup() {
		if(null == portProtocolGroup)
			this.portProtocolGroup =  new PortProtocolGroup();
		
		return portProtocolGroup;
	}

	public void setPortProtocolGroup(PortProtocolGroup portProtocolGroup) {
		this.portProtocolGroup = portProtocolGroup;
	}

	public Collection<Port> getPortList() {
		return portList;
	}

	public void setPortList(Collection<Port> portList) {
		this.portList = portList;
	}

	public Collection<Protocol> getProtocolList() {
		return protocolList;
	}

	public void setProtocolList(Collection<Protocol> protocolList) {
		this.protocolList = protocolList;
	}

	public String getNewPortList() {
		return newPortList;
	}

	public void setNewPortList(String newPortList) {
		this.newPortList = newPortList;
	}

	public String getNewProtocolList() {
		return newProtocolList;
	}

	public void setNewProtocolList(String newProtocolList) {
		this.newProtocolList = newProtocolList;
	}

	public Collection<Port> getAvPorts() {
		return avPorts;
	}

	public void setAvPorts(Collection<Port> avPorts) {
		this.avPorts = avPorts;
	}

	public Collection<Protocol> getAvProtocol() {
		return avProtocol;
	}

	public void setAvProtocol(Collection<Protocol> avProtocol) {
		this.avProtocol = avProtocol;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		boolean emptyForm = true;
	
//Verifica validade das informações digitadas conforme tipo do dado.
		if (((String) request.getParameter("action")).equals("save")) {
		if (this.getPortProtocolGroup().getName()!=null&&this.getPortProtocolGroup().getName()!=""){
			emptyForm = false;
			if(BasicValueCheck.isEmptyString(this.getPortProtocolGroup().getName())) {
				errors.add("portProtocolForm.name",new ActionMessage("erro.portProtocolForm.Name"));
			}
		}
		
		if (this.getNewProtocolList()!=null && this.getNewProtocolList()!="" ){
			emptyForm = false;
			retrievLists();
			if(this.getPortProtocolGroup().getProtocols().size()<1) {
				errors.add("portProtocolForm.protocol",new ActionMessage("erro.portProtocolForm.Protocol"));
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
			this.portProtocolGroup= new PortProtocolGroup();	
		}
		
	}
	
	/**
	 * Carrega as Listas com os novos protocolos e portas selecionadas no form
	 * 
	 */
	private void retrievLists() {

		ProtocolBO protocolBO = new ProtocolBO();
		Set<Protocol> newSetProtocol = new HashSet<Protocol>();
		String newProtocolList =this.getNewProtocolList();
		String[] newArrayProtocol = newProtocolList.split(",");

		try {
			for (String id : newArrayProtocol) {
				if(id!=null && !id.equals(""))
				newSetProtocol.add(protocolBO.edit(id));
			}
			if(!newSetProtocol.isEmpty()){
				this.getPortProtocolGroup().setProtocols(newSetProtocol);
			}else {
				this.getPortProtocolGroup().setProtocols(null);
			}
		} catch (ExceptionSys e) {
			e.printStackTrace();
		}

	}

	public Boolean getFlActive() {
		return flActive;
	}

	public void setFlActive(Boolean flActive) {
		this.flActive = flActive;
	}
	


}