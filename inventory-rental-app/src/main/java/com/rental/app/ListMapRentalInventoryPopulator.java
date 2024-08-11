package com.rental.app;

import java.util.List;
import java.util.Map;

/**
 * Populates RentalInventory object utilizing a list of InventoryItem attribute
 * maps and a Map containing InventoryItemType objects mapped to String type
 * names
 * 
 * @author Malcolm White
 *
 */
public class ListMapRentalInventoryPopulator implements RentalInventoryPopulator {

	/**
	 * List containing maps and each map containing attributes used to generate
	 * InventoryItem objects
	 */
	private List<Map<String, String>> itemMapList;

	/**
	 * Map containing InventoryItemTypes used to populate type attribute of
	 * corresponding InventoryItem object
	 */
	private Map<String, InventoryItemType> typeMap;

	/**
	 * Throws IllegalArgumentException if either itemMapList or typeMap are null
	 * @param itemMapList
	 * @param typeMap
	 */
	public ListMapRentalInventoryPopulator(List<Map<String, String>> itemMapList,
			Map<String, InventoryItemType> typeMap) {
		if (itemMapList == null)
			throw new IllegalArgumentException("itemMap cannot be null");
		if (typeMap == null)
			throw new IllegalArgumentException("typeMap cannot be null");
		this.itemMapList = itemMapList;
		this.typeMap = typeMap;
	}

	/**
	 * Utilizes itemMapList and typeMap to populate internal data structure of inventory object.
	 * For each item map in itemMapList, the following steps are executed:
	 * <pre>
	 * - Retrieves code and type attributes. IllegalArgumentException is thrown 
	 *   if either are null
	 * - Uses type attribute to retrieves corresponding InventoryItemType from typeMap
	 * - Retrieves brand attribute
	 * - Creates InventoryItem object with these attributes and adds resulting object to
	 *   inventory
	 * </pre> 
	 * 
	 */
	@Override
	public void populateInventory(RentalInventory inventory)
			throws RentalInventoryPopulatorException, InvalidInventoryItemException {
		// TODO Auto-generated method stub
		if (inventory == null) {
			throw new IllegalArgumentException("inventory cannot be null");
		}
		for (Map<String, String> itemMap : itemMapList) {
			String itemCode = itemMap.get(InventoryItemAttrNames.CODE.label);
			if (itemCode == null)
				throw new RentalInventoryPopulatorException("item name cannot be null");
			String itemType = itemMap.get(InventoryItemAttrNames.TYPE.label);
			if (itemType == null)
				throw new RentalInventoryPopulatorException("item type cannot be null");
			InventoryItemType inventoryItemType = typeMap.get(itemType);
			String itemBrand = itemMap.getOrDefault(InventoryItemAttrNames.BRAND.label, "");
			inventory.addInventoryItem(itemCode, new InventoryItem(itemCode, inventoryItemType, itemBrand));
		}
	}

}
