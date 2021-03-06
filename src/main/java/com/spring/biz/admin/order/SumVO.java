package com.spring.biz.admin.order;

public class SumVO implements Comparable<SumVO>{
	private String user_e_mail;
	private int sum;
	
	public String getUser_e_mail() {
		return user_e_mail;
	}
	public void setUser_e_mail(String user_e_mail) {
		this.user_e_mail = user_e_mail;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	@Override
	public int compareTo(SumVO o) {
		return user_e_mail.compareTo(o.user_e_mail);
	}

}
