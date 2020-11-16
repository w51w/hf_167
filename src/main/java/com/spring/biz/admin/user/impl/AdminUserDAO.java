package com.spring.biz.admin.user.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.biz.admin.user.AdminUserDTO;

@Repository("adminUserDAO")
public class AdminUserDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// CRUD
	private final String ADMIN_USER_GET = "select * from admin where id=? and pw=?";

	public AdminUserDTO getAdminUser(AdminUserDTO vo) {
		Object[] args = { vo.getId(), vo.getPw() };
		return jdbcTemplate.queryForObject(ADMIN_USER_GET, args, new AdminUserRowMapper());
	}

	class AdminUserRowMapper implements RowMapper<AdminUserDTO> {
		public AdminUserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			AdminUserDTO adminUser = new AdminUserDTO();
			adminUser.setId(rs.getString("id"));
			adminUser.setPw(rs.getString("pw"));
			adminUser.setStore_name(rs.getString("store_name"));
			return adminUser;
		}
	}
}
