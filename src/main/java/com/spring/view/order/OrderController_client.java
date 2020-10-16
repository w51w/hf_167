package com.spring.view.order;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.biz.order.OrderCartDTO;
import com.spring.biz.order.OrderCartListDTO;
import com.spring.biz.order.OrderService;


@RestController
public class OrderController_client {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/orderCart.do")
	public OrderCartListDTO getOrderCart(HttpServletRequest request) {
		String e_mail = request.getParameter("e_mail");
		return orderService.getOrderCartList_client(e_mail);
	}
	
	
	@RequestMapping("/orderCartAdd.do")
	public String insert_orderCart(HttpServletRequest request) {
		String e_mail = request.getParameter("e_mail");
		String store_name = request.getParameter("store_name");
		String food = request.getParameter("food");
		String food_cnt = request.getParameter("food_cnt");
		String food_price = request.getParameter("food_price");
		String food_opt = request.getParameter("food_opt");
		
		OrderCartDTO vo = new OrderCartDTO();
		vo.setE_mail(e_mail);
		vo.setStore_name(store_name);
		vo.setFood(food);
		vo.setFood_cnt(Integer.parseInt(food_cnt));
		vo.setFood_price(Integer.parseInt(food_price));
		vo.setFood_opt(food_opt);
		
		return orderService.insertOrderCart_client(vo);
	}
	@RequestMapping("/delete_cart_selected.do")
	public String delete_cart_selected(HttpServletRequest request) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		return orderService.delete_cart_selected(seq);		
	}
	@RequestMapping("/maxCheck.do")
	public String maxCheck(HttpServletRequest request) {
		String e_mail = request.getParameter("e_mail");
		String store_name = request.getParameter("store_name");
		return orderService.maxCheck(e_mail, store_name);
	}
}
