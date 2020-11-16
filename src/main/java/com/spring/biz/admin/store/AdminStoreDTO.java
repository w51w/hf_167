package com.spring.biz.admin.store;

import org.springframework.web.multipart.MultipartFile;

public class AdminStoreDTO {
	private String name;
	private int category_code;
	private String tel;
	private String location;
	private int delivery_price;
	private String store_img;
	private int	least_price;
	private String info;
	private String condition;
	private int condition_value;
	private int condition_day;
	private MultipartFile uploadFile;
	
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
	public int getCondition_value() {
		return condition_value;
	}
	public void setCondition_value(int condition_value) {
		this.condition_value = condition_value;
	}	
	public int getCondition_day() {
		return condition_day;
	}
	public void setCondition_day(int condition_day) {
		this.condition_day = condition_day;
	}
	
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	@Override
	public String toString() {
		return "AdminStoreDTO [name=" + name + ", category_code=" + category_code + ", tel=" + tel + ", location="
				+ location + ", delivery_price=" + delivery_price + ", store_img=" + store_img + ", least_price="
				+ least_price + ", info=" + info + ", condition=" + condition + ", condition_value=" + condition_value
				+ ", condition_day=" + condition_day + ", uploadFile=" + uploadFile + "]";
	}
	
	
}
