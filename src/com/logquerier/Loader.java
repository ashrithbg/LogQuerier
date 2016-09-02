package com.logquerier;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.logquerier.utilities.DateOperations;

public class Loader {
	private String path;
	private Map<String, Map<String,int[][]>> map = new HashMap<>();

	public Map<String, Map<String, int[][]>> getMap() {
		constructMapFromFile();
		return map;
	}

	public void setMap(Map<String, Map<String, int[][]>> map) {
		this.map = map;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	

	private String fileContent;

	private void constructMapFromFile() {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileReader(path));
			while (scanner.hasNextLine()) {
				String[] columns = scanner.nextLine().split(" ");
				int hour = DateOperations.getHourFromTimestamp(columns[0]);
				int min = DateOperations.getMinuteFromTimestamp(columns[0]);
				if(map.containsKey(columns[1])){
					int cpuUsage[][];
					if(map.get(columns[1]).containsKey(columns[2])){
						cpuUsage = map.get(columns[1]).get(columns[2]);
						
						cpuUsage[hour][min] = Integer.parseInt(columns[3]);
						map.get(columns[1]).put(columns[2],cpuUsage);
					}
					else{
						cpuUsage = new int[24][60];
						cpuUsage[hour][min] = Integer.parseInt(columns[3]);
						map.get(columns[1]).put(columns[2],cpuUsage);
					}
				}
				else{
					Map<String,int[][]> internalMap = new HashMap<>();
					int [][] usageArray = new int[24][60];
					usageArray[hour][min] = Integer.parseInt(columns[3]);
					internalMap.put(columns[2], usageArray);
					map.put(columns[1],internalMap);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} finally {
			scanner.close();
		}

	}

}
