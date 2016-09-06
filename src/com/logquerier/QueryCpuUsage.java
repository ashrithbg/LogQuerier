package com.logquerier;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.logquerier.exceptions.WrongQueryFormatException;
import com.logquerier.utilities.QueryFormatVerifier;

/**
 * 
 * Represents the main class for querying the CPU usage
 *
 */
public class QueryCpuUsage {
	/**
	 * Variable to store the loader object, which loads the cpu usage file
	 */
	private static Loader loader;
	/**
	 * Each execution of this file will have a new map obtained from the loader
	 * object
	 */
	private static Map<String, Map<String, int[][]>> map = new HashMap<>();

	/**
	 * Entry point of the main class, this function loads, initializes the cpu
	 * usage file Also, the function calls methods to build Query objects and
	 * fetch the results
	 * 
	 * @param args
	 *            arguments to the main method representing the DATA_PATH
	 */

	public static void main(String[] args) {
		Long startTime = System.currentTimeMillis();
		// check if the arguments from the shell script are correct
		checkArguments(args);
		// initialize the hashmap
		initialize(args[0]);
		Long endTime = System.currentTimeMillis();
		System.out.println("Time taken for initialization " + (float) (endTime - startTime) / 1000 + " secs");

		Scanner scanner = new Scanner(System.in);
		String queryLine = null;
		do {
			System.out.print(">");
			queryLine = scanner.nextLine();
			// read the line and exit the program if the user input is "EXIT"
			if (queryLine.equalsIgnoreCase("EXIT")) {
				break;
			}
			// check if the user enters the query correctly,
			// throws a WrongQueryFormatException if the query string
			// does not follow the standard query format

			if (checkQueryFormat(queryLine)) {

				// initialize map and query objects
				LogQuerier querier = new LogQuerier();
				querier.setMap(map);
				Query query = new Query(queryLine);
				startTime = System.currentTimeMillis();
				querier.setQuery(query);
				// get the formatted results
				System.out.println(querier.getCpuUsages());
				endTime = System.currentTimeMillis();
				System.out.println("Time taken for results " + (float) (endTime - startTime) / 1000 + " secs");
			}
		} while (true);// loop until "EXIT"
		scanner.close();

	}

	/**
	 * This method loads the file and constructs the hashmap in the first few minutes
	 * for efficient querying 
	 * 
	 * @param path
	 *            a file system location supplied by the user representing the
	 *            data path directory of the CPU usage file
	 */

	private static void initialize(String path) {
		loader = new Loader();
		loader.setPath(path);
		map = loader.getMap();
	}

	/**
	 * This method checks if the shell script/user supplies the correct number
	 * of arguments. Exits if the number of arguments do not match
	 * 
	 * @param args
	 *            arguments representing the data path
	 */
	private static void checkArguments(String args[]) {
		if (args.length == 0) {
			System.out.println("Plese enter the data path and run again");
			System.exit(0);
		} else if (args.length > 1) {
			System.out.println("Wrong number of arguments");
			System.exit(0);
		}

	}

	/**
	 * This function validates if the user enters the query string correctly.
	 * For eg - is the start date less than the end date, is the ip string a
	 * valid IP address etc. If the query string fails to adhere to the format,
	 * corresponding error message is displayed to the user.
	 * 
	 * @param queryString
	 *            represents the query string the user enters in the prompt
	 * @return true if there are no format problems with the queryString, false
	 *         otherwise.
	 */
	public static Boolean checkQueryFormat(String queryString) {
		try {
			QueryFormatVerifier.checkFormat(queryString);
		} catch (WrongQueryFormatException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;

	}
}
