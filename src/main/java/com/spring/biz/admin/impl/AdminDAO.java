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
	private final String ADMIN_LIST = "SELECT * FROM STORE";
	private final String ADMIN_INFO_UPDATE = "UPDATE STORE SET location = ?, info = ?, tel = ?, delivery_price = ?, least_price = ? WHERE NAME = ?";
	private final String MENU_LIST = "SELECT * FROM MENU";
	private final String INSERT_MENU = "INSERT INTO MENU(store_name, type, menubar, food, food_price) VALUES"
														+ "(store_name = ?, type =?, menubar=?, food=?, food_price=?)";
	private final String DELETE_MENU = "DELETE FROM MENU WHERE SEQ = ?";
	
	// ��� ����
	
	// ���Ը�� 
	public List<AdminVO> getAdminList(AdminVO vo){
		System.out.println(vo.toString());
		return jdbcTemplate.query(ADMIN_LIST, new AdminRowMapper());
	}
	
	// �������� ����
	public void updateAdmin(AdminVO vo) {
		System.out.println("Update ��� ����");
		jdbcTemplate.update(ADMIN_INFO_UPDATE, vo.getLocation(), vo.getInfo(), vo.getTel(), vo.getName());
	}
	

	
	// �޴� ���
	public List<AdminVO> getMenuList(AdminVO vo) {
		return jdbcTemplate.query(MENU_LIST, new MenuRowMapper());
	}
	
	// �޴� �߰�
	public void insertMenu(AdminVO vo) {
		System.out.println("MENU insert ��ɼ���");
		jdbcTemplate.update(INSERT_MENU, vo.getStore_name(), vo.getType(), vo.getMenubar(), vo.getFood(),vo.getFood_price());
	}
	
	// �޴� ����
	public void deleteMenu(AdminVO vo) {
		System.out.println("deleteMenu ��� ����");
		jdbcTemplate.update(DELETE_MENU, vo.getSeq());
	}
	
	//

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

// ��ȣ �����̸� �����̸� ���� �̹��� �ɼ�1 �ɼ�2
class MenuRowMapper implements RowMapper<AdminVO> {
	public AdminVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		AdminVO menu = new AdminVO();
		menu.setSeq(rs.getInt("SEQ"));
		menu.setStore_name(rs.getString("STORE_NAME"));
		menu.setFood(rs.getString("FOOD"));
		menu.setFood_price(rs.getInt("FOOD_PRICE"));
		menu.setFood_img(rs.getString("FOOD_IMG"));
		
		return menu;
	}
}
