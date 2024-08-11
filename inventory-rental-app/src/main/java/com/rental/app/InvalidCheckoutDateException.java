package com.rental.app;

/**
 * Exception for unexpected behavior in RentalInventory objects
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
