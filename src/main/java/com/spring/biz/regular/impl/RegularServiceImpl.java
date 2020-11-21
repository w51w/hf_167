package com.spring.biz.regular.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.biz.regular.ConditionDTO;
import com.spring.biz.regular.OrderDetailDTO;
import com.spring.biz.regular.RegularService;

@Service("RegularService")
public class RegularServiceImpl implements RegularService{
	@Autowired
	private RegularDAO regularDAO;

	@Override
	public List<ConditionDTO> getStoreList(String user_e_mail) {
		return regularDAO.getStoreList(user_e_mail);
	}

	@Override
	public int getCnt(int day, String store_name, String user_e_mail) {
		return  regularDAO.getCnt(day, store_name, user_e_mail);
	}

	@Override
	public List<OrderDetailDTO> getSum(int day, String store_name, String user_e_mail) {
		return regularDAO.getSum(day, store_name, user_e_mail);
	}
}
