package com.rental.app;

public interface RentalInventory {
	public RentalAgreement createRentalAgreement(String code, int numRentalDays, int discountPercent,
			String checkoutDate) throws RentalInventoryException;
	public void addInventoryItem(String code, InventoryItem item) throws InvalidInventoryItemException;
	public InventoryItem removeInventoryItem(String code);
	public InventoryItem getInventoryItem(String code);
}
