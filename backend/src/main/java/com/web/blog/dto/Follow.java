package com.web.blog.dto;

public class Follow {
	private String followId;
	private String memberId;
	private String bandId;
	
	public Follow() {
	}

	public Follow(String followId, String memberId, String bandId) {
		this.followId = followId;
		this.memberId = memberId;
		this.bandId = bandId;
	}

	public String getFollowId() {
		return followId;
	}

	public void setFollowId(String followId) {
		this.followId = followId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getBandId() {
		return bandId;
	}

	public void setBandId(String bandId) {
		this.bandId = bandId;
	}

	
	
}
