package com.imagin.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkItemFieldDetails {
	
	@JsonProperty(value = "System.AreaPath", defaultValue = "")
	private String areaPath;
	
	@JsonProperty(value = "System.IterationPath", defaultValue = "")
	private String iterationPath;
	
	@JsonProperty(value = "System.WorkItemType", defaultValue = "")
	private String workItemType;
	
	@JsonProperty(value = "System.State", defaultValue = "")
	private String state;
	
	@JsonProperty(value = "System.Title", defaultValue = "")
	private String title;

	@JsonProperty(value = "Microsoft.VSTS.Scheduling.StoryPoints", defaultValue = "")
	private int storyPoints;
	
	@JsonProperty(value = "Microsoft.VSTS.Common.ActivatedDate", defaultValue = "")
	private Date devStartDate;
	
	@JsonProperty(value = "JBH.Development.Completed.Date", defaultValue = "")
	private Date developmentCompletedDate;
	
	@JsonProperty(value = "JBH.QA.Completed.Date", defaultValue = "")
	private Date qaCompletedDate;
	
	@JsonProperty(value = "System.Description", defaultValue = "")
	private String description;
	
	@JsonProperty(value = "Microsoft.VSTS.Common.AcceptanceCriteria3", defaultValue = "")
	private String acceptanceCriteria;
	
	@JsonProperty(value = "System.AssignedTo", defaultValue = "")
	private WorkItemAssignedTo assignedTo;

	public String getAreaPath() {
		return areaPath;
	}

	public void setAreaPath(String areaPath) {
		this.areaPath = areaPath;
	}

	public String getIterationPath() {
		return iterationPath;
	}

	public void setIterationPath(String iterationPath) {
		this.iterationPath = iterationPath;
	}

	public String getWorkItemType() {
		return workItemType;
	}

	public void setWorkItemType(String workItemType) {
		this.workItemType = workItemType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStoryPoints() {
		return storyPoints;
	}

	public void setStoryPoints(int storyPoints) {
		this.storyPoints = storyPoints;
	}

	public Date getDevStartDate() {
		return devStartDate;
	}

	public void setDevStartDate(Date devStartDate) {
		this.devStartDate = devStartDate;
	}

	public Date getDevelopmentCompletedDate() {
		return developmentCompletedDate;
	}

	public void setDevelopmentCompletedDate(Date developmentCompletedDate) {
		this.developmentCompletedDate = developmentCompletedDate;
	}

	public Date getQaCompletedDate() {
		return qaCompletedDate;
	}

	public void setQaCompletedDate(Date qaCompletedDate) {
		this.qaCompletedDate = qaCompletedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAcceptanceCriteria() {
		return acceptanceCriteria;
	}

	public void setAcceptanceCriteria(String acceptanceCriteria) {
		this.acceptanceCriteria = acceptanceCriteria;
	}

	public WorkItemAssignedTo getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(WorkItemAssignedTo assignedTo) {
		this.assignedTo = assignedTo;
	}

}
