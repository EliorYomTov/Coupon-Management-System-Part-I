package com.elior.dbdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.elior.beans.Company;
import com.elior.dao.CompanyDAO;
import com.elior.db.DBUtils;

public class CompanyDBDAO implements CompanyDAO {

	@Override
	public int isCompanyExists(String email, String password) throws SQLException, InterruptedException {
		int result = 0;
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, email);
		map.put(2, password);
		ResultSet resultSet = DBUtils.runQueryWithResult(CompanyQueries.QUERY_GET_COMPANY_ID, map);
		boolean rowExists = resultSet.next();
		if (rowExists) {
			result = resultSet.getInt(1);
		}
		return result;
	}

	@Override
	public void addCompany(Company company) throws SQLException, InterruptedException {
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, company.getName());
		map.put(2, company.getEmail());
		map.put(3, company.getPassword());
		DBUtils.runQueryWithResult(CompanyQueries.QUERY_INSERT, map);
	}

	@Override
	public void updateCompany(Company company) throws SQLException, InterruptedException {
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, company.getEmail());
		map.put(2, company.getPassword());
		map.put(3, company.getId());
		DBUtils.runQueryWithResult(CompanyQueries.QUERY_UPDATE, map);
	}

	@Override
	public void deleteCompany(int companyId) throws SQLException, InterruptedException {
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, companyId);
		DBUtils.runQueryWithResult(CompanyQueries.QUERY_DELETE, map);
	}

	@Override
	public List<Company> getAllCompanies() throws SQLException, InterruptedException {
		Map<Integer, Object> map = new HashMap<>();
		List<Company> results = new ArrayList<>();
		ResultSet resultSet = DBUtils.runQueryWithResult(CompanyQueries.QUERY_GET_ALL, map);
		while (resultSet.next()) {
			Company company = new Company(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4));
			results.add(company);
		}
		return results;
	}

	@Override
	public Company getOneCompany(int companyId) throws SQLException, InterruptedException {
		Company company = null;
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, companyId);
		ResultSet resultSet = DBUtils.runQueryWithResult(CompanyQueries.QUERY_GET_ROW, map);
		boolean rowExists = resultSet.next();
		if (rowExists) {
			company = new Company(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4));
		}
		return company;
	}
}
