package com.spring.biz.admin.order.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.admin.order.AdminOrderCntDTO;
import com.spring.biz.admin.order.AdminOrderDTO;
import com.spring.biz.admin.order.AdminOrderService;
import com.spring.biz.admin.store.AdminStoreDTO;

@Service("adminOrderService")
public class AdminOrderServiceImpl implements AdminOrderService{

	@Autowired
	private AdminOrderDAO adminOrderDAO;
	
	@Override
	public String getPhone(String phone) {
		return adminOrderDAO.getPhone(phone);
	}
	
	@Override
	public List<AdminOrderDTO> getOrder_List(AdminOrderDTO vo) {
		return adminOrderDAO.getOrder_List(vo);
	}

	@Override
	public void typeUpdate(AdminOrderDTO vo) {
		adminOrderDAO.typeUpdate(vo);
	}

	@Override
	public List<AdminOrderDTO> getOrderLog_List(AdminOrderDTO vo) {
		return adminOrderDAO.getOrderLog_List(vo);
	}

	@Override
	public List<AdminOrderDTO> sumRegular_list(AdminStoreDTO vo) {
		return adminOrderDAO.sumRegular_list(vo);
	}

	@Override
	public List<AdminOrderCntDTO> countRegular_list(AdminStoreDTO vo) {
		return adminOrderDAO.countRegular_list(vo);
	}


}
