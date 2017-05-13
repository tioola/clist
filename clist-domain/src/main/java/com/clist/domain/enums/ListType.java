package com.clist.domain.enums;

public enum ListType {

	COLABORATIVE("COLAB");
	
	private String type;
	
	private ListType(String type){
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
