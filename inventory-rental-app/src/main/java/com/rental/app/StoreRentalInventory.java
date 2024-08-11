package com.rental.app;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that implements RentalInventory inteface.  InventoryItem objects are stored
 * in internal Map<String, InventoryItem>, items.  See {@link RentalInventory} for 
 * operations
 * 
 * @author Malcolm White
 *
 */
public class StoreRentalInventory implements RentalInventory {
	/**
	 * Internal Map used to store InventoryItem objects
	 */
	private Map<String, InventoryItem> items;

	private static String normalizeCode(String code) {
		return code == null ? code : code.toLowerCase();
	}

	public StoreRentalInventory() {
		items = new HashMap<String, InventoryItem>();
	}

	@Override
	public void addInventoryItem(String code, InventoryItem item){
		// TODO Auto-generated method stub
		items.put(normalizeCode(code), item);
	}

	@Override
	public InventoryItem removeInventoryItem(String code) {
		// TODO Auto-generated method stub
		return items.remove(normalizeCode(code));
	}

	@Override
	public InventoryItem getInventoryItem(String code) {
		// TODO Auto-generated method stub
		return items.get(normalizeCode(code));
	}

}
