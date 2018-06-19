package com.abc.asms.beans;

public class SearchKeepS {

	private String saleDate1;
	private String saleDate2;
	private String accountName;
	private String[] categoryName;
	private String tradeName;
	private String note;
	public SearchKeepS( String saleDate1,String saleDate2, String accountName, String[] categoryName, String tradeName, String note) {
		super();

		this.saleDate1 = saleDate1;
		this.saleDate2 = saleDate2;
		this.accountName = accountName;
		this.categoryName = categoryName;
		this.tradeName = tradeName;
		this.note = note;

	}

	public String getSaleDate1() {
		return saleDate1;
	}
	public void setSaleString1(String saleDate1) {
		this.saleDate1 = saleDate1;
	}
	public String getSaleDate2() {
		return saleDate2;
	}
	public void setSaleDate2(String saleDate2) {
		this.saleDate2 = saleDate2;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String[] getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String[] categoryName) {
		this.categoryName = categoryName;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}


}
