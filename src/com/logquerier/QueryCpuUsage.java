package com.logquerier;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class QueryCpuUsage {
	private static Loader loader;
	private static Map<String, Map<String,int[][]>> map = new HashMap<>();
	
	public static void main(String[] args) {
		Long startTime = System.currentTimeMillis();
		initialize(args[0]);
		Long endTime = System.currentTimeMillis();
		System.out.println("Time taken for initialization "+(endTime - startTime));
		
		Scanner scanner = new Scanner(System.in);
		String queryLine = null;
		do{
			System.out.print(">");
			queryLine = scanner.nextLine();
			if(queryLine.equalsIgnoreCase("EXIT")){
				break;
			}
			LogQuerier querier = new LogQuerier();
			querier.setMap(map);
			Query query = new Query(queryLine);
			startTime = System.currentTimeMillis();
			querier.setQuery(query);
			System.out.println(querier.getCpuUsages());
			endTime = System.currentTimeMillis();
			System.out.println("Time taken for results "+(endTime - startTime));
		}while(true);
		scanner.close();
		
	}
	
	private static void initialize(String path){
		loader = new Loader();
		loader.setPath(path);
		map = loader.getMap();
	}

}
