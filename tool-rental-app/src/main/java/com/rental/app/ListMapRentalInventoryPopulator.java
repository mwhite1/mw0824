package com.rental.app;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ListMapRentalInventoryPopulator implements RentalInventoryPopulator {
	private static final String TYPE_NAME_KEY = "name";
	private static final String TYPE_DAILY_CHARGE_KEY = "dailyCharge";
	private static final String TYPE_WEEKEND_CHARGE_KEY = "weekendCharge";
	private static final String TYPE_WEEKDAY_CHARGE_KEY = "weekdayCharge";
	private static final String TYPE_HOLIDAY_CHARGE_KEY = "holidayCharge";
	private static final String ITEM_CODE_KEY = "code";
	private static final String ITEM_TYPE_KEY = "type";
	private static final String ITEM_BRAND_KEY = "brand";

	private List<HashMap<String, String>> itemMapList;
	private HashMap<String, HashMap<String, String>> typeMap;

	public ListMapRentalInventoryPopulator(List<HashMap<String, String>> itemMapList,
			HashMap<String, HashMap<String, String>> typeMap) throws IllegalArgumentException {
		if (itemMapList == null)
			throw new IllegalArgumentException("itemMap cannot be null");
		if (typeMap == null)
			throw new IllegalArgumentException("typeMap cannot be null");
		this.itemMapList = itemMapList;
		this.typeMap = typeMap;
	}


	private InventoryItemType createInventoryItemType(String name, HashMap<String, String> typeAttrs) {
		double dailyCharge = Double.parseDouble(typeAttrs.getOrDefault(TYPE_DAILY_CHARGE_KEY, "0.00"));
		boolean isWeekendCharge = Boolean.parseBoolean(typeAttrs.getOrDefault(TYPE_WEEKEND_CHARGE_KEY, "false"));
		boolean isWeekdayCharge = Boolean.parseBoolean(typeAttrs.getOrDefault(TYPE_WEEKDAY_CHARGE_KEY, "false"));
		boolean isHolidayCharge = Boolean.parseBoolean(typeAttrs.getOrDefault(TYPE_HOLIDAY_CHARGE_KEY, "false"));
		return new InventoryItemType(name, dailyCharge, isWeekdayCharge, isWeekendCharge, isHolidayCharge);
	}

	@Override
	public void populateInventory(RentalInventory inventory) throws RentalInventoryPopulatorException, InvalidInventoryItemException {
		// TODO Auto-generated method stub
		for (HashMap<String, String> itemMap : itemMapList) {
			String itemCode = itemMap.get(ITEM_CODE_KEY);
			if (itemCode == null)
				throw new RentalInventoryPopulatorException("item name cannot be null");
			String itemType = itemMap.get(ITEM_TYPE_KEY);
			if (itemType == null)
				throw new RentalInventoryPopulatorException("item type cannot be null");
			InventoryItemType inventoryItemType = createInventoryItemType(itemType,
					typeMap.getOrDefault(itemType, new HashMap<String, String>()));
			String itemBrand = itemMap.getOrDefault(ITEM_BRAND_KEY, "");
			inventory.addInventoryItem(itemCode.toLowerCase(), new InventoryItem(itemCode,inventoryItemType,itemBrand));
		}
	}

}
