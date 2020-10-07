package com.spring.biz.store.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.biz.common.JDBCUtil;
import com.spring.biz.store.StoreDTO;
import com.spring.biz.store.StoreListDTO;
import com.spring.biz.store.StoreMenuDTO;
import com.spring.biz.store.StoreMenuListDTO;

@Repository("storeDAO")
public class StoreDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private final String GET_STORE_LIST = "select * from store where category_code=?";
	private final String GET_STORE_MENU_HEAD ="select * from menu where store_name = ? and type = 0";
	private final String GET_STORE_MENU_CHILD ="select * from menu where store_name = ? and menubar = ? and type = 1";
	
	public StoreListDTO getStoreList_client(int code) {
		StoreListDTO storeListDTO = new StoreListDTO();
		ArrayList<StoreDTO> storeList = new ArrayList<StoreDTO>();
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_STORE_LIST);
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				StoreDTO store = new StoreDTO();
				store.setName(rs.getString("name"));
				store.setCategory_name(rs.getInt("category_code"));
				store.setTel(rs.getString("tel"));
				store.setLocation(rs.getString("location"));
				store.setDelivery_price(rs.getInt("delivery_price"));
				store.setStore_img(rs.getString("store_img"));
				store.setLeast_price(rs.getInt("least_price"));
				store.setRate(rs.getDouble("rate"));
				store.setInfo(rs.getString("info"));
				store.setCondition(rs.getString("condition"));
				storeList.add(store);
			}
			storeListDTO.setStoreList(storeList);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return storeListDTO;
	}
	
	public StoreMenuListDTO getMenu_client(String store_name) {
		StoreMenuListDTO list = new StoreMenuListDTO();
		 ArrayList<StoreMenuDTO> data = new ArrayList<StoreMenuDTO>();
		  ArrayList<String> menubar = new ArrayList<String>(); 
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_STORE_MENU_HEAD); 
			pstmt.setString(1, store_name);
			rs = pstmt.executeQuery();
			while(rs.next()) {			//store_name=?, type=0 메뉴바 검색 후 저장
				menubar.add(rs.getString("menubar"));
			}
			for(int i =0; i<menubar.size(); i++) { //메뉴바의 개수만큼 쿼리문 실행
				StoreMenuDTO temp1 = new StoreMenuDTO();				
				temp1.child = new ArrayList<StoreMenuDTO>();
				pstmt = conn.prepareStatement(GET_STORE_MENU_CHILD);
				pstmt.setString(1, store_name);
				pstmt.setString(2, menubar.get(i));
				rs = pstmt.executeQuery();
				while(rs.next()) {			//store_name=?, type=1, menubar=? 자식 검색 후 저장
					StoreMenuDTO temp2 = new StoreMenuDTO();
					temp2.setStore_name(rs.getString("store_name"));
					temp2.setType(rs.getInt("type"));
					temp2.setMenubar(rs.getString("menubar"));
					temp2.setFood(rs.getString("food"));
					temp2.setFood_price(rs.getInt("food_price"));
					temp2.setFood_img(rs.getString("food_img"));
					temp2.setFood_opt(rs.getString("food_opt"));
					temp1.child.add(temp2);
					System.out.println(temp1.toString());
				}
				temp1.setType(0);
				temp1.setMenubar(menubar.get(i));
				data.add(temp1);		//메뉴바가 하나 이상이면 for 문 안에서 add 시켜야 됨
			}
			System.out.println(data.toString());
			list.setList(data);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return list;
		
	}
}
