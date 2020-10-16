package com.spring.biz.order.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.order.OrderCartDTO;
import com.spring.biz.order.OrderCartListDTO;
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
	public String insertOrderCart_client(OrderCartDTO vo) {
		return orderDAO.insertOrderCart_client(vo);
	}
	@Override
	public String delete_cart_selected(int seq) {
		return orderDAO.delete_cart_selected(seq);
	}
	@Override
	public String maxCheck(String e_mail, String store_name) {
		return orderDAO.maxCheck(e_mail, store_name);
	}

}
