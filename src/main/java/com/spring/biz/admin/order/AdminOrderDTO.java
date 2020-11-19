package com.spring.biz.admin.order;

public class AdminOrderDTO {
	private String phone;
	
	private int seq;
    private int type;
    private String user_e_mail;
    private String store_name;
    private String address;
    private String address_detail;
    private double rate;
    private String date_order;
    private String review;
    private String review_img;
    private String date_review;
	
    private String food1;
    private String food2;
    private String food3;
    private String food4;
    private String food5;
      	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	public int getSeq() {
		return seq;
	}	
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUser_e_mail() {
		return user_e_mail;
	}
	public void setUser_e_mail(String user_e_mail) {
		this.user_e_mail = user_e_mail;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
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
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getDate_order() {
		return date_order;
	}
	public void setDate_order(String date_order) {
		this.date_order = date_order;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getReview_img() {
		return review_img;
	}
	public void setReview_img(String review_img) {
		this.review_img = review_img;
	}
	public String getDate_review() {
		return date_review;
	}
	public void setDate_review(String date_review) {
		this.date_review = date_review;
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
	@Override
	public String toString() {
		return "\nAdminOrderDTO [phone=" + phone + ", seq=" + seq + ", type=" + type + ", user_e_mail=" + user_e_mail
				+ ", store_name=" + store_name + ", address=" + address + ", address_detail=" + address_detail
				+ ", rate=" + rate + ", date_order=" + date_order + ", review=" + review + ", review_img=" + review_img
				+ ", date_review=" + date_review + ", food1=" + food1 + ", food2=" + food2 + ", food3=" + food3
				+ ", food4=" + food4 + ", food5=" + food5 + "]";
	}  
}
