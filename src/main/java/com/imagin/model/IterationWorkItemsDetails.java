package com.imagin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IterationWorkItemsDetails {
	
	@JsonProperty("href")
	private String workItemsLink;

	public String getWorkItemsLink() {
		return workItemsLink;
	}

	public void setWorkItemsLink(String workItemsLink) {
		this.workItemsLink = workItemsLink;
	}

}
