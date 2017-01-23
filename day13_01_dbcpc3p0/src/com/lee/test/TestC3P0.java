package com.lee.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.lee.entity.User;
import com.lee.utils.C3P0Util;

public class TestC3P0 {
	@Test
	public void test1() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = C3P0Util.getConnection();
			ps = conn.prepareStatement("select * from users");
			rs = ps.executeQuery();
			
			User u = null;
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
			
		} catch (SQLException e) {
			throw new RuntimeException("≤È—Ø¥ÌŒÛ...");
		} finally {
			C3P0Util.release(conn, ps, rs);
		}

	}
}
