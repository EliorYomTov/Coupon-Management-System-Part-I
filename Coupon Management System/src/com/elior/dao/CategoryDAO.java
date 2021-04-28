package com.elior.dao;
import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO {
	
	void addCategory(String category) throws SQLException, InterruptedException;
	List<String> getAllCategories() throws SQLException, InterruptedException;
}
