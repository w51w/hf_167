package com.spring.biz.admin.store.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.admin.store.AdminStoreDTO;
import com.spring.biz.admin.store.AdminStoreMenuDTO;
import com.spring.biz.admin.store.AdminStoreService;

@Service("adminStoreService")
public class AdminStoreServiceImpl implements AdminStoreService{

	@Autowired
	private AdminStoreDAO adminStoreDAO;
	
	@Override
	public AdminStoreDTO getAdminStore(AdminStoreDTO vo) {
		return adminStoreDAO.getAdminStore(vo);
	}

	@Override
	public void updateStore(AdminStoreDTO vo) {
		adminStoreDAO.updateStore(vo);
		
	}

	@Override
	public List<AdminStoreMenuDTO> getMenuList(AdminStoreMenuDTO vo) {
		return adminStoreDAO.getMenuList(vo);
	}
	
	@Override
	public void insertMenu(AdminStoreMenuDTO vo) {
		adminStoreDAO.insertMenu(vo);
	}

	@Override
	public void deleteMenu(AdminStoreMenuDTO vo) {
		adminStoreDAO.deleteMenu(vo);
	}

	@Override
	public void updateCondition(AdminStoreDTO vo) {
		adminStoreDAO.updateCondition(vo);
	}

	
	
	

}
