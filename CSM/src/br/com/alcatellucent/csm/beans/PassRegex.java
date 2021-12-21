package br.com.alcatellucent.csm.beans;

import br.com.alcatellucent.csm.beans.common.CustomObject;

public class PassRegex extends CustomObject{
	
	private String literal;
	
	private Boolean isRegExp;
	
	private Boolean isRefused;
	
	private Boolean isSystem;

	public PassRegex() {
		
		//Initialize fields
		this.isRefused=false;
		this.isRegExp=false;
		this.isSystem=false;
	}
	
	public String getLiteral() {
		return literal;
	}

	public void setLiteral(String literal) {
		this.literal = literal;
	}

	public Boolean getIsRegExp() {
		
		return isRegExp;
	}

	public void setIsRegExp(Boolean isRegExp) {
		
		if (null == isRegExp) {
			isRegExp = false;
		}
		this.isRegExp = isRegExp;
	}

	public Boolean getIsRefused() {
		return isRefused;
	}

	public void setIsRefused(Boolean isRefused) {
		
		this.isRefused = (null==isRefused ? false : isRefused);

	}

	public Boolean getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Boolean isSystem) {
		
		this.isSystem = (null == isSystem ? false : isSystem);

	}
	
}
