package com.spring.biz.admin.order;

import java.util.List;

import com.spring.biz.admin.store.AdminStoreDTO;

public interface AdminOrderService {
	public String getPhone(String phone);
	
	public List<AdminOrderDTO> getOrder_List(AdminOrderDTO vo);
	
	public void typeUpdate(AdminOrderDTO vo);
	
	public List<AdminOrderDTO> getOrderLog_List(AdminOrderDTO vo);

	public List<AdminOrderDTO> sumRegular_list(AdminStoreDTO vo);
	
	public List<AdminOrderCntDTO> countRegular_list(AdminStoreDTO vo);
}
