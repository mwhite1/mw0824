package com.rental.app;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.apache.commons.math3.util.Precision;

public class Checkout {
	private static final String INVALID_DATE_MESSAGE = "Date must be entered in format %s";
	private static final String DATE_TIME_PATTERN = "MM/dd/yy";
	private static final String DISCOUNT_PERCENT_OUT_OF_RANGE_EXCEPTION_MESSAGE = "Discount percent is out of range.  Please choose value between 0 and 100";
	private static final String INVALID_RENTAL_DAY_EXCEPTION_MESSAGE = "Number of rental days must be greater than or equal to 1";
	private static final String ITEM_DOES_NOT_EXIST_EXCEPTION_MESSAGE = "Item with code %s does not exist";
	private static final String TYPE_IS_NULL_EXCEPTION_MESSAGE = "Item with code %s does not have valid type";
	private static final int MIN_DISCOUNT_PERCENT = 0;
	private static final int MAX_DISCOUNT_PERCENT = 100;
	private static final int MIN_RENTAL_DAY_COUNT = 1;

	protected RentalInventory inventory;
	protected RentalAgreementGenerator rentalAgreementGenerator;

	/**
	 * Constructor
	 * 
	 * @param inventory inventory containing items to checkout
	 * @param rentalAgreementGenerator generates RentalAgreement objects
	 */
	public Checkout(RentalInventory inventory, RentalAgreementGenerator rentalAgreementGenerator) {
		this.inventory = inventory;
		this.rentalAgreementGenerator = rentalAgreementGenerator;
	}

	/**
	 * Returns result of LocalDate.parse and wraps DateTimeParseException
	 * 
	 * @param date
	 * @param datePattern
	 * @return
	 * @throws RentalInventoryException wraps DateTimeParseException
	 */
	protected LocalDate formatDate(String date, DateTimeFormatter datePattern) throws InvalidCheckoutDateException {
		try {
			return LocalDate.parse(date, datePattern);
		}
		catch(DateTimeParseException e) {
			throw new InvalidCheckoutDateException(String.format(INVALID_DATE_MESSAGE, DATE_TIME_PATTERN));
		}
	}

	/**
	 * Checks if date is a holiday by looping through all days and for each invoking the
	 * isDateHoliday function. If for any day, true is returned, this function returns true.
	 * If the loop completes without returning true, false is returned
	 * @param date date in question
	 * @param days list of holidays to check 
	 * @return
	 */
	protected boolean isHoliday(LocalDate date, List<? extends Holiday> days) {
		for(Holiday day : days) {
			if(day.isDateHoliday(date))
				return true;
		}
		return false;
	}

	/**
	 * Checks if date is weekend
	 * @param date date to check
	 * @return true if weekend and false otherwise
	 */
	protected boolean isWeekend(LocalDate date) {
		return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
	}

	/**
	 * Creates a rental agreement for InventoryItem item
	 * 
	 * @param code unique identifier of InventoryItem item
	 * @param numRentalDays number of days item is rented for (Must be more than 0 or
	 *        InvalidRentalDayException is thrown)
	 * @param discountPercent percentage item is discounted (Must be between 0 and 100 or
	 *        DiscountPercentOutOfRangeException is thrown)
	 * @param checkoutDate date item is checked out (Must be format MM/dd/yy or RentalInventoryException
	 *        is thrown)
	 * @param holidays days that will not count towards charge if
	 *        there is no charge for item on holidays
	 * @return rental agreement
	 * @throws RentalInventoryException
	 */
	public RentalAgreement createRentalAgreement(String code, int numRentalDays, int discountPercent,
			String checkoutDate, List<? extends Holiday> holidays) throws CheckoutException {
		// TODO Auto-generated method stub
		if (discountPercent < MIN_DISCOUNT_PERCENT || discountPercent > MAX_DISCOUNT_PERCENT) {
			throw new DiscountPercentOutOfRangeException(DISCOUNT_PERCENT_OUT_OF_RANGE_EXCEPTION_MESSAGE);
		}
		if (numRentalDays < MIN_RENTAL_DAY_COUNT) {
			throw new InvalidRentalDayException(INVALID_RENTAL_DAY_EXCEPTION_MESSAGE);
		}
		InventoryItem itemToRent = inventory.getInventoryItem(code);
		if (itemToRent == null) {
			throw new ItemDoesNotExistException(String.format(ITEM_DOES_NOT_EXIST_EXCEPTION_MESSAGE, code));
		}
		InventoryItemType itemType = itemToRent.getType();
		if (itemType == null) {
			throw new InvalidInventoryItemException(String.format(TYPE_IS_NULL_EXCEPTION_MESSAGE, code));
		}
		boolean isWeekendCharge = itemType.isWeekendCharge();
		boolean isWeekdayCharge = itemType.isWeekdayCharge();
		boolean isHolidayCharge = itemType.isHolidayCharge();
		double dailyCharge = itemType.getDailyCharge();
		int chargeDays = 0;
		DateTimeFormatter datePattern = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
		LocalDate dueLocalDate = formatDate(checkoutDate,datePattern);
		for (int dayCount = MIN_RENTAL_DAY_COUNT; dayCount <= numRentalDays; dayCount++) {
			dueLocalDate = dueLocalDate.plusDays(1);
			if (this.isWeekend(dueLocalDate) && !isWeekendCharge)
				continue;
			if (!this.isWeekend(dueLocalDate) && !isWeekdayCharge)
				continue;
			if (this.isHoliday(dueLocalDate,holidays) && !isHolidayCharge)
				continue;
			chargeDays++;
		}
		String dueDate = dueLocalDate.format(datePattern);
		double preDiscountCharge = Precision.round(dailyCharge * chargeDays, 2);
		double discountAmount = Precision.round(preDiscountCharge * (discountPercent / 100D), 2);
		double finalCharge = preDiscountCharge - discountAmount;
		return rentalAgreementGenerator.generateRentalAgreement(itemToRent.getCode(),itemType.getName(), itemToRent.getBrand(), numRentalDays, checkoutDate, dueDate,
				dailyCharge, chargeDays, preDiscountCharge, discountPercent, discountAmount, finalCharge);
	}

	public RentalInventory getInventory() {
		return inventory;
	}

	public void setInventory(RentalInventory inventory) {
		this.inventory = inventory;
	}

	public RentalAgreementGenerator getRentalAgreementGenerator() {
		return rentalAgreementGenerator;
	}

	public void setRentalAgreementGenerator(RentalAgreementGenerator rentalAgreementGenerator) {
		this.rentalAgreementGenerator = rentalAgreementGenerator;
	}

}
