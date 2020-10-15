package com.spring.biz.order.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.order.OrderService;
import com.spring.biz.order.OrderVO;

@Service("orderService")
public class OrderServiceimpl implements OrderService{

	@Autowired
	private OrderDAO orderDAO;
	
	@Override
	public List<OrderVO> getOrder_List(OrderVO vo) {
		return orderDAO.getOrder_List(vo);
	}

	@Override
	public void orderType(OrderVO vo) throws Exception {
		orderDAO.orderType(vo);
	}
	
}
