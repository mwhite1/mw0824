package com.rental.app;

public interface RentalAgreementGenerator {
	public RentalAgreement generateRentalAgreement(String itemCode, String itemType, String itemBrand, Integer rentalDays, String checkOutDate,
			String dueDate, Double dailyRentalCharge, Integer chargeDays, Double preDiscountCharge,
			Integer discountPercent, Double discountAmount, Double finalCharge);
}
