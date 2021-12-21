package br.com.alcatellucent.csm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BasicValueCheck {
	/**
	 * Esta classe tem por objetivos fazer a consistensia de valores conforme o
	 * seu tipo.
	 */

	/**
	 * This method check if the value is empty
	 * 
	 * @param value
	 *            Is the value to be checked
	 * @return {@link Boolean}
	 */
	public final static boolean isEmptyString(String value) {
		return (value == null || value.trim().length() == 0);

	}

	/**
	 * This method check if the value is empty
	 * 
	 * @param {@link {@link Date}}
	 * @return {@link Boolean}
	 */
	// @SuppressWarnings("unused")
	public final static boolean isNumericNoSignal(String value) {
		value = value.trim();
		return value.matches("[0-9]+");
	}

	public final static boolean isEmail(String value) {
		int partFirst = value.indexOf("@");
		int parteSecond = value.indexOf(".");
		int parteThird = value.length();

		if (value.length() > 0) {
			if (!(partFirst >= 1 && parteSecond >= 3 && parteThird >= 7 && parteThird>=(parteSecond+2))) {
				return false;
			}
		}
		return true;
	}

	public final static java.sql.Date stringToDate(String date) {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date newDate = null;

		try {
			newDate = new java.sql.Date(sf.parse(date).getTime());
		} catch (Exception e) {
			newDate = null;
		}

		return (new java.sql.Date(newDate.getTime()));
	}

	public final static boolean isIP(String ip) {
		
		try {
	
		String[] part = null;
		part = ip.split("\\.");

		if ((part.length) != 4) {
			return false;
		}

		for (int i = 0; i < 4; i++) {
			int num = Integer.parseInt(part[i]);
			if (num > 255 || num < 0 || part[i].length() == 0
					|| part[i] == null) {
				return false;
			}
		}
		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
			// TODO: handle exception
		}
		return true;

	}

	public final static boolean isMobile(String value) {
		boolean result = true;
		if (value.length() > 0) {
			result = (value.length() > 7 && isNumericNoSignal(value));
		}

		return result;
	}

	public final static boolean isURL(String value) {
		value = value.trim();
		return value.matches("^[A-Za-z]+://[A-Za-z0-9]+[\\.[A-Za-z0-9]+[/+]]+");
	}

	/**
	 * This method is deprecated. Use DateUtil
	 * 
	 * @param date
	 * @return
	 */
	@Deprecated
	public final static String dateToString(java.sql.Date date) {

		long timeStamp = date.getTime();

		java.util.Date newDate = new java.util.Date(timeStamp);

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

		return sf.format(newDate);
	}

	/**
	 * 
	 * This method is deprecated. Use DateUtil
	 * 
	 * This method receive a Date in java.lang.String and return another string
	 * formatted with 2 valid digits for days and months and 4 digits for year:
	 * 01/1/2001 will return 01/01/2001
	 * 
	 * 
	 * @param date
	 * @return java.lang.String
	 */

	@Deprecated
	public final static String stringToStringDate(String date) {

		String[] dt = date.split("/");

		date = (dt[0].length() < 2 ? ("0" + dt[0]) : dt[0]) + "/"
				+ (dt[1].length() < 2 ? ("0" + dt[1]) : dt[1]) + "/"
				+ (dt[2].length() < 2 ? ("0" + dt[2]) : dt[2]);

		return (date);
	}

	/**
	 * This method is deprecated. Use DateUtil
	 * 
	 * This method convert a valid date em java.lang.String and return in
	 * java.util.Date
	 * 
	 * @param java.util.Date
	 *            date
	 * @return java.util.Date
	 */
	@Deprecated
	public final static java.sql.Date getSQLDate(String date) {

		java.sql.Date result;
		java.util.Date parsedDate;

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		try {
			parsedDate = dateFormat.parse(date);
			result = new java.sql.Date(parsedDate.getTime());
		} catch (Exception e) {
			return null;
		}

		return result;

	}

}
