package com.imagin.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "workItems")
public class WorkItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String iteration;
	
	private String iterationId;
	
	private String workItemType;
	
	private String workItemNumber;
	
	private String title;
	
	private String areaPath;
	
	private String iterationPath;
	
	private String state;
	
	private int storyPoints;
	
	private Date devStartDate;
	
	private Date developmentCompletedDate;
	
	private Date qaCompletedDate;
	
	private Date prCreatedDate;
	
	private String assignedTo;
	
	private String assignedEmail;
	
	private String parentWorkItemNumber;
	
	private int spillOverCount;
	
	private String prNumber;
	
	private String prType;
	
	private String prStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIteration() {
		return iteration;
	}

	public void setIteration(String iteration) {
		this.iteration = iteration;
	}

	public String getIterationId() {
		return iterationId;
	}

	public void setIterationId(String iterationId) {
		this.iterationId = iterationId;
	}

	public String getWorkItemType() {
		return workItemType;
	}

	public void setWorkItemType(String workItemType) {
		this.workItemType = workItemType;
	}

	public String getWorkItemNumber() {
		return workItemNumber;
	}

	public void setWorkItemNumber(String workItemNumber) {
		this.workItemNumber = workItemNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getParentWorkItemNumber() {
		return parentWorkItemNumber;
	}

	public void setParentWorkItemNumber(String parentWorkItemNumber) {
		this.parentWorkItemNumber = parentWorkItemNumber;
	}

	public int getSpillOverCount() {
		return spillOverCount;
	}

	public void setSpillOverCount(int spillOverCount) {
		this.spillOverCount = spillOverCount;
	}

	public Date getPrCreatedDate() {
		return prCreatedDate;
	}

	public void setPrCreatedDate(Date prCreatedDate) {
		this.prCreatedDate = prCreatedDate;
	}

	public String getAssignedEmail() {
		return assignedEmail;
	}

	public void setAssignedEmail(String assignedEmail) {
		this.assignedEmail = assignedEmail;
	}

	public String getPrNumber() {
		return prNumber;
	}

	public void setPrNumber(String prNumber) {
		this.prNumber = prNumber;
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

}
