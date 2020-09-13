package com.spring.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.spring.biz.common.JDBCUtil;

public class UserDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private final String USER_GET = "select * from user where e_mail=? and password=?";
	
	
	public String connectionTest(String e_mail, String password) {
		
	
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_GET);
			pstmt.setString(1, e_mail);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return "로그인 성공";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return "로그인 실패";
	}
}
