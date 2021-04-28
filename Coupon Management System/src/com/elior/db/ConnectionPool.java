package com.elior.db;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPool {
	private static final int NUM_OF_CONS = 10;
	private static ConnectionPool instance = null;
	private static Stack<Connection> connections = new Stack<>();

	private ConnectionPool() throws SQLException {
		for (int i = 0; i < NUM_OF_CONS; i++) {
			Connection connection = DbConfig.getDataSource();
			connections.add(connection);
		}
	}

	public static ConnectionPool getInstance() throws SQLException {
		if (instance == null) {
			synchronized (ConnectionPool.class) {
				if (instance == null)
					instance = new ConnectionPool();
			}
		}
		return instance;
	}

	public Connection getConnection() throws InterruptedException {
		synchronized (connections) {
			if (connections.isEmpty())
				connections.wait();
		}
		return connections.pop();
	}

	public void restoreConnection(Connection connection) {
		synchronized (connections) {
			connections.push(connection);
			connections.notifyAll();
		}
	}

	public void closeAllConnection() throws SQLException {
		for (Connection connection : connections) {
			connection.close();
		}
		connections.removeAllElements();
	}
}
