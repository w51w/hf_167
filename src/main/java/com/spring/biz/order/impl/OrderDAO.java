package com.spring.biz.order.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.biz.admin.AdminVO;
import com.spring.biz.order.OrderService;
import com.spring.biz.order.OrderVO;

@Repository("orderDAO")
public class OrderDAO implements OrderService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String getOrder_List = "SELECT  o.user_e_mail, o.address, o.address_detail, d.food1, d.food2, d.food3, d.food4, d.food5, user.o.type FROM user.order as o JOIN order_detail as d ON o.seq = d.order_seq ORDER BY o.seq;";
	private String getOrderLog_List = "SELECT * FROM ORDER_DETAIL";
	
	@Override
	public List<OrderVO> getOrder_List(OrderVO vo) {
		return jdbcTemplate.query(getOrder_List, new OrderRowMapper());
	}
	
	
//	<th>주소</th>  order테이블 = address
//    <th>상세주소</th> order테이블= address_detail
//    <th>주문메뉴 및 수량</th> order_detail테이블 = food1
//    <th>상태</th> order테이블 type
	
	class OrderRowMapper implements RowMapper<OrderVO> {
		public OrderVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			OrderVO orderVO = new OrderVO();
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

}