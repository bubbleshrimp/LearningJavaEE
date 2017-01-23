package com.lee.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.lee.dbutils.DBUtils;
import com.lee.wrapper.MyConnection;

public class MyDataSource implements DataSource {

	private static List<Connection> pool = new ArrayList<Connection>();

	static {
		try {
			for (int i = 0; i < 10; i++) {
				pool.add(DBUtils.getConnection());
			}
		} catch (Exception e) {
			throw new ExceptionInInitializerError("连接池初始化错误。");
		}
	}

	@Override
	public Connection getConnection() throws SQLException {

		Connection conn = null;
		if (pool.size() > 0) {
			conn = pool.remove(0);
			return new MyConnection(conn, pool);
		} else
			throw new RuntimeException("服务器忙");
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
