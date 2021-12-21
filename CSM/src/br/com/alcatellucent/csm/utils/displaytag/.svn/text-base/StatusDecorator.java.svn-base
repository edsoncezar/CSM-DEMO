package br.com.alcatellucent.csm.utils.displaytag;

import org.displaytag.decorator.ColumnDecorator;

public class StatusDecorator implements ColumnDecorator {

    public final String decorate(Object val) {
    	
    	if (!(val instanceof java.lang.Boolean)) {
    		
			return val.toString();
		}
    	
    	String string = (Boolean)val==false?"Error":"Applied - OK";
    	
    	return string;
    }
}
