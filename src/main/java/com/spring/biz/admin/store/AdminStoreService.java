package com.spring.biz.admin.store;

import java.util.List;

public interface AdminStoreService {
	public AdminStoreDTO getAdminStore(AdminStoreDTO vo);
	
	public void updateStore(AdminStoreDTO vo);
	
	public List<AdminStoreMenuDTO> getMenuList(AdminStoreMenuDTO vo);
	
	public void insertMenu(AdminStoreMenuDTO vo);
	
	public void deleteMenu(AdminStoreMenuDTO vo);
	
	public void updateCondition(AdminStoreDTO vo);
}
