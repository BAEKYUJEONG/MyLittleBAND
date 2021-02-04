package com.web.blog.dto;

public class Member {
	private String memberId;
	private String email;
	private String name;
	private String pw;
	private String phone;
	private String img;
	private String profile;
	private String manager;
	private String emailcheck;
	private String authkey;
	
	public Member() {
	}

	public Member(String email, String name, String pw, String phone) {
		this.email = email;
		this.name = name;
		this.pw = pw;
		this.phone = phone;
	}

	public Member(String memberId, String email, String name, String pw, String phone, String img, String profile,
			String manager, String emailcheck, String authkey) {
		this.memberId = memberId;
		this.email = email;
		this.name = name;
		this.pw = pw;
		this.phone = phone;
		this.img = img;
		this.profile = profile;
		this.manager = manager;
		this.emailcheck = emailcheck;
		this.authkey = authkey;
	}

	public String getEmailcheck() {
		return emailcheck;
	}

	public void setEmailcheck(String emailcheck) {
		this.emailcheck = emailcheck;
	}

	public String getAuthkey() {
		return authkey;
	}

	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	
	
	
}
