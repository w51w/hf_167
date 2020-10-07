package com.spring.biz.store;

import java.util.ArrayList;

public class StoreMenuDTO {
	private String store_name;
	private int type;
	private String menubar;
	private String food;
	private int food_price;
	private String food_img;
	private String food_opt;
	public ArrayList<StoreMenuDTO> child;
	
	
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
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
	public String getFood_img() {
		return food_img;
	}
	public void setFood_img(String food_img) {
		this.food_img = food_img;
	}
	public String getFood_opt() {
		return food_opt;
	}
	public void setFood_opt(String food_opt) {
		this.food_opt = food_opt;
	}
	@Override
	public String toString() {
		return "StoreMenuDTO [type=" + type + ", menubar=" + menubar + ", food=" + food + ", food_price=" + food_price
				+ ", food_img=" + food_img + ", child=" + child + "]";
	}
	
}
