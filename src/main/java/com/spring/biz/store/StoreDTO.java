package com.spring.biz.store;

public class StoreDTO {
	private String name;
	private int category_name;
	private String tel;
	private String location;
	private int delivery_price;
	private String store_img;
	private int	least_price;
	private double rate;
	private String info;
	private String condition;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCategory_name() {
		return category_name;
	}
	public void setCategory_name(int category_name) {
		this.category_name = category_name;
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
	public String getStore_img() {
		return store_img;
	}
	public void setStore_img(String store_img) {
		this.store_img = store_img;
	}
	public int getLeast_price() {
		return least_price;
	}
	public void setLeast_price(int least_price) {
		this.least_price = least_price;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
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
	
	
}
