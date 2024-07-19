package com.imagin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrDetails {
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("isDraft")
	private boolean isDraft;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isDraft() {
		return isDraft;
	}

	public void setDraft(boolean isDraft) {
		this.isDraft = isDraft;
	}

}
