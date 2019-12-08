package com.example.exception;

public class ElementCanNotBeCreatedException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ElementCanNotBeCreatedException(String message) {
		super(message);
	}
	
}
