package com.spring.biz.admin;

public class AdminVO {
	
	private String name;
	private int category_code;
	private String tel;
	private String location;
	private int delivery_price;
	private int least_price;
	private float rate;
	private String info;
	private String condition;
	
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
	
	
	// toString()
	@Override
	public String toString() {
		return "AdminVO [name=" + name + ", category_code=" + category_code + ", tel=" + tel + ", location=" + location
				+ ", delivery_price=" + delivery_price + ", least_price=" + least_price + ", info=" + info
				+ ", condition=" + condition + "]";
	}
	
	
}
