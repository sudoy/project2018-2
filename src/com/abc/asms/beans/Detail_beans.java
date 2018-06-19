package com.abc.asms.beans;

import java.time.LocalDate;

public class Detail_beans {
	private int saleId;
	private LocalDate saleDate;
	private String name;
	private int categoryId;
	private int accountId;
	private String tradeName;
	private int unitPrice;
	private int saleNumber;
	private int sum;
	private String note;

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

	public LocalDate getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDate saleDate) {
		this.saleDate = saleDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getTradeName() {
		return tradeName;
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

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Detail_beans(int saleId, LocalDate saleDate, String name, int categoryId, int accountId, String tradeName, int price, int count,
			int sum, String note) {
		super();
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.name = name;
		this.categoryId = categoryId;
		this.accountId = accountId;
		this.tradeName = tradeName;
		this.unitPrice = price;
		this.saleNumber = count;
		this.sum = sum;
		this.note = note;
	}
}
