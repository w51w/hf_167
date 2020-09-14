package com.spring.view;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.spring.biz.user.UserDTO;
import com.spring.biz.user.impl.UserDAO;

@Controller
@EnableWebMvc //Json�� Ŭ���̾�Ʈ�� �ȳѾ .. �õ� Spinrg 4��� @EnableWebMvc�� �߰��Ѵ� ... ����
public class UserController_Server {
	@RequestMapping(value="/server_login.do")
	@ResponseBody	//	�޼ҵ忡 @ResponseBody �� ������̼��� �Ǿ� �ִٸ� �޼ҵ忡�� ���ϵǴ� ���� View �� ���ؼ� 
					//	��µ��� �ʰ� HTTP Response Body �� ���� �������� �˴ϴ�.
	public UserDTO getUser(HttpServletRequest request) {
		
//		request.setCharacterEncoding("UTF-8");
		//web.xml�� ���� �߰� �ص� ���� ������ �ѱ� ���� .. ���� �𸣰���
		//�õ� server.xml �� ���� �߰� URIEncoding="UTF-8" ... ����
		//�õ� web.xml /*�� ���� ... ����
		//�õ� produces = "text/plain;charset=UTF=8 ... ����
		//�߰� web.xml <init> �±� �߸��� 
		//Ŭ���̾�Ʈ�� ���� �ڷᱸ�� Map
		UserDTO userDTO = new UserDTO();
		
		//Ŭ���̾�Ʈ�� ���� ���� �Ű�����
		String e_mail = request.getParameter("e_mail");
		String password = request.getParameter("password");
		
		//DB ����
		UserDTO vo = new UserDTO();
		vo.setE_mail(e_mail);
		vo.setPassword(password);
		
		UserDAO userDAO = new UserDAO();
		userDTO = userDAO.getUser(e_mail, password);
				
		return userDTO;
	}
}
