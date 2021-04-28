package com.elior.facade;
import java.sql.SQLException;

import com.elior.dao.CompanyDAO;
import com.elior.dao.CouponDAO;
import com.elior.dao.CustomerDAO;
import com.elior.db.DatebaseManager;

public abstract class ClientFacade {
	protected CompanyDAO companyDAO = DatebaseManager.getCompanyDAO();
	protected CustomerDAO customerDAO = DatebaseManager.getCustomerDAO();
	protected CouponDAO couponDAO= DatebaseManager.getCouponDAO();
	public abstract boolean login(String email, String password) throws SQLException, InterruptedException;
}
