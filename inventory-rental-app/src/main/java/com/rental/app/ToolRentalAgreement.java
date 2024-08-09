package com.rental.app;

import java.util.Objects;

public class ToolRentalAgreement implements RentalAgreement {
	private static final String border = "*************************";
	private String toolCode;
	private String toolType;
	private String toolBrand;
	private int rentalDays;
	private String checkOutDate;
	private String dueDate;
	private double dailyRentalCharge;
	private int chargeDays;
	private double preDiscountCharge;
	private int discountPercent;
	private double discountAmount;
	private double finalCharge;
	
	public ToolRentalAgreement(String toolCode, String toolType, String toolBrand, int rentalDays, String checkOutDate,
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
	
	public String getToolCode() {
		return toolCode;
	}
	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}
	public String getToolType() {
		return toolType;
	}
	public void setToolType(String toolType) {
		this.toolType = toolType;
	}
	public String getToolBrand() {
		return toolBrand;
	}
	public void setToolBrand(String toolBrand) {
		this.toolBrand = toolBrand;
	}
	public int getRentalDays() {
		return rentalDays;
	}
	public void setRentalDays(int rentalDays) {
		this.rentalDays = rentalDays;
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public double getDailyRentalCharge() {
		return dailyRentalCharge;
	}
	public void setDailyRentalCharge(double dailyRentalCharge) {
		this.dailyRentalCharge = dailyRentalCharge;
	}
	public int getChargeDays() {
		return chargeDays;
	}
	public void setChargeDays(int chargeDays) {
		this.chargeDays = chargeDays;
	}
	public double getPreDiscountCharge() {
		return preDiscountCharge;
	}
	public void setPreDiscountCharge(double preDiscountCharge) {
		this.preDiscountCharge = preDiscountCharge;
	}
	public int getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}
	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public double getFinalCharge() {
		return finalCharge;
	}
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

	@Override
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
		ToolRentalAgreement other = (ToolRentalAgreement) obj;
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
