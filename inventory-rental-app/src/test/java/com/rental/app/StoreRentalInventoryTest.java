package com.rental.app;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.apache.commons.math3.util.Precision;
import static org.junit.Assert.assertEquals;

public class StoreRentalInventoryTest {
	private StoreRentalInventory storeInventory;
	private HashMap<String, InventoryItem> storeInventoryMap;

	@Before
	public void setupBefore() throws InvalidInventoryItemException {
		InventoryItemType ladder = new InventoryItemType("Ladder", 1.99, true, true, false);
		InventoryItemType chainsaw = new InventoryItemType("Chainsaw", 1.49, true, false, true);
		InventoryItemType jackhammer = new InventoryItemType("Jackhammer", 2.99, true, false, false);
		storeInventory = new StoreRentalInventory(new ToolRentalAgreementGenerator());

		storeInventoryMap = new HashMap<String, InventoryItem>();
		storeInventoryMap.put("CHNS", new InventoryItem("CHNS", chainsaw, "Stihl"));
		storeInventoryMap.put("LADW", new InventoryItem("LADW", ladder, "Werner"));
		storeInventoryMap.put("JAKD", new InventoryItem("JAKD", jackhammer, "DeWalt"));
		storeInventoryMap.put("JAKR", new InventoryItem("JAKR", jackhammer, "Ridgid"));

		for (Map.Entry<String, InventoryItem> set : storeInventoryMap.entrySet()) {
			storeInventory.addInventoryItem(set.getKey(), set.getValue());
		}
	}
	
	@Test(expected = DiscountPercentOutOfRangeException.class)
	public void test1() throws RentalInventoryException {
		storeInventory.createRentalAgreement("JAKR", 5, 101, "09/03/15");
	}
	
	@Test
	public void test2() throws RentalInventoryException {
		InventoryItem item = storeInventoryMap.get("LADW");
		String checkoutDate = "07/02/20";
		String dueDate = "07/05/20";
		Integer rentalDays = 3;
		Integer chargeDays = 2;
		Double dailyCharge = item.getType().getDailyCharge();
		Double preDiscountCharge = dailyCharge * chargeDays;
		Integer discountPercent = 10;
		Double discountAmount = Precision.round(preDiscountCharge * (discountPercent / 100D), 2);
		Double finalCharge = preDiscountCharge - discountAmount;
		ToolRentalAgreement expectedRentalAgreement = new ToolRentalAgreement(item.getCode(),
				item.getType().getName(), item.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays,
				preDiscountCharge, discountPercent, discountAmount, finalCharge);
		ToolRentalAgreement actualRentalAgreement = (ToolRentalAgreement) storeInventory
				.createRentalAgreement(item.getCode(), rentalDays, discountPercent, checkoutDate);
		assertEquals(expectedRentalAgreement, actualRentalAgreement);
	}
	
	@Test
	public void test3() throws RentalInventoryException {
		InventoryItem item = storeInventoryMap.get("CHNS");
		String checkoutDate = "07/02/15";
		String dueDate = "07/07/15";
		Integer rentalDays = 5;
		Integer chargeDays = 3;
		Double dailyCharge = item.getType().getDailyCharge();
		Double preDiscountCharge = dailyCharge * chargeDays;
		Integer discountPercent = 25;
		Double discountAmount = Precision.round(preDiscountCharge * (discountPercent / 100D), 2);
		Double finalCharge = preDiscountCharge - discountAmount;
		ToolRentalAgreement expectedRentalAgreement = new ToolRentalAgreement(item.getCode(),
				item.getType().getName(), item.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays,
				preDiscountCharge, discountPercent, discountAmount, finalCharge);
		ToolRentalAgreement actualRentalAgreement = (ToolRentalAgreement) storeInventory
				.createRentalAgreement(item.getCode(), rentalDays, discountPercent, checkoutDate);
		assertEquals(expectedRentalAgreement, actualRentalAgreement);
	}
	
	@Test
	public void test4() throws RentalInventoryException {
		InventoryItem item = storeInventoryMap.get("JAKD");
		String checkoutDate = "09/03/15";
		String dueDate = "09/09/15";
		Integer rentalDays = 6;
		Integer chargeDays = 3;
		Double dailyCharge = item.getType().getDailyCharge();
		Double preDiscountCharge = dailyCharge * chargeDays;
		Integer discountPercent = 0;
		Double discountAmount = Precision.round(preDiscountCharge * (discountPercent / 100D), 2);
		Double finalCharge = preDiscountCharge - discountAmount;
		ToolRentalAgreement expectedRentalAgreement = new ToolRentalAgreement(item.getCode(),
				item.getType().getName(), item.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays,
				preDiscountCharge, discountPercent, discountAmount, finalCharge);
		ToolRentalAgreement actualRentalAgreement = (ToolRentalAgreement) storeInventory
				.createRentalAgreement(item.getCode(), rentalDays, discountPercent, checkoutDate);
		assertEquals(expectedRentalAgreement, actualRentalAgreement);
	}
	
	@Test
	public void test5() throws RentalInventoryException {
		InventoryItem item = storeInventoryMap.get("JAKR");
		String checkoutDate = "07/02/15";
		String dueDate = "07/11/15";
		Integer rentalDays = 9;
		Integer chargeDays = 6;
		Double dailyCharge = item.getType().getDailyCharge();
		Double preDiscountCharge = dailyCharge * chargeDays;
		Integer discountPercent = 0;
		Double discountAmount = Precision.round(preDiscountCharge * (discountPercent / 100D), 2);
		Double finalCharge = preDiscountCharge - discountAmount;
		ToolRentalAgreement expectedRentalAgreement = new ToolRentalAgreement(item.getCode(),
				item.getType().getName(), item.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays,
				preDiscountCharge, discountPercent, discountAmount, finalCharge);
		ToolRentalAgreement actualRentalAgreement = (ToolRentalAgreement) storeInventory
				.createRentalAgreement(item.getCode(), rentalDays, discountPercent, checkoutDate);
		assertEquals(expectedRentalAgreement, actualRentalAgreement);
	}
	
	@Test
	public void test6() throws RentalInventoryException {
		InventoryItem item = storeInventoryMap.get("JAKR");
		String checkoutDate = "07/02/20";
		String dueDate = "07/06/20";
		Integer rentalDays = 4;
		Integer chargeDays = 2;
		Double dailyCharge = item.getType().getDailyCharge();
		Double preDiscountCharge = dailyCharge * chargeDays;
		Integer discountPercent = 50;
		Double discountAmount = Precision.round(preDiscountCharge * (discountPercent / 100D), 2);
		Double finalCharge = preDiscountCharge - discountAmount;
		ToolRentalAgreement expectedRentalAgreement = new ToolRentalAgreement(item.getCode(),
				item.getType().getName(), item.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays,
				preDiscountCharge, discountPercent, discountAmount, finalCharge);
		ToolRentalAgreement actualRentalAgreement = (ToolRentalAgreement) storeInventory
				.createRentalAgreement(item.getCode(), rentalDays, discountPercent, checkoutDate);
		assertEquals(expectedRentalAgreement, actualRentalAgreement);
	}
	
	@Test(expected = InvalidRentalDayException.class)
	public void shouldThrowInvalidRentalDayException() throws RentalInventoryException {
		storeInventory.createRentalAgreement("JAKR", 0, 10, "09/03/15");
	}

	@Test(expected = InvalidInventoryItemException.class)
	public void shouldThrowInvalidToolException() throws RentalInventoryException {
		storeInventory.addInventoryItem("TEST", new InventoryItem("TEST", null, "test"));
		storeInventory.createRentalAgreement("TEST", 1, 10, "09/03/15");
	}

	@Test(expected = ItemDoesNotExistException.class)
	public void shouldThrowToolDoesNotExistException() throws RentalInventoryException {
		storeInventory.createRentalAgreement("TEST", 1, 10, "09/03/15");
	}
	
	@Test(expected = RentalInventoryException.class)
	public void shouldThrowRentalInventoryExceptionForBadDate() throws RentalInventoryException {
		storeInventory.createRentalAgreement("JAKR", 0, 10, "09/03/155");
	}
}
