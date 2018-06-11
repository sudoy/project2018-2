package project2.beans;

import java.sql.Date;

public class Sales {
	private int saleId;
	private Date saleDate;
	private int accountId;
	private int categoryId;
	private String tradeName;
	private int unitPrice;
	private int saleNumber;
	private String note;

	public Sales(int saleId, Date saleDate, int accountId, int categoryId, String tradeName, int unitPrice,
			int saleNumber, String note) {
		super();
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.accountId = accountId;
		this.categoryId = categoryId;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
		this.note = note;
	}

	public Sales(int saleId, Date saleDate, int categoryId, String tradeName, int unitPrice, int saleNumber) {
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.categoryId = categoryId;
		this.tradeName = tradeName;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
	}



	public Sales(int saleId) {
		this.saleId = saleId;
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
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

}
