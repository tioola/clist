package com.clist.domain.enums;

public enum Visibility {

	ALL("ALL"),OWNER("OWNER"), ADMIN("ADMIN");
	
	private String visibility;
	
	private Visibility(String visibility){
		this.visibility = visibility;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
}
