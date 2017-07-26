package org.teracy.scats.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.teracy.scats.utils.TestLogger;

public class DateTime {
	/**
	 * Get date by text format
	 * ex. Saturday, Febuary 16, 2015
	 * @param format
	 */
	public String getDateByTextFormat(String format){
		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		TestLogger.info(date);
		return date;
	}
	
	/**
	 * Get the day of next year
	 * @param format
	 * @param year
	 * @return dayOfYear
	 */
	public String getDayOfNextYear(String format,int year){
		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();

		Calendar nextYear = Calendar.getInstance();
		nextYear.setTime(calendar.getTime());
		nextYear.add(Calendar.YEAR,year);
		String dayOfYear= dateFormat.format(nextYear.getTime());
		TestLogger.info("dayOfYear:"+dayOfYear);
		return dayOfYear;
	}
}
