package com.spring.biz.admin.order;

public class AdminOrderCntDTO {
	private String user_e_mail;
	private int cnt;

	public String getUser_e_mail() {
		return user_e_mail;
	}
	public void setUser_e_mail(String user_e_mail) {
		this.user_e_mail = user_e_mail;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "Cnt [user_e_mail=" + user_e_mail + ", cnt=" + cnt + "]";
	}

}
