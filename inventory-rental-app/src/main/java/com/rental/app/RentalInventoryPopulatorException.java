package com.rental.app;

public class RentalInventoryPopulatorException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7679826480093010375L;

	public RentalInventoryPopulatorException(String message) {
		super(message);
	}
	
	public RentalInventoryPopulatorException(String message, Throwable cause) {
		super(message, cause);
	}
}
