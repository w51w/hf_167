package com.spring.biz.store;

import java.util.ArrayList;

public class StoreMenuDTO {
	private int type;
	private String menubar;
	private String food;
	private int food_price;
	public ArrayList<StoreMenuDTO> child;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getMenubar() {
		return menubar;
	}
	public void setMenubar(String menubar) {
		this.menubar = menubar;
	}
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public int getFood_price() {
		return food_price;
	}
	public void setFood_price(int food_price) {
		this.food_price = food_price;
	}
	
	
}
