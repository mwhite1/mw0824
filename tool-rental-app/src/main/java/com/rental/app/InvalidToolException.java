package com.rental.app;

public class InvalidToolException extends CheckoutException{
	private static final long serialVersionUID = 1L;

	public InvalidToolException(String message) {
		super(message);
	}
	
	public InvalidToolException(String message, Throwable cause) {
		super(message,cause);
	}
}
