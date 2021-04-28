package com.elior.factory;

import java.sql.SQLException;

import com.elior.db.DatebaseManager;

public class InitCustomersVsCoupons {
	public static void initTable() throws SQLException, InterruptedException {
		DatebaseManager.getCouponDAO().addCouponPurchase(1, 2);
		DatebaseManager.getCouponDAO().addCouponPurchase(1, 3);
		DatebaseManager.getCouponDAO().addCouponPurchase(1, 4);
		DatebaseManager.getCouponDAO().addCouponPurchase(1, 10);
		DatebaseManager.getCouponDAO().addCouponPurchase(1, 11);
		DatebaseManager.getCouponDAO().addCouponPurchase(2, 1);
		DatebaseManager.getCouponDAO().addCouponPurchase(2, 9);
		DatebaseManager.getCouponDAO().addCouponPurchase(2, 3);
		DatebaseManager.getCouponDAO().addCouponPurchase(3, 5);
		DatebaseManager.getCouponDAO().addCouponPurchase(4, 1);
		DatebaseManager.getCouponDAO().addCouponPurchase(4, 2);
		DatebaseManager.getCouponDAO().addCouponPurchase(5, 1);
		DatebaseManager.getCouponDAO().addCouponPurchase(5, 9);
	}
}
