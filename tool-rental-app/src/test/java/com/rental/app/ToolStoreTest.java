package com.rental.app;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.apache.commons.math3.util.Precision;
import static org.junit.Assert.assertEquals;

public class ToolStoreTest {
	private ToolStore toolStore;
	private HashMap<String, Tool> toolMap;

	@Before
	public void setupBefore() throws InvalidToolException {
		ToolType ladder = new ToolType("Ladder", 1.99, true, true, false);
		ToolType chainsaw = new ToolType("Chainsaw", 1.49, true, false, true);
		ToolType jackhammer = new ToolType("Jackhammer", 2.99, true, false, false);
		toolStore = new ToolStore();

		toolMap = new HashMap<String, Tool>();
		toolMap.put("CHNS", new Tool("CHNS", chainsaw, "Stihl"));
		toolMap.put("LADW", new Tool("LADW", ladder, "Werner"));
		toolMap.put("JAKD", new Tool("JAKD", jackhammer, "DeWalt"));
		toolMap.put("JAKR", new Tool("JAKR", jackhammer, "Ridgid"));

		for (Map.Entry<String, Tool> set : toolMap.entrySet()) {
			toolStore.addTool(set.getKey(), set.getValue());
		}
	}
	
	@Test(expected = DiscountPercentOutOfRangeException.class)
	public void test1() throws CheckoutException {
		toolStore.createRentalAgreement("JAKR", 5, 101, "09/03/15");
	}
	
	@Test
	public void test2() throws CheckoutException {
		Tool tool = toolMap.get("LADW");
		String checkoutDate = "07/02/20";
		String dueDate = "07/05/20";
		Integer rentalDays = 3;
		Integer chargeDays = 2;
		Double dailyCharge = tool.getType().getDailyCharge();
		Double preDiscountCharge = dailyCharge * chargeDays;
		Integer discountPercent = 10;
		Double discountAmount = Precision.round(preDiscountCharge * (discountPercent / 100D), 2);
		Double finalCharge = preDiscountCharge - discountAmount;
		RentalAgreementImpl expectedRentalAgreement = new RentalAgreementImpl(tool.getToolCode(),
				tool.getType().getName(), tool.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays,
				preDiscountCharge, discountPercent, discountAmount, finalCharge);
		RentalAgreementImpl actualRentalAgreement = (RentalAgreementImpl) toolStore
				.createRentalAgreement(tool.getToolCode(), rentalDays, discountPercent, checkoutDate);
		assertEquals(expectedRentalAgreement, actualRentalAgreement);
	}
	
	@Test
	public void test3() throws CheckoutException {
		Tool tool = toolMap.get("CHNS");
		String checkoutDate = "07/02/15";
		String dueDate = "07/07/15";
		Integer rentalDays = 5;
		Integer chargeDays = 3;
		Double dailyCharge = tool.getType().getDailyCharge();
		Double preDiscountCharge = dailyCharge * chargeDays;
		Integer discountPercent = 25;
		Double discountAmount = Precision.round(preDiscountCharge * (discountPercent / 100D), 2);
		Double finalCharge = preDiscountCharge - discountAmount;
		RentalAgreementImpl expectedRentalAgreement = new RentalAgreementImpl(tool.getToolCode(),
				tool.getType().getName(), tool.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays,
				preDiscountCharge, discountPercent, discountAmount, finalCharge);
		RentalAgreementImpl actualRentalAgreement = (RentalAgreementImpl) toolStore
				.createRentalAgreement(tool.getToolCode(), rentalDays, discountPercent, checkoutDate);
		assertEquals(expectedRentalAgreement, actualRentalAgreement);
	}
	
	@Test
	public void test4() throws CheckoutException {
		Tool tool = toolMap.get("JAKD");
		String checkoutDate = "09/03/15";
		String dueDate = "09/09/15";
		Integer rentalDays = 6;
		Integer chargeDays = 3;
		Double dailyCharge = tool.getType().getDailyCharge();
		Double preDiscountCharge = dailyCharge * chargeDays;
		Integer discountPercent = 0;
		Double discountAmount = Precision.round(preDiscountCharge * (discountPercent / 100D), 2);
		Double finalCharge = preDiscountCharge - discountAmount;
		RentalAgreementImpl expectedRentalAgreement = new RentalAgreementImpl(tool.getToolCode(),
				tool.getType().getName(), tool.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays,
				preDiscountCharge, discountPercent, discountAmount, finalCharge);
		RentalAgreementImpl actualRentalAgreement = (RentalAgreementImpl) toolStore
				.createRentalAgreement(tool.getToolCode(), rentalDays, discountPercent, checkoutDate);
		assertEquals(expectedRentalAgreement, actualRentalAgreement);
	}
	
	@Test
	public void test5() throws CheckoutException {
		Tool tool = toolMap.get("JAKR");
		String checkoutDate = "07/02/15";
		String dueDate = "07/11/15";
		Integer rentalDays = 9;
		Integer chargeDays = 6;
		Double dailyCharge = tool.getType().getDailyCharge();
		Double preDiscountCharge = dailyCharge * chargeDays;
		Integer discountPercent = 0;
		Double discountAmount = Precision.round(preDiscountCharge * (discountPercent / 100D), 2);
		Double finalCharge = preDiscountCharge - discountAmount;
		RentalAgreementImpl expectedRentalAgreement = new RentalAgreementImpl(tool.getToolCode(),
				tool.getType().getName(), tool.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays,
				preDiscountCharge, discountPercent, discountAmount, finalCharge);
		RentalAgreementImpl actualRentalAgreement = (RentalAgreementImpl) toolStore
				.createRentalAgreement(tool.getToolCode(), rentalDays, discountPercent, checkoutDate);
		assertEquals(expectedRentalAgreement, actualRentalAgreement);
	}
	
	@Test
	public void test6() throws CheckoutException {
		Tool tool = toolMap.get("JAKR");
		String checkoutDate = "07/02/20";
		String dueDate = "07/06/20";
		Integer rentalDays = 4;
		Integer chargeDays = 2;
		Double dailyCharge = tool.getType().getDailyCharge();
		Double preDiscountCharge = dailyCharge * chargeDays;
		Integer discountPercent = 50;
		Double discountAmount = Precision.round(preDiscountCharge * (discountPercent / 100D), 2);
		Double finalCharge = preDiscountCharge - discountAmount;
		RentalAgreementImpl expectedRentalAgreement = new RentalAgreementImpl(tool.getToolCode(),
				tool.getType().getName(), tool.getBrand(), rentalDays, checkoutDate, dueDate, dailyCharge, chargeDays,
				preDiscountCharge, discountPercent, discountAmount, finalCharge);
		RentalAgreementImpl actualRentalAgreement = (RentalAgreementImpl) toolStore
				.createRentalAgreement(tool.getToolCode(), rentalDays, discountPercent, checkoutDate);
		assertEquals(expectedRentalAgreement, actualRentalAgreement);
	}
	
	@Test(expected = InvalidRentalDayException.class)
	public void shouldThrowInvalidRentalDayException() throws CheckoutException {
		toolStore.createRentalAgreement("JAKR", 0, 10, "09/03/15");
	}

	@Test(expected = InvalidToolException.class)
	public void shouldThrowInvalidToolException() throws CheckoutException {
		toolStore.addTool("TEST", new Tool("TEST", null, "test"));
		toolStore.createRentalAgreement("TEST", 1, 10, "09/03/15");
	}

	@Test(expected = ToolDoesNotExistException.class)
	public void shouldThrowToolDoesNotExistException() throws CheckoutException {
		toolStore.createRentalAgreement("TEST", 1, 10, "09/03/15");
	}

}
