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
	
	private String getOrder_List = "SELECT o.seq ,o.user_e_mail, o.address, o.address_detail, d.food1, d.food2, d.food3, d.food4, d.food5, hf_167.o.type FROM hf_167.order as o JOIN order_detail as d ON o.seq = d.order_seq WHERE user.o.type = 1 ORDER BY o.seq;";
	private String getOrderLog_List = "SELECT o.user_e_mail, o.address, o.address_detail, o.date_order, d.food1, d.food2, d.food3, d.food4, d.food5 FROM hf_167.order as o JOIN order_detail as d ON o.seq = d.order_seq ORDER BY o.seq";
	private String orderprocess = "UPDATE hf_167.order SET type = ? WHERE SEQ =?";
	private String orderDelivery = "UPDATE user.order SET type =? WHERE SEQ =?";
	private String orderEnd = "UPDATE user.order SET type =? WHERE SEQ =?";

	
	
	public List<OrderVO> getOrder_List(OrderVO vo) {
		return jdbcTemplate.query(getOrder_List, new OrderRowMapper());
	}
	
	
	public List<OrderVO> getOrder_Log(OrderVO vo) {
		return jdbcTemplate.query(getOrderLog_List, new OrderRowMapper());
	}
	
	
	
	public void orderProcess(OrderVO vo) throws Exception {
		System.out.println(vo.toString());
		jdbcTemplate.update(orderprocess, vo.getSeq());
	}
	
	// �ֹ�ó��(process)0, ����� 1 , ��޿Ϸ� 2 , �ֹ���� 3
	
//	<th>�ּ�</th>  order���̺� = address
//    <th>���ּ�</th> order���̺�= address_detail
//    <th>�ֹ��޴� �� ����</th> order_detail���̺� = food1
//    <th>����</th> order���̺� type
	
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