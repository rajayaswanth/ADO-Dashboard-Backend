package com.imagin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IterationAttributes {
	
	@JsonProperty("startDate")
	private String startDate;
	
	@JsonProperty("finishDate")
	private String endDate;
	
	@JsonProperty("timeFrame")
	private String timeFrame;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(String timeFrame) {
		this.timeFrame = timeFrame;
	}

}
