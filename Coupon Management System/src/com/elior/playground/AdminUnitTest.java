package com.elior.playground;

import java.sql.SQLException;
import com.elior.art.ArtUtils;
import com.elior.beans.Company;
import com.elior.beans.Customer;
import com.elior.db.DatebaseManager;
import com.elior.exception.illegalOperationException;
import com.elior.facade.AdminFacade;
import com.elior.login.ClientType;
import com.elior.login.LoginManager;
import com.elior.utils.PrintUtils;

public class AdminUnitTest {
	public static void adminUnitTesting() throws SQLException, InterruptedException, illegalOperationException {
		ArtUtils.loginAdmin();
		AdminFacade adminFacade = (AdminFacade) LoginManager.login("admin@admin.com", "admin",
				ClientType.ADMINISTRATOR);
		PrintUtils.printTest("# Admin Login successfully");

		ArtUtils.adminTests();
		System.out.println();
		ArtUtils.addCompany();
		PrintUtils.printTest("# Test unit No 1 - Add 3 Companies");
		adminFacade.addCompany(new Company("ASOS", "asos@asos.com", "ASOS2020"));
		adminFacade.addCompany(new Company("Levis", "levis@Levis.com", "levisX67"));
		adminFacade.addCompany(new Company("Hotels.com", "Hotels@Hotels.com", "Hotels12"));
		PrintUtils.printResultList(adminFacade.getAllCompanies());
		/*---------------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.updateCompany();
		PrintUtils.printTest("# Test unit No 2 - Update Company (5)");
		Company companyToUpdate = adminFacade.getOneCompany(5);
		if (companyToUpdate != null) {
			companyToUpdate.setEmail("JJ@JohnsonAndJohnson.com");
			companyToUpdate.setPassword("JJ#1212");
			adminFacade.updateCompany(companyToUpdate);
		}
		PrintUtils.printResultList(adminFacade.getAllCompanies());
		/*---------------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.deleteCompany();
		PrintUtils.printTest("# Test unit No 3 - Delete company (2)");
		adminFacade.deleteCompany(2);
		PrintUtils.printResultList(adminFacade.getAllCompanies());
		PrintUtils.printResultList(DatebaseManager.getCouponDAO().getAllCoupons());
		PrintUtils.printResultList(DatebaseManager.getCustomersVsCouponsDAO().getAllCustomersVsCouponsInfo());
		/*---------------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.getAllCompany();
		PrintUtils.printTest("# Test unit No 4 - Get All Company");
		PrintUtils.printResultList(adminFacade.getAllCompanies());
		/*---------------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.companyById();
		PrintUtils.printTest("# Test unit No 5 - Get One Company (9)");
		PrintUtils.printResult(adminFacade.getOneCompany(9));
		/*---------------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.addCustomer();
		PrintUtils.printTest("# Test unit No 6 - Add Customer (12)");
		adminFacade.addCustomer(new Customer("Shai", "Rabinovich", "shaiR@gmail.com", "shaiRA94"));
		PrintUtils.printResultList(adminFacade.getAllCustomers());
		/*---------------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.updateCustomer();
		PrintUtils.printTest("# Test unit No 7 - Update customer (10)");
		Customer customerToUpdate = adminFacade.getOneCustomer(10);
		if (customerToUpdate != null) {
			customerToUpdate.setFirstName("Jim");
			customerToUpdate.setLastName("carrey");
			customerToUpdate.setEmail("jimcarrey@pixer.com");
			customerToUpdate.setPassword("carrey542");
			adminFacade.updateCustomer(customerToUpdate);
		}
		PrintUtils.printResultList(adminFacade.getAllCustomers());
		/*---------------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.deleteCustomer();
		PrintUtils.printTest("# Test unit No 8 - Delete Customer (5)");
		adminFacade.deleteCustomer(5);
		PrintUtils.printResultList(adminFacade.getAllCustomers());
		PrintUtils.printResultList(DatebaseManager.getCustomersVsCouponsDAO().getAllCustomersVsCouponsInfo());
		/*---------------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.getAllCustomers();
		PrintUtils.printTest("# Test unit No 9 - Get All Customers");
		PrintUtils.printResultList(adminFacade.getAllCustomers());
		/*---------------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.customerById();
		PrintUtils.printTest("# Test unit No 10 - Get Customer (2)");
		PrintUtils.printResult(adminFacade.getOneCustomer(2));
		/*---------------------------------------------------------------------------------------------------------------------------*/
		ArtUtils.faildTests();
		System.out.println(
				"-----------------------------------------------------------------------------------------" + "\r\n");
		System.out.println("# Test unit No 11 - Add company with the same email");
		System.out.println("\r\n" + "Should show an Error that exists a Company with the same email.." + "\r\n");
		try {
			adminFacade.addCompany(new Company("Fila", "Adidas@Adidas.com", "Fila7979"));
		} catch (illegalOperationException e) {
			System.err.println(e.getMessage());
			Thread.sleep(500);
		}
		System.out.println("\r\n"
				+ "-----------------------------------------------------------------------------------------" + "\r\n");
		System.out.println("# Test unit No 12 - Add company with the same name");
		System.out.println("\r\n" + "Should show an Error that exists a Company with the same name.." + "\r\n");
		try {
			
			adminFacade.addCompany(new Company("Adidas", "Nike@nike.com", "nike7979"));
		} catch (illegalOperationException e) {
			System.err.println(e.getMessage());
			Thread.sleep(500);
		}
		System.out.println("\r\n"
				+ "-----------------------------------------------------------------------------------------" + "\r\n");
		System.out.println("# Test unit No 13 - Add Customer with the same email");
		System.out.println("\r\n" + "Should show an Error that exists a Customer with the same email.." + "\r\n");
		try {
			adminFacade.addCustomer(new Customer("Adam", "Kalifa", "Joni-k@gmail.com", "JoniKalifa22"));
		} catch (illegalOperationException e) {
			System.err.println(e.getMessage());
			Thread.sleep(500);
		}
		System.out.println("\r\n"
				+ "-----------------------------------------------------------------------------------------" + "\r\n");
		System.out.println("# Test unit No 14 - Login with wrong details \r\n");
		adminFacade = (AdminFacade) LoginManager.login("Moshe@admin.com", "admin", ClientType.ADMINISTRATOR);
		Thread.sleep(500);
		System.out.println("\r\n"
				+ "-----------------------------------------------------------------------------------------" + "\r\n");
	}
}
