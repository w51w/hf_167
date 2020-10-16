package com.spring.biz.admin;

public class AdminVO {
	
	// STORE VO
	private String name;
	private int category_code;
	private String tel;
	private String location;
	private int delivery_price;
	private int least_price;
	private float rate;
	private String info;
	private String condition;
	
	// MENU VO
	private int seq;
	private String store_name;
	private int type;
	private String menubar;
	private String food;
	private int food_price;
	private String food_img;
	
	// getter and setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCategory_code() {
		return category_code;
	}
	public void setCategory_code(int category_code) {
		this.category_code = category_code;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getDelivery_price() {
		return delivery_price;
	}
	public void setDelivery_price(int delivery_price) {
		this.delivery_price = delivery_price;
	}
	public int getLeast_price() {
		return least_price;
	}
	public void setLeast_price(int least_price) {
		this.least_price = least_price;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
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
	
	
	
	// toString()
	@Override
	public String toString() {
		return "AdminVO [name=" + name + ", category_code=" + category_code + ", tel=" + tel + ", location=" + location
				+ ", delivery_price=" + delivery_price + ", least_price=" + least_price + ", rate=" + rate + ", info="
				+ info + ", condition=" + condition + ", seq=" + seq + ", store_name=" + store_name + ", type=" + type
				+ ", menubar=" + menubar + ", food=" + food + ", food_price=" + food_price + ", food_img=" + food_img
				+ "]";
	}
	
	
	
}
