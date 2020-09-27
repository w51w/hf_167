package com.spring.view;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.spring.biz.user.UserDTO;
import com.spring.biz.user.UserService;
import com.spring.biz.user.impl.UserDAO;

@Controller
public class UserController_Client {
	@Autowired //UserService Ÿ���� UserServiceImpl ��ü�� ������ ���Եȴ�.
	private UserService userService;
	
	@RequestMapping(value="/login.do")
	@ResponseBody	//	�޼ҵ忡 @ResponseBody �� ������̼��� �Ǿ� �ִٸ� �޼ҵ忡�� ���ϵǴ� ���� View �� ���ؼ� ��µ��� �ʰ� HTTP Response Body �� ���� �������� �˴ϴ�.
	public Map<String, String> login(HttpServletRequest request) {
		//Ŭ���̾�Ʈ�� ���� �ڷᱸ�� Map
		Map<String, String> result = new HashMap<String, String>();
		
		//Ŭ���̾�Ʈ��(�Ű������� &�� �̿��� get��������� Post������� ����) ���� ���� �Ű�����
		String e_mail = request.getParameter("e_mail");
		String password = request.getParameter("password");
		
		//DB ���� (Ŭ���̾�Ʈ�� ����� ���� String�̴� ������ Ÿ�Ժ�ȯ���ֱ� �ٶ�)
		UserDTO vo = new UserDTO();
		vo.setE_mail(e_mail);
		vo.setPassword(password);
		
		result = userService.getUser_client(e_mail, password);
				
		return result;
	}
	
	@RequestMapping(value = "/user_register.do")
	@ResponseBody
	public String register(HttpServletRequest request) {
		
		String e_mail = request.getParameter("e_mail");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		
		UserDTO vo = new UserDTO();
		vo.setE_mail(e_mail);
		vo.setPassword(password);
		vo.setName(name);
		vo.setPhone(phone);
		vo.setSex(sex);
		vo.setAge(Integer.parseInt(age));
		
		String result = userService.insertUsert_clinet(vo);
		
		return result;
	}
}
