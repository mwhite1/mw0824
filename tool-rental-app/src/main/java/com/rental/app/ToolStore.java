package com.rental.app;

import java.util.HashMap;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import org.apache.commons.math3.util.Precision;

public class ToolStore implements Checkout {
	private static final Integer MIN_DISCOUNT_PERCENT = 0;
	private static final Integer MAX_DISCOUNT_PERCENT = 100;
	private static final Integer MIN_RENTAL_DAY_COUNT = 1;
	private static final String DISCOUNT_PERCENT_OUT_OF_RANGE_EXCEPTION_MESSAGE = "Discount percent is out of range.  Please choose value between 0 and 100";
	private static final String INVALID_RENTAL_DAY_EXCEPTION_MESSAGE = "Number of rental days must be greater than or equal to 1";
	private static final String TOOL_DOES_NOT_EXIST_EXCEPTION_MESSAGE = "Tool with code %s does not exist";
	private static final String TYPE_IS_NULL_EXCEPTION_MESSAGE = "Tool with code %s does not have valid type";
	private static final String DATE_TIME_PATTERN = "MM/dd/yy";
	
	private HashMap<String, Tool> tools;

	public ToolStore() {
		this.tools = new HashMap<String, Tool>();
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
	public RentalAgreement createRentalAgreement(String toolCode, Integer numRentalDays, Integer discountPercent,
			String checkoutDate) throws CheckoutException {
		// TODO Auto-generated method stub
		if (discountPercent < MIN_DISCOUNT_PERCENT || discountPercent > MAX_DISCOUNT_PERCENT) {
			throw new DiscountPercentOutOfRangeException(DISCOUNT_PERCENT_OUT_OF_RANGE_EXCEPTION_MESSAGE);
		}
		if (numRentalDays < MIN_RENTAL_DAY_COUNT) {
			throw new InvalidRentalDayException(INVALID_RENTAL_DAY_EXCEPTION_MESSAGE);
		}
		Tool toolToRent = this.tools.get(toolCode);
		if (toolToRent == null) {
			throw new ToolDoesNotExistException(String.format(TOOL_DOES_NOT_EXIST_EXCEPTION_MESSAGE, toolCode));
		}
		ToolType toolType = toolToRent.getType();
		if (toolType == null) {
			throw new InvalidToolException(String.format(TYPE_IS_NULL_EXCEPTION_MESSAGE, toolCode));
		}
		boolean isWeekendCharge = toolType.isWeekendCharge();
		boolean isWeekdayCharge = toolType.isWeekdayCharge();
		boolean isHolidayCharge = toolType.isHolidayCharge();
		Double dailyCharge = toolType.getDailyCharge();
		Integer chargeDays = 0;
		DateTimeFormatter datePattern = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
		LocalDate dueLocalDate = LocalDate.parse(checkoutDate, datePattern);
		for (Integer dayCount = MIN_RENTAL_DAY_COUNT; dayCount <= numRentalDays; dayCount++) {
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
		Double preDiscountCharge = Precision.round(dailyCharge * chargeDays, 2);
		Double discountAmount = Precision.round(preDiscountCharge * (discountPercent / 100D), 2);
		Double finalCharge = preDiscountCharge - discountAmount;
		return new RentalAgreementImpl(toolCode, toolType.getName(), toolToRent.getBrand(), numRentalDays, checkoutDate, dueDate,
				dailyCharge, chargeDays, preDiscountCharge, discountPercent, discountAmount, finalCharge);
	}

	@Override
	public void addTool(String toolCode, Tool tool) throws InvalidToolException {
		// TODO Auto-generated method stub
		if (tool == null) {
			throw new InvalidToolException("Tool is invalid");
		}
		tools.put(toolCode, tool);
	}

	@Override
	public Tool removeTool(String toolCode) {
		// TODO Auto-generated method stub
		return tools.remove(toolCode);
	}

}
