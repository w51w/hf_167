package com.spring.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.biz.admin.AdminService;
import com.spring.biz.admin.AdminVO;
import com.spring.biz.admin.OrderService;
import com.spring.biz.admin.OrderVO;
import com.spring.biz.admin.impl.AdminDAO;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private OrderService orderService;
	
	// 가게 목록
	@RequestMapping("/index.do")
	public String getAdminList(AdminVO vo, AdminDAO adminDAO, Model model) {
		System.out.println("테스트 확인---------------------------------------------------");
		model.addAttribute("adminList", adminService.getAdminList(vo));
		return "index.jsp";
	}
	
	// 메뉴 목록
	@RequestMapping("/getMenuList.do")
	public String getMenuList(AdminVO vo, AdminDAO adminDAO, Model model) {
		System.out.println("메뉴 목록 출력");
		model.addAttribute("menuList", adminService.getMenuList(vo));
		return "getMenuList.jsp";
	}
	// 메뉴 등록
	@RequestMapping("/insertMenu.do")
	public String insertMenu(AdminVO vo) {
		adminService.insertMenu(vo);
		return "getMenuList.do";
	}
	// 메뉴 삭제
	@RequestMapping("/deleteMenu.do")
	public String deleteMenu(@RequestParam("seq") int seq ,AdminVO vo) {
		vo.setSeq(seq);
		adminService.deleteMenu(vo);
		return "getMenuList.do";
	}
	
	
	// 주문리스트 출력
	@RequestMapping("/order.do")
	public String getOrder_List(OrderVO vo, Model model) {
		List<OrderVO> list = new ArrayList();
		ObjectMapper mapper = new ObjectMapper();
					
		list = orderService.getOrder_List(vo); 
					
		try {
		// JSON 파싱을 통한 데이터 삽입
			HashMap<String, String> map = new HashMap<String, String>();
			for(int i = 0; i < list.size(); i++) {
							
				map = mapper.readValue(list.get(i).getFood1(), HashMap.class);
				vo.setFood1("메뉴:" + map.get("food")+ " 수량:"+ map.get("food_cnt")+ " 가격:"+map.get("food_price")+" 옵션:"+ map.get("food_opt"));
				list.get(i).setFood1(vo.getFood1());
				if (list.get(i).getFood2() != null) {
					map = mapper.readValue(list.get(i).getFood2(), HashMap.class);
					vo.setFood2("메뉴:" + map.get("food")+ " 수량:"+ map.get("food_cnt")+ " 가격:"+map.get("food_price")+" 옵션:"+ map.get("food_opt"));
					list.get(i).setFood2(vo.getFood2());
					System.out.println(list.get(i).getFood1());
					
					if(list.get(i).getFood3() != null) {
						map = mapper.readValue(list.get(i).getFood3(), HashMap.class);
						vo.setFood3("메뉴:" + map.get("food")+ " 수량:"+ map.get("food_cnt")+ " 가격:"+map.get("food_price")+" 옵션:"+ map.get("food_opt"));
						list.get(i).setFood3(vo.getFood3());
									
						if(list.get(i).getFood4() != null) {
							map = mapper.readValue(list.get(i).getFood4(), HashMap.class);
							vo.setFood4("메뉴:" + map.get("food")+ " 수량:"+ map.get("food_cnt")+ " 가격:"+map.get("food_price")+" 옵션:"+ map.get("food_opt"));
							list.get(i).setFood4(vo.getFood4());
							
							if(list.get(i).getFood5() != null) {
								map = mapper.readValue(list.get(i).getFood5(), HashMap.class);
								vo.setFood5("메뉴:" + map.get("food")+ " 수량:"+ map.get("food_cnt")+ " 가격:"+map.get("food_price")+" 옵션:"+ map.get("food_opt"));
								list.get(i).setFood5(vo.getFood5());
							}
						}
					}
				}
									
			}
						
			model.addAttribute("orderList", list);
						
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "order.jsp";
	}
				
	// 주문상태 처리 - 주문처리, 배달중, 배달완료, 주문취소
	@RequestMapping("/orderType.do")
	public String orderType(@RequestParam int seq, OrderVO vo) throws Exception {
		vo.setType(seq);
		orderService.orderType(vo);
		System.out.println(vo.toString());
		return "order.do";
	}
	
	
	


}
