package com.rental.app;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class ListMapRentalInventoryPopulatorTest {

	private static final String TYPE_DAILY_CHARGE_KEY = "dailyCharge";
	private static final String TYPE_WEEKEND_CHARGE_KEY = "weekendCharge";
	private static final String TYPE_WEEKDAY_CHARGE_KEY = "weekdayCharge";
	private static final String TYPE_HOLIDAY_CHARGE_KEY = "holidayCharge";
	private static final String ITEM_CODE_KEY = "code";
	private static final String ITEM_TYPE_KEY = "type";
	private static final String ITEM_BRAND_KEY = "brand";
	private static final String TEST_TYPE_NAME = "ladder";
	private static final String TEST_TYPE_NAME_2 = "chainsaw";
	private static final String TEST_DAILY_CHARGE = "1.99";
	private static final String TEST_WEEKEND_CHARGE = "true";
	private static final String TEST_WEEKDAY_CHARGE = "true";
	private static final String TEST_HOLIDAY_CHARGE = "true";
	private static final String TEST_BRAND_NAME = "Test Brand";
	private static final String TEST_ITEM_CODE = "TST";
	private static final String TEST_ITEM_CODE_2 = "TST1";

	private ArrayList<HashMap<String, String>> items;
	private HashMap<String, HashMap<String, String>> types;
	private HashMap<String, String> type;
	private StoreRentalInventory inventory;

	@Before
	public void setUp() {
		items = new ArrayList<HashMap<String, String>>();
		types = new HashMap<String, HashMap<String, String>>();
		type = new HashMap<String, String>();
		items.add(new HashMap<String, String>());
		items.get(0).put(ITEM_CODE_KEY, TEST_ITEM_CODE);
		items.get(0).put(ITEM_BRAND_KEY, TEST_BRAND_NAME);
		items.get(0).put(ITEM_TYPE_KEY, TEST_TYPE_NAME);
		type.put(TYPE_DAILY_CHARGE_KEY, TEST_DAILY_CHARGE);
		type.put(TYPE_WEEKEND_CHARGE_KEY, TEST_WEEKEND_CHARGE);
		type.put(TYPE_WEEKDAY_CHARGE_KEY, TEST_WEEKDAY_CHARGE);
		type.put(TYPE_HOLIDAY_CHARGE_KEY, TEST_HOLIDAY_CHARGE);
		inventory = new StoreRentalInventory(new ToolRentalAgreementGenerator());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullItemMapListShouldThrowException() {
		new ListMapRentalInventoryPopulator(null, new HashMap<String, HashMap<String, String>>());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullTypeMapListShouldThrowException() {
		new ListMapRentalInventoryPopulator(new ArrayList<HashMap<String, String>>(), null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullInventoryShouldThrowException()
			throws InvalidInventoryItemException, RentalInventoryPopulatorException {

		ListMapRentalInventoryPopulator populator = new ListMapRentalInventoryPopulator(items, types);
		populator.populateInventory(null);
	}

	@Test(expected = RentalInventoryPopulatorException.class)
	public void nullItemCodeShouldThrowException()
			throws InvalidInventoryItemException, RentalInventoryPopulatorException {
		items.get(0).put(ITEM_CODE_KEY, null);
		ListMapRentalInventoryPopulator populator = new ListMapRentalInventoryPopulator(items, types);
		populator.populateInventory(inventory);
	}

	@Test(expected = RentalInventoryPopulatorException.class)
	public void nullTypeCodeShouldThrowException()
			throws InvalidInventoryItemException, RentalInventoryPopulatorException {
		items.get(0).put(ITEM_TYPE_KEY, null);
		ListMapRentalInventoryPopulator populator = new ListMapRentalInventoryPopulator(items, types);
		populator.populateInventory(inventory);
	}

	@Test
	public void shouldPopulateInventorySuccessfully() throws InvalidInventoryItemException, RentalInventoryPopulatorException {
		InventoryItemType populatedType = new InventoryItemType(TEST_TYPE_NAME, Double.parseDouble(TEST_DAILY_CHARGE),
				Boolean.parseBoolean(TEST_WEEKDAY_CHARGE), Boolean.parseBoolean(TEST_WEEKEND_CHARGE),
				Boolean.parseBoolean(TEST_HOLIDAY_CHARGE));
		InventoryItemType blankType = new InventoryItemType(TEST_TYPE_NAME_2, 0.00, false, false, false);
		InventoryItem populatedItem = new InventoryItem(TEST_ITEM_CODE, populatedType, TEST_BRAND_NAME);
		InventoryItem blankItem = new InventoryItem(TEST_ITEM_CODE_2, blankType, "");
		HashMap<String,String> blankItemMap = new HashMap<String,String>();
		blankItemMap.put(ITEM_CODE_KEY, TEST_ITEM_CODE_2);
		blankItemMap.put(ITEM_TYPE_KEY, TEST_TYPE_NAME_2);
		items.add(blankItemMap);
		types.put(TEST_TYPE_NAME, type);
		ListMapRentalInventoryPopulator populator = new ListMapRentalInventoryPopulator(items, types);
		populator.populateInventory(inventory);
		assertEquals(populatedItem,inventory.getInventoryItem(TEST_ITEM_CODE));
		assertEquals(blankItem,inventory.getInventoryItem(TEST_ITEM_CODE_2));
	}
}
