package com.deppon.baseline.domain;

public class Serie {

	private String name;
	
	private Number[] data;

	public Serie(String name, Number[] yAxisList) {
		this.name=name;
		this.data=yAxisList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Number[] getData() {
		return data;
	}

	public void setData(Number[] data) {
		this.data = data;
	}
	
	
}
