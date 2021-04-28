package com.elior.beans;
import java.util.ArrayList;
import java.util.List;

import com.elior.utils.CommandLineTable.CommandLineTableIntf;

public class Customer implements CommandLineTableIntf {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<Coupon> coupons = new ArrayList<>();

	public Customer(int id, String firstName, String lastName, String email, String password) {
		this(firstName, lastName, email, password);
		setId(id);
	}

	public Customer(String firstName, String lastName, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
		return new String[] {getId()+"", getFirstName(),getLastName(),getEmail(), getPassword()};
	}

	@Override
	public String[] getHeder() {
		return new String[] {"Id", "First Name", "Last Name", "Email", "Password"};
	}
}
