package com.rental.app;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

/**
 * 
 * Class that contains a holiday.  A holiday can either fall on an exact day of the year
 * or a day of the week.
 * 
 * @author Malcolm White
 *
 */
public class Holiday {
	protected Month month;
	protected int dayOfMonth;
	protected DayOfWeek dayOfWeek;
	protected int dayOfWeekOrdinal;

	public Holiday() {
		month = null;
		dayOfMonth = 0;
		dayOfWeek = null;
		dayOfWeekOrdinal = 0;
	}
	
	/**
	 * 
	 * @param month Month the holiday falls on
	 * @param dayOfMonth day of the month the holiday falls on
	 * @param dayOfWeek day of the week the holiday falls on
	 * @param dayOfWeekOrdinal ordinal representing which dayOfWeek the holiday falls on.
	 *        For instance, if month is JANUARY, dayOfWeek is MONDAY and dayOfWeekOrdinal is 1,
	 *        the holiday is the 1st Monday in January.
	 */
	public Holiday(Month month, int dayOfMonth, DayOfWeek dayOfWeek, int dayOfWeekOrdinal) {
		this.month = month;
		this.dayOfMonth = dayOfMonth;
		this.dayOfWeek = dayOfWeek;
		this.dayOfWeekOrdinal = dayOfWeekOrdinal;
	}

	/**
	 * Check if date is an ordinal holiday
	 * 
	 * @param date date
	 * @return true if date is ordinal holiday and false otherwise
	 */
	protected boolean isOrdinalHoliday(LocalDate date) {
		if (date.getDayOfWeek() != dayOfWeek || dayOfWeekOrdinal == 0)
			return false;
		int endDay = Math.abs(dayOfWeekOrdinal);
		for (int dayIdx = 1; dayIdx < endDay; dayIdx++) {
			LocalDate currDate = dayOfWeekOrdinal < 0 ? date.plusWeeks(dayIdx) : date.minusWeeks(dayIdx);
			if (currDate.getMonth() != month)
				return false;
		}
		return date.plusWeeks(-dayOfWeekOrdinal).getMonth() != month;
	}

	/**
	 * Checks if the date is a holiday.  If this.month != null and this.dayOfMonth > 0,
	 * returns whether or not date month equals this.month and 
	 * date day of the month == this.dayOfMonth.  Otherwise returns result of 
	 * isOrdinalHoliday
	 * 
	 * @param date date
	 * @return true if date is holiday and false otherwise
	 */
	public boolean isDateHoliday(LocalDate date) {
		if (month != null) {
			if (date.getMonth() != month)
				return false;
			if (dayOfMonth > 0)
				return date.getDayOfMonth() == dayOfMonth;
			return isOrdinalHoliday(date);
		}
		return false;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getDayOfWeekOrdinal() {
		return dayOfWeekOrdinal;
	}

	public void setDayOfWeekOrdinal(int dayOfWeekOrdinal) {
		this.dayOfWeekOrdinal = dayOfWeekOrdinal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dayOfMonth;
		result = prime * result + ((dayOfWeek == null) ? 0 : dayOfWeek.hashCode());
		result = prime * result + dayOfWeekOrdinal;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Holiday other = (Holiday) obj;
		if (dayOfMonth != other.dayOfMonth)
			return false;
		if (dayOfWeek != other.dayOfWeek)
			return false;
		if (dayOfWeekOrdinal != other.dayOfWeekOrdinal)
			return false;
		if (month != other.month)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Holiday [month=" + month + ", dayOfMonth=" + dayOfMonth + ", dayOfWeek=" + dayOfWeek
				+ ", dayOfWeekOrdinal=" + dayOfWeekOrdinal + "]";
	}

}
