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
	public void orderProcess(OrderVO vo) throws Exception {
		orderDAO.orderProcess(vo);
	}

	@Override
	public void orderDelivery(OrderVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void orderEnd(OrderVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void orderCancel(OrderVO vo) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
