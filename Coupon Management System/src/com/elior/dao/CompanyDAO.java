package com.elior.dao;
import java.sql.SQLException;
import java.util.List;
import com.elior.beans.Company;

public interface CompanyDAO {

	int isCompanyExists(String email, String password) throws SQLException, InterruptedException;

	void addCompany(Company company) throws SQLException, InterruptedException;

	void updateCompany(Company company) throws SQLException, InterruptedException;

	void deleteCompany(int companyId) throws SQLException, InterruptedException;

	List<Company> getAllCompanies() throws SQLException, InterruptedException;

	Company getOneCompany(int companyId) throws SQLException, InterruptedException;
}
