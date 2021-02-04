package com.web.blog.dto;

import io.swagger.annotations.ApiModelProperty;

public class FollowReq {
	@ApiModelProperty(example = "1")
	private String memberId;
	@ApiModelProperty(example = "5")
	private String bandId;
	
	public FollowReq() {
	}

	public FollowReq(String memberId, String bandId) {
		this.memberId = memberId;
		this.bandId = bandId;
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
