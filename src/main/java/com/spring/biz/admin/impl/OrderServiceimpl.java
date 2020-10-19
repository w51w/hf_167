package com.spring.biz.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.admin.OrderService;
import com.spring.biz.admin.OrderVO;


@Service("admin_orderService")
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
