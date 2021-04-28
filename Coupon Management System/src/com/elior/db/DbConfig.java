package com.elior.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {
	public static Connection getDataSource() throws SQLException {
		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "elior";
		String password = "1234";
		return DriverManager.getConnection(dbURL, username, password);
	}
}
