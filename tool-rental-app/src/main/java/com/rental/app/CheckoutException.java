package com.rental.app;

public class CheckoutException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public CheckoutException(String message) {
		super(message);
	}
	
	public CheckoutException(String message, Throwable cause) {
		super(message, cause);
	}
}
