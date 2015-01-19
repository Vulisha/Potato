package com.awfa.potato.exceptions;

public class MissingComponentException extends RuntimeException {
	public MissingComponentException() {
		
	}
	
	public MissingComponentException(String message) {
		super(message);
	}
}
