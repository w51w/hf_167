package com.spring.biz.user;

import java.util.Map;

public interface UserService {

	Map<String, String> getUser_client(String e_mail, String password);
	
	String insertUser_clinet(UserDTO vo);
	
	String updateUser_client(String e_mail, String address);
}