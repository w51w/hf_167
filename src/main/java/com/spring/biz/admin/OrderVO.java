package com.spring.biz.admin;

import java.sql.Date;

public class OrderVO {
	
	private int seq;
	private String address;
	private String address_detail;
	private String user_e_mail;
	private String store_name;
	private Date date_order;
	private int type;
	private String food1;
	private String food2;
	private String food3;
	private String food4;
	private String food5;
	
	// Getter and Setter
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress_detail() {
		return address_detail;
	}
	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}
	public String getUser_e_mail() {
		return user_e_mail;
	}
	public void setUser_e_mail(String user_e_mail) {
		this.user_e_mail = user_e_mail;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getFood1() {
		return food1;
	}
	public void setFood1(String food1) {
		this.food1 = food1;
	}
	public String getFood2() {
		return food2;
	}
	public void setFood2(String food2) {
		this.food2 = food2;
	}
	public String getFood3() {
		return food3;
	}
	public void setFood3(String food3) {
		this.food3 = food3;
	}
	public String getFood4() {
		return food4;
	}
	public void setFood4(String food4) {
		this.food4 = food4;
	}
	public String getFood5() {
		return food5;
	}
	public void setFood5(String food5) {
		this.food5 = food5;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public Date getDate_order() {
		return date_order;
	}
	public void setDate_order(Date date_order) {
		this.date_order = date_order;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	
	
	// toString
	@Override
	public String toString() {
		return "OrderVO [seq=" + seq + ", address=" + address + ", address_detail=" + address_detail + ", user_e_mail="
				+ user_e_mail + ", type=" + type + ", food1=" + food1 + ", food2=" + food2 + ", food3=" + food3
				+ ", food4=" + food4 + ", food5=" + food5 + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
