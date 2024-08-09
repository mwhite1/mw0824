package com.rental.app;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class ListMapRentalInventoryPopulatorTest {

	private static final String TEST_TYPE_NAME = "ladder";
	private static final double TEST_DAILY_CHARGE = 1.99;
	private static final boolean TEST_WEEKEND_CHARGE = true;
	private static final boolean TEST_WEEKDAY_CHARGE = true;
	private static final boolean TEST_HOLIDAY_CHARGE = true;
	private static final String TEST_BRAND_NAME = "Test Brand";
	private static final String TEST_ITEM_CODE = "TST";
	private ArrayList<HashMap<String, String>> items;
	private HashMap<String, InventoryItemType> types;
	private InventoryItemType type;
	private StoreRentalInventory inventory;

	@Before
	public void setUp() {
		items = new ArrayList<HashMap<String, String>>();
		types = new HashMap<String, InventoryItemType>();
		type = new InventoryItemType(TEST_TYPE_NAME,TEST_DAILY_CHARGE,TEST_WEEKDAY_CHARGE,TEST_WEEKEND_CHARGE,TEST_HOLIDAY_CHARGE);
		items.add(new HashMap<String, String>());
		items.get(0).put(InventoryItemAttrNames.CODE.label, TEST_ITEM_CODE);
		items.get(0).put(InventoryItemAttrNames.BRAND.label, TEST_BRAND_NAME);
		items.get(0).put(InventoryItemAttrNames.TYPE.label, TEST_TYPE_NAME);
		inventory = new StoreRentalInventory(new ToolRentalAgreementGenerator());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nullItemMapListShouldThrowException() {
		new ListMapRentalInventoryPopulator(null, types);
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
		items.get(0).put(InventoryItemAttrNames.CODE.label, null);
		ListMapRentalInventoryPopulator populator = new ListMapRentalInventoryPopulator(items, types);
		populator.populateInventory(inventory);
	}

	@Test(expected = RentalInventoryPopulatorException.class)
	public void nullTypeCodeShouldThrowException()
			throws InvalidInventoryItemException, RentalInventoryPopulatorException {
		items.get(0).put(InventoryItemAttrNames.TYPE.label, null);
		ListMapRentalInventoryPopulator populator = new ListMapRentalInventoryPopulator(items, types);
		populator.populateInventory(inventory);
	}

	@Test
	public void shouldPopulateInventorySuccessfully() throws InvalidInventoryItemException, RentalInventoryPopulatorException {
		InventoryItem populatedItem = new InventoryItem(TEST_ITEM_CODE, type, TEST_BRAND_NAME);
		types.put(TEST_TYPE_NAME, type);
		ListMapRentalInventoryPopulator populator = new ListMapRentalInventoryPopulator(items, types);
		populator.populateInventory(inventory);
		assertEquals(populatedItem,inventory.getInventoryItem(TEST_ITEM_CODE));
	}
}
