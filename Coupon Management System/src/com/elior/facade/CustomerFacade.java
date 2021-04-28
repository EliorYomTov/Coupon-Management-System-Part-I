package com.elior.facade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.elior.beans.Category;
import com.elior.beans.Coupon;
import com.elior.beans.Customer;
import com.elior.exception.PurchaseError;
import com.elior.exception.illegalOperationException;

public class CustomerFacade extends ClientFacade {
	private int CustomerId;

	public CustomerFacade() {}

	public int getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(int customerId) {
		CustomerId = customerId;
	}

	@Override
	public boolean login(String email, String password) throws SQLException, InterruptedException {
		setCustomerId(customerDAO.isCustomerExists(email, password));
		return CustomerId != 0;
	}

	public void purchaseCoupon(Coupon coupon) throws SQLException, InterruptedException, illegalOperationException {
		int result = customerDAO.purchaseCouponValid(CustomerId, coupon.getId());
		switch (result) {
		case 1:
			throw new illegalOperationException(PurchaseError.purchased_more_than_once.toString());
		case 2:
			throw new illegalOperationException(PurchaseError.amount_of_coupons_is_zero.toString());
		case 3:
			throw new illegalOperationException(PurchaseError.coupon_exp_date_has_arrived.toString());
		}
		couponDAO.addCouponPurchase(CustomerId, coupon.getId());
		coupon.setAmount(coupon.getAmount() - 1);
		couponDAO.updateCoupon(coupon);
	}

	public List<Coupon> getCustomerCoupon() throws SQLException, InterruptedException {
		return customerDAO.customerCouponsList(CustomerId);
	}

	public List<Coupon> getCustomerCoupon(Category category) throws SQLException, InterruptedException {
		List<Coupon> result = new ArrayList<>();
		customerDAO.customerCouponsList(CustomerId).stream().filter(x -> x.getCategory() == category)
				.forEach(x -> result.add(x));
		return result;

	}

	public List<Coupon> getCustomerCoupon(double maxPrice) throws SQLException, InterruptedException {
		List<Coupon> result = new ArrayList<>();
		customerDAO.customerCouponsList(CustomerId).stream().filter(x -> x.getPrice() <= maxPrice)
				.forEach(x -> result.add(x));
		return result;

	}

	public Customer getCustomerDetails() throws SQLException, InterruptedException {
		Customer customer = customerDAO.getOneCustomer(CustomerId);
		customer.setCoupons(getCustomerCoupon());
		return customer;
	}
}
