package com.logquerier.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateOperations {
	private static final String dateFormat = "yyyy-MM-dd HH:mm";
	private static Calendar calendar = Calendar.getInstance();

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

	public static int getHourFromTimestamp(String timestamp) {
		calendar.setTimeInMillis(Long.parseLong(timestamp) * 1000);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinuteFromTimestamp(String timestamp) {

		calendar.setTimeInMillis(Long.parseLong(timestamp) * 1000);
		return calendar.get(Calendar.MINUTE);

	}

	public static int getHourFromDate(String date) {
		String timestamp = convertDateToTimeStamp(date);
		calendar.setTimeInMillis(Long.parseLong(timestamp) * 1000);
		return calendar.get(Calendar.HOUR_OF_DAY);

	}

	public static int getMinuteFromDate(String date) {
		String timestamp = convertDateToTimeStamp(date);
		calendar.setTimeInMillis(Long.parseLong(timestamp) * 1000);
		return calendar.get(Calendar.MINUTE);

	}

}
