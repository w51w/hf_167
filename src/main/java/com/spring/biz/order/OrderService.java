package com.spring.biz.order;

public interface OrderService {
	public OrderCartListDTO getOrderCartList_client(String e_mail);
	
	public String insert_OrderCart_client(OrderCartDTO vo);
	
	public void delete_cart(String e_mail);
	
	public String delete_cart_selected(int seq);
	
	public String maxCheck(String e_mail, String store_name);
	
	//order
	public int insert_order(OrderDTO vo);
	
	public void insert_OrderDetail(OrderDetailDTO vo);
	
	public OrderListDTO getOrderList(String e_mail);
	
	public OrderDetailDTO getOrderDetail(int order_seq);
}
