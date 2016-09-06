package com.logquerier;

import com.logquerier.utilities.DateOperations;

/**
 * 
 * Represents the query object, it has fields such as startHour, endHour etc The
 * data in this object uses helper functions to extract hours, minutes etc This
 * query object will be created by the main class and will be passed onto the
 * LogQuerie class
 *
 */

public class Query {

	/**
	 * Variable to store the start hour of the query string
	 */
	private int startHour;

	/**
	 * getter for start hour
	 * @return the start hour
	 */
	public int getStartHour() {
		return startHour;
	}
	/**
	 * setter for the start hour
	 * @param startHour the start hour
	 */
	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}
	/**
	 * getter for the end hour
	 * @return the end hour
	 */
	public int getEndHour() {
		return endHour;
	}
	
	/**
	 * setter for the end hour
	 * @param endHour the end hour
	 */
	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}
	
	/**
	 * getter for the start minute
	 * @return the start minute
	 */
	public int getStartMinute() {
		return startMinute;
	}
	
	/**
	 * setter for the start minute
	 * @param startMinute the start minute
	 */
	public void setStartMinute(int startMinute) {
		this.startMinute = startMinute;
	}
	/**
	 * getter for the end minute
	 * @return endMinute the end minute
	 */
	public int getEndMinute() {
		return endMinute;
	}
	
	/**
	 * setter for the end minute
	 * @param endMinute the end minute
	 */

	public void setEndMinute(int endMinute) {
		this.endMinute = endMinute;
	}
	/**
	 * getter for the server ip address
	 * @return the server ip address
	 */

	public String getServer() {
		return server;
	}
	/**
	 * setter for the server ip address
	 * @param server server ip address
	 */
	public void setServer(String server) {
		this.server = server;
	}
	/**
	 * getter for the cpu id
	 * @return cpu id (either 0 or 1)
	 */
	public String getCpuId() {
		return cpuId;
	}
	
	/**
	 * setter for the cpu id 
	 * @param cpuId cpu id(either 0 or 1)
	 */
	public void setCpuId(String cpuId) {
		this.cpuId = cpuId;
	}
	

	/**
	 * Variable to store the end hour of the query string
	 */
	private int endHour;
	/**
	 * Variable to store the start minute of the query string
	 */
	private int startMinute;
	/**
	 * Variable to store the end minute of the query string
	 */
	private int endMinute;
	/**
	 * Variable to store the server ip
	 */
	private String server;
	/**
	 * Variable to store the cpu id
	 */
	private String cpuId;
	/**
	 * Variable to store the day of the query string
	 */
	private String day;
	
	/**
	 * getter for the day
	 * @return the day in format "yyyy-MM-dd"
	 */
	public String getDay() {
		return day;
	}
	/**
	 * setter for the day
	 * @param day in format "yyyy-MM-dd"
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * Constructor method to store query string as an object. Extract the hour,
	 * minute for start and end time
	 * 
	 * @param queryString accepts a query string in the format
	 * QUERY IP_ADDRESS CPU_ID START_DATE START_HOUR:START_MIN END_DATE END_HOUR:END_MIN
	 */
	public Query(String queryString) {
		String filters[] = queryString.split(" ");
		this.server = filters[1];
		this.cpuId = filters[2];
		this.day = filters[3];
		String startDate = filters[3] + " " + filters[4];
		String endDate = filters[5] + " " + filters[6];
		this.startHour = DateOperations.getHourFromDate(startDate);
		this.startMinute = DateOperations.getMinuteFromDate(startDate);
		this.endHour = DateOperations.getHourFromDate(endDate);
		this.endMinute = DateOperations.getMinuteFromDate(endDate);
	}

}
