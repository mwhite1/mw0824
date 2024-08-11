package com.rental.app;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public abstract class SpecialDay {
	protected Month month;
	protected int dayOfMonth;
	protected DayOfWeek dayOfWeek;
	protected int dayOfWeekOrdinal;

	public SpecialDay() {
		month = null;
		dayOfMonth = 0;
		dayOfWeek = null;
		dayOfWeekOrdinal = 0;
	}

	public SpecialDay(Month month, int dayOfMonth, DayOfWeek dayOfWeek, int dayOfWeekOrdinal) {
		this.month = month;
		this.dayOfMonth = dayOfMonth;
		this.dayOfWeek = dayOfWeek;
		this.dayOfWeekOrdinal = dayOfWeekOrdinal;
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

	public abstract boolean isDateSpecial(LocalDate date);
}
