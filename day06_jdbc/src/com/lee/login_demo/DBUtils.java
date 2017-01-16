package com.lee.login_demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DBUtils {
	public static Connection getConnection() throws Exception{
		ResourceBundle rb = ResourceBundle.getBundle("dbinfo");
		String driverClass = rb.getString("driverClass");
		String url = rb.getString("url");
		String user = rb.getString("user");
		String password = rb.getString("password");
		
		Class.forName(driverClass);
		return DriverManager.getConnection(url, user, password);
	}
	
	public static void closeAll(Connection conn, Statement stmt, ResultSet rs){
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				rs=null;
			}
		}
		if (stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				stmt=null;
			}
		}
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				conn=null;
			}
		}
	}
}
