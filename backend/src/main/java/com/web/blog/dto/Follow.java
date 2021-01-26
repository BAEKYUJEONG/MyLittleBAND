package com.web.blog.dto;

public class Follow {
	private String id;
	private String memberid;
	private String bandid;
	
	public Follow() {
	}

	public Follow(String id, String memberid, String bandid) {
		this.id = id;
		this.memberid = memberid;
		this.bandid = bandid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getBandid() {
		return bandid;
	}

	public void setBandid(String bandid) {
		this.bandid = bandid;
	}
	
	
}
