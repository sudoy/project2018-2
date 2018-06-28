package com.abc.asms.beans;

public class InsertAccounts {
	private String name;
	private String mail;
	private String password1;
	private String password2;
	private String authority1;
	private String authority2;

	public InsertAccounts(String name, String mail, String password1, String password2, String authority1,
			String authority2) {
		super();
		this.name = name;
		this.mail = mail;
		this.password1 = password1;
		this.password2 = password2;
		this.authority1 = authority1;
		this.authority2 = authority2;
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
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
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
