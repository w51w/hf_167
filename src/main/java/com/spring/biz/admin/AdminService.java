package com.spring.biz.admin;

import java.util.List;

public interface AdminService {

	// 기능 구현
	List<AdminVO> getAdminList(AdminVO vo);

}