package com.rental.app;

/**
 * Exception for unexpected behavior in RentalInventory objects
 * @author Malcolm White
 *
 */
public class RentalInventoryException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public RentalInventoryException(String message) {
		super(message);
	}
	
	public RentalInventoryException(String message, Throwable cause) {
		super(message, cause);
	}
}
