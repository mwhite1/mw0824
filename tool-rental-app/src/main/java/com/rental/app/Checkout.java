package com.rental.app;

public interface Checkout {
	public RentalAgreement createRentalAgreement(String toolCode, Integer numRentalDays, Integer discountPercent,
			String checkoutDate) throws CheckoutException;
	public void addTool(String toolCode, Tool tool) throws InvalidToolException;
	public Tool removeTool(String toolCode);
}
