package com.rental.app;

/**
 * Interface to populate RentalInventory objects
 * @author Malcolm White
 *
 */
public interface RentalInventoryPopulator {
	
	/**
	 * Populates inner data structure of RentalInventory object
	 * @param inventory
	 * @throws RentalInventoryPopulatorException
	 * @throws InvalidInventoryItemException
	 */
	public void populateInventory(RentalInventory inventory) throws RentalInventoryPopulatorException, InvalidInventoryItemException;
}
