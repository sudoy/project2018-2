package com.abc.asms.beans;

public class InsertSales {
	private String saleDate;
	private String accountId;
	private String categoryId;
	private String tradeName;
	private String unitPrice;
	private String saleNumber;
	private String note;

	public InsertSales(String saleDate, String accountId, String categoryId, String tradeName, String unitPrice,
			String saleNumber, String note) {
		super();
		this.saleDate = saleDate;
		this.accountId = accountId;
		this.categoryId = categoryId;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.note = note;
	}

	public String getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getSaleNumber() {
		return saleNumber;
	}
	public void setSaleNumber(String saleNumber) {
		this.saleNumber = saleNumber;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

}
