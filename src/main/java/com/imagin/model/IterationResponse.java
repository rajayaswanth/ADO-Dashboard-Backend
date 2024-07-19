package com.imagin.model;

import java.util.List;

public class IterationResponse {
	
	private int count;
	
	private List<Iteration> value;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Iteration> getValue() {
		return value;
	}

	public void setValue(List<Iteration> value) {
		this.value = value;
	}

}
