package com.spring.biz.regular;

import java.util.List;

public interface RegularService {
	public List<ConditionDTO> getStoreList(String user_e_mail);
	
	public int getCnt(int day, String store_name, String user_e_mail);
	
	public List<OrderDetailDTO> getSum (int day, String store_name, String user_e_mail);
}
