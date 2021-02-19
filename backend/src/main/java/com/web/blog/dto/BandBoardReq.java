package com.web.blog.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class BandBoardReq {
	private String title;
	private String content;
	private String memberId;
	private String bandId;
	
	public BandBoardReq() {
	}

	

	public BandBoardReq(String title, String content, String memberId, String bandId) {
		this.title = title;
		this.content = content;
		this.memberId = memberId;
		this.bandId = bandId;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
