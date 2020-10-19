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
	

	public AdminVO getAdminList(AdminVO vo){
		return adminDAO.getAdminList(vo);
	}

	public void updateAdmin(AdminVO vo) {
		adminDAO.updateAdmin(vo);
	}


	public List<AdminVO> getMenuList(AdminVO vo) {
		return adminDAO.getMenuList(vo);
	}


	public void insertMenu(AdminVO vo) {
		adminDAO.insertMenu(vo);
		
	}
	
	public void deleteMenu(AdminVO vo) {
		adminDAO.deleteMenu(vo);
		
	}
	
}
