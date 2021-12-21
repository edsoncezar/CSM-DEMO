package br.com.alcatellucent.csm.utils.displaytag;

import org.displaytag.decorator.ColumnDecorator;

public class ModeDecorator implements ColumnDecorator {

    public final String decorate(Object val) {
    	
    	if (!(val instanceof java.lang.Integer)) {
    		
			return val.toString();
		}
    	
    	String string =  ((Integer)val==0?"Manual":"Schedulled");
    	
    	return string;
    }
}
