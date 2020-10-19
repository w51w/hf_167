package com.spring.biz.admin;

import java.util.List;

public interface OrderService {

	List<OrderVO> getOrder_List(OrderVO vo);
	
	void orderProcess(OrderVO vo) throws Exception;
	
	void orderDelivery(OrderVO vo);
	
	void orderEnd(OrderVO vo);
	
	void orderCancel(OrderVO vo);

}