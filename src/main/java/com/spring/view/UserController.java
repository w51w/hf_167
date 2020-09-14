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
@EnableWebMvc //Json이 클라이언트로 안넘어감 .. 시도 Spinrg 4라면 @EnableWebMvc을 추가한다 ... 성공
public class UserController {
	@RequestMapping(value="/login.do")
	@ResponseBody	//	메소드에 @ResponseBody 로 어노테이션이 되어 있다면 메소드에서 리턴되는 값은 View 를 통해서 
					//	출력되지 않고 HTTP Response Body 에 직접 쓰여지게 됩니다.
	public Map<String, String> getUser(HttpServletRequest request) {
		
//		request.setCharacterEncoding("UTF-8");
		//web.xml에 필터 추가 해도 위에 없으면 한글 깨짐 .. 이유 모르겠음
		//시도 server.xml 에 구문 추가 URIEncoding="UTF-8" ... 실패
		//시도 web.xml /*로 변경 ... 실패
		//시도 produces = "text/plain;charset=UTF=8 ... 실패
		//발견 web.xml <init> 태그 잘못씀 
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
