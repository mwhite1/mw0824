package com.rental.app;

public class ItemDoesNotExistException extends RentalInventoryException{
	private static final long serialVersionUID = 1L;

	public ItemDoesNotExistException(String message) {
		super(message);
	}
	
	public ItemDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}
}
