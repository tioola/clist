package com.clist.domain.vo;

public class DMLResult {
	
	public Long count;
	
	public String message;
	
	@Override
	public String toString() {
		return "DMLResult [count=" + count + ", message=" + message + "]";
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
