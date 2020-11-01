package com.spring.biz.review.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.spring.biz.common.JDBCUtil;
import com.spring.biz.order.OrderDTO;
import com.spring.biz.order.OrderListDTO;

@Repository("ReviewDAO")
public class ReviewDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private final String REVIEW_INSERT = "update hf_167_2������.order set type=?, rate=?, review=?, review_img=?, date_review=timestamp(now()) WHERE seq=?";
	private final String GET_RATE = "select round(avg(rate), 2) as avg from hf_167_2������.order where store_name = ?";
	private final String GET_ORDER_LIST = "select * from hf_167_2������.order where store_name = ? and type=4 order by seq desc";
	
	public String review_insert(OrderDTO vo, String img_path) {
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(REVIEW_INSERT);
			//0 �ֹ����   Ŭ���̾�Ʈ���� ���
			//1 �ֹ����� (���� ��, ��� ��)   ������ ��Ʈ��
			//2 ��޿Ϸ�   Ŭ���̾�Ʈ���� ������Ʈ
			//3 �ֹ����   ������ ��Ʈ��
			//4 �����ۼ��Ϸ�
			pstmt.setInt(1,4);
			pstmt.setDouble(2, vo.getRate());
			pstmt.setString(3, vo.getReview());
			pstmt.setString(4, img_path);
			pstmt.setInt(5, vo.getSeq());
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.close(pstmt, conn);
		}
		return "true";
	}
	
	public String getRate(String store_name) {
		double avg = 0;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_RATE);
			pstmt.setString(1, store_name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				avg = rs.getDouble("avg");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.close(pstmt, conn);
		}
		return String.valueOf(avg);
	}
	
	public OrderListDTO getReviewList(String store_name){

		OrderListDTO orderListDTO = new OrderListDTO();
		ArrayList<OrderDTO> list = new ArrayList<OrderDTO>();
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_ORDER_LIST);
			pstmt.setString(1, store_name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderDTO orderDTO = new OrderDTO();
				orderDTO.setSeq(rs.getInt("seq"));
				orderDTO.setType(rs.getInt("type"));
				orderDTO.setUser_e_mail(rs.getString("user_e_mail"));
				orderDTO.setStore_name(rs.getString("store_name"));
				orderDTO.setAddress(rs.getString("address"));
				orderDTO.setAddress_detail(rs.getString("address_detail"));
				StringBuilder sb = new StringBuilder(rs.getTimestamp("date_order").toString());
				orderDTO.setDate_order(sb.substring(0, sb.length()-2));
				orderDTO.setRate(rs.getDouble("rate"));
				orderDTO.setReview(rs.getString("review"));
				orderDTO.setReview_img(rs.getString("review_img"));
				//getDate �� �ú��ʰ� ���� , getTimestamp�� ���� ��������
				//date Ÿ�� -> gson���� ���� ����
				//:====> Date -> String���� Ÿ�� ��ȯ���� gson���� ����
				//StringBuiler�� �۾��� ���� ������  �������� '.0'�� �پ ���´�.
				StringBuilder sb1 = new StringBuilder(rs.getTimestamp("date_review").toString());
				orderDTO.setDate_review(sb1.substring(0, sb1.length()-2));
				list.add(orderDTO);
			}
			orderListDTO.setList(list);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return orderListDTO;
	}

}
