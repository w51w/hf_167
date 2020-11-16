package com.spring.view.admin.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.biz.admin.user.AdminUserDTO;
import com.spring.biz.admin.user.AdminUserService;

@Controller
public class AdminUserController {

	@Autowired
	private AdminUserService adminUserService;

	@RequestMapping(value = "/adminLogin.do", method = RequestMethod.POST)
	public String login(AdminUserDTO vo, HttpSession session) {
		try {
			session.setAttribute("adminUser", adminUserService.getAdminUser(vo));
		} catch (EmptyResultDataAccessException e) {
			System.out.println("오류 메세지");
			return "adminLogin.jsp";
		}
		return "getAdminStore.do";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "adminLogin.jsp";
	}
}
