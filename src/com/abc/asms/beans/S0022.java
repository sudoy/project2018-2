package com.abc.asms.beans;

import java.time.LocalDate;

public class S0022 {
	private int saleId;
	private LocalDate saleDate;
	private String name;
	private String categoryName;
	private String tradeName;
	private int unitPrice;
	private int saleNumber;
	private int sum;
	private String note;


	public int getSale_id() {
		return saleId;
	}

	public void setSale_id(int saleId) {
		this.saleId = saleId;
	}

	public LocalDate getSale_date() {
		return saleDate;
	}

	public void setSale_date(LocalDate saleDate) {
		this.saleDate = saleDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrade_name() {
		return tradeName;
	}

	public void setCategory_name(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategory_name() {
		return categoryName;
	}

	public void setTrade_name(String tradeName) {
		this.tradeName = tradeName;
	}

	public int getUnit_price() {
		return unitPrice;
	}

	public void setUnit_price(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getSale_number() {
		return saleNumber;
	}

	public void setSale_number(int saleNumber) {
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

	public S0022(int saleId, LocalDate saleDate, String name, String categoryName, String tradeName, int price, int count,
			int sum, String note) {
		super();
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.name = name;
		this.categoryName = categoryName;
		this.tradeName = tradeName;
		this.unitPrice = price;
		this.saleNumber = count;
		this.sum = sum;
		this.note = note;

	}





}
