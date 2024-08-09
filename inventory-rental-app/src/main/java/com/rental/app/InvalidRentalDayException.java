package com.rental.app;

public class InvalidRentalDayException extends RentalInventoryException {
	private static final long serialVersionUID = 1L;
	
	public InvalidRentalDayException(String message) {
		super(message);
	}
	
	public InvalidRentalDayException(String message, Throwable cause) {
		super(message,cause);
	}
}
