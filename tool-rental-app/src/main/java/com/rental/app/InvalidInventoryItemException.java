package com.rental.app;

public class InvalidInventoryItemException extends RentalInventoryException{
	private static final long serialVersionUID = 1L;

	public InvalidInventoryItemException(String message) {
		super(message);
	}
	
	public InvalidInventoryItemException(String message, Throwable cause) {
		super(message,cause);
	}
}
