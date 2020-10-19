package com.spring.biz.admin;

import java.util.List;

import org.springframework.stereotype.Service;


public interface AdminService {

	// 기능 구현
	
	// 점포정보
	AdminVO getAdminList(AdminVO vo);
	
	void updateAdmin(AdminVO vo);
	
	
	// 메뉴정보
	List<AdminVO> getMenuList(AdminVO vo);
	
	void insertMenu(AdminVO vo);
	
	void deleteMenu(AdminVO vo);
	
	

}