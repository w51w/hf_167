package com.spring.biz.admin;

import java.util.List;

import org.springframework.stereotype.Service;


public interface AdminService {

	// ��� ����
	
	// ��������
	AdminVO getAdminList(AdminVO vo);
	
	void updateAdmin(AdminVO vo);
	
	
	// �޴�����
	List<AdminVO> getMenuList(AdminVO vo);
	
	void insertMenu(AdminVO vo);
	
	void deleteMenu(AdminVO vo);
	
	

}