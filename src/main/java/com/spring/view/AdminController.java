package com.spring.view;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
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
	
	// ���� ���	
	@RequestMapping("/index.do")
	public String getAdminList(HttpSession session, AdminVO vo, Model model) {
		AdminUserVO userVO = (AdminUserVO)session.getAttribute("adminUser");
		vo.setName(userVO.getStore_name());
		AdminVO list = new AdminVO();
		list = adminService.getAdminList(vo);
		list.setStore_img("http://172.16.52.54:8080/biz/" + list.getStore_img());
		System.out.println(list.getStore_img());
		model.addAttribute("adminList", list);
		return "index.jsp";
	}
	
	// ���� ��������
	@RequestMapping("/admin_update.do")
	public String updateAdmin (@ModelAttribute("adminList") AdminVO vo) throws IOException{
		
		// ���� ���
		MultipartFile uploadFile = vo.getStore_uploadFile();
		String uploadDir = this.getClass().getResource("").getPath(); 
		uploadDir = uploadDir.substring(1, uploadDir.indexOf(".metadata")) +
				"hf_167/src/main/webapp/resource/" + vo.getName();
		System.out.println(uploadDir);
		
		//���丮 ����
		File Folder = new File(uploadDir);
		if(!Folder.exists()) { Folder.mkdir(); }
		File subFolder = new File(Folder.getAbsolutePath() + "/store_img" );	
		if(!subFolder.exists()) { subFolder.mkdir(); }
		
		// ���� �Է�
		String fileName = uploadFile.getOriginalFilename();
		if (!uploadFile.isEmpty()) {
			uploadFile.transferTo(new File(uploadDir + "/store_img/" + fileName));
		}
		
		vo.setStore_img(vo.getName() + "/store_img/" + fileName);
		
		adminService.updateAdmin(vo);
		return "index.do";
	}
	
	// �޴� ���
	@RequestMapping("/getMenuList.do")
	public String getMenuList(@ModelAttribute("adminList") AdminVO vo, Model model) {
		System.out.println("�޴� ��� ���");
		List<AdminVO> list = new ArrayList<AdminVO>();
		list = adminService.getMenuList(vo);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setFood_img("http://172.16.52.54:8080/biz/" + list.get(i).getFood_img());
		}
		System.out.println(list);
		model.addAttribute("menuList", list);
		return "getMenuList.jsp";
	}
	
	// �޴� ���
	@RequestMapping("/insertMenu.do")
	public String insertMenu(@ModelAttribute("adminList") AdminVO vo) throws IOException{
		
		
		MultipartFile uploadFile = vo.getMenu_uploadFile();
		System.out.println(uploadFile.getOriginalFilename());
		String uploadDir = this.getClass().getResource("").getPath(); 
		uploadDir = uploadDir.substring(1, uploadDir.indexOf(".metadata")) +
				"hf_167/src/main/webapp/resource/" + vo.getName();
		
		//���丮 ����
		File Folder = new File(uploadDir);
		if(!Folder.exists()) { Folder.mkdir(); }
		File subFolder = new File(Folder.getAbsolutePath() + "/order_img" );	
		if(!subFolder.exists()) { subFolder.mkdir(); }
		
		// ���� �Է�
		String fileName = uploadFile.getOriginalFilename();
		if (!uploadFile.isEmpty()) {
			uploadFile.transferTo(new File(uploadDir + "/order_img/" + fileName));
		}
		vo.setFood_img(vo.getName() + "/order_img/" + fileName);
		
		adminService.insertMenu(vo);
		return "getMenuList.do";
	}
	// �޴� ����
	@RequestMapping("/deleteMenu.do")
	public String deleteMenu(@ModelAttribute("adminList") AdminVO vo, @RequestParam("seq") int seq) {
		System.out.println(vo.toString());
		vo.setSeq(seq);
		System.out.println("delete ����");
		adminService.deleteMenu(vo);
		return "getMenuList.do";
	}
	
	
	// �ֹ�����Ʈ ���
	@RequestMapping("/orderList.do")
	public String getOrder_List(@ModelAttribute("adminList") AdminVO adminvo, OrderVO vo, Model model) {
		List<OrderVO> list = new ArrayList();
		ObjectMapper mapper = new ObjectMapper();
		vo.setStore_name(adminvo.getName());
		list = orderService.getOrder_List(vo); 
		model.addAttribute("orderList", list);
		
		return "order.jsp";
	}
	
	// �ֹ��α� ���
	@RequestMapping("/orderLog.do")
	public String orderLog(@ModelAttribute("adminList") AdminVO adminvo, OrderVO vo, Model model) {
		List<OrderVO> list = new ArrayList();
		ObjectMapper mapper = new ObjectMapper();
		
		vo.setStore_name(adminvo.getName());
		list = orderService.getOrder_Log(vo);
		
		try {
		// JSON �Ľ��� ���� ������ ����
			HashMap<String, String> map = new HashMap<String, String>();
			for(int i = 0; i < list.size(); i++) {
							
				map = mapper.readValue(list.get(i).getFood1(), HashMap.class);
				if(list.get(i).getFood1() != null) {
					vo.setFood1("�޴�:" + map.get("food1")+ " ����:"+ map.get("food1_cnt")+ " ����:"+map.get("food1_price")+" �ɼ�:"+ map.get("opt1"));
					list.get(i).setFood1(vo.getFood1());	
				}
				else if(list.get(i).getFood1() == null) {
					return "orderLog.jsp";
				}
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
						
			model.addAttribute("orderLog", list);
						
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "orderLog.jsp";
	}
	
	// ���� ������
	// �ֹ����� ó�� - �ֹ�ó��, �����, ��޿Ϸ�, �ֹ����
	@RequestMapping("/orderProcess.do")
	public String orderProcess(@RequestParam int seq, @RequestParam int type , OrderVO vo) throws Exception {
		vo.setSeq(seq);
		vo.setType(type);
		orderService.orderProcess(vo);
		System.out.println(vo.toString());
		return "orderList.do";
	}
	
	@RequestMapping("/orderDelivery.do")
	public String orderDelivery(@RequestParam int seq, OrderVO vo) {
		vo.setSeq(seq);
		System.out.println(vo.toString());
		orderService.orderDelivery(vo);
		return "orderList.do";
	}
	
	@RequestMapping("/orderEnd.do")
	public String orderEnd(@RequestParam int seq, OrderVO vo) {
		vo.setSeq(seq);
		orderService.orderEnd(vo);
		return "orderList.do";
	}
	
	@RequestMapping("/orderCancel.do")
	public String orderCancel(@RequestParam int seq, OrderVO vo) {
		vo.setSeq(seq);
		orderService.orderCancel(vo);
		return "orderList.do";
	}
	
	
	


}
