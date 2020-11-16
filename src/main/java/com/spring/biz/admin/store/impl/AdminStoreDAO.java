package com.spring.biz.admin.store.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.biz.admin.store.AdminStoreDTO;
import com.spring.biz.admin.store.AdminStoreMenuDTO;

@Repository("adminStoreDAO")
public class AdminStoreDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String STORE_GET = "SELECT * FROM STORE WHERE NAME =?";
	private final String STORE_UPDATE = "UPDATE STORE SET TEL=?, LOCATION=?, DELIVERY_PRICE = ?, LEAST_PRICE = ?, STORE_IMG = ?, INFO = ? WHERE NAME = ?";
	private final String CONDITION_UPDATE = "UPDATE STORE SET STORE.CONDITION=?, CONDITION_VALUE=?, CONDITION_DAY=? WHERE NAME=?";
	private final String STORE_MENU_GET = "SELECT * FROM MENU WHERE STORE_NAME = ?";
	private final String INSERT_MENU = "INSERT INTO MENU(STORE_NAME, TYPE, MENUBAR, FOOD, FOOD_PRICE, FOOD_IMG, FOOD_OPT) VALUES (?,?,?,?,?,?,?)";
	private final String INSERT_MENU_BAR = "INSERT INTO MENU(store_name, type, menubar) VALUES (?,?,?)";
	private final String DELETE_MENU = "DELETE FROM MENU WHERE SEQ = ?";
	
// 가게목록 
	public AdminStoreDTO getAdminStore(AdminStoreDTO vo){
		Object[] args = {vo.getName()};
		return jdbcTemplate.queryForObject(STORE_GET, args, new AdminRowMapper());
	}
	
// 가게정보 수정
	public void updateStore(AdminStoreDTO vo) {
		jdbcTemplate.update(STORE_UPDATE, vo.getTel(), vo.getLocation(), vo.getDelivery_price(), 
				vo.getLeast_price(), vo.getStore_img(), vo.getInfo(), vo.getName());
	}
//메뉴 목록
	public List<AdminStoreMenuDTO> getMenuList(AdminStoreMenuDTO vo) {
		Object[] args= {vo.getStore_name()};
		return jdbcTemplate.query(STORE_MENU_GET, args, new MenuRowMapper());
	}

// 메뉴 추가
	public void insertMenu(AdminStoreMenuDTO vo) {
		if (vo.getType() == 0) {
			jdbcTemplate.update(INSERT_MENU_BAR, vo.getStore_name(), vo.getType(), vo.getMenubar());
		}
		else if(vo.getType() == 1) {
			
			jdbcTemplate.update(INSERT_MENU, vo.getStore_name(), vo.getType(), vo.getMenubar(), 
					vo.getFood(), vo.getFood_price(), vo.getFood_img(), vo.getFood_opt());
		}
	}	
// 메뉴 삭제
	public void deleteMenu(AdminStoreMenuDTO vo) {
		jdbcTemplate.update(DELETE_MENU, vo.getSeq());
	}	
	
// 조건 수정
	public void updateCondition(AdminStoreDTO vo) {
		jdbcTemplate.update(CONDITION_UPDATE, vo.getCondition(), vo.getCondition_value(), vo.getCondition_day(), 
				vo.getName());
	}
	
	
// Row Mapper
	class AdminRowMapper implements RowMapper<AdminStoreDTO> {
		public AdminStoreDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
			AdminStoreDTO adminStore = new AdminStoreDTO();
			adminStore.setName(rs.getString("name"));
			adminStore.setTel(rs.getString("tel"));
			adminStore.setLocation(rs.getString("location"));
			adminStore.setDelivery_price(rs.getInt("delivery_price"));
			adminStore.setLeast_price(rs.getInt("least_price"));
			adminStore.setStore_img(rs.getString("store_img"));		
			adminStore.setInfo(rs.getString("info"));
			adminStore.setCondition(rs.getString("condition"));
			adminStore.setCondition_value(rs.getInt("condition_value"));
			adminStore.setCondition_day(rs.getInt("condition_day"));
			return adminStore;
		}
	}
	
	class MenuRowMapper implements RowMapper<AdminStoreMenuDTO> {
		public AdminStoreMenuDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			AdminStoreMenuDTO menu = new AdminStoreMenuDTO();
			menu.setSeq(rs.getInt("seq"));
			menu.setStore_name(rs.getString("store_name"));
			menu.setType(rs.getInt("type"));
			menu.setMenubar(rs.getString("menubar"));
			menu.setFood(rs.getString("food"));
			menu.setFood_price(rs.getInt("food_price"));
			menu.setFood_img(rs.getString("food_img"));
			menu.setFood_opt(rs.getString("food_opt"));
			return menu;
		}
	}
}


