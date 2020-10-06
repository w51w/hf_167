package com.spring.biz.admin;

import java.util.List;

public interface AdminService {

	// 기능 구현
	
	// 점포정보
	List<AdminVO> getAdminList(AdminVO vo);
	
	void updateAdmin(AdminVO vo);
	
	
	// 메뉴정보
	List<AdminVO> getMenuList(AdminVO vo);
	
	void insertMenu(AdminVO vo);
	
	void deleteMenu(AdminVO vo);
	
	

}