package com.elior.dailyJob;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.elior.beans.Coupon;
import com.elior.dao.CouponDAO;
import com.elior.db.DatebaseManager;
import com.elior.utils.PrintUtils;

public class CouponExpirationDailyJob implements Runnable {
	private CouponDAO couponDAO = DatebaseManager.getCouponDAO();
	private volatile static boolean quit;
	private static Thread thread;
	private static CouponExpirationDailyJob instance = null;
	private List<Coupon> coupons = new ArrayList<>();

	private CouponExpirationDailyJob() {
		quit = false;
	}

	public static CouponExpirationDailyJob getInstance() {
		if (instance == null) {
			synchronized (CouponExpirationDailyJob.class) {
				if (instance == null)
					instance = new CouponExpirationDailyJob();
			}
		}
		return instance;
	}

	@Override
	public void run() {
		while (!quit) {
			try {
				PrintUtils.printTest(getCouponExpired());
				couponDAO.CouponExpirationDailyJob();
				
				/* Delete coupns expired every 24 hours */
				Thread.sleep(1000 * 60 * 60 * 24);
				
				/* Delete coupns expired - Test */
				//Thread.sleep(1000 * 30);
				
			} catch (InterruptedException e) {
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		PrintUtils.printTest("Thread " + this.getClass().getSimpleName() + " Shutting down");
	}

	public static void stop() {
		quit = true;
	}

	public void start() {
		PrintUtils.printTest("Starting " + this.getClass().getSimpleName());
		if (thread == null) {
			thread = new Thread(this, this.getClass().getSimpleName());
			thread.start();
		}
	}

	private String getCouponExpired() throws SQLException, InterruptedException {
		coupons = couponDAO.CouponExpirationDailyJobPrint();
		StringBuilder builder = new StringBuilder();
		builder.append("# Delete expired coupons: ");
		for (int i = 0; i < coupons.size(); i++) {
			builder.append(coupons.get(i).getId() + "");
			if (i < coupons.size() - 1)
				builder.append(", ");
		}
		return builder.toString();
	}

}
