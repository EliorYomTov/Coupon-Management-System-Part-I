package com.elior.dbdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.elior.beans.Category;
import com.elior.beans.Coupon;
import com.elior.beans.Customer;
import com.elior.dao.CustomerDAO;
import com.elior.db.DBUtils;

public class CustomerDBDAO implements CustomerDAO {

	@Override
	public int isCustomerExists(String email, String password) throws SQLException, InterruptedException {
		int result = 0;
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, email);
		map.put(2, password);
		ResultSet resultSet = DBUtils.runQueryWithResult(CustomerQueries.QUERY_GET_CUSTOMER_ID, map);
		boolean rowExists = resultSet.next();
		if (rowExists) {
			result = resultSet.getInt(1);
		}
		return result;
	}

	@Override
	public void addCustomer(Customer customer) throws SQLException, InterruptedException {
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, customer.getFirstName());
		map.put(2, customer.getLastName());
		map.put(3, customer.getEmail());
		map.put(4, customer.getPassword());
		DBUtils.runQueryWithResult(CustomerQueries.QUERY_INSERT, map);

	}

	@Override
	public void updateCustomer(Customer customer) throws SQLException, InterruptedException {
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, customer.getFirstName());
		map.put(2, customer.getLastName());
		map.put(3, customer.getEmail());
		map.put(4, customer.getPassword());
		map.put(5, customer.getId());
		DBUtils.runQueryWithResult(CustomerQueries.QUERY_UPDATE, map);
	}

	@Override
	public void deleteCustomer(int customerId) throws SQLException, InterruptedException {
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, customerId);
		DBUtils.runQueryWithResult(CustomerQueries.QUERY_DELETE, map);

	}

	@Override
	public List<Customer> getAllCustomers() throws SQLException, InterruptedException {
		Map<Integer, Object> map = new HashMap<>();
		List<Customer> results = new ArrayList<>();
		ResultSet resultSet = DBUtils.runQueryWithResult(CustomerQueries.QUERY_GET_ALL, map);
		while (resultSet.next()) {
			Customer customer = new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5));
			results.add(customer);
		}
		return results;
	}

	@Override
	public Customer getOneCustomer(int customerId) throws SQLException, InterruptedException {
		Customer customer = null;
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, customerId);
		ResultSet resultSet = DBUtils.runQueryWithResult(CustomerQueries.QUERY_GET_ROW, map);
		boolean rowExists = resultSet.next();
		if (rowExists) {
			customer = new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5));
		}
		return customer;
	}

	public boolean isEmailExists(String email) throws SQLException, InterruptedException {
		int result = 0;
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, email);
		ResultSet resultSet = DBUtils.runQueryWithResult(CustomerQueries.QUERY_CHRCK_CUSTOMER_EMAIL, map);
		boolean rowExists = resultSet.next();
		if (rowExists) {
			result = resultSet.getInt(1);
		}
		return result == 1;
	}

	public int purchaseCouponValid(int customerId, int couponId) throws SQLException, InterruptedException {
		int result = 0;
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, customerId);
		map.put(2, couponId);
		ResultSet resultSet = DBUtils.runQueryWithResult(CustomerQueries.QUERY_CHRCK_CUSTOMER_PURCHASES, map);
		boolean rowExists = resultSet.next();
		if (rowExists)
			result = resultSet.getInt(1);
		if (result != 0) // cannot possible purchase the same coupon more than once.
			return 1;
		map = new HashMap<>();
		map.put(1, couponId);
		resultSet = DBUtils.runQueryWithResult(CustomerQueries.QUERY_CHECK_COUPON_AMOUNT, map);
		rowExists = resultSet.next();
		if (rowExists)
			result = resultSet.getInt(1);
		if (result == 0) // The coupon cannot be purchased if its quantity is 0
			return 2;
		result = 0;
		map = new HashMap<>();
		map.put(1, couponId);
		resultSet = DBUtils.runQueryWithResult(CustomerQueries.QUERY_CHECK_EXP_DATE, map);
		rowExists = resultSet.next();
		if (rowExists)
			result = resultSet.getInt(1);
		if (result == 1) // The coupon expiration date has arrived
			return 3;
		return 0;
	}

	public List<Coupon> customerCouponsList(int customerId) throws SQLException, InterruptedException {
		Coupon coupon = null;
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, customerId);
		List<Coupon> results = new ArrayList<>();
		ResultSet resultSet = DBUtils.runQueryWithResult(CustomerQueries.QUERY_GET_CUSTOMER_COUPONS, map);
		while (resultSet.next()) {
			coupon = new Coupon(resultSet.getInt(1), resultSet.getInt(2), Category.valueOf(resultSet.getInt(3)),
					resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getDate(7),
					resultSet.getInt(8), resultSet.getFloat(9), resultSet.getString(10));
			results.add(coupon);
		}
		return results;
	}
}
