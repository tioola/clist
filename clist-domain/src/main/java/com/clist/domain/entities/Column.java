package com.clist.domain.entities;

import com.clist.domain.enums.Visibility;

public class Column {
	
	private String position;
	
	private Long size;
	
	private Visibility visibleTo;
	
	private String description;

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Visibility getVisibleTo() {
		return visibleTo;
	}

	public void setVisibleTo(Visibility visibleTo) {
		this.visibleTo = visibleTo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
