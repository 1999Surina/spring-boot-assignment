package com.zemoso.springbootassignment.restexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HotelsRestExceptionHandling {
	
	@ExceptionHandler
	public ResponseEntity<HotelsErrorResponse> handleException(HotelsNotFoundException exc){
		
		HotelsErrorResponse error  = new HotelsErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());		
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<HotelsErrorResponse> handleException(Exception exc){
		
		HotelsErrorResponse error  = new HotelsErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());		
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
