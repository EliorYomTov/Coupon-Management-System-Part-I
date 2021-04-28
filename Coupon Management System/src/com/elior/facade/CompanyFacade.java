package com.elior.facade;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.elior.beans.Category;
import com.elior.beans.Company;
import com.elior.beans.Coupon;
import com.elior.exception.illegalOperationException;

public class CompanyFacade extends ClientFacade {
	private int companyId;

	public CompanyFacade() {}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	@Override
	public boolean login(String email, String password) throws SQLException, InterruptedException {
		setCompanyId(companyDAO.isCompanyExists(email, password));
		return companyId != 0;
	}

	public void addCoupon(Coupon coupon) throws SQLException, InterruptedException, illegalOperationException {
		if (couponDAO.checkTitleCouponExists(coupon.getCompanyId(), coupon.getTitle())) {
			throw new illegalOperationException(
					"A coupon with the same title cannot be added to an existing coupon of the same company");
		}
		couponDAO.addCoupon(coupon);
	}

	public void updateCoupon(Coupon coupon) throws SQLException, InterruptedException {
		// Update not allowed of "Id" and "CompanyId" - exists constraint of Oracle DB
		couponDAO.updateCoupon(coupon);
	}

	public void deleteCoupon(int couponId) throws SQLException, InterruptedException {
		couponDAO.deleteCoupon(couponId);
	}

	public List<Coupon> getCompanyCoupons() throws SQLException, InterruptedException {
		List<Coupon> result = new ArrayList<>();
		couponDAO.getAllCoupons().stream().filter(x -> x.getCompanyId() == this.companyId).forEach(x -> result.add(x));
		return result;
	}

	public List<Coupon> getCompanyCoupons(Category category) throws SQLException, InterruptedException {
		List<Coupon> result = new ArrayList<>();
		couponDAO.getAllCoupons().stream()
				.filter(x -> x.getCompanyId() == this.companyId && x.getCategory() == category)
				.forEach(x -> result.add(x));
		return result;
	}

	public List<Coupon> getCompanyCoupons(Double maxPrice) throws SQLException, InterruptedException {
		List<Coupon> result = new ArrayList<>();
		couponDAO.getAllCoupons().stream().filter(x -> x.getCompanyId() == this.companyId && x.getPrice() <= maxPrice)
				.forEach(x -> result.add(x));
		return result;
	}

	public Company getCompanyDetails() throws SQLException, InterruptedException {
		Company company = companyDAO.getOneCompany(companyId);
		company.setCoupons(getCompanyCoupons());	
		return company;
	}
}
