package br.com.alcatellucent.csm.beans;

import br.com.alcatellucent.csm.beans.common.CustomObject;

public class AldeAlertsHours extends CustomObject {

    private String bandIni;
    private String bandEnd;
    private Integer count;
    private String role;

    public String getBandIni() {
	return bandIni;
    }

    public void setBandIni(String bandIni) {
	this.bandIni = bandIni;
    }

    public String getBandEnd() {
	return bandEnd;
    }

    public void setBandEnd(String bandEnd) {
	this.bandEnd = bandEnd;
    }

    public Integer getCount() {
	return count;
    }

    public void setCount(Integer count) {
	this.count = count;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

}
