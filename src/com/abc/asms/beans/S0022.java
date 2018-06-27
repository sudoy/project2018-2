package com.abc.asms.beans;

import java.sql.Date;

public class S0022 {
	private int saleId;
	private Date saleDate;
	private String name;
	private String categoryName;
	private String tradeName;
	private int unitPrice;
	private int saleNumber;
	private String note;

	public S0022(int saleId, Date saleDate, String name, String categoryName, String tradeName, int price, int count,
			String note) {
		super();
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.name = name;
		this.categoryName = categoryName;
		this.tradeName = tradeName;
		this.unitPrice = price;
		this.saleNumber = count;
		this.note = note;

	}

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getSaleNumber() {
		return saleNumber;
	}

	public void setSaleNumber(int saleNumber) {
		this.saleNumber = saleNumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
