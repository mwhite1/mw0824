package com.rental.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.commons.math3.util.Precision;

/**
 * Class that implements RentalInventory inteface.  InventoryItem objects are stored
 * in internal Map<String, InventoryItem>, items.  See {@link RentalInventory} for 
 * operations
 * 
 * @author Malcolm White
 *
 */
public class StoreRentalInventory implements RentalInventory {
	private static final int MIN_DISCOUNT_PERCENT = 0;
	private static final int MAX_DISCOUNT_PERCENT = 100;
	private static final int MIN_RENTAL_DAY_COUNT = 1;
	private static final Month HOLIDAY_MONTH = Month.JULY;
	private static final String DISCOUNT_PERCENT_OUT_OF_RANGE_EXCEPTION_MESSAGE = "Discount percent is out of range.  Please choose value between 0 and 100";
	private static final String INVALID_RENTAL_DAY_EXCEPTION_MESSAGE = "Number of rental days must be greater than or equal to 1";
	private static final String ITEM_DOES_NOT_EXIST_EXCEPTION_MESSAGE = "Item with code %s does not exist";
	private static final String TYPE_IS_NULL_EXCEPTION_MESSAGE = "Item with code %s does not have valid type";
	private static final String INVALID_DATE_MESSAGE = "Date must be entered in format %s";
	private static final String DATE_TIME_PATTERN = "MM/dd/yy";
	
	/**
	 * Internal Map used to store InventoryItem objects
	 */
	private Map<String, InventoryItem> items;
	
	/**
	 * Used by createRentalAgreement to generate RentalAgreement object
	 */
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
			throw new RentalInventoryException(String.format(INVALID_DATE_MESSAGE, DATE_TIME_PATTERN));
		}
		
	}
	
	/**
	 * 
	 * @param rentalAgreementGenerator used by createRentalAgreement to 
	 *        generate RentalAgreement object
	 */
	public StoreRentalInventory(RentalAgreementGenerator rentalAgreementGenerator) {
		items = new HashMap<String, InventoryItem>();
		this.rentalAgreementGenerator = rentalAgreementGenerator;
	}
	
	/**
	 * Determines if a date falls on a holiday
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	private boolean isHoliday(LocalDate date, List<? extends SpecialDay> days) {
		for(SpecialDay day : days) {
			if(day.isDateSpecial(date))
				return true;
		}
		return false;
	}
	
	/**
	 * Checks if date is weekend
	 * @param date date to check
	 * @return true if weekend and false otherwise
	 */
	private boolean isWeekend(LocalDate date) {
		return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
	}

	/**
	 * Creates rental agreement for InventoryItem identified by code with the
	 * following information:
	 * <pre>
	 * - code: InventoryItem attribute, code.
	 * - numRentalDays: Number of rental days
	 * - checkoutDate: Date of Checkout.  Must be in MM/dd/yy format otherwise
	 *                 RentalInventoryException is thrown
	 * - dueDate: Date item is due
	 * - dailyCharge: Daily cost to rent item
	 * - chargeDays: Number of days to charge for, which is days
	 *               between checkoutDate and dueDate minus days that
	 *               are not charged.  This varies by item type
	 * - preDiscountCharge: chargeDays * dailyCharge
	 * - discountPercent: Percent discount between 0 and 100.  If outside of range
	 *                    then DiscountPercentOutOfRangeException is thrown
	 * - discountAmount: preDiscountCharge * discountPercent
	 * - finalCharge: preDiscountCharge - discountAmount
	 * Throws ItemDoesNotExistException if InventoryItem retrieved by code is null
	 * Throws InvalidInventoryItemException if type object in InventoryItem is null
	 * </pre>
	 */
	@Override
	public RentalAgreement createRentalAgreement(String code, int numRentalDays, int discountPercent,
			String checkoutDate, List<? extends SpecialDay> specialDays) throws RentalInventoryException {
		// TODO Auto-generated method stub
		if (discountPercent < MIN_DISCOUNT_PERCENT || discountPercent > MAX_DISCOUNT_PERCENT) {
			throw new DiscountPercentOutOfRangeException(DISCOUNT_PERCENT_OUT_OF_RANGE_EXCEPTION_MESSAGE);
		}
		if (numRentalDays < MIN_RENTAL_DAY_COUNT) {
			throw new InvalidRentalDayException(INVALID_RENTAL_DAY_EXCEPTION_MESSAGE);
		}
		InventoryItem itemToRent = getInventoryItem(code);
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
		LocalDate dueLocalDate = formatDate(checkoutDate);
		for (int dayCount = MIN_RENTAL_DAY_COUNT; dayCount <= numRentalDays; dayCount++) {
			dueLocalDate = dueLocalDate.plusDays(1);
			if (this.isWeekend(dueLocalDate) && !isWeekendCharge)
				continue;
			if (!this.isWeekend(dueLocalDate) && !isWeekdayCharge)
				continue;
			if (this.isHoliday(dueLocalDate,specialDays) && !isHolidayCharge)
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
