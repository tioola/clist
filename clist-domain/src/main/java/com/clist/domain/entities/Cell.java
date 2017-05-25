package com.clist.domain.entities;

public class Cell {
	
	private String position;
	
	private String text;

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return " {\"position\":\"" + position + "\",\"text\":\"" + text + "\"}";
	}

	
}
