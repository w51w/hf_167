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
	
	// ���� ���
	@RequestMapping("/index.do")
	public String getAdminList(AdminVO vo, AdminDAO adminDAO, Model model) {
		System.out.println("�׽�Ʈ Ȯ��---------------------------------------------------");
		model.addAttribute("adminList", adminService.getAdminList(vo));
		return "index.jsp";
	}
	
	// �޴� ���
	@RequestMapping("/getMenuList.do")
	public String getMenuList(AdminVO vo, AdminDAO adminDAO, Model model) {
		System.out.println("�޴� ��� ���");
		model.addAttribute("menuList", adminService.getMenuList(vo));
		return "getMenuList.jsp";
	}
	// �޴� ���
	@RequestMapping("/insertMenu.do")
	public String insertMenu(AdminVO vo) {
		adminService.insertMenu(vo);
		return "getMenuList.do";
	}
	// �޴� ����
	@RequestMapping("/deleteMenu.do")
	public String deleteMenu(@RequestParam("seq") int seq ,AdminVO vo) {
		vo.setSeq(seq);
		adminService.deleteMenu(vo);
		return "getMenuList.do";
	}
	
	
	// �ֹ�����Ʈ ���
	@RequestMapping("/order.do")
	public String getOrder_List(OrderVO vo, Model model) {
		List<OrderVO> list = new ArrayList();
		ObjectMapper mapper = new ObjectMapper();
					
		list = orderService.getOrder_List(vo); 
					
		try {
		// JSON �Ľ��� ���� ������ ����
			HashMap<String, String> map = new HashMap<String, String>();
			for(int i = 0; i < list.size(); i++) {
							
				map = mapper.readValue(list.get(i).getFood1(), HashMap.class);
				vo.setFood1("�޴�:" + map.get("food")+ " ����:"+ map.get("food_cnt")+ " ����:"+map.get("food_price")+" �ɼ�:"+ map.get("food_opt"));
				list.get(i).setFood1(vo.getFood1());
				if (list.get(i).getFood2() != null) {
					map = mapper.readValue(list.get(i).getFood2(), HashMap.class);
					vo.setFood2("�޴�:" + map.get("food")+ " ����:"+ map.get("food_cnt")+ " ����:"+map.get("food_price")+" �ɼ�:"+ map.get("food_opt"));
					list.get(i).setFood2(vo.getFood2());
					System.out.println(list.get(i).getFood1());
					
					if(list.get(i).getFood3() != null) {
						map = mapper.readValue(list.get(i).getFood3(), HashMap.class);
						vo.setFood3("�޴�:" + map.get("food")+ " ����:"+ map.get("food_cnt")+ " ����:"+map.get("food_price")+" �ɼ�:"+ map.get("food_opt"));
						list.get(i).setFood3(vo.getFood3());
									
						if(list.get(i).getFood4() != null) {
							map = mapper.readValue(list.get(i).getFood4(), HashMap.class);
							vo.setFood4("�޴�:" + map.get("food")+ " ����:"+ map.get("food_cnt")+ " ����:"+map.get("food_price")+" �ɼ�:"+ map.get("food_opt"));
							list.get(i).setFood4(vo.getFood4());
							
							if(list.get(i).getFood5() != null) {
								map = mapper.readValue(list.get(i).getFood5(), HashMap.class);
								vo.setFood5("�޴�:" + map.get("food")+ " ����:"+ map.get("food_cnt")+ " ����:"+map.get("food_price")+" �ɼ�:"+ map.get("food_opt"));
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
				
	// �ֹ����� ó�� - �ֹ�ó��, �����, ��޿Ϸ�, �ֹ����
	@RequestMapping("/orderType.do")
	public String orderType(@RequestParam int seq, OrderVO vo) throws Exception {
		vo.setType(seq);
		orderService.orderType(vo);
		System.out.println(vo.toString());
		return "order.do";
	}
	
	
	


}
