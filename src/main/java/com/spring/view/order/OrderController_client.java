package com.spring.view.order;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.biz.order.OrderCartDTO;
import com.spring.biz.order.OrderCartListDTO;
import com.spring.biz.order.OrderDTO;
import com.spring.biz.order.OrderDetailDTO;
import com.spring.biz.order.OrderListDTO;
import com.spring.biz.order.OrderService;
import com.spring.biz.order.impl.OrderDAO;


@RestController
public class OrderController_client {

	@Autowired
	private OrderService orderService;
	
	//orderCart
	@RequestMapping("/getOrderCart.do")
	public OrderCartListDTO getOrderCart(HttpServletRequest request) {
		String e_mail = request.getParameter("e_mail");
		return orderService.getOrderCartList_client(e_mail);
	}
	
	
	@RequestMapping("/orderCartAdd.do")
	public String insert_orderCart(OrderCartDTO vo) {	
		return orderService.insert_OrderCart_client(vo);
	}
	
	@RequestMapping("/delete_cart.do")
	public void delete_cart(HttpServletRequest request) {
		String e_mail = request.getParameter("e_mail");
		orderService.delete_cart(e_mail);
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
	
	// order
	@RequestMapping("/order_insert.do")
	public int order_insert(OrderDTO vo) {
		return orderService.insert_order(vo);
	}
	
	@RequestMapping("/order_detail_insert.do")
	public void orderDetail_insert(OrderDetailDTO vo) {
		orderService.insert_OrderDetail(vo);
	}
	
	@RequestMapping("/getOrderList.do")
	public OrderListDTO getOrderList(String e_mail) {
		return orderService.getOrderList(e_mail);
	}
	
	@RequestMapping("/getOrderDetail.do")
	public OrderDetailDTO getOrderDetail(HttpServletRequest request) {
		int order_seq = Integer.parseInt(request.getParameter("order_seq"));
		return orderService.getOrderDetail(order_seq);
	}
		
}
