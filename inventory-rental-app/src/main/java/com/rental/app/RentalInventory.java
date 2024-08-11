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
	 * Adds InventoryItem object denoted by item at key denoted by code.  If item is
	 * null InvalidInventoryItemException is thrown.
	 * 
	 * @param code key
	 * @param item value
	 * @throws InvalidInventoryItemException
	 */
	public void addInventoryItem(String code, InventoryItem item);

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
