package com.spring.biz.user.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.spring.biz.common.JDBCUtil;
import com.spring.biz.user.UserService;

@Repository("userDAO")
public class UserDAO implements UserService {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private final String USER_GET = "select * from user where e_mail=? and password=?";
	
	
	@Override
	public Map<String, String> geUser_client(String e_mail, String password) {
		Map<String, String> map_user = null;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_GET);
			pstmt.setString(1, e_mail);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				map_user = new HashMap<String, String>();
				map_user.put("e_mail", rs.getString("e_mail"));
				map_user.put("password", rs.getString("password"));
				map_user.put("name", rs.getString("name"));
				map_user.put("address", rs.getString("address"));
				map_user.put("address_detail", rs.getString("address_detail"));
				map_user.put("phone", rs.getString("phone"));
				map_user.put("sex", rs.getString("sex"));
				int age =  rs.getInt("age");
				map_user.put("age", String.valueOf(age));
				Date date = rs.getDate("regdate");
				map_user.put("regdate", String.valueOf(date));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return map_user;
	}
}
