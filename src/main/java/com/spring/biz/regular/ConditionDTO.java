package com.spring.biz.regular;

public class ConditionDTO {
	private String name;
	private String store_img;
	private String condition;
	private int condition_day;
	private int condition_value;
	private int myValue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStore_img() {
		return store_img;
	}

	public void setStore_img(String store_img) {
		this.store_img = store_img;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getCondition_day() {
		return condition_day;
	}

	public void setCondition_day(int condition_day) {
		this.condition_day = condition_day;
	}

	public int getCondition_value() {
		return condition_value;
	}

	public void setCondition_value(int condition_value) {
		this.condition_value = condition_value;
	}

	public int getMyValue() {
		return myValue;
	}

	public void setMyValue(int myValue) {
		this.myValue = myValue;
	}

	@Override
	public String toString() {
		return "ConditionDTO [name=" + name + ", store_img=" + store_img + ", condition=" + condition
				+ ", condition_day=" + condition_day + ", condition_value=" + condition_value + ", myValue=" + myValue
				+ "]";
	}
}
