package com.imagin.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Iteration {
	
	@JsonProperty("id")
	private String iterationId;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("path")
	private String iterationPath;
	
	@JsonProperty("attributes")
	private IterationAttributes attributes;
	
	@JsonProperty("url")
	private String iterationURL;
	
	@JsonProperty("_links")
	private IterationLinks links;

	public String getIterationId() {
		return iterationId;
	}

	public void setIterationId(String iterationId) {
		this.iterationId = iterationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIterationPath() {
		return iterationPath;
	}

	public void setIterationPath(String iterationPath) {
		this.iterationPath = iterationPath;
	}

	public IterationAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(IterationAttributes attributes) {
		this.attributes = attributes;
	}

	public String getIterationURL() {
		return iterationURL;
	}

	public void setIterationURL(String iterationURL) {
		this.iterationURL = iterationURL;
	}

	public IterationLinks getLinks() {
		return links;
	}

	public void setLinks(IterationLinks links) {
		this.links = links;
	}

}
