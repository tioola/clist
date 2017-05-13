package com.clist.front.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.clist.domain.constants.DefaultErrorCodes;
import com.clist.domain.exceptions.CListDataNotFoundException;
import com.clist.domain.exceptions.CListException;
import com.clist.domain.vo.ErrorMessage;

@ControllerAdvice
public class CListRestExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler({CListException.class})
	public ResponseEntity<ErrorMessage> handleCListException(Exception ex, WebRequest request){
		CListException clistEx = (CListException) ex;
		return new ResponseEntity<ErrorMessage>(new ErrorMessage(clistEx.getErrorCode(), clistEx.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({CListDataNotFoundException.class})
	public ResponseEntity<ErrorMessage> handleCListDataNotFoundException(Exception ex, WebRequest request){
		CListException clistEx = (CListException) ex;
		return new ResponseEntity<ErrorMessage>(new ErrorMessage(clistEx.getErrorCode(), clistEx.getMessage()), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<ErrorMessage> handleRuntimeException(Exception ex, WebRequest request){
		return new ResponseEntity<ErrorMessage>(new ErrorMessage(DefaultErrorCodes.DEFAULT_RUNTIME_EXCEPTION, ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
}
