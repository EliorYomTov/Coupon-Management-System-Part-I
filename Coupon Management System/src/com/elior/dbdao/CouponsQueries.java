package com.elior.dbdao;

public class CouponsQueries {
	static final String QUERY_INSERT = "INSERT INTO COUPONS\r\n"
			+ "(company_id, category_id, title, description, start_date, end_date, amount, price, image)\r\n"
			+ "VALUES(?,?,?,?,?,?,?,?,?)";
	static final String QUERY_UPDATE = "UPDATE COUPONS SET \r\n" + "title=?, description= ?, start_date= ?,\r\n"
			+ "end_date=? , amount=?, price=?, image=? WHERE id =?";
	static final String QUERY_DELETE = "DELETE FROM COUPONS WHERE id = ?";
	static final String QUERY_GET_ALL = "SELECT * FROM COUPONS";
	static final String QUERY_GET_ROW = "SELECT * FROM COUPONS WHERE id = ?";

	static final String QUERY_VALID_VALUES = "SELECT DISTINCT CASE \r\n" + "WHEN A.id = ? and B.id =? \r\n"
			+ "THEN 1 ELSE 0 END as CheckText \r\n" + "FROM  CUSTOMERS A, COUPONS B";
	static final String QUERY_CHRCK_COUPON_WITH_SAME_TITLE = "SELECT DISTINCT CASE WHEN company_id = ? AND  title= ? \r\n"
			+ "THEN 1 ELSE 0 END as CheckText FROM coupons";
	static final String QUERY_ADD_COUPON = "INSERT INTO  CUSTOMERS_VS_COUPONS (customer_id, coupon_id) VALUES (?,?)";

	static final String QUERY_DELETE_COMPANY = "DELETE CUSTOMERS_VS_COUPONS WHERE coupon_id IN \r\n"
			+ "(SELECT id from coupons where company_id = ?)";
	static final String QUERY_DELETE_CUSTOMER = "DELETE CUSTOMERS_VS_COUPONS WHERE customer_id = ?";
	static final String QUERY_DELETE_COMPANY_COUPON = "DELETE FROM COUPONS WHERE company_id = ?";
	static final String QUERY_DELETE_COUPON_PURCHASE = "DELETE CUSTOMERS_VS_COUPONS WHERE coupon_id = ?";

	static final String QUERY_CUSTOMERS_VS_COUPONS_DAILY_JOB = "DELETE FROM CUSTOMERS_VS_COUPONS WHERE coupon_id IN\r\n"
			+ "(SELECT id FROM COUPONS WHERE end_date + 1 < CURRENT_DATE)";
	static final String QUERY_COUPON_DAILY_JOB = "DELETE FROM COUPONS WHERE end_date + 1 < CURRENT_DATE";
	static final String QUERY_GET_EXPIRED_COUPONS = "SELECT * FROM COUPONS WHERE end_date + 1 < CURRENT_DATE";
}
