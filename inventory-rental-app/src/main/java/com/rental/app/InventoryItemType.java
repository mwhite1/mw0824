package com.rental.app;

import java.io.Serializable;
import java.util.Objects;

/***
 * 
 * Class that represents a type of inventory item to rent
 * 
 * @author Malcolm White
 *
 */
public class InventoryItemType implements Serializable {
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Type name
	 */
	private String name;

	/**
	 * Daily charge to rent item
	 */
	private double dailyCharge;

	/**
	 * Indicates whether or not charge is applied to weekends
	 */
	private boolean isWeekendCharge;

	/**
	 * Indicates whether or not charge is applied to weekdays
	 */
	private boolean isWeekdayCharge;

	/**
	 * Indicates whether or not charge is applied to holidays
	 */
	private boolean isHolidayCharge;

	/**
	 * Default constructor
	 */
	public InventoryItemType() {

	}

	/**
	 * Constructor that initializes name, dailyCharge, isWeekdayCharge,
	 * isWeekendCharge and isHolidayCharge
	 * 
	 * @param name
	 * @param dailyCharge
	 * @param isWeekdayCharge
	 * @param isWeekendCharge
	 * @param isHolidayCharge
	 */
	public InventoryItemType(String name, double dailyCharge, boolean isWeekdayCharge, boolean isWeekendCharge,
			boolean isHolidayCharge) {
		this.name = name;
		this.dailyCharge = dailyCharge;
		this.isWeekdayCharge = isWeekdayCharge;
		this.isWeekendCharge = isWeekendCharge;
		this.isHolidayCharge = isHolidayCharge;
	}

	/**
	 * 
	 * @return dailyCharge
	 */
	public double getDailyCharge() {
		return dailyCharge;
	}

	/**
	 * 
	 * @param dailyCharge
	 */
	public void setDailyCharge(double dailyCharge) {
		this.dailyCharge = dailyCharge;
	}

	/**
	 * 
	 * @return isWeekendCharge
	 */
	public boolean isWeekendCharge() {
		return isWeekendCharge;
	}

	/**
	 * 
	 * @param isWeekendCharge
	 */
	public void setWeekendCharge(boolean isWeekendCharge) {
		this.isWeekendCharge = isWeekendCharge;
	}

	/**
	 * 
	 * @return isWeekdayCharge
	 */
	public boolean isWeekdayCharge() {
		return isWeekdayCharge;
	}

	/**
	 * 
	 * @param isWeekdayCharge
	 */
	public void setWeekdayCharge(boolean isWeekdayCharge) {
		this.isWeekdayCharge = isWeekdayCharge;
	}

	/**
	 * 
	 * @return isHolidayCharge
	 */
	public boolean isHolidayCharge() {
		return isHolidayCharge;
	}

	/**
	 * 
	 * @param isHolidayCharge
	 */
	public void setHolidayCharge(boolean isHolidayCharge) {
		this.isHolidayCharge = isHolidayCharge;
	}

	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
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
