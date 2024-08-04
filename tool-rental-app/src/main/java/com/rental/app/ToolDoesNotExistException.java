package com.rental.app;

public class ToolDoesNotExistException extends CheckoutException{
	private static final long serialVersionUID = 1L;

	public ToolDoesNotExistException(String message) {
		super(message);
	}
	
	public ToolDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}
}
