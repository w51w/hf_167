package com.spring.biz.order;

public class OrderCartDTO {
	private int seq;
	private String e_mail;
	private String store_name;
	private String food;
	private int food_cnt;
	private int food_price;
	private String food_opt;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getFood() {
		return food;
	}
	public void setFood(String food) {
		this.food = food;
	}
	public int getFood_cnt() {
		return food_cnt;
	}
	public void setFood_cnt(int food_cnt) {
		this.food_cnt = food_cnt;
	}
	public int getFood_price() {
		return food_price;
	}
	public void setFood_price(int food_price) {
		this.food_price = food_price;
	}
	public String getFood_opt() {
		return food_opt;
	}
	public void setFood_opt(String food_opt) {
		this.food_opt = food_opt;
	}
	@Override
	public String toString() {
		return "OrderCartDTO [e_mail=" + e_mail + ", store_name=" + store_name + ", food=" + food + ", food_price="
				+ food_price + ", food_opt=" + food_opt + "]";
	}
	
	
}
