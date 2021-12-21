package teste;

import java.util.Calendar;
import java.util.Date;

public class TesteData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		long timeStamp = (long)(1074074051L * 1000);
//
//		java.util.Date today = new java.util.Date(timeStamp);
//		
//		 System.out.println(new java.sql.Timestamp(today.getTime()));
//		 System.out.println(Calendar.getInstance().getTimeInMillis());

		Date today = new Date();
		
		Calendar cal = Calendar.getInstance();
		
		today = cal.getTime();
		
		System.out.println(today.getDate());

	}

}
