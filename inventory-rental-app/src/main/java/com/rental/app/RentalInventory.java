package com.rental.app;

/**
 * 
 * Class that stores InventoryItem objects. Operations include:
 * <pre>
 * - Add InventoryObject item
 * - Remove InventoryObject item
 * - Retrieve InventoryObject iteem
 * - Create RentalAgreement object from InventoryItem object
 * </pre>
 * 
 * @author Malcolm White
 *
 */
public interface RentalInventory {
	/**
	 * Creates a RentalAgreement for InventoryItem object retrieved by code.
	 * 
	 * @param code unique code used to retrieve InventoryItem object to create 
	 * 			   RentalAgreement for
	 * @param numRentalDays number of days item is rented for
	 * @param discountPercent discount percent
	 * @param checkoutDate date item is checked out
	 * @return RentalAgreement object
	 * @throws RentalInventoryException
	 */
	public RentalAgreement createRentalAgreement(String code, int numRentalDays, int discountPercent,
			String checkoutDate) throws RentalInventoryException;
	
	/**
	 * Adds InventoryItem object denoted by item at key denoted by code.  If item is
	 * null InvalidInventoryItemException is thrown.
	 * 
	 * @param code key
	 * @param item value
	 * @throws InvalidInventoryItemException
	 */
	public void addInventoryItem(String code, InventoryItem item) throws InvalidInventoryItemException;
	
	/**
	 * Removes InventoryItem object at key code
	 * @param code key
	 * @return removed InventoryItem object. Returns null if it does not exist
	 */
	public InventoryItem removeInventoryItem(String code);
	
	/**
	 * Retrieves InventoryItem object by key code
	 * @param code key to retrieve InventoryItem object by
	 * @return
	 */
	public InventoryItem getInventoryItem(String code);
}
