package com.ljd.java.beans.model;

public class User {

	public User() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name:" + name + ", sex:" + sex + ", age:(ah,That's a secret!^_^)" 
				+ ", height:" + height;
	}

	String name;
	String sex;
	public void setUserName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	int age;
	public int getAge() {
		return age;
	}
	
	public Integer height;
}
