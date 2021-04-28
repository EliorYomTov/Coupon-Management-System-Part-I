package com.elior.exception;

public enum PurchaseError {
	purchased_more_than_once("cannot possible purchase the same coupon more than once \r\n"),
	amount_of_coupons_is_zero("The coupon cannot be purchased if is not in stock \r\n"),
	coupon_exp_date_has_arrived("The coupon cannot be purchased if its expired \r\n");

	private final String errorMsg;

	PurchaseError(String text) {
		errorMsg = text;
	}

	@Override
	public String toString() {
		return errorMsg;
	}
}
