package com.lee.login_demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lee.entities.User;

public class DoLogin {

	public boolean findUser(String name, String password) throws Exception {
		Connection conn = DBUtils.getConnection();
		String sql = "select * from users where name=? and password=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, password);
		ResultSet rs = pstmt.executeQuery();
		User u = null;
		while (rs.next()) {
			u = new User();
			u.setId(rs.getInt("id"));
			u.setName(rs.getString("name"));
			u.setPassword(rs.getString("password"));
			u.setGender(rs.getString("gender"));
			u.setEmail(rs.getString("email"));
			u.setBirthday(rs.getDate("birthday"));
			System.out.println(u);
		}
		DBUtils.closeAll(conn, pstmt, rs);
		if (u==null) {
			return false;
		}
		else{
			System.out.println("»¶Ó­Äú,"+u.getName() + ("male".equals(u.getGender())?"ÏÈÉú£¡":"Ð¡½ã£¡"));
			return true;
		}
	}

}
