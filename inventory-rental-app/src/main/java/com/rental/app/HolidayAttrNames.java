package com.rental.app;

public enum HolidayAttrNames {
	MONTH("month"),
	DAY_OF_MONTH("dayOfMonth"),
	DAY_OF_WEEK("dayOfWeek"),
	DAY_OF_WEEK_ORDINAL("dayOfWeekOrdinal");

	public final String label;

	private HolidayAttrNames(String label) {
		this.label = label;
	}
}
