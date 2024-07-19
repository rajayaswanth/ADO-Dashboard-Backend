package com.imagin.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkItemFields {
	
	@JsonProperty("id")
	private String userStoryNumber;
	
	@JsonProperty("fields")
	private WorkItemFieldDetails fields;
	
	@JsonProperty("relations")
	private List<WorkItemRelationDetails> relations;

	public WorkItemFieldDetails getFields() {
		return fields;
	}

	public void setFields(WorkItemFieldDetails fields) {
		this.fields = fields;
	}

	public String getUserStoryNumber() {
		return userStoryNumber;
	}

	public void setUserStoryNumber(String userStoryNumber) {
		this.userStoryNumber = userStoryNumber;
	}

	public List<WorkItemRelationDetails> getRelations() {
		return relations;
	}

	public void setRelations(List<WorkItemRelationDetails> relations) {
		this.relations = relations;
	}

}
