package com.imagin.model;

import java.util.List;
import java.util.Map;

public class SprintDetailsResponse {
	
	private List<SprintDetails> sprintDetails;
	
	private int userStoriesCount;
	
	private int sprintCommitPoints;
	
	private int completedPoints;
	
	private int sprintCompletePercentage;
	
	private String sprintName;
	
	private Map<String, List<SprintDetails>> userdata;

	public List<SprintDetails> getSprintDetails() {
		return sprintDetails;
	}

	public void setSprintDetails(List<SprintDetails> sprintDetails) {
		this.sprintDetails = sprintDetails;
	}

	public int getUserStoriesCount() {
		return userStoriesCount;
	}

	public void setUserStoriesCount(int userStoriesCount) {
		this.userStoriesCount = userStoriesCount;
	}

	public int getCompletedPoints() {
		return completedPoints;
	}

	public void setCompletedPoints(int completedPoints) {
		this.completedPoints = completedPoints;
	}

	public int getSprintCommitPoints() {
		return sprintCommitPoints;
	}

	public void setSprintCommitPoints(int sprintCommitPoints) {
		this.sprintCommitPoints = sprintCommitPoints;
	}

	public int getSprintCompletePercentage() {
		return sprintCompletePercentage;
	}

	public void setSprintCompletePercentage(int sprintCompletePercentage) {
		this.sprintCompletePercentage = sprintCompletePercentage;
	}

	public String getSprintName() {
		return sprintName;
	}

	public void setSprintName(String sprintName) {
		this.sprintName = sprintName;
	}

	public Map<String, List<SprintDetails>> getUserdata() {
		return userdata;
	}

	public void setUserdata(Map<String, List<SprintDetails>> userdata) {
		this.userdata = userdata;
	}
	
}
