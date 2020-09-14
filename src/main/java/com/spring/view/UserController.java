package com.spring.view;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.spring.biz.user.impl.UserDAO;

@Controller
@EnableWebMvc //Json�� Ŭ���̾�Ʈ�� �ȳѾ .. �õ� Spinrg 4��� @EnableWebMvc�� �߰��Ѵ� ... ����
public class UserController {
	@RequestMapping(value="/login.do")
	@ResponseBody	//	�޼ҵ忡 @ResponseBody �� ������̼��� �Ǿ� �ִٸ� �޼ҵ忡�� ���ϵǴ� ���� View �� ���ؼ� 
					//	��µ��� �ʰ� HTTP Response Body �� ���� �������� �˴ϴ�.
	public Map<String, String> getUser(HttpServletRequest request) {
		
//		request.setCharacterEncoding("UTF-8");
		//web.xml�� ���� �߰� �ص� ���� ������ �ѱ� ���� .. ���� �𸣰���
		//�õ� server.xml �� ���� �߰� URIEncoding="UTF-8" ... ����
		//�õ� web.xml /*�� ���� ... ����
		//�õ� produces = "text/plain;charset=UTF=8 ... ����
		//�߰� web.xml <init> �±� �߸��� 
		Map<String, String> result = new HashMap<String, String>();
		
		String e_mail = request.getParameter("e_mail");
		String password = request.getParameter("password");
		
		UserDAO userDAO = new UserDAO();
		String login =userDAO.getUser(e_mail, password);
		
		result.put("e_mail", e_mail);
		result.put("session", "true");
		
		System.out.println(login);
		System.out.println(e_mail);
		System.out.println(password);
		
		return result;
	}
}
