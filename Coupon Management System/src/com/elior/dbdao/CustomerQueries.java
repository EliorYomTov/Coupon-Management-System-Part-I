package com.elior.dbdao;

public class CustomerQueries {
	static final String QUERY_INSERT = "INSERT INTO CUSTOMERS (first_name, last_name, email, password)\r\n"
			+ "	VALUES(?,?,?,?)";
	static final String QUERY_UPDATE = "UPDATE CUSTOMERS SET first_name=?, last_name=?, email=?, password=? WHERE id = ?";
	static final String QUERY_DELETE = "DELETE FROM CUSTOMERS WHERE id = ?";
	static final String QUERY_GET_ALL = "SELECT * FROM CUSTOMERS";
	static final String QUERY_GET_ROW = "SELECT * FROM CUSTOMERS WHERE id = ?";
	static final String QUERY_CHRCK_CUSTOMER_EMAIL = "SELECT DISTINCT CASE WHEN email = ? \r\n"
			+ "THEN 1 ELSE 0 END as CheckText FROM CUSTOMERS";
	static final String QUERY_CHRCK_CUSTOMER_PURCHASES = "SELECT COUNT (coupon_id) \r\n"
			+ "FROM CUSTOMERS_VS_COUPONS \r\n" + "WHERE customer_id = ? AND coupon_id = ?";
	static final String QUERY_CHECK_COUPON_AMOUNT = "SELECT amount FROM COUPONS WHERE id = ?";

	static final String QUERY_CHECK_EXP_DATE = "SELECT  DISTINCT CASE \r\n"
			+ "WHEN (end_date + 1 < CURRENT_DATE) \r\n" + "THEN 1 ELSE 0 END as CheckText \r\n" + "FROM COUPONS \r\n"
			+ "WHERE id = ? ";

	static final String QUERY_GET_CUSTOMER_ID = "SELECT DISTINCT CASE WHEN email = ? AND password= ? \r\n"
			+ "THEN id ELSE 0 END as CheckText FROM CUSTOMERS";

	static final String QUERY_GET_CUSTOMER_COUPONS = "SELECT * FROM COUPONS WHERE id IN \r\n"
			+ "(SELECT coupon_id FROM CUSTOMERS_VS_COUPONS \r\n" + "WHERE  customer_id = ?)";
}
