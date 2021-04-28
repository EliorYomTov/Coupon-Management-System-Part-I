package com.elior.dao;
import java.sql.SQLException;
import java.util.List;

import com.elior.beans.Coupon;
import com.elior.beans.Customer;

public interface CustomerDAO {
	int isCustomerExists(String email, String password) throws SQLException, InterruptedException;

	void addCustomer(Customer customer) throws SQLException, InterruptedException;

	void updateCustomer(Customer customer) throws SQLException, InterruptedException;

	void deleteCustomer(int customerId) throws SQLException, InterruptedException;

	List<Customer> getAllCustomers() throws SQLException, InterruptedException;

	Customer getOneCustomer(int customerId) throws SQLException, InterruptedException;

	/*----------------------------------------------------------------------------------------------------*/

	boolean isEmailExists(String email) throws SQLException, InterruptedException;

	int purchaseCouponValid(int customerId, int couponId) throws SQLException, InterruptedException;

	List<Coupon> customerCouponsList(int customerId) throws SQLException, InterruptedException;
}
