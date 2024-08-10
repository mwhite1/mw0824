package com.rental.app;

/**
 * Interface for generating RentalAgreement objects
 * @author Malcolm White
 *
 */
public interface RentalAgreementGenerator {
	
	/**
	 * Generates RentalAgreement object
	 * 
	 * @param itemCode unique identifier for item
	 * @param itemType type of item
	 * @param itemBrand brand of item
	 * @param rentalDays number of days item will be rented for
	 * @param checkOutDate date of checkout
	 * @param dueDate date item is due
	 * @param dailyRentalCharge how much it costs to rent item each day
	 * @param chargeDays number of days that will be charged
	 * @param preDiscountCharge total charge without discount applied
	 * @param discountPercent discount percentage
	 * @param discountAmount amount that will be discounted 
	 *        (preDiscountCharge * discountPercent)
	 * @param finalCharge the charge after the discount amount has been subtracted
	 * @return RentalAgreement object
	 */
	public RentalAgreement generateRentalAgreement(String itemCode, String itemType, String itemBrand, Integer rentalDays, String checkOutDate,
			String dueDate, Double dailyRentalCharge, Integer chargeDays, Double preDiscountCharge,
			Integer discountPercent, Double discountAmount, Double finalCharge);
}
