package br.com.alcatellucent.csm.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.alcatellucent.csm.beans.AldeAlertsHours;
import br.com.alcatellucent.csm.beans.Alert;
import br.com.alcatellucent.csm.exception.ExceptionSys;

public class DashBoardChartBO {
	
	  private static final Logger logger = Logger.getLogger(DashBoardChartBO.class);
	  private AlertBO alertBO = null;	  
	  

	  public DashBoardChartBO() {
		logger.debug("Carregando DashBoardChartBO...");		
		alertBO = new AlertBO();
	  }
	  
	  @SuppressWarnings("unchecked")
	  public   HashMap<String, AldeAlertsHours> chartLast24Hours(Date date) {
		   
	     HashMap<String, AldeAlertsHours>  mLast24Hours = new HashMap();
	     try{
		     for (Alert alert : alertBO.getActiveAlerts(date,0 ) ) {
		    	 java.sql.Timestamp ts = new java.sql.Timestamp(Long.valueOf( alert.getTimeStampMessage()  ));		    	  
         	     
		    	 SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");		    	 
		    	 String timeStampMessage = String.valueOf(formater.format(ts)); 
		    	 Integer hourMessage = Integer.valueOf( timeStampMessage.substring( 11 , 13 ) );
		    	 Integer minuteMessage = Integer.valueOf( timeStampMessage.substring( 14 , 16 ) );  
		        	             	     
		    	 for (AldeAlertsHours hours : this.getHours()) {
		    		 Integer hrIni = Integer.valueOf( hours.getBandIni().substring(0, 2) )  ;
		    		 Integer hrEnd = Integer.valueOf( hours.getBandEnd().substring(0, 2) )  ; 
		    		 if(  hourMessage >=  hrIni &&  hourMessage <= hrEnd   ) {
		    			 String key  = hours.getId()+alert.getRule();		    			 
		    			 AldeAlertsHours mapTemp = (AldeAlertsHours)  mLast24Hours.get(key);
    			    	 int x = ( null == mapTemp ? 1 : mapTemp.getCount()+1   );    			    	    			    	     			    	 
    			    	 hours.setCount( x );
    			    	 hours.setRole( alert.getRule() );
		    			 mLast24Hours.put(key, hours);
		    		 }	 	
				 }	    	 
		     }   
	     } catch (ExceptionSys e) {
	 	    e.printStackTrace();
	 	 }		 
	  
		 return mLast24Hours;
	  }  
	  
	  @SuppressWarnings("unchecked")
	  public   HashMap<String, AldeAlertsHours> chartLastMonth(Date date) {
		   
	     HashMap<String, AldeAlertsHours>  mLastMonth= new HashMap();
	     try{
		     for (Alert alert : alertBO.getActiveAlertsMonth(date) ) {
		    	 java.sql.Timestamp ts = new java.sql.Timestamp(Long.valueOf( alert.getTimeStampMessage()));		    	  
		    	 String timeStampMessage = String.valueOf(ts); 
		    	 Calendar c1 = new GregorianCalendar();
		    	 Date dt = new Date(Integer.valueOf(timeStampMessage.substring(0 ,4))-1900,Integer.valueOf(timeStampMessage.substring(5 ,7 ))-1,Integer.valueOf(timeStampMessage.substring(8 ,10 )));
		    	 c1.setTime(dt);
		    	 int weekMessage=c1.get(c1.WEEK_OF_MONTH);
		    	 for (AldeAlertsHours weeks : this.getWeeks()) {
		    		 Integer wkIni = Integer.valueOf(weeks.getBandIni());
		    		 Integer wkEnd = Integer.valueOf(weeks.getBandEnd())  ; 
		    		 if(  weekMessage >=  wkIni &&  weekMessage < wkEnd   ) {
		    			 String key  = weeks.getId()+alert.getRule();		    			 
		    			 AldeAlertsHours mapTemp = (AldeAlertsHours)  mLastMonth.get(key);
    			    	 int x = ( null == mapTemp ? 1 : mapTemp.getCount()+1  );    			    	    			    	     			    	 
    			    	 weeks.setCount( x );
    			    	 weeks.setRole( alert.getRule() );
    			    	 mLastMonth.put(key, weeks);
		    		 }	 	
				 }	    	 
		     }   
	     } catch (ExceptionSys e) {
	 	    e.printStackTrace();
	 	 }		 
	  
		 return mLastMonth;
	  }  
	  
	  /*
	   * TODO : DashBoard
	   */
	  public List<AldeAlertsHours> getHours() {
			
		    List<AldeAlertsHours> cHoursTemp = new ArrayList<AldeAlertsHours>();
			
			AldeAlertsHours aldeAlertsHours = new AldeAlertsHours();		
			aldeAlertsHours.setId("1");		
			aldeAlertsHours.setName("00:00 AM to 03:59 AM");
			aldeAlertsHours.setBandIni("00:00 AM");
			aldeAlertsHours.setBandEnd("03:59 AM");
			cHoursTemp.add(aldeAlertsHours);
			
			aldeAlertsHours = new AldeAlertsHours();
			aldeAlertsHours.setId("2");		
			aldeAlertsHours.setName("04:00 AM to 07:59 AM");
			aldeAlertsHours.setBandIni("04:00 AM");
			aldeAlertsHours.setBandEnd("07:59 AM");
			cHoursTemp.add(aldeAlertsHours);
			
			aldeAlertsHours = new AldeAlertsHours();
			aldeAlertsHours.setId("3");		
			aldeAlertsHours.setName("08:00 AM to 11:59 AM");
			aldeAlertsHours.setBandIni("08:00 AM");
			aldeAlertsHours.setBandEnd("11:59 AM");
			cHoursTemp.add(aldeAlertsHours);
			
			aldeAlertsHours = new AldeAlertsHours();
			aldeAlertsHours.setId("4");		
			aldeAlertsHours.setName("12:00 to 15:59 PM");
			aldeAlertsHours.setBandIni("12:00");
			aldeAlertsHours.setBandEnd("15:59 PM");
			cHoursTemp.add(aldeAlertsHours);
			
			aldeAlertsHours = new AldeAlertsHours();
			aldeAlertsHours.setId("5");		
			aldeAlertsHours.setName("16:00 PM to 19:59 PM");
			aldeAlertsHours.setBandIni("16:00 PM");
			aldeAlertsHours.setBandEnd("19:59 PM");
			cHoursTemp.add(aldeAlertsHours);
			
			aldeAlertsHours = new AldeAlertsHours();
			aldeAlertsHours.setId("6");		
			aldeAlertsHours.setName("20:00 PM to 23:59 PM");
			aldeAlertsHours.setBandIni("20:00 PM");
			aldeAlertsHours.setBandEnd("23:59 PM");
			cHoursTemp.add(aldeAlertsHours);
					
			return cHoursTemp;
		}
	  
	  public List<AldeAlertsHours> getWeeks() {
			
		    List<AldeAlertsHours> cWeeksTemp = new ArrayList<AldeAlertsHours>();
			
			AldeAlertsHours aldeAlertsWeeks = new AldeAlertsHours();		
			aldeAlertsWeeks.setId("1");		
			aldeAlertsWeeks.setName("1");
			aldeAlertsWeeks.setBandIni("1");
			aldeAlertsWeeks.setBandEnd("2");
			cWeeksTemp.add(aldeAlertsWeeks);
			
			aldeAlertsWeeks = new AldeAlertsHours();
			aldeAlertsWeeks.setId("2");		
			aldeAlertsWeeks.setName("2");
			aldeAlertsWeeks.setBandIni("2");
			aldeAlertsWeeks.setBandEnd("3");
			cWeeksTemp.add(aldeAlertsWeeks);
			
			aldeAlertsWeeks = new AldeAlertsHours();
			aldeAlertsWeeks.setId("3");		
			aldeAlertsWeeks.setName("3");
			aldeAlertsWeeks.setBandIni("3");
			aldeAlertsWeeks.setBandEnd("4");
			cWeeksTemp.add(aldeAlertsWeeks);
			
			aldeAlertsWeeks = new AldeAlertsHours();
			aldeAlertsWeeks.setId("4");		
			aldeAlertsWeeks.setName("4");
			aldeAlertsWeeks.setBandIni("4");
			aldeAlertsWeeks.setBandEnd("5");
			cWeeksTemp.add(aldeAlertsWeeks);
			
			aldeAlertsWeeks = new AldeAlertsHours();
			aldeAlertsWeeks.setId("5");		
			aldeAlertsWeeks.setName("5");
			aldeAlertsWeeks.setBandIni("5");
			aldeAlertsWeeks.setBandEnd("6");
			cWeeksTemp.add(aldeAlertsWeeks);
			
			aldeAlertsWeeks = new AldeAlertsHours();
			aldeAlertsWeeks.setId("6");		
			aldeAlertsWeeks.setName("6");
			aldeAlertsWeeks.setBandIni("6");
			aldeAlertsWeeks.setBandEnd("7");
			cWeeksTemp.add(aldeAlertsWeeks);
			
			return cWeeksTemp;
		}
	    
	  
	  
	  
	  

}
