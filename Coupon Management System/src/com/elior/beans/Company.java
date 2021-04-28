package com.elior.beans;

import java.util.ArrayList;
import java.util.List;

import com.elior.utils.CommandLineTable.CommandLineTableIntf;

public class Company implements CommandLineTableIntf {
	private int id;
	private String name;
	private String email;
	private String password;
	private List<Coupon> coupons = new ArrayList<>();

	public Company(int id, String name, String email, String password) {
		this(name, email, password);
		setId(id);
	}

	public Company(String name, String email, String password) {
		setName(name);
		setEmail(email);
		setPassword(password);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String[] asRow() {
		return new String[] { getId() + "", getName(), getEmail(), getPassword() };
	}

	@Override
	public String[] getHeder() {
		return new String[] { "Id", "Name", "Email", "Password" };
	}
}
