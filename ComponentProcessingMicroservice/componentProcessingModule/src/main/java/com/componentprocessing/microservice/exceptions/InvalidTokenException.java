package com.componentprocessing.microservice.exceptions;

public class InvalidTokenException extends RuntimeException{
	
	public InvalidTokenException(String message) {
		super(message);
	}

}
