package com.spring.biz.order;

public interface OrderService {
	public OrderCartListDTO getOrderCartList_client(String e_mail);
	
	public String insertOrderCart_client(OrderCartDTO vo);
	
	public String delete_cart_selected(int seq);
	
	public String maxCheck(String e_mail, String store_name);
}
