package com.spring.biz.review.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.order.OrderDTO;
import com.spring.biz.order.OrderListDTO;
import com.spring.biz.review.ReviewService;

@Service("ReviewService")
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewDAO reviewDAO;

	@Override
	public String review_insert(OrderDTO vo, String img_path) {
		return reviewDAO.review_insert(vo, img_path);
	}

	@Override
	public String getRate(String store_name) {
		return reviewDAO.getRate(store_name);
	}

	@Override
	public OrderListDTO getReviewList(String store_name) {
		return reviewDAO.getReviewList(store_name);
	}



}
