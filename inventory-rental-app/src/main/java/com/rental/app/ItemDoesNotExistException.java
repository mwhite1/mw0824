package com.rental.app;

/**
 * Exception indicating that an InventoryItem does not exist
 *
 * @author Malcolm White
 *
 */
public class ItemDoesNotExistException extends RentalInventoryException{
	private static final long serialVersionUID = 1L;

	public ItemDoesNotExistException(String message) {
		super(message);
	}
	
	public ItemDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}
}
