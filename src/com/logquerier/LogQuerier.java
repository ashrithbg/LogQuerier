package com.logquerier;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This class does the querying of cpu usages, it queries the hash map level by
 * level which was set and passed on to it by the Loader class.
 * 
 * 
 *
 */
public class LogQuerier {

	/**
	 * Variable to be set by the main class through the Loader class
	 */
	private Map<String, Map<String, int[][]>> map = new HashMap<>();

	/**
	 * getter for map
	 * 
	 * @return a map
	 */
	public Map<String, Map<String, int[][]>> getMap() {
		return map;
	}

	/**
	 * setter for map
	 * 
	 * @param map
	 *            supplied from the main class
	 */
	public void setMap(Map<String, Map<String, int[][]>> map) {
		this.map = map;
	}

	/**
	 * Variable representing the query object passed down from the main class
	 */
	private Query query;

	/**
	 * a dynamic variable which will have the most recent results
	 */
	private StringBuilder result = new StringBuilder();

	/**
	 * getter for result
	 * 
	 * @return a result variable, containing the latest formatted results
	 */
	public StringBuilder getResult() {
		return result;
	}

	/**
	 * setter for result
	 * 
	 * @param result
	 */
	public void setResult(StringBuilder result) {
		this.result = result;
	}

	/**
	 * getter for query object
	 * 
	 * @return query object
	 */
	public Query getQuery() {
		return query;
	}

	/**
	 * setter for query object
	 * 
	 * @param Query object
	 */

	public void setQuery(Query query) {
		this.query = query;
	}

	/**
	 * This function forms the crux of hashmap search The function first
	 * searches the main hashmap with the server ip followed by cpu id, after
	 * which it iterates from start time to end time over the 2D array
	 */

	private void searchInHashMap() {
		initializeResult();
		if (map != null) {
			Map<String, int[][]> internalMap;
			internalMap = map.get(query.getServer());
			// is there an existing internal hashmap corresponding
			// to the sever ip entered in the query?
			if (internalMap != null) {
				int[][] cpuUsage = internalMap.get(query.getCpuId());
				// is there an existing array corresponding
				// to the cpu id entered in the query?
				if (cpuUsage != null) {
					for (int hour = query.getStartHour(); hour <= query.getEndHour(); hour++) {
						// if start and end time fall within the same hour
						if (hour == query.getEndHour()) {
							for (int min = query.getStartMinute(); min <= query.getEndMinute(); min++) {
								makeTimeCpuTuple(hour, min, cpuUsage[hour][min]);
							}
							break;
						}
						// if start and end time correspond to different hours
						// i.e cpu usage for
						// more than 60 minutes
						else {
							for (int min = query.getStartMinute(); min < 60; min++) {
								makeTimeCpuTuple(hour, min, cpuUsage[hour][min]);
							}
						}
					}

				}

			}
		}
	}

	/**
	 * appends to the result object and formats it according to the requirements
	 * 
	 * @param hour
	 *            hour
	 * @param min
	 *            minute
	 * @param usage
	 *            cpu usage at a particular hour and minute
	 */
	private void makeTimeCpuTuple(int hour, int min, int usage) {
		result.append("(").append(query.getDay()).append(" ").append(String.format("%02d", hour)).append(":")
				.append(String.format("%02d", min)).append(" ").append(usage).append("%").append(")").append(",");
	}

	/**
	 * initializes the result object with the format as required
	 */
	private void initializeResult() {
		result.append("CPU").append(query.getCpuId()).append(" usage on ").append(query.getServer()).append(":")
				.append("\n");
	}

	/**
	 * This function makes a call to the searchInHahsMap() function and returns
	 * the latest result object
	 * 
	 * @return a string containing all the cpu usages betweeen start and end
	 *         time
	 */
	public String getCpuUsages() {
		searchInHashMap();
		return result.substring(0, result.length() - 1);
	}

}
