package com.spring.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.biz.admin.AdminService;
import com.spring.biz.admin.AdminVO;
import com.spring.biz.admin.impl.AdminDAO;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	// ���� ���
	@RequestMapping("/index.do")
	String getAdminList(AdminVO vo, AdminDAO adminDAO, Model model) {
		System.out.println("�׽�Ʈ Ȯ��---------------------------------------------------");
		model.addAttribute("adminList", adminService.getAdminList(vo));
		return "index.jsp";
	}
	
	// �޴� ���
	@RequestMapping("/menu.do")
	String getMenuList(AdminVO vo, AdminDAO adminDAO, Model model) {
		System.out.println("�޴� ��� ���");
		model.addAttribute("menuList", adminService.getMenuList(vo));
		return "menu.jsp";
	}
	
	
	


}
