package br.com.alcatellucent.csm;

import java.util.ResourceBundle;

public class SystemInformation {
/**
 * This class return information about the version installed
 * for a while, the only information returned will be the
 * version number
 * 
 * @author Igor I. Takats	
 * 
 */
	
  private String version;

  public SystemInformation() {
	  
	  try {
		  ResourceBundle bundle = ResourceBundle.getBundle("agya_version");
		  this.setVersion(bundle.getString("current_version"));
		  
	  } catch(Exception e) {
		  this.setVersion("0.0.0");
	  }
	  
  }
  
  public String getVersion() {
	  return this.version;
  }

  public void setVersion(String version) {
	  this.version = version;
  }
}
