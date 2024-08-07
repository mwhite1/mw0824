package com.rental.app;

public interface RentalInventory {
	public RentalAgreement createRentalAgreement(String code, int numRentalDays, int discountPercent,
			String checkoutDate) throws RentalInventoryException;
	public void addInventoryItem(String code, InventoryItem item) throws InvalidInventoryItemException;
	public InventoryItem removeTool(String toolCode);
}
