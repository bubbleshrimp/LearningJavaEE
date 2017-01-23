package com.lee.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lee.entity.User;
import com.lee.utils.DBCPUtil;

public class TestDBCP {
	//@Test
	public static void main(String[] args) throws Exception{
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		DBCPUtil.init();
		
		try {
			conn = DBCPUtil.getConnection();
			
			ps = conn.prepareStatement("select * from users");
			
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
		} finally{
			DBCPUtil.closeAll(conn, ps, rs);
		}
	}
}
