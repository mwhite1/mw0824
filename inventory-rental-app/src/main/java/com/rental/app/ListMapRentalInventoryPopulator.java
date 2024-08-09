package com.rental.app;

import java.util.HashMap;
import java.util.List;

public class ListMapRentalInventoryPopulator implements RentalInventoryPopulator {
	private List<HashMap<String, String>> itemMapList;
	private HashMap<String, InventoryItemType> typeMap;

	public ListMapRentalInventoryPopulator(List<HashMap<String, String>> itemMapList,
			HashMap<String, InventoryItemType> typeMap) {
		if (itemMapList == null)
			throw new IllegalArgumentException("itemMap cannot be null");
		if (typeMap == null)
			throw new IllegalArgumentException("typeMap cannot be null");
		this.itemMapList = itemMapList;
		this.typeMap = typeMap;
	}

	@Override
	public void populateInventory(RentalInventory inventory)
			throws RentalInventoryPopulatorException, InvalidInventoryItemException {
		// TODO Auto-generated method stub
		if (inventory == null) {
			throw new IllegalArgumentException("inventory cannot be null");
		}
		for (HashMap<String, String> itemMap : itemMapList) {
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
