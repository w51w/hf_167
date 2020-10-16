package com.spring.biz.admin.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.biz.admin.AdminUserService;
import com.spring.biz.admin.AdminUserVO;

@Repository
public class AdminUserDAO  {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	// CRUD
	private final String ADMINUSER_GET = "select * from admin where id=? and pw=?";
	

	public AdminUserVO getAdminUser(AdminUserVO vo) {
		Object[] args = {vo.getId(), vo.getPw()};
		System.out.println(vo.toString());
		return jdbcTemplate.queryForObject(ADMINUSER_GET, args,  new AdminUserRowMapper());
	}
	
	public int getAdminUserCount(AdminUserVO vo) {
		int count = jdbcTemplate.queryForObject(ADMINUSER_GET, Integer.class);
		return count;
	}
	
	
	class AdminUserRowMapper implements RowMapper<AdminUserVO> {
		public AdminUserVO mapRow(ResultSet rs, int rowNim) throws SQLException {
			AdminUserVO adminUser = new AdminUserVO();
			adminUser.setId(rs.getString("ID"));
			adminUser.setPw(rs.getString("PW"));
			adminUser.setStore_name(rs.getString("STORE_NAME"));
			return adminUser;
		}
	}

}
