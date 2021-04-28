package com.elior.beans;

import java.util.Date;

import com.elior.utils.CommandLineTable.CommandLineTableIntf;

public class Coupon implements CommandLineTableIntf {
	private int id;
	private int companyId;
	private Category category;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private int amount;
	private float price;
	private String image;

	public Coupon(int id, int companyId, Category category, String title, String description, Date startDate,
			Date endDate, int amount, float price, String image) {
		this(companyId, category, title, description, startDate, endDate, amount, price, image);
		this.id = id;
	}

	public Coupon(int companyId, Category category, String title, String description, Date startDate, Date endDate,
			int amount, float price, String image) {
		setCompanyId(companyId);
		setCategory(category);
		setTitle(title);
		setDescription(description);
		setStartDate(startDate);
		setEndDate(endDate);
		setAmount(amount);
		setPrice(price);
		setImage(image);
	}

	public int getCategoryId() {
		return category.getValue();
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	@Override
	public String[] asRow() {
		return new String[] { getId() + "", getCompanyId() + "", getCategory() + "", getTitle(), getDescription(),
				getStartDate() + "", getEndDate() + "", getAmount() + "", getPrice() + "", getImage() + "" };
	}

	@Override
	public String[] getHeder() {
		return new String[] { "Id", "CompanyId", "Category", "Title", "Description", "Start Date", "End Date", "Amount",
				"Price", "Image" };
	}
}
