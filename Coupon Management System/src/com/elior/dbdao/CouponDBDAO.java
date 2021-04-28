package com.elior.dbdao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.elior.beans.Category;
import com.elior.beans.Coupon;
import com.elior.dao.CouponDAO;
import com.elior.db.DBUtils;

public class CouponDBDAO implements CouponDAO {
	@Override
	public void addCoupon(Coupon coupon) throws SQLException, InterruptedException {
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, coupon.getCompanyId());
		map.put(2, coupon.getCategoryId());
		map.put(3, coupon.getTitle());
		map.put(4, coupon.getDescription());
		map.put(5, coupon.getStartDate());
		map.put(6, coupon.getEndDate());
		map.put(7, coupon.getAmount());
		map.put(8, coupon.getPrice());
		map.put(9, coupon.getImage());
		DBUtils.runQueryWithResult(CouponsQueries.QUERY_INSERT, map);
	}

	@Override
	public void updateCoupon(Coupon coupon) throws SQLException, InterruptedException {
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, coupon.getTitle());
		map.put(2, coupon.getDescription());
		map.put(3, coupon.getStartDate());
		map.put(4, coupon.getEndDate());
		map.put(5, coupon.getAmount());
		map.put(6, coupon.getPrice());
		map.put(7, coupon.getImage());
		map.put(8, coupon.getId());
		DBUtils.runQueryWithResult(CouponsQueries.QUERY_UPDATE, map);
	}

	@Override
	public void deleteCoupon(int couponId) throws SQLException, InterruptedException {
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, couponId);
		DBUtils.runQueryWithResult(CouponsQueries.QUERY_DELETE_COUPON_PURCHASE, map);
		map = new HashMap<>();
		map.put(1, couponId);
		DBUtils.runQueryWithResult(CouponsQueries.QUERY_DELETE, map);
	}

	@Override
	public List<Coupon> getAllCoupons() throws SQLException, InterruptedException {
		Map<Integer, Object> map = new HashMap<>();
		List<Coupon> results = new ArrayList<>();
		ResultSet resultSet = DBUtils.runQueryWithResult(CouponsQueries.QUERY_GET_ALL, map);
		while (resultSet.next()) {
			Coupon coupon = new Coupon(resultSet.getInt(1), resultSet.getInt(2), Category.valueOf(resultSet.getInt(3)),
					resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getDate(7),
					resultSet.getInt(8), resultSet.getFloat(9), resultSet.getString(10));
			results.add(coupon);
		}
		return results;
	}

	@Override
	public Coupon getOneCoupon(int couponId) throws SQLException, InterruptedException {
		Coupon coupon = null;
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, couponId);
		ResultSet resultSet = DBUtils.runQueryWithResult(CouponsQueries.QUERY_GET_ROW, map);
		boolean rowExists = resultSet.next();
		if (rowExists) {
			coupon = new Coupon(resultSet.getInt(1), resultSet.getInt(2), Category.valueOf(resultSet.getInt(3)),
					resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getDate(7),
					resultSet.getInt(8), resultSet.getFloat(9), resultSet.getString(10));
		}
		return coupon;
	}

	@Override
	public void addCouponPurchase(int customerId, int couponId) throws SQLException, InterruptedException {
		int result = 0;
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, customerId);
		map.put(2, couponId);
		ResultSet resultSet = DBUtils.runQueryWithResult(CouponsQueries.QUERY_VALID_VALUES, map);
		boolean rowExists = resultSet.next();
		if (rowExists) {
			result = resultSet.getInt(1);
		}
		if (result == 1) {
			map = new HashMap<>();
			map.put(1, customerId);
			map.put(2, couponId);
			DBUtils.runQueryWithResult(CouponsQueries.QUERY_ADD_COUPON, map);
		}
	}

	@Override
	public void deleteCouponPurchase(int customerId, int couponId) throws SQLException, InterruptedException {
		Map<Integer, Object> map = new HashMap<>();
		if (customerId != 0) {
			map.put(1, customerId);
			DBUtils.runQueryWithResult(CouponsQueries.QUERY_DELETE_CUSTOMER, map);
		} else if (couponId != 0) {
			map.put(1, couponId);
			DBUtils.runQueryWithResult(CouponsQueries.QUERY_DELETE_COMPANY, map);
		}
	}

	public boolean checkTitleCouponExists(int companyId, String title) throws SQLException, InterruptedException {
		int result = 0;
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, companyId);
		map.put(2, title);
		ResultSet resultSet = DBUtils.runQueryWithResult(CouponsQueries.QUERY_CHRCK_COUPON_WITH_SAME_TITLE, map);
		boolean rowExists = resultSet.next();
		if (rowExists) {
			result = resultSet.getInt(1);
		}
		return result == 1 ? true : false;
	}

	public void deleteCompanyCoupon(int companyId) throws SQLException, InterruptedException {
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, companyId);
		DBUtils.runQueryWithResult(CouponsQueries.QUERY_DELETE_COMPANY_COUPON, map);
	}
	
	public void CouponExpirationDailyJob() throws SQLException, InterruptedException {
		DBUtils.runQueryWithResult(CouponsQueries.QUERY_CUSTOMERS_VS_COUPONS_DAILY_JOB, new HashMap<>());
		DBUtils.runQueryWithResult(CouponsQueries.QUERY_COUPON_DAILY_JOB, new HashMap<>());
	}
	
	public List<Coupon> CouponExpirationDailyJobPrint() throws SQLException, InterruptedException {
		Map<Integer, Object> map = new HashMap<>();
		List<Coupon> results = new ArrayList<>();
		ResultSet resultSet = DBUtils.runQueryWithResult(CouponsQueries.QUERY_GET_EXPIRED_COUPONS, map);
		while (resultSet.next()) {
			Coupon coupon = new Coupon(resultSet.getInt(1), resultSet.getInt(2), Category.valueOf(resultSet.getInt(3)),
					resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6), resultSet.getDate(7),
					resultSet.getInt(8), resultSet.getFloat(9), resultSet.getString(10));
			results.add(coupon);
		}
		return results;
	}
	
}
