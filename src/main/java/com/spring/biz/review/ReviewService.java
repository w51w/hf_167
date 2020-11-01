package com.spring.biz.review;

import com.spring.biz.order.OrderDTO;
import com.spring.biz.order.OrderListDTO;

public interface ReviewService {
	public String delete_cart(OrderDTO vo, String img_path);
	
	public String getRate(String store_name);

	public OrderListDTO getReviewList(String store_name);
}
