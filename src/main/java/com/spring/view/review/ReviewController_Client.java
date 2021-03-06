package com.spring.view.review;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.biz.order.OrderDTO;
import com.spring.biz.order.OrderListDTO;
import com.spring.biz.review.ReviewService;
import com.spring.biz.review.impl.ReviewDAO;

@RestController
public class ReviewController_Client {
	
	@Autowired
	private ReviewService reviewService;
	
	@RequestMapping("/insert_review.do")
	public String review_insert(HttpServletRequest request , OrderDTO orderDTO) {
        //db에 들어갈 경로는  "/resource~~"이렇게 시작해야됨
		String file_name = request.getParameter("file_name");
	
		String name = file_name.substring(file_name.indexOf("20")); // 20~~
		name = name.substring(0, name.indexOf(".jpg")+4); //20~~.jpg
		
		String img_path = "/resource/" + orderDTO.getStore_name() + "/review_img/" +  name;
		
		System.out.println(img_path);
	
		return reviewService.review_insert(orderDTO, img_path);
	}
	
	@RequestMapping("/getRate.do")
	public String getRate(HttpServletRequest request) {
		String store_name = request.getParameter("store_name");
		return reviewService.getRate(store_name);
	}
	
	@RequestMapping("getReview.do")
	public OrderListDTO getReviewList(HttpServletRequest request) {
		String store_name = request.getParameter("store_name");
		return reviewService.getReviewList(store_name);
		
	}

}
