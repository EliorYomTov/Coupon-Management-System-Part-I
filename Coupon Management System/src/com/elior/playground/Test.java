package com.elior.playground;
import java.sql.SQLException;

import com.elior.art.ArtUtils;
import com.elior.dailyJob.CouponExpirationDailyJob;
import com.elior.db.ConnectionPool;
import com.elior.db.DatebaseManager;
import com.elior.exception.illegalOperationException;

public class Test {

	public static void main(String[] args) throws SQLException, InterruptedException, illegalOperationException {
		testAll();
	}

	public static void testAll() throws SQLException, InterruptedException, illegalOperationException {
		ArtUtils.CouponSystem();
		DatebaseManager.dropCreateAndInit();
		FetchTablesTest.fetchTablesTest();
		AdminUnitTest.adminUnitTesting();
		CompanyUnitTest.companyUnitTesting();
		CustomerUnitTest.customerCompanyUnitTesting();
		CouponExpirationDailyJob.getInstance().start();
		Thread.sleep(1000);
		CouponExpirationDailyJob.stop();
		ConnectionPool.getInstance().closeAllConnection();
	}
}
