package com.imagin.model;

import java.util.Date;

public class SprintDetails {
	
	private boolean isExpand;
	
	private int soCount;
	
	private String userStoryNumber;
	
	private String title;
	
	private String assignedTo;
	
	private int storyPoints;
	
	private String state;
	
	private Date qaCompletedDate;
	
	private Date devCompletedDate;
	
	private Date devStartDate;
	
	private Date prCreatedDate;
	
	private String prType;
	
	private String prStatus;
	
	private int completionPercentage;
	
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSoCount() {
		return soCount;
	}

	public void setSoCount(int soCount) {
		this.soCount = soCount;
	}

	public String getUserStoryNumber() {
		return userStoryNumber;
	}

	public void setUserStoryNumber(String userStoryNumber) {
		this.userStoryNumber = userStoryNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public int getStoryPoints() {
		return storyPoints;
	}

	public void setStoryPoints(int storyPoints) {
		this.storyPoints = storyPoints;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isExpand() {
		return isExpand;
	}

	public void setExpand(boolean isExpand) {
		this.isExpand = isExpand;
	}

	public Date getQaCompletedDate() {
		return qaCompletedDate;
	}

	public void setQaCompletedDate(Date qaCompletedDate) {
		this.qaCompletedDate = qaCompletedDate;
	}

	public Date getDevCompletedDate() {
		return devCompletedDate;
	}

	public void setDevCompletedDate(Date devCompletedDate) {
		this.devCompletedDate = devCompletedDate;
	}

	public Date getDevStartDate() {
		return devStartDate;
	}

	public void setDevStartDate(Date devStartDate) {
		this.devStartDate = devStartDate;
	}

	public Date getPrCreatedDate() {
		return prCreatedDate;
	}

	public void setPrCreatedDate(Date prCreatedDate) {
		this.prCreatedDate = prCreatedDate;
	}

	public String getPrType() {
		return prType;
	}

	public void setPrType(String prType) {
		this.prType = prType;
	}

	public String getPrStatus() {
		return prStatus;
	}

	public void setPrStatus(String prStatus) {
		this.prStatus = prStatus;
	}

	public int getCompletionPercentage() {
		return completionPercentage;
	}

	public void setCompletionPercentage(int completionPercentage) {
		this.completionPercentage = completionPercentage;
	}

}
