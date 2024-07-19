package com.imagin.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkItemRelationAttributes {
	
	private int id;
	
	@JsonProperty("resourceCreatedDate")
	private Date prCreatedDate;
	
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getPrCreatedDate() {
		return prCreatedDate;
	}

	public void setPrCreatedDate(Date prCreatedDate) {
		this.prCreatedDate = prCreatedDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
