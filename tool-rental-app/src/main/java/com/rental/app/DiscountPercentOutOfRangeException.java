package com.rental.app;

public class DiscountPercentOutOfRangeException extends CheckoutException{
	private static final long serialVersionUID = 1L;

	public DiscountPercentOutOfRangeException(String message) {
		super(message);
	}
	
	public DiscountPercentOutOfRangeException(String message, Throwable cause) {
		super(message, cause);
	}
}
