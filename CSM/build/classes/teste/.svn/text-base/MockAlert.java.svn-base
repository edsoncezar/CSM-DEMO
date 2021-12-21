package teste;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import br.com.alcatellucent.csm.beans.Alert;
import br.com.alcatellucent.csm.bo.AlertBO;

public class MockAlert {
	
	
	public static void main(String[] args) {
		
		
		HashMap  map = new HashMap();
		
		//
		// 1190726449198
		// 1190640000000
		
		try{
			AlertBO  alertBO = new AlertBO();	
			Alert alert = new Alert();
			
			alert.setDescription("Attack 02");						
			alert.setMessageType(1);	
								
			
			Calendar c1 = new GregorianCalendar();			
			c1.set(Calendar.DAY_OF_MONTH,1 );
			c1.set(Calendar.HOUR_OF_DAY ,10);
			c1.set(Calendar.MINUTE , 25 );
			
			alert.setTimeStampMessage(c1.getTimeInMillis());
			alert.setId("");
			alert.setRule("DROP");
			alert.setSourceIp("85.128.137.29");
			alert.setDestinationPort(8080);			
			alert.setProtocol(1);
			alert.setDestinationIp("65.203.252.13");
			alert.setStatus(0);
			alert.setAttackType(2);
			alert.setCsmUser("e2c4af62-9e1e-102a-a276-0019b9ccd856");	
			alert.setAlde("LDE1");		
			alertBO.save(alert);	
			
		} catch(Exception e){
			e.printStackTrace();
		}	
	}	

}
