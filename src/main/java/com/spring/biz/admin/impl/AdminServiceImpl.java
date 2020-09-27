package com.spring.biz.admin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.admin.AdminService;
import com.spring.biz.admin.AdminVO;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDAO adminDAO;
	
	

	public List<AdminVO> getAdminList(AdminVO vo){
		return adminDAO.getAdminList(vo);
	}

}
