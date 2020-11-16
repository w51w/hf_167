package com.spring.biz.admin.order;

import java.util.List;

public interface AdminOrderService {
	public String getPhone(String phone);
	
	public List<AdminOrderDTO> getOrder_List(AdminOrderDTO vo);
	
	public void typeUpdate(AdminOrderDTO vo);
	
	public List<AdminOrderDTO> getOrderLog_List(AdminOrderDTO vo);
}
