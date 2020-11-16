package com.spring.biz.admin.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.admin.user.AdminUserDTO;
import com.spring.biz.admin.user.AdminUserService;

@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService{
	
	@Autowired
	private AdminUserDAO adminUserDAO;

	@Override
	public AdminUserDTO getAdminUser(AdminUserDTO vo) {
		return adminUserDAO.getAdminUser(vo);
	}

}
