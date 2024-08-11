package com.rental.app;

import static org.junit.Assert.assertEquals;

import java.time.DayOfWeek;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.util.Precision;
import org.junit.Before;
import org.junit.Test;

public class CheckoutTest {
	private StoreRentalInventory storeInventory;
	private HashMap<String, InventoryItem> storeInventoryMap;
	private List<Holiday> holidays;
	private Checkout checkout;

	private static void printTest(String code, String checkoutDate, int rentalDays, int discountPercent) {
		StringBuilder builder = new StringBuilder();
		builder.append("Testing these values:\n");
		builder.append(String.format("Tool code: %s\n",code));
		builder.append(String.format("Checkout Date: %s\n", checkoutDate));
		builder.append(String.format("Rental Days: %s\n", rentalDays));
		builder.append(String.format("Discount: %d%%\n", discountPercent));
		System.out.println(builder.toString());
	}

	@Before
	public void setupBefore() throws InvalidInventoryItemException {
		InventoryItemType ladder = new InventoryItemType("Ladder", 1.99, true, true, false);
		InventoryItemType chainsaw = new InventoryItemType("Chainsaw", 1.49, true, false, true);
		InventoryItemType jackhammer = new InventoryItemType("Jackhammer", 2.99, true, false, false);
		storeInventory = new StoreRentalInventory();

		storeInventoryMap = new HashMap<String, InventoryItem>();
		storeInventoryMap.put("CHNS", new InventoryItem("CHNS", chainsaw, "Stihl"));
		storeInventoryMap.put("LADW", new InventoryItem("LADW", ladder, "Werner"));
		storeInventoryMap.put("JAKD", new InventoryItem("JAKD", jackhammer, "DeWalt"));
		storeInventoryMap.put("JAKR", new InventoryItem("JAKR", jackhammer, "Ridgid"));

		for (Map.Entry<String, InventoryItem> set : storeInventoryMap.entrySet()) {
			storeInventory.addInventoryItem(set.getKey(), set.getValue());
		}

		holidays = new ArrayList<Holiday>();
		holidays.add(new Holiday(Month.JULY, 4, null, 0));
		holidays.add(new Holiday(Month.SEPTEMBER, 0, DayOfWeek.MONDAY, 1));

		checkout = new Checkout(storeInventory, new RentalAgreementGenerator());
	}

	@Test(expected = DiscountPercentOutOfRangeException.class)
	public void test1() throws CheckoutException {
		String code = "JAKR";
		int numRentalDays = 5;
		int discountPercent = 101;
		String checkoutDate = "09/03/15";	
		printTest(code,checkoutDate,numRentalDays,discountPercent);
		checkout.createRentalAgreement(code, numRentalDays, discountPercent, checkoutDate, holidays);
	}

	@Test
	public void test2() throws CheckoutException {
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

		printTest(item.getCode(),checkoutDate,rentalDays,discountPercent);
		RentalAgreement expectedRentalAgreement = new RentalAgreement(item.getCode(), item.getType().getName(),
				item.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays, preDiscountCharge,
				discountPercent, discountAmount, finalCharge);
		RentalAgreement actualRentalAgreement = checkout.createRentalAgreement(item.getCode(), rentalDays,
				discountPercent, checkoutDate, holidays);
		assertEquals(expectedRentalAgreement, actualRentalAgreement);
	}

	@Test
	public void test3() throws CheckoutException {
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
		printTest(item.getCode(),checkoutDate,rentalDays,discountPercent);
		RentalAgreement expectedRentalAgreement = new RentalAgreement(item.getCode(), item.getType().getName(),
				item.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays, preDiscountCharge,
				discountPercent, discountAmount, finalCharge);
		RentalAgreement actualRentalAgreement = checkout.createRentalAgreement(item.getCode(), rentalDays,
				discountPercent, checkoutDate, holidays);
		assertEquals(expectedRentalAgreement, actualRentalAgreement);
	}

	@Test
	public void test4() throws CheckoutException {
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
		printTest(item.getCode(),checkoutDate,rentalDays,discountPercent);
		RentalAgreement expectedRentalAgreement = new RentalAgreement(item.getCode(), item.getType().getName(),
				item.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays, preDiscountCharge,
				discountPercent, discountAmount, finalCharge);
		RentalAgreement actualRentalAgreement = checkout.createRentalAgreement(item.getCode(), rentalDays,
				discountPercent, checkoutDate, holidays);
		assertEquals(expectedRentalAgreement, actualRentalAgreement);
	}

	@Test
	public void test5() throws CheckoutException {
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
		printTest(item.getCode(),checkoutDate,rentalDays,discountPercent);
		RentalAgreement expectedRentalAgreement = new RentalAgreement(item.getCode(), item.getType().getName(),
				item.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays, preDiscountCharge,
				discountPercent, discountAmount, finalCharge);
		RentalAgreement actualRentalAgreement = checkout.createRentalAgreement(item.getCode(), rentalDays,
				discountPercent, checkoutDate, holidays);
		assertEquals(expectedRentalAgreement, actualRentalAgreement);
	}

	@Test
	public void test6() throws CheckoutException {
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
		printTest(item.getCode(),checkoutDate,rentalDays,discountPercent);
		RentalAgreement expectedRentalAgreement = new RentalAgreement(item.getCode(), item.getType().getName(),
				item.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays, preDiscountCharge,
				discountPercent, discountAmount, finalCharge);
		RentalAgreement actualRentalAgreement = checkout.createRentalAgreement(item.getCode(), rentalDays,
				discountPercent, checkoutDate, holidays);
		assertEquals(expectedRentalAgreement, actualRentalAgreement);
	}

	@Test(expected = InvalidRentalDayException.class)
	public void shouldThrowInvalidRentalDayException() throws CheckoutException {
		String code = "JAKR";
		String checkoutDate = "07/02/20";
		Integer rentalDays = 4;
		Integer discountPercent = 50;
		printTest(code,checkoutDate,rentalDays,discountPercent);
		checkout.createRentalAgreement("JAKR", 0, 10, "09/03/15", holidays);
	}

	@Test(expected = InvalidInventoryItemException.class)
	public void shouldThrowInvalidToolException() throws CheckoutException {
		String code = "TEST";
		String checkoutDate = "09/03/15";
		Integer rentalDays = 1;
		Integer discountPercent = 10;
		printTest(code,checkoutDate,rentalDays,discountPercent);

		storeInventory.addInventoryItem(code, new InventoryItem(code, null, "test"));
		checkout.createRentalAgreement(code, rentalDays, discountPercent, checkoutDate, holidays);
	}

	@Test(expected = ItemDoesNotExistException.class)
	public void shouldThrowToolDoesNotExistException() throws CheckoutException {
		String code = "TEST";
		String checkoutDate = "09/03/15";
		Integer rentalDays = 1;
		Integer discountPercent = 10;
		printTest(code,checkoutDate,rentalDays,discountPercent);

		checkout.createRentalAgreement(code, rentalDays, discountPercent, checkoutDate, holidays);
	}

	@Test(expected = InvalidCheckoutDateException.class)
	public void shouldThrowRentalInventoryExceptionForBadDate() throws CheckoutException {
		String code = "JAKR";
		String checkoutDate = "09/03/155";
		Integer rentalDays = 1;
		Integer discountPercent = 10;
		printTest(code,checkoutDate,rentalDays,discountPercent);
		checkout.createRentalAgreement(code, rentalDays, discountPercent, checkoutDate, holidays);
	}

}
