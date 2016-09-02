package com.logquerier;

import com.logquerier.utilities.DateOperations;

public class Query {
	
	private int startHour;
	public int getStartHour() {
		return startHour;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public int getEndHour() {
		return endHour;
	}

	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}

	public int getStartMinute() {
		return startMinute;
	}

	public void setStartMinute(int startMinute) {
		this.startMinute = startMinute;
	}

	public int getEndMinute() {
		return endMinute;
	}

	public void setEndMinute(int endMinute) {
		this.endMinute = endMinute;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getCpuId() {
		return cpuId;
	}

	public void setCpuId(String cpuId) {
		this.cpuId = cpuId;
	}

	public int getCpuUsage() {
		return cpuUsage;
	}

	public void setCpuUsage(int cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	private int endHour;
	private int startMinute;
	private int endMinute;
	private String server;
	private String cpuId;
	private int cpuUsage;
	private String day;
	
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Query(String queryString){
		String filters[] = queryString.split(" ");
		this.server = filters[1];
		this.cpuId = filters[2];
		this.day = filters[3];
		String startDate = filters[3]+" "+filters[4];
		String endDate = filters[5]+" "+filters[6];
		this.startHour = DateOperations.getHourFromDate(startDate);
		this.startMinute = DateOperations.getMinuteFromDate(startDate);
		this.endHour = DateOperations.getHourFromDate(endDate);
		this.endMinute = DateOperations.getMinuteFromDate(endDate);
	}

}
