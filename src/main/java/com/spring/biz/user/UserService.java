package com.spring.biz.user;

import java.util.Map;

public interface UserService {

	Map<String, String> getUser_client(String e_mail, String password);
	
	public String insertUsert_clinet(UserDTO vo);

}