package com.spring.biz.user.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.user.UserDTO;
import com.spring.biz.user.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	public Map<String, String> getUser_client(String e_mail, String password) {
		return userDAO.getUser_client(e_mail, password);
	}

	@Override
	public String insertUsert_clinet(UserDTO vo) {
		return userDAO.insertUsert_clinet(vo);
	}

}
