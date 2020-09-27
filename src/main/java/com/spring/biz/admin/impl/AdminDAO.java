package com.spring.biz.admin.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.biz.admin.AdminVO;

@Repository("adminDAO")
public class AdminDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// SQL ��ɾ�
	private final String ADMIN_CONTORL = "SELECT * FROM STORE";
	
	// ��� ����
	public List<AdminVO> getAdminList(AdminVO vo){
		System.out.println(vo.toString());
		return jdbcTemplate.query(ADMIN_CONTORL, new AdminRowMapper());
	}
	

}

// Row Mapper
class AdminRowMapper implements RowMapper<AdminVO> {
	public AdminVO mapRow(ResultSet rs, int rowNum) throws SQLException{
		AdminVO admin = new AdminVO();
		admin.setName(rs.getString("NAME"));
		admin.setLocation(rs.getString("LOCATION"));
		admin.setTel(rs.getString("TEL"));
		admin.setDelivery_price(rs.getInt("DELIVERY_PRICE"));
		admin.setLeast_price(rs.getInt("LEAST_PRICE"));
		admin.setInfo(rs.getString("INFO"));
		
		return admin;
	}
}
