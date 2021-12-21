package teste;

import java.sql.Date;
import java.util.Calendar;

public class TesteDatas {
	

	public TesteDatas() {
		
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		TesteDatas td = new TesteDatas();
		
		System.out.println("Data = " + td.getCurrentSQLDate());
		
	}
	
	public java.sql.Date getCurrentSQLDate() {
		
		java.util.Date utlDate = new java.util.Date();
		
		return (new java.sql.Date(utlDate.getTime())); 
	}

}
