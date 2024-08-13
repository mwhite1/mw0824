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
	private static final String DATE_TIME_PATTERN = "MM/dd/yy";
	private static final String INVALID_DATE_MESSAGE = String.format("Date must be entered in format %s",DATE_TIME_PATTERN);
	private static final String DISCOUNT_PERCENT_OUT_OF_RANGE_EXCEPTION_MESSAGE = "Discount percent is out of range.  Please choose value between 0 and 100";
	private static final String INVALID_RENTAL_DAY_EXCEPTION_MESSAGE = "Number of rental days must be greater than or equal to 1";
	private static final String ITEM_DOES_NOT_EXIST_EXCEPTION_MESSAGE = "Item with code %s does not exist";
	private static final String TYPE_IS_NULL_EXCEPTION_MESSAGE = "Item with code %s does not have valid type";
	
	private StoreRentalInventory storeInventory;
	private HashMap<String, InventoryItem> storeInventoryMap;
	private List<Holiday> holidays;
	private Checkout checkout;

	private void printTest(String code, String checkoutDate, int rentalDays, int discountPercent) {
		StringBuilder builder = new StringBuilder();
		builder.append("Testing these values:\n");
		builder.append(String.format("Tool code: %s\n", code));
		builder.append(String.format("Checkout Date: %s\n", checkoutDate));
		builder.append(String.format("Rental Days: %s\n", rentalDays));
		builder.append(String.format("Discount: %d%%", discountPercent));
		System.out.println(builder.toString());
	}

	private void runTest(String testName, String code, String checkoutDate, int rentalDays, int discountPercent,
			RentalAgreement expectedRentalAgreement, String expectedException) throws CheckoutException {
		System.out.println(String.format("******Start %s******", testName));
		printTest(code, checkoutDate, rentalDays, discountPercent);
		try {
			if(expectedRentalAgreement != null) {
				System.out.println("EXPECTED RENTAL AGREEMENT");
				System.out.println(expectedRentalAgreement.printRentalAgreement());
			}
			if(expectedException != null) {
				System.out.println(String.format("EXPECTED EXCEPTION: %s", expectedException));
			}
			RentalAgreement actualRentalAgreement = checkout.createRentalAgreement(code, rentalDays, discountPercent,
					checkoutDate, holidays);
			System.out.println("ACTUAL RENTAL AGREEMENT");
			System.out.println(actualRentalAgreement.printRentalAgreement());
			assertEquals(expectedRentalAgreement,actualRentalAgreement);
			
		} catch (CheckoutException e) {
			System.out.println(String.format("ACTUAL EXCEPTION: %s",e.getMessage()));
			throw (e);
		}
		finally {
			System.out.println(String.format("******End %s******",testName));
			System.out.println();
		}
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
		runTest("Test 1", code, checkoutDate, numRentalDays, discountPercent, null, DISCOUNT_PERCENT_OUT_OF_RANGE_EXCEPTION_MESSAGE);
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

		RentalAgreement expectedRentalAgreement = new RentalAgreement(item.getCode(), item.getType().getName(),
				item.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays, preDiscountCharge,
				discountPercent, discountAmount, finalCharge);
		runTest("Test 2", item.getCode(), checkoutDate, rentalDays, discountPercent, expectedRentalAgreement,null);
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
		RentalAgreement expectedRentalAgreement = new RentalAgreement(item.getCode(), item.getType().getName(),
				item.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays, preDiscountCharge,
				discountPercent, discountAmount, finalCharge);
		runTest("Test 3", item.getCode(), checkoutDate, rentalDays, discountPercent, expectedRentalAgreement,null);
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
		RentalAgreement expectedRentalAgreement = new RentalAgreement(item.getCode(), item.getType().getName(),
				item.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays, preDiscountCharge,
				discountPercent, discountAmount, finalCharge);
		runTest("Test 4", item.getCode(), checkoutDate, rentalDays, discountPercent, expectedRentalAgreement, null);
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
		RentalAgreement expectedRentalAgreement = new RentalAgreement(item.getCode(), item.getType().getName(),
				item.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays, preDiscountCharge,
				discountPercent, discountAmount, finalCharge);
		runTest("Test 5", item.getCode(), checkoutDate, rentalDays, discountPercent, expectedRentalAgreement, null);
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
		RentalAgreement expectedRentalAgreement = new RentalAgreement(item.getCode(), item.getType().getName(),
				item.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays, preDiscountCharge,
				discountPercent, discountAmount, finalCharge);
		runTest("Test 6", item.getCode(), checkoutDate, rentalDays, discountPercent, expectedRentalAgreement, null);
	}

	@Test(expected = InvalidRentalDayException.class)
	public void shouldThrowInvalidRentalDayException() throws CheckoutException {
		String code = "JAKR";
		String checkoutDate = "07/02/20";
		Integer rentalDays = 0;
		Integer discountPercent = 50;
		runTest("Test 7", code, checkoutDate, rentalDays, discountPercent, null, INVALID_RENTAL_DAY_EXCEPTION_MESSAGE);
	}

	@Test(expected = InvalidInventoryItemException.class)
	public void shouldThrowInvalidToolException() throws CheckoutException {
		String code = "TEST";
		String checkoutDate = "09/03/15";
		Integer rentalDays = 1;
		Integer discountPercent = 10;

		storeInventory.addInventoryItem(code, new InventoryItem(code, null, "test"));
		String exceptionMessage = String.format(TYPE_IS_NULL_EXCEPTION_MESSAGE, code);
		runTest("Test 8", code, checkoutDate, rentalDays, discountPercent, null, exceptionMessage);
	}

	@Test(expected = ItemDoesNotExistException.class)
	public void shouldThrowToolDoesNotExistException() throws CheckoutException {
		String code = "TEST";
		String checkoutDate = "09/03/15";
		Integer rentalDays = 1;
		Integer discountPercent = 10;
		String exceptionMessage = String.format(ITEM_DOES_NOT_EXIST_EXCEPTION_MESSAGE, code);
		runTest("Test 9", code, checkoutDate, rentalDays, discountPercent, null, exceptionMessage);
	}

	@Test(expected = InvalidCheckoutDateException.class)
	public void shouldThrowRentalInventoryExceptionForBadDate() throws CheckoutException {
		String code = "JAKR";
		String checkoutDate = "09/03/155";
		Integer rentalDays = 1;
		Integer discountPercent = 10;
		runTest("Test 10", code, checkoutDate, rentalDays, discountPercent, null, INVALID_DATE_MESSAGE);
	}

}
