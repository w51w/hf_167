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
public class UserController_Client {
	@RequestMapping(value="/client_login.do")
	@ResponseBody	//	�޼ҵ忡 @ResponseBody �� ������̼��� �Ǿ� �ִٸ� �޼ҵ忡�� ���ϵǴ� ���� View �� ���ؼ� 
					//	��µ��� �ʰ� HTTP Response Body �� ���� �������� �˴ϴ�.
	public Map<String, String> getUser(HttpServletRequest request) {
		//Ŭ���̾�Ʈ�� ���� �ڷᱸ�� Map
		Map<String, String> result = new HashMap<String, String>();
		
		//Ŭ���̾�Ʈ�� ���� ���� �Ű�����
		String e_mail = request.getParameter("e_mail");
		String password = request.getParameter("password");
		
		//DB ����
		UserDTO vo = new UserDTO();
		vo.setE_mail(e_mail);
		vo.setPassword(password);
		
		UserDAO userDAO = new UserDAO();
		result = userDAO.getUser_Map(e_mail, password);
				
		return result;
	}
}
