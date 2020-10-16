package com.spring.biz.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.admin.AdminUserService;
import com.spring.biz.admin.AdminUserVO;


@Service("adminUserService")
public class AdminUserServiceimpl implements AdminUserService{
	
	@Autowired
	private AdminUserDAO adminUserDAO;

	public AdminUserVO getAdminUser(AdminUserVO vo) {
		return adminUserDAO.getAdminUser(vo);
	}
	
	public int getAdminUserCount(AdminUserVO vo) {
		return adminUserDAO.getAdminUserCount(vo);
	}
	
	

}
