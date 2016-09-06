package com.logquerier.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import com.logquerier.exceptions.WrongQueryFormatException;
/**
 * 
 * This class checks the query string if it adheres to the following format
 * QUERY IP_ADDRESS CPU_ID START_DATE START_HOUR:START_MIN END_DATE END_HOUR:END_MIN
 * in the question, it parses through each of the query parameters and throws a
 * WrongQueryFormatException in case the query string fails to stick to the format
 * 
 *
 */
public class QueryFormatVerifier {
	/**
	 * Stores the pattern to check for validity of an IP address
	 */
	private static final Pattern PATTERN = Pattern
			.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
	
	/**
	 * this method calls sub methods which internally check for violation of the query format,
	 * throws a WrongQueryFormatException in case one of the checks fails
	 * @param queryString query string passed down from the main class
	 * @throws WrongQueryFormatException The exception is thrown in the following cases
	 * 1. The first argument is not "QUERY" or "EXIT"
	 * 2. The second argument of the query is not a valid IP address
	 * 3. The third argument of the query string is either not a 0 or 1
	 * 4. The start date/time is after the end date/time
	 * 5. The start and end dates do not follow the format(yyyy-MM-dd HH:mm)
	 */
	public static void checkFormat(String queryString) throws WrongQueryFormatException {
		String filters[] = queryString.split(" ");
		if (filters.length != 7)
			throw new WrongQueryFormatException(
					"the query should be in this format-- QUERY <IP_ADDRESS> <CPU_ID> <START_DATE> <START_HOUR:START_MIN> <END_DATE> <END_HOUR:END_MIN>");
		if (!filters[0].equals("QUERY") && !filters[0].equals("EXIT"))
			throw new WrongQueryFormatException("the query should either start with a 'QUERY' or type 'EXIT' to end");
		if (!isAnIp(filters[1]))
			throw new WrongQueryFormatException("the second argument must be an ip address");
		if (!isCpuId(filters[2]))
			throw new WrongQueryFormatException("the third argument must be a cpu id(0 or 1)");
		isDateTime(filters);
	}
	/**
	 * checks if the ip address string from the query string is a valid ip address
	 * @param ip ip addresss string from the query string
	 * @returns true if its a valid ip address, returns false otherwise
	 */
	private static Boolean isAnIp(final String ip) {
		return PATTERN.matcher(ip).matches();
	}
	/**
	 * checks if the cpu id from the query string is a valid cpu id(either 0 or 1)
	 * @param cpuId cpu id from the query string
	 * @return true if the cpu id is either 0 or 1, false otherwise
	 */
	private static Boolean isCpuId(final String cpuId) {
		return (Integer.parseInt(cpuId) == 0 || Integer.parseInt(cpuId) == 1) ? true : false;
	}
	
	/**
	 * checks if the dates follow the "yyyy-MM-dd HH:mm" format and checks if the 
	 * start date is less than the end date. If one of the checks fails the method throws a
	 * WrongQueryFormatException
	 * @param filters query string array containing the start and end date
	 * @throws WrongQueryFormatException
	 */
	private static void isDateTime(String filters[]) throws WrongQueryFormatException {
		SimpleDateFormat format = new SimpleDateFormat(DateOperations.dateFormat);
		try {
			Date startDate = format.parse(filters[3] + " " + filters[4]);
			Date endDate = format.parse(filters[5] + " " + filters[6]);
			if (startDate.after(endDate)) {
				throw new WrongQueryFormatException("Start date,time should be before the end date/time");
			}
		} catch (ParseException e) {
			throw new WrongQueryFormatException("Wrong format of date, format is yyyy-MM-dd HH:MM");
		}
	}

}
