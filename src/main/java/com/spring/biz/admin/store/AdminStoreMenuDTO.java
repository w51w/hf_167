package com.spring.biz.admin.store;

import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public class AdminStoreMenuDTO {
	private int seq;
	private String store_name;
	private int type;
	private String menubar;
	private String food;
	private int food_price;
	private String food_img;
	private String food_opt;
	private MultipartFile uploadFile;
	
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
	public String getFood_opt() {
		return food_opt;
	}
	public void setFood_opt(String food_opt) {
		this.food_opt = food_opt;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	@Override
	public String toString() {
		return "AdminStoreMenuDTO [seq=" + seq + ", store_name=" + store_name + ", type=" + type + ", menubar="
				+ menubar + ", food=" + food + ", food_price=" + food_price + ", food_img=" + food_img + ", food_opt="
				+ food_opt + ", uploadFile=" + uploadFile + "]";
	}

	
	
}
