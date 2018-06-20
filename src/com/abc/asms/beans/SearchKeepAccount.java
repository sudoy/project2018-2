package com.abc.asms.beans;

public class SearchKeepAccount {
	private String accountName;
	private String mail;
	private String saleAuthority;
	private String accountAuthority;
	public SearchKeepAccount(String accountName, String mail,String saleAuthority,String accountAuthority) {
		super();

		this.accountName = accountName;
		this.mail = mail;
		this.saleAuthority = saleAuthority;
		this.accountAuthority = accountAuthority;

	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getSaleAuthority() {
		return saleAuthority;
	}
	public void setSaleAuthority(String saleAuthority) {
		this.saleAuthority = saleAuthority;
	}
	public String getAccountAuthority() {
		return accountAuthority;
	}
	public void setAccountAuthority(String accountAuthority) {
		this.accountAuthority = accountAuthority;
	}


}
