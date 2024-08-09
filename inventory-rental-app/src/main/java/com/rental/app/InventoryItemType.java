package com.rental.app;

import java.util.Objects;

public class InventoryItemType {
	private String name;
	private double dailyCharge;
	private boolean isWeekendCharge;
	private boolean isWeekdayCharge;
	private boolean isHolidayCharge;
	
	public InventoryItemType() {
		
	}
	
	public InventoryItemType(String name, double dailyCharge, boolean isWeekdayCharge, boolean isWeekendCharge, boolean isHolidayCharge) {
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

	@Override
	public int hashCode() {
		return Objects.hash(dailyCharge, isHolidayCharge, isWeekdayCharge, isWeekendCharge, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryItemType other = (InventoryItemType) obj;
		return Double.doubleToLongBits(dailyCharge) == Double.doubleToLongBits(other.dailyCharge)
				&& isHolidayCharge == other.isHolidayCharge && isWeekdayCharge == other.isWeekdayCharge
				&& isWeekendCharge == other.isWeekendCharge && Objects.equals(name, other.name);
	}

	
	
	
}
