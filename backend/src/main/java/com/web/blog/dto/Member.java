package com.web.blog.dto;

public class Member {
	private String id;
	private String email;
	private String name;
	private String pw;
	private String phone;
	private String img;
	private String profile;
	private String manager;
	
	public Member() {
	}

	public Member(String id, String email, String name, String pw, String phone, String img, String profile,
			String manager) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.pw = pw;
		this.phone = phone;
		this.img = img;
		this.profile = profile;
		this.manager = manager;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
