package com.payment.microservice.exception;

public class InvalidTokenException extends RuntimeException{
	
	public InvalidTokenException(String message) {
		super(message);
	}

}
