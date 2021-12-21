package br.com.alcatellucent.csm.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.alcatellucent.csm.exception.ExceptionSys;
//* Igor
public class DateUtil {
	
	public final static int DATE_FORMAT_DDMMAAAA = 0;
	public final static int DATE_FORMAT_MMDDAAAA = 1;
	public final static int DATE_FORMAT_AAAAMMDD = 2;
	
	public final static int TIME_FORMAT_HHMM = 0;
	public final static int TIME_FORMAT_HHMMSS = 1;
	
	public DateUtil() {
		
	}

	/**
	 * Return the Current Date 
	 * 
	 * @return java.util.Date
	 */
	public static java.util.Date getCurrentSQLDate() {
		
		java.util.Date utlDate = new java.util.Date();

		return (new java.util.Date(utlDate.getTime())); 
	}
	
	/**
	 * Return the Current Date 
	 * 
	 * @return java.sqlate
	 * @param Boolean isSqlDate - true returns date in type java.sql.date
	 *                            false returns null
	 */
	public static java.sql.Date getCurrentSQLDate(Boolean isSqlDate) {
		
		if (!isSqlDate) {return null;} 
		
		java.util.Date utlDate = new java.util.Date();

		return (new java.sql.Date(utlDate.getTime())); 
	}
	
	/**
	 * Format a java.util.date to format yyyy-mm-dd
	 * 
	 * @param java.util.Date date to format
	 * @return java.lang.String  
	 */
	public final static String dateToString(java.util.Date date) {
		
		long timeStamp = date.getTime();
		
		java.util.Date newDate = new java.util.Date(timeStamp);
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
		
		return sf.format(newDate);
	}
	
	/**
	 * Format a java.sql.date to format yyyy-mm-dd
	 * 
	 * @param java.sql.Date date to format
	 * @return
	 */
	public final static String dateToString(java.sql.Date date) {
		
		long timeStamp = date.getTime();
		
		java.util.Date newDate = new java.util.Date(timeStamp);
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		
		return sf.format(newDate);
	}
	/**
	 * 
	 * This method receive a Date in java.lang.String and return another 
	 *  string formatted with 2 valid digits for days and months and 4
	 *  digits for year:
	 *  01/1/2001 will return 01/01/2001
	 * 
	 * 
	 * @param date
	 * @return java.lang.String
	 */

	public final static String stringToStringDate(String date) {
		
		String[] dt = date.split("/");
		
		date = (dt[0].length()<2 ? ("0" + dt[0]) : dt[0]) + "/" +
		       (dt[1].length()<2 ? ("0" + dt[1]) : dt[1]) + "/" +
		       (dt[2].length()<2 ? ("0" + dt[2]) : dt[2]);

		return (date);
	}

	/**
	 * Format a java.util.Date to format yyyy-mm-dd hh:mm:ss
	 * 
	 * @param java.util.Date date to format
	 * @return java.lang.String  
	 */
	public final static String longToString(java.util.Date date) {
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		return sf.format(date);
	}
	
	/**
	 * This method convert a valid date em java.lang.String and 
	 * return in java.util.Date
	 * 
	 * @param java.util.Date date
	 * @return java.util.Date
	 */

	public  final static java.sql.Date getSQLDate(String date) {
		
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
	
	/**
	 * Format a java.util.Long to format java.util.Date
	 * @param java.util.Date date to format
	 * @return java.lang.String  
	 */
	public final static java.util.Date getLongToDate(Long value) {
		java.util.Date date = new java.util.Date();
		
		try {
			date.setTime(value);
			return date;
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * This method receive a Date in string format, and return it in java.util.Calendar 
	 * with time equal to zero
	 * 
	 * @param value      java.lang.String 
	 * @param DateFormat AAAAMMDD / DDMMAAAA / MMDDAAA
	 * @return
	 */
	
	
	@SuppressWarnings("unused")
	public final static java.util.Calendar StringToCalendar(String value, int dateFormat) throws ExceptionSys{
		
		String[] tmpDate = value.split("/");
		
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.HOUR,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		
		switch (dateFormat) {
		case DATE_FORMAT_AAAAMMDD:
			 cal.set(Calendar.DAY_OF_MONTH,Integer.parseInt(tmpDate[2]));
			 cal.set(Calendar.MONTH,Integer.parseInt(tmpDate[1]));
			 cal.set(Calendar.YEAR,Integer.parseInt(tmpDate[0]));
			 break;
		case DATE_FORMAT_DDMMAAAA:
			 cal.set(Calendar.DAY_OF_MONTH,Integer.parseInt(tmpDate[0]));
			 cal.set(Calendar.MONTH,Integer.parseInt(tmpDate[1]));
			 cal.set(Calendar.YEAR,Integer.parseInt(tmpDate[2]));
			 break;
		case DATE_FORMAT_MMDDAAAA:
			 cal.set(Calendar.DAY_OF_MONTH,Integer.parseInt(tmpDate[1]));
			 cal.set(Calendar.MONTH,Integer.parseInt(tmpDate[0]));
			 cal.set(Calendar.YEAR,Integer.parseInt(tmpDate[2]));
			 break;
		default:
			throw new ExceptionSys("Invalid Date or Format: " + value.toString());
		}
		
		return cal;
	}
	
	/**
	 * This method receive a Date in string format, and return it in java.util.Calendar with 
	 * a given time
	 * 
	 * @param value      java.lang.String 
	 * @param DateFormat AAAAMMDD / DDMMAAAA / MMDDAAA
	 * @return
	 */
	
	
	@SuppressWarnings("unused")
	public final static java.util.Calendar StringToCalendar(String value, String value2, int dateFormat, int timeFormat) throws ExceptionSys{
		
		String[] tmpDate = value.split("/");
		String[] tmpTime = value2.split(":");
		
		Calendar cal = Calendar.getInstance();
		
		
		switch (timeFormat) {
		case TIME_FORMAT_HHMM:
			 cal.set(Calendar.HOUR,Integer.parseInt(tmpTime[0]));
			 cal.set(Calendar.MINUTE,Integer.parseInt(tmpTime[1]));
			 cal.set(Calendar.SECOND,0);			
			 break;
		case TIME_FORMAT_HHMMSS:
			 cal.set(Calendar.HOUR,Integer.parseInt(tmpTime[0]));
			 cal.set(Calendar.MINUTE,Integer.parseInt(tmpTime[1]));
			 cal.set(Calendar.SECOND,Integer.parseInt(tmpTime[2]));			
			 break;
		default:
			throw new ExceptionSys("Invalid Time or Format: " + value.toString());
		}
		
		switch (dateFormat) {
		case DATE_FORMAT_AAAAMMDD:
			 cal.set(Calendar.DAY_OF_MONTH,Integer.parseInt(tmpDate[2]));
			 cal.set(Calendar.MONTH,Integer.parseInt(tmpDate[1]));
			 cal.set(Calendar.YEAR,Integer.parseInt(tmpDate[0]));
			 break;
		case DATE_FORMAT_DDMMAAAA:
			 cal.set(Calendar.DAY_OF_MONTH,Integer.parseInt(tmpDate[0]));
			 cal.set(Calendar.MONTH,Integer.parseInt(tmpDate[1]));
			 cal.set(Calendar.YEAR,Integer.parseInt(tmpDate[2]));
			 break;
		case DATE_FORMAT_MMDDAAAA:
			 cal.set(Calendar.DAY_OF_MONTH,Integer.parseInt(tmpDate[1]));
			 cal.set(Calendar.MONTH,Integer.parseInt(tmpDate[0]));
			 cal.set(Calendar.YEAR,Integer.parseInt(tmpDate[2]));
			 break;
		default:
			throw new ExceptionSys("Invalid Date or Format: " + value.toString());
		}
		
		return cal;
	}
	
	/**
	 * Convert a format String date "dd/MM/yyyy" to format "dd-mm-yyyy"
	 * @param java.lang.String date to format
	 * @return java.lang.String  
	 */
	public final static String getCorrectStringDate(String date){
		
		String data = date;
		if(date.substring(2, 3).equalsIgnoreCase("/")){
			data = date.replaceAll("/", "-");
		}
		
		return data;
		
	}
}
