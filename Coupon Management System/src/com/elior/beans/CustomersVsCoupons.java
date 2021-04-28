package com.elior.beans;
import com.elior.utils.CommandLineTable.CommandLineTableIntf;

/**
 * 
 * @author Elior
 *	The class was created to pull data from the database and print it
 */

public class CustomersVsCoupons implements CommandLineTableIntf {
	private int customerId;
	private int couponId;

	public CustomersVsCoupons(int customerId, int couponId) {
		this.customerId = customerId;
		this.couponId = couponId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public int getCouponId() {
		return couponId;
	}

	@Override
	public String[] asRow() {
		return new String[] { getCustomerId() + "", getCouponId() + "" };
	}

	@Override
	public String[] getHeder() {
		return new String[] { "Customer Id", "Coupon Id"};
	}
}
