package com.spring.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.biz.admin.AdminService;
import com.spring.biz.admin.AdminUserVO;
import com.spring.biz.admin.AdminVO;
import com.spring.biz.admin.OrderService;
import com.spring.biz.admin.OrderVO;
import com.spring.biz.admin.impl.AdminDAO;

@Controller
@SessionAttributes("adminList")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private OrderService orderService;
	
	// 가게 목록	
	@RequestMapping("/index.do")
	public String getAdminList(HttpSession session, AdminVO vo, Model model) {
		AdminUserVO userVO = (AdminUserVO)session.getAttribute("adminUser");
		vo.setName(userVO.getStore_name());
		AdminVO list = new AdminVO();
		list = adminService.getAdminList(vo);
		list.setStore_img("http://192.168.43.242:8080/biz/" + list.getStore_img());
		System.out.println(list.getStore_img());
		model.addAttribute("adminList", list);
		return "index.jsp";
	}
	
	// 가게 정보수정
	@RequestMapping("/admin_update.do")
	public String updateAdmin (AdminVO vo, Model model) {
		adminService.updateAdmin(vo);
		return "index.do";
	}
	
	// 메뉴 목록
	@RequestMapping("/getMenuList.do")
	public String getMenuList(@ModelAttribute("adminList") AdminVO vo, Model model) {
		System.out.println("메뉴 목록 출력");
		List<AdminVO> list = new ArrayList<AdminVO>();
		list = adminService.getMenuList(vo);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFood_img("http://192.168.219.101:8080/biz/" + list.get(i).getFood_img());
		}
		System.out.println(list);
		model.addAttribute("menuList", list);
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
	public String deleteMenu(@ModelAttribute("adminList") AdminVO vo, @RequestParam("seq") int seq) {
		System.out.println(vo.toString());
		vo.setSeq(seq);
		System.out.println("delete 실행");
		adminService.deleteMenu(vo);
		return "getMenuList.do";
	}
	
	
	// 주문리스트 출력
	@RequestMapping("/order.do")
	public String getOrder_List(@ModelAttribute("adminList") AdminVO adminvo, OrderVO vo, Model model) {
		List<OrderVO> list = new ArrayList();
		ObjectMapper mapper = new ObjectMapper();
		vo.setStore_name(adminvo.getName());
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
	
	// 주문로그 출력
	@RequestMapping("/orderLog.do")
	public String orderLog(@ModelAttribute("adminList") AdminVO adminvo, OrderVO vo, Model model) {
		List<OrderVO> list = new ArrayList();
		ObjectMapper mapper = new ObjectMapper();
		
		vo.setStore_name(adminvo.getName());
		list = orderService.getOrder_Log(vo);
		
		try {
		// JSON 파싱을 통한 데이터 삽입
			HashMap<String, String> map = new HashMap<String, String>();
			for(int i = 0; i < list.size(); i++) {
							
				map = mapper.readValue(list.get(i).getFood1(), HashMap.class);
				if(list.get(i).getFood1() != null) {
					vo.setFood1("메뉴:" + map.get("food")+ " 수량:"+ map.get("food_cnt")+ " 가격:"+map.get("food_price")+" 옵션:"+ map.get("food_opt"));
					list.get(i).setFood1(vo.getFood1());	
				}
				else if(list.get(i).getFood1() == null) {
					return "orderLog.jsp";
				}
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
						
			model.addAttribute("orderLog", list);
						
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "orderLog.jsp";
	}
	
	// 제일 마지막
	// 주문상태 처리 - 주문처리, 배달중, 배달완료, 주문취소
	@RequestMapping("/orderProcess.do")
	public String orderProcess(@RequestParam int seq, @RequestParam int type , OrderVO vo) throws Exception {
		vo.setSeq(seq);
		vo.setType(type);
		orderService.orderProcess(vo);
		System.out.println(vo.toString());
		return "order.do";
	}
	
	@RequestMapping("/orderDelivery.do")
	public String orderDelivery(@RequestParam int seq, OrderVO vo) {
		vo.setSeq(seq);
		System.out.println(vo.toString());
		orderService.orderDelivery(vo);
		return "order.do";
	}
	
	@RequestMapping("/orderEnd.do")
	public String orderEnd(@RequestParam int seq, OrderVO vo) {
		vo.setSeq(seq);
		orderService.orderEnd(vo);
		return "order.do";
	}
	
	@RequestMapping("/orderCancel.do")
	public String orderCancel(@RequestParam int seq, OrderVO vo) {
		vo.setSeq(seq);
		orderService.orderCancel(vo);
		return "order.do";
	}
	
	
	


}
