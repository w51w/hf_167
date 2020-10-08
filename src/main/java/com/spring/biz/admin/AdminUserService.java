package com.spring.biz.admin;

import org.springframework.stereotype.Service;


public interface AdminUserService {

	AdminUserVO getAdminUser(AdminUserVO vo);
	
	int getAdminUserCount(AdminUserVO vo);

}