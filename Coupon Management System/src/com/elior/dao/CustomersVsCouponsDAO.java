package com.elior.dao;
import java.sql.SQLException;
import java.util.List;

import com.elior.beans.CustomersVsCoupons;

public interface CustomersVsCouponsDAO {

	List<CustomersVsCoupons> getAllCustomersVsCouponsInfo() throws SQLException, InterruptedException;
}
