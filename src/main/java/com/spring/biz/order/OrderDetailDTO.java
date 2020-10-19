package com.spring.biz.order;

public class OrderDetailDTO {
    private int order_seq;
    private String food1;
    private String food2;
    private String food3;
    private String food4;
    private String food5;
	public int getOrder_seq() {
		return order_seq;
	}
	public void setOrder_seq(int order_seq) {
		this.order_seq = order_seq;
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
		return "OrderDetailDTO [order_seq=" + order_seq + ", food1=" + food1 + ", food2=" + food2 + ", food3=" + food3
				+ ", food4=" + food4 + ", food5=" + food5 + "]";
	}
    
}
