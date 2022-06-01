package com.zemoso.springbootassignment.restexception;

public class HotelsNotFoundException extends RuntimeException{

	public HotelsNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public HotelsNotFoundException(String message) {
		super(message);
	}

	public HotelsNotFoundException(Throwable cause) {
		super(cause);
	}

}
