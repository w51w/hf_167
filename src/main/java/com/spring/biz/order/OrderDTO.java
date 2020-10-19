package com.spring.biz.order;

import java.util.Date;

public class OrderDTO {
	private int seq;
    private int type;
    private String user_e_mail;
    private String store_name;
    private String address;
    private String address_detail;
    private double rate;
    private Date date_order;
    private String review;
    private String review_img;
    private Date date_review;
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
	public Date getDate_order() {
		return date_order;
	}
	public void setDate_order(Date date_order) {
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
	public Date getDate_review() {
		return date_review;
	}
	public void setDate_review(Date date_review) {
		this.date_review = date_review;
	}
	@Override
	public String toString() {
		return "OrderDTO [seq=" + seq + ", type=" + type + ", user_e_mail=" + user_e_mail + ", store_name=" + store_name
				+ ", address=" + address + ", address_detail=" + address_detail + ", rate=" + rate + ", date_order="
				+ date_order + ", review=" + review + ", review_img=" + review_img + ", date_review=" + date_review
				+ "]";
	}
    
}
