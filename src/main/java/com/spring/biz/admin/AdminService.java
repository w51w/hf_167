package com.spring.biz.admin;

import java.util.List;

public interface AdminService {

	// ��� ����
	
	// ��������
	List<AdminVO> getAdminList(AdminVO vo);
	
	void updateAdmin(AdminVO vo);
	
	
	// �޴�����
	List<AdminVO> getMenuList(AdminVO vo);
	
	void insertMenu(AdminVO vo);
	
	void deleteMenu(AdminVO vo);
	
	

}