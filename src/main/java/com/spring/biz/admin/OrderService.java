package com.spring.biz.admin;

import java.util.List;

public interface OrderService {

	List<OrderVO> getOrder_List(OrderVO vo);
	void orderType(OrderVO vo) throws Exception;

}