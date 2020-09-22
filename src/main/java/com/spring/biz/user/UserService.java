package com.spring.biz.user;

import java.util.Map;

public interface UserService {

	Map<String, String> geUser_client(String e_mail, String password);

}