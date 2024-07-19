package com.imagin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkItemAssignedTo {
	
	@JsonProperty(value = "displayName", defaultValue = "")
	private String displayName;
	
	@JsonProperty(value = "uniqueName", defaultValue = "")
	private String email;
	
	@JsonProperty(value = "imageUrl", defaultValue = "")
	private String imageUrl;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
