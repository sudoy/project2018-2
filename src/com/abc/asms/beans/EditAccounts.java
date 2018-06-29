package com.abc.asms.beans;

public class EditAccounts {
	private String accountId;
	private String name;
	private String mail;
	private String password;
	private String passwordc;
	private String authority1;
	private String authority2;

	public EditAccounts(String accountId, String name, String mail, String password, String passwordc, String authority1,
			String authority2) {
		super();
		this.accountId = accountId;
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.passwordc = passwordc;
		this.authority1 = authority1;
		this.authority2 = authority2;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordc() {
		return passwordc;
	}
	public void setPasswordc(String passwordc) {
		this.passwordc = passwordc;
	}
	public String getAuthority1() {
		return authority1;
	}
	public void setAuthority1(String authority1) {
		this.authority1 = authority1;
	}
	public String getAuthority2() {
		return authority2;
	}
	public void setAuthority2(String authority2) {
		this.authority2 = authority2;
	}


}
