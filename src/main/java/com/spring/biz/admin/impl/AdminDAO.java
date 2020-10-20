package com.spring.biz.admin.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.spring.biz.admin.AdminUserVO;
import com.spring.biz.admin.AdminVO;
import com.spring.biz.admin.impl.AdminUserDAO.AdminUserRowMapper;

@Repository("adminDAO")
public class AdminDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// SQL 명령어
	private final String ADMIN_LIST = "SELECT * FROM STORE WHERE name =?";
	private final String ADMIN_INFO_UPDATE = "UPDATE STORE SET location = ?, info = ?, tel = ?, delivery_price = ?, least_price = ? WHERE NAME = ?";
	private final String MENU_LIST = "SELECT m.seq, m.store_name, m.food, m.food_price, m.food_img, m.food_opt FROM menu as m JOIN store as s ON m.store_name = s.name";
	private final String INSERT_MENU = "INSERT INTO MENU(store_name, type, menubar, food, food_price,food_opt) VALUES (?,?,?,?,?,?)";
	private final String DELETE_MENU = "DELETE FROM MENU WHERE SEQ = ?";
	
	// 기능 구현
	
	// 가게목록 
	public AdminVO getAdminList(AdminVO vo){
		Object[] args = {vo.getName()};
		System.out.println(vo.toString());
		return jdbcTemplate.queryForObject(ADMIN_LIST, args, new AdminRowMapper());
	}
	
	// 가게정보 수정
	public void updateAdmin(AdminVO vo) {
		System.out.println("Update 기능 실행");
	
		jdbcTemplate.update(ADMIN_INFO_UPDATE, vo.getLocation(), vo.getInfo(), vo.getTel(), vo.getDelivery_price(), vo.getLeast_price(), vo.getName());
	}
	

	
	// 메뉴 목록
	public List<AdminVO> getMenuList(AdminVO vo) {
		System.out.println(vo.toString());
		return jdbcTemplate.query("SELECT * FROM menu WHERE store_name = "+"'"+vo.getName()+"'", new MenuRowMapper());
		
	}
	
	// 메뉴 추가
	public void insertMenu(AdminVO vo) {
		System.out.println("MENU insert 기능수행");
		String opt = '{'+vo.getFood1_opt() +":"+ vo.getFood1_value() + "," + vo.getFood2_opt() + ":" + vo.getFood2_value()+'}';
		System.out.println(opt);
		jdbcTemplate.update(INSERT_MENU, vo.getStore_name(), vo.getType(), vo.getMenubar(), vo.getFood(),vo.getFood_price(),opt);
	}
	
	// 메뉴 삭제
	public void deleteMenu(AdminVO vo) {
		System.out.println("deleteMenu 기능 수행");
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

// 번호 가게이름 음식이름 가격 이미지 옵션1 옵션2
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
