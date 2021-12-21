package br.com.alcatellucent.csm.utils.displaytag;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.displaytag.decorator.ColumnDecorator;

public class TimeDecorator implements ColumnDecorator {

	private SimpleDateFormat timeFormat;

	public final String decorate(Object col) {
    	
    	if (!(col instanceof java.util.GregorianCalendar)) {
    		
			return col.toString();
		}
    	
    	long timeZone = ((java.util.GregorianCalendar) col).getTimeInMillis();
    	timeFormat = new SimpleDateFormat("HH:mm:ss");
    		
    	Date formatedDate = new java.util.Date();
    	formatedDate.setTime(timeZone);
		
    	return timeFormat.format(formatedDate);
	}
}
