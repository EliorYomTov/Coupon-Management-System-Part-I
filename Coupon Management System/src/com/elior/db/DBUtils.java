package com.elior.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import com.elior.utils.DateUtils;

public class DBUtils {

	public static void runQuery(String sql) throws SQLException, InterruptedException {
		Connection connection = ConnectionPool.getInstance().getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.execute();
		ConnectionPool.getInstance().restoreConnection(connection);
	}

	public static boolean checkIfTablExists(String sqlQuery) throws SQLException, InterruptedException {
		boolean result = false;
		Connection connection = ConnectionPool.getInstance().getConnection();
		PreparedStatement statement = connection.prepareStatement(sqlQuery);
		statement.execute();
		ResultSet resultSet = statement.executeQuery();
		resultSet.next();
		if (resultSet.getInt(1) == 1)
			result = true;
		ConnectionPool.getInstance().restoreConnection(connection);
		return result;
	}

	public static ResultSet runQueryWithResult(String sql, Map<Integer, Object> map)
			throws SQLException, InterruptedException {
		Connection connection = ConnectionPool.getInstance().getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = null;
		for (Entry<Integer, Object> entry : map.entrySet()) {
			int key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof Integer)
				statement.setInt(key, (int) value);
			else if (value instanceof Float)
				statement.setFloat(key, (float) value);
			else if (value instanceof String)
				statement.setString(key, String.valueOf(value));
			else if (value instanceof Date)
				statement.setDate(key, DateUtils.convert((Date) value));
		}
		if (sql.contains("SELECT"))
			resultSet = statement.executeQuery();
		else
			statement.execute();
		ConnectionPool.getInstance().restoreConnection(connection);
		return resultSet;
	}
}
