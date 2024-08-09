package com.rental.app;

import java.util.HashMap;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.commons.math3.util.Precision;

public class StoreRentalInventory implements RentalInventory {
	private static final int MIN_DISCOUNT_PERCENT = 0;
	private static final int MAX_DISCOUNT_PERCENT = 100;
	private static final int MIN_RENTAL_DAY_COUNT = 1;
	private static final String DISCOUNT_PERCENT_OUT_OF_RANGE_EXCEPTION_MESSAGE = "Discount percent is out of range.  Please choose value between 0 and 100";
	private static final String INVALID_RENTAL_DAY_EXCEPTION_MESSAGE = "Number of rental days must be greater than or equal to 1";
	private static final String TOOL_DOES_NOT_EXIST_EXCEPTION_MESSAGE = "Tool with code %s does not exist";
	private static final String TYPE_IS_NULL_EXCEPTION_MESSAGE = "Tool with code %s does not have valid type";
	private static final String INVALID_DATE_MESSAGE = "%s is an invalid date";
	private static final String DATE_TIME_PATTERN = "MM/dd/yy";
	
	private HashMap<String, InventoryItem> items;
	private RentalAgreementGenerator rentalAgreementGenerator;
	
	private static String normalizeCode(String code) {
		return code == null ? code : code.toLowerCase();
	}
	
	private static LocalDate formatDate(String date) throws RentalInventoryException {
		DateTimeFormatter datePattern = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
		try {
			return LocalDate.parse(date, datePattern);
		}
		catch(DateTimeParseException e) {
			throw new RentalInventoryException(String.format(INVALID_DATE_MESSAGE, date));
		}
		
	}
	
	public StoreRentalInventory(RentalAgreementGenerator rentalAgreementGenerator) {
		items = new HashMap<String, InventoryItem>();
		this.rentalAgreementGenerator = rentalAgreementGenerator;
	}

	private boolean isHoliday(LocalDate date) {
		Month month = date.getMonth();
		Month prevWeekMonth = date.minusWeeks(1).getMonth();
		if (month == Month.JULY && date.getDayOfMonth() == 4)
			return true;
		return date.getDayOfWeek() == DayOfWeek.MONDAY && month == Month.SEPTEMBER && prevWeekMonth == Month.AUGUST;
	}
	
	private boolean isWeekend(LocalDate date) {
		return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
	}

	@Override
	public RentalAgreement createRentalAgreement(String code, int numRentalDays, int discountPercent,
			String checkoutDate) throws RentalInventoryException {
		// TODO Auto-generated method stub
		if (discountPercent < MIN_DISCOUNT_PERCENT || discountPercent > MAX_DISCOUNT_PERCENT) {
			throw new DiscountPercentOutOfRangeException(DISCOUNT_PERCENT_OUT_OF_RANGE_EXCEPTION_MESSAGE);
		}
		if (numRentalDays < MIN_RENTAL_DAY_COUNT) {
			throw new InvalidRentalDayException(INVALID_RENTAL_DAY_EXCEPTION_MESSAGE);
		}
		InventoryItem itemToRent = getInventoryItem(code);
		if (itemToRent == null) {
			throw new ItemDoesNotExistException(String.format(TOOL_DOES_NOT_EXIST_EXCEPTION_MESSAGE, code));
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
		LocalDate dueLocalDate = formatDate(checkoutDate);
		for (int dayCount = MIN_RENTAL_DAY_COUNT; dayCount <= numRentalDays; dayCount++) {
			dueLocalDate = dueLocalDate.plusDays(1);
			if (this.isWeekend(dueLocalDate) && !isWeekendCharge)
				continue;
			if (!this.isWeekend(dueLocalDate) && !isWeekdayCharge)
				continue;
			if (this.isHoliday(dueLocalDate) && !isHolidayCharge)
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

	@Override
	public void addInventoryItem(String code, InventoryItem item) throws InvalidInventoryItemException {
		// TODO Auto-generated method stub
		if (item == null) {
			throw new InvalidInventoryItemException("Item is invalid");
		}
		items.put(normalizeCode(code), item);
	}

	@Override
	public InventoryItem removeInventoryItem(String code) {
		// TODO Auto-generated method stub
		return items.remove(normalizeCode(code));
	}

	@Override
	public InventoryItem getInventoryItem(String code) {
		// TODO Auto-generated method stub
		return items.get(normalizeCode(code));
	}

}
