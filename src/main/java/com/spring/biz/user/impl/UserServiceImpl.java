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
	public String insertUser_clinet(UserDTO vo) {
		return userDAO.insertUser_clinet(vo);
	}

	@Override
	public String updateUser_client(String isDetail, String e_mail, String address, String address_detail) {
		return userDAO.updateUser_client(isDetail, e_mail, address, address_detail);
	}

}
