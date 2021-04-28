package com.elior.playground;

import java.sql.SQLException;

import com.elior.art.ArtUtils;
import com.elior.db.DatebaseManager;
import com.elior.utils.PrintUtils;

public class FetchTablesTest {
	public static void fetchTablesTest() throws SQLException, InterruptedException {
		ArtUtils.tablesDisplay();
		PrintUtils.printTest("# Displays all tables in the DB");
		PrintUtils.printCategoriesList();
		PrintUtils.printResultList(DatebaseManager.getCustomerDAO().getAllCustomers());
		PrintUtils.printResultList(DatebaseManager.getCompanyDAO().getAllCompanies());
		PrintUtils.printResultList(DatebaseManager.getCouponDAO().getAllCoupons());
		PrintUtils.printResultList(DatebaseManager.getCustomersVsCouponsDAO().getAllCustomersVsCouponsInfo());
	}
}
