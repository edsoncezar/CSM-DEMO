package br.com.alcatellucent.csm.utils.displaytag;

import java.util.ArrayList;
import java.util.Collection;

import org.displaytag.decorator.ColumnDecorator;

import br.com.alcatellucent.csm.beans.ProcessorPacket;

public class ProcessorPacketDecorator implements ColumnDecorator {

    @SuppressWarnings("unchecked")
    public final String decorate(Object val) {

	Collection<ProcessorPacket> pPacket = null;
	StringBuffer str = new StringBuffer();

	if (val instanceof Collection) { // A Collection
	    pPacket = (Collection<ProcessorPacket>) val;
	} else { // One Object
	    pPacket = new ArrayList<ProcessorPacket>();
	    pPacket.add((ProcessorPacket) val);
	}
	for (ProcessorPacket p : pPacket) {
	    str.append(p.getName() + "<br>");
	}
	return str.toString();
    }
}
