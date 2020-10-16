package com.spring.biz.order;

import java.util.ArrayList;

public class OrderCartListDTO {
	private ArrayList<OrderCartDTO> cartList;

	public ArrayList<OrderCartDTO> getCartList() {
		return cartList;
	}

	public void setCartList(ArrayList<OrderCartDTO> cartList) {
		this.cartList = cartList;
	}

	@Override
	public String toString() {
		return "OrderCartListDTO [cartList=" + cartList + "]";
	}
	
	
}
