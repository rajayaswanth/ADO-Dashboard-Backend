package com.imagin.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "iterations")
public class Iterations {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String iterationId;
	
	private String name;
	
	private String teamName;
	
	private String path;
	
	private Date startDate;
	
	private Date endDate;
	
	private String timeFrame;
	
	private String iterationUrl;
	
	private String workItemsLink;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getIterationId() {
		return iterationId;
	}

	public void setIterationId(String iterationId) {
		this.iterationId = iterationId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getIterationUrl() {
		return iterationUrl;
	}

	public void setIterationUrl(String iterationUrl) {
		this.iterationUrl = iterationUrl;
	}

	public String getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(String timeFrame) {
		this.timeFrame = timeFrame;
	}

	public String getWorkItemsLink() {
		return workItemsLink;
	}

	public void setWorkItemsLink(String workItemsLink) {
		this.workItemsLink = workItemsLink;
	}

}
