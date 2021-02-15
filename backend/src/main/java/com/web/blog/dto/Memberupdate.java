package com.web.blog.dto;

public class Memberupdate {
	private String memberId;
	private String name;
	private String phone;
	private String profile;
	
	public Memberupdate() {
	}

	public Memberupdate(String memberId, String name, String phone, String profile) {
		this.memberId = memberId;
		this.name = name;
		this.phone = phone;
		this.profile = profile;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	
	
	
	
	
	
}
