package com.spring.biz.admin;

public class AdminUserVO {
	
	private String id;
	private String pw;
	private String store_name;
	public String getId() {
		return id;
	}
	
	// get and setter
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	
	// toString()
	@Override
	public String toString() {
		return "AdminUserVO [id=" + id + ", pw=" + pw + ", store_name=" + store_name + "]";
	}
	
	
	

}
