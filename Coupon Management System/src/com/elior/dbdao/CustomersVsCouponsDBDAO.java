package com.elior.dbdao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.elior.beans.CustomersVsCoupons;
import com.elior.dao.CustomersVsCouponsDAO;
import com.elior.db.DBUtils;

public class CustomersVsCouponsDBDAO implements CustomersVsCouponsDAO {
	private static final String QUERY_GET_ALL = "SELECT * FROM CUSTOMERS_VS_COUPONS";

	@Override
	public List<CustomersVsCoupons> getAllCustomersVsCouponsInfo() throws SQLException, InterruptedException {
		Map<Integer, Object> map = new HashMap<>();
		List<CustomersVsCoupons> results = new ArrayList<>();
		ResultSet resultSet = DBUtils.runQueryWithResult(QUERY_GET_ALL, map);
		while (resultSet.next()) {
			CustomersVsCoupons customersVsCoupons = new CustomersVsCoupons(resultSet.getInt(1), resultSet.getInt(2));
			results.add(customersVsCoupons);
		}
		return results;
	}
}
