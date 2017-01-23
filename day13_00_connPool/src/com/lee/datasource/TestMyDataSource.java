package com.lee.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

import com.lee.dbutils.DBUtils;
import com.lee.entity.User;

public class TestMyDataSource {
	@Test
	public void test1() throws SQLException{
		Connection myConn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		DataSource	ds = new MyDataSource();
		try {
			myConn = ds.getConnection();
			
			ps = myConn.prepareStatement("select * from users");
			
			rs = ps.executeQuery();
			
			User u = null;
			while(rs.next())
			{
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
		} finally{
			DBUtils.closeAll(myConn, ps, rs);
		}
	}
}
