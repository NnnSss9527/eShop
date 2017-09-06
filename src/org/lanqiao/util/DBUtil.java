package org.lanqiao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static Connection conn;
	static final String URL="jdbc:oracle:thin:@localhost:1521:orcl";
	static final String DRIVER="oracle.jdbc.driver.OracleDriver";
	static final String USER="eshop";
	static final String PASSWORD="eshop";
	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
