package com.logquerier;

import java.util.HashMap;
import java.util.Map;

public class LogQuerier {
	private Map<String, Map<String, int[][]>> map = new HashMap<>();

	public Map<String, Map<String, int[][]>> getMap() {
		return map;
	}

	public void setMap(Map<String, Map<String, int[][]>> map) {
		this.map = map;
	}

	private static final int noOfHours = 24;
	private Query query;
	private StringBuilder result = new StringBuilder();

	public StringBuilder getResult() {
		return result;
	}

	public void setResult(StringBuilder result) {
		this.result = result;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	private void searchInHashMap() {
		initializeResult();
		if (map != null) {
			Map<String, int[][]> internalMap;
			internalMap = map.get(query.getServer());
			if (internalMap != null) {
				int[][] cpuUsage = internalMap.get(query.getCpuId());
				if (cpuUsage != null) {
					for (int hour = query.getStartHour(); hour <= query.getEndHour(); hour++) {
						if (hour == query.getEndHour()) {
							for (int min = query.getStartMinute(); min <= query.getEndMinute(); min++) {
								makeTimeCpuTuple(hour, min, cpuUsage[hour][min]);
							}
							break;
						} else {
							for (int min = query.getStartMinute(); min < 60; min++) {
								makeTimeCpuTuple(hour, min, cpuUsage[hour][min]);
							}
						}
					}

				}

			}
		}
	}

	private void makeTimeCpuTuple(int hour, int min, int usage) {
		result.append("(").append(query.getDay()).append(" ").append(hour).append(":").append(min).append(" ")
				.append(usage).append("%").append(")").append(",");
	}

	private void initializeResult() {
		result.append("CPU").append(query.getCpuId()).append(" usage on ").append(query.getServer()).append(":")
				.append("\n");
	}

	public String getCpuUsages() {
		searchInHashMap();
		return result.substring(0, result.length()-1);
	}

}
