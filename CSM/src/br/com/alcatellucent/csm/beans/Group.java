package br.com.alcatellucent.csm.beans;

import br.com.alcatellucent.csm.beans.common.CustomObject;

// Checar se esta classe n�o � mais utilizada
// Creio que portprotocolgroup � a correta <Cristiano>
@Deprecated
public class Group extends CustomObject {
	
	private boolean active;
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	public void clear() {
		active = true;
	}
	
}
