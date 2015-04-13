package com.raipeng.appAB.model;

/**
 * @author Administrator
 *个人实体
 */
public class PersonEntity {
	private String name;
	private String pinYinName;
	private int customerDegree ;
	private String phone_num;
public String getIcon() {
		return icon;
	}

	public String getPhone_num() {
	return phone_num;
}

public PersonEntity() {
		super();
	}

public void setPhone_num(String phone_num) {
	this.phone_num = phone_num;
}

	public int getCustomerDegree() {
	return customerDegree;
}

public void setCustomerDegree(int customerDegree) {
	this.customerDegree = customerDegree;
}

	public void setIcon(String icon) {
		this.icon = icon;
	}

private String icon;
	public PersonEntity(String name) {
		super();
		this.name = name;
	}

	public PersonEntity(String name, String pinYinName) {
		super();
		this.name = name;
		this.pinYinName = pinYinName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinYinName() {
		return pinYinName;
	}

	public void setPinYinName(String pinYinName) {
		this.pinYinName = pinYinName;
	}

}
