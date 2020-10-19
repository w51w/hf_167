package com.spring.biz.order.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.spring.biz.common.JDBCUtil;
import com.spring.biz.order.OrderCartDTO;
import com.spring.biz.order.OrderCartListDTO;
import com.spring.biz.order.OrderDTO;
import com.spring.biz.order.OrderDetailDTO;
import com.spring.biz.order.OrderListDTO;
import com.spring.biz.user.UserDTO;

@Repository("orderDAO")
public class OrderDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	/////		ORDER_CART
	private final String ORDER_CART_GET_CLIENT = "select * from order_cart where e_mail=?";
	private final String CHECK = "select * from order_cart where e_mail=? and store_name=?";
	private final String CHECK_MAX = "select count(*) as cnt from order_cart where e_mail=? and store_name=?";
	private final String DELETE_CART = "delete from order_cart where e_mail=?";
	private final String DELETE_CART_SELECTED = "delete from order_cart where seq=? ";
	private final String INSERT_CART = "insert into order_cart(e_mail,store_name,food,food_cnt,food_price,food_opt) "
			+ "VALUES (?,?,?,?,?,?)";

	public OrderCartListDTO getOrderCartList_client(String e_mail) {
		OrderCartListDTO cartListDTO = new OrderCartListDTO();
		ArrayList<OrderCartDTO> list = new ArrayList<OrderCartDTO>();
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(ORDER_CART_GET_CLIENT);
			pstmt.setString(1, e_mail);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderCartDTO  cartDTO = new OrderCartDTO();
				cartDTO.setSeq(rs.getInt("seq"));
				cartDTO.setE_mail(rs.getString("e_mail"));
				cartDTO.setStore_name(rs.getString("store_name"));
				cartDTO.setFood(rs.getString("food"));
				cartDTO.setFood_cnt(rs.getInt("food_cnt"));
				cartDTO.setFood_price(rs.getInt("food_price"));
				cartDTO.setFood_opt(rs.getString("food_opt"));
				list.add(cartDTO);
			}
			cartListDTO.setCartList(list);
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return cartListDTO;
	}

	public String insert_OrderCart_client(OrderCartDTO vo) {
		String result = null;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(CHECK); //�˻�
			pstmt.setString(1, vo.getE_mail());
			pstmt.setString(2, vo.getStore_name());
			rs = pstmt.executeQuery();
			if(rs.next() == false) { //���� ���� ���� ����� ���ٸ� ������ �͵��� ������ ��
				pstmt = conn.prepareStatement(DELETE_CART);
				pstmt.setString(1, vo.getE_mail());
				pstmt.executeUpdate();
			}
			pstmt = conn.prepareStatement(INSERT_CART);
			pstmt.setString(1, vo.getE_mail());
			pstmt.setString(2, vo.getStore_name());
			pstmt.setString(3, vo.getFood());
			pstmt.setInt(4, vo.getFood_cnt());
			pstmt.setInt(5, vo.getFood_price());
			pstmt.setString(6, vo.getFood_opt());
			System.out.println(vo.getFood_opt());
			if(pstmt.executeUpdate() == 1) { //������ �޴� �� ��
				result = "true";
			}
			else {
				result = "false";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return result;
	}
	
	public void delete_cart(String e_mail) {
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(DELETE_CART);
			pstmt.setString(1, e_mail);
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	public String delete_cart_selected(int seq) {

		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(DELETE_CART_SELECTED);
			pstmt.setInt(1, seq);
			if(pstmt.executeUpdate() == 1) {
				return "true";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.close(pstmt, conn);
		}
		return "false";	
	}
	
	public String maxCheck(String e_mail, String store_name) {
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(CHECK_MAX);
			pstmt.setString(1, e_mail);
			pstmt.setString(2, store_name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getInt("cnt"));
				//??? 5���� ���ω�µ� �ֿܼ��� 4���� ���� ?? mysql������ 6���� �����µ�
				//�� ����� �ϳ� ��Ͻ��״��� 0���� �αװ� ���
				//��� ���ϰ��� +1 �ٿ��� ��
				if(rs.getInt("cnt")+1 > 5) {
					
					return "true";
				}		
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return "false";
	}
	/////		ORDER_CART
	
	
	/////		ORDER
	private final String ORDER_INSERT = "insert into hf_167_2������.order (`user_e_mail`,`store_name`,`address`,`address_detail`) values(?,?,?,?); ";
	private final String LAST_INSERTED = "select max(seq) from hf_167_2������.order";
	private final String ORDER_DETAIL_INSERT = "insert into order_detail (`order_seq`,`food1`,`food2`,`food3`,`food4`,`food5`) values(?,?,?,?,?,?)";
	private final String GET_ORDER_LIST = "select * from hf_167_2������.order where user_e_mail = ?";
	public int insert_Order(OrderDTO vo) {
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(ORDER_INSERT);
			pstmt.setString(1, vo.getUser_e_mail());
			pstmt.setString(2, vo.getStore_name());
			pstmt.setString(3, vo.getAddress());
			pstmt.setString(4, vo.getAddress_detail());
			if(pstmt.executeUpdate() == 1) {
				pstmt = conn.prepareStatement(LAST_INSERTED);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					return rs.getInt("max(seq)");
				}
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return 0;
	}
	
	public void insert_OrderDetail(OrderDetailDTO vo) {
		try {
			System.out.println(vo.getFood1().toString());
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(ORDER_DETAIL_INSERT);
			pstmt.setInt(1, vo.getOrder_seq());
			pstmt.setString(2, vo.getFood1());
			pstmt.setString(3, vo.getFood2());
			pstmt.setString(4, vo.getFood3());
			pstmt.setString(5, vo.getFood4());
			pstmt.setString(6, vo.getFood5());
			if(pstmt.executeUpdate() == 1) {
				System.out.println("success");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	public OrderListDTO getOrderList(String e_mail){
		OrderListDTO orderListDTO = new OrderListDTO();
		ArrayList<OrderDTO> list = new ArrayList<OrderDTO>();
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(GET_ORDER_LIST);
			pstmt.setString(1, e_mail);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderDTO orderDTO = new OrderDTO();
				orderDTO.setType(rs.getInt("type"));
				orderDTO.setStore_name(rs.getString("store_name"));
				orderDTO.setAddress(rs.getString("address"));
				orderDTO.setAddress_detail(rs.getString("address_detail"));
				orderDTO.setDate_order(rs.getDate("date_order"));
				orderDTO.setRate(rs.getDouble("rate"));
				orderDTO.setReview(rs.getString("review"));
				orderDTO.setReview_img(rs.getString("review_img"));
				//getDate �� �ú��ʰ� ���� , getTimestamp�� ���� ��������
				//date Ÿ�� -> gson���� ���� ����
				//TODO :====> Date -> String���� Ÿ�� ��ȯ���� gson���� ����
				orderDTO.setDate_review(rs.getDate("date_review"));
				System.out.println(orderDTO.getDate_order());
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
	/////		ORDER
}

