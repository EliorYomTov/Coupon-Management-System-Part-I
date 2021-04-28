package com.elior.dbdao;

public class CompanyQueries {
	static final String QUERY_INSERT = "INSERT INTO COMPANIES (name, email, password) VALUES (?,?,?)";
	static final String QUERY_UPDATE = "UPDATE COMPANIES SET email= ?, password= ? WHERE id = ?";
	static final String QUERY_DELETE = "DELETE FROM COMPANIES WHERE id = ?";
	static final String QUERY_GET_ALL = "SELECT * FROM COMPANIES";
	static final String QUERY_GET_ROW = "SELECT * FROM COMPANIES WHERE id = ?";
	static final String QUERY_GET_COMPANY_ID = "SELECT DISTINCT CASE WHEN email = ? AND password= ? \r\n"
			+ "THEN id ELSE 0 END as CheckText FROM COMPANIES";
}
