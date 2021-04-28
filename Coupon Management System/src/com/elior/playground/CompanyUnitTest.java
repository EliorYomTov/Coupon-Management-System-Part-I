package com.elior.playground;
import java.sql.SQLException;
import java.util.Date;

import com.elior.art.ArtUtils;
import com.elior.beans.Category;
import com.elior.beans.Coupon;
import com.elior.db.DatebaseManager;
import com.elior.exception.illegalOperationException;
import com.elior.facade.CompanyFacade;
import com.elior.login.ClientType;
import com.elior.login.LoginManager;
import com.elior.utils.DateUtils;
import com.elior.utils.PrintUtils;

public class CompanyUnitTest {
	public static void companyUnitTesting() throws SQLException, InterruptedException, illegalOperationException {
		CompanyFacade companyFacade = (CompanyFacade) LoginManager.login("lego@lego.com", "LEGO2000",
				ClientType.COMPANY);

		ArtUtils.loginCompany();
		PrintUtils.printTest("# Display Company details");
		PrintUtils.printResult(companyFacade.getCompanyDetails());
		System.out.println("\r\n");
		PrintUtils.printTest("# Display Company's coupons");
		PrintUtils.printResultList(companyFacade.getCompanyDetails().getCoupons());
		/*----------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.compnyUnitTest();
		ArtUtils.addCoupon();
		PrintUtils.printTest("# Test unit No 15 - Add new Coupon (12)");
		companyFacade.addCoupon(new Coupon(10, Category.ELECTRONICS, "Discount of Robots Lego Sets",
				"Get up to 50% off on select Lego sets and toys! ", DateUtils.addDays(new Date(), -7),
				DateUtils.addDays(new Date(), +7), 25, 99.99f,
				"https://www.lego.com/en-gb/categories/robots-for-kids"));
		PrintUtils.printResultList(DatebaseManager.getCouponDAO().getAllCoupons());
		/*----------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.updateCoupon();
		PrintUtils.printTest("# Test unit No 16 - Update Coupon (11)");
		Coupon couponToUpdate = DatebaseManager.getCouponDAO().getOneCoupon(11);
		if (couponToUpdate != null) {
			couponToUpdate.setTitle("40% discount");
			couponToUpdate.setDescription("Extra 40% off first purchase");
			couponToUpdate.setStartDate(new Date());
			couponToUpdate.setEndDate(DateUtils.addDays(new Date(), +7));
			couponToUpdate.setAmount(50);
			couponToUpdate.setPrice(79.99f);
			couponToUpdate.setImage("https://www.lego.com/en-us/product/the-first-adventure-21169");
			companyFacade.updateCoupon(couponToUpdate);
		}
		PrintUtils.printResultList(DatebaseManager.getCouponDAO().getAllCoupons());
		/*----------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.deleteCoupon();
		PrintUtils.printTest("# Test unit No 17 - Delete Company coupon (7)");
		companyFacade.deleteCoupon(7);
		PrintUtils.printResultList(DatebaseManager.getCouponDAO().getAllCoupons());
		PrintUtils.printResult(companyFacade.getCompanyDetails());
		PrintUtils.printResultList(DatebaseManager.getCustomersVsCouponsDAO().getAllCustomersVsCouponsInfo());
		/*----------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.companyCoupons();
		PrintUtils.printTest("# Test unit No 18 - Company Coupons");
		PrintUtils.printResultList(companyFacade.getCompanyCoupons());
		/*----------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.companyCouponsByCategory();
		PrintUtils.printTest("# Test unit No 19 - Company Coupons by Category");
		PrintUtils.printResultList(companyFacade.getCompanyCoupons(Category.ELECTRONICS));
		/*----------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.companyCouponsByPrice();
		PrintUtils.printTest("# Test unit No 20 - Coupons up to max price");
		PrintUtils.printResultList(companyFacade.getCompanyCoupons(85.5));
		/*----------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.companyDetails();
		PrintUtils.printTest("# Test unit No 21 - Company details");
		PrintUtils.printResult(companyFacade.getCompanyDetails());
		PrintUtils.printResultList(companyFacade.getCompanyDetails().getCoupons());
		/*----------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.faildTests();
		System.out.println(
				"-----------------------------------------------------------------------------------------" + "\r\n");
		System.out.println("# Test unit No 22 - Add a coupon with the same title");
		System.out.println("\r\n" + "Should show an Error that exists a Coupon with the same title.." + "\r\n");
		try {
			companyFacade.addCoupon(new Coupon(10, Category.TOYS, "40% discount", "40% discount", new Date(),
					DateUtils.addDays(new Date(), 7), 230, 1200.0f, "lego.jpeg"));
		} catch (illegalOperationException e) {
			System.err.println(e.getMessage());
			Thread.sleep(500);
		}
		System.out.println(
				"\r\n" + "-----------------------------------------------------------------------------------------"
						+ "\r\n\r\n");
		/*----------------------------------------------------------------------------------------------------------------------*/
		System.out.println("# Test unit No 24 - Login with wrong details \r\n");
		companyFacade =  (CompanyFacade) LoginManager.login("Adidas@Adidas.com", "ksp7546", ClientType.COMPANY);
		Thread.sleep(500);
		System.out.println("\r\n"
				+ "-----------------------------------------------------------------------------------------" + "\r\n");
	}
}
