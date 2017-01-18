package com.lee.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import org.junit.Test;

import com.lee.entities.User;

public class Demo1 {

	/**
	 * 基本jdbc数据查询
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@Test
	public void test1_SimpleJDBC() throws SQLException, ClassNotFoundException {
		// 1. 注册驱动
		// 方式一：此种注册方式优缺点，会导致注册两次，建议采用第二种
		// DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		// 方式二：反射。优点：类名参数化，可外部获取，增加通用性，便于移植
		Class.forName("com.mysql.jdbc.Driver");

		// 2. 获取连接

		// Connection conn =
		// DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false&user=root&password=lee123qwe");//传值方式同html
		// get方式，？分界，&分隔
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false", "root",
				"lee123qwe");

		// 3. 获取执行sql语句的Statement对象
		Statement stmt = conn.createStatement();

		// 4. 执行sql语句
		String sql;
		sql = "select * from users";
		ResultSet rs = stmt.executeQuery(sql);

		// 5. 打印结果集
		// 获取元数据，包括表结构及标志等信息
		ResultSetMetaData rsmd = rs.getMetaData();
		for (int i = 1, length = rsmd.getColumnCount(); i <= length; i++) {
			System.out.print(rsmd.getColumnName(i) + "\t");
		}
		System.out.println();
		while (rs.next()) {
			System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t"
					+ rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6));
		}

		// 6. 关闭资源
		rs.close();
		stmt.close();
		conn.close();
	}

	/**
	 * 使用Properties文件进行基本jdbc查询
	 * @throws Exception
	 */
	@Test
	public void test2_PropertiesJDBC() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");

		ResourceBundle rb = ResourceBundle.getBundle("dbinfo");
		String user = rb.getString("user");
		String password = rb.getString("password");
		/*
		 * //构建Properties对象并赋值 Properties info = new Properties();
		 * //info.put("user", "root"); //info.put("password", "lee123qwe");
		 * info.setProperty("user", "root"); info.setProperty("password",
		 * "lee123qwe");
		 */

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false", user,
				password);

		// 3. 获取执行sql语句的Statement对象
		Statement stmt = conn.createStatement();

		// 4. 执行sql语句
		String sql;
		sql = "select * from users";
		ResultSet rs = stmt.executeQuery(sql);

		// 5. 打印结果集
		// 获取元数据，包括表结构及标志等信息
		ResultSetMetaData rsmd = rs.getMetaData();
		for (int i = 1, length = rsmd.getColumnCount(); i <= length; i++) {
			System.out.print(rsmd.getColumnName(i) + "\t");
		}
		System.out.println();
		while (rs.next()) {
			System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t"
					+ rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6));
		}

		// 6. 关闭资源
		rs.close();
		stmt.close();
		conn.close();
	}

	/**
	 * 使用try...catch完美关闭jdbc相关对象，同时将ResultSet结果集输出为User对象
	 */
	@Test
	public void test3_TryCatchJDBC(){

		String driverClass, url, user, password, sql;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		ResourceBundle rb = ResourceBundle.getBundle("mysql");
		driverClass = rb.getString("driverClass");
		url = rb.getString("url");
		user = rb.getString("user");
		password = rb.getString("password");
		
		sql = "select * from users where birthday>'1992-05-01'";
		
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			User u =null;
			while(rs.next()){
				u = new User();
				u.setId(rs.getInt("id"));
				u.setName(rs.getString("name"));
				u.setPassword(rs.getString("password"));
				u.setGender(rs.getString("gender"));
				u.setEmail(rs.getString("email"));
				u.setBirthday(rs.getDate("birthday"));
				System.out.println(u);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
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

	
}
