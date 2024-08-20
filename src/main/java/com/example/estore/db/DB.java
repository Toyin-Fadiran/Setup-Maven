package com.example.estore.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {

	private static final String TAG = "ABC";
	private static DB db = new DB();

	private Connection connection;

	// private PreparedStatement ps;

	private DB() {
		// Private constructor to enforce singleton pattern
	}

	public static DB getInstance() {
		return db;
	}

	private void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String user = "john";
			String password = "P@ssw0rd1";
			String url = "jdbc:mysql://localhost/estore";
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		if (connection == null || isClosed(connection)) {
			// Re-establish the connection if it's null or closed
			connect();
		}
		return connection;
	}

	private boolean isClosed(Connection connection) {
		try {
			return connection == null || connection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}

	public int executeUpdate(PreparedStatement preparedStatement) {
		int result = 0;
		try {
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	// create an executeQuery Method here for our SELECT statements
	// ps --> select * from <table_name>
	public ResultSet executeQuery(PreparedStatement ps) {
		// method body-- the actual reusable oode that
		// gets executed when the method is invoked.
		
		ResultSet resultSet = null;
		try {
			resultSet = ps.executeQuery();

		} catch (Exception e) {
			// TODO: handle
			System.out.println("exception in executeQuery Method!");
			e.printStackTrace();

		}
		return resultSet;

	}

}
