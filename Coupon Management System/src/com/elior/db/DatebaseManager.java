package com.elior.db;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.elior.beans.Company;
import com.elior.beans.Coupon;
import com.elior.beans.Customer;
import com.elior.dao.CategoryDAO;
import com.elior.dao.CompanyDAO;
import com.elior.dao.CouponDAO;
import com.elior.dao.CustomerDAO;
import com.elior.dao.CustomersVsCouponsDAO;
import com.elior.dbdao.CategoryDBDAO;
import com.elior.dbdao.CompanyDBDAO;
import com.elior.dbdao.CouponDBDAO;
import com.elior.dbdao.CustomerDBDAO;
import com.elior.dbdao.CustomersVsCouponsDBDAO;
import com.elior.factory.FactoryCategory;
import com.elior.factory.FactoryCompany;
import com.elior.factory.FactoryCoupon;
import com.elior.factory.FactoryCustomer;
import com.elior.factory.InitCustomersVsCoupons;

public class DatebaseManager {
	private static CustomerDAO customerDAO = new CustomerDBDAO();
	private static CompanyDAO companyDAO = new CompanyDBDAO();
	private static CouponDAO couponDAO = new CouponDBDAO();
	private static CategoryDAO categoryDAO = new CategoryDBDAO();
	private static CustomersVsCouponsDAO customersVsCouponsDAO = new CustomersVsCouponsDBDAO();

	public static CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	public static CompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public static CouponDAO getCouponDAO() {
		return couponDAO;
	}

	public static CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}
	
	public static CustomersVsCouponsDAO getCustomersVsCouponsDAO() {
		return customersVsCouponsDAO;
	}


	private static final String CREATE_TABLE_COMPANIES = "CREATE TABLE COMPANIES \r\n"
			+ "(Id INT GENERATED ALWAYS AS IDENTITY \r\n" + "(START WITH 1 INCREMENT BY 1) NOT NULL, \r\n"
			+ "name VARCHAR(35) NOT NULL, \r\n" + "email VARCHAR(45) NOT NULL, \r\n"
			+ "password VARCHAR(35) NOT NULL,\r\n" + "PRIMARY KEY (Id))";

	private static final String CREATE_TABLE_CUSTOMERS = "CREATE TABLE CUSTOMERS \r\n"
			+ "(Id INT GENERATED ALWAYS AS IDENTITY \r\n" + "(START WITH 1 INCREMENT BY 1) NOT NULL, \r\n"
			+ "first_name VARCHAR(35) NOT NULL, \r\n" + "last_name VARCHAR(35) NOT NULL, \r\n"
			+ "email VARCHAR(35) NOT NULL, \r\n" + "password VARCHAR(35) NOT NULL,\r\n" + "PRIMARY KEY (Id))";

	private static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE CATEGORIES \r\n"
			+ "(Id INT GENERATED ALWAYS AS IDENTITY \r\n" + "(START WITH 1 INCREMENT BY 1) NOT NULL, \r\n"
			+ "name VARCHAR(35) NOT NULL, \r\n" + "PRIMARY KEY (Id))";

	private static final String CREATE_TABLE_COUPONS = "CREATE TABLE COUPONS \r\n"
			+ "(Id INT GENERATED ALWAYS AS IDENTITY \r\n" + "(START WITH 1 INCREMENT BY 1) NOT NULL, \r\n"
			+ "company_id INT NOT NULL, \r\n" + "category_id INT NOT NULL, \r\n" + "title VARCHAR(35) NOT NULL, \r\n"
			+ "description VARCHAR(120) NOT NULL, \r\n" + "start_date DATE NOT NULL,\r\n"
			+ "end_date DATE NOT NULL, \r\n" + "amount INT NOT NULL,\r\n" + "price float NOT NULL,\r\n"
			+ "image VARCHAR(1000) NOT NULL, \r\n" + "PRIMARY KEY (Id),\r\n" + "CONSTRAINT category_foreign_key\r\n"
			+ "FOREIGN KEY (category_id)\r\n" + "REFERENCES CATEGORIES (Id) ,\r\n"
			+ "CONSTRAINT companies_foreign_key\r\n" + "FOREIGN KEY (company_id)\r\n" + "REFERENCES COMPANIES (Id))";

	private static final String CREATE_TABLE_CUSTOMERS_VS_COUPONS = "CREATE TABLE CUSTOMERS_VS_COUPONS \r\n"
			+ "(CUSTOMER_ID INT NOT NULL,\r\n" + "COUPON_ID INT  NOT NULL,\r\n"
			+ "PRIMARY KEY (CUSTOMER_ID, COUPON_ID),\r\n" + "CONSTRAINT customers_foreign_key\r\n"
			+ "FOREIGN KEY (CUSTOMER_ID)\r\n" + "REFERENCES CUSTOMERS (Id) ,\r\n" + "CONSTRAINT coupon_foreign_key\r\n"
			+ "FOREIGN KEY (COUPON_ID)\r\n" + "REFERENCES COUPONS (Id))";

	private static final String DROP_TABLE_COMPANIES = "DROP TABLE COMPANIES";
	private static final String DROP_TABLE_CUSTOMERS = "DROP TABLE CUSTOMERS";
	private static final String DROP_TABLE_CATEGORIES = "DROP TABLE CATEGORIES";
	private static final String DROP_TABLE_COUPONS = "DROP TABLE COUPONS";
	private static final String DROP_TABLE_CUSTOMERS_VS_COUPONS = "DROP TABLE CUSTOMERS_VS_COUPONS";

	private static final String CHECK_TABLE_COMPANIES_EXISTS = "SELECT DISTINCT CASE WHEN object_name = 'COMPANIES' THEN 1 ELSE 0 END as CheckText FROM all_objects";
	private static final String CHECK_TABLE_CUSTOMERS_EXISTS = "SELECT DISTINCT CASE WHEN object_name = 'CUSTOMERS' THEN 1 ELSE 0 END as CheckText FROM all_objects";
	private static final String CHECK_TABLE_CATEGORIES_EXISTS = "SELECT DISTINCT CASE WHEN object_name = 'CATEGORIES' THEN 1 ELSE 0 END as CheckText FROM all_objects";
	private static final String CHECK_TABLE_COUPONS_EXISTS = "SELECT DISTINCT CASE WHEN object_name = 'COUPONS' THEN 1 ELSE 0 END as CheckText FROM all_objects";
	private static final String CHECK_TABLE_CUSTOMERS_VS_COUPONS_EXISTS = "SELECT DISTINCT CASE WHEN object_name = 'CUSTOMERS_VS_COUPONS' THEN 1 ELSE 0 END as CheckText FROM all_objects";

	private static void createTables() throws SQLException, InterruptedException {
		DBUtils.runQuery(CREATE_TABLE_COMPANIES);
		DBUtils.runQuery(CREATE_TABLE_CUSTOMERS);
		DBUtils.runQuery(CREATE_TABLE_CATEGORIES);
		DBUtils.runQuery(CREATE_TABLE_COUPONS);
		DBUtils.runQuery(CREATE_TABLE_CUSTOMERS_VS_COUPONS);
	}

	private static void dropTables() throws SQLException, InterruptedException {
		if (DBUtils.checkIfTablExists(CHECK_TABLE_CUSTOMERS_VS_COUPONS_EXISTS))
			DBUtils.runQuery(DROP_TABLE_CUSTOMERS_VS_COUPONS);
		if (DBUtils.checkIfTablExists(CHECK_TABLE_COUPONS_EXISTS))
			DBUtils.runQuery(DROP_TABLE_COUPONS);
		if (DBUtils.checkIfTablExists(CHECK_TABLE_CATEGORIES_EXISTS))
			DBUtils.runQuery(DROP_TABLE_CATEGORIES);
		if (DBUtils.checkIfTablExists(CHECK_TABLE_CUSTOMERS_EXISTS))
			DBUtils.runQuery(DROP_TABLE_CUSTOMERS);
		if (DBUtils.checkIfTablExists(CHECK_TABLE_COMPANIES_EXISTS))
			DBUtils.runQuery(DROP_TABLE_COMPANIES);
	}

	private static void init() throws SQLException, InterruptedException {
		List<Customer> customers = FactoryCustomer.initList();
		for (Customer customer : customers) {
			customerDAO.addCustomer(customer);
		}

		List<Company> companies = FactoryCompany.initList();
		for (Company company : companies) {
			companyDAO.addCompany(company);
		}

		List<String> categories = FactoryCategory.initList();
		for (String category : categories) {
			categoryDAO.addCategory(category);
		}

		List<Coupon> coupons = FactoryCoupon.initList();
		for (Coupon coupon : coupons) {
			couponDAO.addCoupon(coupon);
		}

		InitCustomersVsCoupons.initTable();
	}

	public static void dropCreateAndInit() throws SQLException, InterruptedException {
		Connection connection = ConnectionPool.getInstance().getConnection();
		dropTables();
		createTables();
		init();
		ConnectionPool.getInstance().restoreConnection(connection);
	}

}
