package com.rental.app;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = HolidaySerializer.class)
@JsonDeserialize(using = HolidayDeserializer.class)
public class Holiday extends SpecialDay {
	public Holiday() {
		super();
	}
	public Holiday(Month month, int dayOfMonth, DayOfWeek dayOfWeek, int dayOfWeekOrdinal) {
		super(month,dayOfMonth,dayOfWeek,dayOfWeekOrdinal);
	}
	@Override
	public boolean isDateSpecial(LocalDate date){
		if (month != null) {
			if (date.getMonth() != month)
				return false;
			if (dayOfMonth > 0)
				return date.getDayOfMonth() == dayOfMonth;
			if (dayOfWeek != null)
				return date.getDayOfWeek() == dayOfWeek && date.plusWeeks(-dayOfWeekOrdinal).getMonth() != month;
		}
		return false;
	}
}
