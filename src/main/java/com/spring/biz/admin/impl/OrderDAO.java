package com.spring.biz.admin.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.biz.admin.AdminVO;
import com.spring.biz.admin.OrderVO;


@Repository("admin_orderDAO")
public class OrderDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String getOrder_List = "SELECT o.seq ,o.user_e_mail, o.address, o.address_detail, d.food1, d.food2, d.food3, d.food4, d.food5, hf_167.o.type FROM hf_167.order as o JOIN order_detail as d ON o.seq = d.order_seq WHERE hf_167.o.type <= 2 AND o.store_name=? ORDER BY o.seq;";
	private String getOrderLog_List = "SELECT o.seq, o.user_e_mail, o.address, o.address_detail, o.date_order, d.food1, d.food2, d.food3, d.food4, d.food5, hf_167.o.type FROM hf_167.order as o JOIN order_detail as d ON o.seq = d.order_seq WHERE o.store_name=? ORDER BY o.seq";
	private String orderDelivery = "UPDATE hf_167.order SET type =1 WHERE SEQ =?";
	private String orderEnd = "UPDATE hf_167.order SET type =2 WHERE SEQ =?";
	private String orderCancel = "UPDATE hf_167.order SET type = 3 WHERE SEQ =?";

	
	
	public List<OrderVO> getOrder_List(OrderVO vo) {
		Object[] args = {vo.getStore_name()};
		return jdbcTemplate.query(getOrder_List, args, new OrderRowMapper());
	}
	
	
	public List<OrderVO> getOrder_Log(OrderVO vo) {
		Object[] args = {vo.getStore_name()};
		System.out.println(vo.getStore_name());
		return jdbcTemplate.query(getOrderLog_List, args, new OrderLogRowMapper());
	}
	
	
	
	public void orderDelivery(OrderVO vo)  {
		jdbcTemplate.update(orderDelivery, vo.getSeq());
	}
	
	
	public void orderEnd(OrderVO vo) {
		jdbcTemplate.update(orderEnd, vo.getSeq());
	}
	
	public void orderCancel(OrderVO vo) {
		jdbcTemplate.update(orderCancel, vo.getSeq());
	}
	
	
	
	// 주문처리(process)0, 배달중 1 , 배달완료 2 , 주문취소 3
	
//	<th>주소</th>  order테이블 = address
//    <th>상세주소</th> order테이블= address_detail
//    <th>주문메뉴 및 수량</th> order_detail테이블 = food1
//    <th>상태</th> order테이블 type
	
	class OrderRowMapper implements RowMapper<OrderVO> {
		public OrderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrderVO orderVO = new OrderVO();
			orderVO.setSeq(rs.getInt("SEQ"));
			orderVO.setUser_e_mail(rs.getString("USER_E_MAIL"));
			orderVO.setAddress(rs.getString("ADDRESS"));
			orderVO.setAddress_detail(rs.getString("ADDRESS_DETAIL"));
			orderVO.setType(rs.getInt("TYPE"));
			orderVO.setFood1(rs.getString("FOOD1"));
			orderVO.setFood2(rs.getString("FOOD2"));
			orderVO.setFood3(rs.getString("FOOD3"));
			orderVO.setFood4(rs.getString("FOOD4"));
			orderVO.setFood5(rs.getString("FOOD5"));
			return orderVO;
		}
	}
	
	
	class OrderLogRowMapper implements RowMapper<OrderVO> {
		public OrderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrderVO orderVO = new OrderVO();
			orderVO.setSeq(rs.getInt("SEQ"));
			orderVO.setUser_e_mail(rs.getString("USER_E_MAIL"));
			orderVO.setAddress(rs.getString("ADDRESS"));
			orderVO.setAddress_detail(rs.getString("ADDRESS_DETAIL"));
			orderVO.setType(rs.getInt("TYPE"));
			orderVO.setDate_order(rs.getDate("DATE_ORDER"));
			orderVO.setFood1(rs.getString("FOOD1"));
			orderVO.setFood2(rs.getString("FOOD2"));
			orderVO.setFood3(rs.getString("FOOD3"));
			orderVO.setFood4(rs.getString("FOOD4"));
			orderVO.setFood5(rs.getString("FOOD5"));
			return orderVO;
		}
	}
}
