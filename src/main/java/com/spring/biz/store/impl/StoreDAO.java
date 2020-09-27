package com.spring.biz.store.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.spring.biz.common.JDBCUtil;
import com.spring.biz.store.StoreDTO;

public class StoreDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private final String GET_STORE_LIST = "select * from store where category_code=?";
	
	public ArrayList<StoreDTO> getStoreList_clinet(int code) {
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
				store.setLeast_price(rs.getInt("least_price"));
				store.setRate(rs.getDouble("rate"));
				store.setInfo(rs.getString("info"));
				store.setCondition(rs.getString("condition"));
				storeList.add(store);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return storeList;
		
	}
}
