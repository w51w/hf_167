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
	public String deleteMenu(AdminVO vo) {
		adminService.deleteMenu(vo);
		return "getMenuList.do";
	}
	
	
	


}
