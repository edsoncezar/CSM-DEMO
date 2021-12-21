package br.com.alcatellucent.csm.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class Utils {

	private static final String STR_ZERO = "0";

	public static List collectionToList(Collection collection, Class klass) {
		List returnList = new ArrayList();
		Iterator it = collection.iterator();
		while (it.hasNext()) {
			returnList.add(klass.cast(it.next()));
		}
		return returnList;
	}

	public static String fillZerosLeft(String src, int zerosToFill) {
		StringBuilder sb = new StringBuilder();
		while (zerosToFill-- > 0) {
			sb.append(STR_ZERO);
		}
		sb.append(src);
		return sb.toString();
	}

}
