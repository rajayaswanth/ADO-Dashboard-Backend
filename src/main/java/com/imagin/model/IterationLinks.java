package com.imagin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IterationLinks {
	
	@JsonProperty("workitems")
	private IterationWorkItemsDetails workitems;

	public IterationWorkItemsDetails getWorkitems() {
		return workitems;
	}

	public void setWorkitems(IterationWorkItemsDetails workitems) {
		this.workitems = workitems;
	}

}
