package com.elior.dao;
import java.sql.SQLException;
import java.util.List;
import com.elior.beans.Coupon;

public interface CouponDAO {

	void addCoupon(Coupon coupon) throws SQLException, InterruptedException;

	void updateCoupon(Coupon coupon) throws SQLException, InterruptedException;

	void deleteCoupon(int couponId) throws SQLException, InterruptedException;

	List<Coupon> getAllCoupons() throws SQLException, InterruptedException;

	Coupon getOneCoupon(int couponId) throws SQLException, InterruptedException;

	void addCouponPurchase(int customerId, int couponId) throws SQLException, InterruptedException;

	void deleteCouponPurchase(int customerId, int couponId) throws SQLException, InterruptedException;

	/*----------------------------------------------------------------------------------------------------*/

	boolean checkTitleCouponExists(int companyId, String title) throws SQLException, InterruptedException;

	void deleteCompanyCoupon(int companyId) throws SQLException, InterruptedException;

	void CouponExpirationDailyJob() throws SQLException, InterruptedException;

	List<Coupon> CouponExpirationDailyJobPrint() throws SQLException, InterruptedException;
}
