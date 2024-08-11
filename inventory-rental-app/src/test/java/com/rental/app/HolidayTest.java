package com.rental.app;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class HolidayTest {
	private static final String DATE_FORMAT_STRING = "MM/dd/yy";

	@Test
	public void dateShouldBeSpecialWithMonthandDayOfWeekSet() {
		Holiday specialDay = new Holiday(Month.JULY,4,null,0);
		LocalDate date = LocalDate.parse("07/04/22", DateTimeFormatter.ofPattern(DATE_FORMAT_STRING));
		assertTrue(specialDay.isDateHoliday(date));
	}

	@Test
	public void dateShouldBeSpecialWithDayOfMonthSetAndDayOfWeekOrdinalSetToPositive() {
		Holiday specialDay = new Holiday(Month.SEPTEMBER,0,DayOfWeek.MONDAY, 1);
		LocalDate date = LocalDate.parse("09/02/24", DateTimeFormatter.ofPattern(DATE_FORMAT_STRING));
		assertTrue(specialDay.isDateHoliday(date));
	}

	@Test
	public void dateShouldBeSpecialWithDayOfMonthSetAndDayOfWeekOrdinalSetToNegative() {
		Holiday specialDay = new Holiday(Month.MAY,0,DayOfWeek.MONDAY,-1);
		LocalDate date = LocalDate.parse("05/27/24", DateTimeFormatter.ofPattern(DATE_FORMAT_STRING));
		assertTrue(specialDay.isDateHoliday(date));
	}

	@Test
	public void dateShouldNotBeSpecialWithMonthandDayOfWeekSet() {
		Holiday specialDay = new Holiday(Month.JULY,4,null,0);
		LocalDate date = LocalDate.parse("07/03/22", DateTimeFormatter.ofPattern(DATE_FORMAT_STRING));
		assertFalse(specialDay.isDateHoliday(date));
	}

	@Test
	public void dateShouldNotBeSpecialWithDayOfMonthAndDayOfWeekOrdinalSet() {
		Holiday specialDay = new Holiday(Month.MAY,0,DayOfWeek.MONDAY,-1);
		LocalDate date = LocalDate.parse("05/23/24", DateTimeFormatter.ofPattern(DATE_FORMAT_STRING));
		assertFalse(specialDay.isDateHoliday(date));
	}
}
