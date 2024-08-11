package com.rental.app;

/**
 * Exception for unexpected behavior in Checkout objects
 * @author Malcolm White
 *
 */
public class CheckoutException extends Exception {
	private static final long serialVersionUID = 1L;

	public CheckoutException(String message) {
		super(message);
	}

	public CheckoutException(String message, Throwable cause) {
		super(message, cause);
	}
}
