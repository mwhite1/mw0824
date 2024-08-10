package com.rental.app;

/**
 * Exception indicating that the discount percent is not in acceptable range
 * @author Malcolm White
 *
 */
public class DiscountPercentOutOfRangeException extends RentalInventoryException{
	private static final long serialVersionUID = 1L;

	public DiscountPercentOutOfRangeException(String message) {
		super(message);
	}
	
	public DiscountPercentOutOfRangeException(String message, Throwable cause) {
		super(message, cause);
	}
}
