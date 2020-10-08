package com.spring.view;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.admin.AdminUserService;
import com.spring.biz.admin.AdminUserVO;
import com.spring.biz.admin.impl.AdminUserDAO;

@Controller
public class AdminUserController {
	
	@Autowired
	private AdminUserService adminUserService;
	
	@RequestMapping("/adminLogin.do")
	public String getAdminUser(AdminUserVO vo, AdminUserDAO adminUserDAO, HttpSession session) {
		System.out.println("로그인 테스트");
		try {
			session.setAttribute("adminUser", adminUserService.getAdminUser(vo));
		} catch(EmptyResultDataAccessException e) {
			System.out.println("오류 메세지");
			return "adminLogin.jsp";
		}
		
		
		return "index.do";
	}

}
