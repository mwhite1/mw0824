package com.rental.app;

public class RentalAgreementImpl implements RentalAgreement {
	private String toolCode;
	private String toolType;
	private String toolBrand;
	private Integer rentalDays;
	private String checkOutDate;
	private String dueDate;
	private Double dailyRentalCharge;
	private Integer chargeDays;
	private Double preDiscountCharge;
	private Integer discountPercent;
	private Double discountAmount;
	private Double finalCharge;
	
	public RentalAgreementImpl(String toolCode, String toolType, String toolBrand, Integer rentalDays, String checkOutDate,
			String dueDate, Double dailyRentalCharge, Integer chargeDays, Double preDiscountCharge,
			Integer discountPercent, Double discountAmount, Double finalCharge) {
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
	public Integer getRentalDays() {
		return rentalDays;
	}
	public void setRentalDays(Integer rentalDays) {
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
	public Double getDailyRentalCharge() {
		return dailyRentalCharge;
	}
	public void setDailyRentalCharge(Double dailyRentalCharge) {
		this.dailyRentalCharge = dailyRentalCharge;
	}
	public Integer getChargeDays() {
		return chargeDays;
	}
	public void setChargeDays(Integer chargeDays) {
		this.chargeDays = chargeDays;
	}
	public Double getPreDiscountCharge() {
		return preDiscountCharge;
	}
	public void setPreDiscountCharge(Double preDiscountCharge) {
		this.preDiscountCharge = preDiscountCharge;
	}
	public Integer getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(Integer discountPercent) {
		this.discountPercent = discountPercent;
	}
	public Double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Double getFinalCharge() {
		return finalCharge;
	}
	public void setFinalCharge(Double finalCharge) {
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
		builder.append(String.format("Tool code: %s\n",toolCode));
		builder.append(String.format("Tool type: %s\n", toolType));
		builder.append(String.format("Tool brand: %s\n", toolBrand));
		builder.append(String.format("Rental days: %d\n", rentalDays));
		builder.append(String.format("Checkout date: %s\n", checkOutDate));
		builder.append(String.format("Due Date: %s\n", dueDate));
		builder.append(String.format("Daily Rental Charge: $%d\n", dailyRentalCharge));
		builder.append(String.format("Charge days: %d\n", chargeDays));
		builder.append(String.format("Pre Discount Charge: $%d\n", preDiscountCharge));
		builder.append(String.format("Discount Percent: %d%%\n", discountPercent));
		builder.append(String.format("Discount Amount: $%d\n", discountAmount));
		builder.append(String.format("Final Charge: $%d\n", finalCharge));
		return builder.toString();
	}
	
	
}
