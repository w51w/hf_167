package com.spring.biz.admin.order.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.biz.admin.order.AdminOrderCntDTO;
import com.spring.biz.admin.order.AdminOrderDTO;
import com.spring.biz.admin.store.AdminStoreDTO;

@Repository("adminOrderDAO")
public class AdminOrderDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String PHONE_GET = "SELECT phone FROM user WHERE e_mail = ?";
	private String ORDER_LIST_GET = "SELECT o.seq, o.date_order ,o.user_e_mail, o.address, o.address_detail, d.food1, d.food2, d.food3, d.food4, d.food5, hf_167_2차수정.o.type FROM hf_167_2차수정.order as o JOIN order_detail as d ON o.seq = d.order_seq WHERE hf_167_2차수정.o.type < 2 AND o.store_name=? ORDER BY o.seq;";
	private String TYPE_UPDATE = "UPDATE hf_167_2차수정.order SET type =? WHERE SEQ =?";
	private String ORDER_LOG_LIST_GET = "SELECT o.seq, o.date_order, o.user_e_mail, o.address, o.address_detail, d.food1, d.food2, d.food3, d.food4, d.food5, hf_167_2차수정.o.type FROM hf_167_2차수정.order as o JOIN order_detail as d ON o.seq = d.order_seq WHERE o.store_name=? ORDER BY o.seq";
	private String SUM_REGULAR_LIST_GET = "SELECT o.user_e_mail, d.* FROM hf_167_2차수정.order as o JOIN order_detail as d ON o.seq = d.order_seq WHERE o.date_order > date(subdate(curdate(), interval ? day)) and store_name=?"; 
	private String COUNT_REGULAR_LIST_GET = "select user_e_mail, count(*) as cnt from hf_167_2차수정.order where date_order > date(subdate(curdate(), interval ? day))and store_name=? group by user_e_mail";

	
	public String getPhone(String phone) {
		Object[] args = {phone};
		return jdbcTemplate.queryForObject(PHONE_GET, args, new PhoneRowMapper());
	}
	
	public List<AdminOrderDTO> getOrder_List(AdminOrderDTO vo) {
		Object[] args = {vo.getStore_name()};
		return jdbcTemplate.query(ORDER_LIST_GET, args, new OrderRowMapper());
	}
	
	public void typeUpdate(AdminOrderDTO vo)  {
		jdbcTemplate.update(TYPE_UPDATE, vo.getType() , vo.getSeq());
	}
	
	public List<AdminOrderDTO> getOrderLog_List(AdminOrderDTO vo) {
		Object[] args = {vo.getStore_name()};
		return jdbcTemplate.query(ORDER_LOG_LIST_GET, args, new OrderRowMapper());
	}
	
	public List<AdminOrderDTO> sumRegular_list(AdminStoreDTO vo){
		Object[] args = {vo.getCondition_day(), vo.getName()};
		return jdbcTemplate.query(SUM_REGULAR_LIST_GET, args, new SumRowMapper());
	}
	
	public List<AdminOrderCntDTO> countRegular_list(AdminStoreDTO vo){
		Object[] args = {vo.getCondition_day(), vo.getName()};
		return jdbcTemplate.query(COUNT_REGULAR_LIST_GET, args, new CountRowMapper());
	}
	
	class OrderRowMapper implements RowMapper<AdminOrderDTO> {
		public AdminOrderDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			AdminOrderDTO orderVO = new AdminOrderDTO();
			orderVO.setSeq(rs.getInt("SEQ"));
			orderVO.setType(rs.getInt("TYPE"));
			StringBuilder builder = new StringBuilder(rs.getTimestamp("date_order").toString());
			orderVO.setDate_order(builder.substring(0, builder.length()-2));
			orderVO.setUser_e_mail(rs.getString("USER_E_MAIL"));
			orderVO.setAddress(rs.getString("ADDRESS"));
			orderVO.setAddress_detail(rs.getString("ADDRESS_DETAIL"));
			orderVO.setFood1(rs.getString("FOOD1"));
			orderVO.setFood2(rs.getString("FOOD2"));
			orderVO.setFood3(rs.getString("FOOD3"));
			orderVO.setFood4(rs.getString("FOOD4"));
			orderVO.setFood5(rs.getString("FOOD5"));
			return orderVO;
		}
	}
	
	class SumRowMapper implements RowMapper<AdminOrderDTO> {
		public AdminOrderDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			AdminOrderDTO orderVO = new AdminOrderDTO();
			orderVO.setUser_e_mail(rs.getString("USER_E_MAIL"));
			orderVO.setSeq(rs.getInt("ORDER_SEQ"));
			orderVO.setFood1(rs.getString("FOOD1"));
			orderVO.setFood2(rs.getString("FOOD2"));
			orderVO.setFood3(rs.getString("FOOD3"));
			orderVO.setFood4(rs.getString("FOOD4"));
			orderVO.setFood5(rs.getString("FOOD5"));
			return orderVO;
		}
	}
	

	class CountRowMapper implements RowMapper<AdminOrderCntDTO>{
		public AdminOrderCntDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			AdminOrderCntDTO cnt = new AdminOrderCntDTO();
			cnt.setUser_e_mail(rs.getString("user_e_mail"));
			cnt.setCnt(rs.getInt("cnt"));
			return cnt;
		}
	}
	
	class PhoneRowMapper implements RowMapper<String>{
		public String mapRow(ResultSet rs, int rowNum) throws SQLException{
			return rs.getString("phone");
		}
	}
}
