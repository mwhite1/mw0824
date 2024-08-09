package com.rental.app;

public interface RentalInventoryPopulator {
	public void populateInventory(RentalInventory inventory) throws RentalInventoryPopulatorException, InvalidInventoryItemException;
}
