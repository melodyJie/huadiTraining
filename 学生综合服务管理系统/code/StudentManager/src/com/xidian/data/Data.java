package com.xidian.data;

public class Data {
	private String id;
	private String type;
	private String value;

	public Data() {
		super();
	}

	public Data(String type, String value) {
		super();
		this.type = type;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
