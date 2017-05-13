package com.clist.domain.exceptions;

public class CListException extends Exception{

	private static final long serialVersionUID = 1L;
	
	protected Integer errorCode;
	
	public CListException(Integer errorCode, String message){
		super(message);
		this.errorCode = errorCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
}
