package com.logquerier;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.logquerier.utilities.DateOperations;

/**
 * 
 * Represents the class which loads the cpu usage file into memory(hashmap)
 *
 */
public class Loader {
	/**
	 * Variable corresponding to the data directory
	 */
	private String path;

	/**
	 * A constant to represent which file should the Loader object load from
	 */
	private static final String logFileName = "log.txt";

	/**
	 * Every Loader object will have a corresponding map associated with it
	 */
	private Map<String, Map<String, int[][]>> map = new HashMap<>();

	/**
	 * getter for the map
	 * @return a HashMap after constructing, reading from the log file
	 */
	public Map<String, Map<String, int[][]>> getMap() {
		constructMapFromFile();
		return map;
	}

	

	/**
	 * get the data path
	 * 
	 * @return the data path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * set the data path
	 * 
	 * @param path the path of the data directory
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * This method scans the log file line by line, constructs a HashMap The
	 * Hashmap will have the following structure--- The key of the main hashmap
	 * is the IP address of the server The key maps to another hashmap whose key
	 * is the cpu usage The key of the internal hashmap maps to a double
	 * dimensional array of 24*60 dimension
	 */

	private void constructMapFromFile() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileReader(path + "/" + logFileName));
			while (scanner.hasNextLine()) {
				String[] columns = scanner.nextLine().split(" ");
				int hour = DateOperations.getHourFromTimestamp(columns[0]);
				int min = DateOperations.getMinuteFromTimestamp(columns[0]);

				// in the main hashmap, check if the Ip address exists as a key
				if (map.containsKey(columns[1])) {
					int cpuUsage[][];
					// check for presence of cpu id as the key in the internal
					// hashmap
					// if present add the cpu usage to the existing double
					// dimensional
					// array
					if (map.get(columns[1]).containsKey(columns[2])) {
						cpuUsage = map.get(columns[1]).get(columns[2]);

						cpuUsage[hour][min] = Integer.parseInt(columns[3]);
						map.get(columns[1]).put(columns[2], cpuUsage);
					}
					// if cpu id does not exist already, create a new double
					// dimensional
					// array and record the cpu usage corresponding to the hour
					// and minute

					else {
						cpuUsage = new int[24][60];
						cpuUsage[hour][min] = Integer.parseInt(columns[3]);
						map.get(columns[1]).put(columns[2], cpuUsage);
					}
				}
				// if the ip address does not map to a key, create a new key
				// value pair
				// also initialize the double dimensional array followed by the
				// hashmap
				else {
					Map<String, int[][]> internalMap = new HashMap<>();
					int[][] usageArray = new int[24][60];
					usageArray[hour][min] = Integer.parseInt(columns[3]);
					internalMap.put(columns[2], usageArray);
					map.put(columns[1], internalMap);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Please enter a valid data path");

		} finally {
			scanner.close();
		}

	}

}
