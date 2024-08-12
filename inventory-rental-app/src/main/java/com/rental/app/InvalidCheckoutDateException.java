package com.rental.app;

/**
 * Exception thrown when checkout date is invalid
 * @author Malcolm White
 *
 */
public class InvalidCheckoutDateException extends CheckoutException {
	private static final long serialVersionUID = 1L;

	public InvalidCheckoutDateException(String message) {
		super(message);
	}

	public InvalidCheckoutDateException(String message, Throwable cause) {
		super(message, cause);
	}
}
