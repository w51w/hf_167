package com.spring.view.order;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.spring.biz.order.OrderService;
import com.spring.biz.order.OrderVO;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/order.do")
	public String getOrder_List(OrderVO vo, Model model) {
		List<OrderVO> list = new ArrayList();
		ObjectMapper mapper = new ObjectMapper();
		list = orderService.getOrder_List(vo); 
		list.get(0).getFood1().replace("food", "АЁАн");
		System.out.println(list.toString());
		try {
			model.addAttribute("orderList", list);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "order.jsp";
	}
	
	
	@RequestMapping("/orderType.do")
	public String orderType(@RequestParam int seq, OrderVO vo) throws Exception {
		vo.setType(seq);
		orderService.orderType(vo);
		System.out.println(vo.toString());
		return "order.do";
	}
	
	

}
