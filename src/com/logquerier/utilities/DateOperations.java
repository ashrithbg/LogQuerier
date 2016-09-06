package com.logquerier.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateOperations {
	/**
	 * The format of the date/time throughout the project
	 */
	public static final String dateFormat = "yyyy-MM-dd HH:mm";
	/**
	 * calendar instance to be used to extract hour and minutes
	 */
	private static Calendar calendar = Calendar.getInstance();

	/**
	 * Helper method to convert queryDate in the format "yyyy-MM-dd HH:mm" to a
	 * timestamp
	 * 
	 * @param queryDate
	 *            of format "yyyy-MM-dd HH:mm"
	 * @return a string corresponding to timestamp
	 */

	public static String convertDateToTimeStamp(String queryDate) {
		DateFormat formatter = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = formatter.parse(queryDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long output = date.getTime() / 1000L;
		return Long.toString(output);
	}

	/**
	 * Helper method to extract the hour of the day from timestamp
	 * 
	 * @param timestamp timestamp in string format
	 * @return an integer corresponding to the hour of the day
	 */

	public static int getHourFromTimestamp(String timestamp) {
		calendar.setTimeInMillis(Long.parseLong(timestamp) * 1000);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * Helper method to extract the minute from timestamp
	 * 
	 * @param timestamp timestamp in string format
	 * @return an integer corresponding to the minute
	 */

	public static int getMinuteFromTimestamp(String timestamp) {

		calendar.setTimeInMillis(Long.parseLong(timestamp) * 1000);
		return calendar.get(Calendar.MINUTE);

	}

	/**
	 * Helper method to extract the hour from date
	 * 
	 * @param date
	 *            should be of the format "yyyy-MM-dd HH:mm"
	 * @return an integer corresponding to the hour of the day
	 */

	public static int getHourFromDate(String date) {
		String timestamp = convertDateToTimeStamp(date);
		calendar.setTimeInMillis(Long.parseLong(timestamp) * 1000);
		return calendar.get(Calendar.HOUR_OF_DAY);

	}

	/**
	 * Helper method to extract the minute from date
	 * 
	 * @param date
	 *            date should be a string of the format "yyyy-MM-dd HH:mm"
	 * @return an integer corresponding to the hour of the day
	 */
	public static int getMinuteFromDate(String date) {
		String timestamp = convertDateToTimeStamp(date);
		calendar.setTimeInMillis(Long.parseLong(timestamp) * 1000);
		return calendar.get(Calendar.MINUTE);

	}

}
