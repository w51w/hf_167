package com.spring.biz.order.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.order.OrderCartDTO;
import com.spring.biz.order.OrderCartListDTO;
import com.spring.biz.order.OrderDTO;
import com.spring.biz.order.OrderDetailDTO;
import com.spring.biz.order.OrderListDTO;
import com.spring.biz.order.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDAO orderDAO;
	@Override
	public OrderCartListDTO getOrderCartList_client(String e_mail) {
		return orderDAO.getOrderCartList_client(e_mail);
	}
	@Override
	public String insert_OrderCart_client(OrderCartDTO vo) {
		return orderDAO.insert_OrderCart_client(vo);
	}
	
	@Override
	public void delete_cart(String e_mail) {
		orderDAO.delete_cart(e_mail);
	}
	
	@Override
	public String delete_cart_selected(int seq) {
		return orderDAO.delete_cart_selected(seq);
	}
	@Override
	public String maxCheck(String e_mail, String store_name) {
		return orderDAO.maxCheck(e_mail, store_name);
	}

	//order
	@Override
	public int insert_order(OrderDTO vo) {
		return orderDAO.insert_Order(vo);
	}
	@Override
	public void insert_OrderDetail(OrderDetailDTO vo) {
		orderDAO.insert_OrderDetail(vo);
	}
	@Override
	public OrderListDTO getOrderList(String e_mail) {
		return orderDAO.getOrderList(e_mail);
	}
	
}
