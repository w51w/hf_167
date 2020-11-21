package com.spring.biz.regular.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.biz.common.JDBCUtil;
import com.spring.biz.regular.ConditionDTO;
import com.spring.biz.regular.OrderDetailDTO;

@Repository("RegularDAO")
public class RegularDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/*==> 주문했던 가게목록 중복제거*/
	private final String GET_STORE_CONDITION = 
			"select name, store_img, store.condition, condition_value, condition_day from store where name in (select distinct store_name from hf_167_2차수정.order where user_e_mail= ? and type = 2 or type = 4)";
	/*==>주문 횟수 검색*/
	private final String COUNT_REGULAR_LIST_GET = 
			"select count(*) as cnt from hf_167_2차수정.order where date_order > date(subdate(curdate(), interval ? day)) and store_name=? and user_e_mail = ?";
	/*==> 주문 내역 검색 (합산가격 뽑아내기)*/
	private final String SUM_REGULAR_LIST_GET = 
			"SELECT o.user_e_mail, d.* FROM hf_167_2차수정.order as o JOIN order_detail as d ON o.seq = d.order_seq WHERE o.date_order > date(subdate(curdate(), interval ? day)) and store_name=? and user_e_mail=?";
	
	
	public List<ConditionDTO> getStoreList(String user_e_mail){
		List<ConditionDTO> storeList = new ArrayList<ConditionDTO>();
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_STORE_CONDITION);
			pstmt.setString(1, user_e_mail);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ConditionDTO VO = new ConditionDTO();
				VO.setName(rs.getString("name"));
				VO.setStore_img(rs.getString("store_img"));
				VO.setCondition(rs.getString("condition"));
				VO.setCondition_day(rs.getInt("condition_day"));
				VO.setCondition_value(rs.getInt("condition_value"));
				storeList.add(VO);				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.close(rs, pstmt, conn);
		}	
		return storeList;
	}
	public int getCnt(int day, String store_name, String user_e_mail){
		int num = 0;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(COUNT_REGULAR_LIST_GET);
			pstmt.setInt(1, day);
			pstmt.setString(2, store_name);
			pstmt.setString(3, user_e_mail);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				num = rs.getInt("cnt");	
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return num;
	}

	public List<OrderDetailDTO> getSum (int day, String store_name, String user_e_mail){
		List<OrderDetailDTO> list = new ArrayList<OrderDetailDTO>();
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(SUM_REGULAR_LIST_GET);
			pstmt.setInt(1, day);
			pstmt.setString(2, store_name);
			pstmt.setString(3, user_e_mail);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderDetailDTO VO = new OrderDetailDTO();
				VO.setUser_e_mail(rs.getString("user_e_mail"));
				VO.setOrder_seq(rs.getInt("order_seq"));
				VO.setFood1(rs.getString("food1"));
				VO.setFood2(rs.getString("food2"));
				VO.setFood3(rs.getString("food3"));
				VO.setFood4(rs.getString("food4"));
				VO.setFood5(rs.getString("food5"));
				list.add(VO);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return list;
	}
}
