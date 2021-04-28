package com.elior.facade;
import java.sql.SQLException;
import java.util.List;

import com.elior.beans.Company;
import com.elior.beans.Customer;
import com.elior.exception.illegalOperationException;

public class AdminFacade extends ClientFacade {
	private static AdminFacade instance = null;

	private AdminFacade() {}

	public static AdminFacade getInstance() {
		if (instance == null) {
			synchronized (AdminFacade.class) {
				if (instance == null)
					instance = new AdminFacade();
			}
		}
		return instance;
	}

	@Override
	public boolean login(String email, String password) throws SQLException, InterruptedException {
		return email == "admin@admin.com" && password == "admin";
	}

	public void addCompany(Company company) throws SQLException, InterruptedException, illegalOperationException {
		for (Company comp : companyDAO.getAllCompanies()) {
			if (comp.getEmail().equals(company.getEmail()))
				throw new illegalOperationException("It is not possible to add a company with the same email");
			if (comp.getName().equals(company.getName()))
				throw new illegalOperationException("It is not possible to add a company with the same name");
		}
		companyDAO.addCompany(company);
	}

	public void updateCompany(Company company) throws SQLException, InterruptedException {
		// Update not allowed of "Id" and "Name" - exists constraint of Oracle DB
		companyDAO.updateCompany(company);
	}

	public void deleteCompany(int CompanyId) throws SQLException, InterruptedException {
		couponDAO.deleteCouponPurchase(0, CompanyId);
		couponDAO.deleteCompanyCoupon(CompanyId);
		companyDAO.deleteCompany(CompanyId);
	}

	public List<Company> getAllCompanies() throws SQLException, InterruptedException {
		return companyDAO.getAllCompanies();
	}

	public Company getOneCompany(int companyId) throws SQLException, InterruptedException {
		return companyDAO.getOneCompany(companyId);
	}

	public void addCustomer(Customer customer) throws SQLException, InterruptedException, illegalOperationException {
		if (customerDAO.isEmailExists(customer.getEmail())) {
			throw new illegalOperationException("It is not possible to add a customer with the same email");
		}
		customerDAO.addCustomer(customer);
	}

	public void updateCustomer(Customer customer) throws SQLException, InterruptedException {
		// Update not allowed of "Id" - exists constraint of Oracle DB
		customerDAO.updateCustomer(customer);
	}

	public void deleteCustomer(int customerId) throws SQLException, InterruptedException {
		couponDAO.deleteCouponPurchase(customerId, 0);
		customerDAO.deleteCustomer(customerId);
	}

	public List<Customer> getAllCustomers() throws SQLException, InterruptedException {
		return customerDAO.getAllCustomers();
	}

	public Customer getOneCustomer(int customerId) throws SQLException, InterruptedException {
		return customerDAO.getOneCustomer(customerId);
	}
}
