package com.clist.domain.exceptions;

public class CListDataNotFoundException extends CListException {

	private static final long serialVersionUID = 1L;

	public CListDataNotFoundException(Integer errorCode, String message) {
		super(errorCode, message);
	}


}
