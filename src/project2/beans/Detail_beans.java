package project2.beans;

import java.time.LocalDate;

public class Detail_beans {
	private LocalDate sale_date;
	private String name;
	private String category_name;
	private String trade_name;
	private int unit_price;
	private int sale_number;
	private int sum;
	private String note;
	private int sale_id;

	public Detail_beans() {

	}

	public LocalDate getSale_date() {
		return sale_date;
	}

	public void setSale_date(LocalDate sale_date) {
		this.sale_date = sale_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getTrade_name() {
		return trade_name;
	}

	public void setTrade_name(String trade_name) {
		this.trade_name = trade_name;
	}

	public int getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(int price) {
		this.unit_price = price;
	}

	public int getSale_number() {
		return sale_number;
	}

	public void setSale_number(int count) {
		this.sale_number = count;
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

	public int getSale_id() {
		return sale_id;
	}

	public void setSale_id(int sale_id) {
		this.sale_id = sale_id;
	}

	public Detail_beans(LocalDate sale_date, String name, String category_name, String trade_name, int price, int count,
			int sum, String note, int sale_id) {
		super();
		this.sale_date = sale_date;
		this.name = name;
		this.category_name = category_name;
		this.trade_name = trade_name;
		this.unit_price = price;
		this.sale_number = count;
		this.sum = sum;
		this.note = note;
		this.sale_id = sale_id;
	}





}
