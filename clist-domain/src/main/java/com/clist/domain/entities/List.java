package com.clist.domain.entities;

import com.clist.domain.enums.ListType;

public class List {

	private String id;
	
	private ListType type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ListType getType() {
		return type;
	}

	public void setType(ListType type) {
		this.type = type;
	}
	
	
	
}
