package com.rental.app;

public class ToolType {
	private String name;
	private double dailyCharge;
	private boolean isWeekendCharge;
	private boolean isWeekdayCharge;
	private boolean isHolidayCharge;
	
	public ToolType(String name, double dailyCharge, boolean isWeekendCharge, boolean isWeekdayCharge, boolean isHolidayCharge) {
		this.name = name;
		this.dailyCharge = dailyCharge;
		this.isWeekdayCharge = isWeekdayCharge;
		this.isWeekendCharge = isWeekendCharge;
		this.isHolidayCharge = isHolidayCharge;
	}

	public double getDailyCharge() {
		return dailyCharge;
	}

	public void setDailyCharge(double dailyCharge) {
		this.dailyCharge = dailyCharge;
	}

	public boolean isWeekendCharge() {
		return isWeekendCharge;
	}

	public void setWeekendCharge(boolean isWeekendCharge) {
		this.isWeekendCharge = isWeekendCharge;
	}

	public boolean isWeekdayCharge() {
		return isWeekdayCharge;
	}

	public void setWeekdayCharge(boolean isWeekdayCharge) {
		this.isWeekdayCharge = isWeekdayCharge;
	}

	
	public boolean isHolidayCharge() {
		return isHolidayCharge;
	}

	public void setHolidayCharge(boolean isHolidayCharge) {
		this.isHolidayCharge = isHolidayCharge;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ToolType [name=" + name + ", dailyCharge=" + dailyCharge + ", isWeekendCharge=" + isWeekendCharge
				+ ", isWeekdayCharge=" + isWeekdayCharge + ", isHolidayCharge=" + isHolidayCharge + "]";
	}

	
	
	
}
