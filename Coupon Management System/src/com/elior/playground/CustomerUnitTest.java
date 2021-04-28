package com.elior.playground;

import java.sql.SQLException;
import com.elior.art.ArtUtils;
import com.elior.beans.Category;
import com.elior.db.DatebaseManager;
import com.elior.exception.illegalOperationException;
import com.elior.facade.CustomerFacade;
import com.elior.login.ClientType;
import com.elior.login.LoginManager;
import com.elior.utils.PrintUtils;

public class CustomerUnitTest {
	public static void customerCompanyUnitTesting() throws SQLException, InterruptedException, illegalOperationException {
		CustomerFacade customerFacade = (CustomerFacade) LoginManager.login("kobi@gmail.com", "kobi1234",
				ClientType.CUSTOMER);
		ArtUtils.customerUnitTest();
		ArtUtils.purchaseCoupon();
		PrintUtils.printTest("# Test unit No 25 - Purchase Coupon (8)");
		customerFacade.purchaseCoupon(DatebaseManager.getCouponDAO().getOneCoupon(8));
		PrintUtils.printResultList(customerFacade.getCustomerCoupon());
		PrintUtils.printResultList(DatebaseManager.getCustomersVsCouponsDAO().getAllCustomersVsCouponsInfo());
		/*----------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.customerCoupons();
		PrintUtils.printTest("# Test unit No 26 - Customer Coupons");
		PrintUtils.printResultList(customerFacade.getCustomerCoupon());
		/*----------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.customerCouponsByCategort();
		PrintUtils.printTest("# Test unit No 27 - Customer Coupons by Category");
		PrintUtils.printResultList(customerFacade.getCustomerCoupon(Category.FASHION));
		/*----------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.customerCouponsByCategort();
		PrintUtils.printTest("# Test unit No 28 - Customer Coupons by Category");
		PrintUtils.printResultList(customerFacade.getCustomerCoupon(Category.FASHION));
		/*----------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.customerCouponsByPrice();
		PrintUtils.printTest("# Test unit No 29 - Coupons up to max price");
		PrintUtils.printResultList(customerFacade.getCustomerCoupon(500.5));
		/*----------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.customerDetails();
		PrintUtils.printTest("# Test unit No 30 - customer details");
		PrintUtils.printResult(customerFacade.getCustomerDetails());
		/*----------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.faildTests();
		System.out.println(
				"-----------------------------------------------------------------------------------------" + "\r\n");
		Thread.sleep(500);
		System.out.println("# Test unit No 31 - Purchase Coupon more then once");
		System.out.println("\r\n" + "Should show an Error that cannot purchase the same coupon more than once..." + "\r\n");
		try {
			customerFacade.purchaseCoupon(DatebaseManager.getCouponDAO().getOneCoupon(8));
		} catch (illegalOperationException e) {
			System.err.println(e.getMessage());
			Thread.sleep(500);
		}
		System.out.println("-----------------------------------------------------------------------------------------" + "\r\n");
		System.out.println("# Test unit No 32 - Purchase a coupon that is not in stock");
		System.out.println("\r\n" + "Should show an Error that cannot be purchased if its quantity is 0.." + "\r\n");
		try {
			
			customerFacade.purchaseCoupon(DatebaseManager.getCouponDAO().getOneCoupon(6));
		} catch (illegalOperationException e) {
			System.err.println(e.getMessage());
			Thread.sleep(500);
		}
		System.out.println("-----------------------------------------------------------------------------------------" + "\r\n");
		System.out.println("# Test unit No 33 - Purchase a coupon Expired");
		System.out.println("\r\n" + "Should show an Error that cannot be purchased if its expired.." + "\r\n");
		try {
			
			customerFacade.purchaseCoupon(DatebaseManager.getCouponDAO().getOneCoupon(5));
		} catch (illegalOperationException e) {
			System.err.println(e.getMessage());
			Thread.sleep(500);
		}
		System.out.println(
				"-----------------------------------------------------------------------------------------" + "\r\n");
		System.out.println("# Test unit No 34 - Login with wrong details \r\n");
		customerFacade =  (CustomerFacade) LoginManager.login("Test@Test.com", "Shira1122", ClientType.CUSTOMER);
		Thread.sleep(500);
		System.out.println("\r\n"
				+ "-----------------------------------------------------------------------------------------" + "\r\n");
	}
}
