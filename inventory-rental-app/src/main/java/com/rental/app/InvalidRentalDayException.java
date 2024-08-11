package com.rental.app;

/**
 * Exception indicating that rental day is not a valid value
 * @author Malcolm White
 *
 */
public class InvalidRentalDayException extends CheckoutException {
	private static final long serialVersionUID = 1L;

	public InvalidRentalDayException(String message) {
		super(message);
	}

	public InvalidRentalDayException(String message, Throwable cause) {
		super(message,cause);
	}
}
