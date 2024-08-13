package com.rental.app;

import java.util.Objects;

/**
 * 
 * Creates rental agreement for tools
 * 
 * @author Malcolm White
 *
 */
public class RentalAgreement{
	private static final String border = "-----------------------";

	protected String toolCode;
	protected String toolType;
	protected String toolBrand;
	protected int rentalDays;
	protected String checkOutDate;
	protected String dueDate;
	protected double dailyRentalCharge;
	protected int chargeDays;
	protected double preDiscountCharge;
	protected int discountPercent;
	protected double discountAmount;
	protected double finalCharge;

	/**
	 * 
	 * @param toolCode unique identifier of tool
	 * @param toolType type of tool
	 * @param toolBrand tool brand
	 * @param rentalDays number of days tool will be rented for
	 * @param checkOutDate date tool is checked out
	 * @param dueDate date tool is due by
	 * @param dailyRentalCharge daily charge for tool rental
	 * @param chargeDays number of days that are charged for rental
	 * @param preDiscountCharge charge before the discount is applied
	 * @param discountPercent percent discount provided by clerk
	 * @param discountAmount discount amount provided by clerk
	 * @param finalCharge charge once discount has been applied to preDiscountCharge
	 */
	public RentalAgreement(String toolCode, String toolType, String toolBrand, int rentalDays, String checkOutDate,
			String dueDate, double dailyRentalCharge, int chargeDays, double preDiscountCharge,
			int discountPercent, double discountAmount, double finalCharge) {
		this.toolCode = toolCode;
		this.toolType = toolType;
		this.toolBrand = toolBrand;
		this.rentalDays = rentalDays;
		this.checkOutDate = checkOutDate;
		this.dueDate = dueDate;
		this.dailyRentalCharge = dailyRentalCharge;
		this.chargeDays = chargeDays;
		this.preDiscountCharge = preDiscountCharge;
		this.discountPercent = discountPercent;
		this.discountAmount = discountAmount;
		this.finalCharge = finalCharge;
	}

	/**
	 * 
	 * @return toolCode
	 */
	public String getToolCode() {
		return toolCode;
	}

	/**
	 * 
	 * @param toolCode
	 */
	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}

	/**
	 *
	 * @return toolType
	 */
	public String getToolType() {
		return toolType;
	}

	/**
	 * 
	 * @param toolType
	 */
	public void setToolType(String toolType) {
		this.toolType = toolType;
	}

	/**
	 * 
	 * @return toolBrand
	 */
	public String getToolBrand() {
		return toolBrand;
	}

	/**
	 * 
	 * @param toolBrand
	 */
	public void setToolBrand(String toolBrand) {
		this.toolBrand = toolBrand;
	}

	/**
	 * 
	 * @return rentalDays
	 */
	public int getRentalDays() {
		return rentalDays;
	}

	/**
	 * 
	 * @param rentalDays
	 */
	public void setRentalDays(int rentalDays) {
		this.rentalDays = rentalDays;
	}

	/**
	 * 
	 * @return checkoutDate
	 */
	public String getCheckOutDate() {
		return checkOutDate;
	}

	/**
	 * 
	 * @param checkOutDate
	 */
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	/**
	 * 
	 * @return dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * 
	 * @param dueDate
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * 
	 * @return dailyRentalCharge
	 */
	public double getDailyRentalCharge() {
		return dailyRentalCharge;
	}

	/**
	 * 
	 * @param dailyRentalCharge
	 */
	public void setDailyRentalCharge(double dailyRentalCharge) {
		this.dailyRentalCharge = dailyRentalCharge;
	}

	/**
	 * 
	 * @return chargeDays
	 */
	public int getChargeDays() {
		return chargeDays;
	}

	/**
	 * 
	 * @param chargeDays
	 */
	public void setChargeDays(int chargeDays) {
		this.chargeDays = chargeDays;
	}

	/**
	 * 
	 * @return preDiscountCharge
	 */
	public double getPreDiscountCharge() {
		return preDiscountCharge;
	}

	/**
	 * 
	 * @param preDiscountCharge
	 */
	public void setPreDiscountCharge(double preDiscountCharge) {
		this.preDiscountCharge = preDiscountCharge;
	}

	/**
	 * 
	 * @return discountPercent
	 */
	public int getDiscountPercent() {
		return discountPercent;
	}

	/**
	 * 
	 * @param discountPercent
	 */
	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	/**
	 * 
	 * @return discountAmount
	 */
	public double getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * 
	 * @param discountAmount
	 */
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	/**
	 * 
	 * @return finalCharge
	 */
	public double getFinalCharge() {
		return finalCharge;
	}

	/**
	 * 
	 * @param finalCharge
	 */
	public void setFinalCharge(double finalCharge) {
		this.finalCharge = finalCharge;
	}
	@Override
	public String toString() {
		return "RentalAgreement [toolCode=" + toolCode + ", toolType=" + toolType + ", toolBrand=" + toolBrand
				+ ", rentalDays=" + rentalDays + ", checkOutDate=" + checkOutDate + ", dueDate=" + dueDate
				+ ", dailyRentalCharge=" + dailyRentalCharge + ", chargeDays=" + chargeDays + ", preDiscountCharge="
				+ preDiscountCharge + ", discountPercent=" + discountPercent + ", discountAmount=" + discountAmount
				+ ", finalCharge=" + finalCharge + "]";
	}

	public String printRentalAgreement() {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("%s\n", border));
		builder.append(String.format("Tool code: %s\n",toolCode));
		builder.append(String.format("Tool type: %s\n", toolType));
		builder.append(String.format("Tool brand: %s\n", toolBrand));
		builder.append(String.format("Rental days: %d\n", rentalDays));
		builder.append(String.format("Checkout date: %s\n", checkOutDate));
		builder.append(String.format("Due Date: %s\n", dueDate));
		builder.append(String.format("Daily Rental Charge: $%.2f\n", dailyRentalCharge));
		builder.append(String.format("Charge days: %d\n", chargeDays));
		builder.append(String.format("Pre Discount Charge: $%.2f\n", preDiscountCharge));
		builder.append(String.format("Discount Percent: %d%%\n", discountPercent));
		builder.append(String.format("Discount Amount: $%.2f\n", discountAmount));
		builder.append(String.format("Final Charge: $%.2f\n", finalCharge));
		builder.append(String.format("%s\n", border));
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(chargeDays, checkOutDate, dailyRentalCharge, discountAmount, discountPercent, dueDate,
				finalCharge, preDiscountCharge, rentalDays, toolBrand, toolCode, toolType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RentalAgreement other = (RentalAgreement) obj;
		return Objects.equals(chargeDays, other.chargeDays) && Objects.equals(checkOutDate, other.checkOutDate)
				&& Objects.equals(dailyRentalCharge, other.dailyRentalCharge)
				&& Objects.equals(discountAmount, other.discountAmount)
				&& Objects.equals(discountPercent, other.discountPercent) && Objects.equals(dueDate, other.dueDate)
				&& Objects.equals(finalCharge, other.finalCharge)
				&& Objects.equals(preDiscountCharge, other.preDiscountCharge)
				&& Objects.equals(rentalDays, other.rentalDays) && Objects.equals(toolBrand, other.toolBrand)
				&& Objects.equals(toolCode, other.toolCode) && Objects.equals(toolType, other.toolType);
	}


}
