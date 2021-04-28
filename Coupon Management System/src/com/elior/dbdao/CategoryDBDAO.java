package com.elior.dbdao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.elior.dao.CategoryDAO;
import com.elior.db.DBUtils;

public class CategoryDBDAO implements CategoryDAO {
	private static final String QUERY_INSERT = "INSERT INTO CATEGORIES (name) VALUES (?)";
	private static final String QUERY_GET_ALL = "SELECT * FROM CATEGORIES";

	@Override
	public void addCategory(String category) throws SQLException, InterruptedException {
		Map<Integer, Object> map = new HashMap<>();
		map.put(1, category);
		DBUtils.runQueryWithResult(QUERY_INSERT, map);
	}

	@Override
	public List<String> getAllCategories() throws SQLException, InterruptedException {
		Map<Integer, Object> map = new HashMap<>();
		List<String> results = new ArrayList<>();
		ResultSet resultSet = DBUtils.runQueryWithResult(QUERY_GET_ALL, map);
		while (resultSet.next()) {
			String category = resultSet.getString(2);
			results.add(category);
		}
		return results;
	}
}
