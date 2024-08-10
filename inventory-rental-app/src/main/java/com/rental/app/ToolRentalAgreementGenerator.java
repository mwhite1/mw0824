package com.rental.app;

/**
 * Gnerator class that generates ToolRentalAgreement
 * @author Malcolm White
 *
 */
public class ToolRentalAgreementGenerator implements RentalAgreementGenerator {

	/**
	 * Generates ToolRentalAgreement object
	 */
	@Override
	public RentalAgreement generateRentalAgreement(String itemCode, String itemType, String itemBrand,
			Integer rentalDays, String checkOutDate, String dueDate, Double dailyRentalCharge, Integer chargeDays,
			Double preDiscountCharge, Integer discountPercent, Double discountAmount, Double finalCharge) {
		// TODO Auto-generated method stub
		return new ToolRentalAgreement(itemCode, itemType, itemBrand, rentalDays, checkOutDate, dueDate,
				dailyRentalCharge, chargeDays, preDiscountCharge, discountPercent, discountAmount, finalCharge);
	}

}
