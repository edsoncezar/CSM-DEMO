package br.com.alcatellucent.csm.utils.displaytag;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.displaytag.decorator.ColumnDecorator;

public class DateDecorator implements ColumnDecorator {
	
	private SimpleDateFormat dateFormat;


    public final String decorate(Object col) {
    	
    	if (!(col instanceof java.util.GregorianCalendar)) {
    		
			return col.toString();
		}
    	
    	long timeZone = ((java.util.GregorianCalendar) col).getTimeInMillis();
    	dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    		
    	Date formatedDate = new java.util.Date();
    	formatedDate.setTime(timeZone);
		
    	return dateFormat.format(formatedDate);
    }
}
